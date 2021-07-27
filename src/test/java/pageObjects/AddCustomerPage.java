package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddCustomerPage {

    public WebDriver driver;

    @FindBy(xpath = "//a[@href='#']//p[contains(text(),'Customers')]")
    private WebElement customerParentLink;

    @FindBy(xpath = "//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")
    private WebElement customerChildLink;

    @FindBy(css = "a[class='btn btn-primary']")
    private WebElement addNewButton;

    @FindBy(xpath = "//input[@id='Email']")
    private WebElement customerEmail;

    @FindBy(css = "#Password")
    private WebElement customerPassword;

    @FindBy(css = "#FirstName")
    private WebElement customerFirstName;

    @FindBy(css = "#LastName")
    private WebElement customerLastName;

    @FindBy(css = "#Gender_Male")
    private WebElement customerGender;

    @FindBy(css = "#DateOfBirth")
    private WebElement customerDOBParent;

    @FindBy(css = "span[aria-label='select']")
    private WebElement customerDOBChild;

    @FindBy(css = "#Company")
    private WebElement companyName;

    @FindBy(css = "#IsTaxExempt")
    private WebElement taxExempt;

    @FindBy(css = "div[class='input-group-append'] div[role='listbox']")
    private WebElement newsLetter;

    @FindBy(xpath = "//div[@class='input-group-append input-group-required']//div[@role='listbox']")
    private WebElement customerRoles;

    @FindBy(xpath = "//span[@title='delete']")
    private WebElement deleteRoles;

    @FindAll(@FindBy(xpath = "//li[@class='k-item']"))
    private List<WebElement> customerList;

    @FindBy(css = "#VendorId")
    private WebElement ManagerofVendor;

    @FindBy(xpath = "//input[@id='Active']")
    private WebElement Active;

    @FindBy(css = "#AdminComment")
    private WebElement AdminComment;

    @FindBy(css = "button[name='save']")
    private WebElement saveButton;

    @FindBy(css = "button[name='save-continue']")
    private WebElement saveAndContinue;


    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public WebElement clickCustomerParentLink() {
        customerParentLink.click();
        return customerParentLink;
    }

    public WebElement clickCustomerChildLink() {
        customerChildLink.click();
        return customerChildLink;
    }

    public WebElement clickAddNewButton() {
        addNewButton.click();
        return addNewButton;
    }

    public WebElement enterCustomerEmail(String email) {
        customerEmail.sendKeys(email);
        return customerEmail;
    }

    public WebElement enterCustomerPassword(String pass) {
        customerPassword.sendKeys(pass);
        return customerPassword;
    }

    public WebElement clickCustomerGender() {
        customerGender.click();
        return customerGender;
    }

    public WebElement enterCustomerFirstName(String firstName) {
        customerFirstName.sendKeys(firstName);
        return customerFirstName;
    }

    public WebElement enterCustomerLastName(String lastName) {
        customerLastName.sendKeys(lastName);
        return customerLastName;
    }

    public WebElement enterCustomerDOBParent(String parentDOB) {
        customerDOBParent.sendKeys(parentDOB);
        return customerDOBParent;
    }

    public WebElement enterCustomerDOBChild(String childDOB) {
        customerDOBChild.sendKeys(childDOB);
        return customerDOBChild;
    }

    public WebElement enterCompanyName(String company) {
        companyName.sendKeys(company);
        return companyName;
    }

    public WebElement clickTaxExempt() {
        taxExempt.click();
        return taxExempt;
    }

    public WebElement enterNewsLetter(String news) {
        newsLetter.sendKeys(news);
        return newsLetter;
    }

    public WebElement selectCustomerRoles(String roles) throws InterruptedException {
        deleteRoles.click();
        Thread.sleep(3000);
        customerRoles.click();
        Thread.sleep(3000);

        for(WebElement cusRoles : customerList){
            if(cusRoles.getText().equals(roles)) {
                cusRoles.click();
            }
        }
        return customerRoles;
    }

    public WebElement selectManagerofVendor() {
        Select mv = new Select(ManagerofVendor);
        mv.selectByValue("2");
        return ManagerofVendor;
    }

    public WebElement tickActiveBox() {
        Active.click();
        return Active;
    }

    public WebElement enterComment(String administrativeComment) {
        AdminComment.sendKeys(administrativeComment);
        return AdminComment;
    }

    public WebElement clickSaveButton() {
        saveButton.click();
        return saveButton;
    }

    public WebElement clickSaveAndContinueButton() {
        saveAndContinue.click();
        return saveAndContinue;
    }

}