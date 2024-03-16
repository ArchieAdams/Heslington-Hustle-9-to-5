package eng1.group9.startscreen;

import eng1.group9.BaseScreen;
import eng1.group9.HustleGame;

public class StartScreen extends BaseScreen {

    /**
     *
     * @param game HustleGame object that controls the application
     */
    public StartScreen(HustleGame game) {
        super(game, new StartScreenUI(), new StartScreenInput(game));
    }
}
