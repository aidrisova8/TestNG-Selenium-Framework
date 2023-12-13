package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class WeatherbaseUSAPage {

    public WeatherbaseUSAPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//li")
    public List<WebElement> listOfUSStates;

    @FindBy(xpath = "//a[text()='California']")
    public WebElement stateCalifornia;
}
