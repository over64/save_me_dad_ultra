package com.catinthedark.savemedad.common

import com.badlogic.gdx.math.Vector2
import com.catinthedark.tweaker.Tweakable
import com.sun.istack.internal.NotNull

import scala.beans.BeanProperty

/**
 * Created by over on 03.01.15.
 */
object Const {

  object UI {
    var COOLDOWN_INDICATOR_COL = new Vector2(1185, 510)
    var COOLDOWN_INDICATOR_ROW = new Vector2(1185, 390)
  }

  object Timing {
    var COOLDOWN_ROW_TIME = 1.5f
    val COOLDOWN_COL_TIME = 1.5f
    val FIST_ANIMATION_TIME = 0.3f
  }

  object Ints {
    var i: Int  = 5
  }

  object Strings {
    @Tweakable
    var str = "Hello, World!"
  }

}
