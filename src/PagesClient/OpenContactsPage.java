package PagesClient;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

public class OpenContactsPage {

        public class RandomUtils {

                // Метод для генерации случайной строки
                public static String generateRandomString(int length) {
                        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; //
                        StringBuilder randomString = new StringBuilder();
                        Random random = new Random();

                        for (int i = 0; i < length; i++) {
                                int index = random.nextInt(chars.length());
                                randomString.append(chars.charAt(index));
                        }

                        return randomString.toString();
                }
        }

        String selectedClient = ConfigManager.getProperty("SelectedClient");

        // test
        public final String NameContactsValue;

        private final WebDriver driver;
        private final WebDriverWait wait;
        private JavascriptExecutor js;

        public OpenContactsPage(WebDriver driver) {

                this.NameContactsValue = RandomUtils.generateRandomString(10); // Длина 10 символов
                System.out.println("Генерированное имя клиента: " + NameContactsValue);

                this.driver = driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                this.js = (JavascriptExecutor) driver;
        }

        private void switchToIframe() {
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.className("designer-client-frame")));
                driver.switchTo().frame(iframe);
        }

        public void selectRecordByText(String recordNumber) {
                try {
                        System.out.println("Ищем запись: '" + recordNumber + "'");

                        // Убеждаемся, что пробелы не мешают
                        recordNumber = recordNumber.trim();

                        WebElement record = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//a[contains(text(),'" + recordNumber + "')]")));

                        System.out.println("Нашли запись: " + recordNumber);

                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", record);
                        record.click();

                        System.out.println("Кликнули по записи: " + recordNumber);
                } catch (Exception e) {
                        System.out.println(" Ошибка: Запись '" + recordNumber + "' не найдена!");
                        e.printStackTrace(); // Выведет полную ошибку
                }
        }

        public void OpenContacts() {

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe();
                System.out.println("Перешли в фрейм.");

                WebElement NameContacts = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@controlname, 'NameTRSL')]//descendant::input[contains(@id, 'ee')]")));
                System.out.println("Нашли поле Название контакта");

                NameContacts.click();

                JavascriptExecutor js = (JavascriptExecutor) driver;
                // controlname="NameTRSL" and id = ee

                js.executeScript("arguments[0].value = arguments[1];", NameContacts, NameContactsValue);
                System.out.println("Заполнили стартовую дату через JavaScript: " + NameContactsValue);

                // в будущем понадобится
                WebElement CompanyNo = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@controlname, 'Company No.')]//descendant::input[contains(@id, 'ee')]")));
                System.out.println("Нашли поле Название контакта");

                // Клик для авторизации
                CompanyNo.click();

                WebElement DopButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("(//button[@title='Показать дополнительные действия'])[2]")));

                System.out.println("Нашли поле Дополнительно");

                DopButton.click();

                WebElement DeystviyaButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("(//button[@aria-label='Действия'])")));

                System.out.println("Нашли поле Дополнительно");

                DeystviyaButton.click();

                WebElement FunkciiButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("(//button[@aria-label='Функции'])")));

                FunkciiButton.click();

                WebElement SozdatkakButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("(//button[@aria-label='Создать как'])")));

                SozdatkakButton.click();

                WebElement ClientButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("(//button[@aria-label='Клиент'])")));

                ClientButton.click();
                // Клиент

                selectRecordByText(selectedClient);

                WebElement PerehodVSchetNet = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[contains(@class, '1632124310')]/span[text()='Нет']")));
                PerehodVSchetNet.click();
                System.out.println("Нажата кнопка 'Нет'.");

                // Нажатие кнопки "ОК"
                WebElement buttonInOk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//div[contains(@class, 'ms-nav-actionbar-container') and contains(@class, 'has-actions')]//button[contains(@class, '1632124310')]//span[text()='ОК']")));
                buttonInOk.click();
        }

}
