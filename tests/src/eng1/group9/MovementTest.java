package eng1.group9;

import com.badlogic.gdx.math.Vector2;
import eng1.group9.gamestate.Direction;
import eng1.group9.gamestate.GameState;
import eng1.group9.gamestate.MapGraph;
import eng1.group9.gamestate.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class MovementTest {

    @Test
    public void MapBoundsLimitTest() {
        GameState gameState = new GameState(100, 100, new Player(new Vector2(0, 0)), new MapGraph("test2"));
        assertFalse(gameState.move(Direction.UP));
        assertFalse(gameState.move(Direction.DOWN));
        assertFalse(gameState.move(Direction.LEFT));
        assertFalse(gameState.move(Direction.RIGHT));
    }

    @Test
    public void MoveUp() {
        GameState gameState = new GameState(100, 100, new Player(new Vector2(0, 0)), new MapGraph("test1"));
        assertTrue(gameState.move(Direction.UP));
        assertEquals(gameState.getPlayerPosition(),new Vector2(0,1));
    }

    @Test
    public void MoveDown() {
        GameState gameState = new GameState(100, 100, new Player(new Vector2(0, 1)), new MapGraph("test1"));
        assertTrue(gameState.move(Direction.DOWN));
        assertEquals(gameState.getPlayerPosition(),new Vector2(0,0));
    }

    @Test
    public void MoveRight() {
        GameState gameState = new GameState(100, 100, new Player(new Vector2(0, 0)), new MapGraph("test1"));
        assertTrue(gameState.move(Direction.RIGHT));
        assertEquals(gameState.getPlayerPosition(),new Vector2(1,0));
    }


    @Test
    public void MoveLeft() {
        GameState gameState = new GameState(100, 100, new Player(new Vector2(1, 0)), new MapGraph("test1"));
        assertTrue(gameState.move(Direction.LEFT));
        assertEquals(gameState.getPlayerPosition(),new Vector2(0,0));
    }
}
