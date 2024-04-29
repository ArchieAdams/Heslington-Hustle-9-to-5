package eng1.group9;

import com.badlogic.gdx.math.Vector2;
import eng1.group9.gamestate.GameState;
import eng1.group9.gamestate.MapGraph;
import eng1.group9.gamestate.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class ActivityTests {


    @Test
    public void Relax() {
        GameState gameState = new GameState(100, 100, new Player(new Vector2(0, 0)), new MapGraph("test1"));
        int pre = gameState.getDay().getNumberOfActivity("Recreation");
        gameState.performActivity(gameState.getActivities().get(0));
        int post = gameState.getDay().getNumberOfActivity("Recreation");
        assertEquals(pre + 1, post);
    }

    @Test
    public void Study() {
        GameState gameState = new GameState(100, 100, new Player(new Vector2(1, 0)), new MapGraph("test1"));
        int pre = gameState.getDay().getNumberOfActivity("Study");
        gameState.performActivity(gameState.getActivities().get(0));
        int post = gameState.getDay().getNumberOfActivity("Study");
        assertEquals(pre + 1, post);
    }

    @Test
    public void Eat() {
        GameState gameState = new GameState(100, 100, new Player(new Vector2(1, 1)), new MapGraph("test1"));
        int pre = gameState.getDay().getNumberOfActivity("Eat");
        gameState.performActivity(gameState.getActivities().get(0));
        int post = gameState.getDay().getNumberOfActivity("Eat");
        assertEquals(pre + 1, post);
    }

    @Test
    public void Sleep() {
        GameState gameState = new GameState(100, 100, new Player(new Vector2(0, 1)), new MapGraph("test1"));
        int pre = gameState.getDayCount();
        gameState.performActivity(gameState.getActivities().get(0));
        int post = gameState.getDayCount();
        assertEquals(pre + 1, post);
    }
}
