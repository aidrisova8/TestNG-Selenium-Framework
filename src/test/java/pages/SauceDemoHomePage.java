package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class SauceDemoHomePage {

    public SauceDemoHomePage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//select[@class='product_sort_container']")
    public WebElement sortingDropDown;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> itemPrices;

    @FindBy(id="add-to-cart-sauce-labs-backpack")
    public WebElement backpackCartBtn;

    @FindBy(id="add-to-cart-sauce-labs-bike-light")
    public WebElement bikeLightCartBtn;

    @FindBy(id="add-to-cart-sauce-labs-onesie")
    public WebElement sauceLabsOnesieCartBtn;

    @FindBy(id="item_2_title_link")
    public WebElement sauceLabsOnesieItemExpected;

    @FindBy(id="shopping_cart_container")
    public WebElement cartBtn;


}
