package nucleate

case class Parameter[A: AWSParameter](
  default: Option[A],
  allowedValues: Option[Seq[A]],
  noEcho: Boolean = false
)

object Parameter {
  def apply[A: AWSParameter]: Parameter[A] = Parameter[A](None, None)
}