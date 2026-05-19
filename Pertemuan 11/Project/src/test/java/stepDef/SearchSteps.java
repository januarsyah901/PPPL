package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.searchPage;
import pages.resultPage;

public class SearchSteps {
    searchPage search;
    resultPage result;

    @Given("aku buka halaman pencarian Bing")
    public void iAmOnTheBingSearchPage() {
        Hooks.driver.get("https://www.bing.com");
        search = new searchPage(Hooks.driver);
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
        result = new resultPage(Hooks.driver);
        String title = result.getPageTitle();
        Assert.assertTrue("Judul halaman '" + title + "' gak ada hubungannya sama '" + query + "'", 
            title.toLowerCase().contains(query.toLowerCase()));
    }
}
