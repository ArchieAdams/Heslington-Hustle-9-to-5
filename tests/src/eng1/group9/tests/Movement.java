import com.badlogic.gdx.math.Vector2;
import eng1.group9.gamestate.Direction;
import eng1.group9.gamestate.GameState;
import eng1.group9.gamestate.MapGraph;
import eng1.group9.gamestate.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class Movement {

    @Test
    public void InvalidMove() {
        GameState gameState = new GameState(100, 100, new Player(new Vector2(1, 1)), new MapGraph("test2"));
        assertFalse(gameState.move(Direction.UP));
        assertFalse(gameState.move(Direction.DOWN));
        assertFalse(gameState.move(Direction.LEFT));
        assertFalse(gameState.move(Direction.RIGHT));
    }

    @Test
    public void ValidMove() {
        GameState gameState = new GameState(100, 100, new Player(new Vector2(1, 1)), new MapGraph("test1"));
        assertTrue(gameState.move(Direction.UP));
        assertTrue(gameState.move(Direction.DOWN));
        assertTrue(gameState.move(Direction.RIGHT));
        assertTrue(gameState.move(Direction.LEFT));
    }
}
