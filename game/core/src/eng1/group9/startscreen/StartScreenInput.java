package eng1.group9.startscreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import eng1.group9.HustleGame;
//import eng1.group9.ScreenInput;

/**
 * Input handler for StartScreen
 */
public class StartScreenInput extends InputAdapter {
    HustleGame game;

    public StartScreenInput(HustleGame game) {
        this.game = game;
    }

    @Override
    public boolean keyDown (int keycode) {
        if (keycode == Input.Keys.ENTER) {
            nextScreen();
            return true;
        }
        else {
            return false;
        }
    }

    private void nextScreen() {
        game.setGameScreen();
    }
}
