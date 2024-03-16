package eng1.group9.startscreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import eng1.group9.HustleGame;

/**
 * Input handler for StartScreen
 */
public class StartScreenInput extends InputAdapter {
    HustleGame game;

    /**
     *
     * @param game HustleGame object that controls the application
     */
    public StartScreenInput(HustleGame game) {
        this.game = game;
    }

    /**
     * Handle user keyboard input
     * @param keycode the key pressed
     * @return true if handled, false otherwise
     */
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

    /**
     * Switch to the main game screen
     */
    private void nextScreen() {
        game.setGameScreen();
    }
}
