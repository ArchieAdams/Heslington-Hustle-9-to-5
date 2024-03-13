package eng1.group9.gamescreen;

import com.badlogic.gdx.Game;
import eng1.group9.BaseScreen;

public class GameScreen extends BaseScreen {

    public GameScreen(Game game) {
        super(game, new GameScreenUI(), new GameScreenInput());
    }

}
