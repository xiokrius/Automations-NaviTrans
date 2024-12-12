package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageOpenTransp {

        private WebDriver driver;

        private String startingVehicleValue = "VEHI-02350";

        public PageOpenTransp(WebDriver driver) {
                this.driver = driver;
        }

        // ТУТ ПЕРЕХОЖУ НА СТРАНИЦУ РЕДАКТИРОВАНИЯ ПЕРЕВОЗКИ (NVT Shipment (2002947)) и
        // инициализирую кнопки Перевозки по заказу/Управление/Правка

        public void clickSomeButtonInFrame() {

                System.out.println("Начинаем PageOpentTransp.");

                // Переключаемся в нужный фрейм
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement iframe = wait.until(ExpectedConditions
                                .presenceOfElementLocated(
                                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("Перешли в фрейм.");

                // Ожидаем появления первой кнопки
                WebElement buttonInFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/span/span")));
                System.out.println("Нашли первую кнопку.");

                // Кликаем по первой кнопке
                buttonInFrame.click();
                System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

                WebElement buttonInFrame2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[4]/div[1]/div/div/div/div/div[1]/div/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/button")));

                buttonInFrame2.click();
                System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

                WebElement buttonInFrame3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[4]/div[1]/div/div/div/div/div[2]/div/div/div/div[1]/div/div/div/div/div/div/div[3]/div/button")));

                buttonInFrame3.click();
                System.out.println("Клик по второй кнопке 3 внутри фрейма выполнен.");
        }

        // Тут планирую перевозку

        public void obrabotkaVypustit() {

                System.out.println("Начинаем PageOpentTransp2 Обработка/Выпустить.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement iframe = wait.until(ExpectedConditions
                                .presenceOfElementLocated(
                                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("Перешли в фрейм.");

                WebElement obrabotkaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button/span")));
                System.out.println("Нашли первую кнопку Выпустить.");

                obrabotkaButton.click();

                WebElement VypustitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[1]/div[1]/div/button")));
                System.out.println("Нашли Вторую кнопку, Выпустить .");

                VypustitButton.click();

        }

        public void vehiclePlan() {
                System.out.println("Начинаем PageOpentTransp3 Обработка/План.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement iframe = wait.until(ExpectedConditions
                                .presenceOfElementLocated(
                                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("Перешли в фрейм.");

                try {
                        // Принудительная задержка
                        Thread.sleep(1000);
                } catch (InterruptedException e) {
                        System.err.println("Ошибка при ожидании: " + e.getMessage());
                }

                WebElement obrabotkButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button")));
                System.out.println("Нашли первую кнопку Обработка.");

                obrabotkButton.click();

                WebElement PlanButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[7]/div[1]/div/button")));
                System.out.println("Нашли Вторую кнопку План.");

                PlanButton.click();

        }

        public void PlanOpen() {
                System.out.println("Начинаем PageOpentTransp3 Обработка/План/ОК.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement iframe = wait.until(ExpectedConditions
                                .presenceOfElementLocated(
                                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("Перешли в фрейм.");

                WebElement OpenDrive = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[5]/form/div/main/div[3]/div/button[1]")));
                System.out.println("Нашли Третью кнопку Ок");
                OpenDrive.click();

        }

        public void VehiclePlanOpen() {
                System.out.println("Начинаем PageOpentTransp4: ввод тягача и прицепа по необходимости.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Переход в iframe
                WebElement iframe = wait.until(ExpectedConditions
                                .presenceOfElementLocated(
                                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("Перешли в фрейм.");

                // Находим и заполняем поле для ввода тягача
                WebElement startingVehicle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "/html/body/div[1]/div[5]/form/main/div/div[3]/div[1]/div/div[4]/div[2]/div[2]/div/div/div[3]/div/input")));
                System.out.println("Нашёл поле для ввода номера тягача.");

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1];", startingVehicle, startingVehicleValue);
                System.out.println("Заполнили стартовую дату через JavaScript: " + startingVehicleValue);

                // Нажимаем на элементы
                WebElement autorisedButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "/html/body/div[1]/div[5]/form/main/div/div[3]/div[1]/div/div[4]/div[2]/div[2]/div/div/div[5]/div/input")));

                startingVehicle.click();

                autorisedButton.click();

                System.out.println("Нажали на кнопку 'Авторизация'.");

                // Проверяем наличие всплывающего окна
                try {
                        WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                        "/html/body/div[1]/div[6]/form"))); // XPath окна
                        System.out.println("Всплывающее окно обнаружено.");

                        // Выполняем действия внутри окна
                        WebElement popupConfirmButton = popupWindow.findElement(By.xpath(
                                        "/html/body/div[1]/div[6]/form/main/div/div[4]/button[1]")); // Кнопка
                                                                                                     // подтверждения
                        popupConfirmButton.click();
                        System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");
                } catch (Exception e) {
                        System.out.println("Всплывающее окно не появилось, продолжаем выполнение." + e.getMessage());
                }

                // Нажимаем кнопку "ОК"
                WebElement vehicleOkButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "/html/body/div[1]/div[5]/form/main/div/div[4]/button[1]")));
                vehicleOkButton.click();
                System.out.println("Нажата кнопка 'ОК'.");
        }

        public void returnToMainContent() {
                driver.switchTo().defaultContent();
                System.out.println("ласт вышел с фрейма, проверка");
        }
}