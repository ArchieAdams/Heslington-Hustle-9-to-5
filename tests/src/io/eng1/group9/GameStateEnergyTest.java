package eng1.group9.tests;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import eng1.group9.gamestate.GameState;
import eng1.group9.gamestate.Player;
import eng1.group9.gamestate.MapGraph;
import eng1.group9.gamestate.activities.*;

public class GameStateEnergyTest {
    private GameState gameState;
    private Player player;
    private MapGraph gameMap;

    @Before
    public void setUp() {
        player = new Player();
        gameMap = new MapGraph("map");
        gameState = new GameState(100, 100, player, gameMap);
    }

    @Test
    public void testEnergyConsumptionForEat() {
        Activity eat = new Eat(10, 20, "Eating");  // Consumes 20 energy
        boolean result = gameState.performActivity(eat);
        assertTrue("The activity should be performed successfully", result);
        assertEquals("Energy should be reduced by 20", 80, gameState.getEnergy());
    }

    @Test
    public void testEnergyConsumptionForSleep() {
        // Assuming Sleep resets energy to max
        Activity sleep = new Sleep(8, -100, "Sleeping");  // Restores energy fully
        boolean result = gameState.performActivity(sleep);
        assertTrue("The activity should be performed successfully", result);
        assertEquals("Energy should be reset to max", 100, gameState.getEnergy());
    }

    @Test
    public void testEnergyConsumptionForStudy() {
        Activity study = new Study(30, 40, "Studying");  // Consumes 40 energy
        boolean result = gameState.performActivity(study);
        assertTrue("The activity should be performed successfully", result);
        assertEquals("Energy should be reduced by 40", 60, gameState.getEnergy());
    }

    @Test
    public void testEnergyConsumptionForRecreation() {
        Activity recreation = new Recreation(15, 30, "Recreation");  // Consumes 30 energy
        boolean result = gameState.performActivity(recreation);
        assertTrue("The activity should be performed successfully", result);
        assertEquals("Energy should be reduced by 30", 70, gameState.getEnergy());
    }

    @Test
    public void testEnergyInsufficient() {
        Activity heavyActivity = new Recreation(5, 110, "Intensive Workout");  // Requires more energy than available
        boolean result = gameState.performActivity(heavyActivity);
        assertFalse("The activity should not be performed due to insufficient energy", result);
        assertEquals("Energy should remain unchanged", 100, gameState.getEnergy());
    }
}
