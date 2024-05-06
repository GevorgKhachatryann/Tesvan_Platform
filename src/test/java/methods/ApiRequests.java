package methods;

import data.userData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import me.shivzee.util.JMailBuilder;

import me.shivzee.JMailTM;

import javax.security.auth.login.LoginException;

public class ApiRequests {
    public static WebDriverWait wait;

    public ApiRequests(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }
    public void postRequest(String endpoint){
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        int statusCode = response.statusCode();
        String responseBody = response.body();
        HttpHeaders headers = response.headers();

        System.out.println("Status code: " + statusCode);
        System.out.println("Response body: " + responseBody);
        System.out.println("Response headers: " + headers);
    }



    public String generateRandomEmailForTest() throws LoginException {
        userData data = new userData();
        JMailTM mailer = JMailBuilder.createDefault("randomPassword");
        mailer.init();
        String generatedEmail = mailer.getSelf().getEmail();
        System.out.println(generatedEmail);
        data.setEmail(generatedEmail);

        return generatedEmail;
    }

}
