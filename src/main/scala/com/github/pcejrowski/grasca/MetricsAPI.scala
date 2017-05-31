package com.github.pcejrowski.grasca

import java.time.Instant

import com.github.pcejrowski.grasca.model.expand.{ExpandResult, Expansion}
import com.github.pcejrowski.grasca.model.find.{FindResult, Metric, Metrics}
import com.github.pcejrowski.grasca.model.index.Index
import org.json4s._
import org.json4s.native.JsonMethods._

import scalaj.http._

/**
  * These API endpoints are useful for finding and listing metrics available in the system.
  *
  * @author pcejrowski
  * @param host - Graphite server address
  * @param port - Graphite server port
  */
class MetricsAPI(private val host: String, private val port: Int = 80) {

  private lazy val metricsEndpoint: String = s"http://$host:$port/metrics"

  private implicit val formats = DefaultFormats

  /**
    * Finds metrics under a given path.
    *
    * @param query     - The query to search for.
    * @param format    - The output format to use. Can be "completer" or "treejson". Default: "treejson" [TODO: completer not yet supported; create types]
    * @param wildcards - Whether to add a wildcard result at the end or no. Default: false
    * @param from      - Timestamp from which to consider metrics. Default: week ago
    * @param until     - Timestamp until which to consider metrics. Default: now
    */
  def find(query: String, format: String = "treejson", wildcards: Boolean = false, from: Instant = Instant.now.minus(WEEK), until: Instant = Instant.now): Option[Metrics] = {
    val response: HttpResponse[String] = Http(s"$metricsEndpoint/find")
      .param("query", query)
      .param("format", format)
      .param("wildcards", wildcards)
      .param("from", from.getEpochSecond.toString)
      .param("until", until.getEpochSecond.toString)
      .asString

    parse(response.body)
      .extractOpt[List[FindResult]]
      .map(_.map(Metric(_)))
  }

  /**
    * Expands the given query with matching paths.
    *
    * @param query       - The metrics query. Can be specified multiple times.
    * @param groupByExpr - Whether to return a flat list of results (false) or group them by query (true). Default: false
    * @param leavesOnly  - Whether to only return leaves (false) or both branches and leaves (true). Default: false // fixme: dobule-check correctness
    */
  def expand(query: String, groupByExpr: Boolean = false, leavesOnly: Boolean = false): Option[Expansion] = {
    val response: HttpResponse[String] = Http(s"$metricsEndpoint/expand")
      .param("query", query)
      .param("groupByExpr", groupByExpr)
      .param("leavesOnly", leavesOnly)
      .asString

    parse(response.body)
      .extractOpt[ExpandResult]
      .map(_.results)
  }

  /**
    * Walks the metrics tree and returns every metric found as a sorted JSON array.
    */
  def index: Option[Index] = {
    val response: HttpResponse[String] = Http(s"$metricsEndpoint/index.json")
      .asString
    parse(response.body)
      .extractOpt[Index]
  }

}

object MetricsAPI {
  def apply(host: String, port: Int = 80) = new MetricsAPI(host, port)
}