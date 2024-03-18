package eng1.group9.gamescreen;

import com.badlogic.gdx.utils.ScreenUtils;
import eng1.group9.ScreenUI;

/**
 * UI handler for GameScreen
 */
public class GameScreenUI extends ScreenUI {
    @Override
    public void update() {
        showBackground();
        showMap();
        showPlayer();
        showHud();
    }

    private void showBackground() {
        ScreenUtils.clear(0, 0, 100, 255);
    }

    private void showMap() {

    }

    private void showPlayer() {

    }

    private void showHud() {

    }
}
