package com.catinthedark.savemedad.lib

import java.util.concurrent.LinkedBlockingQueue

import scala.collection.mutable

/**
 * Created by over on 13.12.14.
 */

class UnitControlBlock(val unit: ComputeUnit) {
  val queue = new LinkedBlockingQueue[() => Unit]()

  def push(task: () => Unit): Unit = queue.put(task)

  def run(delta: Float): Unit = {
    Stream.continually {
      Option(queue.poll())
    }.takeWhile {
      _ match {
        case Some(f) => f(); true
        case None => false
      }
    }

    unit.run(delta)
  }
}

class RouteMachine {
  private val routes = mutable.ListBuffer[(YieldUnit[Any], (Any => YieldUnit[Any]))]()
  private var currentUCB: UnitControlBlock = _

  implicit def fnToRunnable(f: () => Unit): Runnable = {
    new Runnable {
      override def run(): Unit = f()
    }
  }

  val dm: DeferredManager = new DeferredManager {
    override def schedule(delay: Float, f: () => Unit): Unit = {
      new Thread(() => {
        Thread.sleep((delay * 1000).toLong)
        currentUCB.push(f)
      }).start()

    }
  }

  def doRoute[T](cond: T): Unit = {
    println(s"begin transition from $currentUCB")
    val from = currentUCB.unit
    from.onExit()

    val routeFn = routes.filter(route => {
      val (routeFrom, _) = route
      routeFrom eq from //reference equal
    }).map(route => route._2)
      .headOption
      .getOrElse(throw new RuntimeException(s"Could not find route function from $from"))

    currentUCB = new UnitControlBlock(routeFn(cond))
    currentUCB.unit.onActivate()
    println(s"end transition to $currentUCB")
  }

  def addRoute[T >: Any](from: YieldUnit[T], routeFn: T => YieldUnit[Any]): Unit = {
    routes += ((from, routeFn));
  }

  def start(unit: ComputeUnit) = {
    currentUCB = new UnitControlBlock(unit)
    currentUCB.unit.onActivate()
  }

  def run(delta: Float): Unit = currentUCB.run(delta)
}
