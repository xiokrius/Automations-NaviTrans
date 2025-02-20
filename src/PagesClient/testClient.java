package PagesClient;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class testClient {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public testClient(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void switchToIframe() {

    }

}
