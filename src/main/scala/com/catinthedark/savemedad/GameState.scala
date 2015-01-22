package com.catinthedark.savemedad

import com.catinthedark.savemedad.lib.{RouteMachine, YieldUnit}
import com.catinthedark.savemedad.units.{Control, Physics, View}

/**
 * Created by over on 14.12.14.
 */
class GameState(val machine: RouteMachine) extends YieldUnit[Boolean] {
  override def done[U >: Boolean](value: U): Unit = machine.doRoute(value)

  override def toString = "Game"
  val shared = new Shared
  val view = new View(shared)
  val control = new Control

  control.onShoot.addPort(view.onShoot(_))

  val units = Seq(view, new Physics(shared), control)

  override def onActivate(): Unit = {

    units.foreach(_.onActivate())
  }

  override def onExit(): Unit = {
    units.foreach(_.onExit())
  }

  override def run(delta: Float): Unit = {
    units.foreach(_.run(delta))
  }
}
