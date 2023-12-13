package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class SauceDemoCartPage {

    public SauceDemoCartPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);

    }
    @FindBy(id="checkout")
    public WebElement checkoutBtn;

    @FindBy(id="item_2_title_link")
    public WebElement sauceLabsOnesieItemActual;

    @FindBy(id="remove-sauce-labs-onesie")
    public WebElement removeBtn;

    @FindBy(xpath = "//html[@lang='en']")
    public List<WebElement> allElementsInTheCartPage;
}
