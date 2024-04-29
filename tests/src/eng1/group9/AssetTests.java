package eng1.group9;

import com.badlogic.gdx.Gdx;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class AssetTests {
    @Test
    public void testMapExists() {
        assertTrue("The map exists",
                Gdx.files.internal("map").exists());
    }

    @Test
    public void testPlayerAssetExists() {
        assertTrue("The player asset exists",
                Gdx.files.internal("player.png").exists());
    }

    @Test
    public void testMapAssetExists() {
        assertTrue("The map asset exists",
                Gdx.files.internal("fullmap.png").exists());
    }
}
