package com.catinthedark.savemedad.lib

/**
 * Created by over on 13.12.14.
 */
trait ComputeUnit {
  def onActivate()
  def run(delta: Float)
  def onExit()
}
