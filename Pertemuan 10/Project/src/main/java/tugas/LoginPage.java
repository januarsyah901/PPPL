package tugas;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        type(SauceLocators.USERNAME_FIELD, username);
        type(SauceLocators.PASSWORD_FIELD, password);
        click(SauceLocators.LOGIN_BUTTON);
    }
}
