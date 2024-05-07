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
 * The type Game state energy test.
 */
@RunWith(GdxTestRunner.class)
public class GameStateEnergyTests {
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
   * Test energy consumption for eat.
   */
  @Test
  public void testEnergyConsumptionForEat() {
    Activity eat = new Eat(10, 20, "Eating");  // Consumes 20 energy
    boolean result = gameState.performActivity(eat);
    Assert.assertTrue("The activity should be performed successfully", result);
    Assert.assertEquals("Energy should be reduced by 20", 80, gameState.getEnergy());
  }

  /**
   * Test energy consumption for sleep.
   */
  @Test
  public void testEnergyConsumptionForSleep() {
    // Assuming Sleep resets energy to max
    Activity sleep = new Sleep(0, 0, "Sleeping");  // Restores energy fully
    boolean result = gameState.performActivity(sleep);
    Assert.assertTrue("The activity should be performed successfully", result);
    Assert.assertEquals("Energy should be reset to max", 100, gameState.getEnergy());
  }

  /**
   * Test energy consumption for study.
   */
  @Test
  public void testEnergyConsumptionForStudy() {
    Activity study = new Study(30, 40, "Studying");  // Consumes 40 energy
    boolean result = gameState.performActivity(study);
    Assert.assertTrue("The activity should be performed successfully", result);
    Assert.assertEquals("Energy should be reduced by 40", 60, gameState.getEnergy());
  }

  /**
   * Test energy consumption for recreation.
   */
  @Test
  public void testEnergyConsumptionForRecreation() {
    Activity recreation = new Recreation(15, 30, "Recreation");  // Consumes 30 energy
    boolean result = gameState.performActivity(recreation);
    Assert.assertTrue("The activity should be performed successfully", result);
    Assert.assertEquals("Energy should be reduced by 30", 70, gameState.getEnergy());
  }

  /**
   * Test energy insufficient.
   */
  @Test
  public void testEnergyInsufficient() {
    Activity heavyActivity =
        new Recreation(5, 110, "Intensive Workout");  // Requires more energy than available
    boolean result = gameState.performActivity(heavyActivity);
    Assert.assertFalse("The activity should not be performed due to insufficient energy", result);
    Assert.assertEquals("Energy should remain unchanged", 100, gameState.getEnergy());
  }


}
