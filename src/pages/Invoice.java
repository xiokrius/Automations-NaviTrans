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

                System.out.println("Начинаем Invoice/fullSchet");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.className("designer-client-frame")));
                driver.switchTo().frame(iframe);

                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ Учёт
                WebElement uchetButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                                "[data-control-id='b54a']")));
                System.out.println("Нашли первую кнопку Учёт.");
                uchetButton.click();

                // НАШЛИ КНОПКУ Учёт
                WebElement uchetFullButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//button[contains(@role, 'menuitem') and contains(@title, 'F9') and contains(@aria-label, 'Учет')]")));
                System.out.println("Нашли Вторую кнопку, Учёт .");

                uchetFullButton.click();

                // Проверить ID учтённых кнопок, сейчас учёт+ учёт = b54a + b54b, мейби я
                // перепутал или же они динамические,
                // Пока обход этого горячей клавишей
                // role="menuitem", title=(F9), aria-label "Учет"

                try {
                        WebElement popupWindow = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//p[contains(@title, 'Отсутствует экспорт!')]"))); // XPath

                        System.out.println("Всплывающее окно обнаружено.");

                        WebElement popupConfirmButton = popupWindow
                                        .findElement(By.xpath("//button[contains(@id, 'ip')]/span[text()='Да']"));

                        popupConfirmButton.click();
                        System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");

                        WebElement UchetSchetButton = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//button[contains(@id, 'iv')]/span[text()='Да']")));

                        UchetSchetButton.click();

                        WebElement PerehodVSchetNet = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//button[contains(@id, 'j2')]/span[text()='Нет']")));

                        PerehodVSchetNet.click();

                } catch (Exception e) {
                        System.out.println("Всплывающее окно не появилось, продолжаем выполнение." + e.getMessage());
                }

        }

}
