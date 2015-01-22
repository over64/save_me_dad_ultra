package com.catinthedark.savemedad.units


import com.badlogic.gdx.{Gdx, Input, InputAdapter}
import com.catinthedark.savemedad.common.Attacks
import com.catinthedark.savemedad.common.Attacks.Attacks
import com.catinthedark.savemedad.lib.{ComputeUnit, Pipe}

/**
 * Created by over on 22.01.15.
 */
class Control extends ComputeUnit {

  val onShoot = new Pipe[Attacks];

  override def onActivate(): Unit = {
    Gdx.input.setInputProcessor(new InputAdapter {
      override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
        val direction = button match {
          case Input.Buttons.LEFT => Some(Attacks.Col)
          case Input.Buttons.RIGHT => Some(Attacks.Row)
          case _ => None
        }

        direction.foreach(onShoot.write(_))

        true
      }
    })
  }

  override def onExit(): Unit = {}

  override def run(delta: Float): Unit = {}
}
