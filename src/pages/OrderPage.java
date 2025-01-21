package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

public class OrderPage {

        private String transportRequirementValue = ConfigManager.getProperty("transportRequirementValue");
        private String customersCodeValue = ConfigManager.getProperty("customersCodeValue");
        private String carrierValue = ConfigManager.getProperty("carrierValue");
        private String typeCarrierValue = ConfigManager.getProperty("typeCarrierValue");

        private final WebDriver driver;
        private final WebDriverWait wait;

        public OrderPage(WebDriver driver) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        // Метод для переключения в iframe
        private void switchToIframe() {
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.className("designer-client-frame")));
                driver.switchTo().frame(iframe);
        }

        // Метод для получения элемента
        private WebElement findElement(By locator) {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }

        // Метод для заполнения значения через JavaScript
        private void fillInputWithJS(WebElement element, String value) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1];", element, value);
                System.out.println("Заполнили поле: " + value);
        }

        // Метод для выбора значения в select
        private void selectDropdownByValue(WebElement selectElement, String value) {
                Select select = new Select(selectElement);
                select.selectByValue(value);
                System.out.println("Выбрали значение в select: " + value);
        }

        // Увеличенный таймаут для повышения отказоустойчивости после ребута, бо сил
        // моих больше нет!!!!!
        private void switchToIframeWithWait() {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45)); // Увеличенный таймаут
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.tagName("designer-client-frame")));
                driver.switchTo().frame(iframe);
                System.out.println("Переключились в iframe c более длительным ожиданием");
        }

        // ТУТ ПЕРЕХОЖУ НА СТРАНИЦУ РЕДАКТИРОВАНИЯ ПЕРЕВОЗКИ (NVT Shipment (2002947)) и
        // инициализирую кнопки Перевозки по заказу/Управление/Правка

        public void clickSomeButtonInFrame() {

                System.out.println("Начинаем OrderPage/clickSomeButtonInFrame");

                // Переключаемся в нужный фрейм
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe();
                System.out.println("Перешли в фрейм.");

                // Клик для раскрытия списка перевозок
                WebElement buttonInFrame = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//span[@title='Перевозки по заказу']")));

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

                System.out.println("Начинаем OrderPage/obrabotkaVypustit");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe();
                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement obrabotkaButton = wait
                                .until(ExpectedConditions
                                                .visibilityOfElementLocated(By.xpath("//*[@aria-label=' Обработка']")));
                System.out.println("Нашли первую кнопку Обработка.");
                obrabotkaButton.click();

                // НАШЛИ КНОПКУ ВЫПУСТИТЬ
                WebElement VypustitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[1]/div[1]/div/button/span")));
                System.out.println("Нашли Вторую кнопку, Выпустить .");

                VypustitButton.click();

        }

        // CОЗДАНИЕ РЕЙСА
        public void vehiclePlan() {

                System.out.println("Начинаем OrderPage/vehiclePlan");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe();
                System.out.println("Перешли в фрейм.");

                try {
                        // Принудительная задержка
                        Thread.sleep(1000);
                } catch (InterruptedException e) {
                        System.err.println("Ошибка при ожидании: " + e.getMessage());
                }
                // Кнопка Обработки
                WebElement obrabotkButton = wait
                                .until(ExpectedConditions
                                                .visibilityOfElementLocated(By.xpath("//*[@aria-label=' Обработка']")));
                System.out.println("Нашли первую кнопку Обработка.");

                obrabotkButton.click();

                // Кнопка Планирования для перехода в планирование рейса
                WebElement PlanButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[7]/div[1]/div/button")));
                System.out.println("Нашли Вторую кнопку План.");

                PlanButton.click();

        }

        // Нажатие Готов к инвойсированию
        public void readyInInvoicing() {

                System.out.println("Начинаем OrderPage/readyInInvoicing");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                switchToIframe();
                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement obrabotkaButton = wait
                                .until(ExpectedConditions
                                                .visibilityOfElementLocated(By.xpath("//*[@aria-label=' Обработка']")));
                System.out.println("Нашли первую кнопку Обработка.");
                obrabotkaButton.click();

                // НАШЛИ КНОПКУ Готово к инвойсированию
                WebElement readyInInvoic = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[7]/div[2]/div/button")));
                System.out.println("Нашли Вторую кнопку, Готов к инв .");

                readyInInvoic.click();

                try {
                        WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                        "//p[contains(@title, 'Бухгалтеру будет отправлено уведомление.')]"))); // XPath
                                                                                                                // окна
                        System.out.println("Всплывающее окно обнаружено.");
                        // Выполняем действия внутри окна
                        Thread.sleep(300);

                        WebElement popupConfirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                        "//button[contains(@class, '1632124310')]/span[text()='ОК']"))); // Кнопка
                        // подтверждения
                        popupConfirmButton.click();
                        System.out.println("Кнопку ОК нашли ");

                        // Добавляем небольшую паузу, чтобы дать время на обновление страницы
                        Thread.sleep(500); // Можно заменить на более сложные ожидания, если нужно

                        // Ожидаем исчезновения старой кнопки
                        wait.until(ExpectedConditions.stalenessOf(popupConfirmButton));

                        // Ожидаем исчезновения старой кнопки и появления новой
                        WebElement ButtonOkSchet = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//button[contains(@class, '1632124310')]/span[text()='ОК']")));
                        ButtonOkSchet.click();
                        System.out.println("Нажата кнопка 'ОК 2, бух-ру отправлено ув-ие'.");

                        System.out.println("Прогон успешен.");
                } catch (Exception e) {
                        System.out.println("Всплывающее окно не появилось, продолжаем выполнение3." + e.getMessage());
                }

        }

        // Создание счёта
        public void obrabotkaSchet() {

                System.out.println("Начинаем OrderPage/obrabotkaSchet");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe();
                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement obrabotkaButton = wait
                                .until(ExpectedConditions
                                                .visibilityOfElementLocated(By.xpath("//*[@aria-label=' Обработка']")));
                System.out.println("Нашли первую кнопку Обработка.");
                obrabotkaButton.click();

                // НАШЛИ КНОПКУ Счёт
                WebElement schet = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[3]/div[1]/div/button")));
                System.out.println("Нашли Вторую кнопку, Выпустить .");

                schet.click();

        }

        // После обработка/План, нужно выбрать в какой поездке будут изменения
        public void PlanOpen() {
                System.out.println("Начинаем OrderPage/PlanOpen");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                switchToIframe();

                System.out.println("Перешли в фрейм.");

                WebElement OpenDrive = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div[4]/form/div/main/div[3]/div/button[1]")));
                System.out.println("Нашли Третью кнопку Ок");
                OpenDrive.click();
        }

        public void PerevozkaInFrame() {

                System.out.println("Начинаем OrderPage/PerevozkaInFrame");

                // Переключаемся в нужный фрейм
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                switchToIframe();
                System.out.println("Перешли в фрейм.");

                // Клик для раскрытия списка перевозок
                WebElement buttonInFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//*[@id=\'b3fe\']/div[2]/div/span")));

                // Кликаем по первой кнопке
                buttonInFrame.click();
                System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

                // Перевозки/Управление(кнопка)
                WebElement buttonInPerevozkiUpravlenie = wait
                                .until(ExpectedConditions
                                                .elementToBeClickable(By.xpath("//*[@data-control-id='b3ff']")));
                buttonInPerevozkiUpravlenie.click();
                System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

                // Перевозки/Управление/Создать
                WebElement buttonInPereozkiUpravlenieSozdat = wait
                                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                                                "//*[@data-control-id=\'b3fh\']")));

                buttonInPereozkiUpravlenieSozdat.click();
                System.out.println("Клик по второй кнопке 3 внутри фрейма выполнен.");
        }

        // ИНИЦИАЛИЗИРУЮ ОБРАБОТКУ ДЛЯ ПЕРЕХОДА В РАСХОДЫ И СОЗДАНИЯ ИНТЕРКОМПАНИ

        public void fillIntercompanyForm() {
                switchToIframe();

                System.out.println("Начинаем OrderPage/fillIntercompanyForm");

                WebElement obrabotkaButton = wait
                                .until(ExpectedConditions
                                                .visibilityOfElementLocated(By.xpath("//*[@aria-label=' Обработка']")));

                obrabotkaButton.click();

                System.out.println("Нашли Обработка");

                WebElement rashodiButton = wait
                                .until(ExpectedConditions
                                                .visibilityOfElementLocated(By.xpath("//*[@aria-label='Расходы']")));

                rashodiButton.click();

                System.out.println("Перешли в расходы");

        }

        // Заполнение основной формы в Заявке

        public void fillOrderForm() {

                // Переход в iframe
                switchToIframe();

                System.out.println("Начинаем OrderPage/fillOrderForm");

                // Нахождение элементов, явная прогрузка первого элемента

                WebElement transportRequirement = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("b3m1ee")));

                System.out.println("Нашли ид");

                WebElement customersCode = driver.findElement(
                                By.xpath("//input[contains(@id, 'b3m2ee')]"));
                //
                WebElement carrier = driver.findElement(
                                By.xpath("//select[contains(@id, 'b3mdee')]"));
                //
                WebElement typeCarrier = driver.findElement(
                                By.xpath("//select[contains(@id, 'b3meee')]"));
                //
                // Заполнение данных
                fillInputWithJS(transportRequirement, transportRequirementValue);
                fillInputWithJS(customersCode, customersCodeValue);

                // Выбор значений в select
                selectDropdownByValue(carrier, carrierValue);
                selectDropdownByValue(typeCarrier, typeCarrierValue);

                System.out.println("Форма заполнена.");

                // Инициализация строк
                customersCode.click();
                transportRequirement.click();

        }

        public void returnToMainContent() {
                driver.switchTo().defaultContent();
                System.out.println("ласт вышел с фрейма, проверка");
        }
}
