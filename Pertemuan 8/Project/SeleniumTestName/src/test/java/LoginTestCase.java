import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTestCase {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new SafariDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testLoginFlow() {
        // 1. Cek text "Swag Labs" ada
        WebElement swagLabsText = driver.findElement(By.className("login_logo"));
        assertTrue(swagLabsText.isDisplayed(), "Text Swag Labs harus terlihat");
        assertEquals("Swag Labs", swagLabsText.getText(), "Text harus 'Swag Labs'");

        // 2. Cek field username ada
        WebElement usernameField = driver.findElement(By.id("user-name"));
        // WebElement usernameByTag = driver.findElements(By.tagName("input")).get(0);
        assertTrue(usernameField.isDisplayed(), "Field username harus ada");

        // 3. Clear field username
        usernameField.clear();

        // 4. Isi field username
        usernameField.sendKeys("standard_user");

        // 5. Cek field password ada
        WebElement passwordField = driver.findElement(By.id("password"));
        // WebElement passwordByTag = driver.findElements(By.tagName("input")).get(1);
        assertTrue(passwordField.isDisplayed(), "Field password harus ada");

        // 6. Clear field password
        passwordField.clear();

        // 7. Isi field password
        passwordField.sendKeys("secret_sauce");

        // 8. Cek button login ada
        WebElement loginButton = driver.findElement(By.name("login-button"));
        assertTrue(loginButton.isDisplayed(), "Button login harus ada");

        // 9. Click button login pakai locator name
        loginButton.click();

        // Verifikasi login berhasil
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentUrl, "Harus redirect ke inventory");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
