package com.example.Environment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;
import com.example.PagesOrder.FrameSwitcher;

public class SettingUser {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private FrameSwitcher frameSwitcher;
    private String normally = ConfigManager.getProperty("normally");
    private String test = ConfigManager.getProperty("test");
    private Settings setInputValue;

    public SettingUser(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        this.frameSwitcher = new FrameSwitcher(driver);
        this.setInputValue = new Settings(driver);
    }

    public void SettingsOfUser() {

        driver.get(normally);

        frameSwitcher.switchToIframe();

        WebElement SearchUser = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//div[contains(@class, 'ms-SearchBox root')]")));

        SearchUser.click();

        WebElement SearchUserInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//input[@aria-label='Поиск Настройки пользователей']")));

        setInputValue.setInputValue(SearchUserInput, test);

        WebElement readyUser = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Изменить список']")));

        readyUser.click();

    }

}
