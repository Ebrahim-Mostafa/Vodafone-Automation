package pages;

import BasePackage.BasePage;
import Utilities.ObjectRepositoryJsonParser;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    WebElement Email = ObjectRepositoryJsonParser.getObjectLocator("$.loginpage.email");
    WebElement Password = ObjectRepositoryJsonParser.getObjectLocator("$.loginpage.password");
    WebElement SignInBtn=ObjectRepositoryJsonParser.getObjectLocator("$.loginpage.signinbtn");
    WebElement emailAddress = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.emailaddress");
    WebElement createAccountBtn = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.createaccountbtn");

    public void loginIntoAccount(String email,String password){
        elementClear(Email);
        elementClick(Email);
        elementSendKeys(Email,email);
        elementClear(Password);
        elementClick(Password);
        elementSendKeys(Password,password);
        elementClick(SignInBtn);
    }

    public void createAccount(String email){
        elementClear(emailAddress);
        elementClick(emailAddress);
        elementSendKeys(emailAddress,email);
        elementClick(createAccountBtn);
    }

    public static String failedLogin(){
        WebElement failedLogin = ObjectRepositoryJsonParser.getObjectLocator("$.loginpage.failedauth");
        String failedLoginMessage=failedLogin.getText();
        return failedLoginMessage;
    }

    public static String withoutCreds(){
        WebElement noCreds = ObjectRepositoryJsonParser.getObjectLocator("$.loginpage.withoutcreds");
        String loginWithoutCreds = noCreds.getText();
        return loginWithoutCreds;
    }

    public static String alreadyRegisteredEmail(){
        WebElement alreadyRegisteredEmailMessage = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.alreadyregisteremail");
        String failedMessage = alreadyRegisteredEmailMessage.getText();
        return failedMessage;
    }
}
