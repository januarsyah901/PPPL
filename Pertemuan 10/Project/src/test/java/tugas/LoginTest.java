package tugas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeEach
    public void setUp() {
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.login("standard_user", "secret_sauce");
        
        // Assertion to check if we are on the inventory page
        String expectedTitle = "Products";
        assertEquals(expectedTitle, inventoryPage.getPageTitle());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
