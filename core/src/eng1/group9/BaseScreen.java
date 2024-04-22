package eng1.group9;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

/**
 * Base class which handles stuff common to all screens in the game
 */
public abstract class BaseScreen implements Screen {

    private final Game game;
    private final ScreenUI ui;
    private final InputAdapter input;

    /**
     * @param game  Game object that controls the application
     * @param ui    ScreenUI that handles UI updates
     * @param input ScreenInput that handles user input
     */
    public BaseScreen(Game game, ScreenUI ui, InputAdapter input) {
        this.game = game;
        this.ui = ui;
        this.input = input;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float v) {
        //updateInput();
        updateUi();
    }

    @Override
    public void resize(int width, int height) {
        ui.resize(width, height);
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
     * Update the UI
     */
    public void updateUi() {
        ui.update();
    }
}
