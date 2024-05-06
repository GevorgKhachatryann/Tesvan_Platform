package methods;

import base.setup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class General extends setup {
    private final WebDriver driver;

    public General(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(By selector) {
        driver.findElement(selector).click();
    }
    public void enterText(By selector, String text) {
        WebElement element = driver.findElement(selector);
        element.clear();
        element.sendKeys(text);
    }

    public void assertElementPresent(By selector) {
        Assert.assertTrue(driver.findElement(selector).isDisplayed(), "Element is not present: " + selector);
    }

    public void assertTextEquals(By selector, String expectedText) {
        String actualText = driver.findElement(selector).getText();
        Assert.assertEquals(actualText, expectedText, "Text does not match: " + selector);
    }
    public void waitForElementToBeClickable(By selector, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public void waitForElementToBeVisible(By selector, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public void waitForElementToBeInvisible(By selector, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
    }

    public void waitForTextToBePresentInElement(By selector, String text, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.textToBe(selector, text));
    }

    public void scrollToElement(By selector) {
        WebElement element = driver.findElement(selector);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void hoverOverElement(By selector) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(selector);
        actions.moveToElement(element).perform();
    }

}
