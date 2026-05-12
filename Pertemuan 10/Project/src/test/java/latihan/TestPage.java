package latihan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPage {
    private WebDriver driver;
    private SearchPage searchPage;
    private ResultPage resultPage;

    @BeforeEach
    public void setUp() {
        // SafariDriver is pre-installed on macOS.
        // Ensure "Allow Remote Automation" is enabled in Safari -> Develop menu.
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bing.com/");
        
        searchPage = new SearchPage(driver);
        resultPage = new ResultPage(driver);
    }

    @Test
    public void testBingSearch() {
        String query = "janu ganteng";
        searchPage.enterSearchQuery(query);
        searchPage.submitSearch();

        // Adding a small wait might be necessary as title might not update instantly
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assertion adjusted to match Bing's actual title format
        assertEquals("janu ganteng - Search", resultPage.getTitle());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
