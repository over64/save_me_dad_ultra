package com.catinthedark.savemedad.lib

import scala.collection.mutable

/**
 * Created by over on 02.01.15.
 */
class Pipe[T] {
  val ports = new mutable.ListBuffer[T => Unit]

  def addPort(port: T => Unit) = ports += port

  def write(msg: T) = ports.foreach(_(msg))
}
