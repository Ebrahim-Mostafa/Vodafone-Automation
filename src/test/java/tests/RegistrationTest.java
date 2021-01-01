package tests;

import BasePackage.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterationPage;

public class RegistrationTest extends BaseTest {

    private static HomePage homePage;
    private static LoginPage loginPage;
    private static RegisterationPage registerationpage;
    public static SoftAssert softAssert;

    @Test(priority = 1)
    public void alreadyRegisteredEmail(){
        softAssert=new SoftAssert();
        homePage=new HomePage();
        homePage.clickOnSignin();
        loginPage=new LoginPage();
        loginPage.createAccount("xokoka2392@aranelab.com");
        String errorMessage = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
        softAssert.assertEquals(loginPage.alreadyRegisteredEmail(),errorMessage);
        softAssert.assertTrue(loginPage.alreadyRegisteredEmail().contains(errorMessage));
        Assert.assertTrue(loginPage.alreadyRegisteredEmail().contains(errorMessage));
    }

    @Test(priority = 2)
    public void requiredFieldsEmpty(){
        softAssert=new SoftAssert();
        loginPage=new LoginPage();
        loginPage.createAccount("rolodi2988@aomrock.com");
        registerationpage = new RegisterationPage();
        registerationpage.setCustomerTitleMr();
        registerationpage.setCustomerFirstName("  ");
        registerationpage.setCustomerLastName(" ");
        registerationpage.setCustomerEmailField(" ");
        registerationpage.setCustomerPasswordField("12345");
        registerationpage.selectCustomerDateOfBirthDay("6");
        registerationpage.selectCustomerDateOfBirthMonth("9");
        registerationpage.selectCustomerDateOfBirthYear("1993");
        registerationpage.setCustomerFirstNameField("  ");
        registerationpage.setCustomerLastNameField(" ");
        registerationpage.setCompanyField(" ");
        registerationpage.setAddressField(" ");
        registerationpage.setAddressFieldTwo("   ");
        registerationpage.setCityField(" ");
        registerationpage.selectState("Kansas");
        registerationpage.setPostalCodeField(" ");
        registerationpage.selectCountry("United States");
        registerationpage.setAdditionalInfo("   ");
        registerationpage.setHomePhoneField("0226903517");
        registerationpage.setMobilePhoneField(" ");
        registerationpage.setAddressAliasField(" ");
        registerationpage.clickOnRegisterBtn();
        softAssert.assertTrue(registerationpage.dangerAlert());
        Assert.assertTrue(registerationpage.dangerAlert());
    }

    @Test(priority = 3)
    public void createAccountSuccessfully(){
        softAssert=new SoftAssert();
        homePage=new HomePage();
        homePage.clickOnSignin();
        loginPage=new LoginPage();
        loginPage.createAccount("keliw97550@28woman.com");
        registerationpage = new RegisterationPage();
        registerationpage.setCustomerTitleMr();
        registerationpage.setCustomerFirstName("Ebrahim");
        registerationpage.setCustomerLastName("Mostafa");
        registerationpage.setCustomerEmailField("keliw97550@28woman.com");
        registerationpage.setCustomerPasswordField("12345");
        registerationpage.selectCustomerDateOfBirthDay("6");
        registerationpage.selectCustomerDateOfBirthMonth("9");
        registerationpage.selectCustomerDateOfBirthYear("1993");
        registerationpage.setCustomerFirstNameField("Ebrahim");
        registerationpage.setCustomerLastNameField("Mostafa");
        registerationpage.setCompanyField("Vodafone");
        registerationpage.setAddressField("Maadi");
        registerationpage.setAddressFieldTwo("Nasr City");
        registerationpage.setCityField("Cairo");
        registerationpage.selectState("Kansas");
        registerationpage.setPostalCodeField("19960");
        registerationpage.selectCountry("United States");
        registerationpage.setAdditionalInfo("Automation Testing");
        registerationpage.setHomePhoneField("0226903517");
        registerationpage.setMobilePhoneField("01118766522");
        registerationpage.setAddressAliasField("Test");
        registerationpage.clickOnRegisterBtn();
        String expectedURL = "http://automationpractice.com/index.php?controller=my-account";
        String expectedTitle ="My account - My Store" ;
        softAssert.assertEquals(driver.getCurrentUrl(),expectedURL);
        softAssert.assertEquals(driver.getTitle(),expectedTitle);
        homePage.clickOnSignout();
    }
}
