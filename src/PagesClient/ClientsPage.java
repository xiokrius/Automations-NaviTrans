package PagesClient;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClientsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ClientsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    private void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
    }

    public void ClientsOrderForm() {

    }
}
