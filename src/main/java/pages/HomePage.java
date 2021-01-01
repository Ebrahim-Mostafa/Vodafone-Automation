package pages;

import BasePackage.BasePage;
import Utilities.ObjectRepositoryJsonParser;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    WebElement SignIn = ObjectRepositoryJsonParser.getObjectLocator("$.homepage.signin");


    public void clickOnSignin(){
        elementClick(SignIn);
    }
    public void clickOnSignout(){
        WebElement SignOut = ObjectRepositoryJsonParser.getObjectLocator("$.homepage.signout");
        elementClick(SignOut);
    }
}
