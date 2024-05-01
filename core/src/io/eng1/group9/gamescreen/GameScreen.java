package io.eng1.group9.gamescreen;

import io.eng1.group9.BaseScreen;
import io.eng1.group9.HustleGame;

/**
 * The type Game screen.
 */
public class GameScreen extends BaseScreen {

  /**
   * Instantiates a new Game screen.
   *
   * @param game HustleGame object that controls the application
   */
  public GameScreen(HustleGame game) {
    super(new GameScreenUi(game), new GameScreenInput(game));
  }

}
