package tests;

import base.Constants;
import base.setup;
import base.url;
import data.userData;
import locators.LoginLocators;
import locators.RegistrationLocators;
import locators.SettingsLocators;
import methods.ApiRequests;
import methods.General;
import org.testng.annotations.Test;

import javax.security.auth.login.LoginException;

import static pages.RegistrationPage.generateStrongPassword;

public class SettingsTests extends setup {
    @Test
    public void testDeleteAccountFromSettings() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators loginLoc = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        SettingsLocators locators = new SettingsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();

        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(loginLoc.email, data.getEmail());
        general.enterText(loginLoc.password, password);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(regLoc.hello, 15);
        general.assertTextEquals(regLoc.hello, Constants.HELLO_MESSAGE + data.getFirstName() + "!");
        general.waitForElementToBeVisible(locators.settingsIcon, 15);
        general.clickElement(locators.settingsIcon);
        general.clickElement(locators.deleteAccount);
        general.enterText(locators.currentPassword, password);
        general.clickElement(locators.deleteBtn);
        general.waitForElementToBeVisible(loginLoc.email, 10);
        general.enterText(loginLoc.email, data.getEmail());
        general.enterText(loginLoc.password, password);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(loginLoc.errorMessage, 10);
        general.assertTextEquals(loginLoc.errorMessage, Constants.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    public void testDeleteAccountFromSettingsWithWrongPass() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators loginLoc = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        SettingsLocators locators = new SettingsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();

        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(loginLoc.email, data.getEmail());
        general.enterText(loginLoc.password, password);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(regLoc.hello, 15);
        general.assertTextEquals(regLoc.hello, Constants.HELLO_MESSAGE + data.getFirstName() + "!");
        general.waitForElementToBeVisible(locators.settingsIcon, 15);
        general.clickElement(locators.settingsIcon);
        general.clickElement(locators.deleteAccount);
        general.enterText(locators.currentPassword, Constants.INVALID_PASSWORD);
        general.clickElement(locators.deleteBtn);
        general.waitForElementToBeVisible(locators.errorMessage, 15);
        general.assertTextEquals(locators.errorMessage, Constants.SOMETHING_WENT_WRONG);
        requests.deleteAccount(data.getPassword());
    }

    @Test
    public void testChangePasswordFromSettings() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators loginLoc = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        SettingsLocators locators = new SettingsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();

        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(loginLoc.email, data.getEmail());
        general.enterText(loginLoc.password, password);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(regLoc.hello, 15);
        general.assertTextEquals(regLoc.hello, Constants.HELLO_MESSAGE + data.getFirstName() + "!");
        general.waitForElementToBeVisible(locators.settingsIcon, 15);
        general.clickElement(locators.settingsIcon);
        general.clickElement(locators.changePassword);
        general.enterText(regLoc.password, Constants.VALID_PASSWORD);
        general.enterText(regLoc.confirmPassword, Constants.VALID_PASSWORD);
        general.clickElement(locators.changePasswordBtn);
        general.waitForElementToBeVisible(locators.changed, 15);
        general.assertTextEquals(locators.changed, Constants.SUCCESSFULLY_CHANGED);
        general.clickElement(loginLoc.logoutIcon);
        general.waitForElementToBeVisible(loginLoc.leaveBtn, 15);
        general.clickElement(loginLoc.leaveBtn);
        driver.get(url.LOGIN_URL);
        general.urlContainsPath(driver.getCurrentUrl(), url.LOGIN_URL);
        general.waitForElementToBeVisible(loginLoc.email, 15);
        general.enterText(loginLoc.email, data.getEmail());
        general.enterText(loginLoc.password, Constants.VALID_PASSWORD);
        general.clickElement(regLoc.loginBtn);
        requests.deleteAccount(Constants.VALID_PASSWORD);
    }

    @Test
    public void testChangePasswordFromSettingsWithWeakPassword() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators loginLoc = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        SettingsLocators locators = new SettingsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();

        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(loginLoc.email, data.getEmail());
        general.enterText(loginLoc.password, password);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(regLoc.hello, 15);
        general.assertTextEquals(regLoc.hello, Constants.HELLO_MESSAGE + data.getFirstName() + "!");
        general.waitForElementToBeVisible(locators.settingsIcon, 15);
        general.clickElement(locators.settingsIcon);
        general.clickElement(locators.changePassword);
        general.enterText(regLoc.password, Constants.INVALID_PASSWORD);
        general.enterText(regLoc.confirmPassword, Constants.INVALID_PASSWORD);
        general.isDisabled(locators.changePasswordBtn);
        general.assertTextEquals(regLoc.invalidPassword, Constants.INVALID_PASSWORD_MESSAGE);
        requests.deleteAccount(password);
    }

    @Test
    public void testChangePasswordWithMismatchedPasswords() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators loginLoc = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        SettingsLocators locators = new SettingsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();

        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(loginLoc.email, data.getEmail());
        general.enterText(loginLoc.password, password);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(regLoc.hello, 15);
        general.assertTextEquals(regLoc.hello, Constants.HELLO_MESSAGE + data.getFirstName() + "!");
        general.scrollToElement(locators.settingsIcon);
        general.waitForElementToBeVisible(locators.settingsIcon, 15);
        general.clickElement(locators.settingsIcon);
        general.clickElement(locators.changePassword);
        general.enterText(regLoc.password, Constants.VALID_PASSWORD);
        general.enterText(regLoc.confirmPassword, Constants.INVALID_PASSWORD);
        general.isDisabled(locators.changePasswordBtn);
        general.assertTextEquals(regLoc.passwordDoesntMatch, Constants.PASSWORD_DOESNT_MATCH);
        requests.deleteAccount(Constants.VALID_PASSWORD);
    }
}
