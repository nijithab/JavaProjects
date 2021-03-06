import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class FertileLandTest {
    FertileLand test;

    /**
     * Tests empty string input
     */
    @Test
    public void testZeroInput() {

        test = new FertileLand();
        String result = ("240000");
        String input = new String("{“”}");
        assertEquals(result,test.getFertileLandOutput(input));
    }

    /**
     * Tests input {“0 292 399 307”}
     */
    @Test
    public void testInput1() {

        test = new FertileLand();
        String result = ("116800 116800");
        String input = new String("{“0 292 399 307”}");
        assertEquals(result,test.getFertileLandOutput(input));
    }

    /**
     * Tests input {“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}
     */
    @Test
    public void testInput2() {
        test = new FertileLand();
        String result = ("22816 192608");
        String input = new String("{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}");
        assertEquals(result,test.getFertileLandOutput(input));
    }

    /**
     * Tests input {“48 192 351 207”, “48 392 351 407”, “120 52 135 547”}
     */
    @Test
    public void testInput3() {
        test = new FertileLand();
        String result = ("22816 192608");
        String input = new String("{“0 180 250 207”, “48 392 340 407”, “120 52 111 540”}");
        assertNotEquals(result,test.getFertileLandOutput(input));
    }

}