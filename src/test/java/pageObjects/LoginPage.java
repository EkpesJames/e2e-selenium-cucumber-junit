package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(id="Email")
    private WebElement txtEmail;

    @FindBy(id="Password")
    private WebElement txtPassword;

    @FindBy(css="button[type='submit']")
    private WebElement btnLogin;

    @FindBy(linkText = "Logout")
    private WebElement linkLogout;


    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement setUserName(String uname){
        txtEmail.clear();
        txtEmail.sendKeys(uname);
        return txtEmail;
    }

    public WebElement setPassword(String pass){
        txtPassword.clear();
        txtPassword.sendKeys(pass);
        return txtPassword;
    }

    public WebElement clickLogin(){
        btnLogin.click();
        return btnLogin;
    }

    public WebElement clickLogOut(){
        linkLogout.click();
        return linkLogout;
    }

}