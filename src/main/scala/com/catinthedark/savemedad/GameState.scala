package com.catinthedark.savemedad

import com.catinthedark.savemedad.lib._
import com.catinthedark.savemedad.units.{Control, Physics, View}

/**
 * Created by over on 14.12.14.
 */
class GameState extends YieldUnit[Boolean] with Deferred {

  override def toString = "Game"
  val shared = new Shared
  val view = new View(shared)
  val control = new Control()

  control.onShoot.addPort(view.onShoot(_))

  val units = Seq(view, new Physics(shared), control)

  override def onActivate(): Unit = {

    units.foreach(_.onActivate())
  }

  override def onExit(): Unit = {
    units.foreach(_.onExit())
  }

  override def run(delta: Float): Option[Boolean] = {
    units.foreach(_.run(delta))
    None
  }
}
