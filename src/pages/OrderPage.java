package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class OrderPage {

    private String TransportrequirementValue = "TENT";
    private String CustomersCodeValue = "CUST-00383";
    private String CarrierValue = "1";

    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSomeButtonInFrame() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions
                .presenceOfElementLocated(
                        By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в фрейм.");

        WebElement Transportrequirement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[3]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[4]/div/input")));
        System.out.println("Нашли элемент input для ввода.");

        ///html/body/div[1]/div[3]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[4]/div/input

        System.out.println("Нашёл кнопку транспорта.");

        WebElement CustomersCode = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[3]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[5]/div/input")));
        System.out.println("Нашли элемент input для ввода.");

        // /html/body/div[1]/div[3]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[5]/div/input

        System.out.println("Нашёл кнопку транспорта2");

        WebElement Carrier = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[3]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[17]/div/select")));
        System.out.println("Нашли элемент input для ввода.");

        System.out.println("Нашёл кнопку транспорта 3");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].value = arguments[1];", Transportrequirement, TransportrequirementValue);
        System.out.println("Заполнили стартовую дату через JavaScript: " + TransportrequirementValue);

        js.executeScript("arguments[0].value = arguments[1];", CustomersCode, CustomersCodeValue);
        System.out.println("Заполнили стартовую дату через JavaScript: " + CustomersCodeValue);

        Select select = new Select(Carrier);

        select.selectByValue(CarrierValue);
        System.out.println("Заполнили select элемент: " + CarrierValue);

        Transportrequirement.click();

    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("ласт вышел с фрейма, проверка");
    }

}
