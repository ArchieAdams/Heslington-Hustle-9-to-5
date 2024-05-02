package io.eng1.group9.scoring;

import io.eng1.group9.BaseScreen;
import io.eng1.group9.HustleGame;


/**
 * The screen that is shown when the game is over.
 */
public class ScoreScreen extends BaseScreen {


  /**
   * Instantiates a new End screen.
   *
   * @param game HustleGame that controls the application
   */
  public ScoreScreen(HustleGame game) {
    super(new ScoreScreenUi(game), new ScoreScreenInput(game));
  }

}
