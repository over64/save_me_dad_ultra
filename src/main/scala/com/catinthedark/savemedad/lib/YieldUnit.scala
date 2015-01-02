package com.catinthedark.savemedad.lib

/**
 * Created by over on 13.12.14.
 */
trait YieldUnit[+T] extends ComputeUnit {
  def done[U >: T](value: U)
}
