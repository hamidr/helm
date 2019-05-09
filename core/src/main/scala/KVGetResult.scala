package helm

import io.circe._

/** Case class representing the response to a KV "Read Key" API call to Consul */
final case class KVGetResult(
  key:         String,
  value:       Option[String],
  flags:       Long,
  session:     Option[String],
  lockIndex:   Long,
  createIndex: Long,
  modifyIndex: Long
)

object KVGetResult {
  implicit def KVGetResultDecoder: Decoder[KVGetResult] =
    Decoder.instance(c =>
      for {
        key         <- c.downField("Key").as[String]
        value       <- c.downField("Value").as[Option[String]]
        flags       <- c.downField("Flags").as[Long]
        session     <- c.downField("Session").as[Option[String]]
        lockIndex   <- c.downField("LockIndex").as[Long]
        createIndex <- c.downField("CreateIndex").as[Long]
        modifyIndex <- c.downField("ModifyIndex").as[Long]
      } yield KVGetResult(
        key,
        value,
        flags,
        session,
        lockIndex,
        createIndex,
        modifyIndex)
    )
}
