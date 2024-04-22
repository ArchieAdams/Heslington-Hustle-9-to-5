package eng1.group9.gamescreen;

import eng1.group9.BaseScreen;
import eng1.group9.HustleGame;

public class GameScreen extends BaseScreen {

    /**
     * @param game HustleGame object that controls the application
     */
    public GameScreen(HustleGame game) {
        super(game, new GameScreenUI(game), new GameScreenInput(game));
    }

}
