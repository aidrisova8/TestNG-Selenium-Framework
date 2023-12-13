package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConfigReader;
import utilities.TestBase;
/*
Test case 1:
1. Go to http://www.weatherbase.com/
2. Search for zip code 60656
3. Validate that result is including Chicago, IL

Test case 2:
1. Go to http://www.weatherbase.com/
2. Search for invalid zip code 1234
3. Validate error message 'We're sorry. Your search for 1234 returned no results. Please try the following:  '

Test case 3:
1. Go to http://www.weatherbase.com/
2. Click on North America then United States of America
3. Validate that page has listed at least 50 states

Test case 4:
1. Go to http://www.weatherbase.com/
2. Click on North America, United States of America, California, and San Jose
3. Then click on Celsius
4. Validate that in Average Temperature table it will display 'C' letter

Test case 5:
1. Go to http://www.weatherbase.com/
2. Click on North America, United States of America, California, and San Jose
3. Then click on Celsius
4. Validate that temperature  in Fahrenheit is correctly converted to Celsius. (Validate only Annual temperature in Average Temperature table.)
 */
public class WeatherbaseApp extends TestBase {

    @Test(groups = {"regression","smoke","searchBar","verifySearchByZipcode"})
    public void verifySearchByZipcode(){
        driver.get(ConfigReader.getProperty("WeatherbaseURL"));

        WeatherbaseMainPage weatherbaseMainPage=new WeatherbaseMainPage();
        weatherbaseMainPage.searchBar.clear();
        weatherbaseMainPage.searchBar.sendKeys(ConfigReader.getProperty("WeatherbaseZipcode"));
        weatherbaseMainPage.searchBarBtn.click();

        WeatherbaseSearchResultsPage weatherbaseSearchResultsPage =new WeatherbaseSearchResultsPage();
        String expectedCity="Chicago, Illinois";
        String actualCity= weatherbaseSearchResultsPage.chicagoIL.getText();
        Assert.assertEquals(actualCity,expectedCity);

    }

    @Test(groups = {"regression","smoke","searchBar","verifySearchByInvalidZipcode"})
    public void verifySearchByInvalidZipcode(){
        driver.get(ConfigReader.getProperty("WeatherbaseURL"));

        WeatherbaseMainPage weatherbaseMainPage=new WeatherbaseMainPage();
        weatherbaseMainPage.searchBar.clear();
        weatherbaseMainPage.searchBar.sendKeys(ConfigReader.getProperty("WeatherbaseInvalidZipcode"));
        weatherbaseMainPage.searchBarBtn.click();

        WeatherbaseErrorMsgsPage weatherbaseErrorMsgsPage=new WeatherbaseErrorMsgsPage();
        String expectedErrorMsg="We're sorry. Your search for 1234 returned no results. Please try the following:";
        String actualErrorMsg= weatherbaseErrorMsgsPage.zipcodeErrorMsg.getText();
        Assert.assertEquals(actualErrorMsg,expectedErrorMsg);

    }

    @Test(groups = {"regression","UI","verifyNumberOfUSStates"})
    public void verifyNumberOfUSStates() throws InterruptedException {
        driver.get(ConfigReader.getProperty("WeatherbaseURL"));
        WeatherbaseMainPage weatherbaseMainPage=new WeatherbaseMainPage();
        Thread.sleep(4000);
        weatherbaseMainPage.northAmericaLink.click();

        WeatherbaseNorthAmericaPage weatherbaseNorthAmericaPage=new WeatherbaseNorthAmericaPage();
        Thread.sleep(4000);
        weatherbaseNorthAmericaPage.USALink.click();

       WeatherbaseUSAPage weatherbaseUSAPage=new WeatherbaseUSAPage();
        int actualCountOfStates= weatherbaseUSAPage.listOfUSStates.size();
        int expectedCountOfStates=50;
        Assert.assertTrue(actualCountOfStates>=expectedCountOfStates);

    }

    @Test(groups = {"regression","UI","verifyCelsius"})
    public void verifyCelsius() throws InterruptedException {
        driver.get(ConfigReader.getProperty("WeatherbaseURL"));
        WeatherbaseMainPage weatherbaseMainPage=new WeatherbaseMainPage();
        weatherbaseMainPage.northAmericaLink.click();
        Thread.sleep(2000);

        WeatherbaseNorthAmericaPage weatherbaseNorthAmericaPage=new WeatherbaseNorthAmericaPage();
        weatherbaseNorthAmericaPage.USALink.click();

        WeatherbaseUSAPage weatherbaseUSAPage=new WeatherbaseUSAPage();
        weatherbaseUSAPage.stateCalifornia.click();

        WeatherbaseCaliforniaStatePage weatherbaseCaliforniaStatePage=new WeatherbaseCaliforniaStatePage();
        weatherbaseCaliforniaStatePage.citySanJose.click();

        WeatherbaseSanJosePage weatherbaseSanJosePage=new WeatherbaseSanJosePage();
        String actualResult= weatherbaseSanJosePage.celsiusElement.getText().substring(1);
        String expectedResult="C";
        Assert.assertEquals(actualResult,expectedResult);

    }
    @Test(groups = {"regression","smoke","UI","verifyConversionFromFahrenheitToCelsius"})
    public void verifyConversionFromFahrenheitToCelsius() throws InterruptedException {
        driver.get(ConfigReader.getProperty("WeatherbaseURL"));
        WeatherbaseMainPage weatherbaseMainPage=new WeatherbaseMainPage();
        Thread.sleep(4000);
        weatherbaseMainPage.northAmericaLink.click();

        WeatherbaseNorthAmericaPage weatherbaseNorthAmericaPage=new WeatherbaseNorthAmericaPage();
        Thread.sleep(4000);
        weatherbaseNorthAmericaPage.USALink.click();

        WeatherbaseUSAPage weatherbaseUSAPage=new WeatherbaseUSAPage();
        Thread.sleep(4000);
        weatherbaseUSAPage.stateCalifornia.click();


        WeatherbaseCaliforniaStatePage weatherbaseCaliforniaStatePage=new WeatherbaseCaliforniaStatePage();
        weatherbaseCaliforniaStatePage.citySanJose.click();

        WeatherbaseSanJosePage weatherbaseSanJosePage=new WeatherbaseSanJosePage();
        String annualAverageTempInFStr=weatherbaseSanJosePage.annualAverageTemperature.getText();
        double annualAverageTempInF=Double.parseDouble(annualAverageTempInFStr);
        Thread.sleep(4000);
        weatherbaseSanJosePage.celsiusElement.click();
        Double actualAnnualAverageTempInC=Double.parseDouble(weatherbaseSanJosePage.annualAverageTemperature.getText());
        double expectedAnnualAverageTempInC=Math.round(((annualAverageTempInF-32)*5/9)*10)/10.0;
        Assert.assertEquals(actualAnnualAverageTempInC,expectedAnnualAverageTempInC);

    }

}
