package com.github.pcejrowski.grasca

import com.github.pcejrowski.grasca.model.expand.Expansion
import com.github.pcejrowski.grasca.model.find.{Metric, Metrics}
import com.github.pcejrowski.grasca.model.index.Index
import com.github.pcejrowski.wiremock.WiremockServer
import com.github.tomakehurst.wiremock.client.WireMock._
import org.scalatest.{FlatSpec, Matchers}

class MetricsAPITest extends FlatSpec with Matchers with WiremockServer {

  override val host: String = "localhost"
  override val port: Int = 8081

  val testee = new MetricsAPI(host, port)

  behavior of "A MetricsAPI"

  it should "parse all metrics from the server response" in {
    val findResponse =
      """
        |[
        |  {
        |    "leaf": 0,
        |    "context": {},
        |    "text": "dummy",
        |    "expandable": 1,
        |    "id": "dummy",
        |    "allowChildren": 1
        |  },
        |  {
        |    "leaf": 0,
        |    "context": {},
        |    "text": "dummy2",
        |    "expandable": 1,
        |    "id": "dummy2",
        |    "allowChildren": 1
        |  }
        |]
      """.stripMargin
    val urlRegexp = "\\/metrics\\/find\\?query=dummy(.*)"
    stubFor(get(urlMatching(urlRegexp))
      .willReturn(
        aResponse()
          .withBody(findResponse)
          .withStatus(200)))

    val actual: Option[Metrics] = testee.find("dummy")
    val expected = Some(List(
      Metric("dummy", "dummy", Map(), leaf = false, expandable = true, allowChildren = true),
      Metric("dummy2", "dummy2", Map(), leaf = false, expandable = true, allowChildren = true)
    ))
    actual should be(expected)
  }

  it should "parse expanded metrics from the server response" in {
    val expandResponse =
      """
        |{
        |  "results": [
        |    "dummy",
        |    "dummy2"
        |  ]
        |}
      """.stripMargin
    val urlRegexp = "\\/metrics\\/expand\\?query=dummy(.*)"
    stubFor(get(urlMatching(urlRegexp))
      .willReturn(aResponse().withBody(expandResponse).withStatus(200)))

    val actual: Option[Expansion] = testee.expand("dummy")
    val expected = Some(List("dummy", "dummy2"))
    actual should be(expected)
  }

  it should "parse metrics from the server response" in {
    val indexResponse =
      """
        |[
        |  "dummy",
        |  "dummy2"
        |]
      """.stripMargin
    stubFor(get("/metrics/index.json")
      .willReturn(aResponse().withBody(indexResponse).withStatus(200)))

    val actual: Option[Index] = testee.index
    val expected = Some(List("dummy", "dummy2"))
    actual should be(expected)
  }
}
