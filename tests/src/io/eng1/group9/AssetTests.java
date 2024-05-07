package io.eng1.group9;

import com.badlogic.gdx.Gdx;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The type Asset tests.
 */
@RunWith(GdxTestRunner.class)
public class AssetTests {
  /**
  * Test map exists.
  */
  @Test
  public void testMapExists() {
      Assert.assertTrue("The map exists", Gdx.files.internal("map").exists());
  }

  /**
  * Test player asset exists.
  */
  @Test
  public void testPlayerAssetExists() {
    Assert.assertTrue("The player asset exists", Gdx.files.internal("player.png").exists());
  }

  /**
  * Test map asset exists.
  */
  @Test
  public void testMapAssetExists() {
    Assert.assertTrue("The map asset exists", Gdx.files.internal("fullmap.png").exists());
  }
}
