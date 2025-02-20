package com.example.PagesOrder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;

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
        private FrameSwitcher frameSwitcher;

        public PageTransp(WebDriver driver) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                this.js = (JavascriptExecutor) driver;
                this.frameSwitcher = new FrameSwitcher(driver);
        }

        private void setInputValue(WebElement element, String value) {
                js.executeScript(
                                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                element, value);
                System.out.println("Заполнили значение: " + value);
        }

        public void OpenOrLoadingLocation() {

                System.out.println("Начинаем PageTransp/OpenOrLoadingLocation");
                // Инициализация WebDriverWait
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Переключаемся в iframe
                frameSwitcher.switchToIframe();

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

                System.out.println("Начинаем обработку элемента 'Груз'");
                try {
                        // Поиск кнопки "Груз"
                        WebElement Cargo = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//span[@role='button' and .//span[text()='Груз']]")));
                        System.out.println("Нашли кнопку 'Груз'.");

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
                        js.executeScript(
                                        "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
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
                                        By.xpath("//a[contains(@aria-label, 'элемента Код товара')]/following::input[contains(@id, 'ee')]")));
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

                frameSwitcher.returnToMainContent();
                System.out.println("Вышли из iframe.");

        }

        public void OpenOrLoadingLocationIteration(int iteration) {

                System.out.println("Начинаем PageTransp/OpenOrLoadingLocation");
                // Инициализация WebDriverWait
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Переключаемся в iframe
                frameSwitcher.switchToIframe();

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

                if (iteration == 0) { // Проверяем условие итерации
                        System.out.println("Начинаем обработку элемента 'Груз' (Итерация: " + iteration + ")");
                        try {
                                // Поиск кнопки "Груз"
                                WebElement Cargo = wait.until(ExpectedConditions.elementToBeClickable(
                                                By.xpath("//span[@role='button' and .//span[text()='Груз']]")));
                                System.out.println("Нашли кнопку 'Груз'.");

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
                                js.executeScript(
                                                "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
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
                } else {
                        // Если не нулевая итерация, логируем пропуск обработки
                        System.out.println("Пропускаем обработку элемента 'Груз' (Итерация: " + iteration + ")");
                }

                try {

                        Actions actions = new Actions(driver);
                        for (int i = 0; i < 2; i++) { // Количество прокруток
                                actions.sendKeys(Keys.PAGE_DOWN).perform();
                                Thread.sleep(400); // Ожидание для подгрузки элементов
                        }
                        System.out.println("Прокрутили страницу вниз с помощью Actions.");

                        WebElement CodeTovara = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//a[contains(@aria-label, 'элемента Код товара')]/following::input[contains(@id, 'ee')]")));
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

                frameSwitcher.returnToMainContent();
                System.out.println("Вышли из iframe.");

        }
}