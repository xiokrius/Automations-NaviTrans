package com.example.PagesOrder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// КОПИРОВАНИЕ ЗАЯВКИ ПО ДЕФОЛТУ

public class ZayavkaByPage {

        private WebDriver driver;
        private WebDriverWait wait;

        public ZayavkaByPage(WebDriver driver) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Инициализация WebDriverWait
        }

        private void switchToIframe() {
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.className("designer-client-frame")));
                driver.switchTo().frame(iframe);
        }

        // ФУНКЦИЯ ДЛЯ КОПИРОВАНИЯ ЗАКАЗА
        public void clickSomeButtonInFrame() {

                System.out.println("Начинаем ZayavkaByPage/clickSomeButtonInFrame.");

                // Переключаемся в нужный фрейм
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                switchToIframe();

                // Ожидаем кнопку Обработка
                WebElement buttonInFrame = wait
                                .until(ExpectedConditions
                                                .elementToBeClickable(By.xpath("//*[@aria-label=' Обработка']")));
                System.out.println("Нашли кнопку ОБРАБОТКА.");

                // Кликаем по первой кнопке
                buttonInFrame.click();

                // ДАЛЬШЕ НАШЛИ КНОПКУ "СКОПИРОВАТЬ", ДЛЯ КОПИРОВАНИЯ ЗАКАЗА

                WebElement buttonInFrame2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//button[@aria-label='Копировать файл' and @title='Копировать файл (Alt+C)']")));

                buttonInFrame2.click();
                System.out.println("Копирование заказа прошло успешно");

                // НАЖАТИЕ ОК ДЛЯ КОПИРОВАНИЯ ЗАЯВКИ
                WebElement buttonInFrame3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//button//span[text()='ОК']")));
                buttonInFrame3.click();
        }

        // ПЕРЕХОЖУ В СЕРВИСЫ
        public void clickSomeButtonInService() {

                System.out.println("Начинаем ZayavkaByPage/lickSomeButtonInService.");

                // Переключаемся в нужный фрейм
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                switchToIframe();

                System.out.println("Перешли в фрейм.");

                // Ожидаем появления первой кнопки
                WebElement buttonInObrabotka = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//*[@aria-label=' Обработка']")));
                System.out.println("Нашли Кнопку Обработка.");
                //
                buttonInObrabotka.click();

                WebElement buttonInService = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "//button[@title='Сервисы (Ctrl+Alt+S)']")));
                System.out.println("Нашли Кнопку Сервис");

                buttonInService.click();
                System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

                returnToMainContent();
        }

        public void returnToMainContent() {
                driver.switchTo().defaultContent();
                System.out.println("ласт вышел с фрейма, проверка");
        }
}