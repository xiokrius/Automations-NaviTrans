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

                System.out.println("clickzayavkaBy");

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

        // Делаю на коленке, нужно быстро протестить ФХ, тут более 1000 заказов в день
        // выходит, нет времени нормально писать, потом переделать!
        public void CopiedOrders() {

                System.out.println("CopiedOrders");

                // Шаг 1: Ожидаем загрузки фрейма и переключаемся на него
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe(); // Переключаемся в фрейм

                // Шаг 2: КНОПКА ЗАЯВКИ на пейдже страницы заявок
                WebElement DateButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[contains(@title, 'Дата заявки')]")));

                try {
                        DateButton.click();
                        System.out.println("Клик по кнопке выполнен.");
                        Thread.sleep(300);
                        DateButton.click();
                        System.out.println("Клик по кнопке выполнен.");

                } catch (Exception e) {
                        System.out.println("Не удалось кликнуть по кнопке: " + e.getMessage());
                }

                // Указываю full Xpath потому что мне нужно само место,а не элемент, прости
                // господи :)

                try {
                        WebElement OrdersPage = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("/html/body/div[1]/div[2]/form/div/div[2]/main/div[2]/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[1]/td[3]/a")));
                        Thread.sleep(300);
                        OrdersPage.click();
                } catch (Exception e) {
                        System.out.println("Не удалось кликнуть по кнопке: " + e.getMessage());
                }

        }

        public void CreateNewZayavkaCZ() {

                System.out.println("ZayavkaPage/CreateNewZayavkaCZ");

                // Шаг 1: Ожидаем загрузки фрейма и переключаемся на него
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe(); // Переключаемся в фрейм

                // Шаг 2: КНОПКА НОВЫЙ(для создания заказа)
                WebElement Noviy = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//button[@aria-label='Новый']//span[contains(@class, '2dIQkzAbHK18inPTYeiPdF')]")));

                Noviy.click();

                // Кнопка Создать(потом надо дать нормальный нейм)
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[@aria-label='Создать' and contains(@class, 'ms-Button')]")));
                button.click();

        }

        public void NewOrderCreate() {

                System.out.println("Начинаем ZayavkaPage/NewOrderCreate");

                // Шаг 1: Ожидаем загрузки фрейма и переключаемся на него
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe(); // Переключаемся в фрейм;

                // Поле TSGroupCode ПЕРЕДЕЛАТЬ, НУЖНО УКАЗЫВАТЬ ПО ПУТИ, Т.К. ARIA МЕНЯЕТСЯ
                WebElement TSGroupCode = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[contains(@title, 'элемента TS Group code')]/following-sibling::input")));

                WebElement ButtonInOk = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[contains(@id, 'gc')]/span[text()='ОК']")));

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1];", TSGroupCode, TSGroupCodeValue);
                System.out.println("Заполнил ТС групп Код: " + TSGroupCodeValue);

                System.out.println("Клик для инициализации.");

                // Шаг 2: Поле Отдел ПЕРЕДЕЛАТЬ, НУЖНО УКАЗЫВАТЬ ПО ПУТИ, Т.К. ARIA МЕНЯЕТСЯ
                WebElement ButtonNewZayavka = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[contains(@title, 'элемента Отдел')]/following-sibling::input")));

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