package tests;

import base.Constants;
import base.setup;
import base.url;
import data.userData;
import locators.ContactUsLocators;
import locators.RegistrationLocators;
import methods.ApiRequests;
import methods.General;
import org.testng.annotations.Test;
import pages.RegistrationPage;

import javax.security.auth.login.LoginException;

public class ContactUsTest extends setup {
    @Test
    public void testContactUsFormFunctionality() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        ApiRequests requests = new ApiRequests(driver);
        ContactUsLocators locators = new ContactUsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();

        driver.get(url.Contact_US_URL);
        general.enterText(regLoc.firstName, data.getFirstName());
        general.enterText(regLoc.lastName, data.getLastName());
        requests.generateRandomEmailForTest();
        general.enterText(regLoc.email, data.getEmail());
        general.enterText(regLoc.phone, "99"+ data.getPhoneNumber());
        general.enterText(locators.messageField, data.getEmail());
        general.clickElement(regLoc.terms);
        general.clickElement(locators.sendMessageBtn);
        general.waitForElementToBeClickable(locators.sentMessage, 10);
        general.assertTextEquals(locators.sentMessage, Constants.SUCCESSFULLY_SENT);
    }

    @Test
    public void testContactUsWithInvalidEmail() {
        userData data = new userData();
        General general = new General(driver);
        ContactUsLocators locators = new ContactUsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();

        driver.get(url.Contact_US_URL);
        general.enterText(regLoc.firstName, data.getFirstName());
        general.enterText(regLoc.lastName, data.getLastName());
        general.enterText(regLoc.email, Constants.INVALID_EMAIL);
        general.enterText(regLoc.phone, "99"+ data.getPhoneNumber());
        general.enterText(locators.messageField, Constants.INVALID_EMAIL);
        general.clickElement(regLoc.terms);
        general.isDisabled(locators.sendMessageBtn);
        general.waitForElementToBeClickable(regLoc.invalidEmail, 10);
        general.assertTextEquals(regLoc.invalidEmail, Constants.EMAIL_IS_NOT_VALID);
    }

    @Test
    public void testContactUsWithEmptyFirstName() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        ApiRequests requests = new ApiRequests(driver);
        ContactUsLocators locators = new ContactUsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();

        driver.get(url.Contact_US_URL);
        requests.generateRandomEmailForTest();
        general.enterText(regLoc.firstName, "");
        general.enterText(regLoc.lastName, data.getLastName());
        general.enterText(regLoc.email, data.getEmail());
        general.enterText(regLoc.phone, "99"+ data.getPhoneNumber());
        general.enterText(locators.messageField, Constants.HELLO_MESSAGE);
        general.clickElement(regLoc.terms);
        general.isDisabled(locators.sendMessageBtn);
        general.waitForElementToBeClickable(locators.invalidFirstName, 10);
        general.assertTextEquals(locators.invalidFirstName, Constants.FIRST_NAME_IS_NOT_VALID);
    }

    @Test
    public void testContactUsWithEmptyLastName() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        ApiRequests requests = new ApiRequests(driver);
        ContactUsLocators locators = new ContactUsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();

        driver.get(url.Contact_US_URL);
        requests.generateRandomEmailForTest();
        general.enterText(regLoc.firstName, data.getFirstName());
        general.enterText(regLoc.lastName, "");
        general.enterText(regLoc.email, data.getEmail());
        general.enterText(regLoc.phone, "99"+ data.getPhoneNumber());
        general.enterText(locators.messageField, Constants.HELLO_MESSAGE);
        general.clickElement(regLoc.terms);
        general.isDisabled(locators.sendMessageBtn);
        general.waitForElementToBeClickable(locators.invalidLastName, 10);
        general.assertTextEquals(locators.invalidLastName, Constants.LAST_NAME_IS_NOT_VALID);
    }
}
