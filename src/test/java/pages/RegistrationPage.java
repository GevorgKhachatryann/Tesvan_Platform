package pages;

import base.setup;
import com.github.javafaker.Faker;
import data.userData;
import locators.RegistrationLocators;
import methods.General;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static base.setup.driver;

public class RegistrationPage extends setup {
    private final WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    public void register(String email){
        userData data = new userData();
        General general = new General(driver);
        RegistrationLocators locators = new RegistrationLocators();
        general.enterText(locators.firstName, data.getFirstName());
        general.enterText(locators.lastName, data.getLastName());
        general.enterText(locators.email, email);
        general.enterText(locators.phone, "99"+ data.getPhoneNumber());
        handleDatePicker();
        general.clickElement(locators.gender);
        general.waitForElementToBeClickable(locators.countryArrowBtn, 10);
        general.clickElement(locators.countryArrowBtn);
        countrySelection();
        general.enterText(locators.city, data.getCity());
        general.clickElement(locators.nextStep);
        general.waitForElementToBeClickable(locators.englishArrowBtn,20);
        general.clickElement(locators.englishArrowBtn);
        englishLevelSelection();
        general.enterText(locators.study, data.getUniversity());
        general.clickElement(locators.backgroundQA);
        general.clickElement(locators.next);
        String password = generateStrongPassword();
        general.enterText(locators.password, password);
        general.enterText(locators.confirmPassword, password);
        data.setPassword(password);
        general.clickElement(locators.terms);
        general.clickElement(locators.submit);
    }

    public void handleDatePicker() {
        General general = new General(driver);
        RegistrationLocators locators = new RegistrationLocators();
//        general.waitForElementToBeVisible(locators.datePicker,25);
        WebElement datePicker = driver.findElement(By.id("datePicker"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", datePicker);
        executor.executeScript("arguments[0].click();", datePicker);

//        general.clickElement(locators.datePicker);
//        general.waitForElementToBeClickable(locators.arrowDropDown,25);
//        WebElement arrow = driver.findElement(locators.arrowDropDown);
//
//        new Actions(driver)
//                .click(arrow)
//                .perform();
//        general.clickElement(locators.arrowDropDown);
        // Choose a random year
//        general.waitForElementToBeClickable(locators.year,10);
//        List<WebElement> yearOptions = driver.findElements(locators.year);
//        if (!yearOptions.isEmpty()) {
//            int randomYearIndex = new Random().nextInt(yearOptions.size());
//            WebElement randomYearButton = yearOptions.get(randomYearIndex);
//            randomYearButton.click();
//        }
//        general.waitForElementToBeClickable(locators.month,10);
        // Choose a random month
//        List<WebElement> monthOptions = driver.findElements(locators.month);
//        if (!monthOptions.isEmpty()) {
//            int randomMonthIndex = new Random().nextInt(monthOptions.size());
//            WebElement randomMonthButton = monthOptions.get(randomMonthIndex);
//            randomMonthButton.click();
//        }
//        general.waitForElementToBeClickable(locators.day,20);

        // Choose a random day
//        List<WebElement> dayOptions = driver.findElements(locators.day);
//        if (!dayOptions.isEmpty()) {
//            int randomDayIndex = new Random().nextInt(dayOptions.size());
//            WebElement randomDayButton = dayOptions.get(randomDayIndex);
//            randomDayButton.click();
//        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

//        WebElement day = driver.findElement(By.cssSelector("button:nth-child(5)"));
//        new Actions(driver)
//                .click(day)
//                .perform();
        JavascriptExecutor executor2 = (JavascriptExecutor) driver;
        executor2.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(" div > div:nth-child(1) > button:nth-child(2)[aria-selected=\"false\"]")));
//        general.clickElement(locators.datePicker);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


    }

    public void countrySelection() {
        General general = new General(driver);
        general.waitForElementToBeClickable(By.xpath("//*[@id='root']/div/main/div/div[2]/div/div[3]/form/div[1]/div[7]/label/div[2]/div/p"),15);
        // Find all the country options within the dropdown
        List<WebElement> countryOptions = driver.findElements(By.xpath("//*[@id='root']/div/main/div/div[2]/div/div[3]/form/div[1]/div[7]/label/div[2]/div/p"));

        // Choose a random country from the options
        int randomIndex = new Random().nextInt(countryOptions.size());
        WebElement randomCountryOption = countryOptions.get(randomIndex);

        // Click on the chosen country to select it
        randomCountryOption.click();
    }

    public void englishLevelSelection() {
        RegistrationLocators locators = new RegistrationLocators();
        List<WebElement> countryOptions = driver.findElements(locators.level);
        int randomIndex = new Random().nextInt(countryOptions.size());
        WebElement randomCountryOption = countryOptions.get(randomIndex);
        randomCountryOption.click();
    }

    public static String generateStrongPassword() {
        Faker faker = new Faker();
        StringBuilder password = new StringBuilder();

        // Generate at least one character of each type
        password.append(faker.regexify("[A-Z]")); // Uppercase letter
        password.append(faker.regexify("[a-z]")); // Lowercase letter
        password.append(faker.regexify("[0-9]")); // Digit
        password.append(faker.regexify("[!@#$%^&*()-_=+{}|;:,.<>?]")); // Special character

        // Generate remaining characters to reach a total length of 10
        while (password.length() < 10) {
            password.append(faker.regexify("[A-Za-z0-9!@#$%^&*()-_=+{}|;:,.<>?]"));
        }

        // Shuffle the characters to ensure randomness
        List<Character> characters = password.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        Collections.shuffle(characters);
        StringBuilder shuffledPassword = new StringBuilder();
        characters.forEach(shuffledPassword::append);

        return shuffledPassword.toString();
    }

}
