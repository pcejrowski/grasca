package grasca

import org.scalatest.{FlatSpec, Matchers}

class MetricsAPITest extends FlatSpec with Matchers {

  behavior of "A MetricsAPI"


  it should "query all metrics properly" in {
    val metricsAPI = new MetricsAPI("localhost", 2003)
    println(metricsAPI.index)
  }
}
