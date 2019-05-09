package helm

import io.circe._

/** Case class representing a health check as returned from an API call to Consul */
final case class NodeResponse(
  id:              String,
  node:            String,
  address:         String,
  datacenter:      String,
  meta:            Map[String, String],
  taggedAddresses: TaggedAddresses,
  createIndex:     Long,
  modifyIndex:     Long
)

object NodeResponse {
  implicit def NodeResponseDecoder: Decoder[NodeResponse] =
    Decoder.instance(c =>
      for {
        id              <- c.downField("ID").as[String]
        node            <- c.downField("Node").as[String]
        address         <- c.downField("Address").as[String]
        datacenter      <- c.downField("Datacenter").as[String]
        meta            <- c.downField("Meta").as[Map[String, String]]
        taggedAddresses <- c.downField("TaggedAddresses").as[TaggedAddresses]
        createIndex     <- c.downField("CreateIndex").as[Long]
        modifyIndex     <- c.downField("ModifyIndex").as[Long]
      } yield NodeResponse(
        id,
        node,
        address,
        datacenter,
        meta,
        taggedAddresses,
        createIndex,
        modifyIndex)
    )
}

final case class TaggedAddresses(lan: String, wan: String)

object TaggedAddresses {
  implicit def TaggedAddressesDecoder: Decoder[TaggedAddresses] =
    Decoder.instance(c =>
      for {
        lan <- c.downField("lan").as[String]
        wan <- c.downField("wan").as[String]
      } yield TaggedAddresses(lan, wan)
    )
}
