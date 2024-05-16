package tests;

import base.Constants;
import base.setup;
import base.url;
import data.userData;
import locators.RegistrationLocators;
import methods.ApiRequests;
import methods.General;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import pages.Retry;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class RegistrationTest extends setup {
    @Test(retryAnalyzer = Retry.class)
    public void testRegistration() throws LoginException, IOException {
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

    @Test(retryAnalyzer = Retry.class)
    public void testRegisterWithInvalidEmail() {
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

    @Test(retryAnalyzer = Retry.class)
    public void testRegisterWithInvalidPhoneNumber() throws LoginException {
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

    @Test(retryAnalyzer = Retry.class)
    public void testRegisterWithExistingEmail() throws LoginException, IOException {
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
        driver.get(url.REGISTRATION_URL);
        general.enterText(locators.firstName, data.getFirstName());
        general.enterText(locators.lastName, data.getLastName());
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.phone, "99" + data.getPhoneNumber());
        regPage.handleDatePicker();
        general.clickElement(locators.gender);
        general.waitForElementToBeClickable(locators.countryArrowBtn, 10);
        general.clickElement(locators.countryArrowBtn);
        regPage.countrySelection();
        general.enterText(locators.city, data.getCity());
        general.clickElement(locators.nextStep);
        general.isDisabled(locators.nextStep);
        general.assertTextEquals(locators.existingEmail, Constants.THIS_EMAIL_IS_ALREADY_USED);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testRegisterWithWeakPassword() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        ApiRequests requests = new ApiRequests(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        RegistrationLocators locators = new RegistrationLocators();
        requests.generateRandomEmailForTest();
        driver.get(url.REGISTRATION_URL);
        general.enterText(locators.firstName, data.getFirstName());
        general.enterText(locators.lastName, data.getLastName());
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.phone, "99" + data.getPhoneNumber());
        regPage.handleDatePicker();
        general.clickElement(locators.gender);
        general.waitForElementToBeClickable(locators.countryArrowBtn, 10);
        general.clickElement(locators.countryArrowBtn);
        regPage.countrySelection();
        general.enterText(locators.city, data.getCity());
        general.clickElement(locators.nextStep);
        general.waitForElementToBeClickable(locators.englishArrowBtn, 20);
        general.clickElement(locators.englishArrowBtn);
        regPage.englishLevelSelection();
        general.enterText(locators.study, data.getUniversity());
        general.clickElement(locators.backgroundQA);
        general.clickElement(locators.next);
        general.enterText(locators.password, Constants.INVALID_PASSWORD);
        general.enterText(locators.confirmPassword, Constants.INVALID_PASSWORD);
        general.clickElement(locators.terms);
        general.assertTextEquals(locators.invalidPassword, Constants.INVALID_PASSWORD_MESSAGE);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testRegisterWithMismatchedPasswords() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        ApiRequests requests = new ApiRequests(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        RegistrationLocators locators = new RegistrationLocators();
        requests.generateRandomEmailForTest();
        driver.get(url.REGISTRATION_URL);
        general.enterText(locators.firstName, data.getFirstName());
        general.enterText(locators.lastName, data.getLastName());
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.phone, "99" + data.getPhoneNumber());
        regPage.handleDatePicker();
        general.clickElement(locators.gender);
        general.waitForElementToBeClickable(locators.countryArrowBtn, 10);
        general.clickElement(locators.countryArrowBtn);
        regPage.countrySelection();
        general.enterText(locators.city, data.getCity());
        general.clickElement(locators.nextStep);
        general.waitForElementToBeClickable(locators.englishArrowBtn, 20);
        general.clickElement(locators.englishArrowBtn);
        regPage.englishLevelSelection();
        general.enterText(locators.study, data.getUniversity());
        general.clickElement(locators.backgroundQA);
        general.clickElement(locators.next);
        general.enterText(locators.password, Constants.VALID_PASSWORD);
        general.enterText(locators.confirmPassword, Constants.INVALID_PASSWORD);
        general.clickElement(locators.terms);
        general.assertTextEquals(locators.passwordDoesntMatch, Constants.PASSWORD_DOESNT_MATCH);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testRegisterWithInvalidBirthDate() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        Actions actions = new Actions(driver);
        ApiRequests requests = new ApiRequests(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        RegistrationLocators locators = new RegistrationLocators();
        requests.generateRandomEmailForTest();
        driver.get(url.REGISTRATION_URL);
        general.enterText(locators.firstName, data.getFirstName());
        general.enterText(locators.lastName, data.getLastName());
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.phone, "99" + data.getPhoneNumber());
        actions.sendKeys(driver.findElement(locators.dateField), Constants.INVALID_DATE).perform();
        general.clickElement(locators.gender);
        general.waitForElementToBeClickable(locators.countryArrowBtn, 10);
        general.clickElement(locators.countryArrowBtn);
        regPage.countrySelection();
        general.enterText(locators.city, data.getCity());
        general.isDisabled(locators.nextStep);
        general.assertTextEquals(locators.invalidBirthDate, Constants.BIRTH_DATE_IS_NOT_VALID);
    }
}
