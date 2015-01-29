package com.catinthedark.savemedad.lib

/**
 * Created by over on 29.01.15.
 */
trait DeferredManager {
  def schedule(delay: Float, f: () => Unit)
}
