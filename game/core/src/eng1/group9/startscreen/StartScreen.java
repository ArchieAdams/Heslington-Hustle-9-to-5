package eng1.group9.startscreen;

import com.badlogic.gdx.Game;
import eng1.group9.BaseScreen;
import eng1.group9.HustleGame;

public class StartScreen extends BaseScreen {

    /**
     *
     * @param game Game object that controls the application
     */
    public StartScreen(HustleGame game) {
        super(game, new StartScreenUI(), new StartScreenInput(game));
    }
}
