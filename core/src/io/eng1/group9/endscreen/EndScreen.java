package io.eng1.group9.endscreen;

import io.eng1.group9.BaseScreen;
import io.eng1.group9.HustleGame;

/**
 * The screen that is shown when the game is over.
 */
public class EndScreen extends BaseScreen {

  /**
   * Instantiates a new End screen.
   *
   * @param game HustleGame that controls the application
   */
  public EndScreen(HustleGame game) {
    super(new EndScreenUi(game), new EndScreenInput(game));
  }

}
