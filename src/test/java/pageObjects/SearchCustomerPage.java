package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.WaitHelper;

import java.util.List;

public class SearchCustomerPage {

    public WebDriver driver;
    WaitHelper waitHelper;

    @FindBy(xpath = "//input[@id='SearchEmail']")
    private WebElement searchEmailField;

    @FindBy(xpath = "//input[@id='SearchFirstName']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='SearchLastName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//select[@id='SearchMonthOfBirth']")
    private WebElement birthMonthDropdown;

    @FindBy(xpath = "//select[@id='SearchDayOfBirth']")
    private WebElement birthDayDropdown;

    @FindBy(xpath = "//input[@id='SearchCompany']")
    private WebElement companyTextField;

    @FindBy(xpath = "//input[@id='SearchIpAddress']")
    private WebElement ipAddressTextField;

    @FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
    private WebElement roleParent;

    @FindAll(@FindBy(xpath = "//li[@unselectable='on']"))
    private List<WebElement> rolesOption;

    @FindBy(xpath = "//span[@title='delete']")
    private WebElement deleteDefaultRole;

    @FindBy(css = "button[id='search-customers'] i[class='fas fa-search']")
    private WebElement searchButton;

    @FindBy(xpath = "//table[@id='customers-grid']")
    private WebElement table;

    @FindBy(xpath = "//table[@role='grid']")
    private WebElement tableSearchResult;

    @FindBy(xpath = "//table[@id='customers-grid']//tbody/tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//table[@id='customers-grid']//tbody/tr/td")
    private List<WebElement> tableColumns;


    public SearchCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    public WebElement inputSearchEmail(String mail) {
        waitHelper.waitForElement(searchEmailField, 40);
        searchEmailField.clear();
        searchEmailField.sendKeys(mail);
        return searchEmailField;
    }

    public WebElement inputFirstName(String firstName) {
        waitHelper.waitForElement(firstNameField, 30);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        return firstNameField;
    }

    public WebElement inputLastName(String lastName) {
        waitHelper.waitForElement(lastNameField, 30);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        return lastNameField;
    }

    public WebElement selectBirthMonth() {
        Select bm = new Select(birthMonthDropdown);
        bm.selectByVisibleText("");
        return birthMonthDropdown;
    }

    public WebElement selectBirthDay() {
        Select bd = new Select(birthDayDropdown);
        bd.selectByVisibleText("");
        return birthDayDropdown;
    }

    public WebElement inputCompany(String companyName) {
        companyTextField.sendKeys(companyName);
        return companyTextField;
    }

    public WebElement inputIpAddress(String ipAddress) {
        ipAddressTextField.sendKeys(ipAddress);
        return ipAddressTextField;
    }

    public WebElement selectRole(String customerRoles) throws InterruptedException {
        deleteDefaultRole.click();
        Thread.sleep(3000);
        roleParent.click();
        Thread.sleep(3000);

        for (WebElement roles : rolesOption) {
            if (roles.getText().equals(customerRoles)) {
                roles.click();
            }
        }
        return roleParent;
    }

    public WebElement clickSearch() {
        searchButton.click();
        return searchButton;
    }

    public int getNoRows() {
        return tableRows.size();
    }

    public int getNoColumns() {
        return tableColumns.size();
    }

    public boolean retrieveCustomerByEmail(String email) {
        boolean flag = false;

        for (int i = 1; i <= getNoRows(); i++) {
            String emailID = table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[2]")).getText();
            System.out.println(emailID);

            if (emailID.equals(email)) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean retrieveCustomerByNames(String names) {
        boolean flag = false;

        for (int i = 1; i <= getNoRows(); i++) {
            String allName = table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[3]")).getText();
            System.out.println(allName);

            String nameList[] = allName.split(" "); //seperate firstname and lastname

            if (nameList[0].equals("Victoria") && nameList[1].equals("Terces")) {
                flag = true;
            }
        }
        return flag;
    }

}