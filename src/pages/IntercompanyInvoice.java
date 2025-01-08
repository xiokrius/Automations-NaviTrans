package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import java.time.Duration;

public class IntercompanyInvoice {

    private WebDriver driver;
    private WebDriverWait wait;

    public IntercompanyInvoice(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Инициализация wait
    }

    private void switchToIframe(String iframeXpath) {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(iframeXpath)));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в iframe.");
    }

    public void fillIntercompanyForm() {
        switchToIframe("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe");
        WebElement obrabotkaButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label=' Обработка']")));

        obrabotkaButton.click();

        WebElement rashodiButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label='Расходы']")));

        rashodiButton.click();

    }
}
