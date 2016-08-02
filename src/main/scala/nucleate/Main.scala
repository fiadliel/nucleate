package nucleate

import shapeless._
import record._
import shapeless.labelled.FieldType
import shapeless.ops.coproduct.Inject
import shapeless.syntax.SingletonOps
import syntax.singleton._
import shapeless.record._

import Regions._
import AvailabilityZones._

object Main extends App {

  val region = Regions.`US-EAST-1`
  val availabilityZone = AvailabilityZones.`US-EAST-1A`

  val parameters = Record(
    region = Parameter(Some(region), None),
    az = Parameter(Some(availabilityZone), None)
  )

  type ISB[A] = Parameter[A] :+: Id[A] :+: If[A] :+: CNil

  trait Condition
  case class Equals[A](l: ISB[A], r: ISB[A]) extends Condition
  case class If[A](cond: Condition, t: ISB[A], f: ISB[A])
  case class Not(cond: Condition) extends Condition
  case class Or(conditions: Seq[Condition]) extends Condition
  case class And(conditions: Seq[Condition]) extends Condition

  //implicit def toL[A](a: A): ValOrRef[A] = Left(a)
  //implicit def toR[A](a: Parameter[A]): ValOrRef[A] = Right(a)
  implicit def toCoproduct[F[_], A](a: F[A])(implicit inj: Inject[ISB[A], F[A]]): ISB[A] = Coproduct[ISB[A]](a)

  val emptyCondition = new Condition {}

  case class ConditionSyntaxPartial[A](c: Condition, t: ISB[A]) {
    def `|`(f: ISB[A]): If[A] = If(c, t, f)
  }

  implicit class ConditionSyntax[A](c: Condition) {
    def ?(t: ISB[A]): ConditionSyntaxPartial[A] = ConditionSyntaxPartial(c, t)
  }

  implicit class ISBSyntax[A](l: ISB[A]) {
    def ===(r: ISB[A]): Equals[A] = Equals(l, r)
  }

  val resources = Record(
    instance = EC2Instance((parameters.get('region) === `US-EAST-1`) ? `US-EAST-1A` | `US-WEST-1A`),
    instance2 = EC2Instance(parameters.get('az))
  )

  val template = Template(Some("test template"), parameters, resources, None)

  //val template2 = template.addInstance("az")

  val a = template.parameter('region)
  val b = template.parameters.get('az)
  val c = template.resources.get('instance)
  val d = template.resources.get('instance2)

  println(a)
  println(b)
  println(c)
  println(d)

  //val addInstance = template.addResource('instance3, EC2Instance(Coproduct(availabilityZone)))

  //println(addInstance.resources.get('instance3))

  //  def p[F[_], A](i: F[A]): String = {
  //    implicitly[Value[F, A]].v(i).getOrElse("")
  //  }
  //
  //  println("blah blah" + p(c))
  //println(b)

  //println(a.default == b.availabilityZone)
}
