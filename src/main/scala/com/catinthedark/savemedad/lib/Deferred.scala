package com.catinthedark.savemedad.lib

/**
 * Created by over on 29.01.15.
 */
trait Deferred {
  self: ComputeUnit[Any] =>

  def defer(delay: Float, f: () => Unit) = {
    new Thread(new Runnable {
      override def run(): Unit = {
        try {
          Thread.sleep((delay * 1000).toLong)
          f()
        } catch {
          case ex: InterruptedException =>
        }
      }
    }).start()
  }
}
