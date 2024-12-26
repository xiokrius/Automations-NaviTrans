package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

public class OpenInvoice {

        private String InputServiceCodeValue = ConfigManager.getProperty("InputServiceCodeValue");
        private String PriceValueValue = ConfigManager.getProperty("PriceValue");

        private WebDriver driver;

        public OpenInvoice(WebDriver driver) {
                this.driver = driver;
        }

        public void OpenServices() {
                System.out.println("Начинаем OpenInInvoice.");

                // Переключаемся в нужный фрейм
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("Перешли в фрейм.");

                // Ожидаем появления инпут-поля с динамическим ID
                WebElement InputServiceCode = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[contains(@id, 'b4') and @role='combobox' and @type='text']")));
                System.out.println("Нашли поле Сервисный код");

                // Заполняем значение через JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                InputServiceCode, InputServiceCodeValue);

                System.out.println("Заполнили значение: " + InputServiceCodeValue);

                // Находим элемент, к которому нужно проскроллить(ЦЕНА)
                WebElement price = driver.findElement(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div/div[2]/table/tbody/tr[1]/td[22]/input"));

                // Прокручиваем к дочернему элементу внутри прокручиваемого окна
                js.executeScript("arguments[0].scrollIntoView({inline: 'center'});", price);
                try {
                        Thread.sleep(1000); // добавить паузу после прокрутки
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                // Логи для проверки состояния элемента
                System.out.println("Price field is displayed: " + price.isDisplayed());
                System.out.println("Price field is enabled: " + price.isEnabled());
                System.out.println("Price field value before input: " + price.getAttribute("value"));

                // Попытка установить значение через JS
                js.executeScript(
                                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));",
                                price,
                                PriceValueValue);

                // Проверка результата
                System.out.println("Price field value after input: " + price.getAttribute("value"));

                WebElement InvoiceInTamojnaButton = driver.findElement(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div/div[2]/table/tbody/tr[1]/td[44]/div/input"));

                // Прокручиваем к дочернему элементу внутри прокручиваемого окна
                js.executeScript("arguments[0].scrollIntoView({inline: 'center'});", InvoiceInTamojnaButton);

                InvoiceInTamojnaButton.click();

        }

        public void returnToMainContent() {
                driver.switchTo().defaultContent();
                System.out.println("ласт вышел с фрейма, проверка");
        }
}
