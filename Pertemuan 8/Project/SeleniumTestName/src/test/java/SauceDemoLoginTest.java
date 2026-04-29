import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.jupiter.api.Assertions.*;

public class SauceDemoLoginTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new SafariDriver();
    }

    @Test
    public void testLogin() {
        driver.get("https://www.saucedemo.com/");
        // 1) cek text "Swag Labs" ada menggunakan XPath (text node)
        By swagText = By.xpath("//*[text()='Swag Labs']");
        assertTrue(isPresent(swagText), "Swag Labs text should be present");

        // 2) cek field username ada (gunakan locator by NAME)
        By usernameBy = By.name("user-name");
        assertTrue(isPresent(usernameBy), "Username field should be present (by name)");

        // 3) kosongkan field username
        WebElement usernameInput = find(usernameBy);
        usernameInput.clear();

        // 4) isi field username
        usernameInput.sendKeys("standard_user");

        // 5) cek field password ada menggunakan XPath dengan axes (following-sibling / following)
        By passwordBy = By.xpath("//input[@name='user-name']/following::input[@type='password'][1]");
        assertTrue(isPresent(passwordBy), "Password field should be present (xpath axes)");

        // 6) kosongkan field password
        WebElement passwordInput = find(passwordBy);
        passwordInput.clear();

        // 7) isi field password
        passwordInput.sendKeys("secret_sauce");

        // 8) cek tombol login ada menggunakan TAG / collection approach (gunakan tagName untuk mencari inputs)
        java.util.List<WebElement> inputs = driver.findElements(By.tagName("input"));
        WebElement loginButton = null;
        for (WebElement el : inputs) {
            String type = el.getAttribute("type");
            // pilih input submit/button sebagai tombol login
            if (type != null && (type.equalsIgnoreCase("submit") || type.equalsIgnoreCase("button"))) {
                loginButton = el;
                break;
            }
        }
        assertNotNull(loginButton, "Login button should be present (found via tagName inputs)");

        // 9) klik login
        loginButton.click();

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
    }

    // Helper: wrapper sederhana untuk mencari element
    private WebElement find(By locator) {
        return driver.findElement(locator);
    }

    // Helper: cek keberadaan tanpa throwing
    private boolean isPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

