package projects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HRAppHomePage;
import pages.HRAppLoginPage;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.List;

public class EditAndDeleteEmployee extends TestBase {

    @Test
    public void editEmployee() throws InterruptedException {

        driver.get(ConfigReader.getProperty("HRAppURL"));
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(ConfigReader.getProperty("HRAppUsername"));
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ConfigReader.getProperty("HRAppPassword"));
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        Thread.sleep(3000);
        List<WebElement> table=driver.findElements(By.xpath("//tr"));

        int index=table.size()-1;

        driver.findElement(By.xpath("(//button[@class='btn btn-success'])["+index+"]")).click();

        //driver.findElement(By.xpath("(//button[@class='btn btn-success'])[2]")).click();
        driver.findElement(By.name("firstName")).clear();
        driver.findElement(By.name("firstName")).sendKeys("Patel");
        Thread.sleep(1000);
        driver.findElement(By.name("lastName")).clear();
        driver.findElement(By.name("lastName")).sendKeys("Harsh");
        WebElement dropdown= driver.findElement(By.id("department"));
        Select select=new Select(dropdown);
        select.selectByValue("60");
        WebElement jobDown =driver.findElement(By.id("job"));
        Select select1=new Select(jobDown);
        select1.selectByValue("AD_VP");
        driver.findElement(By.xpath("//input[@name='salary']")).clear();
        driver.findElement(By.xpath("//input[@name='salary']")).sendKeys("10000000");
        driver.findElement(By.xpath("//button")).click();


        Thread.sleep(1000);
        List<WebElement> table2=driver.findElements(By.xpath("/html/body/app-root/div[1]/app-employee-details"));
        for(int i=0; i<table2.size(); i++){
            System.out.println(table2.get(i).getText());
            Assert.assertTrue(table2.get(i).getText().contains("Patel Harsh IT Edit Delete"));
        }

        }
    @Test
    public void deleteTest() throws InterruptedException {
        driver.get(ConfigReader.getProperty("HRAppURL"));
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(ConfigReader.getProperty("HRAppUsername"));
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ConfigReader.getProperty("HRAppPassword"));
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//button[@class='btn btn-warning'])[5]")).click();
        String actualResult=driver.findElement(By.xpath("//div[@class='alert alert-warning']")).getText();
        String[] strArray=actualResult.split(" ");
        String empID=strArray[4];
        System.out.println(actualResult);
        String expectedResult="Employee with employe id "+empID+" is deleted sucessfully";
        System.out.println(expectedResult);
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void deleteTest2() throws InterruptedException {

        HRAppLoginPage hrAppLoginPage=new HRAppLoginPage();
        hrAppLoginPage.loginProject();

        HRAppHomePage hrAppHomePage=new HRAppHomePage();
        hrAppHomePage.createEmployee.click();

        driver.findElement(By.name("firstName")).sendKeys("Donald");
        driver.findElement(By.name("lastName")).sendKeys("Trump");
        WebElement departmentDropDown= driver.findElement(By.id("department"));
        Select select=new Select(departmentDropDown);
        Thread.sleep(3000);
        select.selectByValue("80");

        WebElement jobTitleDropDown=driver.findElement(By.id("job"));
        Select select1=new Select(jobTitleDropDown);
        select1.selectByVisibleText("Sales Manager");
        WebElement salaryBox=driver.findElement(By.name("salary"));
        salaryBox.clear();
        salaryBox.sendKeys("1000000");
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(3000);
        List<WebElement> table=driver.findElements(By.xpath("//tr"));

        int index=table.size()-1;
        System.out.println(index);
        String id= String.valueOf(index);
        driver.findElement(By.xpath("//input")).sendKeys(id);

        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        driver.findElement(By.xpath("//button")).click();

        String empID=driver.findElement(By.xpath("//td[1]")).getText();
        String expectedResult="Employee with employe id "+empID+" is deleted sucessfully";
        System.out.println(expectedResult);
        String actualResult=driver.findElement(By.xpath("//div[@class='alert alert-warning']")).getText();
        Assert.assertEquals(actualResult,expectedResult);
    }

    }

