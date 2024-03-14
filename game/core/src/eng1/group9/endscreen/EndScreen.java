package eng1.group9.endscreen;

import com.badlogic.gdx.Game;
import eng1.group9.BaseScreen;

/**
 * The screen that is shown when the game is over
 */
public class EndScreen extends BaseScreen {

    /**
     *
     * @param game Game object that controls the application
     */
    public EndScreen(Game game) {
        super(game, new EndScreenUI(), new EndScreenInput());
    }

}
