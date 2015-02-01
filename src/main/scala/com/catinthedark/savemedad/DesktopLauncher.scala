package com.catinthedark.savemedad

import java.awt.Dimension

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.catinthedark.savemedad.common.Const
import com.catinthedark.tweaker.{DoubleTweaker, IntTweaker}

import scala.swing._

/**
 * Created by over on 13.12.14.
 */
object DesktopLauncher {
  def main(args: Array[String]) {
    val conf = new LwjglApplicationConfiguration
    conf.title = "save-me-dad-ultra"
    conf.height = 768
    conf.width = 1366

    new LwjglApplication(new SaveMeDadUltra, conf)

    new SimpleSwingApplication {

      override def top = new MainFrame {
        //preferredSize = new Dimension(640, 480)

        title = "Hello, World"

        val panel = new BoxPanel(orientation = Orientation.Vertical) {
          contents ++= Seq(new IntTweaker("Some int") {
            min = 0
            max = 10
            step = 1

            override def get = Const.Ints.i
            override def set(value: Int) = Const.Ints.i = value
          }.component, new DoubleTweaker("cooldown row") {
            override def get = Const.Timing.COOLDOWN_ROW_TIME.toDouble
            override def set(value: Double) = Const.Timing.COOLDOWN_ROW_TIME = value.toFloat
          }.component)
        }
        contents = panel
      }
    }.main(args)


    //    new Thread(new Runnable {
    //      override def run(): Unit = {
    //        Thread.sleep(3000)
    //
    //        val l = universe.typeOf[Tweakable]
    //        val tt = universe.typeTag[Const.Strings.type].tpe
    //        tt.typeSymbol.typeSignature.decls.foreach(s => {
    //          val forTweak = s.annotations.map(ai => {
    //            ai.tree.symbol.
    //            ai.tree.tpe match {
    //              case `l` => Some(Tweakable())
    //              case _ => None
    //            }
    //          }).filter(_ != None).map(_.get)
    //          println(s"for tweak -> $forTweak")
    //
    //        })
    //      }
    //    }).start();
  }
}
