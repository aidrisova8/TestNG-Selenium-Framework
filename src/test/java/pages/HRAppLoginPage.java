package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class HRAppLoginPage {
    WebDriver driver;
    public HRAppLoginPage(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@name='username']")
    public WebElement projectName;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement projectPassword;

    @FindBy(xpath = "//button[@class='btn btn-success']")
    public WebElement loginBtn;

    public void loginProject() throws InterruptedException {
        driver.get(ConfigReader.getProperty("HRAppURL"));
        projectName.sendKeys(ConfigReader.getProperty("HRAppUsername"));
        projectPassword.sendKeys(ConfigReader.getProperty("HRAppPassword"));
        loginBtn.click();
        Thread.sleep(3000);
    }
}
