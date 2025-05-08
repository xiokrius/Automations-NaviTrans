package com.example.PaymentPages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;
import com.example.PagesOrder.FrameSwitcher;

public class PaymentLogbook {

        private JavascriptExecutor js;
        private WebDriver driver;
        private WebDriverWait wait;
        private FrameSwitcher frameSwitcher;

        private String orderNumberValue = ConfigManager.getProperty("order.number");
        private String AccountTypeValue = ConfigManager.getProperty("AccountTypeValue");
        private String customersCodeValue = ConfigManager.getProperty("customersCodeValue");
        private String RecipientBankAccountValue = ConfigManager.getProperty("RecipientBankAccountValue");
        private String BalAccountNoValue = ConfigManager.getProperty("BalAccountNoValue");
        private String CurrencyCodeValue = ConfigManager.getProperty("CurrencyCodeValue");
        private String BalAccountTypeValue = ConfigManager.getProperty("BalAccountTypeValue");
        private String PriceValueValue = ConfigManager.getProperty("PriceValueValue");
        String negativeAmount = "-" + PriceValueValue;

        public PaymentLogbook(WebDriver driver) {
                this.driver = driver;
                this.js = (JavascriptExecutor) driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                this.frameSwitcher = new FrameSwitcher(driver);
        }

        public void openPaymentLoogbook() {

                driver.get(ConfigManager.getProperty("PaymentLogbook"));

                // Получаем текущую дату в формате "08.05.2025"
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                String paymentDateValue = currentDate.format(formatter);

                frameSwitcher.switchToIframe();

                // дата учёта
                WebElement InputPostingDate = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@controlname='Posting Date']//input[@role='combobox' and @type='text']")));
                System.out.println("Нашли поле дата учёта");

                WebElement PaymentDate = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@controlname='Payment Date']//input[@role='combobox' and @type='text']")));
                System.out.println("Нашли поле Дата оплаты");

                js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                PaymentDate, paymentDateValue);

                System.out.println("Заполнили значение: " + paymentDateValue);

                WebElement DocumentNo = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@controlname='Document No.']//input")));
                System.out.println("Нашли поле Номер счёта");

                js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                DocumentNo, orderNumberValue);

                System.out.println("Заполнили значение: " + orderNumberValue);

                WebElement AccountType = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//td[@controlname='Account Type']//select")));
                System.out.println("Нашли поле Тип счёта");

                frameSwitcher.fillSelectWithJS(AccountType, AccountTypeValue);

                // Номер клиента вставить через настройку где сохраняется номер заказа
                WebElement AccountNo = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@controlname='Account No.']//input")));
                System.out.println("Нашли поле Номер клиента");

                frameSwitcher.setInputValue(AccountNo, customersCodeValue);

                WebElement MessagetoRecipient = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//td[@controlname='Message to Recipient']//input")));
                System.out.println("Нашли поле тестовое сообщение");

                frameSwitcher.setInputValue(MessagetoRecipient, RecipientBankAccountValue);

                // ДАЛЬШЕ СКРОЛЛЮ

                WebElement scrollContainer = driver.findElement(
                                By.xpath("(//div[contains(@class, 'freeze-pane-scrollbar-div')])"));

                // Bal. Account No. номер баланс. счёта, B020
                WebElement BalAccountNo = driver.findElement((By.xpath(
                                "//td[@controlname='Bal. Account No.']//input")));
                System.out.println("Нашли поле Номер баланс. счёта");

                frameSwitcher.scrollToElementHorizontally(scrollContainer, BalAccountNo);

                WebElement BalAccountType = driver.findElement(
                                By.xpath("//td[@controlname='Account Type']//select"));
                System.out.println("Нашли поле Тип баланс. счёта");

                frameSwitcher.fillSelectWithJS(BalAccountType, BalAccountTypeValue);

                frameSwitcher.setInputValue(BalAccountNo, BalAccountNoValue);

                WebElement CurrencyCode = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//td[@controlname='Currency Code']//input")));
                System.out.println("Нашли поле КОД ВАЛЮТЫ");

                frameSwitcher.setInputValue(CurrencyCode, CurrencyCodeValue);

                // вводить с минусом, обязательна инициализация
                WebElement Amount = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//td[@controlname='Amount']//input")));
                System.out.println("Нашли поле Цена");

                frameSwitcher.setInputValue(Amount, negativeAmount);

                frameSwitcher.returnToMainContent();

        }

}
