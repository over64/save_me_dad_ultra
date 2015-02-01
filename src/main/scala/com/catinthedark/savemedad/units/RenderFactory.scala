package com.catinthedark.savemedad.units

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.catinthedark.savemedad.CooldownIndicator
import com.catinthedark.savemedad.common.Attacks._
import com.catinthedark.savemedad.lib.{RenderTask, Renderable}
import com.catinthedark.savemedad.common.Const.UI
import com.catinthedark.savemedad.Assets.{Textures => Tex}

/**
 * Created by over on 02.01.15.
 */
object RenderFactory {

  def cooldownAnimation(attack: Attacks): CooldownIndicator = {
    import com.catinthedark.savemedad.common.Const.Timing

    val (pos, time, texture) = attack match {
      case Row => (UI.COOLDOWN_INDICATOR_ROW, Timing.COOLDOWN_ROW_TIME, Tex.cooldownIndicatorRow)
      case Col => (UI.COOLDOWN_INDICATOR_COL, Timing.COOLDOWN_COL_TIME, Tex.cooldownIndicatorCol)
    }

    new CooldownIndicator(pos, time, texture)
  }

  def dadFistAttack(attack: Attacks, pos: Vector2, onDone: => Unit): RenderTask = {
    import com.catinthedark.savemedad.common.Const.Timing

//    val texture = attack match {
//      case Row => Tex.
//    }
    new RenderTask(Timing.FIST_ANIMATION_TIME, onDone) {
      override def renderFn(delta: Float, batch: SpriteBatch): Unit = {

      }
    }
  }
}
