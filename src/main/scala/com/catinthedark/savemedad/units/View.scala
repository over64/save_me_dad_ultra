package com.catinthedark.savemedad.units

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.catinthedark.savemedad.Assets
import com.catinthedark.savemedad.common.Attacks
import com.catinthedark.savemedad.common.Attacks._
import com.catinthedark.savemedad.common.Const.{Timing, UI}
import com.catinthedark.savemedad.lib.Magic._
import com.catinthedark.savemedad.lib._
import com.catinthedark.savemedad.lib.animation.{Animation, TriggeredAnimation}

import scala.collection.mutable.ListBuffer

/**
 * Created by over on 02.01.15.
 */
abstract class View extends SimpleUnit with Deferred {

  val roomAndHUD = new Layer {
    val batch = new SpriteBatch

    override def render(delta: Float): Unit = {
      batch.managed { self =>
        self.draw(Assets.Textures.room, 0, 0)
        data.indicatorCol.animate(delta, self)
        data.indicatorRow.animate(delta, self)

        if (dadColFist.isDefined)
          dadColFist.get.animate(delta, self)
        else
          self.drawCentered(Assets.Textures.dadFistCol, colFistX, UI.colFistY)

        if (dadRowFist.isDefined)
          dadRowFist.get.animate(delta, self)
        else
          self.drawCentered(Assets.Textures.dadFistRow, UI.rowFistX, rowFistY)
      }
    }
  }


  case class Data(val indicatorRow: TriggeredAnimation = RenderFactory.cooldownAnimation(Row),
                  val indicatorCol: TriggeredAnimation = RenderFactory.cooldownAnimation(Col),
                  val renderTasks: ListBuffer[RenderTask] = ListBuffer[RenderTask]())


  val data = new Data

  var (colFistX, rowFistY) = (0f, 0f)
  var dadColFist: Option[Animation] = None
  var dadRowFist: Option[Animation] = None


  override def onActivate(): Unit = {}

  override def onExit(): Unit = {}

  def onShoot(attack: Attacks) = attack match {
    case Attacks.Row =>
      data.indicatorRow.start()
      data.indicatorRow.reset()

      dadRowFist =
        Some(RenderFactory.dadFistAnimation(Row, new Vector2(UI.rowFistX, rowFistY)))
      defer(Timing.fistAnimation, () => dadRowFist = None)
    case Attacks.Col =>
      data.indicatorCol.start()
      data.indicatorCol.reset()

      dadColFist =
        Some(RenderFactory.dadFistAnimation(Col, new Vector2(colFistX, UI.colFistY)))
      defer(Timing.fistAnimation, () => dadColFist = None)
  }

  override def run(delta: Float) = {
    val ptrPos = new Vector2(Gdx.input.getX, Gdx.input.getY)
    if (UI.gameRect.contains(ptrPos)) {
      colFistX = ptrPos.x
      rowFistY = Utils.wndYtoGL(ptrPos.y)
    }

    Gdx.gl.glClearColor(0, 0, 0, 0)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    roomAndHUD.render(delta)
  }
}
