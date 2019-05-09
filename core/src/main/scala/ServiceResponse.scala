package helm

import io.circe._

/** Case class representing a service as returned from an API call to Consul */
final case class ServiceResponse(
  service:           String,
  id:                String,
  tags:              List[String],
  address:           String,
  port:              Int,
  enableTagOverride: Boolean,
  createIndex:       Long,
  modifyIndex:       Long
)

object ServiceResponse {
  implicit def ServiceResponseDecoder: Decoder[ServiceResponse] =
    Decoder.instance { c =>
      for {
        id                <- c.downField("ID").as[String]
        address           <- c.downField("Address").as[String]
        enableTagOverride <- c.downField("EnableTagOverride").as[Boolean]
        createIndex       <- c.downField("CreateIndex").as[Long]
        modifyIndex       <- c.downField("ModifyIndex").as[Long]
        port              <- c.downField("Port").as[Int]
        service           <- c.downField("Service").as[String]
        tags              <- c.downField("Tags").as[List[String]]
      } yield ServiceResponse(service, id, tags, address, port, enableTagOverride, createIndex, modifyIndex)
    }
}
