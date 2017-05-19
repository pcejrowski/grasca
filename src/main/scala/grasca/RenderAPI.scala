package grasca

import java.time.Instant

import grasca.model.render.{RenderResult, RenderedValues}
import org.json4s._
import org.json4s.native.JsonMethods._

import scala.collection.immutable.ListMap
import scalaj.http._

/**
  * These API endpoints are useful for retrieving raw data.
  *
  * @author pcejrowski
  * @param host - Graphite server address
  * @param port - Graphite server port
  */
class RenderAPI(val host: String, val port: Int = 80) {

  private lazy val metricsEndpoint: String = s"http://$host:$port/render"

  implicit val formats = DefaultFormats

  /**
    * Renders values from the target.
    *
    * @param target - specifies a path identifying one or several metrics, optionally with functions acting on those metrics.
    * @param from   - Timestamp from which to consider target. Default: 24 hours ago
    * @param until  - Timestamp until which to consider targe. Default: now
    * @return
    */
  def values(target: String, from: Instant = Instant.now.minus(DAY), until: Instant = Instant.now): Option[RenderedValues] = {
    val response: HttpResponse[String] = Http(s"$metricsEndpoint/find")
      .param("target", target)
      .param("format", "json")
      .param("from", from.getEpochSecond.toString)
      .param("until", until.getEpochSecond.toString)
      .asString

    parse(response.body)
      .extractOpt[List[RenderResult]]
      .map(_.map(r => {
        val legend = r.target
        val values: List[Int] = r.datapoints.filter(_.head != null).sortBy(-_(1)).take(1).map(_.head)
        val meanValue: Double = values.foldLeft(0.0)(_ + _) / values.length
        legend -> meanValue.toInt
      }).sortBy(_._1)
      ).map(ListMap(_: _*))
  }
}
