# Cucumber & Selenium

**Shinta Nuraisya Arizky** *Praktisi Mengajar 4 (PPPL) - Sekolah Vokasi UGM* 

---

## Outline

1. Cucumber Introduction 


2. Integrate Cucumber with Selenium 


3. Best Practice 



---

01. Cucumber Introduction 

### BDD (Behavior Driven Development)

> 🖼️ **[Diagram Description]:** A structural flow chart illustrating the layers of a Behavior Driven Development (BDD) lifecycle utilizing Cucumber. The structure is split into two primary categories:
> * **Business Facing:** This encompasses the **Project**, **Features**, **Scenario**, and **Steps** layers. It represents the domain understandable by business stakeholders.
> * **Technology Facing:** This encompasses the **Steps Definition**, **Automation code support**, and **System** layers. It represents the technical implementation behind the business steps. 
> 
> 
> 
> 

### What is Cucumber?

* Open source testing framework that supports Behavior Driven Development 


* Can be implemented for automation testing 


* In order for Cucumber to understand the scenarios, it must follow some basic Gherkin syntax rules 



BDD Framework for Cucumber-Selenium 

1. **Feature File**
A standalone unit or a single functionality (such as a login) for a project 


2. **Step Definitions**
Stores the mapping data between each step of a scenario defined in the feature file and the code to be executed 


3. **Test Runner**
JUnit Test Runner Class containing the Step Definition location 



> 🖼️ **[Architecture Diagram]:** A diagram showing the execution relationship within the BDD Framework for Selenium:
> * The **Test Runner** class (`public class RunCucumberTest` annotated with `@RunWith(Cucumber.class)`) loads each feature as a test, and each scenario as a test case. It uses them when executing the feature files as separate tests.
> * The **Feature File (Gherkin)** contains the human-readable scenario (e.g., `Scenario: Users solve challenges` followed by `Given`, `When`, and `Then` steps).
> - Each step in the Feature File maps to a specific method in the **Step definitions file** (e.g., `public class ChallengeStepDefinitions` containing a method annotated with `@Then("her/his stats include {int}")` to execute the actual test step code). 
> 
> 
> 
> 

---

02. Integrate Cucumber with Selenium 

### Add Cucumber Dependency

To get started, add the following dependencies to your Maven configurations: 

```xml
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>7.18.0</version>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit</artifactId>
    <version>6.11.0</version>
    <scope>test</scope>
</dependency>

```



### Integration Workflow

1. Create feature file, e.g: `Login.feature` 


2. Write test scenario using Gherkin syntax 


3. Step Definitions 


4. Setup Test Runner 



> 🖼️ **[Directory Structure Image]:** A screenshot of a Java project directory tree structure inside an IDE under the `src` directory showing the folder layouts:
> * `src/test/java/stepDefinitions` containing `LoginSteps` and `LoginTest` (Test Runner).
> - `src/test/resources/features` containing the `login.feature` file. 
> 
> 
> 
> 

1. Create Feature File - `Login.feature` 

```gherkin
Feature: User Login
  Scenario: Successful login with valid credentials
    Given User is on the login page
    When User submit valid credentials
    Then User should be redirected to the dashboard

```



2. Step Definitions 

```java
public class LoginStepDef {
    WebDriver driver;

    void setupChromeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("User is on the login page")
    public void navigateToLoginPage() {
        setupChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @When("User submit valid credentials")
    public void submitValidCredentials() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
    }

    @Then("User should be redirected to the dashboard")
    public void loginSuccessful() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.getTextProducts();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(home.getActualUrl(), expectedUrl);
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}

```



3. Test Runner 

```java
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepDefinitions"
)
public class TestRunner {
}

```



Run the Script 

* Run `login.feature`. 


* Browser will be navigated to Saucedemo homepage and do login. 


* Wait until the script finished to run. If there is no error, the test result is passed. 



> 🖼️ **[Execution Console Image]:** A screenshot of an IDE test execution terminal showing a successful run output. It lists green checkmarks indicating all steps passed: `✔ User is on the login page`, `✔ User submit valid credentials`, `✔ User should be redirected to the dashboard`, and `✔ After(closeBrowser)`. The message summary reads "Done: Scenarios 1 of 1 (11 sec 478 ms)". 
> 
> 

---

Enhanced Step Definitions 

To separate concerns appropriately, we can split a bloated single step definition file into dedicated Step classes and centralized Hooks. 

> 🖼️ **[Refactoring Comparison Image]:** A screenshot showing a project structure before and after enhancement:
> - **Before:** `stepDefinitions` package only contains `LoginSteps` and `LoginTest`. - **After:** `stepDefinitions` package is cleanly refactored into `HomeSteps`, `Hooks`, and `LoginSteps`. 
> 
> 

Enhanced Step Definitions – Hooks 

Hooks are blocks of code that run **before** or **after** each scenario. They are typically used for setup and teardown of the environment before and after each scenario. 

```java
public class Hooks {
    private static WebDriver driver;

    @Before
    public void setup() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

```



Why Cucumber Hooks? 

We need to perform prerequisite steps before testing any test scenario, such as: 

* Starting a webdriver 


* Setting up DB connections 


* Setting up test data 


* Navigating to a certain page, etc. 



In the same way, there are always cleanup steps required after tests execution: 

* Killing the webdriver 


* Closing DB connections 


* Clearing the test data 


* Logging out from the application 


* Printing reports or logs 


* Taking screenshots on error, etc. 



Enhanced Step Definitions – LoginSteps 

```java
public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    public LoginSteps() {
        this.driver = Hooks.getDriver(); // Mendapatkan instance WebDriver dari Hooks
        this.loginPage = new LoginPage(driver);
    }

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("User submit valid credentials")
    public void user_submit_valid_credentials() throws InterruptedException {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }
}

```



Enhanced Step Definitions – HomeSteps 

```java
public class HomeSteps {
    WebDriver driver;
    HomePage homePage;

    public HomeSteps() {
        this.driver = Hooks.getDriver(); // Mendapatkan instance WebDriver dari Hooks
        this.homePage = new HomePage(driver);
    }

    @Then("User should be redirected to the dashboard")
    public void user_should_be_redirected_to_the_dashboard() throws InterruptedException {
        // verify if the homepage contains text Products
        homePage.getTextProducts();
        
        // verify the current URL
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(homePage.getActualUrl(), expectedUrl);
    }
}

```



---

03. Best Practice 

1. Write Declarative Features 

* 
**Imperative testing or programming** is essentially spelling out with as much detail as necessary how to accomplish something. 


* 
**Declarative testing or programming** is only specifying (or declaring) what needs to be accomplished. 



| ❌ Bad Sample (Imperative) | Good Sample (Declarative) |
| --- | --- |
| 1. Given I open a browser<br>

<br>2. And I navigate to `http://example.com/login`<br>

<br>3. When I type in the username field bob97<br>

<br>4. And I type in the password field F1d0<br>

<br>5. And I click on Submit button<br>

<br>6. Then I should see the message Welcome Back Bob | 1. Given I am on the Login Page<br>

<br>2. When I sign in with correct credentials<br>

<br>3. Then I should see a welcome message |



2. Avoid UI Actions Steps 

| ❌ Bad Sample | Good Sample |
| --- | --- |
| 1. Given I am on the home page<br>

<br>2. When I fill in "Username" with: "jondkinney"<br>

<br>3. And I fill in "Password" with: "SuperSecret123"<br>

<br>4. And I check "Remember me"<br>

<br>5. And I press "Log in"<br>

<br>6. Then a user session should be persisted<br>

<br>7. And I should be on my dashboard<br>

<br>8. And I should see "You have successfully logged in." | 1. Given I am on the home page<br>

<br>2. When I login as an admin<br>

<br>3. Then I should be on my dashboard<br>

<br>4. And I should see "You have successfully logged in." |



3. Avoid Conjunctive Steps 

| ❌ Bad Sample | Good Sample |
| --- | --- |
| 1. Given I am on the home page<br>

<br>2. When I login as an admin<br>

<br>3. **Then I should be on my dashboard and I should see "You have successfully logged in."** | 1. Given I am on the home page<br>

<br>2. When I login as an admin<br>

<br>3. **Then I should be on my dashboard**<br>

<br>4. **And I should see "You have successfully logged in."** |



4. Limit the Number of Scenarios Per Feature 

1. Limit one feature per feature file 


2. Limit the number of scenarios per feature 


3. If a feature has too large of a scope, it should be split 



---

What's Next 

* Project Discussion 


* Integrate POM (Page Object Model) for End-to-End Test 



---

Reference 

* 
[https://cucumber.io/docs/guides/overview/](https://cucumber.io/docs/guides/overview/) 


* 
[https://www.browserstack.com/guide/automation-using-cucumber-selenium](https://www.browserstack.com/guide/automation-using-cucumber-selenium) 


* 
[https://www.linkedin.com/pulse/bdd-cucumber-features-best-practices-liraz-shay/](https://www.google.com/search?q=https://www.linkedin.com/pulse/bdd-cucumber-features-best-practices-liraz-shay/) 



---

Proyek Akhir 

* Mulai minggu depan, silakan update perkembangan pengerjaan dengan mengumpulkan laporan perkembangan. 


* Jika ada pertanyaan atau diskusi, silakan untuk diskusi per kelompok ke praktisi, dosen pengampu atau asisten. 



Tugas 

Lakukan implementasi Gherkin syntax pada kasus uji proyek di kelas proyek aplikasi dasar menggunakan Cucumber dan Selenium!