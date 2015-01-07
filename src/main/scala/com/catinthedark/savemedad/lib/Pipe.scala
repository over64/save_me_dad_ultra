package com.catinthedark.savemedad.lib

/**
 * Created by over on 02.01.15.
 */
class Pipe[T](val ports: (T => Unit)*) {
  def write(msg: T) = ports.foreach(_(msg))
}
