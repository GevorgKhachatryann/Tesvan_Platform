package locators;

import org.openqa.selenium.By;

public class LoginLocators {
    public By email = By.cssSelector("[placeholder=\"johnsmith@example.com\"]");
    public By password = By.cssSelector("[placeholder=\"Enter your password\"]");
    public By errorMessage = By.xpath("//p[contains(text(),'Invalid email or password')]");
    public By logoutIcon = By.xpath("//p[contains(text(),'Log out')]");
    public By leaveBtn = By.xpath("//button[contains(text(),'Leave')]");
    public By closeBtn = By.xpath("//button[contains(text(),'Close')]");
    public By rememberMe = By.cssSelector("input[type=\"checkbox\"]");

}
