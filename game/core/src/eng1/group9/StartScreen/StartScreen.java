package eng1.group9.StartScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import eng1.group9.ScreenInput;
import eng1.group9.ScreenUI;

public class StartScreen implements Screen {
    Game game;
    ScreenUI ui;
    ScreenInput input;

    public StartScreen(Game game) {
        this.game = game;
        ui = new StartScreenUI();
        input = new StartScreenInput();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

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
