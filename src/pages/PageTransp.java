package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

// ЗАПОЛНЕНИЕ ПЕРЕМЕЩЕНИЯ (ПОКА ТОЛЬКО ПЛАНОВЫЕ ДАТЫ)
public class PageTransp {

        private String CodeTovaraValue = ConfigManager.getProperty("CodeTovaraValue");
        private String PlanningLoadingDateValue = ConfigManager.getProperty("PlanningLoadingDateValue");
        private String PlanningUnloadingDateValue = ConfigManager.getProperty("PlanningUnloadingDateValue");
        private String OpenLoadingLocationValue = ConfigManager.getProperty("OpenLoadingLocationValue");
        private String OpenUnloadingLocationValue = ConfigManager.getProperty("OpenUnloadingLocationValue");

        private String NumberOfShipmentValue = ConfigManager.getProperty("NumberOfShipmentValue");
        private String TheWholeCargoValue = ConfigManager.getProperty("TheWholeCargoValue");
        private String CargoVolumeValue = ConfigManager.getProperty("CargoVolumeValue");
        private String TheTemperatureOfTheCargoFROMValue = ConfigManager
                        .getProperty("TheTemperatureOfTheCargoFROMValue");
        private String TheTemperatureOfTheCargoIsUpToValue = ConfigManager
                        .getProperty("TheTemperatureOfTheCargoIsUpToValue");

        private String startingDateValue = ConfigManager.getProperty("startingDateValue");
        private String unloadDateValue = ConfigManager.getProperty("unloadDateValue");

        private WebDriver driver;
        private WebDriverWait wait;
        private JavascriptExecutor js;

        public PageTransp(WebDriver driver) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                this.js = (JavascriptExecutor) driver;
        }

        // Метод для переключения в iframe
        private void switchToIframe() {
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.className("designer-client-frame")));
                driver.switchTo().frame(iframe);
        }

        private void setInputValue(WebElement element, String value) {
                js.executeScript(
                                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                element, value);
                System.out.println("Заполнили значение: " + value);
        }

        public void fillDateFieldInFrame() {

                System.out.println("Начинаем PageTransp/fillDateFieldInFrame");
                // Инициализация WebDriverWait
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Переключаемся в iframe
                switchToIframe();

                // Ожидаем элемент input для заполнения
                WebElement startingDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "/html/body/div[1]/div[5]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div[6]/div/input")));
                System.out.println("Нашли элемент input для ввода.");

                WebElement unloadDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "/html/body/div[1]/div[5]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[6]/div/input")));
                System.out.println("Нашли элемент input для ввода.");

                WebElement back = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                "/html/body/div[1]/div[5]/form/main/div[2]/div[2]/div/div/div[1]/span/button/span/i")));
                System.out.println("Нашёл выход для ввода.");

                ///html/body/div[1]/div[5]/form/main/div[2]/div[2]/div/div/div[1]/span/button/span/i

                // Установка значения в поле input через JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1];", startingDate, startingDateValue);
                System.out.println("Заполнили стартовую дату через JavaScript: " + startingDateValue);

                startingDate.click();
                System.out.println("Клик по первой дате для инициализации.");

                js.executeScript("arguments[0].value = arguments[1];", unloadDate, unloadDateValue);
                System.out.println("Заполнили дату выгрузки через JavaScript: " + unloadDateValue);

                unloadDate.click();
                System.out.println("Кликнул для инициализации");

                back.click();
                System.out.println("Кликнул назад");

                // Проверяем, что значения успешно установлены
                @SuppressWarnings("deprecation")
                String currentValue = startingDate.getAttribute("value");
                if (startingDateValue.equals(currentValue)) {
                        System.out.println("Проверка: стартовая дата установлена корректно.");
                } else {
                        System.out.println("Ошибка: стартовая дата не установлена корректно.");
                }

                @SuppressWarnings("deprecation")
                String currentValue2 = unloadDate.getAttribute("value");
                if (unloadDateValue.equals(currentValue2)) {
                        System.out.println("Проверка: дата выгрузки установлена корректно.");
                } else {
                        System.out.println("Ошибка: дата выгрузки не установлена корректно.");
                }

        }

        public void setStartingDateValue(String startingDateValue) {
                this.startingDateValue = startingDateValue;
        }

        public void setUnloadDateValue(String unloadDateValue) {
                this.unloadDateValue = unloadDateValue;
        }

        public void OpenOrLoadingLocation() {

                System.out.println("Начинаем PageTransp/OpenOrLoadingLocation");
                // Инициализация WebDriverWait
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Переключаемся в iframe
                switchToIframe();

                // id и прочее не статично, пришлось по DOM идти
                WebElement OpenLoadingLocation = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//a[contains(@aria-label, 'Код адреса погрузки') and @role='button']/following-sibling::input[contains(@id, 'ee') and @role='combobox']")));
                System.out.println("Нашли поле ввода Код адреса погрузки v111");

                // id и прочее не статично, пришлось по DOM идти
                WebElement OpenUnloadingLocation = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[contains(@aria-label, 'Код адреса разгрузки') and @role='button']/following-sibling::input[contains(@id, 'ee') and @role='combobox']")));
                System.out.println("Нашли поле ввода Код адреса разгрузки");

                // План Дата загрузки id и прочее не статично, пришлось по DOM идти
                WebElement PlanningLoadingDate = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[contains(@aria-label, 'элемент выбора даты для Дата загрузки')]/following::input[contains(@id, 'ee') and @role='combobox']")));
                System.out.println("Нашли поле ввода даты загрузки");

                // План Дата разгрузки id и прочее не статично, пришлось по DOM идти
                WebElement PlanningUnloadingDate = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[contains(@aria-label, 'элемент выбора даты для План Дата разгрузки')]/following::input[contains(@id, 'ee') and @role='combobox']")));
                System.out.println("Нашли поле ввода Даты разгрузки");

                JavascriptExecutor js = (JavascriptExecutor) driver;

                try {
                        // Поиск кнопки "Груз"
                        WebElement Cargo = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//span[@role='button' and .//span[text()='Груз']]")));
                        System.out.println("Нашли кнопку Груз.");

                        // Прокрутка страницы вниз, чтобы элемент стал видимым
                        for (int i = 0; i < 10; i++) {
                                js.executeScript("window.scrollBy(0, 900);"); // Скроллим вниз
                                Thread.sleep(100); // Пауза для стабильности
                                if (Cargo.isDisplayed()) {
                                        System.out.println("Элемент 'Груз' стал видимым.");
                                        break;
                                }

                        }

                        // Прокрутка к элементу с использованием scrollIntoView для точного
                        // позиционирования
                        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
                                        Cargo);
                        System.out.println("Прокрутили к элементу 'Груз'.");

                        // Клик по элементу
                        wait.until(ExpectedConditions.elementToBeClickable(Cargo));
                        js.executeScript("arguments[0].click();", Cargo);
                        System.out.println("Клик по элементу 'Груз' выполнен.");
                        Thread.sleep(400);
                } catch (Exception e) {
                        System.out.println("Ошибка при взаимодействии с элементом 'Груз': " + e.getMessage());
                }

                try {

                        Actions actions = new Actions(driver);
                        for (int i = 0; i < 2; i++) { // Количество прокруток
                                actions.sendKeys(Keys.PAGE_DOWN).perform();
                                Thread.sleep(400); // Ожидание для подгрузки элементов
                        }
                        System.out.println("Прокрутили страницу вниз с помощью Actions.");

                        WebElement CodeTovara = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//a[contains(@aria-label, 'элемента Код товара')]/following::input[contains(@id, 'xee')]")));
                        System.out.println("Нашли поле ввода Кода товара");

                        js.executeScript("arguments[0].value = arguments[1];", CodeTovara, CodeTovaraValue);
                        System.out.println("Заполнили Код Товара: " + CodeTovaraValue);

                        CodeTovara.click();
                        System.out.println("Авторизация");

                        js.executeScript("arguments[0].value = arguments[1];", OpenLoadingLocation,
                                        OpenLoadingLocationValue);
                        System.out.println("Заполнили плановую локацию загрузки: " + OpenLoadingLocationValue);

                        js.executeScript("arguments[0].value = arguments[1];", OpenUnloadingLocation,
                                        OpenUnloadingLocationValue);
                        System.out.println("Заполнили плановую локацию выгрузки: " + OpenUnloadingLocationValue);

                        js.executeScript("arguments[0].value = arguments[1];", PlanningLoadingDate,
                                        PlanningLoadingDateValue);
                        System.out.println("Заполнили плановую стартовую дату: " + PlanningLoadingDateValue);

                        js.executeScript("arguments[0].value = arguments[1];", PlanningUnloadingDate,
                                        PlanningUnloadingDateValue);
                        System.out.println("Заполнили дату выгрузки: " + PlanningUnloadingDateValue);

                        System.out.println("Заполнили поля");

                } catch (Exception e) {
                        System.out.println("Ошибка при взаимодействии с элементом 'Груз': " + e.getMessage());
                }

                // Кол-во груза input поле
                WebElement NumberOfShipment = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[contains(@controlname, 'Quantity')]/input[contains(@id, 'ee')]")));

                System.out.println("1");
                // aria-labelledby="column_header_b3tj"
                // id = b3szee

                // Вес груза input поле
                WebElement TheWholeCargo = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[contains(@controlname, 'Order Weight')]//descendant::input[contains(@id, 'ee')]")));
                System.out.println("НАШЛИ ВЕС ГРУЗА");
                // Order Weight
                // Объём груза input поле
                WebElement CargoVolume = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[contains(@controlname, 'Order Volume')]//descendant::input[contains(@id, 'ee')]")));
                // id = b3t2ee/ column_header_b3tg
                System.out.println("3");
                // Температура груза ОТ input поле
                WebElement TheTemperatureOfTheCargoFROM = driver.findElement(
                                By.xpath("//td[contains(@controlname, 'Temperature from')]//descendant::input[contains(@id, 'ee')]"));
                System.out.println("4");

                js.executeScript("arguments[0].value = arguments[1];", NumberOfShipment, NumberOfShipmentValue);
                System.out.println("Количество груза" + NumberOfShipmentValue);

                js.executeScript("arguments[0].value = arguments[1];", TheWholeCargo, TheWholeCargoValue);
                System.out.println("Заполнили вес заказа: " + TheWholeCargoValue);

                js.executeScript("arguments[0].value = arguments[1];", CargoVolume, CargoVolumeValue);
                System.out.println("Заполнили обьём заказа: " + CargoVolumeValue);

                WebElement scrollContainer = driver
                                .findElement(By.xpath("(//div[contains(@class, 'freeze-pane-scrollbar')])[7]"));

                // Прокрутить контейнер вправо до элемента
                js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth;", scrollContainer);
                System.out.println("Прокрутили контейнер вправо.");

                // Температура груза ДО input поле
                WebElement TheTemperatureOfTheCargoIsUpTo = wait.until(
                                ExpectedConditions.visibilityOfElementLocated(
                                                By.xpath("//td[contains(@controlname, 'Temperature to')]//descendant::input[contains(@id, 'ee')]")));
                System.out.println("5");
                // Выход на пэйдж заявки
                WebElement BackPage = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[contains(@data-is-focusable, 'true') and contains(@title, 'Назад')]")));
                System.out.println("6");

                setInputValue(TheTemperatureOfTheCargoIsUpTo, TheTemperatureOfTheCargoIsUpToValue);

                System.out.println("Заполнили температуру ДО: " + TheTemperatureOfTheCargoIsUpToValue);

                setInputValue(TheTemperatureOfTheCargoFROM, TheTemperatureOfTheCargoFROMValue);

                System.out.println("Заполнили температуру После: " + TheTemperatureOfTheCargoFROMValue);

                TheTemperatureOfTheCargoFROM.click();
                TheTemperatureOfTheCargoIsUpTo.click();
                OpenLoadingLocation.click();
                OpenUnloadingLocation.click();
                PlanningLoadingDate.click();
                PlanningUnloadingDate.click();
                OpenLoadingLocation.click();
                NumberOfShipment.click();
                TheWholeCargo.click();
                CargoVolume.click();

                BackPage.click();

                // column_header_b3t9
                // Возвращаемся в основной контекст
                driver.switchTo().defaultContent();
                System.out.println("Вышли из iframe.");

        }
}

// Погрузка aria-labelledby="b3v3lbl"

// разгрузка

// Для входа в заявку