package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreAppCreateAccountPage {

    public StoreAppCreateAccountPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="firstname")
    public WebElement firstNameInput;

    @FindBy(id="lastname")
    public WebElement lastNameInput;

    @FindBy(id="is_subscribed")
    public WebElement subscribeCheckbox;

    @FindBy(id="email_address")
    public WebElement emailInput;

    @FindBy(id="password")
    public WebElement passwordInput;

    @FindBy(id="password-confirmation")
    public  WebElement passwordConfirmationInput;

    @FindBy(xpath = "//button[@title='Create an Account']")
    public WebElement createAccountBtn;

}
