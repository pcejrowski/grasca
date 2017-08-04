package com.github.pcejrowski

import java.time.Duration

import scala.collection.immutable.ListMap

package object grasca {

  private[grasca] val WEEK: Duration = Duration.ofDays(7)
  private[grasca] val DAY: Duration = Duration.ofDays(1)

  private[grasca] implicit def int2bool(x: Int): Boolean = {
    if (x != 0 && x != 1) throw new AssertionError(s"API returned value $x in dichotomous field")
    if (x == 1) true else false
  }

  private[grasca] implicit def bool2intString(x: Boolean): String = {
    if (x) "1" else "0"
  }

  private[grasca] implicit class ToListMap[A, B](seq: Seq[(A, B)]) {
    def toListMap: ListMap[A, B] = ListMap(seq: _*)
  }

}
