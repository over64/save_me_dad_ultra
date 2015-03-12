package com.catinthedark.savemedad.units


import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.{Gdx, Input, InputAdapter}
import com.catinthedark.savemedad.common.Attacks
import com.catinthedark.savemedad.common.Attacks.Attacks
import com.catinthedark.savemedad.lib.{SimpleUnit, Deferred, Pipe}
import com.catinthedark.savemedad.common.Const.Timing._

/**
 * Created by over on 22.01.15.
 */
class Control extends SimpleUnit with Deferred {

  val onShoot = new Pipe[Attacks]
  val onPause = new Pipe[Unit]
  var canShootCol = true
  var canShootRow = true

  var paused = false;

  override def onActivate(): Unit = {
    Gdx.input.setInputProcessor(new InputAdapter {
      override def keyDown(keycode: Int): Boolean = {
        keycode match {
          case Input.Keys.P =>
            onPause()
            paused = !paused
          case _ =>
        }
        true
      }

      override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
        if (!paused)
          button match {
            case Input.Buttons.LEFT if canShootCol =>
              canShootCol = false;
              onShoot(Attacks.Col)
              defer(COOLDOWN_COL_TIME, () => canShootCol = true)
            case Input.Buttons.RIGHT if canShootRow =>
              canShootRow = false;
              onShoot(Attacks.Row)
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

  override def run(delta: Float) = {
    println(new Vector2(Gdx.input.getX, Gdx.input.getY))
  }
}
