package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WeatherbaseMainPage {

    public WeatherbaseMainPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="query")
    public WebElement searchBar;

    @FindBy(xpath = " //button[@type='submit']")
    public WebElement searchBarBtn;

    @FindBy(xpath = "//li[11]")////li[11]  //a[text()='North America']  United States
    public WebElement northAmericaLink;



}
