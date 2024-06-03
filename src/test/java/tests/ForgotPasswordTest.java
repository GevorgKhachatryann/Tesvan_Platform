package tests;

import base.Constants;
import base.setup;
import base.url;
import data.userData;
import locators.ForgetPasswordLocators;
import locators.RegistrationLocators;
import methods.ApiRequests;
import methods.General;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import javax.security.auth.login.LoginException;

import java.io.IOException;

import static pages.RegistrationPage.generateStrongPassword;

public class ForgotPasswordTest extends setup {

    @Test
    public void testForgotPassword() throws LoginException, IOException {
        userData data = new userData();
        General general = new General(driver);
        ApiRequests requests = new ApiRequests(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        ForgetPasswordLocators locators = new ForgetPasswordLocators();

        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        driver.get(url.LOGIN_URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('language', 'en');");
        driver.navigate().refresh();
        general.clickElement(locators.forgotPassword);
        general.enterText(locators.emailField, data.getEmail());
        general.clickElement(locators.sendBtn);
        requests.retrieveForgetPasswordEmail();
        String verificationLink = requests.extractForgetPasswordLink(data.getRegistrationMail());
        System.out.println(verificationLink);
        driver.get(verificationLink);
        String newPassword = generateStrongPassword();
        general.enterText(regLoc.password, newPassword);
        general.enterText(regLoc.confirmPassword, newPassword);
        System.out.println(newPassword);
        general.clickElement(locators.resetPassword);
        general.assertTextEquals(locators.passwordChanged, Constants.YOUR_PASSWORD_HAS_BEEN_SUCCESSFULLY_CHANGED);
        general.clickElement(locators.backToLogin);
        general.enterText(regLoc.email, data.getEmail());
        general.enterText(regLoc.enterPassword, newPassword);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeClickable(regLoc.hello, 10);
        general.assertTextEquals(regLoc.hello, Constants.HELLO_MESSAGE + data.getFirstName() + "!");
        requests.deleteAccount(newPassword);
    }

    @Test
    public void testForgotPasswordWithInvalidEmailFormat() {
        General general = new General(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        ForgetPasswordLocators locators = new ForgetPasswordLocators();
        driver.get(url.LOGIN_URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('language', 'en');");
        driver.navigate().refresh();
        general.clickElement(locators.forgotPassword);
        general.enterText(locators.emailField, Constants.INVALID_EMAIL);
        general.clickElement(locators.sendBtn);
        general.assertTextEquals(regLoc.invalidEmail, Constants.EMAIL_IS_NOT_VALID);
    }

    @Test
    public void testNoVerifiedUserErrorMessage() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        ApiRequests requests = new ApiRequests(driver);
        ForgetPasswordLocators locators = new ForgetPasswordLocators();
        driver.get(url.LOGIN_URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('language', 'en');");
        driver.navigate().refresh();
        requests.generateRandomEmailForTest();
        general.clickElement(locators.forgotPassword);
        general.enterText(locators.emailField, data.getEmail());
        general.clickElement(locators.sendBtn);
        general.waitForElementToBeVisible(locators.noVerifiedUser, 10);
        general.assertTextEquals(locators.noVerifiedUser, Constants.THERE_IS_NO_VERIFIED_USER);
    }

    @Test
    public void testForgotPasswordWithWeakPassword() throws LoginException, IOException {
        userData data = new userData();
        General general = new General(driver);
        ApiRequests requests = new ApiRequests(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        ForgetPasswordLocators locators = new ForgetPasswordLocators();

        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        driver.get(url.LOGIN_URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('language', 'en');");
        driver.navigate().refresh();
        general.clickElement(locators.forgotPassword);
        general.enterText(locators.emailField, data.getEmail());
        general.clickElement(locators.sendBtn);
        requests.retrieveForgetPasswordEmail();
        String verificationLink = requests.extractForgetPasswordLink(data.getRegistrationMail());
        System.out.println(verificationLink);
        driver.get(verificationLink);
        general.enterText(regLoc.password, Constants.INVALID_PASSWORD);
        general.enterText(regLoc.confirmPassword, Constants.INVALID_PASSWORD);
        general.assertTextEquals(regLoc.invalidPassword, Constants.INVALID_PASSWORD_MESSAGE);
        requests.deleteAccount(password);
    }

    @Test
    public void testForgotPasswordWithMismatchedPasswords() throws LoginException, IOException {
        userData data = new userData();
        General general = new General(driver);
        ApiRequests requests = new ApiRequests(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        ForgetPasswordLocators locators = new ForgetPasswordLocators();

        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        driver.get(url.LOGIN_URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('language', 'en');");
        driver.navigate().refresh();
        general.clickElement(locators.forgotPassword);
        general.enterText(locators.emailField, data.getEmail());
        general.clickElement(locators.sendBtn);
        requests.retrieveForgetPasswordEmail();
        String verificationLink = requests.extractForgetPasswordLink(data.getRegistrationMail());
        System.out.println(verificationLink);
        driver.get(verificationLink);
        general.enterText(regLoc.password, password);
        general.enterText(regLoc.confirmPassword, Constants.INVALID_PASSWORD);
        general.clickElement(locators.resetPassword);
        general.waitForElementToBeVisible(regLoc.passwordDoesntMatch, 15);
        general.assertTextEquals(regLoc.passwordDoesntMatch, Constants.PASSWORD_DOESNT_MATCH);
        requests.deleteAccount(password);
    }
}
