package com.example.Environment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;


public class SettingUser extends BasePage{



         private WebDriver driver;
        private WebDriverWait wait;
        private JavascriptExecutor js;

    public SettingUser(WebDriver driver) {

        super(driver);

    }

    public void SettingsOfUser() {

        driver.get("normally");

        switchToIframe();

        WebElement SearchUser = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//div[contains(@class, 'ms-SearchBox root')]")));

        SearchUser.click();

        WebElement SearchUserInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//input[@aria-label='Поиск Настройки пользователей']")));


        WebElement readyUser = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Изменить список']")));

        readyUser.click();

    }

}
