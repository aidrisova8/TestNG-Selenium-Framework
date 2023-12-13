package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class SauceDemoLoginPage {
    WebDriver driver;
    public SauceDemoLoginPage(){
         driver = Driver.getDriver();
        PageFactory .initElements(driver,this);
    }

    @FindBy(id = "user-name")
    public WebElement usernameInput;

    @FindBy(id="password")
    public WebElement passwordInput;

    @FindBy(id="login-button")
    public WebElement loginBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement errorMsg;

    public void login(String username, String password){
        driver.get(ConfigReader.getProperty("SauceDemoURL"));
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

}
