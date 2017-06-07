package com.github.pcejrowski.grasca

import java.time.Instant

import com.github.pcejrowski.grasca.model.render.RenderedValues
import com.github.pcejrowski.wiremock.WiremockServer
import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, get, stubFor, urlMatching}
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable.ListMap

class RenderAPITest extends FlatSpec with Matchers with WiremockServer {

  val testee: RenderAPI = RenderAPI(host, port)
  behavior of "A RenderAPI"

  it should "parse rendered server response correctly" in {

    val indexResponse =
      """
        |[
        |  {
        |    "target": "1",
        |    "datapoints": [
        |      [
        |        2,
        |        1496158910
        |      ],
        |      [
        |        1,
        |        1496158920
        |      ],
        |      [
        |        null,
        |        1496158930
        |      ]
        |    ]
        |  },
        |  {
        |    "target": "2",
        |    "datapoints": [
        |      [
        |        4,
        |        1496158910
        |      ]
        |    ]
        |  }
        |]
      """.stripMargin
    val urlRegexp = "\\/render\\?target=dummy(.*)"
    stubFor(get(urlMatching(urlRegexp))
      .willReturn(aResponse().withBody(indexResponse).withStatus(200)))

    val actual: Option[RenderedValues] = testee.values("dummy")
    val expected: Option[ListMap[String, ListMap[Instant, Option[Long]]]] =
      Some(ListMap(
        "1" -> ListMap(
          Instant.ofEpochSecond(1496158930) -> None,
          Instant.ofEpochSecond(1496158920) -> Some(1),
          Instant.ofEpochSecond(1496158910) -> Some(2)),
        "2" -> ListMap(
          Instant.ofEpochSecond(1496158910) -> Some(4)
      )))
    actual should be(expected)
  }
}
