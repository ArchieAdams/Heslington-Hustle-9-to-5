package io.eng1.group9;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The type Test junit.
 */
@RunWith(GdxTestRunner.class)
public class TestJunit {

  /**
   * The Message.
   */
  String message = "Hello World";

  /**
   * True test.
   */
  @Test
  public void trueTest() {
    Assert.assertEquals(message, "Hello World");
  }

  /**
   * False test.
   */
  @Test
  public void falseTest() {
    Assert.assertNotEquals(message, "Hello");
  }

}
