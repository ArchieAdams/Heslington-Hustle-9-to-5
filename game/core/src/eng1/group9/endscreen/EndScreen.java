package eng1.group9.endscreen;

import com.badlogic.gdx.Game;
import eng1.group9.BaseScreen;

public class EndScreen extends BaseScreen {

    public EndScreen(Game game) {
        super(game, new EndScreenUI(), new EndScreenInput());
    }

}
