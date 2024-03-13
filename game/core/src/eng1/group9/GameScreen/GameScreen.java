package eng1.group9.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import eng1.group9.ScreenInput;
import eng1.group9.ScreenUI;

public class GameScreen implements Screen {
    Game game;
    private ScreenUI ui;
    private ScreenInput input;

    public GameScreen(Game game) {
        this.game = game;
        input = new GameScreenInput();
        ui = new GameScreenUI();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        input.Update();
        ui.Update();
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
}
