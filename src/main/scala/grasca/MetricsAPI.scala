package grasca

import java.time.{Duration, Instant}

import scalaj.http._

//todo:
// create consts for format, wildcards
/**
  * These API endpoints are useful for finding and listing metrics available in the system.
  *
  * @param host - Graphite server address
  * @param port - Graphite server port
  */
class MetricsAPI(val host: String, val port: Int) {

  private lazy val metricsEndpoint: String = s"http://$host:$port/metrics"

  private val WEEK: Duration = Duration.ofDays(7)

  /**
    * Finds metrics under a given path.
    *
    * @param query     - The query to search for.
    * @param format    - The output format to use. Can be "completer" or "treejson" (default).
    * @param wildcards - Whether to add a wildcard result at the end or no(default).
    * @param from      - Timestamp from which to consider metrics.
    * @param until     - Timestamp until which to consider metrics.
    */
  def find(query: String, format: String = "treejson", wildcards: Boolean = false, from: Instant = Instant.now.minus(WEEK), until: Instant = Instant.now): String = {
    val response: HttpResponse[String] = Http(s"$metricsEndpoint/find")
      .param("query", query)
      .param("format", format)
      .param("wildcards", if (wildcards) "1" else "0")
      .param("from", from.getEpochSecond.toString)
      .param("until", until.getEpochSecond.toString)
      .asString
    response.body
  }

  /**
    * Expands the given query with matching paths.
    *
    * @param query       - The metrics query. Can be specified multiple times.
    * @param groupByExpr - Whether to return a flat list of results(default) or group them by query.
    * @param leavesOnly  - Whether to only return leaves(default) or both branches and leaves.
    */
  def expand(query: String, groupByExpr: Boolean = false, leavesOnly: Boolean = false): String = {
    val response: HttpResponse[String] = Http(s"$metricsEndpoint/expand")
      .param("query", query)
      .param("groupByExpr", if (groupByExpr) "1" else "0")
      .param("leavesOnly", if (leavesOnly) "1" else "0")
      .asString
    response.body
  }

  /**
    * Walks the metrics tree and returns every metric found as a sorted JSON array.
    */
  def index: String = {
    val response: HttpResponse[String] = Http(s"$metricsEndpoint/index.json")
      .asString
    response.body
  }

}
