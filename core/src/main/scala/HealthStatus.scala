package helm

import io.circe._

sealed abstract class HealthStatus extends Product with Serializable

object HealthStatus {

  final case object Passing extends HealthStatus
  final case object Unknown extends HealthStatus
  final case object Warning extends HealthStatus
  final case object Critical extends HealthStatus

  def fromString(s: String): Option[HealthStatus] =
    s.toLowerCase match {
      case "passing"  => Some(Passing)
      case "warning"  => Some(Warning)
      case "critical" => Some(Critical)
      case "unknown"  => Some(Unknown)
      case _          => None
    }

  def toString(hs: HealthStatus): String =
    hs match {
      case Passing  => "passing"
      case Warning  => "warning"
      case Critical => "critical"
      case Unknown  => "unknown"
    }

  implicit val HealthStatusDecoder: Decoder[HealthStatus] =
    Decoder.instance { c =>
      c.as[String].flatMap { s =>
        fromString(s) match {
          case Some(r) => Right(r)
          case None => Left(DecodingFailure(s"invalid health status: $s", c.history))
        }
      }
    }

  implicit val HealthStatusEncoder: Encoder[HealthStatus] =
    Encoder.encodeString.contramap { hs => toString(hs) }
}
