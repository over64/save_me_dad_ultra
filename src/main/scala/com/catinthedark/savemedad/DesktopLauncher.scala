package com.catinthedark.savemedad
import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}

/**
 * Created by over on 13.12.14.
 */
object DesktopLauncher {
  def main (args: Array[String]) {
    val conf = new LwjglApplicationConfiguration
    conf.title = "save-me-dad-ultra"
    conf.height = 480
    conf.width = 800

    new LwjglApplication(new SaveMeDadUltra, conf)
  }
}
