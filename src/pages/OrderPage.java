package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

        System.out.println("Начинаем PageTransp.");
        // Инициализация WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Переключаемся в iframe
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
        driver.switchTo().frame(iframe);

        WebElement Transportrequirement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[4]/div/input")));
        System.out.println("Нашли элемент input для ввода.");

        WebElement CustomersCode = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[5]/div/input")));
        System.out.println("Нашли элемент input для ввода.");

        WebElement carrier = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
            "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[17]/div/select")));
    System.out.println("Нашли элемент input для ввода.");

        JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value = arguments[1];", Transportrequirement, TransportrequirementValue);
            System.out.println("Заполнили стартовую дату через JavaScript: " + TransportrequirementValue);


            js.executeScript("arguments[0].value = arguments[1];", CustomersCode, CustomersCodeVehicleValue);
            System.out.println("Заполнили стартовую дату через JavaScript: " + startingVehicleValue);


            js.executeScript("arguments[0].value = arguments[1];", startingVehicle, startingVehicleValue);
            System.out.println("Заполнили стартовую дату через JavaScript: " + startingVehicleValue);




}
