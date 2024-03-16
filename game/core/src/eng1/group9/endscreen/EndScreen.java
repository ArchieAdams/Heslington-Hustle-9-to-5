package eng1.group9.endscreen;

import eng1.group9.BaseScreen;
import eng1.group9.HustleGame;

/**
 * The screen that is shown when the game is over
 */
public class EndScreen extends BaseScreen {

    /**
     *
     * @param game HustleGame object that controls the application
     */
    public EndScreen(HustleGame game) {
        super(game, new EndScreenUI(), new EndScreenInput(game));
    }

}
