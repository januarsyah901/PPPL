import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Latihan penerapan modul Actions dan beberapa interaksi.
 * - Hover pada gambar di https://the-internet.herokuapp.com/hovers
 * - Key presses pada https://the-internet.herokuapp.com/key_presses
 * - Drag and drop pada https://the-internet.herokuapp.com/drag_and_drop
 *
 * Catatan: Pada macOS kita menggunakan SafariDriver (tidak perlu chromedriver binary).
 * Pastikan Safari -> Develop -> "Allow Remote Automation" diaktifkan sebelum menjalankan.
 */
public class LatihanActionsTest {
    private WebDriver driver;
    private Actions actions;

    @BeforeEach
    public void setup() {
        // Gunakan SafariDriver di macOS (sudah tersedia pada sistem)
        driver = new SafariDriver();
        driver.manage().window().maximize();
        // default implicit wait singkat supaya pencarian elemen sedikit toleran
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        actions = new Actions(driver);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Hover: nama user1 muncul saat hover gambar pertama")
    public void testHoverShowsName() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        // Lokator untuk figure pertama
        WebElement firstFigure = driver.findElement(By.cssSelector(".figure:nth-of-type(1)"));
        // Arahkan pointer ke gambar
        actions.moveToElement(firstFigure).perform();

        // Setelah hover, caption muncul — cari teks "name: user1"
        WebElement caption = firstFigure.findElement(By.cssSelector(".figcaption h5"));
        String text = caption.getText().trim();
        Assertions.assertTrue(text.toLowerCase().contains("user1"), "Caption harus mengandung 'user1' tetapi ditemukan: " + text);
    }

    @Test
    @DisplayName("Key presses: tekan SHIFT dan verifikasi teks hasil")
    public void testKeyPressesShift() {
        driver.get("https://the-internet.herokuapp.com/key_presses");

        WebElement input = driver.findElement(By.id("target"));
        // Kirim tombol SHIFT menggunakan Actions
        actions.click(input).sendKeys(Keys.SHIFT).perform();

        WebElement result = driver.findElement(By.id("result"));
        String text = result.getText().trim();
        // Halaman biasanya menampilkan "You entered: SHIFT" — kita cek mengandung "SHIFT"
        Assertions.assertTrue(text.toUpperCase().contains("SHIFT"), "Hasil harus mengandung SHIFT tetapi ditemukan: " + text);
    }

//    @Test
//    @DisplayName("Drag and drop: pindahkan box A ke B menggunakan Actions")
//    public void testDragAndDropAtoB() {
//        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
//
//        // Locator kolom A dan B
//        WebElement columnA = driver.findElement(By.id("column-a"));
//        WebElement columnB = driver.findElement(By.id("column-b"));
//
//        // Cek text awal (A di kiri, B di kanan)
//        String textAStart = columnA.findElement(By.tagName("header")).getText();
//        String textBStart = columnB.findElement(By.tagName("header")).getText();
//        assertEquals("A", textAStart);
//        assertEquals("B", textBStart);
//
//        // Drag & drop A ke B
//        Actions actions = new Actions(driver);
//        actions.dragAndDrop(columnA, columnB).perform();
//
//        // Verifikasi setelah drag & drop
//        String textAEnd = columnA.findElement(By.tagName("header")).getText();
//        String textBEnd = columnB.findElement(By.tagName("header")).getText();
//
//        // Di beberapa browser, hasilnya bisa kebalik (B di kiri, A di kanan)
//        assertEquals("B", textAEnd);
//         assertEquals("A", textBEnd);
//    }

}

