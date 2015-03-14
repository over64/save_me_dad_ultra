package com.catinthedark.savemedad

import com.catinthedark.savemedad.lib._
import com.catinthedark.savemedad.units.{Control, Physics, View}

/**
 * Created by over on 14.12.14.
 */
class GameState extends YieldUnit[Boolean] {

  override def toString = "Game"
  val shared = new Shared
  val view = new View
  val control = new Control with LocalDeferred with Interval {
    override val interval = 0.2f
  }
  val units = Seq(control, view)

  control.onShoot + (view.onShoot(_))
  control.onPause + (onPause(_))

  var paused = false

  def onPause(ignored: Unit): Unit = paused = !paused

  override def onActivate(): Unit = {
    units.foreach(_.onActivate())
  }

  override def onExit(): Unit = {
    units.foreach(_.onExit())
  }

  override def run(delta: Float): Option[Boolean] = {
    if (!paused)
      units.foreach(_.run(delta))
    None
  }
}
