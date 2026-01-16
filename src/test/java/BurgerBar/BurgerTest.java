package BurgerBar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {
 public Burger test = new Burger("BBQ", 10.99, true);
    @Test
    void checken() {
        double preistest = test.berechneBruttoPreis();
        String nametest = test.getName();
        boolean vegantest = test.isIstVegan();

        assertEquals(13.0781, preistest);
        assertEquals("BBQ", nametest);
        assertTrue(vegantest);
    }
}