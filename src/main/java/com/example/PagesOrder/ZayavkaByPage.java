package com.example.PagesOrder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.Environment.BasePage;


import java.time.Duration;

// КОПИРОВАНИЕ ЗАЯВКИ ПО ДЕФОЛТУ

public class ZayavkaByPage extends BasePage {


        public ZayavkaByPage(WebDriver driver) {

                super(driver);

        }

        // ФУНКЦИЯ ДЛЯ КОПИРОВАНИЯ ЗАКАЗА
        public void clickSomeButtonInFrame() {

                System.out.println("Начинаем ZayavkaByPage/clickSomeButtonInFrame.");

                // Переключаемся в нужный фрейм

                switchToIframe();

                // Ожидаем кнопку Обработка
                WebElement buttonInFrame = waitAndGetClickableElement(
                        By.xpath("//*[@aria-label=' Обработка']"));
                System.out.println("Нашли кнопку ОБРАБОТКА.");

                // Кликаем по первой кнопке
                buttonInFrame.click();

                // ДАЛЬШЕ НАШЛИ КНОПКУ "СКОПИРОВАТЬ", ДЛЯ КОПИРОВАНИЯ ЗАКАЗА

                WebElement buttonInFrame2 = waitAndGetClickableElement(By.xpath(
                        "//button[@aria-label='Копировать файл' and @title='Копировать файл (Alt+C)']"));

                buttonInFrame2.click();
                System.out.println("Копирование заказа прошло успешно");

                // НАЖАТИЕ ОК ДЛЯ КОПИРОВАНИЯ ЗАЯВКИ
                WebElement buttonInFrame3 = waitAndGetClickableElement(By.xpath(
                                "//button//span[text()='ОК']"));
                buttonInFrame3.click();

                returnToMainContent();
        }

        // ПЕРЕХОЖУ В СЕРВИСЫ
        public void clickSomeButtonInService() {

                System.out.println("Начинаем ZayavkaByPage/lickSomeButtonInService.");

                // Переключаемся в нужный фрейм

                switchToIframe();

                System.out.println("Перешли в фрейм.");

                // Ожидаем появления первой кнопки
                WebElement buttonInObrabotka = waitAndGetClickableElement(By.xpath(
                                "//button[@aria-label=' Обработка']"));
                System.out.println("Нашли Кнопку Обработка.");
                //
                buttonInObrabotka.click();

                WebElement buttonInService = waitAndGetPresentElement(By.xpath(
                                "//button[@title='Сервисы (Ctrl+Alt+S)']"));
                System.out.println("Нашли Кнопку Сервис");

                buttonInService.click();
                System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

                returnToMainContent();
        }

}