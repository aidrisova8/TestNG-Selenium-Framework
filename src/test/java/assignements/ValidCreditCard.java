package assignements;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ConfigReader;
import utilities.TestBase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 1. Given user navigates to https://blazedemo.com/index.php
 2. And user selects flight from "San Diego" to "New York"
 3. When user chooses any flight
 4. And user provides following data into form
 | Name | David Clark |
 | Address | 123 Washington ave. |
 | City | Austin |
 | State | TX |
 | Zip Code | 12345 |
 | Card Type | American Express |
 | Credit Card Number | mycreditcardnumber |
 | Month | 11 |
 | Year | 2025 |
 | Name on Card | David Clark  |
 5. Then validate success message "Invalid credit card number! "
 Test Case 2:
 1. Given user navigates to https://blazedemo.com/index.php
 2. And user selects flight from "San Diego" to "New York"
 3. When user chooses any flight
 4. And user provides following data into form
 | Name | John Doe |
 | Address | 123 Washington ave. |
 | City | New York |
 | State | NY |
 | Zip Code | 12345 |
 | Card Type | Visa |
 | Credit Card Number | 1234567890 |
 | Month | 11 |
 | Year | 2025 |
 | Name on Card | John Doe |
 5. Then validate success message "Thank you for your purchase today! "
 6. Then validate last 4 digits of card is matching with provided card
 7. Then validate date when it was booked is today's date
 *
 */
public class ValidCreditCard extends TestBase {



    @Test
    public void invalidCreditCardNo(){

        driver.get(ConfigReader.getProperty("BlazeDemoURL"));
        Select select=new Select(driver.findElement(By.name("fromPort")));
        select.selectByValue("San Diego");
        Select select1=new Select(driver.findElement(By.name("toPort")));
        select1.selectByValue("New York");
        driver.findElement(By.tagName("input")).click();
        driver.findElement(By.xpath("//tr[2]/td[1]/input")).click();
        driver.findElement(By.id("inputName")).sendKeys("David Clark");
        driver.findElement(By.id("address")).sendKeys("123 Washington ave.");
        driver.findElement(By.id("city")).sendKeys("Austin");
        driver.findElement(By.id("state")).sendKeys("TX");
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        Select select2=new Select(driver.findElement(By.id("cardType")));
        select2.selectByValue("amex");
        driver.findElement(By.id("creditCardNumber")).sendKeys("mycreditcardnumber");
        driver.findElement(By.id("creditCardMonth")).sendKeys("11");
        driver.findElement(By.id("creditCardYear")).sendKeys("2025");
        driver.findElement(By.id("nameOnCard")).sendKeys("David Clark");
        driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
        String expectedInvalidMsg="Invalid credit card number!";
        String actualMsg=driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(actualMsg,expectedInvalidMsg);

    }

    @Test
    public void validCreditCardNo(){
        driver.get(ConfigReader.getProperty("BlazeDemoURL"));
        Select select=new Select(driver.findElement(By.name("fromPort")));
        select.selectByValue("San Diego");
        Select select1=new Select(driver.findElement(By.name("toPort")));
        select1.selectByValue("New York");
        driver.findElement(By.tagName("input")).click();
        driver.findElement(By.xpath("//tr[2]/td[1]/input")).click();
        driver.findElement(By.id("inputName")).sendKeys("John Doe");
        driver.findElement(By.id("address")).sendKeys("123 Washington ave.");
        driver.findElement(By.id("city")).sendKeys("New York");
        driver.findElement(By.id("state")).sendKeys("NY");
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        Select select2=new Select(driver.findElement(By.id("cardType")));
        select2.selectByValue("visa");
        driver.findElement(By.id("creditCardNumber")).sendKeys("1234567890");
        driver.findElement(By.id("creditCardMonth")).sendKeys("11");
        driver.findElement(By.id("creditCardYear")).sendKeys("2025");
        driver.findElement(By.id("nameOnCard")).sendKeys("John Doe");
        driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();

        String expectedValidMsg="Thank you for your purchase today!";
        String actualMsg=driver.findElement(By.tagName("h1")).getText();
        String expectedCardLast4Digits="7890";
        String actualCardLast4Digits=driver.findElement(By.xpath("//tr[4]/td[2]")).getText();
        actualCardLast4Digits=actualCardLast4Digits.substring(actualCardLast4Digits.length()-4);
        LocalDate date = LocalDate.now();
        DateTimeFormatter f=DateTimeFormatter.ofPattern("dd MMM YYYY");
        String todaysDate=date.format(f);
        String actualDate=driver.findElement(By.xpath("//tr[7]/td[2]")).getText();
        boolean dateStatus =false;
        if(actualDate.contains(todaysDate)){
            dateStatus=true;
        }

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualMsg,expectedValidMsg);
        softAssert.assertEquals(actualCardLast4Digits,expectedCardLast4Digits);
        softAssert.assertTrue(dateStatus);
        softAssert.assertAll();
    }

}
