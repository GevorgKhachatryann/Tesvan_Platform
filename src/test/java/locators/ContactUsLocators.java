package locators;

import org.openqa.selenium.By;

public class ContactUsLocators {
    public By messageField = By.cssSelector("[placeholder=\"Type your message ...\"]");
    public By sendMessageBtn = By.xpath("//button[contains(text(),'Send message')]");
    public By sentMessage = By.xpath("//p[contains(text(),'Your message has successfully been sent.')]");
    public By invalidFirstName = By.xpath("//p[contains(text(),'First name is not valid')]");
    public By invalidLastName = By.xpath("//p[contains(text(),'Last name is not valid')]");

}
