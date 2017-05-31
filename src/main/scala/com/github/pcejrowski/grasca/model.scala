package com.github.pcejrowski.grasca

import java.time.Instant

import scala.collection.immutable.ListMap


private object model {

  object find {
    type Metrics = List[Metric]

    case class Metric(id: String, text: String, context: Object, leaf: Boolean, expandable: Boolean, allowChildren: Boolean)

    object Metric {
      def apply(metricsNode: FindResult): Metric = {
        import metricsNode._
        new Metric(id, text, context, leaf, expandable, allowChildren)
      }
    }

    private[grasca] case class FindResult(leaf: Int, context: Object, text: String, expandable: Int, id: String, allowChildren: Int)

  }

  object expand {

    type Expansion = List[String]

    private[grasca] case class ExpandResult(results: List[String])

  }

  object index {
    type Index = List[String]
  }

  object render {
    type RenderedValues = ListMap[String, ListMap[Instant, Long]]

    private[grasca] case class RenderResult(target: String, datapoints: List[List[Long]])

  }

}