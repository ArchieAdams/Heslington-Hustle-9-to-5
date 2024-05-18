package io.eng1.group9.startscreen;

import io.eng1.group9.BaseScreen;
import io.eng1.group9.HustleGame;

/**
 * The type Start screen.
 */
public class StartScreen extends BaseScreen {

  /**
   * Instantiates a new Start screen.
   *
   * @param game HustleGame object that controls the application.
   */
  public StartScreen(HustleGame game) {
    super(new StartScreenUi(), new StartScreenInput(game));
  }

  @Override
  public void update(float delta) {
  }
}
