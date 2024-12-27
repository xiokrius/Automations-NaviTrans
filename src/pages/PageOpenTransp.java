package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

public class PageOpenTransp {

        private WebDriver driver;

        private String startingVehicleValue = ConfigManager.getProperty("startingVehicleValue");

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

                // Клик для раскрытия списка перевозок
                WebElement buttonInFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/span/span")));

                // Кликаем по первой кнопке
                buttonInFrame.click();
                System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

                // Перевозки/Управление(кнопка)
                WebElement buttonInPerevozkiUpravlenie = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                                "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[4]/div[1]/div/div/div/div/div[1]/div/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/button")));

                buttonInPerevozkiUpravlenie.click();
                System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

                // Перевозки/Управление/Правка
                WebElement buttonInPereozkiUpravleniePravka = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                                "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[4]/div[1]/div/div/div/div/div[2]/div/div/div/div[1]/div/div/div/div/div/div/div[3]/div/button")));

                buttonInPereozkiUpravleniePravka.click();
                System.out.println("Клик по второй кнопке 3 внутри фрейма выполнен.");
        }

        // ФУНКЦИЯ ДЛЯ ВЫПУСКА ЗАКАЗА ПОСЛЕ ТОГО КАК ВБИЛ ПЛАНОВЫЕ ДАТЫ ПО ПЕРЕВОЗКЕ!
        public void obrabotkaVypustit() {

                System.out.println("Начинаем Обработка/Выпустить.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement iframe = wait.until(ExpectedConditions
                                .presenceOfElementLocated(
                                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement obrabotkaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button/span")));
                System.out.println("Нашли первую кнопку Выпустить.");
                obrabotkaButton.click();
                ///html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button/span

                // НАШЛИ КНОПКУ ВЫПУСТИТЬ
                WebElement VypustitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[1]/div[1]/div/button/span")));
                System.out.println("Нашли Вторую кнопку, Выпустить .");

                VypustitButton.click();

        }

        // CОЗДАНИЕ РЕЙСА
        public void vehiclePlan() {

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
                // Кнопка Обработки
                WebElement obrabotkButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button")));
                System.out.println("Нашли первую кнопку Обработка.");

                obrabotkButton.click();

                // Кнопка Планирования для перехода в планирование рейса
                WebElement PlanButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[7]/div[1]/div/button")));
                System.out.println("Нашли Вторую кнопку План.");

                ///html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[7]/div[1]/div/button

                PlanButton.click();

        }

        // Нажатие Готов к инвойсированию
        public void readyInInvoicing() {

                System.out.println("Начинаем Обработка/Готов к инвойсированию.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement iframe = wait.until(ExpectedConditions
                                .presenceOfElementLocated(
                                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement obrabotkaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button/span")));
                System.out.println("Нашли первую кнопку Выпустить.");
                obrabotkaButton.click();

                // НАШЛИ КНОПКУ Готово к инвойсированию
                WebElement readyInInvoic = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[7]/div[2]/div/button")));
                System.out.println("Нашли Вторую кнопку, Готов к инв .");

                readyInInvoic.click();

                try {
                        WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                        "/html/body/div[1]/div[4]/form/div/main"))); // XPath окна
                        System.out.println("Всплывающее окно обнаружено.");
                        // Выполняем действия внутри окна
                        WebElement popupConfirmButton = popupWindow.findElement(By.xpath(
                                        "/html/body/div[1]/div[4]/form/div/main/div[3]/div/button")); // Кнопка
                                                                                                      // подтверждения
                        popupConfirmButton.click();
                        System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");
                } catch (Exception e) {
                        System.out.println("Всплывающее окно не появилось, продолжаем выполнение." + e.getMessage());
                }

        }

        // Создание счёта
        public void obrabotkaSchet() {

                System.out.println("Начинаем Обработка/Выпустить.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement iframe = wait.until(ExpectedConditions
                                .presenceOfElementLocated(
                                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement obrabotkaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button/span")));
                System.out.println("Нашли первую кнопку Выпустить.");
                obrabotkaButton.click();

                // НАШЛИ КНОПКУ Счёт
                WebElement schet = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[3]/div[1]/div/button")));
                System.out.println("Нашли Вторую кнопку, Выпустить .");

                schet.click();

        }

        // После обработка/План, нужно выбрать в какой поездке будут изменения
        public void PlanOpen() {
                System.out.println("Обработка/План/ОК.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement iframe = wait.until(ExpectedConditions
                                .presenceOfElementLocated(
                                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("Перешли в фрейм.");

                WebElement OpenDrive = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[4]/form/div/main/div[3]/div/button[1]")));
                System.out.println("Нашли Третью кнопку Ок");
                OpenDrive.click();

        }

        public void VehiclePlanOpen() {
                System.out.println("ввод тягача и прицепа по необходимости.");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

                // Переход в iframe
                WebElement iframe = wait.until(ExpectedConditions
                                .presenceOfElementLocated(
                                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
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

                startingVehicle.click();

                System.out.println("Нажали на кнопку 'Авторизация'.");

                // Проверяем наличие всплывающего окна в случае если тягач уже задействован в
                // поездке
                try {
                        WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                        "/html/body/div[1]/div[5]/form"))); // XPath окна
                        System.out.println("Всплывающее окно обнаружено.");
                        ///html/body/div[1]/div[5]/form/main/div/div[1]/div[1]
                        // Выполняем действия внутри окна
                        WebElement popupConfirmButton = popupWindow.findElement(By.xpath(
                                        "/html/body/div[1]/div[5]/form/main/div/div[4]/button[1]")); // Кнопка
                                                                                                     // подтверждения
                        popupConfirmButton.click();
                        System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");
                } catch (Exception e) {
                        System.out.println("Всплывающее окно не появилось, продолжаем выполнение." + e.getMessage());
                }

                // Нажимаем кнопку "ОК"
                WebElement vehicleOkButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "/html/body/div[1]/div[4]/form/main/div/div[4]/button[1]")));
                vehicleOkButton.click();
                System.out.println("Нажата кнопка 'ОК'.");
        }

        public void returnToMainContent() {
                driver.switchTo().defaultContent();
                System.out.println("ласт вышел с фрейма, проверка");
        }
}
