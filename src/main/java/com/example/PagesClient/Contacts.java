package com.example.PagesClient;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;
import com.example.Environment.BasePage;

public class Contacts extends BasePage {

    public Contacts(WebDriver driver) {

        super(driver);
    }

    public void ContactsOrderOpen() {

        System.out.println("Начинаем Contacts/ContactsOrderOpen");

        openUrl(ConfigManager.getProperty("URLContacts"));

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

        System.out.println("URLContacts: " + ConfigManager.getProperty("URLContacts"));

        // Переключаемся в нужный фрейм
        createWait(4).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("designer-client-frame")));

        System.out.println("Перешли в фрейм.");

        WebElement CreateContacts = waitAndGetVisibleElement(By.xpath(
                "//button[@title='Создать новую операцию.']"));
        // Клик для создания контакта

        // Кликаем по первой кнопке
        CreateContacts.click();

        System.out.println("Клик по кнопке создания контакта выполнен.");

        returnToMainContent();

    }

}
