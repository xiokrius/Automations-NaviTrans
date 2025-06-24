package com.example.PagesOrder;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;
import com.example.Environment.BasePage;


// Конфигурация счёта 

public class OpenInvoice extends BasePage {

        private static final Logger logger = LogManager.getLogger(OpenInvoice.class);

        private String InputServiceCodeValue = ConfigManager.getProperty("InputServiceCodeValue");
        private String PriceValueValue = ConfigManager.getProperty("PriceValueValue");

        private final WebDriverWait customWait;

        public OpenInvoice(WebDriver driver) {
                super(driver);
                this.customWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        }



        public void OpenServices() {

                System.out.println("Начинаем OpenInvoice/OpenServices");

                // Переключаемся в нужный фрейм

                switchToIframe();

                System.out.println("Перешли в фрейм.");

                // Ожидаем появления инпут-поля с динамическим ID
                WebElement InputServiceCode = waitAndGetClickableElement(
                                By.xpath("//td[@controlname='Service Code']//input[@role='combobox' and @type='text']"));
                System.out.println("Нашли поле Сервисный код");

                // Заполняем значение через JavaScript
                getJS().executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                InputServiceCode, InputServiceCodeValue);

                System.out.println("Заполнили значение: " + InputServiceCodeValue);

                // Находим элемент, к которому нужно проскроллить (ЦЕНА)
                WebElement price = getDriver().findElement(By.xpath(
                                "//td[@controlname='Unit price']//input[@role='textbox' and @type='text']"));

                WebElement scrollContainer = getDriver().findElement(
                                By.xpath("(//div[contains(@class, 'ms-nav-scrollable scroll-source thm-bgcolor-1241058378')])[3]"));

                // Скроллинг к элементу
                scrollToElementHorizontally(scrollContainer, price);

                // фокус и события для ввода
                try {
                        // Добавление фокуса на поле
                        getJS().executeScript("arguments[0].focus();", price);

                        // Используем JavaScript для ввода значения
                        getJS().executeScript(
                                        "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change')); arguments[0].dispatchEvent(new Event('blur'));",
                                        price, PriceValueValue);

                        System.out.println("Заполнили поле Цена значением: " + PriceValueValue);

                        // Ожидаем изменения значения в поле
                        customWait.until(ExpectedConditions.attributeToBe(price, "value", PriceValueValue));
                        Thread.sleep(300); // Даем немного времени для обработки

                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

                // Проверка результата
                System.out.println("Price field value after input: " + PriceValueValue);

                WebElement InvoiceInTamojnaButton = getDriver().findElement(By.xpath(
                                "//td[@controlname='Invoice For Custom']"));
                // Прокручиваем к дочернему элементу внутри прокручиваемого окна
                scrollToElementHorizontally(scrollContainer, InvoiceInTamojnaButton);

                InvoiceInTamojnaButton.click();

                WebElement backButton = waitAndGetClickableElement(
                                By.xpath(
                                                "//button[@data-is-focusable='true' and @title='Назад']"));

                backButton.click();

                returnToMainContent();
        }

        public String extractInvoiceNumber() {

                System.out.println("Начинаем OpenInvoice/OpenServices");

                switchToIframe();

                System.out.println("Перешли в фрейм.");

                // Ожидаем появления инпут-поля с динамическим ID
                WebElement inputDocumentNo = waitAndGetPresentElement(
                                By.xpath(
                                "//td[@controlname='Document No.']//input[@role='combobox' and @type='text']"));
                System.out.println("Считали поле");

                String invoiceNumber = inputDocumentNo.getAttribute("value");
                System.out.println("Номер счёта: " + invoiceNumber);
                logger.info("считали номер " + invoiceNumber);

                if (invoiceNumber == null || invoiceNumber.isEmpty()) {
                        throw new RuntimeException("Не удалось извлечь номер счёта!");
                }

                returnToMainContent();

                return invoiceNumber;
        }

}
