package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.List;

public class SauceDemoTests extends TestBase {



    @Test(priority = 1,groups={"regression","smoke","login","verifyLoginFunctionality"})
    public void verifyLoginFunctionality(){
        SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage();
        sauceDemoLoginPage.login(ConfigReader.getProperty("SauceUsername"),ConfigReader.getProperty("SaucePassword"));
        WebElement productsTitle=driver.findElement(By.xpath("//span[@class='title']")); //it checks text itself
        Assert.assertEquals(productsTitle.getText(),"PRODUCTS");

//        WebElement productTitle1=driver.findElement(By.xpath("//span[@class='title']"));  it checks just if webelement is displayed, but not the text, here can be any text
//        Assert.assertTrue(productTitle1.isDisplayed());
    }

    @Test(groups = {"regression","smoke","login","verifyLoginFunctionalityNegative"})
    public void verifyLoginFunctionalityNegative(){
        SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage();
        sauceDemoLoginPage.login(ConfigReader.getProperty("SauceLockedOutUser"),ConfigReader.getProperty("SaucePassword"));
        String expectedErrorMsg="Epic sadface: Sorry, this user has been locked out.";
        String actualErrorMsg= sauceDemoLoginPage.errorMsg.getText();
        Assert.assertEquals(actualErrorMsg,expectedErrorMsg);

    }

    @Test(priority = 2,groups={"regression","priceFilter","verifyPriceHighToLow"})
    public void verifyPriceHighToLow(){
        SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage();
        sauceDemoLoginPage.login(ConfigReader.getProperty("SauceUsername"),ConfigReader.getProperty("SaucePassword"));
        SauceDemoHomePage sauceDemoHomePage=new SauceDemoHomePage();
        Select select=new Select(sauceDemoHomePage.sortingDropDown);
        select.selectByValue("hilo");
        List <WebElement> el=select.getOptions();//count the number of options
        System.out.println(el.size());

        for(int i=0;i<sauceDemoHomePage.itemPrices.size()-1;i++){
        double itemprice1=Double.parseDouble(sauceDemoHomePage.itemPrices.get(i).getText().substring(1));
        double itemprice2=Double.parseDouble(sauceDemoHomePage.itemPrices.get(i+1).getText().substring(1));
            System.out.println(itemprice1+" is greater or equal than "+itemprice2);
        Assert.assertTrue(itemprice1>=itemprice2);
        }
    }

    @Test(groups = {"regression","cart","verifyCheckOutTotal"})
    public void verifyCheckOutTotal(){
    SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage();
    sauceDemoLoginPage.login(ConfigReader.getProperty("SauceUsername"),ConfigReader.getProperty("SaucePassword"));

    SauceDemoHomePage sauceDemoHomePage=new SauceDemoHomePage();
    sauceDemoHomePage.backpackCartBtn.click();
    sauceDemoHomePage.bikeLightCartBtn.click();
    sauceDemoHomePage.cartBtn.click();

    SauceDemoCartPage sauceDemoCartPage=new SauceDemoCartPage();
    sauceDemoCartPage.checkoutBtn.click();

    SauceDemoCheckoutPage sauceDemoCheckoutPage=new SauceDemoCheckoutPage();
    sauceDemoCheckoutPage.firstName.sendKeys("Patel");
    sauceDemoCheckoutPage.lastName.sendKeys("Harsh");
    sauceDemoCheckoutPage.postalCode.sendKeys("45067");
    sauceDemoCheckoutPage.continueBtn.click();

    SauceDemoCheckoutOverviewPage sauceDemoCheckoutOverviewPage=new SauceDemoCheckoutOverviewPage();
    String subtotalPriceStr=sauceDemoCheckoutOverviewPage.subtotalPrice.getText();
    double subtotalPriceDouble= Double.parseDouble(subtotalPriceStr.substring(subtotalPriceStr.indexOf("$")+1));
    double sumOfItemPrices=0.0;
        for(int i=0;i<sauceDemoCheckoutOverviewPage.cartItemPrices.size()-1;i++){
            double price1=Double.parseDouble(sauceDemoCheckoutOverviewPage.cartItemPrices.get(i).getText().substring(1));
            double price2=Double.parseDouble(sauceDemoCheckoutOverviewPage.cartItemPrices.get(i+1).getText().substring(1));
            sumOfItemPrices=price1+price2;
        }

        Assert.assertEquals(sumOfItemPrices,subtotalPriceDouble,"The prices did not match");

    }

    @Test(groups = {"regression","smoke","login","loginWithInvalidPassword"})
    public void loginWithInvalidPassword(){
    SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage();
    driver.get(ConfigReader.getProperty("SauceDemoURL"));
    sauceDemoLoginPage.usernameInput.sendKeys(ConfigReader.getProperty("SauceUsername"));
    sauceDemoLoginPage.passwordInput.sendKeys(ConfigReader.getProperty("SauceInvalidPassword"));
    sauceDemoLoginPage.loginBtn.click();
    String expectedErrorMsg="Epic sadface: Username and password do not match any user in this service";
    String actualErrorMsg=sauceDemoLoginPage.errorMsg.getText();
    Assert.assertEquals(actualErrorMsg,expectedErrorMsg);

    }

    @Test(groups = {"regression","smoke","cart","verifyAddToCartFunctionality"})
    public void verifyAddToCartFunctionality(){

        SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage();
        sauceDemoLoginPage.login(ConfigReader.getProperty("SauceUsername"),ConfigReader.getProperty("SaucePassword"));

        SauceDemoHomePage sauceDemoHomePage=new SauceDemoHomePage();
        String expectedItem=sauceDemoHomePage.sauceLabsOnesieItemExpected.getText();
        sauceDemoHomePage.sauceLabsOnesieCartBtn.click();
        sauceDemoHomePage.cartBtn.click();

        SauceDemoCartPage sauceDemoCartPage=new SauceDemoCartPage();
        String actualItem=sauceDemoCartPage.sauceLabsOnesieItemActual.getText();

        Assert.assertEquals(actualItem,expectedItem);

    }
    @Test(groups = {"regression","smoke","cart","verifyRemoveBtnOnCartFunctionality"})
    public void verifyRemoveBtnOnCartFunctionality(){

        SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage();
        sauceDemoLoginPage.login(ConfigReader.getProperty("SauceUsername"),ConfigReader.getProperty("SaucePassword"));

        SauceDemoHomePage sauceDemoHomePage=new SauceDemoHomePage();
        String addedToCartItem=sauceDemoHomePage.sauceLabsOnesieItemExpected.getText();
        sauceDemoHomePage.sauceLabsOnesieCartBtn.click();
        sauceDemoHomePage.cartBtn.click();

        SauceDemoCartPage sauceDemoCartPage=new SauceDemoCartPage();
        sauceDemoCartPage.removeBtn.click();

        for (WebElement el:sauceDemoCartPage.allElementsInTheCartPage) {
            Assert.assertFalse(el.getText().contains(addedToCartItem));
        }
    }
}
