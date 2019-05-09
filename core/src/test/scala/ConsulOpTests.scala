package helm

import io.circe._
import cats.effect.IO
import org.scalatest.{FlatSpec, Matchers}
import org.scalactic.TypeCheckedTripleEquals
import ConsulOp._

class ConsulOpTests extends FlatSpec with Matchers with TypeCheckedTripleEquals {
  val I = Interpreter.prepare[ConsulOp, IO]

  "getJson" should "return none right when get returns None" in {
    val interp = for {
      _ <- I.expectU[QueryResponse[Option[Array[Byte]]]] {
        case ConsulOp.KVGetRaw("foo", None, None) => IO.pure(QueryResponse(None, -1, true, -1))
      }
    } yield ()
    interp.run(kvGetJson[Json]("foo", None, None)).unsafeRunSync should equal(Right(QueryResponse(None, -1, true, -1)))
  }

  it should "return a value when get returns a decodeable value" in {
    val interp = for {
      _ <- I.expectU[QueryResponse[Option[Array[Byte]]]] {
        case ConsulOp.KVGetRaw("foo", None, None) => IO.pure(QueryResponse(Some("42".getBytes), -1, true, -1))
      }
    } yield ()
    interp.run(kvGetJson[Json]("foo", None, None)).unsafeRunSync should equal(Right(QueryResponse(Some(Json.fromInt(42)), -1, true, -1)))
  }

  it should "return an error when get returns a non-decodeable value" in {
    val interp = for {
      _ <- I.expectU[QueryResponse[Option[Array[Byte]]]] {
        case ConsulOp.KVGetRaw("foo", None, None) => IO.pure(QueryResponse(Some("{".getBytes), -1, true, -1))
      }
    } yield ()
    interp.run(kvGetJson[Json]("foo", None, None)).unsafeRunSync.left.map(_.getMessage) should equal(Left("exhausted input"))
  }
}
