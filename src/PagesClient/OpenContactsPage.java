package PagesClient;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

public class OpenContactsPage {

    private String NameContactsValue = ConfigManager.getProperty("NameContactsValue");

    private final WebDriver driver;
    private final WebDriverWait wait;
    private JavascriptExecutor js;

    public OpenContactsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    private void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
    }

    public void OpenContacts() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        switchToIframe();
        System.out.println("Перешли в фрейм.");

        WebElement NameContacts = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@controlname, 'NameTRSL')]//descendant::input[contains(@id, 'ee')]")));
        System.out.println("Нашли поле Название контакта");

        NameContacts.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // controlname="NameTRSL" and id = ee

        js.executeScript("arguments[0].value = arguments[1];", NameContacts, NameContactsValue);
        System.out.println("Заполнили стартовую дату через JavaScript: " + NameContactsValue);

    }
}
