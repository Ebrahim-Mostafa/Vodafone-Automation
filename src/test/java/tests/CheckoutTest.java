package tests;

import BasePackage.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Checkout;

public class CheckoutTest extends BaseTest {

    private static Checkout checkout;
    public static SoftAssert softAssert;

    @Test
    public void purchaseTshirt(){
        softAssert=new SoftAssert();
        checkout=new Checkout();
        checkout.clickOnWomen();
        checkout.hooverOverShortSleeveTshirt();
        checkout.addToCartBtn();
        checkout.clickOnCheckoutBtn();
        checkout.proceedToCheckoutBtn();
        checkout.proceedToCheckoutBtn();
        checkout.clickOntermsOfServiceCheckBox();
        checkout.proceedToCheckoutBtn();
        checkout.choosePayByBankWire();
        checkout.confirmMyOrder();
        softAssert.assertTrue(checkout.completeOrder());
    }
}
