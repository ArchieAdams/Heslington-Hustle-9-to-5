package io.eng1.group9;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import io.eng1.group9.endscreen.EndScreen;
import io.eng1.group9.gamescreen.GameScreen;
import io.eng1.group9.gamestate.GameState;
import io.eng1.group9.scoring.ScoreScreen;
import io.eng1.group9.startscreen.StartScreen;


/**
 * The type Hustle game.
 */
public class HustleGame extends Game {
  private Screen startScreen;
  private Screen gameScreen;
  private GameState gameState;


  @Override
  public void create() {
    gameState = new GameState();
    startScreen = new StartScreen(this);
    gameScreen = new GameScreen(this);

    setStartScreen();
  }

  /**
   * Switch to the start screen.
   */
  public void setStartScreen() {
    setScreen(startScreen);
    System.out.println("Switched to start screen");
  }

  /**
   * Switch to the game screen.
   */
  public void setGameScreen() {
    setScreen(gameScreen);
    System.out.println("Switched to game screen");
  }

  /**
   * Switch to the end screen.
   */
  public void setEndScreen() {
    Screen endScreen = new EndScreen(this);
    setScreen(endScreen);
    System.out.println("Switched to end screen");
  }

  public void setScoreScreen() {
    Screen scoreScreen = new ScoreScreen(this);
    setScreen(scoreScreen);
  }

  /**
   * Gets game state.
   *
   * @return the game state
   */
  public GameState getGameState() {
    return gameState;
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void dispose() {
  }

  /**
   * Restarts the game by hiding and disposing the current game screen, then recreating the game.
   */
  public void restartGame() {
    gameScreen.hide();
    gameScreen.dispose();
    create();
  }
}
