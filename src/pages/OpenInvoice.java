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

                // Кликаем по полю
                InputServiceCode.click();

                // Заполняем значение через JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                InputServiceCode, InputServiceCodeValue);
                System.out.println("Заполнили значение: " + InputServiceCodeValue);

                System.out.println("Завершили OpenServices.");

        }

        public void returnToMainContent() {
                driver.switchTo().defaultContent();
                System.out.println("ласт вышел с фрейма, проверка");
        }
}
