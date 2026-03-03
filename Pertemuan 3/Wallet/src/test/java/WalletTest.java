import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    Wallet wallet;

    @BeforeEach
    void setup() {
        Owner owner = new Owner("1", "Janu", "myemail@gmail.com");
        wallet = new Wallet();
        wallet.setOwner(owner);
    }

    @Test
    void testSetOwner() {
        assertEquals("Janu", wallet.getOwner().getName());
    }

    private static Stream<Arguments> ownerProvider() {
        return Stream.of(
                Arguments.of(new Owner("1", "Budi", "budi@mail.com")),
                Arguments.of(new Owner("2", "Andi", "andi@mail.com")),
                Arguments.of(new Owner("3", "Sinta", "sinta@mail.com"))
        );
    }

    @ParameterizedTest
    @MethodSource("ownerProvider")
    void testSetOwner(Owner owner) {

        Wallet wallet = new Wallet();
        wallet.setOwner(owner);

        assertEquals(owner, wallet.getOwner());
    }

    @Test
    void testAddCard() {
        wallet.addCard("KTP");
        wallet.addCard("ATM");

        assertEquals(2, wallet.getCards().size());
        assertTrue(wallet.getCards().contains("KTP"));
    }

    @Test
    void testTakeCard() {
        wallet.addCard("SIM");
        String card = wallet.takeCard("SIM");

        assertNotNull(card);
        assertFalse(wallet.getCards().contains("SIM"));
    }

    @ParameterizedTest
    @CsvSource({"10000,5000,5000", "20000,5000,15000", "10000,9000,1000"})
    void testTransaction(int deposit, int withdraw, int expected) {
        wallet.deposit(deposit);
        wallet.withdraw(withdraw);
        assertEquals(expected, wallet.getTotalMoney());
    }

    @ParameterizedTest
    @CsvSource({
            "10000,15000,InsufficientFundsException",
            "10000,0,IllegalArgumentException",
            "10000,-1000,IllegalArgumentException",
            "0,1000,InsufficientFundsException"
    })
    void testTransactionInvalid(int deposit, int withdraw, String exceptionType) {

        if (deposit > 0) {
            wallet.deposit(deposit);
        }

        if (exceptionType.equals("InsufficientFundsException")) {
            assertThrows(InsufficientFundsException.class,
                    () -> wallet.withdraw(withdraw));
        } else {
            assertThrows(IllegalArgumentException.class,
                    () -> wallet.withdraw(withdraw));
        }
    }


    @Test
    void testWithdraw() {
        wallet.deposit(100000);
        boolean result = wallet.withdraw(40000);

        assertTrue(result);
        assertEquals(60000, wallet.getTotalMoney());
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 5000, 0})
    void testBalancePositive(int amount) {

        Wallet wallet = new Wallet();

        assertTrue(wallet.validBalance(amount));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1000, -5000})
    void testBalanceNegative(int amount) {

        Wallet wallet = new Wallet();

        assertFalse(wallet.validBalance(amount));
    }

    @Test
    void testWalletSummary() {
        wallet.addCard("ATM");
        wallet.deposit(50000);

        assertAll(
                () -> assertEquals("Janu", wallet.getOwner()),
                () -> assertEquals(1, wallet.getCards().size()),
                () -> assertEquals(50000, wallet.getTotalMoney())
        );
    }
}