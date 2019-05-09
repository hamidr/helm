package helm

import io.circe._

/** Case class representing a health check as returned from an API call to Consul */
final case class HealthCheckResponse(
  node:        String,
  checkId:     String,
  name:        String,
  status:      HealthStatus,
  notes:       String,
  output:      String,
  serviceId:   String,
  serviceName: String,
  serviceTags: List[String],
  createIndex: Long,
  modifyIndex: Long
)

object HealthCheckResponse {
  implicit def HealthCheckResponseDecoder: Decoder[HealthCheckResponse] =
    Decoder.instance(c =>
      for {
        node        <- c.downField("Node").as[String]
        checkId     <- c.downField("CheckID").as[String]
        name        <- c.downField("Name").as[String]
        status      <- c.downField("Status").as[HealthStatus]
        notes       <- c.downField("Notes").as[String]
        output      <- c.downField("Output").as[String]
        serviceId   <- c.downField("ServiceID").as[String]
        serviceName <- c.downField("ServiceName").as[String]
        serviceTags <- c.downField("ServiceTags").as[List[String]]
        createIndex <- c.downField("CreateIndex").as[Long]
        modifyIndex <- c.downField("ModifyIndex").as[Long]
      } yield HealthCheckResponse(
        node,
        checkId,
        name,
        status,
        notes,
        output,
        serviceId,
        serviceName,
        serviceTags,
        createIndex,
        modifyIndex)
    )
}
