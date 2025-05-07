package com.example.PagesOrder;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;

// Конфигурация счёта 

public class OpenInvoice {

        private static final Logger logger = LogManager.getLogger(OpenInvoice.class);

        private String InputServiceCodeValue = ConfigManager.getProperty("InputServiceCodeValue");
        private String PriceValueValue = ConfigManager.getProperty("PriceValueValue");

        private WebDriver driver;
        private FrameSwitcher frameSwitcher;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        private WebDriverWait wait;

        public OpenInvoice(WebDriver driver) {
                this.driver = driver;
                this.js = (JavascriptExecutor) driver;
                this.frameSwitcher = new FrameSwitcher(driver);
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        }

        private void scrollToElementHorizontally(WebElement scrollContainer, WebElement targetElement) {
                js.executeScript(
                                "const container = arguments[0];" +
                                                "const target = arguments[1];" +
                                                "const containerWidth = container.offsetWidth;" +
                                                "const targetLeft = target.getBoundingClientRect().left;" +
                                                "const containerLeft = container.getBoundingClientRect().left;" +
                                                "const targetOffset = targetLeft - containerLeft;" +
                                                "const scrollAmount = targetOffset - containerWidth / 2 + target.offsetWidth / 2;"
                                                +
                                                "container.scrollLeft += scrollAmount;",
                                scrollContainer, targetElement);
        }

        public void OpenServices() {

                System.out.println("Начинаем OpenInvoice/OpenServices");

                // Переключаемся в нужный фрейм
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                frameSwitcher.switchToIframe();

                System.out.println("Перешли в фрейм.");

                // Ожидаем появления инпут-поля с динамическим ID
                WebElement InputServiceCode = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@controlname='Service Code']//input[@role='combobox' and @type='text']")));
                System.out.println("Нашли поле Сервисный код");

                // Заполняем значение через JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                InputServiceCode, InputServiceCodeValue);

                System.out.println("Заполнили значение: " + InputServiceCodeValue);

                // Находим элемент, к которому нужно проскроллить (ЦЕНА)
                WebElement price = driver.findElement(By.xpath(
                                "//td[@controlname='Unit price']//input[@role='textbox' and @type='text']"));

                WebElement scrollContainer = driver.findElement(
                                By.xpath("(//div[contains(@class, 'ms-nav-scrollable scroll-source thm-bgcolor-1241058378')])[3]"));

                // Скроллинг к элементу
                scrollToElementHorizontally(scrollContainer, price);

                // фокус и события для ввода
                try {
                        // Добавление фокуса на поле
                        js.executeScript("arguments[0].focus();", price);

                        // Используем JavaScript для ввода значения
                        js.executeScript(
                                        "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change')); arguments[0].dispatchEvent(new Event('blur'));",
                                        price, PriceValueValue);

                        System.out.println("Заполнили поле Цена значением: " + PriceValueValue);

                        // Ожидаем изменения значения в поле
                        wait.until(ExpectedConditions.attributeToBe(price, "value", PriceValueValue));
                        Thread.sleep(300); // Даем немного времени для обработки

                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

                // Проверка результата
                System.out.println("Price field value after input: " + PriceValueValue);

                WebElement InvoiceInTamojnaButton = driver.findElement(By.xpath(
                                "//td[@controlname='Invoice For Custom']"));
                // Прокручиваем к дочернему элементу внутри прокручиваемого окна
                scrollToElementHorizontally(scrollContainer, InvoiceInTamojnaButton);

                InvoiceInTamojnaButton.click();

                WebElement backButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//button[@data-is-focusable='true' and @title='Назад']")));

                backButton.click();

                frameSwitcher.returnToMainContent();
        }

        public String extractInvoiceNumber() {

                System.out.println("Начинаем OpenInvoice/OpenServices");

                frameSwitcher.switchToIframe();

                System.out.println("Перешли в фрейм.");

                // Ожидаем появления инпут-поля с динамическим ID
                WebElement inputDocumentNo = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath(
                                                "//td[@controlname='Document No.']//input[@role='combobox' and @type='text']")));
                System.out.println("Считали поле");

                String invoiceNumber = inputDocumentNo.getAttribute("value");
                System.out.println("Номер счёта: " + invoiceNumber);
                logger.info("считали номер " + invoiceNumber);

                if (invoiceNumber == null || invoiceNumber.isEmpty()) {
                        throw new RuntimeException("Не удалось извлечь номер счёта!");
                }

                frameSwitcher.returnToMainContent();

                return invoiceNumber;
        }

}
