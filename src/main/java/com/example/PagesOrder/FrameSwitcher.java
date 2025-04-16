package com.example.PagesOrder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameSwitcher {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public FrameSwitcher(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Инициализация WebDriverWait
        this.js = (JavascriptExecutor) driver;
    }

    public void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в фрейм.");
    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("Возвращаемся в основной контент страницы.");
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}