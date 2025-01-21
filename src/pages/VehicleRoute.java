package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

public class VehicleRoute {

    private String ActualStartingDateValue = ConfigManager.getProperty("ActualStartingDateValue");
    private String ActualStartingTimeValue = ConfigManager.getProperty("ActualStartingTimeValue");
    private String ActualEndingDateValue = ConfigManager.getProperty("ActualEndingDateValue");
    private String ActualEndingTimeValue = ConfigManager.getProperty("ActualEndingTimeValue");
    private String SupplyKmValue = ConfigManager.getProperty("SupplyKmValue");
    private String StartingKmValue = ConfigManager.getProperty("StartingKmValue");
    private String EndingKmValue = ConfigManager.getProperty("EndingKmValue");

    private WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public VehicleRoute(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    private void scrollToElementHorizontally(WebElement scrollContainer, WebElement targetElement) {
        js.executeScript(
                "arguments[0].scrollLeft = arguments[0].scrollLeft + arguments[1].getBoundingClientRect().left;",
                scrollContainer, targetElement);
    }

    private void setInputValue(WebElement element, String value) {
        js.executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                element, value);
        System.out.println("Заполнили значение: " + value);
    }

    public void clickSomeButtonInFrame() {

        System.out.println("Начинаем VehicleRoute/clickSomeButtonInFrame.");

        // Переключаемся в нужный фрейм
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в фрейм.");

        WebElement ActualStartingDate = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(
                        "//td[contains(@controlname, 'FTS_Actual Starting Date')]//descendant::input[contains(@id, 'ee')]")));
        System.out.println("1.");
        WebElement ActualStartingTime = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(
                        "//td[contains(@controlname, 'FTS_Actual Start Time')]//descendant::input[contains(@id, 'ee')]")));
        System.out.println("2");
        WebElement ActualEndingDate = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(
                        "//td[contains(@controlname, 'FTS_Actual Ending Date')]//descendant::input[contains(@id, 'ee')]")));
        System.out.println("Перешли в фрейм.");
        WebElement ActualEndingTime = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(
                        "//td[contains(@controlname, 'FTS_Actual End Time')]//descendant::input[contains(@id, 'ee')]")));

        WebElement scrollContainer = driver.findElement(
                By.xpath("(//div[contains(@class, 'freeze-pane-scrollbar')])[7]"));

        scrollToElementHorizontally(scrollContainer, ActualEndingTime);

        setInputValue(ActualStartingDate, ActualStartingDateValue);

        setInputValue(ActualStartingTime, ActualStartingTimeValue);

        setInputValue(ActualEndingDate, ActualEndingDateValue);

        setInputValue(ActualEndingTime, ActualEndingTimeValue);

        ActualStartingDate.click();
        ActualEndingTime.click();
        ActualEndingDate.click();
        ActualEndingTime.click();

        WebElement SupplyKm = driver.findElement(
                By.xpath("//td[contains(@controlname, 'Supply Km')]//descendant::input[contains(@id, 'ee')]"));

        WebElement StartingKm = driver.findElement(
                By.xpath("//td[contains(@controlname, 'Starting Km')]//descendant::input[contains(@id, 'ee')]"));

        WebElement EndingKm = driver.findElement(
                By.xpath("//td[contains(@controlname, 'Ending Km')]//descendant::input[contains(@id, 'ee')]"));

        scrollToElementHorizontally(scrollContainer, EndingKm);

        setInputValue(SupplyKm, SupplyKmValue);

        setInputValue(StartingKm, StartingKmValue);

        setInputValue(EndingKm, EndingKmValue);

        SupplyKm.click();
        StartingKm.click();
        EndingKm.click();

        try {

            Thread.sleep(500);
            // Ожидаем появления первой кнопки
            WebElement BackPage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(@data-is-focusable, 'true') and contains(@title, 'Назад')]")));
            System.out.println("Нашли кнопку назад.");

            // Кликаем по первой кнопке
            BackPage.click();
            System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("ласт вышел с фрейма, проверка");
    }
}
