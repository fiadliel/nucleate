package nucleate

import scalaz.Foldable

@annotation.implicitNotFound(msg = """ Type ${A} is not supported by AWS Properties.

Supported types:
 - String
 - BigDecimal, Double, Float
 - BigInt, Long, Int, Short
 - Foldable[_] for all scalar types
 - custom types which implement the AWSParameterType typeclass""")
trait AWSParameter[A] {
  def asString(a: A): String
}

trait AWSScalarParameter[A] extends AWSParameter[A]

object AWSParameter {
  def apply[A: AWSParameter] = implicitly[AWSParameter[A]]

  implicit val stringInstance: AWSScalarParameter[String] = new AWSScalarParameter[String] {
    override def asString(a: String): String = a
  }

  implicit val shortInstance: AWSScalarParameter[Short] = new AWSScalarParameter[Short] {
    override def asString(s: Short): String = s.toString
  }

  implicit val intInstance: AWSScalarParameter[Int] = new AWSScalarParameter[Int] {
    override def asString(i: Int): String = i.toString
  }

  implicit val longInstance: AWSScalarParameter[Long] = new AWSScalarParameter[Long] {
    override def asString(l: Long): String = l.toString
  }

  implicit val floatInstance: AWSScalarParameter[Float] = new AWSScalarParameter[Float] {
    override def asString(d: Float): String = d.toString
  }

  implicit val doubleInstance: AWSScalarParameter[Double] = new AWSScalarParameter[Double] {
    override def asString(d: Double): String = d.toString
  }

  implicit val bigIntegerInstance: AWSScalarParameter[BigInt] = new AWSScalarParameter[BigInt] {
    override def asString(a: BigInt): String = a.toString
  }

  implicit val bigDoubleInstance: AWSScalarParameter[BigDecimal] = new AWSScalarParameter[BigDecimal] {
    override def asString(a: BigDecimal): String = a.toString
  }

  implicit val regionInstance: AWSScalarParameter[Region] = new AWSScalarParameter[Region] {
    override def asString(rgn: Region) = rgn.toString
  }

  implicit val availabilityZoneInstance: AWSScalarParameter[AvailabilityZone] = new AWSScalarParameter[AvailabilityZone] {
    override def asString(azn: AvailabilityZone) = azn.name
  }
}
