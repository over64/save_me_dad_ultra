package com.catinthedark.savemedad.units


import com.badlogic.gdx.{Gdx, Input, InputAdapter}
import com.catinthedark.savemedad.common.Attacks
import com.catinthedark.savemedad.common.Attacks.Attacks
import com.catinthedark.savemedad.lib.{DeferredManager, Deferred, ComputeUnit, Pipe}
import com.catinthedark.savemedad.common.Const.Timing._

/**
 * Created by over on 22.01.15.
 */
class Control(val dm: DeferredManager) extends ComputeUnit with Deferred {

  val onShoot = new Pipe[Attacks];
  var canShootCol = true
  var canShootRow = true

  override def onActivate(): Unit = {
    Gdx.input.setInputProcessor(new InputAdapter {
      override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
        button match {
          case Input.Buttons.LEFT if canShootCol =>
            canShootCol = false;
            onShoot.write(Attacks.Col)
            defer(COOLDOWN_COL_TIME, () => canShootCol = true)
          case Input.Buttons.RIGHT if canShootRow =>
            canShootRow = false;
            onShoot.write(Attacks.Row)
            defer(COOLDOWN_ROW_TIME, () => canShootRow = true)
          case _ =>
        }

        true
      }
    })
  }

  override def onExit(): Unit = {
    Gdx.input.setInputProcessor(null)
  }

  override def run(delta: Float): Unit = {}
}
