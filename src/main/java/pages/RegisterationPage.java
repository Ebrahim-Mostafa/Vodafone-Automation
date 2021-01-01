package pages;

import BasePackage.BasePage;
import Utilities.JSUtils;
import Utilities.ObjectRepositoryJsonParser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterationPage extends BasePage {

    WebElement RadioBtn = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.radiobox");
    WebElement firstName = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.firstname");
    WebElement lastName = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.lastname");
    WebElement Email = ObjectRepositoryJsonParser.getObjectLocator("$.loginpage.email");
    WebElement Password = ObjectRepositoryJsonParser.getObjectLocator("$.loginpage.password");
    WebElement Day = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.dateofbirthday");
    WebElement Month = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.dateofbirthmonth");
    WebElement Year = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.dateofbirthyear");
    WebElement firstNameFiled = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.firstnamefield");
    WebElement lastNameField = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.lastnamefield");
    WebElement companyField = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.companyfield");
    WebElement Address1 = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.address1");
    WebElement Address2 = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.address2");
    WebElement City = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.city");
    WebElement State = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.state");
    WebElement postalCode = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.postalcode");
    WebElement additionalInfo = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.additionalinfo");
    WebElement Country = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.country");
    WebElement homeNO = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.homeno");
    WebElement mobileNO = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.mobileno");
    WebElement addressAlias = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.addressalias");
    WebElement registerBtn = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.registerbtn");


    public void setCustomerTitleMr() {
        elementClick(RadioBtn);
    }

    public void setCustomerFirstName(String firstname) {
        elementClear(firstName);
        elementClick(firstName);
        elementSendKeys(firstName,firstname);
    }

    public void setCustomerLastName(String lastname) {
        elementClear(lastName);
        elementClick(lastName);
        elementSendKeys(lastName,lastname);
    }

    public void setCustomerEmailField(String email) {
        elementClear(Email);
        elementClick(Email);
        elementSendKeys(Email,email);
    }

    public void setCustomerPasswordField(String password) {
        elementClear(Password);
        elementClick(Password);
        elementSendKeys(Password,password);
    }

    public void selectCustomerDateOfBirthDay(String day) {
        Select dayOfBirth = new Select(Day);
        dayOfBirth.selectByValue(day);
    }

    public void selectCustomerDateOfBirthMonth(String month) {
        Select monthOfBirth = new Select(Month);
        monthOfBirth.selectByValue(month);
    }

    public void selectCustomerDateOfBirthYear(String year) {
        Select yearOfBirth = new Select(Year);
        yearOfBirth.selectByValue(year);
    }

    public void setCustomerFirstNameField(String firstname) {
        elementClear(firstNameFiled);
        elementClick(firstNameFiled);
        elementSendKeys(firstNameFiled,firstname);
    }

    public void setCustomerLastNameField(String lastname) {
        elementClear(lastNameField);
        elementClick(lastNameField);
        elementSendKeys(lastNameField,lastname);
    }

    public void setCompanyField(String company) {
        elementClear(companyField);
        elementClick(companyField);
        elementSendKeys(companyField,company);
    }

    public void setAddressField(String addressone) {
        elementClear(Address1);
        elementClick(Address1);
        elementSendKeys(Address1,addressone);
    }

    public void setAddressFieldTwo(String addrestwo) {
        elementClear(Address2);
        elementClick(Address2);
        elementSendKeys(Address2,addrestwo);
    }

    public void setCityField(String city) {
        elementClear(City);
        elementClick(City);
        elementSendKeys(City,city);
    }

    public void selectState(String state) {
        JSUtils.scrollIntoView(State);
        Select selectState = new Select(State);
        selectState.selectByVisibleText(state);
    }

    public void setPostalCodeField(String zip) {
        elementClear(postalCode);
        elementClick(postalCode);
        elementSendKeys(postalCode,zip);
    }

    public void selectCountry(String country) {
        Select selectCountry = new Select(Country);
        selectCountry.selectByVisibleText(country);
    }

    public void setAdditionalInfo(String info) {
        elementClear(additionalInfo);
        elementClick(additionalInfo);
        elementSendKeys(additionalInfo,info);
    }

    public void setHomePhoneField(String phone) {
        elementClear(homeNO);
        elementClick(homeNO);
        elementSendKeys(homeNO,phone);
    }

    public void setMobilePhoneField(String phone) {
        elementClear(mobileNO);
        elementClick(mobileNO);
        elementSendKeys(mobileNO,phone);
    }

    public void setAddressAliasField(String alias) {
        elementClear(addressAlias);
        elementClick(addressAlias);
        elementSendKeys(addressAlias,alias);
    }

    public void clickOnRegisterBtn(){
        elementClick(registerBtn);
    }

    public static boolean dangerAlert(){
        WebElement redAlert = ObjectRepositoryJsonParser.getObjectLocator("$.createaccount.dangeralert");
        boolean alert = redAlert.isDisplayed();
        return alert;
    }
}
