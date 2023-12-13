package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WeatherbaseSanJosePage {

    public WeatherbaseSanJosePage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//a[text()='°C']")
    public WebElement celsiusElement;

    @FindBy(xpath = "//table[2]//tr[2]/td[2] ")
    public WebElement annualAverageTemperature;
}
