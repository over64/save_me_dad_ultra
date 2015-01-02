package com.catinthedark.savemedad.lib

import scala.collection.mutable

/**
 * Created by over on 13.12.14.
 */

class RouteMachine {
  private val routes = mutable.ListBuffer[(YieldUnit[Any], (Any => YieldUnit[Any]))]()
  private var currentUnit: ComputeUnit = _

  def doRoute[T](cond: T): Unit = {
    println(s"begin transition from $currentUnit")
    val from = currentUnit
    from.onExit()

    val routeFn = routes.filter(route => {
      val (routeFrom, _) = route
      routeFrom eq from //reference equal
    }).map(route => route._2)
      .headOption
      .getOrElse(throw new RuntimeException(s"Could not find route function from $from"))

    currentUnit = routeFn(cond)
    currentUnit.onActivate()
    println(s"end transition to $currentUnit")
  }

  def addRoute[T >: Any](from: YieldUnit[T], routeFn: T => YieldUnit[Any]): Unit = {
    routes += ((from, routeFn));
  }

  def start(unit: ComputeUnit) = {
    currentUnit = unit
    currentUnit.onActivate()
  }

  def run(delta: Float): Unit = currentUnit.run(delta)
}
