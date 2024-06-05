package locators;

import org.openqa.selenium.By;

public class SettingsLocators {
    public By settingsIcon = By.xpath("//a[contains(text(),'Settings')]");
    public By deleteAccount = By.xpath("//a[contains(text(),'Delete account')]");
    public By currentPassword = By.cssSelector("[placeholder=\"Please enter your password\"]");
    public By deleteBtn = By.xpath("//button[contains(text(),'Delete account')]");
    public By errorMessage = By.xpath("//p[contains(text(),'Something went wrong. Please try again!')]");
    public By changePassword = By.xpath("//a[contains(text(),'Change password')]");
    public By changePasswordBtn = By.xpath("//button[contains(text(),'Change password')]");
    public By changed = By.xpath("//p[contains(text(),'Your password is successfully changed.')]");

}
