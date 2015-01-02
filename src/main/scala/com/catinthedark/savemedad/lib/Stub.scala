package com.catinthedark.savemedad.lib

/**
 * Created by over on 13.12.14.
 */
abstract class Stub(name: String, val machine: RouteMachine) extends YieldUnit[Unit] {

  override def done[U >: Unit](value: U): Unit = machine.doRoute(value)

  override def toString = name

  override def onActivate() = {}

  override def onExit() = {}

  override def run(delay: Float) = {}
}
