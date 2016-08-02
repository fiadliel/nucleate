package nucleate

import shapeless.ops.record.{Values, Selector}
import shapeless.{Id, UnaryTCConstraint, HList, Witness}

import scalaz.Unapply

//sealed trait ResourceId[A]
//trait AccountId extends ResourceId
//
//trait Region extends ResourceId
//trait StackId extends ResourceId
//trait StackName extends ResourceId
//trait NotificationARN extends ResourceId
//
//sealed trait Reference[A]
//case class Ref[A](name: String) extends Reference[A]
//case object AWSAccountId extends Reference[AccountId]
//case object AWSNotificationARNs extends Reference[List[NotificationARN]]
//case object NoValue extends Reference[Nothing]
//case object AWSRegion extends Reference[Region]
//case object AWSStackId extends Reference[StackId]
//case object AWSStackName extends Reference[StackName]

trait Value[F[_], A] {
  def v(a: F[A]): Option[A]
}

object Value {
  implicit def rawValue[A: AWSParameter]: Value[Id, A] = new Value[Id, A] {
    override def v(a: Id[A]): Option[A] = Some(a)
  }

  implicit def refValue[A: AWSParameter]: Value[Parameter, A] = new Value[Parameter, A] {
    override def v(a: Parameter[A]): Option[A] = a.default
  }
}
