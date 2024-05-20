package io.eng1.group9.gamescreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.eng1.group9.HustleGame;
import io.eng1.group9.gamestate.Direction;
import io.eng1.group9.gamestate.activities.Activity;
import java.util.ArrayList;

/**
 * Input handler for GameScreen.
 */
public class GameScreenInput extends InputAdapter {

  private final HustleGame game;
  private boolean upPressed = false;
  private boolean downPressed = false;
  private boolean leftPressed = false;
  private boolean rightPressed = false;
  private final float moveCooldown = 0.2f;
  private float moveTimer = moveCooldown;


  /**
   * Instantiates a new Game screen input.
   *
   * @param game HustleGame object that controls the application
   */
  public GameScreenInput(HustleGame game) {
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
      case Input.Keys.W:
      case Input.Keys.UP:
        upPressed = true;
        break;
      case Input.Keys.A:
      case Input.Keys.LEFT:
        leftPressed = true;
        break;
      case Input.Keys.S:
      case Input.Keys.DOWN:
        downPressed = true;
        break;
      case Input.Keys.D:
      case Input.Keys.RIGHT:
        rightPressed = true;
        break;
      case Input.Keys.ENTER:
        performGameActivity();
        break;
      case Input.Keys.ESCAPE:
        game.restartGame();
        break;
      default:
        return false;
    }
    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    switch (keycode) {
      case Input.Keys.W:
      case Input.Keys.UP:
        upPressed = false;
        break;
      case Input.Keys.A:
      case Input.Keys.LEFT:
        leftPressed = false;
        break;
      case Input.Keys.S:
      case Input.Keys.DOWN:
        downPressed = false;
        break;
      case Input.Keys.D:
      case Input.Keys.RIGHT:
        rightPressed = false;
        break;
      default:
        return false;
    }
    return true;
  }

  /**
   * Updates the input state and attempts to move the player based on the current input.
   *
   * @param delta The time in seconds since the last update.
   */
  public void update(float delta) {
    moveTimer += delta;

    if (moveTimer >= moveCooldown) {
      if (upPressed) {
        moveUp();
        moveTimer = 0;
      }
      if (downPressed) {
        moveDown();
        moveTimer = 0;
      }
      if (leftPressed) {
        moveLeft();
        moveTimer = 0;
      }
      if (rightPressed) {
        moveRight();
        moveTimer = 0;
      }
    }
  }


  /**
   * Attempt to move the player up.
   *
   */
  public void moveUp() {
    game.getGameState().move(Direction.UP);
  }

  /**
   * Attempt to move the player left.
   *
   */
  public void moveLeft() {
    game.getGameState().move(Direction.LEFT);
  }

  /**
   * Attempt to move the player down.
   *
   */
  public void moveDown() {
    game.getGameState().move(Direction.DOWN);
  }

  /**
   * Attempt to move the player right.
   */
  public void moveRight() {
    game.getGameState().move(Direction.RIGHT);
  }

  /**
   * Attempt to move perform an activity.
   */
  public void performGameActivity() {
    ArrayList<Activity> tempList = game.getGameState().getActivities();
    if (!tempList.isEmpty()) {
      game.getGameState().performActivity(tempList.get(0));
      if (game.getGameState().isGameOver()) {
        nextScreen();
      }
    }
  }

  /**
   * Change to the end screen.
   */
  private void nextScreen() {
    game.getGameState().saveScore();
    game.setEndScreen();
  }

}
