package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ServiceInvoice {

    private WebDriver driver;

    public ServiceInvoice(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSomeButtonInService() {

        System.out.println("Начинаем ServiCeInvoice");

        // Переключаемся в нужный фрейм
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions
                .presenceOfElementLocated(
                        By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в фрейм.");

        // Ожидаем появления первой кнопки
        WebElement buttonInObrabotka = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button")));
        System.out.println("Нашли Кнопку Обработка.");

        // /html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button

        buttonInObrabotka.click();

        WebElement buttonInService = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[1]/div[2]/div/button")));
        System.out.println("Нашли Кнопку Сервис");

        buttonInService.click();
        System.out.println("Клик по второй кнопке внутри фрейма выполнен.");
    }

}
