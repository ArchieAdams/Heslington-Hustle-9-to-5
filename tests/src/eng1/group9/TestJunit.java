package eng1.group9;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(GdxTestRunner.class)
public class TestJunit {

    String message = "Hello World";

    @Test
    public void trueTest() {
        assertEquals(message, "Hello World");
    }

    @Test
    public void falseTest() {
        assertNotEquals(message, "Hello");
    }


}
