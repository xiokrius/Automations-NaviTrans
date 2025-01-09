package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

public class VehiclePlanning {

    private WebDriver driver;

    private String startingVehicleValue = ConfigManager.getProperty("startingVehicleValue");

    public VehiclePlanning(WebDriver driver) {
        this.driver = driver;
    }

    public void VehiclePlanOpen() {
        System.out.println("Начинаем VehiclePlanning/VehiclePlanOpen.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Переход в iframe
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в фрейм.");

        // Находим и заполняем поле для ввода тягача
        WebElement startingVehicle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[4]/form/main/div/div[3]/div[1]/div/div[4]/div[2]/div[2]/div/div/div[3]/div/input")));
        System.out.println("Нашёл поле для ввода номера тягача.");

        ///html/body/div[1]/div[4]/form/main/div/div[3]/div[1]/div/div[4]/div[2]/div[2]/div/div/div[3]/div/input

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", startingVehicle, startingVehicleValue);
        System.out.println("Заполнили стартовую дату через JavaScript: " + startingVehicleValue);

        // Нажимаем на элементы
        WebElement autorisedButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[4]/form/main/div/div[3]/div[1]/div/div[4]/div[2]/div[2]/div/div/div[5]/div/input")));

        startingVehicle.click();

        autorisedButton.click();

        try {

            startingVehicle.click();

        } catch (Exception i) {

            System.out.println("Окно сработало раньше");

            System.out.println("Нажали на кнопку 'Авторизация'.");

            // Проверяем наличие всплывающего окна в случае если тягач уже задействован в
            // поездке
            try {
                // Проверка на всплывающее окно
                WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "/html/body/div[1]/div[5]/form"))); // XPath окна
                System.out.println("Всплывающее окно обнаружено.");

                // Выполняем действия внутри окна
                WebElement popupConfirmButton = popupWindow.findElement(By.xpath(
                        "/html/body/div[1]/div[5]/form/main/div/div[4]/button[1]")); // Кнопка
                                                                                     // подтверждения
                popupConfirmButton.click();
                System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");

            } catch (Exception e) {
                System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
            }
        }
        // Нажимаем кнопку "ОК"
        WebElement vehicleOkButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[4]/form/main/div/div[4]/button[1]")));
        vehicleOkButton.click();
        System.out.println("Нажата кнопка 'ОК'.");
    }

}
