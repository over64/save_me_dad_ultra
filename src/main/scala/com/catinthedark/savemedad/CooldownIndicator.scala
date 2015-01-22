package com.catinthedark.savemedad

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.catinthedark.savemedad.lib.Renderable

/**
 * Created by over on 03.01.15.
 */
class CooldownIndicator(pos: Vector2, time: Float, texture: Texture) extends Renderable {
  var stateTime = time;

  override def render(delta: Float, batch: SpriteBatch) = {
    batch.draw(texture, pos.x, pos.y,
      texture.getWidth / 2, texture.getHeight / 2,
      texture.getWidth, texture.getHeight,
      1, 1,
      360 - stateTime * 360 / time,
      0, 0,
      texture.getWidth, texture.getHeight, false, false)

    if(stateTime < time)
      stateTime += delta
  }

  def animate() =
    stateTime = 0f;
}
