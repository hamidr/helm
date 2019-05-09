package helm

import io.circe._

/** Case class representing the response to the Health API's List Nodes For Service function */
final case class HealthNodesForServiceResponse(
  node:        NodeResponse,
  service:     ServiceResponse,
  checks:      List[HealthCheckResponse]
)

object HealthNodesForServiceResponse {
  implicit def HealthNodesForServiceResponseDecoder: Decoder[HealthNodesForServiceResponse] =
    Decoder.instance(c =>
      for {
        node    <- c.downField("Node").as[NodeResponse]
        service <- c.downField("Service").as[ServiceResponse]
        checks  <- c.downField("Checks").as[List[HealthCheckResponse]]
      } yield HealthNodesForServiceResponse(node, service, checks)
    )
}
