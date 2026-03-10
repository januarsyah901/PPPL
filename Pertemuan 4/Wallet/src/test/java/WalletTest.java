import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Wallet Tests")
class WalletTest {

    Wallet wallet;

    @BeforeEach
    void setup() {
        Owner owner = new Owner("1", "Janu", "myemail@gmail.com");
        wallet = new Wallet();
        wallet.setOwner(owner);
    }

    @Nested
    @DisplayName("Owner Tests")
    class OwnerTests {

        @Test
        @DisplayName("Set owner dan verifikasi nama")
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
        @DisplayName("Set berbagai owner dan verifikasi")
        void testSetOwnerParameterized(Owner owner) {
            Wallet wallet = new Wallet();
            wallet.setOwner(owner);
            assertEquals(owner, wallet.getOwner());
        }

        @Test
        @DisplayName("Wallet summary: nama owner, kartu, dan saldo")
        void testWalletSummary() {
            wallet.addCard("ATM");
            wallet.deposit(50000);

            assertAll(
                    () -> assertEquals("Janu", wallet.getOwner().getName()),
                    () -> assertEquals(1, wallet.getCards().size()),
                    () -> assertEquals(50000, wallet.getTotalMoney())
            );
        }
    }

    @Nested
    @DisplayName("Card Tests")
    class CardTests {

        @Test
        @DisplayName("Tambah kartu ke wallet")
        void testAddCard() {
            wallet.addCard("KTP");
            wallet.addCard("ATM");

            assertEquals(2, wallet.getCards().size());
            assertTrue(wallet.getCards().contains("KTP"));
        }

        @Test
        @DisplayName("Ambil kartu dari wallet")
        void testTakeCard() {
            wallet.addCard("SIM");
            String card = wallet.takeCard("SIM");

            assertNotNull(card);
            assertFalse(wallet.getCards().contains("SIM"));
        }
    }

    @Nested
    @DisplayName("Transaction Tests")
    class TransactionTests {

        @Nested
        @DisplayName("Valid Transactions")
        class ValidTransactionTests {

            @ParameterizedTest
            @CsvSource({"10000,5000,5000", "20000,5000,15000", "10000,9000,1000"})
            @DisplayName("Deposit lalu withdraw, cek saldo akhir")
            void testTransaction(int deposit, int withdraw, int expected) {
                wallet.deposit(deposit);
                wallet.withdraw(withdraw);
                assertEquals(expected, wallet.getTotalMoney());
            }

            @Test
            @DisplayName("Withdraw berhasil dan saldo berkurang")
            void testWithdraw() {
                wallet.deposit(100000);
                boolean result = wallet.withdraw(40000);

                assertTrue(result);
                assertEquals(60000, wallet.getTotalMoney());
            }
        }

        @Nested
        @DisplayName("Invalid Transactions")
        class InvalidTransactionTests {

            @ParameterizedTest
            @CsvSource({
                    "10000,15000,InsufficientFundsException",
                    "10000,0,IllegalArgumentException",
                    "10000,-1000,IllegalArgumentException",
                    "0,1000,InsufficientFundsException"
            })
            @DisplayName("Withdraw tidak valid melempar exception yang sesuai")
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
        }
    }

    @Nested
    @DisplayName("Balance Tests")
    class BalanceTests {

        @ParameterizedTest
        @ValueSource(ints = {1000, 5000, 0})
        @DisplayName("Saldo valid (>= 0)")
        void testBalancePositive(int amount) {
            Wallet wallet = new Wallet();
            assertTrue(wallet.validBalance(amount));
        }

        @ParameterizedTest
        @ValueSource(ints = {-1000, -5000})
        @DisplayName("Saldo tidak valid (< 0)")
        void testBalanceNegative(int amount) {
            Wallet wallet = new Wallet();
            assertFalse(wallet.validBalance(amount));
        }
    }
}