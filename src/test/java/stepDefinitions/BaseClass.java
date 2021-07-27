package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {


    public WebDriver driver;
    public static Logger logger;
    public Properties prop;
    public ChromeOptions options;

    public WebDriver initDriver() throws IOException {

        logger = Logger.getLogger("nopCommerce");
        PropertyConfigurator.configure("Log4j.properties"); //Added logger

        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/config.properties");

        prop.load(fis);
        //mvn test -DBrowser=chrome
        // String browserName = System.getProperty("browser"); //retrieve browser from maven command at run time
        String browserName = prop.getProperty("browser"); //retrieve browser from config file
        System.out.println(browserName);

        if (browserName.contains("chrome")) {
            options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-notifications");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("IE")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }

    public static String randomString() {  //For generating random email strings
        return (RandomStringUtils.randomAlphabetic(5));
    }

    public void quitDriver() {
        driver.close();
    }

}