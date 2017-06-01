package com.github.pcejrowski.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import org.scalatest.{BeforeAndAfterEach, Suite}

trait WiremockServer extends BeforeAndAfterEach {

  this: Suite =>

  def host: String = "localhost"

  def port: Int = 8080

  val wireMockServer: WireMockServer = new WireMockServer(wireMockConfig().port(port))

  override def beforeEach {
    wireMockServer.start()
    WireMock.configureFor(host, wireMockServer.port())
  }

  override def afterEach {
    wireMockServer.stop()
  }
}
