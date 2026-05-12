package latihan;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    private WebDriver driver;
    private By searchBox = By.name("q");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchQuery(String query) {
        driver.findElement(searchBox).sendKeys(query);
    }

    public void submitSearch() {
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }
}
