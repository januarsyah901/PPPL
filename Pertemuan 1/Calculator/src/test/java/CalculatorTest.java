import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void tambah() {
        Calculator Cal =  new Calculator(2,5);
        int actual = Cal.tambah();
        int expected = 7;

        assertEquals(actual,expected);
    }

    @Test
    void kurang() {
        Calculator Cal = new Calculator(5, 2);
        int actual = Cal.kurang();
        int expected = 3;

        assertEquals(actual, expected);
    }

    @Test
    void bagi() {
        Calculator Cal = new Calculator(10, 2);
        int actual = Cal.bagi();
        int expected = 5;

        assertEquals(actual, expected);
    }

    @Test
    void kali() {
        Calculator Cal = new Calculator(3, 4);
        int actual = Cal.kali();
        int expected = 12;

        assertEquals(actual, expected);
    }
}