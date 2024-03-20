package eng1.group9.gamescreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import eng1.group9.HustleGame;
import eng1.group9.gamestate.Direction;
import eng1.group9.gamestate.activities.Activity;
import java.util.ArrayList;

/**
 * Input handler for GameScreen
 */
public class GameScreenInput extends InputAdapter {

    private HustleGame game;

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
            case Input.Keys.UP:
                valid = moveUp();
                break;
            case Input.Keys.A:
            case Input.Keys.LEFT:
                valid = moveLeft();
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                valid = moveDown();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                valid = moveRight();
                break;
            case Input.Keys.K:
            case Input.Keys.ENTER:
                valid = performGameActivity();
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
        return game.getGameState().move(Direction.UP);
    }

    /**
     * Attempt to move the player left
     * @return true if successful, false otherwise
     */
    public boolean moveLeft() {
        System.out.println("Move left");
        return game.getGameState().move(Direction.LEFT);
    }

    /**
     * Attempt to move the player down
     * @return true if successful, false otherwise
     */
    public boolean moveDown() {
        System.out.println("Move down");
        return game.getGameState().move(Direction.DOWN);
    }

    /**
     * Attempt to move the player right
     * @return true if successful, false otherwise
     */
    public boolean moveRight() {
        System.out.println("Move right");
        return game.getGameState().move(Direction.RIGHT);
    }

    /**
     * Attempt to move perform an activity
     * @return true if successful, false otherwise
     */
    public boolean performGameActivity() {
        ArrayList<Activity> tempList = game.getGameState().getActivities();
        if (!tempList.isEmpty()){
            boolean success = game.getGameState().performActivity(tempList.get(0));
            if (game.getGameState().isGameOver()) {
                nextScreen();
            }
            return success;
        }
        return false;
    }

    /**
     * Change to the end screen
     */
    private void nextScreen() {
        game.setEndScreen();
    }

}
