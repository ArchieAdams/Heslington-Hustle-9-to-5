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
  private Screen currentScreen;



  @Override
  public void create() {
    gameState = new GameState();
    startScreen = new StartScreen(this);
    gameScreen = new GameScreen(this);

    setStartScreen();
  }

  @Override
  public void setScreen(Screen screen) {
    super.setScreen(screen);
    this.currentScreen = screen;
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
  }


  /**
   * Switch to the end screen.
   */
  public void setEndScreen() {
    Screen endScreen = new EndScreen(this);
    setScreen(endScreen);
  }

  /**
   * Gets the EndScreen instance if it exists, otherwise returns null.
   *
   * @return the EndScreen instance if it exists, otherwise returns null.
   */
  public EndScreen getEndScreen() {
    if (currentScreen != null) {
      return (EndScreen) currentScreen;
    }
    return null;
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
