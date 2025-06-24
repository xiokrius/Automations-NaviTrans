package com.example.PagesOrder;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.Environment.BasePage;


public class ServiceInvoice extends BasePage{


    public ServiceInvoice(WebDriver driver) {

        super(driver);

    }

    public void clickSomeButtonInService() {

        System.out.println("Начинаем ServiCeInvoice/clickSomeButtonInService");

        switchToIframe();

        // Ожидаем появления первой кнопки
        WebElement buttonInObrabotka = waitAndGetClickableElement(
                By.xpath("//*[@aria-label=' Обработка' and @data-is-focusable='true']"));
        System.out.println("Нашли Кнопку Обработка.");

        buttonInObrabotka.click();

        WebElement buttonInService = waitAndGetPresentElement(By.xpath(
                "//button[@aria-label='Сервисы' and @title='Сервисы (Ctrl+Alt+S)']"));
        System.out.println("Нашли Кнопку Сервис");

        buttonInService.click();
        System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

        returnToMainContent();
    }

}
