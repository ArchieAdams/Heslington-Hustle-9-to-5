package eng1.group9.EndScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import eng1.group9.ScreenInput;
import eng1.group9.ScreenUI;

public class EndScreen implements Screen {
    private Game game;
    private ScreenUI ui;
    private ScreenInput input;

    public EndScreen(Game game) {
        this.game = game;
        ui = new EndScreenUI();
        input = new EndScreenInput();
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
