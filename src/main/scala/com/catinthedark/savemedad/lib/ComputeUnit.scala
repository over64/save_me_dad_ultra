package com.catinthedark.savemedad.lib

/**
 * Created by over on 13.12.14.
 */
trait ComputeUnit[+T] {
  def onActivate()
  def run(delta: Float): Option[T]
  def onExit()
}
