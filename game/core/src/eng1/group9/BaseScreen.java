package eng1.group9;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Base class which handles stuff common to all screens in the game
 */
public abstract class BaseScreen implements Screen {

    private Game game;
    private ScreenUI ui;
    private ScreenInput input;

    /**
     *
     * @param game Game object that controls the application
     * @param ui ScreenUI that handles UI updates
     * @param input ScreenInput that handles user input
     */
    public BaseScreen(Game game, ScreenUI ui, ScreenInput input) {
        this.game = game;
        this.ui = ui;
        this.input = input;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        updateInput();
        updateUi();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    /**
     * Handle user input
     */
    public void updateInput() {
        input.update();
    }

    /**
     * Update the UI
     */
    public void updateUi() {
        ui.update();
    }
}
