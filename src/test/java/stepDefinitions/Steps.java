package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Steps extends BaseClass {

    @Before
    public void setUp() throws IOException {
        //Reading property file
        prop = new Properties();
        FileInputStream fis = new FileInputStream("config.properties");
        prop.load(fis);

        logger = Logger.getLogger("nopCommerce");
        PropertyConfigurator.configure("Log4j.properties"); //Added logger

        String br=prop.getProperty("browser");
        if (br.equalsIgnoreCase("chrome")){
            options = new ChromeOptions();
            webDriverManager.chromedriver().setup();
            //options.addArguments("headless");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-notifications");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        }
        else if(br.equalsIgnoreCase("firefox")) {
            webDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (br.equalsIgnoreCase("IE")) {
            webDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }

    }

    @Given("user launch chrome browser")
    public void user_launch_chrome_browser() {
        logger.info("Launching the browser");
        driver = new ChromeDriver(options);
    }

    @When("user opens the URL {string}")
    public void user_opens_the_URL(String url) {
        driver.get(url);
        logger.info("Opening browser");
        driver.manage().window().maximize();
    }

    @And("user enters email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        loginPage = new LoginPage(driver);
        logger.info("Providing email details");
        loginPage.setUserName(email);
        loginPage.setPassword(password);
    }

    @And("I click on login")
    public void i_click_on_login() {
        loginPage.clickLogin();
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String title) {
        if (driver.getPageSource().contains("Login was unsuccessful")) {
            driver.close();
            Assert.assertTrue(false);
        } else {
            logger.info("Asserting page title");
            Assert.assertEquals(title, driver.getTitle());
        }

    }

    @When("user click on log out link")
    public void user_click_on_log_out_link() throws InterruptedException {
        loginPage.clickLogOut();
        Thread.sleep(5000);
    }

    @And("close browser")
    public void close_browser() {
        driver.close();
    }

    //Add Customer step definitions ................................
    @Then("user can view dashboard")
    public void user_can_view_dashboard() {
        addCustomerPage = new AddCustomerPage(driver);
        logger.info("Checking dashboard page title..............");
        Assert.assertEquals("Dashboard / nopCommerce administration", addCustomerPage.getPageTitle());
    }

    @When("user click on customers menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        Thread.sleep(3000);
        addCustomerPage.clickCustomerParentLink();
    }

    @When("click on customers menu item")
    public void click_on_customers_menu_item() throws InterruptedException {
        Thread.sleep(3000);
        addCustomerPage.clickCustomerChildLink();
    }

    @When("click on add new button")
    public void click_on_add_new_button() throws InterruptedException {
        addCustomerPage.clickAddNewButton();
        Thread.sleep(3000);
    }

    @Then("user can view Add New customer page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCustomerPage.getPageTitle());
    }

    @When("user enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        String email = randomString() + "@gmail.com";
        addCustomerPage.enterCustomerEmail(email);
        addCustomerPage.enterCustomerPassword("password1");
        addCustomerPage.enterCustomerFirstName("Chris");
        addCustomerPage.enterCustomerLastName("Essien");
        addCustomerPage.clickCustomerGender();
        addCustomerPage.enterCustomerDOBParent("7/05/1985");
        addCustomerPage.enterCompanyName("RS-Tech");
        addCustomerPage.selectCustomerRoles("Guests");
        addCustomerPage.selectManagerofVendor();
        addCustomerPage.enterComment("Test to add new customer.............");
    }

    @When("click on Save button")
    public void click_on_save_button() throws InterruptedException {
        addCustomerPage.clickSaveButton();
        Thread.sleep(3000);
    }

    @Then("user can view confirmation message  {string}")
    public void user_can_view_confirmation_message(String confirmMessage) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully."));
    }

    //Customer search and validate steps
    @When("enter customer email")
    public void enter_customer_email() {
        searchCustomerPage = new SearchCustomerPage(driver);
        searchCustomerPage.inputSearchEmail("victoria_victoria@nopCommerce.com");
    }

    @When("click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCustomerPage.clickSearch();
        Thread.sleep(3000);
    }

    @Then("user should find email in the search table")
    public void user_should_find_email_in_the_search_table() {
        boolean status = searchCustomerPage.retrieveCustomerByEmail("victoria_victoria@nopCommerce.com");
        Assert.assertEquals(true, status);
    }

    //cusromer search by firstName and lastName
    @When("enter customer FirstName")
    public void enter_customer_first_name() {
        searchCustomerPage = new SearchCustomerPage(driver);
        searchCustomerPage.inputFirstName("Victoria");
    }

    @When("enter customer LastName")
    public void enter_customer_last_name() {
        searchCustomerPage.inputLastName("Terces");
    }

    @Then("user should find Name in the search table")
    public void user_should_find_name_in_the_search_table() {
        logger.info("Extracting customer name from the search table...............");
        boolean result = searchCustomerPage.retrieveCustomerByNames("Victoria Terces");
        Assert.assertEquals(true, result);
    }

}