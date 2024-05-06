package tests;
import base.setup;
import base.url;
import data.userData;
import methods.ApiRequests;
import org.testng.annotations.Test;
import pages.RegistrationPage;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class RegistrationTest extends setup {
    @Test
    public void registration() throws LoginException {
        userData data = new userData();
        ApiRequests requests = new ApiRequests(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        String generatedEmail = requests.generateRandomEmailForTest();
        driver.get(url.REGISTRATION_URL);
        System.out.println(generatedEmail);
//        regPage.register(data.getEmail());
    }
}
