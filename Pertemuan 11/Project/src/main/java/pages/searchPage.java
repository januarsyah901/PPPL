package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class searchPage extends basePage {
    public searchPage(WebDriver driver) {
        super(driver);
    }

    By searchBar = By.id("sb_form_q");
    By searchForm = By.id("sb_form");

    public void enterQuery(String query) {
        sendKeys(searchBar, query);
    }

    public void submitForm() {
        waitUntil(searchForm).submit();
    }
}
