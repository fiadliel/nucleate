package nucleate

import nucleate.Main.ISB
import shapeless.{CNil, :+:}

trait AWSResource[A] {
  def resourceType(a: A): String
  def asString(a: A): String
}

//case class VolumeType(name: String)
//object STANDARD extends VolumeType("standard")
//object IO1 extends VolumeType("io1")
//object IO2 extends VolumeType("io2")
//
//case class SnapshotId(name: String)
//case class EBSDevice(deleteOnTermination: Option[Boolean], encrypted: Option[Boolean], snapshotId: Option[SnapshotId], volumeSize: Int, volumeType: VolumeType)
//case class VirtualName(name: String)
//
//case class BlockDeviceMapping(deviceName: String, storage: Either[EBSDevice, VirtualName], unmap: Boolean)

case class EC2Instance(
  availabilityZone: ISB[AvailabilityZone]
)

object AWSResource {

  implicit val ec2Instance: AWSResource[EC2Instance] = new AWSResource[EC2Instance] {
    override def resourceType(a: EC2Instance): String = "AWS::EC2::Instance"
    override def asString(a: EC2Instance): String = a.toString
  }

}

//case class Resource[A: AWSResourceType](
//
//                                         )

//case class CloudFormationTemplate[A <: HList](
//                                  description: Option[String],
//                                 parameters: A,
//                                                resources:
//                                                  )

// Fn:Base64 useful because the template would otherwise be larger

// need a .apply(idx: Int) which converts to an Fn::Select for sequences of parameters?

