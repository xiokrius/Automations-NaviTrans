package com.example.PagesInventory;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.PagesOrder.FrameSwitcher;

import io.qameta.allure.Step;

public class VehicleTrailerDriver {

    WebDriver driver;
    WebDriverWait wait;
    FrameSwitcher frameSwitcher;

    public VehicleTrailerDriver(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.frameSwitcher = new FrameSwitcher(driver);
    }

    @Step("Проверка перевода для элемента: {expectedText}")
    public void checkTranslation(By locator, String expectedText) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        String actualText = element.getText();

        if (actualText.equals(expectedText)) {
            System.out.println("Текст корректен: " + actualText);
        } else {
            System.out.println("Текст некорректен. Ожидалось: " + expectedText + ", но получено: " + actualText);
        }
    }

    public void PageTrailerDriver() {

        frameSwitcher.returnToMainContent();

        checkTranslation(By.xpath("//a[text()='Изменить ТС']"), "Изменить ТС");
        checkTranslation(By.xpath("//a[text()=' Код ТС']"), " Код ТС");
        checkTranslation(By.xpath("//a[text()=' Гос. номер ТС']"), " Гос. номер ТС");
        checkTranslation(By.xpath("//a[text()=' Дата начала']"), " Дата начала");
        checkTranslation(By.xpath("//a[text()=' Дата окончания']"), " Дата окончания");
        checkTranslation(By.xpath("//a[text()='Водитель получивший ТМЦ']"), "Водитель получивший ТМЦ");

        frameSwitcher.returnToMainContent();
    }

}
