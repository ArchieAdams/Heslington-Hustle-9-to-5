package io.eng1.group9.endscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.eng1.group9.HustleGame;

/**
 * Input handler for EndScreen.
 */
class EndScreenInput extends InputAdapter {
  /**
   * The Game.
   */
  HustleGame game;
  private StringBuilder nameBuffer = new StringBuilder();

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
        updateName(); // Handle enter as finish action
        finish();
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
      nameBuffer.append(character); // Append the character to the buffer
      updateName(); // Update the name on the screen
      return true;
    }
    return false;
  }

  /**
   * Returns the content of the name buffer.
   *
   * @return the current content of the name buffer
   */
  public void updateName() {
    game.getGameState().setName(nameBuffer.toString());
  }

  /**
   * Exit the application.
   */
  private void finish() {
    Gdx.app.exit();
  }

}
