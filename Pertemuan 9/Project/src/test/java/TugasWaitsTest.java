import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Demonstrasi strategi wait dan pengecualian pada halaman practice-test-exceptions.
 * File ini menunjukkan contoh NoSuchElementException, TimeoutException, dan StaleElementReferenceException
 * serta bagaimana Thread.sleep, implicit wait, dan explicit wait mempengaruhi perilaku.
 *
 * Catatan: ini adalah contoh yang ditulis untuk tujuan pembelajaran — sesuaikan lokator
 * dengan elemen nyata pada halaman tugas jika diperlukan.
 */
public class TugasWaitsTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new SafariDriver();
        driver.manage().window().maximize();
        // default implicit wait singkat
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Test Case 1 - demonstrasi NoSuchElementException dan strategi wait")
    public void testCase1_noSuchElementAndWaits() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");

        By missing = By.id("this-id-does-not-exist-hopefully");

        // 1) Tanpa wait/implicit (set 0) -> langsung NoSuchElementException
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        Assertions.assertThrows(NoSuchElementException.class, () -> driver.findElement(missing), "Harus melempar NoSuchElementException saat elemen tidak ada dan implicit wait=0");

        // 2) Dengan implicit wait lebih besar -> waktu tunggu sebelum exception
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        long start = System.nanoTime();
        Assertions.assertThrows(NoSuchElementException.class, () -> driver.findElement(missing));
        long elapsedMs = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Elapsed with implicit wait ~ ms: " + elapsedMs + " (should be ~5000ms)");

        // 3) Dengan explicit wait -> akan melempar TimeoutException jika condition tidak terpenuhi
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Assertions.assertThrows(org.openqa.selenium.TimeoutException.class, () -> wait.until(ExpectedConditions.presenceOfElementLocated(missing)), "Explicit wait akan melempar TimeoutException jika elemen tidak muncul dalam timeout");

        // 4) Thread.sleep hanya menunda eksekusi, tidak membuat elemen muncul: tetap NoSuchElementException
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assertions.assertThrows(NoSuchElementException.class, () -> driver.findElement(missing));
    }

    @Test
    @DisplayName("Test Case 5 - demonstrasi StaleElementReferenceException dan solusinya")
    public void testCase5_staleElement() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");

        // Ambil elemen yang pasti ada pada halaman (misal: heading)
        WebElement heading = driver.findElement(By.cssSelector("h1, h2, h3"));
        String text = heading.getText();
        Assertions.assertNotNull(text);

        // Muat ulang halaman supaya reference menjadi stale
        driver.navigate().refresh();

        // Menggunakan objek heading lama akan melempar StaleElementReferenceException
        Assertions.assertThrows(StaleElementReferenceException.class, () -> heading.getText());

        // Solusi: re-locate elemen setelah perubahan DOM
        WebElement headingNew = driver.findElement(By.cssSelector("h1, h2, h3"));
        Assertions.assertNotNull(headingNew.getText());
    }
}

