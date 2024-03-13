package eng1.group9.StartScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import eng1.group9.BaseScreen;
import eng1.group9.ScreenInput;
import eng1.group9.ScreenUI;

public class StartScreen extends BaseScreen {

    public StartScreen(Game game) {
        super(game, new StartScreenUI(), new StartScreenInput());
    }
}
