package com.github.pcejrowski.grasca

import org.scalatest.{FlatSpec, Matchers}

class MetricsAPITest extends FlatSpec with Matchers {

  behavior of "A MetricsAPI"
  val host: String = "localhost"

  it should "find all metrics according to given query" in {
    val metricsAPI = new MetricsAPI(host)
    println(metricsAPI.find("spark"))
  }

  it should "expand given infix" in {
    val metricsAPI = new MetricsAPI(host)
    println(metricsAPI.expand("spark"))
  }

  it should "return every metric found" in {
    val metricsAPI = new MetricsAPI(host)
    println(metricsAPI.index)
  }
}
