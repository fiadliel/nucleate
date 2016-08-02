/**
 * ap-northeast-1
 * Asia Pacific (Tokyo)
 * ap-southeast-1
 * Asia Pacific (Singapore)
 * ap-southeast-2
 * Asia Pacific (Sydney)
 * eu-central-1
 * EU (Frankfurt)
 * eu-west-1
 * EU (Ireland)
 * sa-east-1
 * South America (Sao Paulo)
 * us-east-1
 * US East (N. Virginia)
 * us-west-1
 * US West (N. California)
 * us-west-2
 * US West (Oregon)
 */

package nucleate

import shapeless._

case class Region(name: String)

object Regions {
  val `AP-NORTHEAST-1`: Id[Region] = Region("ap-northeast-1")
  val `AP-SOUTHEAST-1`: Id[Region] = Region("ap-southeast-1")
  val `US-EAST-1`: Id[Region] = Region("us-east-1")
  val `US-WEST-1`: Id[Region] = Region("us-west-1")
}