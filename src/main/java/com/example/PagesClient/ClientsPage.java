package com.example.PagesClient;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.example.ConfigManager;
import com.example.Environment.BasePage;

public class ClientsPage extends BasePage {

        private String RegNumberValue;

        private String typeCarrierValue = ConfigManager.getProperty("typeClientValue");
        private String CityValue = ConfigManager.getProperty("CityValue");
        // private String RegNumberValue = ConfigManager.getProperty("RegNumberValue");
        private String BlockCustomerValue = ConfigManager.getProperty("BlockCustomerValue");
        private String CDElementValue = ConfigManager.getProperty("CDElementValue");
        private String NVTBusinessUnitValue = ConfigManager.getProperty("NVTBusinessUnitValue");
        private String AttractedByWhom = ConfigManager.getProperty("AttractedByWhom");
        private String emailValue = ConfigManager.getProperty("emailValue");
        // CDElement

        public ClientsPage(WebDriver driver) {

                super(driver);
                this.RegNumberValue = generateRandomINN(9);
                System.out.println("Сгенерированный ИНН: " + this.RegNumberValue);
        }

        // Метод для генерации случайного 9-значного ИНН
        private String generateRandomINN(int length) {
                StringBuilder inn = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < length; i++) {
                        inn.append(random.nextInt(10)); // Добавляем случайную цифру от 0 до 9
                }
                return inn.toString();
        }

        public void fillingClientsForm() {

                System.out.println("Начинаем ClientsPage/fillingClientsForm");

                // Переключаемся в Фрейм
                switchToIframe();

                // копирую номер клиента для того что бы потом продублировать в плательщика
                WebElement inputField = waitAndGetVisibleElement(By.xpath(
                                "//a[contains(@title, 'обновить значение для Код')]/following-sibling::input"));
                String value = inputField.getAttribute("value");
                System.out.println("Значение поля: " + value);

                TestData.clientValue = value;

                // new field кем привлечён
                WebElement AttractedByWhomInput = waitAndGetClickableElement(By.xpath(
                                "//a[contains(@title, 'Кем привлечён')]/following-sibling::input"));

                setInputJS(AttractedByWhomInput, AttractedByWhom);

                // Вводим "Тип клиента"
                WebElement typeClient = waitAndGetVisibleElement(
                                By.xpath("//a[text()='Тип клиента']/following::select[1]"));
                System.out.println("Ввели значение поля Тип клиента");

                selectDropdownByValue(typeClient, typeCarrierValue);

                // Вводим "по подразделению"

                WebElement NVTBusinessUnit = waitAndGetClickableElement(
                                By.xpath("//a[contains(@title, 'элемента По подразделению')]/following-sibling::input"));

                setInputValue(NVTBusinessUnit, NVTBusinessUnitValue);

                WebElement SpanCountry = waitAndGetVisibleElement(
                                By.xpath("//span[text()='Адрес и контакты']"));
                scrollToElement(SpanCountry);

                SpanCountry.click();

                WebElement emailInput = waitAndGetVisibleElement(By.xpath(
                                "//input[@type='email']"));

                setInputJS(emailInput, emailValue);

                scrollToElement(emailInput);

                WebElement Score = waitAndGetVisibleElement(
                                By.xpath("//span[text()='Счет'][@class='caption-text']"));

                scrollToElement(Score);

                Score.click();

                WebElement City = waitAndGetVisibleElement(
                                By.xpath(
                                                "//a[contains(@title, 'для элемента Город')]/following-sibling::input"));

                City.click();

                setInputValue(City, CityValue);

                // На будущее, если нужен будет СНГ регистр. номер.
                // WebElement RegNumber =
                // waitAndGetVisibleElement(
                // By.xpath("//div[@controlname='FTS Registration No']//input")));

                WebElement VATRegistrationNo = waitAndGetVisibleElement(
                                By.xpath(
                                                "//div[@controlname='VAT Registration No.']//input"));

                setInputValue(VATRegistrationNo, RegNumberValue);

                try {

                        createWait(2).until(ExpectedConditions.presenceOfElementLocated(
                                        By.xpath("//div[@title='Почтовые индексы' and contains(text(), 'Почтовые индексы')]")));
                        System.out.println("Всплывающее окно обнаружено");

                        WebElement ButtonInOk = waitAndGetClickableElement(By.xpath(
                                        "//button[contains(@class, '1876861216 thm-bgcolor')]//span[text()='ОК']"));
                        System.out.println("Кнопка ОК идентифицирована");
                        ButtonInOk.click();
                        System.out.println("Нажата кнопка ОК");
                } catch (Exception e) {

                        System.out.println("Окно не обнаружено");
                }

                System.out.println("Раскрытие списка");
                WebElement BolchePoley = waitAndGetClickableElement(By.xpath(
                                "//button[@aria-label='Счет, Показать больше']"));

                BolchePoley.click();
                System.out.println("Список раскрыт");

                WebElement BilltoCustomerNo = waitAndGetClickableElement(By.xpath(
                                "//a[contains(@title, 'для элемента Плательщик')]/following-sibling::input"));

                setInputValue(BilltoCustomerNo, value);

                WebElement Pochta = waitAndGetVisibleElement(By.xpath(
                                "//span[text()='Почтовый адрес']"));

                System.out.println("Почта найдена");

                scrollToElement(Pochta);
                System.out.println("скролл выполнен");

                WebElement Plateji = waitAndGetVisibleElement(By.xpath(
                                "//img[contains(@alt, 'обязательных полей')]/following-sibling::span[text()='Платежи']"));

                System.out.println("Платежи найдены");

                Plateji.click();

                WebElement FTSPaymenttype = waitAndGetVisibleElement(
                                By.xpath(
                                                "//div[@controlname='FTS Payment type']//select"));

                selectDropdownByValue(FTSPaymenttype, typeCarrierValue);

                WebElement PaymentTermsCodetrsl = waitAndGetClickableElement(
                                By.xpath("//a[@aria-label='Просмотреть или обновить значение для Код условий платежа']"));

                System.out.println("Нашёл код условий платежа");

                PaymentTermsCodetrsl.click();

                System.out.println("кликнул по код условий платежа");

                try {
                        WebElement WindowPaymentTermsCode = waitAndGetVisibleElement(By.xpath(
                                        "//div[@title='Условия платежа']"));

                        System.out.println("нашёл окно");
                        WebElement ElementPaymentTermsCode = waitAndGetClickableElement(
                                        By.xpath("//a[text()='AFT L 10Д']"));

                        System.out.println("нашёл элемент");

                        ElementPaymentTermsCode.click();

                } catch (Exception e) {

                        System.out.println("Не нашёл окно");
                }

                WebElement BlockCustomer = waitAndGetVisibleElement(By.xpath(
                                "//div[@controlname='Blocked']//select"));

                scrollToElement(BlockCustomer);

                selectDropdownByValue(BlockCustomer, BlockCustomerValue);

                WebElement ButtonBack = waitAndGetClickableElement(By.xpath(
                                "//button[@title='Назад']"));

                ButtonBack.click();

                returnToMainContent();

        }

        public class TestData {
                public static String clientValue; // Статическая переменная для хранения значения
        }

        public void OpenCD() {

                System.out.println("Начинаем ClientsPage/fillingClientsForm");

                // Переключаемся в Фрейм
                switchToIframe();

                WebElement CDElement = waitAndGetClickableElement(
                                By.xpath("//div[@controlname='Credit Limit (LCY)']//input"));

                setInputValue(CDElement, CDElementValue);

                WebElement ButtonBack = waitAndGetClickableElement(By.xpath(
                                "//button[@title='Назад']"));

                ButtonBack.click();

                returnToMainContent();

        }

        public void waitingForTheClientPageToLoad() {

                switchToIframe();

                WebElement CardOfClient = waitAndGetVisibleElement(By.xpath("//span[text()='Карточка клиента']"));

                returnToMainContent();

        }

        public void editCardOfClients() {

                switchToIframe();

                WebElement EditCardOfClientsButton = waitAndGetClickableElement(
                                By.xpath("//i[@data-icon-name='Edit']"));

                EditCardOfClientsButton.click();

                returnToMainContent();

        }

}
