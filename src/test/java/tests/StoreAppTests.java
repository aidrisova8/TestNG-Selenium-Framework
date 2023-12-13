package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.StoreAppCreateAccountPage;
import pages.StoreAppMainPage;
import pages.StoreAppSignInPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

public class StoreAppTests extends TestBase {
    String email;
    String password;
    String firstName;
    String lastName;

    @DataProvider(name = "signUpTestData")
    public static Object[][] testData(){
        return new Object[][]{
            {"Harsh","Patel"},
            {"Jane","Doe"},
            {"Kim","Yan"}
        };
    }

    @Test(groups = {"regression", "smoke", "storeApp", "verifyCreateAccount"},dataProvider = "signUpTestData")
    public void verifyCreateAccount(String firstName, String lastName) {
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        StoreAppMainPage storeAppMainPage = new StoreAppMainPage();
        storeAppMainPage.createAccountLink.click();

        StoreAppCreateAccountPage storeAppCreateAccountPage = new StoreAppCreateAccountPage();
        this.firstName=firstName;
        this.lastName=lastName;
        storeAppCreateAccountPage.firstNameInput.sendKeys(firstName);
        storeAppCreateAccountPage.lastNameInput.sendKeys(lastName);
        storeAppCreateAccountPage.subscribeCheckbox.click();

        email = BrowserUtils.getRandomEmail();
        password = BrowserUtils.getRandomStrongPassword();

        storeAppCreateAccountPage.emailInput.sendKeys(email);
        storeAppCreateAccountPage.passwordInput.sendKeys(password);
        storeAppCreateAccountPage.passwordConfirmationInput.sendKeys(password);
        storeAppCreateAccountPage.createAccountBtn.click();

        System.out.println(email);
        System.out.println(password);
        Assert.assertTrue(driver.getTitle().contains("My Account"));
    }

    @Test(groups = {"regression", "smoke", "storeApp", "verifySignIn"},dependsOnMethods ="verifyCreateAccount" )
    public void verifySignIn(){
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        StoreAppMainPage storeAppMainPage=new StoreAppMainPage();
        storeAppMainPage.signInLink.click();

        StoreAppSignInPage storeAppSignInPage=new StoreAppSignInPage();
        storeAppSignInPage.emailInput.sendKeys(email);
        storeAppSignInPage.passwordInput.sendKeys(password);
        storeAppSignInPage.signInBtn.click();
        Assert.assertTrue(driver.getTitle().contains("Home Page"));

    }

}