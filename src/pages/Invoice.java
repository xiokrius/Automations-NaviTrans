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
                WebElement iframe = wait.until(ExpectedConditions
                                .presenceOfElementLocated(
                                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ Учёт
                WebElement uchetButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button")));
                System.out.println("Нашли первую кнопку Учёт.");
                uchetButton.click();

                // НАШЛИ КНОПКУ Cчёт номер ручной
                WebElement uchetFullButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[1]/div/div/button")));
                System.out.println("Нашли Вторую кнопку, Счёт номер ручной .");

                uchetFullButton.click();

                try {
                        WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                        "/html/body/div[1]/div[5]/form/div/main"))); // XPath окна
                        System.out.println("Всплывающее окно обнаружено.");
                        ///html/body/div[1]/div[5]/form/main/div/div[1]/div[1]
                        // Выполняем действия внутри окна
                        WebElement popupConfirmButton = popupWindow.findElement(By.xpath(
                                        "/html/body/div[1]/div[5]/form/div/main/div[3]/div/button[1]")); // Кнопка
                        // подтверждения
                        popupConfirmButton.click();
                        System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");

                        Thread.sleep(1000);

                        WebElement UchetSchetButton = wait.until(ExpectedConditions
                                        .presenceOfElementLocated(By.xpath(
                                                        "/html/body/div[1]/div[5]/form/div/main/div[3]/div/button[1]")));

                        UchetSchetButton.click();

                        Thread.sleep(1000);

                        WebElement PerehodVSchetNet = wait.until(ExpectedConditions
                                        .presenceOfElementLocated(By.xpath(
                                                        "/html/body/div[1]/div[5]/form/div/main/div[3]/div/button[2]")));
                        PerehodVSchetNet.click();

                } catch (Exception e) {
                        System.out.println("Всплывающее окно не появилось, продолжаем выполнение." + e.getMessage());
                }

        }

}
