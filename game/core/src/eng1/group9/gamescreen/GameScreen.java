package eng1.group9.gamescreen;

import com.badlogic.gdx.Game;
import eng1.group9.BaseScreen;
import eng1.group9.HustleGame;

public class GameScreen extends BaseScreen {

    /**
     *
     * @param game Game object that controls the application
     */
    public GameScreen(HustleGame game) {
        super(game, new GameScreenUI(), new GameScreenInput(game));
    }

}
