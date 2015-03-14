package com.catinthedark.savemedad.lib

/**
 * Created by over on 15.03.15.
 */
trait Deferred {
  def defer(delay: Float, f: () => Unit)
}
