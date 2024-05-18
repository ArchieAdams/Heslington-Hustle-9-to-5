package io.eng1.group9.endscreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.eng1.group9.HustleGame;
import io.eng1.group9.scoring.ScoreManager;

/**
 * Input handler for EndScreen.
 */
class EndScreenInput extends InputAdapter {
  /**
   * The Game.
   */
  HustleGame game;
  private final StringBuilder nameBuffer = new StringBuilder();

  /**
   * Instantiates a new End screen input.
   *
   * @param game HustleGame object that controls the application
   */
  public EndScreenInput(HustleGame game) {
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
      case Input.Keys.BACKSPACE:
        if (nameBuffer.length() > 0) {
          nameBuffer.deleteCharAt(nameBuffer.length() - 1); // Remove the last character
          updateName();
        }
        return true;
      case Input.Keys.ENTER:
        if (nameBuffer.length() > 0) {
          int score = game.getGameState().getScore();
          ScoreManager.addScore(nameBuffer.toString(), score);
          game.setScoreScreen();
        }
        return true;
      default:
        return false;
    }
  }


  /**
   * Handle user keyboard input when characters are typed.
   *
   * @param character the character typed
   * @return true if handled, false otherwise
   */
  @Override
  public boolean keyTyped(char character) {
    if ((Character.isLetterOrDigit(character) || character == ' ') && nameBuffer.length() < 30) {
      nameBuffer.append(character);
      updateName();
      return true;
    }
    return false;
  }

  /**h
   * Sets name in gamestate.
   *
   */
  public void updateName() {
    game.getGameState().setName(nameBuffer.toString());
    System.out.println(nameBuffer);
    if (game.getEndScreen() != null) {
      System.out.println("running");
      EndScreenUi endScreenUi = game.getEndScreen().getEndScreenUi();
      endScreenUi.updateNameLabel();
    }
  }
}
