package com.catinthedark.savemedad.units

import com.badlogic.gdx.math.Vector2
import com.catinthedark.savemedad.Assets.{Textures => Tex}
import com.catinthedark.savemedad.common.Attacks._
import com.catinthedark.savemedad.common.Const._
import com.catinthedark.savemedad.lib.animation._

/**
 * Created by over on 02.01.15.
 */
object RenderFactory {

  def cooldownAnimation(attack: Attacks): TriggeredAnimation = {

    val (pos, _time, tex) = attack match {
      case Row => (UI.COOLDOWN_INDICATOR_ROW, Timing.COOLDOWN_ROW_TIME, Tex.cooldownIndicatorRow)
      case Col => (UI.COOLDOWN_INDICATOR_COL, Timing.COOLDOWN_COL_TIME, Tex.cooldownIndicatorCol)
    }

    new RotateAnimation(tex, pos, (angle, delta) => angle + 360.0f * delta / 1.5f)
      with TimingAnimation with TriggeredAnimation {
      override val time = _time
    }
  }

  def dadFistAnimation(attack: Attacks, pos: Vector2): Animation = attack match {
    case Row => new FlatAnimation(Tex.dadFistRow, pos, (start, vec, arg, delay) => {
      vec.x = start.x - Math.sin(arg * 3.14 / Timing.fistAnimation).toFloat * 20
      vec
    })
    case Col => new FlatAnimation(Tex.dadFistCol, pos, (start, vec, arg, delay) => {
      vec.y = start.y + Math.sin(arg * 3.14 / Timing.fistAnimation).toFloat * 20
      vec
    })
  }

}
