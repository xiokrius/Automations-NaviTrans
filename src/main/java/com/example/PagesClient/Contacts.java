package com.example.PagesClient;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Contacts {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public Contacts(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Метод для переключения в iframe
    private void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
    }

    public void ContactsOrderOpen() {

        System.out.println("Начинаем Contacts/ContactsOrderOpen");

        // Переключаемся в нужный фрейм
        switchToIframe();
        System.out.println("Перешли в фрейм.");

        // Клик для создания контакта
        WebElement CreateContacts = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@title='Создать новую операцию.']")));

        // Кликаем по первой кнопке
        CreateContacts.click();
        System.out.println("Клик по кнопке создания контакта выполнен.");

    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("ласт вышел с фрейма, проверка");
    }

}
