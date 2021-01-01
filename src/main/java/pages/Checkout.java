package pages;

import BasePackage.BasePage;
import Utilities.JSUtils;
import Utilities.ObjectRepositoryJsonParser;
import org.openqa.selenium.WebElement;

public class Checkout extends BasePage {

    WebElement tShirts = ObjectRepositoryJsonParser.getObjectLocator("$.purchase.tshirts");
    WebElement Women = ObjectRepositoryJsonParser.getObjectLocator("$.purchase.women");

    public void clickOnTshirts(){
        elementClick(tShirts);
    }

    public void clickOnWomen(){
        elementHoverOver(Women);
        elementClick(tShirts);
    }

    public void hooverOverShortSleeveTshirt(){
        WebElement shortSleeve = ObjectRepositoryJsonParser.getObjectLocator("$.purchase.shortsleeve");
        JSUtils.scrollIntoView(shortSleeve);
        elementHoverOver(shortSleeve);
    }

    public void addToCartBtn(){
        WebElement addToCartBtn = ObjectRepositoryJsonParser.getObjectLocator("$.purchase.addtocart");
        elementClick(addToCartBtn);
    }

    public void clickOnCheckoutBtn(){
        WebElement checkoutBtn = ObjectRepositoryJsonParser.getObjectLocator("$.purchase.proceedtocheckout");
        JSUtils.scrollIntoView(checkoutBtn);
        elementClick(checkoutBtn);
    }

    public void proceedToCheckoutBtn(){
        WebElement checkoutBtn = ObjectRepositoryJsonParser.getObjectLocator("$.purchase.paymentproceedtocheckout");
        JSUtils.scrollIntoView(checkoutBtn);
        elementClick(checkoutBtn);
    }

    public void clickOntermsOfServiceCheckBox(){
        WebElement termsOfService = ObjectRepositoryJsonParser.getObjectLocator("$.purchase.termsofservice");
        JSUtils.scrollIntoView(termsOfService);
        elementClick(termsOfService);
    }

    public void choosePayByBankWire(){
        WebElement payByBankWire = ObjectRepositoryJsonParser.getObjectLocator("$.purchase.paymentmethod");
        JSUtils.scrollIntoView(payByBankWire);
        elementClick(payByBankWire);
    }

    public void confirmMyOrder(){
        WebElement confrimOrder = ObjectRepositoryJsonParser.getObjectLocator("$.purchase.confirmorder");
        JSUtils.scrollIntoView(confrimOrder);
        elementClick(confrimOrder);
    }

    public boolean completeOrder(){
        WebElement orderComplete = ObjectRepositoryJsonParser.getObjectLocator("$.purchase.completeorder");
        JSUtils.scrollIntoView(orderComplete);
        boolean compOrder=orderComplete.isDisplayed();
        return compOrder;
    }
}
