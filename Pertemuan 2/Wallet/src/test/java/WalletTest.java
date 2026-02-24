import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WalletTest {

    private static Wallet staticSharedWallet;
    private Wallet wallet;

    @BeforeAll
    static void setUp() {
        staticSharedWallet = new Wallet();
        staticSharedWallet.setOwner("Shared Wallet for All Tests");
        staticSharedWallet.addMoney(500_000);
        staticSharedWallet.addCard("MANDIRI Debit");
        staticSharedWallet.addCard("BCA Visa");
    }

    @AfterAll
    static void cleanUp() {
        staticSharedWallet = null;
    }

    @BeforeEach
    void setUpEachTest() {
        wallet = new Wallet();
        wallet.setOwner("Kadal Coklat");
        wallet.addMoney(100_000);
        wallet.addCard("BRI BritAma");
    }

    @AfterEach
    void tearDownEachTest() {
        wallet = null;
    }

    @Test
    @Order(1)
    void testAddAndTakeMoney() {
        assertEquals(100_000, wallet.getTotalMoney());

        wallet.addMoney(50_000);
        assertEquals(150_000, wallet.getTotalMoney());

        boolean success = wallet.takeMoney(70_000);
        assertTrue(success);
        assertEquals(80_000, wallet.getTotalMoney());

        success = wallet.takeMoney(100_000);
        assertFalse(success);
        assertEquals(80_000, wallet.getTotalMoney());
    }

    @Test
    @Order(2)
    void testAddAndTakeCard() {
        assertEquals(1, wallet.getCards().size());

        wallet.addCard("BNI Taplus");
        wallet.addCard("OVO");

        assertEquals(3, wallet.getCards().size());
        assertTrue(wallet.getCards().contains("BNI Taplus"));

        String taken = wallet.takeCard("OVO");
        assertEquals("OVO", taken);
        assertFalse(wallet.getCards().contains("OVO"));
        assertEquals(2, wallet.getCards().size());
    }

    @Test
    @Order(3)
    void testTakeNonExistentCard() {
        String result = wallet.takeCard("Gopay");
        assertNull(result);
        assertEquals(1, wallet.getCards().size());
    }

    @Test
    @Order(4)
    void testOwner() {
        assertEquals("Kadal Coklat", wallet.getOwner());

        wallet.setOwner("Janu Si Kadal");
        assertEquals("Janu Si Kadal", wallet.getOwner());
    }

    @Test
    @Order(5)
    void testStaticWalletStillExists() {
        assertNotNull(staticSharedWallet);
        assertEquals("Shared Wallet for All Tests", staticSharedWallet.getOwner());
        assertEquals(500_000, staticSharedWallet.getTotalMoney());
        assertEquals(2, staticSharedWallet.getCards().size());
    }
}