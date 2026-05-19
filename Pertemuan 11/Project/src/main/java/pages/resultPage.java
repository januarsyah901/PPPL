package pages;

import org.openqa.selenium.WebDriver;

public class resultPage extends basePage {
    public resultPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
