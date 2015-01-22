package com.catinthedark.savemedad.units

import com.catinthedark.savemedad.CooldownIndicator
import com.catinthedark.savemedad.common.Attacks._
import com.catinthedark.savemedad.lib.Renderable
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
}
