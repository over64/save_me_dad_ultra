package com.catinthedark.savemedad

import com.catinthedark.savemedad.lib.{RouteMachine, YieldUnit}

/**
 * Created by over on 14.12.14.
 */
class GameState(val machine: RouteMachine) extends YieldUnit[Boolean] {
  override def done[U >: Boolean](value: U): Unit = machine.doRoute(value)

  override def toString = "GameState"

  override def onActivate(): Unit = {

  }

  override def onExit(): Unit = {

  }

  override def run(delta: Float): Unit = {

  }
}
