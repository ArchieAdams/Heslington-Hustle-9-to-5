package io.eng1.group9.scoring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.eng1.group9.HustleGame;

/**
 * Input handler for EndScreen.
 */
class ScoreScreenInput extends InputAdapter {
  /**
   * The Game.
   */
  HustleGame game;

  /**
   * Instantiates a new End screen input
   *
   * @param game HustleGame object that controls the application
   */
  public ScoreScreenInput(HustleGame game) {
    this.game = game;
  }

  /**
   * Handle user keyboard input.
   *
   * @param keycode the key pressed
   * @return true if handled, false otherwise
   */
  @Override
  public boolean keyDown(int keycode) {
    switch (keycode) {
      case Input.Keys.Q:
        finish();
        return true;
      case Input.Keys.ENTER:
        game.restartGame();
        return true;
      default:
        return false;
    }
  }

  /**
   * Exit the application.
   */
  private void finish() {
    Gdx.app.exit();
  }

}
