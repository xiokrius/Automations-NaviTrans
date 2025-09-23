package com.example.PagesOrder;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.PagesClient.ClientsPage.TestData;

import com.example.ConfigManager;
import com.example.Environment.BasePage;

public class OrderPage extends BasePage {

        private String transportRequirementValue = ConfigManager.getProperty("transportRequirementValue");
        private String customersCodeValue = ConfigManager.getProperty("customersCodeValue");
        private String carrierValue = ConfigManager.getProperty("carrierValue");
        private String typeCarrierValue = ConfigManager.getProperty("typeCarrierValue");

        public OrderPage(WebDriver driver) {

                super(driver);

        }

        // Метод ДЛЯ ВЫПУСКА ЗАКАЗА ПОСЛЕ ТОГО КАК ВБИЛ ПЛАНОВЫЕ ДАТЫ ПО ПЕРЕВОЗКЕ!
        public void obrabotkaVypustit() {

                System.out.println("Начинаем OrderPage/obrabotkaVypustit");

                switchToIframe();

                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement ProcessingButton = waitAndGetClickableElement(
                                By.xpath("//button[@aria-label=' Обработка' and @data-is-focusable='true']"));

                System.out.println("Нашли первую кнопку Обработка.");

                getJS().executeScript("arguments[0].click();", ProcessingButton);

                // НАШЛИ КНОПКУ ВЫПУСТИТЬ
                WebElement VypustitButton = waitAndGetClickableElement(By.xpath(
                                "//button[@title='Выпустить (Ctrl+F9)']"));
                System.out.println("Нашли Вторую кнопку, Выпустить .");
                // Выпустить (Ctrl+F9) "//button[@title='Выпустить (Ctrl+F9)']"

                getJS().executeScript("arguments[0].click();", VypustitButton);

                returnToMainContent();

        }

        // CОЗДАНИЕ РЕЙСА
        public void vehiclePlan() {

                System.out.println("Начинаем OrderPage/vehiclePlan");

                switchToIframe();

                WebElement SaveSettingsPage = waitAndGetClickableElement(
                                By.xpath("//button[@title='Открыть страницу в режиме только для чтения.']"));

                SaveSettingsPage.click();

                microWait(300);

                SaveSettingsPage.click();

                try {
                        // 2. Улучшенное ожидание и клик для первой кнопки
                        WebElement ProcessingButton = getWait().until(driver -> {
                                WebElement btn = getDriver().findElement(By.xpath(
                                                "//button[@aria-label=' Обработка' and @data-is-focusable='true']"));
                                return (btn.isDisplayed() && btn.isEnabled()) ? btn : null;
                        });

                        System.out.println("Нашли первую кнопку Обработка.");
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",
                                        ProcessingButton);
                        ProcessingButton.click();

                        // 3. Явное ожидание между действиями
                        createWait(20).until(ExpectedConditions.invisibilityOfElementLocated(
                                        By.xpath("//div[contains(@class,'processing-indicator')]")));

                        // 4. Улучшенный клик для второй кнопки
                        WebElement planButton = getWait().until(driver -> {
                                WebElement btn = getDriver().findElement(By.xpath("//button[@aria-label='Plan']"));
                                return (btn.isDisplayed() && btn.isEnabled()) ? btn : null;
                        });

                        System.out.println("Нашли вторую кнопку План.");
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",
                                        planButton);
                        planButton.click();
                        System.out.println("Нажали вторую кнопку План.");

                } finally {
                        returnToMainContent();
                }
        }

        // Нажатие Готов к инвойсированию
        public void readyInInvoicing() {

                System.out.println("Начинаем OrderPage/readyInInvoicing");

                switchToIframe();

                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement ProcessingButton = waitAndGetClickableElement(
                                By.xpath("//*[@aria-label=' Обработка']"));
                System.out.println("Нашли первую кнопку Обработка.");

                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", ProcessingButton);

                // НАШЛИ КНОПКУ Готово к инвойсированию
                WebElement readyInInvoic = waitAndGetClickableElement(By.xpath(
                                "//*[@aria-label='Готов к инвойсированию']"));
                System.out.println("Нашли Вторую кнопку, Готов к инв .");

                readyInInvoic.click();
                try {
                        WebDriverWait shortWait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));

                        WebElement popupWindow = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                        "//p[contains(@title, 'Бухгалтеру будет отправлено уведомление.')]")));
                        System.out.println("Всплывающее окно обнаружено.");

                        WebElement popupConfirmButton = shortWait
                                        .until(ExpectedConditions.elementToBeClickable(By.xpath(
                                                        "(//div[@class='dialog-action-bar'])[2]//button[contains(@class, '1632124310')]//span[text()='ОК']")));

                        getJS().executeScript("arguments[0].click();", popupConfirmButton);

                        Thread.sleep(500);

                } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                }

                try {
                        WebElement ButtonInOk = waitAndGetClickableElement(By.xpath(
                                        "//div[contains(@class, 'ms-nav-actionbar-container') and contains(@class, 'has-actions')]//button[contains(@class, '1632124310')]//span[text()='ОК']"));

                        getJS().executeScript("arguments[0].click();", ButtonInOk);
                } catch (Exception i) {
                        System.out.println("окно 2 не найдено" + i.getMessage());
                }

                returnToMainContent();

        }

        // Создание счёта
        public void obrabotkaSchet() {

                System.out.println("Начинаем OrderPage/obrabotkaSchet");

                switchToIframe();
                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ ОБРАБОТКА
                WebElement ProcessingButton = waitAndGetClickableElement(By.xpath("//*[@aria-label=' Обработка']"));
                System.out.println("Нашли первую кнопку Обработка.");
                ProcessingButton.click();

                // НАШЛИ КНОПКУ Счёт
                WebElement schet = waitAndGetClickableElement(By.xpath("//button[@title='Счет (Shift+Ctrl+F11)']"));

                System.out.println("Нашли Вторую кнопку, Cчёт");

                schet.click();

                returnToMainContent();

        }

        // После обработка/План, нужно выбрать в какой поездке будут изменения
        public void PlanOpen() {
                System.out.println("Начинаем OrderPage/PlanOpen");

                switchToIframe();

                WebElement OpenDrive = waitAndGetClickableElement(By.xpath(
                                "//button//span[text()='ОК']"));
                System.out.println("Нашли Третью кнопку Ок");
                OpenDrive.click();

                returnToMainContent();
        }

        public void PerevozkaInFrameIteration(int iteration) {

                System.out.println("Начинаем OrderPage/PerevozkaInFrame IT (Итерация: " + iteration + ")");

                switchToIframe();

                System.out.println("Перешли в фрейм.");

                if (iteration == 0) {
                        // Клик для раскрытия списка перевозок
                        WebElement buttonInFrame = waitAndGetClickableElement(By.xpath(
                                        "//span[text()='Перевозки по заказу']"));

                        // Кликаем по первой кнопке
                        buttonInFrame.click();
                        System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

                } else {
                        System.out.println(
                                        "Пропускаем клик по первой кнопке внутри фрейма (Итерация: " + iteration + ")");
                }
                // Перевозки/Управление(кнопка)
                WebElement buttonInPerevozkiUpravlenie = waitAndGetClickableElement(By.xpath(
                                "//div[contains(@data-control-id, 'b')]//button[@type='button' and .//span[contains(@aria-label, 'Управление')]]"));
                buttonInPerevozkiUpravlenie.click();
                System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

                // Перевозки/Управление/Создать
                WebElement buttonInPereozkiUpravlenieSozdat = waitAndGetClickableElement(By.xpath(
                                "//div[contains(@data-control-id, 'b')]//button[@type='button' and .//span[contains(@aria-label, 'Создать')]]"));

                buttonInPereozkiUpravlenieSozdat.click();
                System.out.println("Клик по второй кнопке 3 внутри фрейма выполнен.");

                returnToMainContent();
        }

        // Переход в перевозку
        public void PerevozkaInFrame() {

                System.out.println("Начинаем OrderPage/PerevozkaInFrame");

                switchToIframe();
                System.out.println("Перешли в фрейм.");

                // Клик для раскрытия списка перевозок
                WebElement buttonInFrame = waitAndGetClickableElement(By.xpath(
                                "//span[text()='Перевозки по заказу']"));

                // Кликаем по первой кнопке
                buttonInFrame.click();
                System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

                // Перевозки/Управление(кнопка)
                WebElement buttonInPerevozkiUpravlenie = waitAndGetClickableElement(By.xpath(
                                "//div[contains(@data-control-id, 'b')]//button[@type='button' and .//span[contains(@aria-label, 'Управление')]]"));
                buttonInPerevozkiUpravlenie.click();
                System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

                // Перевозки/Управление/Создать
                WebElement buttonInPereozkiUpravlenieSozdat = waitAndGetClickableElement(By.xpath(
                                "//div[contains(@data-control-id, 'b')]//button[@type='button' and .//span[contains(@aria-label, 'Создать')]]"));

                buttonInPereozkiUpravlenieSozdat.click();
                System.out.println("Клик по второй кнопке 3 внутри фрейма выполнен.");

                returnToMainContent();
        }

        // Переход в перевозку
        public void OpenTranspVehicle() {

                System.out.println("Начинаем OrderPage/OpenTranspVehicle");

                switchToIframe();
                System.out.println("Перешли в фрейм.");

                // Клик для раскрытия списка перевозок
                WebElement buttonInFrame = waitAndGetClickableElement(
                                By.xpath("(//span[text()='Перевозки по заказу'])[2]"));

                // Кликаем по первой кнопке
                buttonInFrame.click();

                System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

                // Перевозки/Управление(кнопка)
                WebElement buttonInPerevozkiUpravlenie = waitAndGetClickableElement(By.xpath(
                                "//div[contains(@data-control-id, 'b4')]//button[@type='button' and .//span[contains(@aria-label, 'Управление')]]"));
                buttonInPerevozkiUpravlenie.click();
                System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

                // Перевозки/Управление/Создать
                WebElement buttonInPereozkiUpravlenieSozdat = waitAndGetClickableElement(By.xpath(
                                "//div[contains(@data-control-id, 'b4')]//button[@type='button' and .//span[contains(@aria-label, 'Правка')]]"));

                buttonInPereozkiUpravlenieSozdat.click();
                System.out.println("Клик по второй кнопке 3 внутри фрейма выполнен.");

                returnToMainContent();
        }

        // ИНИЦИАЛИЗИРУЮ ОБРАБОТКУ ДЛЯ ПЕРЕХОДА В РАСХОДЫ И СОЗДАНИЯ ИНТЕРКОМПАНИ

        public void fillIntercompanyForm() {

                switchToIframe();

                System.out.println("Начинаем OrderPage/fillIntercompanyForm");

                WebElement ProcessingButton = waitAndGetVisibleElement(By.xpath(
                                "//*[@aria-label=' Обработка']"));

                ProcessingButton.click();

                System.out.println("Нашли Обработка");

                WebElement rashodiButton = waitAndGetVisibleElement(By.xpath(
                                "//*[@aria-label='Расходы']"));

                rashodiButton.click();

                System.out.println("Перешли в расходы");

                returnToMainContent();

        }

        // Заполнение основной формы в Заявке

        public void fillOrderForm() {

                switchToIframe();

                System.out.println("Начинаем OrderPage/fillOrderForm");

                // Нахождение элементов, явная прогрузка первого элемента

                WebElement transportRequirement = createWait(50)
                                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                                "//a[contains(@title, 'элемента Требования к транспорту')]/following-sibling::input")));

                System.out.println("Нашли ид");

                WebElement customersCode = getDriver().findElement(
                                By.xpath("//a[contains(@title, 'элемента Код Заказчика')]/following-sibling::input"));

                //
                WebElement carrier = getDriver().findElement(
                                By.xpath("//a[text()='Перевозчик']/following::select[1]"));
                // select
                WebElement typeCarrier = getDriver().findElement(
                                By.xpath("//a[text()='Тип перевозки']/following::select[1]"));
                //
                // Заполнение данных
                fillSelectWithJS(transportRequirement, transportRequirementValue);

                fillSelectWithJS(customersCode, customersCodeValue);

                // Выбор значений в select
                selectDropdownByValue(carrier, carrierValue);
                selectDropdownByValue(typeCarrier, typeCarrierValue);

                System.out.println("Форма заполнена.");

                // Инициализация строк
                customersCode.click();
                transportRequirement.click();

                returnToMainContent();

        }

        public void fillOrderFormClients() {

                switchToIframe();

                System.out.println("Начинаем OrderPage/fillOrderForm");

                // Нахождение элементов, явная прогрузка первого элемента

                WebElement transportRequirement = waitAndGetVisibleElement(By.xpath(
                                "//a[contains(@title, 'элемента Требования к транспорту')]/following-sibling::input"));

                System.out.println("Нашли ид");

                WebElement customersCode = getDriver().findElement(
                                By.xpath("//a[contains(@title, 'элемента Код Заказчика')]/following-sibling::input"));
                //
                WebElement carrier = getDriver().findElement(
                                By.xpath("//a[text()='Перевозчик']/following::select[1]"));
                // select
                WebElement typeCarrier = getDriver().findElement(
                                By.xpath("//a[text()='Тип перевозки']/following::select[1]"));
                //
                // Заполнение данных
                fillSelectWithJS(transportRequirement, transportRequirementValue);

                String clientValue = TestData.clientValue;

                fillSelectWithJS(customersCode, clientValue);

                // Выбор значений в select
                selectDropdownByValue(carrier, carrierValue);
                selectDropdownByValue(typeCarrier, typeCarrierValue);

                System.out.println("Форма заполнена.");

                // Инициализация строк
                customersCode.click();
                transportRequirement.click();

                returnToMainContent();

        }

}
