package nucleate

import nucleate.Regions.{`US-WEST-1`, `US-EAST-1`}
import shapeless.Id

case class AvailabilityZone(region: Region, name: String)

object AvailabilityZones {
  val `US-EAST-1A`: Id[AvailabilityZone] = AvailabilityZone(`US-EAST-1`, "us-east-1a")
  val `US-WEST-1A`: Id[AvailabilityZone] = AvailabilityZone(`US-WEST-1`, "us-west-1a")
}
