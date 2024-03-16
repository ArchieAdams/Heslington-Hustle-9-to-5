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

    public EndScreenInput(HustleGame game) {
        this.game = game;
    }

    public boolean keyDown (int keycode) {
        if (keycode == Input.Keys.ENTER) {
            finish();
            return true;
        }
        else {
            return false;
        }
    }

    private void finish() {
        Gdx.app.exit();
    }

}
