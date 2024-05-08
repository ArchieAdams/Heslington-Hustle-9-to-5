package io.eng1.group9;

import com.badlogic.gdx.math.Vector2;
import io.eng1.group9.gamestate.Direction;
import io.eng1.group9.gamestate.GameState;
import io.eng1.group9.gamestate.MapGraph;
import io.eng1.group9.gamestate.Player;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The type Activity tests.
 */
@RunWith(GdxTestRunner.class)
public class ActivityTileTests {

  /**
   * Sleep.
   */
  @Test
  public void sleep() {
    GameState gameState =
        new GameState(new Player(new Vector2(0, 0)), new MapGraph("activityTest"));
    int pre = gameState.getDayCount();
    gameState.performActivity(gameState.getActivities().get(0));
    int post = gameState.getDayCount();
    Assert.assertEquals(pre + 1, post);
  }

  /**
   * Study.
   */
  @Test
  public void study() {
    GameState gameState =
        new GameState(new Player(new Vector2(1, 0)), new MapGraph("activityTest"));
    int pre = gameState.getDay().getNumberOfActivity("Study");
    gameState.performActivity(gameState.getActivities().get(0));
    int post = gameState.getDay().getNumberOfActivity("Study");
    Assert.assertEquals(pre + 1, post);
  }

  /**
   * Eat.
   */
  @Test
  public void eat() {
    GameState gameState =
        new GameState(new Player(new Vector2(2, 0)), new MapGraph("activityTest"));
    int pre = gameState.getDay().getNumberOfActivity("Eat");
    gameState.performActivity(gameState.getActivities().get(0));
    int post = gameState.getDay().getNumberOfActivity("Eat");
    Assert.assertEquals(pre + 1, post);
  }

  /**
   * Relax.
   */
  @Test
  public void relax() {
    GameState gameState =
        new GameState(new Player(new Vector2(2, 0)), new MapGraph("activityTest"));
    for (int i = 0; i < 4; i++) {
      gameState.move(Direction.RIGHT);
      int pre = gameState.getDay().getNumberOfActivity("Recreation");
      gameState.performActivity(gameState.getActivities().get(0));
      int post = gameState.getDay().getNumberOfActivity("Recreation");
      Assert.assertEquals(pre + 1, post);
    }
  }

  @Test
  public void testUnknownTile() {
    GameState gameState =
        new GameState(new Player(new Vector2(7, 0)), new MapGraph("activityTest"));
    Assert.assertEquals("This should return an empty array",gameState.getActivities(),new ArrayList<>());
  }

  @Test
  public void testOutOfMap() {
    GameState gameState =
        new GameState(new Player(new Vector2(8, 0)), new MapGraph("activityTest"));
    int pre = gameState.getDayCount();
    gameState.performActivity(gameState.getActivities().get(0));
    int post = gameState.getDayCount();
    Assert.assertEquals("In this map the spawn will be the top left room which is SleepTile"
        ,pre + 1, post);
  }
}
