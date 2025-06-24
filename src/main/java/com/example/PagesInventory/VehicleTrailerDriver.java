package com.example.PagesInventory;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.Environment.BasePage;

import io.qameta.allure.Step;

public class VehicleTrailerDriver extends BasePage {



    private static final Logger logger = LogManager.getLogger(VehicleTrailerDriver.class);

    public VehicleTrailerDriver(WebDriver driver) {

        super(driver); 

    }

    @Step("Проверка перевода для элемента: {expectedText}")
    public void checkTranslation(By locator, String expectedText) {

        // WebElement element = waitAndGetElement(locator);
        WebElement element = getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        String actualText = element.getText().trim();
        String expectedTextNormalized = expectedText.trim();

        if (actualText.equals(expectedTextNormalized)) {
            logger.info("Текст корректен: " + actualText);
        } else {
            logger.error("Текст некорректен. Ожидалось: " + expectedTextNormalized + ", но получено: " + actualText);
        }
    }

    public void PageTrailerDriver() {


        switchToIframe();

        logger.info("Переключение на iframe выполнено.");

        checkTranslation(By.xpath("(//a[contains(@id, 'column_header_b') and text()='Тип ТС'])[2]"), "Тип ТС");
        checkTranslation(By.xpath("//a[text()=' Код ТС']"), " Код ТС");
        checkTranslation(By.xpath("//a[text()=' Гос. номер ТС']"), " Гос. номер ТС");
        checkTranslation(By.xpath("//a[text()=' Дата начала']"), " Дата начала");
        checkTranslation(By.xpath("//a[text()=' Дата окончания']"), " Дата окончания");
        checkTranslation(By.xpath("(//a[contains(@id, 'column_header_b') and text()='Водитель получивший ТМЦ'])[2]"),
                "Водитель получивший ТМЦ");

        returnToMainContent();
    }

}
// By.xpath("(//a[contains(@id, 'column_header_b') and text()='Тип ТС'])[2]"
// checkTranslation(By.xpath("//a[contains(@id, 'column_header_b') and
// text()='Водитель получивший ТМЦ'][2]"),
// "Водитель получивший ТМЦ");