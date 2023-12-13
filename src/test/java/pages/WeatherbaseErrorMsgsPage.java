package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WeatherbaseErrorMsgsPage {

    public WeatherbaseErrorMsgsPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@id=\"left-content\"]/p[1]")
    public WebElement zipcodeErrorMsg;
}
