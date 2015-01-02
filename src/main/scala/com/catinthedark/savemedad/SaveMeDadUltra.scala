package com.catinthedark.savemedad

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.{Game, Gdx, Input}
import com.catinthedark.savemedad.lib._

/**
 * Created by over on 13.12.14.
 */
class SaveMeDadUltra extends Game {
  val machine = new RouteMachine()

  lazy val logoState = new Stub("LogoState", machine) with TextureState with DelayState {
    val texture: Texture = Assets.Textures.logo
    val delay: Float = 1.0f
  }

  lazy val menuState = new Stub("MenuState", machine) with TextureState with KeyAwaitState {
    val texture: Texture = Assets.Textures.logo
    val keycode: Int = Input.Keys.ENTER
  }

  lazy val gameState = new GameState(machine)

  lazy val gameOverState = new Stub("GameOverState", machine) with TextureState with KeyAwaitState {
    val texture: Texture = Assets.Textures.logo
    val keycode: Int = Input.Keys.ESCAPE
  }

  lazy val gameWinState = new Stub("GameWinState", machine) with TextureState with KeyAwaitState {
    val texture: Texture = Assets.Textures.logo
    val keycode: Int = Input.Keys.ESCAPE
  }

  override def create() = {
    machine.addRoute(logoState, anyway => menuState)
    machine.addRoute(menuState, anyway => gameState)
    machine.addRoute(gameState, res => {
      res match {
        case true => gameWinState
        case false => gameOverState
      }
    })
    machine.addRoute(gameWinState, anyway => menuState)
    machine.addRoute(gameOverState, anyway => menuState)
    machine.start(logoState)
  }

  override def render() = {
    machine.run(Gdx.graphics.getDeltaTime)
  }
}
