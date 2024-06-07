package locators;

import org.openqa.selenium.By;

public class RegistrationLocators {
    public By firstName = By.cssSelector("[placeholder=\"John\"]");
    public By lastName = By.cssSelector("[placeholder=\"Smith\"]");
    public By email = By.cssSelector("[placeholder=\"johnsmith@example.com\"]");
    public By phone = By.className("PhoneInputInput");
    public By datePicker = By.id("datePicker");
    public By arrowDropDown = By.xpath("/html/body/div[2]/div[2]/div/div/div/div[1]/div[1]/button");
    public By year = By.xpath("//button[contains(text(), '2000')]");
    public By month = By.xpath("//button[contains(text(), 'Jan')]");
    public By day = By.xpath("//button[text()=6]");
    public By gender = By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[3]/form/div[1]/div[6]/label/div/div/label/span");
    public By countryArrowBtn = By.cssSelector("[name=\"country\"]");
    public By city = By.cssSelector("[placeholder=\"Where do you live?\"]");
    public By nextStep = By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[3]/form/div[2]/button");
    public By englishArrowBtn = By.cssSelector("[placeholder=\"Choose your english level\"]");
    public By level = By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[3]/form/div/div[1]/div[1]/label/div[2]/div/p");
    public By study = By.cssSelector("[placeholder=\"Where do you study?\"]");
    public By backgroundQA = By.xpath("//form/div/div[1]/div[3]/label/div/div[1]/label/span");
    public By next = By.xpath("//button[2]");
    public By password = By.cssSelector("[placeholder=\"Create a strong password\"]");
    public By confirmPassword = By.cssSelector("[placeholder=\"Please re-enter your password\"]");
    public By terms = By.cssSelector("[type=\"checkbox\"]");
    public By submit = By.cssSelector("[type=\"submit\"]");
    public By successMessage = By.xpath("//p[contains(text(), 'successfully')]");
    public By loginBtn = By.xpath("//button[contains(text(), 'Log in')]");
    public By enterPassword = By.cssSelector("[placeholder=\"Enter your password\"]");
    public By hello = By.xpath("//p[contains(text(), 'Hello')]");
    public By invalidEmail = By.xpath("//p[contains(text(), 'Email is not valid')]");
    public By invalidPhone = By.xpath("//p[contains(text(), 'Phone number is not valid')]");
    public By existingEmail = By.xpath("//p[contains(text(), 'User with this email already exists')]");
    public By invalidPassword = By.xpath("//p[contains(text(),'Password is not valid')]");
    public By passwordDoesntMatch = By.xpath("//p[contains(text(),\"Password doesn't match\")]");
    public By invalidBirthDate = By.xpath("//p[contains(text(),'Birth date is not valid')]");
    public By dateField = By.cssSelector("input[placeholder='DD/MM/YYYY']");
    public By changeEmail = By.xpath("//a[@href='/changeEmail']");
    public By changeEmailBtn = By.xpath("//button[contains(text(), 'Change email address')]");
    public By armLang = By.xpath("//p[contains(text(),'Arm')]");
    public By eng = By.xpath("//p[contains(text(),'Eng')]");
    public By resendLink = By.xpath("//button[contains(text(), 'Resend link')]");
    public By invalidLink = By.xpath("//p[contains(text(), concat('We', \"'\", 're sorry, but it looks like this verification link has already been used.'))]");


}
