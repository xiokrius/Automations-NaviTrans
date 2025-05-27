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

public class TestPaymentLogbook {

        private final JavascriptExecutor js;
        private final WebDriver driver;
        private final WebDriverWait wait;
        private final FrameSwitcher frameSwitcher;

        private String orderNumberValue = ConfigManager.getProperty("order.number");
        private String AccountTypeValue = ConfigManager.getProperty("AccountTypeValue");
        private String customersCodeValue = ConfigManager.getProperty("customersCodeValue");
        private String RecipientBankAccountValue = ConfigManager.getProperty("RecipientBankAccountValue");
        private String BalAccountNoValue = ConfigManager.getProperty("BalAccountNoValue");
        private String CurrencyCodeValue = ConfigManager.getProperty("CurrencyCodeValue");
        private String BalAccountTypeValue = ConfigManager.getProperty("BalAccountTypeValue");
        private String PriceValueValue = ConfigManager.getProperty("PriceValueValue");
        private String MethodCodeValue = ConfigManager.getProperty("MethodCodeValue");

        String negativeAmount = "-" + PriceValueValue;
        private final String paymentDateValue;

        public TestPaymentLogbook(WebDriver driver) {
                this.driver = driver;
                this.js = (JavascriptExecutor) driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                this.frameSwitcher = new FrameSwitcher(driver);
                this.paymentDateValue = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public void openPaymentLoogbook() {

                driver.get(ConfigManager.getProperty("PaymentLogbook"));

                // Получаем текущую дату в формате "08.05.2025"

                frameSwitcher.switchToIframe();
        }

        public void fillPostingDate() {
                // дата учёта
                WebElement InputPostingDate = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@controlname='Posting Date']//input[@role='combobox' and @type='text']")));
                System.out.println("Нашли поле дата учёта");

                js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                InputPostingDate, this.paymentDateValue);

        }

        public void fillPaymentDate() {
                WebElement PaymentDate = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@controlname='Payment Date']//input[@role='combobox' and @type='text']")));
                System.out.println("Нашли поле Дата оплаты");

                js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                PaymentDate, this.paymentDateValue);

                System.out.println("Заполнили значение: " + paymentDateValue);

        }

        public void fillDocumentNo() {
                WebElement DocumentNo = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@controlname='Document No.']//input")));
                System.out.println("Нашли поле Номер счёта");

                js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                DocumentNo, orderNumberValue);
                System.out.println("Заполнили значение: " + orderNumberValue);
        }

        public void fillAccountType() {
                WebElement AccountType = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//td[@controlname='Account Type']//select")));
                System.out.println("Нашли поле Тип счёта");

                frameSwitcher.fillSelectWithJS(AccountType, AccountTypeValue);

                AccountType.click();

        }

        public void fillAccountNo() {
                // Номер клиента вставить через настройку где сохраняется номер заказа
                WebElement AccountNo = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@controlname='Account No.']//input")));
                System.out.println("Нашли поле Номер клиента");

                frameSwitcher.setInputValue(AccountNo, customersCodeValue);

                WebElement PaymentMethodCode = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@controlname='Payment Method Code']//input")));
                System.out.println("Нашли поле Номер клиента");

                frameSwitcher.setInputValue(PaymentMethodCode, MethodCodeValue);

        }

        public void fillMessagetoRecipient() {
                WebElement MessagetoRecipient = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//td[@controlname='Description']//input")));
                System.out.println("Нашли поле тестовое сообщение");

                frameSwitcher.setInputValue(MessagetoRecipient, RecipientBankAccountValue);
        }

        public void fillBalAccountNo() {
                WebElement scrollContainer = driver.findElement(
                                By.xpath("(//div[contains(@class, 'freeze-pane-scrollbar')])"));

                // Bal. Account No. номер баланс. счёта, B020
                WebElement BalAccountNo = driver.findElement((By.xpath(
                                "//td[@controlname='Bal. Account No.']//input")));
                System.out.println("Нашли поле Номер баланс. счёта");

                frameSwitcher.scrollToElementHorizontally(scrollContainer, BalAccountNo);

                frameSwitcher.setInputValue(BalAccountNo, BalAccountNoValue);

                WebElement BalAccountType = driver.findElement(
                                By.xpath("//td[@controlname='Bal. Account Type']//select"));
                System.out.println("Нашли поле Тип баланс. счёта");

                frameSwitcher.fillAndActivateSelect(BalAccountType, BalAccountTypeValue);

        }

        public void fillCurrencyCode() {

                WebElement CurrencyCode = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//td[@controlname='Currency Code']//input")));
                System.out.println("Нашли поле КОД ВАЛЮТЫ");

                frameSwitcher.setInputValue(CurrencyCode, CurrencyCodeValue);

        }

        public void fillAmount() {
                // вводить с минусом, обязательна инициализация
                WebElement Amount = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//td[@controlname='Amount']//input")));
                System.out.println("Нашли поле Цена");

                WebElement scrollContainer = driver.findElement(
                                By.xpath("(//div[contains(@class, 'freeze-pane-scrollbar')])"));

                frameSwitcher.scrollToElementHorizontally(scrollContainer, Amount);

                frameSwitcher.setInputValue(Amount, negativeAmount);

        }

        public void openUchetPechat() {

                WebElement UchetPechatButton = wait.until(ExpectedConditions.elementToBeClickable((By.xpath(
                                "//button[@aria-label='Учет/печать']"))));

                UchetPechatButton.click();

        }

        public void openOpalaScheta() {

                WebElement OplataSchetaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//button[@aria-label='Учет']")));

                OplataSchetaButton.click();

                try {
                        // WebElement WindowsAutorised =
                        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        // "//p[@title='Учесть строки журнала?']")));

                        WebElement windowsAutorised = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.xpath("//p[text()='Учесть строки журнала?']")));

                        WebElement buttonIsOk = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//button[contains(@class, '1632124310')]//span[text()='Да']")));

                        buttonIsOk.click();

                        try {

                                WebElement windowsAutorised2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                                By.xpath("//p[text()='Строки журнала успешно учтены.']")));

                                WebElement ButtonInOk2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                                "//button[contains(@class, '1632124310')]//span[text()='ОК']")));

                                ButtonInOk2.click();

                        } catch (Exception i) {

                                System.out.println("Второе окно \"Строки журнала успешно учтены.\" не появилось ");

                        }

                } catch (Exception e) {

                        System.out.println("Окно не найдено");
                }

        }

}
