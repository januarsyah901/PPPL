package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class inventoryPage extends basePage {
    public inventoryPage(WebDriver driver) {
        super(driver);
    }

    By inventoryList = By.className("inventory_list");

    public boolean isInventoryDisplayed() {
        return waitUntil(inventoryList).isDisplayed();
    }
}
