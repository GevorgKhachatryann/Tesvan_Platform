package locators;

import org.openqa.selenium.By;

public class ForgetPasswordLocators {
    public By forgotPassword = By.xpath("//a[contains(text(),'Forgot Password')]");
    public By emailField = By.cssSelector("[placeholder=\"johnsmith@example.com\"]");
    public By sendBtn = By.xpath("//button[contains(text(),'Send')]");
    public By resetPassword = By.xpath("//button[contains(text(),'Reset Password')]");
    public By passwordChanged = By.xpath("//p[contains(text(),'Your password has been successfully changed.')]");
    public By backToLogin = By.xpath("//button[contains(text(),'Back to log in')]");
    public By noVerifiedUser = By.xpath("//p[contains(text(),'There is not verified user')]");
}
