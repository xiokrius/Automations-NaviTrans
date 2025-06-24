package com.example.PaymentPages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.example.ConfigManager;
import com.example.Environment.BasePage;

public class PaymentLogbook extends BasePage {

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

                super(driver);
        }

        public void openPaymentLoogbook() {

                openUrl(ConfigManager.getProperty("PaymentLogbook"));

                // Получаем текущую дату в формате "08.05.2025"
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                String paymentDateValue = currentDate.format(formatter);

                switchToIframe();

                // дата учёта
                WebElement InputPostingDate = waitAndGetClickableElement(
                                By.xpath("//td[@controlname='Posting Date']//input[@role='combobox' and @type='text']"));
                System.out.println("Нашли поле дата учёта");

                WebElement PaymentDate = waitAndGetClickableElement(
                                By.xpath("//td[@controlname='Payment Date']//input[@role='combobox' and @type='text']"));
                System.out.println("Нашли поле Дата оплаты");

                getJS().executeScript(
                                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                PaymentDate, paymentDateValue);

                System.out.println("Заполнили значение: " + paymentDateValue);

                WebElement DocumentNo = waitAndGetClickableElement(
                                By.xpath("//td[@controlname='Document No.']//input"));
                System.out.println("Нашли поле Номер счёта");

                getJS().executeScript(
                                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                DocumentNo, orderNumberValue);

                System.out.println("Заполнили значение: " + orderNumberValue);

                WebElement AccountType = waitAndGetPresentElement(
                                By.xpath("//td[@controlname='Account Type']//select"));
                System.out.println("Нашли поле Тип счёта");

                fillSelectWithJS(AccountType, AccountTypeValue);

                // Номер клиента вставить через настройку где сохраняется номер заказа
                WebElement AccountNo = waitAndGetClickableElement(
                                By.xpath("//td[@controlname='Account No.']//input"));
                System.out.println("Нашли поле Номер клиента");

                setInputValue(AccountNo, customersCodeValue);

                WebElement MessagetoRecipient = waitAndGetClickableElement(
                                By.xpath(
                                                "//td[@controlname='Message to Recipient']//input"));
                System.out.println("Нашли поле тестовое сообщение");

                setInputValue(MessagetoRecipient, RecipientBankAccountValue);

                // ДАЛЬШЕ СКРОЛЛЮ

                WebElement scrollContainer = getDriver().findElement(
                                By.xpath("(//div[contains(@class, 'freeze-pane-scrollbar-div')])"));

                // Bal. Account No. номер баланс. счёта, B020
                WebElement BalAccountNo = getDriver().findElement((By.xpath(
                                "//td[@controlname='Bal. Account No.']//input")));
                System.out.println("Нашли поле Номер баланс. счёта");

                scrollToElementHorizontally(scrollContainer, BalAccountNo);

                WebElement BalAccountType = getDriver().findElement(
                                By.xpath("//td[@controlname='Account Type']//select"));
                System.out.println("Нашли поле Тип баланс. счёта");

                fillSelectWithJS(BalAccountType, BalAccountTypeValue);

                setInputValue(BalAccountNo, BalAccountNoValue);

                WebElement CurrencyCode = waitAndGetClickableElement(
                                By.xpath(
                                                "//td[@controlname='Currency Code']//input"));
                System.out.println("Нашли поле КОД ВАЛЮТЫ");

                setInputValue(CurrencyCode, CurrencyCodeValue);

                // вводить с минусом, обязательна инициализация
                WebElement Amount = waitAndGetClickableElement(
                                By.xpath(
                                                "//td[@controlname='Amount']//input"));
                System.out.println("Нашли поле Цена");

                setInputValue(Amount, negativeAmount);

                returnToMainContent();

        }

}
