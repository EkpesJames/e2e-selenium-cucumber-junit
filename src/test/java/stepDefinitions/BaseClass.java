package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public LoginPage loginPage;
    public AddCustomerPage addCustomerPage;
    public SearchCustomerPage searchCustomerPage;
    public WebDriverManager webDriverManager;
    public static Logger logger;
    public Properties prop;
    public ChromeOptions options;

    public static String randomString(){  //For generating ramdom email strings
        String generateString1 = RandomStringUtils.randomAlphabetic(5);
        return (generateString1);
    }

}