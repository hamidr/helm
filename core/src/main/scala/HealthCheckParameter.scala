package helm

import io.circe._
import io.circe.syntax._

/** Case class representing a health check as defined in the Register Service API calls */
final case class HealthCheckParameter(
  name:                           String,
  id:                             Option[String],
  interval:                       Option[Interval],
  notes:                          Option[String],
  deregisterCriticalServiceAfter: Option[Interval],
  serviceId:                      Option[String],
  initialStatus:                  Option[HealthStatus],
  http:                           Option[String],
  tlsSkipVerify:                  Option[Boolean],
  script:                         Option[String],
  dockerContainerId:              Option[String],
  tcp:                            Option[String],
  ttl:                            Option[Interval]
)

object HealthCheckParameter {
  implicit def HealthCheckParameterEncoder: Encoder[HealthCheckParameter] =
    Encoder.encodeJsonObject.contramapObject { h =>
      JsonObject(
        "Name"                           := h.name                           ,
        "CheckID"                        := h.id                             ,
        "Interval"                       := h.interval                       ,
        "Notes"                          := h.notes                          ,
        "DeregisterCriticalServiceAfter" := h.deregisterCriticalServiceAfter ,
        "ServiceID"                      := h.serviceId                      ,
        "Status"                         := h.initialStatus                  ,
        "HTTP"                           := h.http                           ,
        "TLSSkipVerify"                  := h.tlsSkipVerify                  ,
        "Script"                         := h.script                         ,
        "DockerContainerID"              := h.dockerContainerId              ,
        "TCP"                            := h.tcp                            ,
        "TTL"                            := h.ttl
      )
    }
}
