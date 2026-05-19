package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage extends basePage {
    public loginPage(WebDriver driver) {
        super(driver);
    }

    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.cssSelector("[data-test='error']");

    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public boolean isErrorMessageDisplayed() {
        return waitUntil(errorMessage).isDisplayed();
    }
}
