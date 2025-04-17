package com.example.PagesClient;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;
import com.example.PagesClient.ClientsPage.TestData;
import com.example.PagesOrder.FrameSwitcher;

public class ClientsPage {

        private WebDriver driver;
        private JavascriptExecutor js;
        private WebDriverWait wait;
        private String RegNumberValue;
        private FrameSwitcher frameSwitcher;

        private String typeCarrierValue = ConfigManager.getProperty("typeClientValue");
        private String CityValue = ConfigManager.getProperty("CityValue");
        // private String RegNumberValue = ConfigManager.getProperty("RegNumberValue");
        private String BlockCustomerValue = ConfigManager.getProperty("BlockCustomerValue");
        private String CDElementValue = ConfigManager.getProperty("CDElementValue");
        private String NVTBusinessUnitValue = ConfigManager.getProperty("NVTBusinessUnitValue");
        // CDElement

        public ClientsPage(WebDriver driver) {

                this.driver = driver;
                this.js = (JavascriptExecutor) driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                this.frameSwitcher = new FrameSwitcher(driver);
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

        public void scrollToElement(WebElement element) {
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        }

        private void selectDropdownByValue(WebElement selectElement, String value) {
                Select select = new Select(selectElement);
                select.selectByValue(value);
                System.out.println("Выбрали значение в select: " + value);
        }

        private void setInputValue(WebElement element, String value) {
                js.executeScript(
                                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                element, value);
                System.out.println("Заполнили значение: " + value);
        }

        private void switchToIframe() {
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.className("designer-client-frame")));
                driver.switchTo().frame(iframe);
        }

        public void returnToMainContent() {
                driver.switchTo().defaultContent();
                System.out.println("ласт вышел с фрейма, проверка");
        }

        public void fillingClientsForm() {

                System.out.println("Начинаем ClientsPage/fillingClientsForm");

                // Переключаемся в Фрейм
                switchToIframe();

                // копирую номер клиента для того что бы потом продублировать в плательщика
                WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                "//a[contains(@title, 'обновить значение для Код')]/following-sibling::input")));
                String value = inputField.getAttribute("value");
                System.out.println("Значение поля: " + value);

                TestData.clientValue = value;

                // Вводим "Тип клиента"
                WebElement typeClient = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//a[text()='Тип клиента']/following::select[1]")));
                System.out.println("Ввели значение поля Тип клиента");

                selectDropdownByValue(typeClient, typeCarrierValue);

                // Вводим "по подразделению"

                WebElement NVTBusinessUnit = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[contains(@title, 'элемента По подразделению')]/following-sibling::input")));

                setInputValue(NVTBusinessUnit, NVTBusinessUnitValue);

                WebElement SpanCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[text()='Адрес и контакты']")));
                scrollToElement(SpanCountry);

                SpanCountry.click();

                WebElement Score = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[text()='Счет'][@class='caption-text']")));

                scrollToElement(Score);

                Score.click();

                WebElement City = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                                "//a[contains(@title, 'для элемента Город')]/following-sibling::input")));

                City.click();

                setInputValue(City, CityValue);

                // На будущее, если нужен будет СНГ регистр. номер.
                // WebElement RegNumber =
                // wait.until(ExpectedConditions.visibilityOfElementLocated(
                // By.xpath("//div[@controlname='FTS Registration No']//input")));

                WebElement VATRegistrationNo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                                "//div[@controlname='VAT Registration No.']//input")));

                setInputValue(VATRegistrationNo, RegNumberValue);

                try {

                        WebDriverWait quickWait = new WebDriverWait(driver, Duration.ofSeconds(3));

                        quickWait.until(ExpectedConditions.presenceOfElementLocated(
                                        By.xpath("//div[@title='Почтовые индексы' and contains(text(), 'Почтовые индексы')]")));
                        System.out.println("Всплывающее окно обнаружено");

                        WebElement ButtonInOk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                        "//button[contains(@class, '1876861216 thm-bgcolor')]//span[text()='ОК']")));
                        System.out.println("Кнопка ОК идентифицирована");
                        ButtonInOk.click();
                        System.out.println("Нажата кнопка ОК");
                } catch (Exception e) {

                        System.out.println("Окно не обнаружено");
                }

                System.out.println("Раскрытие списка");
                WebElement BolchePoley = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//button[@aria-label='Счет, Показать больше']")));

                BolchePoley.click();
                System.out.println("Список раскрыт");

                WebElement BilltoCustomerNo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//a[contains(@title, 'для элемента Плательщик')]/following-sibling::input")));

                setInputValue(BilltoCustomerNo, value);

                WebElement Pochta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                "//span[text()='Почтовый адрес']")));

                System.out.println("Почта найдена");

                scrollToElement(Pochta);
                System.out.println("скролл выполнен");

                WebElement Plateji = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                "//img[contains(@alt, 'обязательных полей')]/following-sibling::span[text()='Платежи']")));

                System.out.println("Платежи найдены");

                Plateji.click();

                WebElement FTSPaymenttype = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                                "//div[@controlname='FTS Payment type']//select")));

                selectDropdownByValue(FTSPaymenttype, typeCarrierValue);

                WebElement PaymentTermsCodetrsl = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[@aria-label='Просмотреть или обновить значение для Код условий платежа']")));

                System.out.println("Нашёл код условий платежа");

                PaymentTermsCodetrsl.click();

                System.out.println("кликнул по код условий платежа");

                try {
                        WebElement WindowPaymentTermsCode = wait
                                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                                        "//div[@title='Условия платежа']")));

                        System.out.println("нашёл окно");
                        WebElement ElementPaymentTermsCode = wait.until(
                                        ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='AFT L 10Д']")));

                        System.out.println("нашёл элемент");

                        ElementPaymentTermsCode.click();

                } catch (Exception e) {

                        System.out.println("Не нашёл окно");
                }

                WebElement BlockCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                "//div[@controlname='Blocked']//select")));

                scrollToElement(BlockCustomer);

                selectDropdownByValue(BlockCustomer, BlockCustomerValue);

                WebElement ButtonBack = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//button[@title='Назад']")));

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

                WebElement CDElement = wait.until(ExpectedConditions
                                .elementToBeClickable(By.xpath("//div[@controlname='Credit Limit (LCY)']//input")));

                setInputValue(CDElement, CDElementValue);

                WebElement ButtonBack = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//button[@title='Назад']")));

                ButtonBack.click();

                returnToMainContent();

        }

        public void waitingForTheClientPageToLoad() {

                frameSwitcher.switchToIframe();

                WebElement CardOfClient = wait.until(ExpectedConditions
                                .visibilityOfElementLocated(By.xpath("//span[text()='Карточка клиента']")));

                frameSwitcher.returnToMainContent();

        }

        public void editCardOfClients() {

                frameSwitcher.switchToIframe();

                WebElement EditCardOfClientsButton = wait.until(ExpectedConditions
                                .elementToBeClickable(By.xpath("//i[@data-icon-name='Edit']")));

                EditCardOfClientsButton.click();

                frameSwitcher.returnToMainContent();

        }

}
