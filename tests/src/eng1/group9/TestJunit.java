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

    //TODO Test files loading
    //TODO Test files with wrong format
    //TODO make player leaving map not crash and just send player to the spawn point TICK
    //TODO make player spawn point so if they leave map it doesn't break (write test for this)
    //TODO default map if error breaks

}
