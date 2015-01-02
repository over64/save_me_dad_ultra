package com.catinthedark.savemedad.lib

import com.badlogic.gdx.graphics.g2d.SpriteBatch

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
  }

  implicit def richifySpriteBatch(batch: SpriteBatch) = new RichSpriteBatch(batch)
}
