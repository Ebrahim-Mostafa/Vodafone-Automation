package tests;

import BasePackage.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    private static HomePage homePage;
    private static LoginPage loginPage;
    public static SoftAssert softAssert;

    @Test(priority = 1)
    public void invalidCredentials(){
        softAssert = new SoftAssert();
        homePage=new HomePage();
        homePage.clickOnSignin();
        loginPage=new LoginPage();
        loginPage.loginIntoAccount("tiwopi6827@aomrock.com","123456");
        softAssert.assertTrue(loginPage.failedLogin().contains("Authentication failed."));
    }

    @Test(priority = 2)
    public void loginWithoutCredentials(){
        softAssert = new SoftAssert();
        homePage=new HomePage();
        homePage.clickOnSignin();
        loginPage=new LoginPage();
        loginPage.loginIntoAccount("  ","  ");
        softAssert.assertTrue(loginPage.withoutCreds().contains("An email address required."));
    }

    @Test(priority = 3)
    public void emailFormat(){
        softAssert = new SoftAssert();
        homePage=new HomePage();
        homePage.clickOnSignin();
        loginPage=new LoginPage();
        loginPage.loginIntoAccount("email@email.email","12345");
        softAssert.assertTrue(loginPage.failedLogin().contains("Authentication failed."));
    }

    @Test(priority = 4)
    public void successfulLogin(){
        softAssert=new SoftAssert();
        homePage=new HomePage();
        homePage.clickOnSignin();
        loginPage=new LoginPage();
        loginPage.loginIntoAccount("tiwopi6827@aomrock.com","12345");//xatac43543@aranelab.com"
        String expectedURL = "http://automationpractice.com/index.php?controller=my-account";
        String expectedTitle ="My account - My Store" ;
        softAssert.assertEquals(driver.getCurrentUrl(),expectedURL);
        softAssert.assertEquals(driver.getTitle(),expectedTitle);
//      homePage.clickOnSignout();
    }
}
