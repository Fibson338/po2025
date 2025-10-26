import static org.junit.Assert.*;

public class CodingBatTest {

    @org.junit.Test
    public void diff21() {
        CodingBat cb = new CodingBat();
        assertEquals(2 , cb.diff21(19));
        assertEquals(11 , cb.diff21(10));
        assertEquals(0 , cb.diff21(21));
        assertEquals(2 , cb.diff21(22));
    }

    @org.junit.Test
    public void nearHundred() {
        CodingBat cb = new CodingBat();
        assertTrue(cb.nearHundred(93));
        assertTrue(cb.nearHundred(90));
        assertFalse(cb.nearHundred(89));
        assertTrue(cb.nearHundred(110));
    }

    @org.junit.Test
    public void helloName() {
        CodingBat cb = new CodingBat();
        assertEquals("Hello Bob!", cb.helloName("Bob"));
        assertEquals("Hello Alice!", cb.helloName("Alice"));
        assertEquals("Hello X!", cb.helloName("X"));
        assertEquals("Hello Dolly!", cb.helloName("Dolly"));

    }

    @org.junit.Test
    public void lucky13() {
        CodingBat cb = new CodingBat();
        assertTrue(cb.lucky13(new int[]{0,2,4}));
        assertFalse(cb.lucky13(new int[]{1,2,4}));
        assertFalse(cb.lucky13(new int[]{1,2,3}));
        assertTrue(cb.lucky13(new int[]{2,7,2,8}));

    }
}