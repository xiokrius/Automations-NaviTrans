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
        private FrameSwitcher frameSwitcher;

        public OrderPage(WebDriver driver) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                this.frameSwitcher = new FrameSwitcher(driver);
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

        // ФУНКЦИЯ ДЛЯ ВЫПУСКА ЗАКАЗА ПОСЛЕ ТОГО КАК ВБИЛ ПЛАНОВЫЕ ДАТЫ ПО ПЕРЕВОЗКЕ!
        public void obrabotkaVypustit() {

                System.out.println("Начинаем OrderPage/obrabotkaVypustit");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                frameSwitcher.switchToIframe();
                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement obrabotkaButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//*[@aria-label=' Обработка' and @data-is-focusable='true']")));

                System.out.println("Нашли первую кнопку Обработка.");
                obrabotkaButton.click();

                // НАШЛИ КНОПКУ ВЫПУСТИТЬ
                WebElement VypustitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//button[@title='Выпустить (Ctrl+F9)']")));
                System.out.println("Нашли Вторую кнопку, Выпустить .");
                // Выпустить (Ctrl+F9) "//button[@title='Выпустить (Ctrl+F9)']"
                VypustitButton.click();

                frameSwitcher.returnToMainContent();

        }

        // CОЗДАНИЕ РЕЙСА
        public void vehiclePlan() {

                System.out.println("Начинаем OrderPage/vehiclePlan");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                frameSwitcher.switchToIframe();
                System.out.println("Перешли в фрейм.");

                // Кнопка Обработки
                WebElement obrabotkaButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//*[@aria-label=' Обработка' and @data-is-focusable='true']")));

                System.out.println("Нашли первую кнопку Обработка.");

                obrabotkaButton.click();

                // Кнопка Планирования для перехода в планирование рейса
                WebElement PlanButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//button[@aria-label='Plan']")));
                System.out.println("Нашли Вторую кнопку План.");

                PlanButton.click();

                frameSwitcher.returnToMainContent();

        }

        // Нажатие Готов к инвойсированию
        public void readyInInvoicing() {

                System.out.println("Начинаем OrderPage/readyInInvoicing");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                frameSwitcher.switchToIframe();

                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement obrabotkaButton = wait
                                .until(ExpectedConditions
                                                .elementToBeClickable(By.xpath("//*[@aria-label=' Обработка']")));
                System.out.println("Нашли первую кнопку Обработка.");
                obrabotkaButton.click();

                // НАШЛИ КНОПКУ Готово к инвойсированию
                WebElement readyInInvoic = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//*[@aria-label='Готов к инвойсированию']")));
                System.out.println("Нашли Вторую кнопку, Готов к инв .");

                readyInInvoic.click();
                try {
                        WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                        "//p[contains(@title, 'Бухгалтеру будет отправлено уведомление.')]")));
                        System.out.println("Всплывающее окно обнаружено.");

                        WebElement popupConfirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                        "(//div[@class='dialog-action-bar'])[2]//button[contains(@class, '1632124310')]//span[text()='ОК']")));

                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript("arguments[0].click();", popupConfirmButton);

                        Thread.sleep(500);

                        WebElement ButtonInOk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                        "//div[contains(@class, 'ms-nav-actionbar-container') and contains(@class, 'has-actions')]//button[contains(@class, '1632124310')]//span[text()='ОК']")));

                        js.executeScript("arguments[0].click();", ButtonInOk);

                } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                }

                frameSwitcher.returnToMainContent();

        }

        // Создание счёта
        public void obrabotkaSchet() {

                System.out.println("Начинаем OrderPage/obrabotkaSchet");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                frameSwitcher.switchToIframe();
                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement obrabotkaButton = wait
                                .until(ExpectedConditions
                                                .visibilityOfElementLocated(By.xpath("//*[@aria-label=' Обработка']")));
                System.out.println("Нашли первую кнопку Обработка.");
                obrabotkaButton.click();

                // НАШЛИ КНОПКУ Счёт
                WebElement schet = wait.until(ExpectedConditions
                                .visibilityOfElementLocated(By.xpath("//button[@title='Счет (Shift+Ctrl+F11)']")));

                System.out.println("Нашли Вторую кнопку, Выпустить .");

                schet.click();

                frameSwitcher.returnToMainContent();

        }

        // После обработка/План, нужно выбрать в какой поездке будут изменения
        public void PlanOpen() {
                System.out.println("Начинаем OrderPage/PlanOpen");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                frameSwitcher.switchToIframe();

                System.out.println("Перешли в фрейм.");

                WebElement OpenDrive = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//button//span[text()='ОК']")));
                System.out.println("Нашли Третью кнопку Ок");
                OpenDrive.click();

                frameSwitcher.returnToMainContent();
        }

        public void PerevozkaInFrameIteration(int iteration) {

                System.out.println("Начинаем OrderPage/PerevozkaInFrame IT (Итерация: " + iteration + ")");

                // Переключаемся в нужный фрейм
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                frameSwitcher.switchToIframe();
                System.out.println("Перешли в фрейм.");

                if (iteration == 0) {
                        // Клик для раскрытия списка перевозок
                        WebElement buttonInFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                        "//span[text()='Перевозки по заказу']")));

                        // Кликаем по первой кнопке
                        buttonInFrame.click();
                        System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

                } else {
                        System.out.println(
                                        "Пропускаем клик по первой кнопке внутри фрейма (Итерация: " + iteration + ")");
                }
                // Перевозки/Управление(кнопка)
                WebElement buttonInPerevozkiUpravlenie = wait
                                .until(ExpectedConditions
                                                .elementToBeClickable(By.xpath(
                                                                "//div[contains(@data-control-id, 'b3')]//button[@type='button' and .//span[contains(@aria-label, 'Управление')]]")));
                buttonInPerevozkiUpravlenie.click();
                System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

                // Перевозки/Управление/Создать
                WebElement buttonInPereozkiUpravlenieSozdat = wait
                                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                                                "//div[contains(@data-control-id, 'b3')]//button[@type='button' and .//span[contains(@aria-label, 'Создать')]]")));

                buttonInPereozkiUpravlenieSozdat.click();
                System.out.println("Клик по второй кнопке 3 внутри фрейма выполнен.");

                frameSwitcher.returnToMainContent();
        }

        // Переход в перевозку
        public void PerevozkaInFrame() {

                System.out.println("Начинаем OrderPage/PerevozkaInFrame");

                // Переключаемся в нужный фрейм
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                frameSwitcher.switchToIframe();
                System.out.println("Перешли в фрейм.");

                // Клик для раскрытия списка перевозок
                WebElement buttonInFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//span[text()='Перевозки по заказу']")));

                // Кликаем по первой кнопке
                buttonInFrame.click();
                System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

                // Перевозки/Управление(кнопка)
                WebElement buttonInPerevozkiUpravlenie = wait
                                .until(ExpectedConditions
                                                .elementToBeClickable(By.xpath(
                                                                "//div[contains(@data-control-id, 'b3')]//button[@type='button' and .//span[contains(@aria-label, 'Управление')]]")));
                buttonInPerevozkiUpravlenie.click();
                System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

                // Перевозки/Управление/Создать
                WebElement buttonInPereozkiUpravlenieSozdat = wait
                                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                                                "//div[contains(@data-control-id, 'b3')]//button[@type='button' and .//span[contains(@aria-label, 'Создать')]]")));

                buttonInPereozkiUpravlenieSozdat.click();
                System.out.println("Клик по второй кнопке 3 внутри фрейма выполнен.");

                frameSwitcher.returnToMainContent();
        }

        // Переход в перевозку
        public void OpenTranspVehicle() {

                System.out.println("Начинаем OrderPage/OpenTranspVehicle");

                // Переключаемся в нужный фрейм
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                frameSwitcher.switchToIframe();
                System.out.println("Перешли в фрейм.");

                // Клик для раскрытия списка перевозок
                WebElement buttonInFrame = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("(//span[text()='Перевозки по заказу'])[2]")));

                // Кликаем по первой кнопке
                buttonInFrame.click();
                System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

                // Перевозки/Управление(кнопка)
                WebElement buttonInPerevozkiUpravlenie = wait
                                .until(ExpectedConditions
                                                .elementToBeClickable(By.xpath(
                                                                "//div[contains(@data-control-id, 'b4')]//button[@type='button' and .//span[contains(@aria-label, 'Управление')]]")));
                buttonInPerevozkiUpravlenie.click();
                System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

                // Перевозки/Управление/Создать
                WebElement buttonInPereozkiUpravlenieSozdat = wait
                                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                                                "//div[contains(@data-control-id, 'b4')]//button[@type='button' and .//span[contains(@aria-label, 'Правка')]]")));

                buttonInPereozkiUpravlenieSozdat.click();
                System.out.println("Клик по второй кнопке 3 внутри фрейма выполнен.");

                frameSwitcher.returnToMainContent();
        }

        // ИНИЦИАЛИЗИРУЮ ОБРАБОТКУ ДЛЯ ПЕРЕХОДА В РАСХОДЫ И СОЗДАНИЯ ИНТЕРКОМПАНИ

        public void fillIntercompanyForm() {
                frameSwitcher.switchToIframe();

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

                frameSwitcher.returnToMainContent();

        }

        // Заполнение основной формы в Заявке

        public void fillOrderForm() {

                frameSwitcher.switchToIframe();

                System.out.println("Начинаем OrderPage/fillOrderForm");

                // Нахождение элементов, явная прогрузка первого элемента

                WebElement transportRequirement = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                                "//a[contains(@title, 'элемента Требования к транспорту')]/following-sibling::input")));

                System.out.println("Нашли ид");

                WebElement customersCode = driver.findElement(
                                By.xpath("//a[contains(@title, 'элемента Код Заказчика')]/following-sibling::input"));
                //
                WebElement carrier = driver.findElement(
                                By.xpath("//a[text()='Перевозчик']/following::select[1]"));
                // select
                WebElement typeCarrier = driver.findElement(
                                By.xpath("//a[text()='Тип перевозки']/following::select[1]"));
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

                frameSwitcher.returnToMainContent();

        }

}
