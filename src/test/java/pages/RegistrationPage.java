package pages;

import base.setup;
import data.userData;
import locators.RegistrationLocators;
import methods.General;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends setup {
    private WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    public void register(String email){
        userData data = new userData();
        General general = new General(driver);
        RegistrationLocators locators = new RegistrationLocators();
        general.enterText(locators.firstName, data.getFirstName());
    }

}
