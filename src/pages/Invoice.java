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

        WebElement backButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[1]/div[4]/form/main/div[2]/div[2]/div/div/div[1]/span/button/span/i")));

        backButton.click();

    }

}
