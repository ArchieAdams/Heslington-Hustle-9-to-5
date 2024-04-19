package eng1.group9.endscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import eng1.group9.HustleGame;

/**
 * Input handler for EndScreen
 */
class EndScreenInput extends InputAdapter {
    HustleGame game;

    /**
     *
     * @param game HustleGame object that controls the application
     */
    public EndScreenInput(HustleGame game) {
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
            finish();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Exit the application
     */
    private void finish() {
        Gdx.app.exit();
    }

}
