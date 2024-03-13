package eng1.group9.startscreen;

import com.badlogic.gdx.Game;
import eng1.group9.BaseScreen;

public class StartScreen extends BaseScreen {

    public StartScreen(Game game) {
        super(game, new StartScreenUI(), new StartScreenInput());
    }
}
