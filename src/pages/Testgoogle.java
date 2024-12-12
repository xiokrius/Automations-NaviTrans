package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testgoogle {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='rso']/div[1]/div/div/div/div[1]/div/div/span/a")
    private WebElement Testgooglepage;

    public Testgoogle(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Сделаем метод нестатическим, чтобы использовать нестатические поля и методы
    public void clickTestgooglepage() {

        System.out.println("Ожидание кнопки заявки...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(Testgooglepage));

        System.out.println("Кнопка заявки доступна. Выполняем клик...");
        Testgooglepage.click();

        System.out.println("Клик по заявке выполнен.");
    }
}