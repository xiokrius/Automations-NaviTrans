package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

//ПЕРЕХОД НА ПЕЙДЖ С ЗАКАЗАМИ И ВХОД В ЗАЯВКУ(1 ПОКА ПО СПИСКУ)

public class ZayavkaPage {

        private WebDriver driver;
        private WebDriverWait wait;

        private void switchToIframe() {
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.className("designer-client-frame")));
                driver.switchTo().frame(iframe);
        }

        private String TSGroupCodeValue = ConfigManager.getProperty("TSGroupCodeValue");
        private String ButtonNewZayavkaValue = ConfigManager.getProperty("ButtonNewZayavkaValue");

        public ZayavkaPage(WebDriver driver) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public void clickZayavkaBY() {

                System.out.println("Переход на страницу Заявок и вход в заявку");

                // Шаг 1: Ожидаем загрузки фрейма и переключаемся на него
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe(); // Переключаемся в фрейм

                // Шаг 2: КНОПКА ЗАЯВКИ на пейдже страницы заявок
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "/html/body/div[1]/div[2]/form/div/div[2]/main/div[2]/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[6]/td[3]/a")));

                try {
                        button.click();
                        System.out.println("Клик по кнопке выполнен.");
                } catch (Exception e) {
                        System.out.println("Не удалось кликнуть по кнопке: " + e.getMessage());
                }
        }

        public void CreateNewZayavkaCZ() {

                System.out.println("Переход на страницу Заявок и вход в заявку");

                // Шаг 1: Ожидаем загрузки фрейма и переключаемся на него
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe(); // Переключаемся в фрейм

                // Шаг 2: КНОПКА НОВЫЙ(для создания заказа)
                WebElement Noviy = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//button[@class='ms-Button ms-Button--action ms-Button--command ms-Button--hasMenu command-bar-button--204nhoRojOXj8kwnk9WtH0 thm-bgcolor-1726194350 thm-bgcolor-1295552850--hover thm-font-size-small thm-segoeNormal thm-color-1818861216--not_FCM root-135']")));

                Noviy.click();

                // Кнопка Создать(потом надо дать нормальный нейм)
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//button[@class='ms-Button ms-Button--action ms-Button--command command-bar-button--204nhoRojOXj8kwnk9WtH0 thm-bgcolor-1295552850--hover thm-segoeNormal thm-font-size-small thm-color-1818861216--not_FCM root-135']")));
                button.click();

        }

        public void NewOrderCreate() {

                System.out.println("Переход на страницу Заявок и вход в заявку");

                // Шаг 1: Ожидаем загрузки фрейма и переключаемся на него
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe(); // Переключаемся в фрейм;

                // Поле TSGroupCode
                WebElement TSGroupCode = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "/html/body/div[1]/div[3]/form/main/div/div[3]/div[1]/div/div[4]/div/div/div/div/div/div[2]/div[1]/div/input")));

                WebElement ButtonInOk = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "/html/body/div[1]/div[3]/form/main/div/div[4]/button[1]")));

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1];", TSGroupCode, TSGroupCodeValue);
                System.out.println("Заполнил ТС групп Код: " + TSGroupCodeValue);

                System.out.println("Клик для инициализации.");

                // Шаг 2: Поле Отдел
                WebElement ButtonNewZayavka = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div/div[3]/div[1]/div/div[4]/div/div/div/div/div/div[2]/div[2]/div/input")));

                js.executeScript("arguments[0].value = arguments[1];", ButtonNewZayavka, ButtonNewZayavkaValue);
                System.out.println("Заполнили отдел: " + ButtonNewZayavkaValue);

                ButtonNewZayavka.click();
                System.out.println("Клик для инициализации.");

                ButtonInOk.click();

        }

        public void returnToMainContent() {
                driver.switchTo().defaultContent();
                System.out.println("Возвращаемся в основной контент страницы.");
        }

}