package grasca


object model {

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

}