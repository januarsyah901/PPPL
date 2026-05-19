package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.searchPage;
import pages.resultPage;
import java.time.Duration;

public class SearchSteps {
    WebDriver driver;
    searchPage search;
    resultPage result;

    @Given("aku buka halaman pencarian Bing")
    public void iAmOnTheBingSearchPage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com");
        search = new searchPage(driver);
    }

    @When("aku ngetik kata kunci {string}")
    public void iEnterTheSearchQuery(String query) {
        search.enterQuery(query);
    }

    @And("aku teken enter buat nyari")
    public void iSubmitTheSearchForm() {
        search.submitForm();
    }

    @Then("harusnya muncul hasil yang ada hubungannya sama {string}")
    public void iShouldSeeResultsRelatedTo(String query) {
        result = new resultPage(driver);
        String title = result.getPageTitle();
        Assert.assertTrue("Judul halaman '" + title + "' gak ada hubungannya sama '" + query + "'", 
            title.toLowerCase().contains(query.toLowerCase()));
        driver.quit();
    }
}
