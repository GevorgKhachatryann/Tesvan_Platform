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
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.security.auth.login.LoginException;

import java.util.ArrayList;

import static pages.RegistrationPage.generateStrongPassword;


public class LoginTests extends setup {

    @Test
    public void testLoginWithValidCredentials() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators locators = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        SettingsLocators settingsLocators = new SettingsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();
        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.password, password);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(regLoc.hello, 15);
        general.assertTextEquals(regLoc.hello, Constants.HELLO_MESSAGE + data.getFirstName() + "!");
        general.waitForElementToBeVisible(settingsLocators.settingsIcon, 15);
        general.clickElement(settingsLocators.settingsIcon);
        String actualText = driver.findElement(locators.email).getAttribute("value");
        Assert.assertEquals(actualText, data.getEmail());
    }

    @Test
    public void testInvalidEmailValidPassword() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators locators = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(locators.email, Constants.INVALID_EMAIL);
        general.enterText(locators.password, password);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(locators.errorMessage, 10);
        general.assertTextEquals(locators.errorMessage, Constants.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    public void testValidEmailInvalidPassword() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators locators = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.password, Constants.INVALID_PASSWORD);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(locators.errorMessage, 10);
        general.assertTextEquals(locators.errorMessage, Constants.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    public void testWithEmptyEmail() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators locators = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(locators.email, "");
        general.enterText(locators.password, password);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(locators.errorMessage, 15);
        general.assertTextEquals(locators.errorMessage, Constants.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    public void testWithEmptyPassword() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators locators = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.password, "");
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(locators.errorMessage, 15);
        general.assertTextEquals(locators.errorMessage, Constants.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    public void testLogoutFunctionality() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators locators = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        SettingsLocators settingsLocators = new SettingsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();
        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.password, password);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(regLoc.hello, 15);
        general.assertTextEquals(regLoc.hello, Constants.HELLO_MESSAGE + data.getFirstName() + "!");
        general.waitForElementToBeVisible(settingsLocators.settingsIcon, 15);
        general.clickElement(settingsLocators.settingsIcon);
        String actualText = driver.findElement(locators.email).getAttribute("value");
        Assert.assertEquals(actualText, data.getEmail());
        general.clickElement(locators.logoutIcon);
        general.waitForElementToBeVisible(locators.leaveBtn, 10);
        general.clickElement(locators.leaveBtn);
        general.urlContainsPath(driver.getCurrentUrl(), url.LOGIN_URL);
    }

    @Test
    public void testCloseBtnFunctionalityInLogoutProcess() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators locators = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        SettingsLocators settingsLocators = new SettingsLocators();
        RegistrationLocators regLoc = new RegistrationLocators();
        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.password, password);
        general.clickElement(regLoc.loginBtn);
        general.waitForElementToBeVisible(regLoc.hello, 15);
        general.assertTextEquals(regLoc.hello, Constants.HELLO_MESSAGE + data.getFirstName() + "!");
        general.waitForElementToBeVisible(settingsLocators.settingsIcon, 15);
        general.clickElement(settingsLocators.settingsIcon);
        String actualText = driver.findElement(locators.email).getAttribute("value");
        Assert.assertEquals(actualText, data.getEmail());
        general.clickElement(locators.logoutIcon);
        general.waitForElementToBeVisible(locators.closeBtn, 10);
        general.clickElement(locators.closeBtn);
        general.urlDoesNotContainPath(driver.getCurrentUrl(), url.LOGIN_URL);
    }

    @Test
    public void testRememberMeFunctionality() throws LoginException {
        userData data = new userData();
        General general = new General(driver);
        LoginLocators locators = new LoginLocators();
        ApiRequests requests = new ApiRequests(driver);
        RegistrationLocators regLoc = new RegistrationLocators();

        driver.get(url.LOGIN_URL);
        requests.generateRandomEmailForTest();
        String password = generateStrongPassword();
        requests.createUser(data.getEmail(), password);
        System.out.println(password);
        general.enterText(locators.email, data.getEmail());
        general.enterText(locators.password, password);
        general.clickElement(locators.rememberMe);
        general.clickElement(regLoc.loginBtn);
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(url.LOGIN_URL);
        general.waitForElementToBeVisible(regLoc.hello, 10);
        general.assertTextEquals(regLoc.hello, Constants.HELLO_MESSAGE + data.getFirstName() + "!");
    }
}
