package io.eng1.group9;


import io.eng1.group9.gamestate.GameState;
import io.eng1.group9.gamestate.MapGraph;
import io.eng1.group9.gamestate.Player;
import io.eng1.group9.gamestate.activities.Activity;
import io.eng1.group9.gamestate.activities.Eat;
import io.eng1.group9.gamestate.activities.Recreation;
import io.eng1.group9.gamestate.activities.Sleep;
import io.eng1.group9.gamestate.activities.Study;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The type Game state time test.
 */
@RunWith(GdxTestRunner.class)
public class GameStateTimeTests {
  private GameState gameState;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    Player player = new Player();
    MapGraph gameMap = new MapGraph("map");
    gameState = new GameState(100, 100, player, gameMap);
  }

  /**
   * Test time consumption for eating.
   */
  @Test
  public void testTimeConsumptionForEating() {
    Activity eat = new Eat(10, 20, "Eating");
    boolean result = gameState.performActivity(eat);
    Assert.assertTrue("The activity should be performed successfully", result);
    Assert.assertEquals("Time should be reduced by 10", 90, gameState.getTime());
  }

  /**
   * Test time consumption for sleep.
   */
  @Test
  public void testTimeConsumptionForSleep() {
    Activity sleep = new Sleep(40, 0, "Sleeping");
    boolean result = gameState.performActivity(sleep);
    Assert.assertTrue("The activity should be performed successfully", result);
    Assert.assertEquals("Time should be reset to max", 100, gameState.getTime());
  }

  /**
   * Test progression.
   */
  @Test
  public void testProgression() {
    Activity sleep = new Sleep(0, 0, "Sleeping");
    for (int i = 0; i < 7; i++) {
      boolean result = gameState.performActivity(sleep);
      Assert.assertTrue("The activity should be performed successfully", result);
      int currentDay = gameState.getDayCount();
      Assert.assertEquals("The day should be " + currentDay, i+1, currentDay);
      int time = gameState.getTime();
      Assert.assertEquals("Time should be reset to max", 100, time);
    }
    boolean gameOver = gameState.isGameOver();
    Assert.assertTrue("The game should be over", gameOver);
    Assert.assertEquals("Time should be reset to max", 100, gameState.getTime());
  }

  /**
   * Test time for study.
   */
  @Test
  public void testTimeForStudy() {
    Activity study = new Study(30, 40, "Studying");
    boolean result = gameState.performActivity(study);
    Assert.assertTrue("The activity should be performed successfully", result);
    Assert.assertEquals("Time should be reduced by 30", 70, gameState.getTime());
  }

  /**
   * Test time consumption for recreation.
   */
  @Test
  public void testTimeConsumptionForRecreation() {
    Activity recreation = new Recreation(15, 30, "Recreation");
    boolean result = gameState.performActivity(recreation);
    Assert.assertTrue("The activity should be performed successfully", result);
    Assert.assertEquals("Time should be reduced by 15", 85,  gameState.getTime());
  }

  /**
   * Test time insufficient.
   */
  @Test
  public void testTimeInsufficient() {
    Activity heavyActivity =
        new Recreation(110, 20, "Intensive Workout");
    boolean result = gameState.performActivity(heavyActivity);
    Assert.assertFalse("The activity should not be performed due to insufficient time", result);
    Assert.assertEquals("Time should remain unchanged", 100, gameState.getTime());
  }


}
