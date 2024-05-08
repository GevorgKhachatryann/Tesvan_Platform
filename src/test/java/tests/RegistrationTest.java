package tests;

import base.Constants;
import base.setup;
import base.url;
import data.userData;
import locators.RegistrationLocators;
import methods.ApiRequests;
import methods.General;
import org.testng.annotations.Test;
import pages.RegistrationPage;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class RegistrationTest extends setup {
    @Test
    public void registration() throws LoginException, IOException {
        userData data = new userData();
        General general = new General(driver);
        ApiRequests requests = new ApiRequests(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        RegistrationLocators locators = new RegistrationLocators();
        requests.generateRandomEmailForTest();
        driver.get(url.REGISTRATION_URL);
        regPage.register(data.getEmail());
        requests.retrieveVerificationEmail();
        String verificationLink = requests.extractVerificationLink(data.getRegistrationMail());
        System.out.println(Constants.VERIFICATION_LINK + verificationLink);
        driver.get(verificationLink);
        general.waitForElementToBeClickable(locators.successMessage, 10);
        general.assertTextEquals(locators.successMessage, Constants.SUCCESSFULLY_VERIFIED);
        general.clickElement(locators.loginBtn);
        general.waitForElementToBeClickable(locators.email, 10);
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.enterPassword, data.getPassword());
        general.clickElement(locators.loginBtn);
        general.waitForElementToBeClickable(locators.hello, 10);
        general.assertTextEquals(locators.hello, Constants.HELLO_MESSAGE + data.getFirstName() + "!");
    }

    @Test
    public void registerWithInvalidEmail() {
        userData data = new userData();
        General general = new General(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        RegistrationLocators locators = new RegistrationLocators();
        driver.get(url.REGISTRATION_URL);
        general.enterText(locators.firstName, data.getFirstName());
        general.enterText(locators.lastName, data.getLastName());
        general.enterText(locators.email, Constants.INVALID_EMAIL);
        general.enterText(locators.phone, "99" + data.getPhoneNumber());
        regPage.handleDatePicker();
        general.clickElement(locators.gender);
        general.waitForElementToBeClickable(locators.countryArrowBtn, 10);
        general.clickElement(locators.countryArrowBtn);
        regPage.countrySelection();
        general.enterText(locators.city, data.getCity());
        general.isDisabled(locators.nextStep);
        general.assertTextEquals(locators.invalidEmail, Constants.EMAIL_IS_NOT_VALID);
    }

    @Test
    public void registerWithInvalidPhoneNumber() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        ApiRequests requests = new ApiRequests(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        RegistrationLocators locators = new RegistrationLocators();
        driver.get(url.REGISTRATION_URL);
        requests.generateRandomEmailForTest();
        general.enterText(locators.firstName, data.getFirstName());
        general.enterText(locators.lastName, data.getLastName());
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.phone, "9922");
        regPage.handleDatePicker();
        general.clickElement(locators.gender);
        general.waitForElementToBeClickable(locators.countryArrowBtn, 10);
        general.clickElement(locators.countryArrowBtn);
        regPage.countrySelection();
        general.enterText(locators.city, data.getCity());
        general.isDisabled(locators.nextStep);
        general.assertTextEquals(locators.invalidPhone, Constants.PHONE_NUMBER_IS_NOT_VALID);
    }
}
