package eng1.group9.gamescreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import eng1.group9.HustleGame;

/**
 * Input handler for GameScreen
 */
public class GameScreenInput extends InputAdapter {

    HustleGame game;

    /**
     *
     * @param game HustleGame object that controls the application
     */
    public GameScreenInput(HustleGame game) {
        this.game = game;
    }

    /**
     * Handle user keyboard input
     * @param keycode the key pressed
     * @return true if handled, false otherwise
     */
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

    /**
     * Attempt to move the player up
     * @return true if successful, false otherwise
     */
    public boolean moveUp() {
        System.out.println("Move up");
        return true;
    }

    /**
     * Attempt to move the player left
     * @return true if successful, false otherwise
     */
    public boolean moveLeft() {
        System.out.println("Move left");
        return true;
    }

    /**
     * Attempt to move the player down
     * @return true if successful, false otherwise
     */
    public boolean moveDown() {
        System.out.println("Move down");
        return true;
    }

    /**
     * Attempt to move the player right
     * @return true if successful, false otherwise
     */
    public boolean moveRight() {
        System.out.println("Move right");
        return true;
    }

    /**
     * Attempt to move perform an activity
     * @return true if successful, false otherwise
     */
    public boolean performActivity() {  // Need to agree on how to determine which activity
        return true;
    }

    /**
     * Change to the end screen
     */
    private void nextScreen() {
        game.setEndScreen();
    }

}
