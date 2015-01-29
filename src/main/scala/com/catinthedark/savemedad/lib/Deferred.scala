package com.catinthedark.savemedad.lib

/**
 * Created by over on 29.01.15.
 */
trait Deferred {
  val dm: DeferredManager

  def defer(delay: Float, f: () => Unit) = dm.schedule(delay, f)
}
