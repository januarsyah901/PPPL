package tugas;

import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(SauceLocators.TITLE_HEADER);
    }
}
