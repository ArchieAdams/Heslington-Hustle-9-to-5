package io.eng1.group9;

import com.badlogic.gdx.math.Vector2;
import io.eng1.group9.gamestate.Direction;
import io.eng1.group9.gamestate.GameState;
import io.eng1.group9.gamestate.MapGraph;
import io.eng1.group9.gamestate.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The type Movement tests.
 */
@RunWith(GdxTestRunner.class)
public class MovementTests {

  /**
   * Map bounds limit test.
   */
  @Test
  public void mapBoundsLimitTest() {
    GameState gameState =
        new GameState(100, 100, new Player(new Vector2(0, 0)), new MapGraph("test2"));
    Assert.assertFalse(gameState.move(Direction.UP));
    Assert.assertFalse(gameState.move(Direction.DOWN));
    Assert.assertFalse(gameState.move(Direction.LEFT));
    Assert.assertFalse(gameState.move(Direction.RIGHT));
  }

  /**
   * Move up.
   */
  @Test
  public void moveUp() {
    GameState gameState =
        new GameState(100, 100, new Player(new Vector2(0, 0)), new MapGraph("test1"));
    Assert.assertTrue(gameState.move(Direction.UP));
    Assert.assertEquals(gameState.getPlayerPosition(), new Vector2(0, 1));
  }

  /**
   * Move down.
   */
  @Test
  public void moveDown() {
    GameState gameState =
        new GameState(100, 100, new Player(new Vector2(0, 1)), new MapGraph("test1"));
    Assert.assertTrue(gameState.move(Direction.DOWN));
    Assert.assertEquals(gameState.getPlayerPosition(), new Vector2(0, 0));
  }

  /**
   * Move right.
   */
  @Test
  public void moveRight() {
    GameState gameState =
        new GameState(100, 100, new Player(new Vector2(0, 0)), new MapGraph("test1"));
    Assert.assertTrue(gameState.move(Direction.RIGHT));
    Assert.assertEquals(gameState.getPlayerPosition(), new Vector2(1, 0));
  }


  /**
   * Move left.
   */
  @Test
  public void moveLeft() {
    GameState gameState =
        new GameState(100, 100, new Player(new Vector2(1, 0)), new MapGraph("test1"));
    Assert.assertTrue(gameState.move(Direction.LEFT));
    Assert.assertEquals(gameState.getPlayerPosition(), new Vector2(0, 0));
  }
}
