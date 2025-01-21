package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BackPageOrder {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BackPageOrder(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
    }

    public void BackPage() {

        System.out.println("Начинаем BackPageOrder/BackPage");

        // Переключаемся в нужный фрейм
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        switchToIframe();
        System.out.println("Перешли в фрейм.");

        // Клик для раскрытия списка перевозок
        WebElement buttonInFrame = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@title='Перевозки по заказу']")));

        // Кликаем по первой кнопке
        buttonInFrame.click();
        System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

        // Перевозки/Управление(кнопка)
        WebElement buttonInPerevozkiUpravlenie = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[4]/div[1]/div/div/div/div/div[1]/div/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/button")));

        buttonInPerevozkiUpravlenie.click();
        System.out.println("Клик по второй кнопке внутри фрейма выполнен.");
    }
}
