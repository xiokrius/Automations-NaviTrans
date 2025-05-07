package com.example.PagesOrder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;

public class VehiclePlanning {

        private WebDriver driver;
        private FrameSwitcher frameSwitcher;

        private String startingVehicleValue = ConfigManager.getProperty("startingVehicleValue");

        public VehiclePlanning(WebDriver driver) {
                this.driver = driver;
                this.frameSwitcher = new FrameSwitcher(driver);
        }

        public void VehiclePlanOpen() {
                System.out.println("Начинаем VehiclePlanning/VehiclePlanOpen.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

                frameSwitcher.switchToIframe();
                System.out.println("Перешли в фрейм.");

                // Находим и заполняем поле для ввода тягача
                WebElement startingVehicle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "//a[contains(@title, 'значение для элемента Тягач')]/following-sibling::input")));
                System.out.println("Нашёл поле для ввода номера тягача.");

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1];", startingVehicle, startingVehicleValue);
                System.out.println("Заполнили стартовую дату через JavaScript: " + startingVehicleValue);

                // Прицеп(пока не нужен, потом можно прокинуть значение)
                WebElement trailerInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "//a[contains(@title, 'значение для элемента Прицеп')]/following-sibling::input")));

                // Водитель, для авторизации
                WebElement driverInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "//a[contains(@title, 'значение для элемента Водитель')]/following-sibling::input")));

                startingVehicle.click();

                driverInput.click();

                try {
                        WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                        "/html/body/div[1]/div[5]/form")));
                        System.out.println("Всплывающее окно обнаружено.");

                        WebElement popupConfirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                        "//div[contains(@class, 'ms-nav-actionbar-container') and contains(@class, 'has-actions')]//button[starts-with(@id, 'b43') and span[text()='ОК']]")));

                        js.executeScript("arguments[0].click();", popupConfirmButton);

                        System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");

                        // Проверка элемента "Внимание! Просрочено плановое ТО"
                        try {
                                WebElement attentionMessage = wait
                                                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                                                "//p[contains(@title, 'Внимание! Просрочено плановое ТО')]")));
                                System.out.println("Обнаружено сообщение: " + attentionMessage.getText());

                                // Нажатие кнопки "ОК"
                                WebElement buttonInOk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                                "//div[contains(@class, 'ms-nav-actionbar-container') and contains(@class, 'has-actions')]//button[contains(@class, '1632124310')]//span[text()='ОК']")));
                                buttonInOk.click();
                                System.out.println("Нажата кнопка 'ОК'.");
                        } catch (Exception innerException) {
                                System.out.println("Ошибка при поиске элемента 'Внимание': "
                                                + innerException.getMessage());
                        }
                } catch (Exception e) {
                        System.out.println("Ошибка при работе со всплывающим окном: " + e.getMessage());
                }
                // Нажимаем кнопку "ОК"
                WebElement vehicleOkButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "//button[contains(@id, 'b4')]/span[text()='ОК']")));
                vehicleOkButton.click();
                System.out.println("Нажата кнопка 'ОК'.");

                frameSwitcher.returnToMainContent();

        }

        public void VehiclePlanOpenCopiedOrder() {
                System.out.println("Начинаем VehiclePlanning/VehiclePlanOpenCopiedOrder.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

                frameSwitcher.switchToIframe();
                System.out.println("Перешли в фрейм.");

                // Находим и заполняем поле для ввода тягача
                WebElement startingVehicle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "//a[contains(@title, 'значение для элемента Тягач')]/following-sibling::input")));
                System.out.println("Нашёл поле для ввода номера тягача.");

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1];", startingVehicle, startingVehicleValue);
                System.out.println("Заполнили стартовую дату через JavaScript: " + startingVehicleValue);

                // Прицеп(пока не нужен, потом можно прокинуть значение)
                WebElement trailerInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "//a[contains(@title, 'значение для элемента Прицеп')]/following-sibling::input")));

                // Водитель, для авторизации
                WebElement driverInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "//a[contains(@title, 'значение для элемента Водитель')]/following-sibling::input")));

                startingVehicle.click();

                driverInput.click();

                try {
                        WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                        "/html/body/div[1]/div[5]/form")));
                        System.out.println("Всплывающее окно обнаружено.");

                        // Ожидание кнопки "Подтвердить"
                        WebElement popupConfirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("/html/body/div[1]/div[6]/form/main/div/div[4]/button[1]")));

                        popupConfirmButton.click();
                        System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");

                        // Проверка элемента "Внимание! Просрочено плановое ТО"
                        try {
                                WebElement attentionMessage = wait
                                                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                                                "//p[contains(@title, 'Внимание! Просрочено плановое ТО')]")));
                                System.out.println("Обнаружено сообщение: " + attentionMessage.getText());

                                // Нажатие кнопки "ОК"
                                WebElement buttonInOk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                                "//div[contains(@class, 'ms-nav-actionbar-container') and contains(@class, 'has-actions')]//button[contains(@class, '1632124310')]//span[text()='ОК']")));
                                buttonInOk.click();
                                System.out.println("Нажата кнопка 'ОК'.");
                        } catch (Exception innerException) {
                                System.out.println("Ошибка при поиске элемента 'Внимание': "
                                                + innerException.getMessage());
                        }
                } catch (Exception e) {
                        System.out.println("Ошибка при работе со всплывающим окном: " + e.getMessage());
                }
                // Нажимаем кнопку "ОК"
                WebElement vehicleOkButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "//button[contains(@id, 'b4')]/span[text()='ОК']")));
                vehicleOkButton.click();
                System.out.println("Нажата кнопка 'ОК'.");

                frameSwitcher.returnToMainContent();
        }

}
