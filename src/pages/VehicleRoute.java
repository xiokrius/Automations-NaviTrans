package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehicleRoute {

    private WebDriver driver;

    public VehicleRoute(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSomeButtonInFrame() {

        System.out.println("Начинаем VehicleRoute.");

        // Переключаемся в нужный фрейм
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в фрейм.");

        // Ожидаем появления первой кнопки
        WebElement buttonInFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/div[1]/div[5]/form/main/div[2]/div[2]/div/div/div[1]/span/button/span/i")));
        System.out.println("Нашли кнопку назад.");

        // Кликаем по первой кнопке
        buttonInFrame.click();
        System.out.println("Клик по первой кнопке внутри фрейма выполнен.");
    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("ласт вышел с фрейма, проверка");
    }
}
