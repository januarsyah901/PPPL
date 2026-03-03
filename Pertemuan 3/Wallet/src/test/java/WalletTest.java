import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    Wallet wallet;

    @BeforeEach
    void setup() {
        wallet = new Wallet();
        wallet.setOwner("Janu");
    }

    @Test
    void testSetOwner() {
        assertEquals("Janu", wallet.getOwner());
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

    @Test
    void testAddMoney() {
        wallet.addMoney(50000);
        wallet.addMoney(25000);

        assertEquals(75000, wallet.getTotalMoney());
    }

    @Test
    void testTakeMoney() {
        wallet.addMoney(100000);
        boolean result = wallet.takeMoney(40000);

        assertTrue(result);
        assertEquals(60000, wallet.getTotalMoney());
    }

    @Test
    void testWalletSummary() {
        wallet.addCard("ATM");
        wallet.addMoney(50000);

        assertAll(
                () -> assertEquals("Janu", wallet.getOwner()),
                () -> assertEquals(1, wallet.getCards().size()),
                () -> assertEquals(50000, wallet.getTotalMoney())
        );
    }
}