package com.example.PaymentPages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.ConfigManager;
import com.example.Environment.BasePage;

public class TestPaymentLogbook extends BasePage {

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

                super(driver);

                this.paymentDateValue = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public void openPaymentLoogbook() {

                openUrl(ConfigManager.getProperty("PaymentLogbook"));

                // Получаем текущую дату в формате "08.05.2025"

                switchToIframe();
        }

        public void fillPostingDate() {
                // дата учёта
                WebElement InputPostingDate = waitAndGetClickableElement(
                                By.xpath("//td[@controlname='Posting Date']//input[@role='combobox' and @type='text']"));
                System.out.println("Нашли поле дата учёта");

                getJS().executeScript(
                                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                InputPostingDate, this.paymentDateValue);

        }

        public void fillPaymentDate() {
                WebElement PaymentDate = waitAndGetClickableElement(
                                By.xpath("//td[@controlname='Payment Date']//input[@role='combobox' and @type='text']"));
                System.out.println("Нашли поле Дата оплаты");

                getJS().executeScript(
                                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                PaymentDate, this.paymentDateValue);

                System.out.println("Заполнили значение: " + paymentDateValue);

        }

        public void fillDocumentNo() {
                WebElement DocumentNo = waitAndGetClickableElement(
                                By.xpath("//td[@controlname='Document No.']//input"));
                System.out.println("Нашли поле Номер счёта");

                getJS().executeScript(
                                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                DocumentNo, orderNumberValue);
                System.out.println("Заполнили значение: " + orderNumberValue);
        }

        public void fillAccountType() {
                WebElement AccountType = waitAndGetPresentElement(
                                By.xpath("//td[@controlname='Account Type']//select"));
                System.out.println("Нашли поле Тип счёта");

                AccountType.click();

                fillSelectWithJS(AccountType, AccountTypeValue);

                AccountType.click();

        }

        public void fillAccountNo() {
                // Номер клиента вставить через настройку где сохраняется номер заказа
                WebElement AccountNo = waitAndGetClickableElement(
                                By.xpath("//td[@controlname='Account No.']//input"));
                System.out.println("Нашли поле Номер клиента");

                setInputValue(AccountNo, customersCodeValue);

                WebElement PaymentMethodCode = waitAndGetClickableElement(
                                By.xpath("//td[@controlname='Payment Method Code']//input"));
                System.out.println("Нашли поле Номер клиента");

                setInputValue(PaymentMethodCode, MethodCodeValue);

        }

        public void fillMessagetoRecipient() {
                WebElement MessagetoRecipient = waitAndGetClickableElement(
                                By.xpath(
                                                "//td[@controlname='Description']//input"));
                System.out.println("Нашли поле тестовое сообщение");

                setInputValue(MessagetoRecipient, RecipientBankAccountValue);
        }

        public void fillBalAccountNo() {
                WebElement scrollContainer = getDriver().findElement(
                                By.xpath("(//div[contains(@class, 'freeze-pane-scrollbar')])"));

                // Bal. Account No. номер баланс. счёта, B020
                WebElement BalAccountNo = getDriver().findElement((By.xpath(
                                "//td[@controlname='Bal. Account No.']//input")));
                System.out.println("Нашли поле Номер баланс. счёта");

                scrollToElementHorizontally(scrollContainer, BalAccountNo);

                setInputValue(BalAccountNo, BalAccountNoValue);

                WebElement BalAccountType = getDriver().findElement(
                                By.xpath("//td[@controlname='Bal. Account Type']//select"));
                System.out.println("Нашли поле Тип баланс. счёта");

                fillAndActivateSelect(BalAccountType, BalAccountTypeValue);

        }

        public void fillCurrencyCode() {

                WebElement CurrencyCode = waitAndGetClickableElement(
                                By.xpath(
                                                "//td[@controlname='Currency Code']//input"));
                System.out.println("Нашли поле КОД ВАЛЮТЫ");

                setInputValue(CurrencyCode, CurrencyCodeValue);

        }

        public void fillAmount() {
                // вводить с минусом, обязательна инициализация
                WebElement Amount = waitAndGetClickableElement(
                                By.xpath(
                                                "//td[@controlname='Amount']//input"));
                System.out.println("Нашли поле Цена");

                WebElement scrollContainer = getDriver().findElement(
                                By.xpath("(//div[contains(@class, 'freeze-pane-scrollbar')])"));

                scrollToElementHorizontally(scrollContainer, Amount);

                setInputValue(Amount, negativeAmount);

        }

        public void openUchetPechat() {

                WebElement UchetPechatButton = waitAndGetClickableElement((By.xpath(
                                "//button[@aria-label='Учет/печать']")));

                UchetPechatButton.click();

        }

        public void openOpalaScheta() {

                WebElement OplataSchetaButton = waitAndGetClickableElement(By.xpath(
                                "//button[@aria-label='Учет']"));

                OplataSchetaButton.click();

                try {
                        // WebElement WindowsAutorised =
                        // waitAndGetVisibleElement(By.xpath(
                        // "//p[@title='Учесть строки журнала?']")));

                        WebElement windowsAutorised = waitAndGetVisibleElement(
                                        By.xpath("//p[text()='Учесть строки журнала?']"));

                        WebElement buttonIsOk = waitAndGetClickableElement(
                                        By.xpath("//button[contains(@class, '1632124310')]//span[text()='Да']"));

                        buttonIsOk.click();

                        try {

                                WebElement windowsAutorised2 = waitAndGetVisibleElement(
                                                By.xpath("//p[text()='Строки журнала успешно учтены.']"));

                                WebElement ButtonInOk2 = waitAndGetClickableElement(By.xpath(
                                                "//button[contains(@class, '1632124310')]//span[text()='ОК']"));

                                ButtonInOk2.click();

                        } catch (Exception i) {

                                System.out.println("Второе окно \"Строки журнала успешно учтены.\" не появилось ");

                        }

                } catch (Exception e) {

                        System.out.println("Окно не найдено");
                }

        }

}
