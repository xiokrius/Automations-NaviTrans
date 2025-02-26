package com.example.PagesOrder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ServiceInvoice {

    private WebDriver driver;
    private FrameSwitcher frameSwitcher;

    public ServiceInvoice(WebDriver driver) {
        this.driver = driver;
        this.frameSwitcher = new FrameSwitcher(driver);
    }

    public void clickSomeButtonInService() {

        System.out.println("Начинаем ServiCeInvoice/clickSomeButtonInService");

        // Переключаемся в нужный фрейм
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        frameSwitcher.switchToIframe();

        // Ожидаем появления первой кнопки
        WebElement buttonInObrabotka = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@aria-label=' Обработка' and @data-is-focusable='true']")));
        System.out.println("Нашли Кнопку Обработка.");

        buttonInObrabotka.click();

        WebElement buttonInService = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//button[@aria-label='Сервисы' and @title='Сервисы (Ctrl+Alt+S)']")));
        System.out.println("Нашли Кнопку Сервис");

        buttonInService.click();
        System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

        frameSwitcher.returnToMainContent();
    }

}
