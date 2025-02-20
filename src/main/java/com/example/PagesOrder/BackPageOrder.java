package com.example.PagesOrder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BackPageOrder {

    private final WebDriver driver;
    private FrameSwitcher frameSwitcher;

    public BackPageOrder(WebDriver driver) {
        this.driver = driver;
        this.frameSwitcher = new FrameSwitcher(driver);
    }

    public void BackPage() {

        System.out.println("Начинаем BackPageOrder/BackPage");

        // Переключаемся в нужный фрейм
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        frameSwitcher.switchToIframe();
        System.out.println("Перешли в фрейм.");

        WebElement BackPage = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@data-is-focusable, 'true') and contains(@title, 'Назад')]")));
        System.out.println("Нашли кнопку назад.");

        // Отладка +
        System.out.println("Кнопка видима: " + BackPage.isDisplayed());
        System.out.println("Кнопка доступна для клика: " + BackPage.isEnabled());

        // Кликаем по первой кнопке
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", BackPage);
        System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

        System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

        frameSwitcher.returnToMainContent();
    }

}
