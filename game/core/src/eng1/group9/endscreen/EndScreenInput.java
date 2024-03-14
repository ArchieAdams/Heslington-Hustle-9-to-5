package eng1.group9.endscreen;

import com.badlogic.gdx.InputAdapter;
import eng1.group9.HustleGame;
//import eng1.group9.ScreenInput;

/**
 * Input handler for EndScreen
 */
class EndScreenInput extends InputAdapter {
    HustleGame game;

    public EndScreenInput(HustleGame game) {
        this.game = game;
    }
}
