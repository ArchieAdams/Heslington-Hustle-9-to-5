package eng1.group9;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class BaseScreen implements Screen {

    private Game game;
    private ScreenUI ui;
    private ScreenInput input;

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

    public void updateInput() {
        input.Update();
    }

    public void updateUi() {
        ui.Update();
    }
}
