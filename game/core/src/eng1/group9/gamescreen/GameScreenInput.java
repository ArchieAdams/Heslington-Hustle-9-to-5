package eng1.group9.gamescreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import eng1.group9.BaseScreen;
import eng1.group9.HustleGame;
import eng1.group9.endscreen.EndScreen;

import java.io.Console;
//import eng1.group9.ScreenInput;

/**
 * Input handler for GameScreen
 */
public class GameScreenInput extends InputAdapter {

    HustleGame game;

    public GameScreenInput(HustleGame game) {
        this.game = game;
    }

    @Override
    public boolean keyDown (int keycode) {
        boolean valid = false;

        switch (keycode) {
            case Input.Keys.W:
                valid = moveUp();
                break;
            case Input.Keys.A:
                valid = moveLeft();
                break;
            case Input.Keys.S:
                valid = moveDown();
                break;
            case Input.Keys.D:
                valid = moveRight();
                break;
        }
        return valid;
    }

    public boolean moveUp() {
        System.out.println("Move up");
        return true;
    }
    public boolean moveLeft() {
        System.out.println("Move left");
        return true;
    }
    public boolean moveDown() {
        System.out.println("Move down");
        return true;
    }
    public boolean moveRight() {
        System.out.println("Move right");
        return true;
    }


    public boolean performActivity() {  // Need to agree on how to determine which activity
        return true;
    }

    private void nextScreen() {
        game.setEndScreen();
    }

}
