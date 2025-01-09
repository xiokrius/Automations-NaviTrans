package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Invoice {

        private WebDriver driver;

        public Invoice(WebDriver driver) {
                this.driver = driver;
        }

        public void fullSchet() {

                System.out.println("Cчёт.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.className("designer-client-frame")));
                driver.switchTo().frame(iframe);

                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ Учёт
                WebElement uchetButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                                "[data-control-id='b54i']")));
                System.out.println("Нашли первую кнопку Учёт.");
                uchetButton.click();

                // НАШЛИ КНОПКУ Учёт
                WebElement uchetFullButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                                "[data-control-id='b54j']")));
                System.out.println("Нашли Вторую кнопку, Учёт .");

                uchetFullButton.click();

                try {
                        WebElement popupWindow = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//p[contains(@id, 'ee') and contains(@title, 'Отсутствует экспорт!')]"))); // XPath
                                                                                                                             // окна
                        System.out.println("Всплывающее окно обнаружено.");

                        WebElement popupConfirmButton = popupWindow
                                        .findElement(By.xpath("//button[contains(@id, 'ip')]/span[text()='Да']")); // Кнопка
                                                                                                                   // подтверждения

                        popupConfirmButton.click();
                        System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");

                        Thread.sleep(1000);

                        WebElement UchetSchetButton = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//button[contains(@id, 'iv')]/span[text()='Да']")));

                        UchetSchetButton.click();

                        Thread.sleep(1000);

                        WebElement PerehodVSchetNet = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//button[contains(@id, 'j2']/span[text()='Нет']")));
                        PerehodVSchetNet.click();

                } catch (Exception e) {
                        System.out.println("Всплывающее окно не появилось, продолжаем выполнение." + e.getMessage());
                }

        }

}
