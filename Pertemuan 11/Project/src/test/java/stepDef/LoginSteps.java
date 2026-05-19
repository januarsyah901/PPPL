package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.loginPage;
import pages.inventoryPage;
import java.time.Duration;

public class LoginSteps {
    WebDriver driver;
    loginPage login;
    inventoryPage inventory;

    @Given("aku lagi di halaman login SauceDemo")
    public void iAmOnTheSauceDemoLoginPage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        login = new loginPage(driver);
    }

    @When("aku masukin username {string} sama password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        login.enterUsername(username);
        login.enterPassword(password);
    }

    @And("aku klik tombol loginnya")
    public void iClickTheLoginButton() {
        login.clickLogin();
    }

    @Then("aku harusnya langsung masuk ke halaman inventory")
    public void iShouldBeRedirectedToTheInventoryPage() {
        inventory = new inventoryPage(driver);
        Assert.assertTrue("Halaman inventory gak muncul nih", inventory.isInventoryDisplayed());
        driver.quit();
    }

    @Then("harusnya muncul pesan error")
    public void iShouldSeeAnErrorMessage() {
        Assert.assertTrue("Pesan error malah gak ada", login.isErrorMessageDisplayed());
        driver.quit();
    }
}
