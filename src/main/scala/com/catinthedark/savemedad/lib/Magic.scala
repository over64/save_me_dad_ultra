package com.catinthedark.savemedad.lib

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

/**
 * Created by over on 13.12.14.
 */
object Magic {

  class RichSpriteBatch(val batch: SpriteBatch) {
    def managed(f: SpriteBatch => Unit): Unit = {
      batch.begin();
      f(batch);
      batch.end()
    }

    def drawCentered(tex: Texture, x: Float, y: Float,
                     centerX: Boolean = true, centerY: Boolean = true) =
      batch.draw(tex,
        if (centerX) x - tex.getWidth / 2 else x,
        if (centerY) y - tex.getHeight / 2 else y
      )
  }

  implicit def richifySpriteBatch(batch: SpriteBatch) = new RichSpriteBatch(batch)

  implicit def vector2ToTuple2(vec: Vector2) = (vec.x, vec.y)
}
