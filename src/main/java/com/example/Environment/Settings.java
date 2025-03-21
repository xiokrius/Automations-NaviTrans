package com.example.Environment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;
import com.example.PagesOrder.FrameSwitcher;

public class Settings {

    private String PasswordOldValue = ConfigManager.getProperty("PasswordOld");
    private String EmailValue = ConfigManager.getProperty("EmailValue");

    private WebDriver driver;
    private WebDriverWait wait;
    private FrameSwitcher frameSwitcher;
    private JavascriptExecutor js;

    public Settings(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.frameSwitcher = new FrameSwitcher(driver);
        this.js = (JavascriptExecutor) driver;
    }

    private void setInputValue(WebElement element, String value) {
        js.executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                element, value);
        System.out.println("Заполнили значение: " + value);
    }

    public void goToSettings() {

        driver.get(
                "http://192.168.1.13:8080/BC210-TEST/?company=Trans_Solutions_CZ&page=9807&dc=0&bookmark=24%3beJQ1dwCReeYyo70A4kWn3vFF3%2bIEOQ%3d%3d");

        frameSwitcher.switchToIframe();

        WebElement pass3 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(
                        "//div[@controlname='Authentication Email']/following::input[contains(@id, 'ee')]")));

        // Адрес электронной почты для проверки подлинности

        WebElement Email = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@aria-label='Просмотреть или обновить значение для Пароль']")));

        Email.click();

        setInputValue(Email, EmailValue);

        try {

            WebElement WindowPass = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                    "//div[@title='Правка - Введите пароль']")));

            WebElement PasswordOld = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "//a[contains(text(), 'Пароль')]/following::input[contains(@id, 'ee')]")));

            setInputValue(PasswordOld, PasswordOldValue);

        } catch (Exception e) {

            System.out.println("Ошибка при взаимодействии с элементом");

        }

        WebElement PasswordOld2 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(
                        "//div[@controlname='ConfirmPassword']/following::input[contains(@id, 'ee')]")));

        setInputValue(PasswordOld2, PasswordOldValue);
        // div[@controlname='Пароль']

        WebElement buttonInOK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//button//span[text()='ОК']")));

        buttonInOK.click();

    }

}
