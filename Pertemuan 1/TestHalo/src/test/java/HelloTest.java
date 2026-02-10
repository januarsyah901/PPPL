import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTest {

    @Test
    void helloWorld() {
    }

    @Test
    public void testMethod1() {
        System.out.println("test class method 1");
        assertTrue(true);
    }

    @Test
    public void testMethod2() {
        System.out.println("test class method 2");
        assertTrue(true);
    }
}