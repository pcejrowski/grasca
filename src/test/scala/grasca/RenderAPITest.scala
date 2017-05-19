package grasca

import java.time.Instant

import grasca.model.render.RenderedValues
import org.scalatest.{FlatSpec, Matchers}

class RenderAPITest extends FlatSpec with Matchers {

  behavior of "A RenderAPI"
  val host: String = "localhost"
  import sext._
  it should "support my real-life use case" in {
    val renderAPI: RenderAPI = new RenderAPI(host)
    val maybeExpanded: Option[RenderedValues] = renderAPI.values("todo", from = Instant.now.minusSeconds(600))
    println(maybeExpanded.get.valueTreeString)
  }
}
