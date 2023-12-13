package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.io.IOException;


public class BlazeDemoTests extends TestBase {



    @Test(groups={"regression","smoke","MB-10648","selectingCities","verifyDestinationCities"})
    public void verifyDestinationCities(){
    driver.get(ConfigReader.getProperty("BlazeDemoURL"));

    String fromCity="Paris";
    String toCity="London";

        BrowserUtils.selectDropdownByValue(driver.findElement(By.name("fromPort")),fromCity);
        BrowserUtils.selectDropdownByValue(driver.findElement(By.name("toPort")),toCity);
        WebElement findFlightsBtn= driver.findElement(By.tagName("input"));
        findFlightsBtn.click();
        String flightText=driver.findElement(By.tagName("h3")).getText();
        String expectedFlightText="Flights from "+fromCity+" to "+toCity+":";
        Assert.assertEquals(flightText,expectedFlightText);

    }

    @Test(groups={"regression","smoke","selectingCities","verifyDestinationOfTheWeek"})
    public void verifyDestinationOfTheWeek(){
        driver.get(ConfigReader.getProperty("BlazeDemoURL"));
        driver.findElement(By.partialLinkText("The Beach!")).click();
        String actualVacationText=driver.findElement(By.xpath("//div[contains(text(),'Hawaii')]")).getText();
        String expectedVacationText="Destination of the week: Hawaii !";
        Assert.assertEquals(actualVacationText,expectedVacationText);
    }

    @Test(groups={"regression","flights","verifyFlightInfo"})
    public void verifyFlightInfo() throws IOException {
        driver.get(ConfigReader.getProperty("BlazeDemoURL"));
        String fromCity="Paris";
        String toCity="London";
        BrowserUtils.selectDropdownByValue(driver.findElement(By.name("fromPort")),fromCity);
        BrowserUtils.selectDropdownByValue(driver.findElement(By.name("toPort")),toCity);
        WebElement findFlightsBtn= driver.findElement(By.tagName("input"));
        findFlightsBtn.click();
        WebElement chooseFlightBtn=driver.findElement(By.xpath("//tr[1]//input[@type='submit']"));
        String expectedFlightNo=driver.findElement(By.xpath("//tr[1]/td[2]")).getText();
        String expectedAirline=driver.findElement(By.xpath("//tr[1]/td[3]")).getText();
        String expectedPrice=driver.findElement(By.xpath("//tr[1]/td[6]")).getText();

        chooseFlightBtn.click();

        String actualAirline=driver.findElement(By.xpath("//div[2]/p[1]")).getText();
        String actualFlightNo=driver.findElement(By.xpath("//div[2]/p[2]")).getText();
        String actualPrice=driver.findElement(By.xpath("//div[2]/p[3]")).getText();

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(actualAirline.substring(actualAirline.indexOf(" ")+1),expectedAirline);
        softAssert.assertEquals(actualFlightNo.substring(actualFlightNo.lastIndexOf(" ")+1),expectedFlightNo);
        softAssert.assertEquals(actualPrice.substring(actualPrice.indexOf(" ")+1),expectedPrice.substring(1));
BrowserUtils.takeScreenshot("BlazeDemo_Test");
        softAssert.assertAll();


    }



}
