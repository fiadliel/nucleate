package nucleate

import shapeless._
import record._
import shapeless.labelled.FieldType
import shapeless.syntax.SingletonOps
import shapeless.syntax.singleton._
import tag._
import shapeless.ops.record._

// literals
// parameter references

// pseudo parameters
// - AWS::AccountId
// - AWS::NotificationARNs
// - AWS::NoValue
// - AWS::Region
// - AWS::StackId
// - AWS::StackName

// mapping value
// intrinsic functions
// - base64
// - Condition
// - Fn::FindInMap
// - Fn::GetAtt
// - Fn::GetAZs
// - Fn::Join
// - Fn::Select
// - Ref

case class Template[ParamRec <: HList, ResRec <: HList](
    description: Option[String],
    parameters: ParamRec,
    resources: ResRec,
    outputs: Option[String]
)(implicit pConstraint: LUBConstraint[ParamRec, Parameter[_]]) {
  def parameter(k: Witness)(implicit sel: Selector[ParamRec, k.T]) = {
    sel(parameters)
  }

  def resource(k: Witness)(implicit sel: Selector[ResRec, k.T]) = {
    sel(resources)
  }

  def addParameter[V](k: Witness, v: V)(implicit ev: Updater[ParamRec, FieldType[k.T, V]]) = {
    this.copy(resources = ev(parameters, labelled.field[k.T](v)))
  }

  def addResource[V](k: Witness, v: V)(implicit ev: Updater[ResRec, FieldType[k.T, V]]) = {
    this.copy(resources = ev(resources, labelled.field[k.T](v)))
  }

}

