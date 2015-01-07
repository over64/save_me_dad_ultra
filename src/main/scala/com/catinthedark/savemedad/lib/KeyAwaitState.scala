package com.catinthedark.savemedad.lib

/**
 * Created by over on 13.12.14.
 */
trait KeyAwaitState extends Stub with InputHelper {
  val keycode: Int

  override def onActivate() = {
    onKeyDown({
      case `keycode` => done()
      case _ =>
    })
    super.onActivate()
  }

  override def onExit() = {
    unregister()
    super.onExit()
  }
}
