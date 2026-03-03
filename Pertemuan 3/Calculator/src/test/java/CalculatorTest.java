import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of("2","5","-3"),
                Arguments.of("1","5","-4"),
                Arguments.of("5","8","-3")
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 0})

    void testCheckEven(int inits) {
        Calculator Cal = new Calculator(inits, 0);
        assertTrue(Cal.checkEven(inits));
    }

    @ParameterizedTest
    @CsvSource({"4,4,8","100,100,200","8,9,17"})
    void testTambah(int a, int b, int expected) {
        Calculator Cal =  new Calculator(a,b);
        int actual = Cal.tambah();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("data")
    void testKurang(int a, int b, int expected) {
        Calculator Cal = new Calculator(a,b);
        int actual = Cal.kurang();
        assertEquals(expected, actual);
    }

    @Test
    void testBagi() {
        Calculator Cal = new Calculator(10, 2);
        int actual = Cal.bagi();
        int expected = 5;

        assertEquals(expected, actual);
    }

    @Test
    void testKali() {
        Calculator Cal = new Calculator(3, 4);
        int actual = Cal.kali();
        int expected = 12;

        assertEquals(expected, actual);
    }

}