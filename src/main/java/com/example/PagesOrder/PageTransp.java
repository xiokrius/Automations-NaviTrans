package com.example.PagesOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.example.ConfigManager;
import com.example.Environment.BasePage;

// ЗАПОЛНЕНИЕ ПЕРЕМЕЩЕНИЯ (ПОКА ТОЛЬКО ПЛАНОВЫЕ ДАТЫ)
public class PageTransp extends BasePage {

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

        public PageTransp(WebDriver driver) {

                super(driver);

        }

        public void OpenOrLoadingLocation() {

                System.out.println("Начинаем PageTransp/OpenOrLoadingLocation");

                // Переключаемся в iframe
                switchToIframe();

                // id и прочее не статично, пришлось по DOM идти
                WebElement OpenLoadingLocation = waitAndGetClickableElement(
                                By.xpath(
                                                "//a[contains(@aria-label, 'Код адреса погрузки') and @role='button']/following-sibling::input[contains(@id, 'ee') and @role='combobox']"));
                System.out.println("Нашли поле ввода Код адреса погрузки v111");

                // id и прочее не статично, пришлось по DOM идти
                WebElement OpenUnloadingLocation = waitAndGetClickableElement(
                                By.xpath("//a[contains(@aria-label, 'Код адреса разгрузки') and @role='button']/following-sibling::input[contains(@id, 'ee') and @role='combobox']"));
                System.out.println("Нашли поле ввода Код адреса разгрузки");

                // План Дата загрузки id и прочее не статично, пришлось по DOM идти
                WebElement PlanningLoadingDate = waitAndGetClickableElement(
                                By.xpath("//a[contains(@aria-label, 'элемент выбора даты для Дата загрузки')]/following::input[contains(@id, 'ee') and @role='combobox']"));
                System.out.println("Нашли поле ввода даты загрузки");

                // План Дата разгрузки id и прочее не статично, пришлось по DOM идти
                WebElement PlanningUnloadingDate = waitAndGetClickableElement(
                                By.xpath("//a[contains(@aria-label, 'элемент выбора даты для План Дата разгрузки')]/following::input[contains(@id, 'ee') and @role='combobox']"));
                System.out.println("Нашли поле ввода Даты разгрузки");

                System.out.println("Начинаем обработку элемента 'Груз'");
                try {
                        // Поиск кнопки "Груз"
                        WebElement Cargo = waitAndGetClickableElement(
                                        By.xpath("//span[@role='button' and .//span[text()='Груз']]"));
                        System.out.println("Нашли кнопку 'Груз'.");

                        // Прокрутка страницы вниз, чтобы элемент стал видимым
                        for (int i = 0; i < 10; i++) {
                                getJS().executeScript("window.scrollBy(0, 900);"); // Скроллим вниз
                                Thread.sleep(100); // Пауза для стабильности
                                if (Cargo.isDisplayed()) {
                                        System.out.println("Элемент 'Груз' стал видимым.");
                                        break;
                                }
                        }

                        // Прокрутка к элементу с использованием scrollIntoView для точного
                        // позиционирования
                        getJS().executeScript(
                                        "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
                                        Cargo);
                        System.out.println("Прокрутили к элементу 'Груз'.");

                        // Клик по элементу
                        Cargo.click();

                        System.out.println("Клик по элементу 'Груз' выполнен.");
                        Thread.sleep(400);
                } catch (Exception e) {
                        System.out.println("Ошибка при взаимодействии с элементом 'Груз': " + e.getMessage());
                }

                try {

                        Actions actions = new Actions(getDriver());
                        for (int i = 0; i < 2; i++) { // Количество прокруток
                                actions.sendKeys(Keys.PAGE_DOWN).perform();
                                Thread.sleep(400); // Ожидание для подгрузки элементов
                        }
                        System.out.println("Прокрутили страницу вниз с помощью Actions.");

                        WebElement CodeTovara = waitAndGetClickableElement(
                                        By.xpath("//a[contains(@aria-label, 'элемента Код товара')]/following::input[contains(@id, 'ee')]"));
                        System.out.println("Нашли поле ввода Кода товара");

                        getJS().executeScript("arguments[0].value = arguments[1];", CodeTovara, CodeTovaraValue);
                        System.out.println("Заполнили Код Товара: " + CodeTovaraValue);

                        CodeTovara.click();
                        System.out.println("Авторизация");

                        getJS().executeScript("arguments[0].value = arguments[1];", OpenLoadingLocation,
                                        OpenLoadingLocationValue);
                        System.out.println("Заполнили плановую локацию загрузки: " + OpenLoadingLocationValue);

                        getJS().executeScript("arguments[0].value = arguments[1];", OpenUnloadingLocation,
                                        OpenUnloadingLocationValue);
                        System.out.println("Заполнили плановую локацию выгрузки: " + OpenUnloadingLocationValue);

                        getJS().executeScript("arguments[0].value = arguments[1];", PlanningLoadingDate,
                                        PlanningLoadingDateValue);
                        System.out.println("Заполнили плановую стартовую дату: " + PlanningLoadingDateValue);

                        getJS().executeScript("arguments[0].value = arguments[1];", PlanningUnloadingDate,
                                        PlanningUnloadingDateValue);
                        System.out.println("Заполнили дату выгрузки: " + PlanningUnloadingDateValue);

                        System.out.println("Заполнили поля");

                } catch (Exception e) {
                        System.out.println("Ошибка при взаимодействии с элементом 'Груз': " + e.getMessage());
                }

                // Кол-во груза input поле
                WebElement NumberOfShipment = waitAndGetClickableElement(
                                By.xpath("//td[contains(@controlname, 'Quantity')]/input[contains(@id, 'ee')]"));

                // Вес груза input поле
                WebElement TheWholeCargo = waitAndGetClickableElement(
                                By.xpath("//td[contains(@controlname, 'Order Weight')]//descendant::input[contains(@id, 'ee')]"));
                System.out.println("НАШЛИ ВЕС ГРУЗА");
                // Order Weight
                // Объём груза input поле
                WebElement CargoVolume = waitAndGetClickableElement(
                                By.xpath("//td[contains(@controlname, 'Order Volume')]//descendant::input[contains(@id, 'ee')]"));
                // id = b3t2ee/ column_header_b3tg
                System.out.println("3");
                // Температура груза ОТ input поле
                WebElement TheTemperatureOfTheCargoFROM = getDriver().findElement(
                                By.xpath("//td[contains(@controlname, 'Temperature from')]//descendant::input[contains(@id, 'ee')]"));
                System.out.println("4");

                getJS().executeScript("arguments[0].value = arguments[1];", NumberOfShipment, NumberOfShipmentValue);
                System.out.println("Количество груза" + NumberOfShipmentValue);

                getJS().executeScript("arguments[0].value = arguments[1];", TheWholeCargo, TheWholeCargoValue);
                System.out.println("Заполнили вес заказа: " + TheWholeCargoValue);

                getJS().executeScript("arguments[0].value = arguments[1];", CargoVolume, CargoVolumeValue);
                System.out.println("Заполнили обьём заказа: " + CargoVolumeValue);

                WebElement scrollContainer = getDriver()
                                .findElement(By.xpath("(//div[contains(@class, 'freeze-pane-scrollbar')])[7]"));

                // Прокрутить контейнер вправо до элемента
                getJS().executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth;", scrollContainer);
                System.out.println("Прокрутили контейнер вправо.");

                // Температура груза ДО input поле
                WebElement TheTemperatureOfTheCargoIsUpTo = waitAndGetVisibleElement(
                                By.xpath("//td[contains(@controlname, 'Temperature to')]//descendant::input[contains(@id, 'ee')]"));
                System.out.println("5");
                // Выход на пэйдж заявки
                WebElement BackPage = waitAndGetClickableElement(
                                By.xpath("//button[contains(@data-is-focusable, 'true') and contains(@title, 'Назад')]"));
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

                returnToMainContent();

                returnToMainContent();

                System.out.println("Вышли из iframe.");

        }

        public void OpenOrLoadingLocationIteration(int iteration) {

                System.out.println("Начинаем PageTransp/OpenOrLoadingLocation");
                // Инициализация WebDriverWait

                // Переключаемся в iframe
                switchToIframe();

                // id и прочее не статично, пришлось по DOM идти
                WebElement OpenLoadingLocation = waitAndGetClickableElement(
                                By.xpath(
                                                "//a[contains(@aria-label, 'Код адреса погрузки') and @role='button']/following-sibling::input[contains(@id, 'ee') and @role='combobox']"));
                System.out.println("Нашли поле ввода Код адреса погрузки v111");

                // id и прочее не статично, пришлось по DOM идти
                WebElement OpenUnloadingLocation = waitAndGetClickableElement(
                                By.xpath("//a[contains(@aria-label, 'Код адреса разгрузки') and @role='button']/following-sibling::input[contains(@id, 'ee') and @role='combobox']"));
                System.out.println("Нашли поле ввода Код адреса разгрузки");

                // План Дата загрузки id и прочее не статично, пришлось по DOM идти
                WebElement PlanningLoadingDate = waitAndGetClickableElement(
                                By.xpath("//a[contains(@aria-label, 'элемент выбора даты для Дата загрузки')]/following::input[contains(@id, 'ee') and @role='combobox']"));
                System.out.println("Нашли поле ввода даты загрузки");

                // План Дата разгрузки id и прочее не статично, пришлось по DOM идти
                WebElement PlanningUnloadingDate = waitAndGetClickableElement(
                                By.xpath("//a[contains(@aria-label, 'элемент выбора даты для План Дата разгрузки')]/following::input[contains(@id, 'ee') and @role='combobox']"));
                System.out.println("Нашли поле ввода Даты разгрузки");

                if (iteration == 0) { // Проверяем условие итерации
                        System.out.println("Начинаем обработку элемента 'Груз' (Итерация: " + iteration + ")");
                        try {
                                // Поиск кнопки "Груз"
                                WebElement Cargo = waitAndGetClickableElement(
                                                By.xpath("//span[@role='button' and .//span[text()='Груз']]"));
                                System.out.println("Нашли кнопку 'Груз'.");

                                // Прокрутка страницы вниз, чтобы элемент стал видимым
                                for (int i = 0; i < 10; i++) {
                                        getJS().executeScript("window.scrollBy(0, 900);"); // Скроллим вниз
                                        Thread.sleep(100); // Пауза для стабильности
                                        if (Cargo.isDisplayed()) {
                                                System.out.println("Элемент 'Груз' стал видимым.");
                                                break;
                                        }
                                }

                                // Прокрутка к элементу с использованием scrollIntoView для точного
                                // позиционирования
                                getJS().executeScript(
                                                "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
                                                Cargo);
                                System.out.println("Прокрутили к элементу 'Груз'.");

                                // Клик по элементу

                                Cargo.click();

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

                        Actions actions = new Actions(getDriver());
                        for (int i = 0; i < 2; i++) { // Количество прокруток
                                actions.sendKeys(Keys.PAGE_DOWN).perform();
                                Thread.sleep(400); // Ожидание для подгрузки элементов
                        }
                        System.out.println("Прокрутили страницу вниз с помощью Actions.");

                        WebElement CodeTovara = waitAndGetClickableElement(
                                        By.xpath("//a[contains(@aria-label, 'элемента Код товара')]/following::input[contains(@id, 'ee')]"));
                        System.out.println("Нашли поле ввода Кода товара");

                        getJS().executeScript("arguments[0].value = arguments[1];", CodeTovara, CodeTovaraValue);
                        System.out.println("Заполнили Код Товара: " + CodeTovaraValue);

                        CodeTovara.click();
                        System.out.println("Авторизация");

                        getJS().executeScript("arguments[0].value = arguments[1];", OpenLoadingLocation,
                                        OpenLoadingLocationValue);
                        System.out.println("Заполнили плановую локацию загрузки: " + OpenLoadingLocationValue);

                        getJS().executeScript("arguments[0].value = arguments[1];", OpenUnloadingLocation,
                                        OpenUnloadingLocationValue);
                        System.out.println("Заполнили плановую локацию выгрузки: " + OpenUnloadingLocationValue);

                        getJS().executeScript("arguments[0].value = arguments[1];", PlanningLoadingDate,
                                        PlanningLoadingDateValue);
                        System.out.println("Заполнили плановую стартовую дату: " + PlanningLoadingDateValue);

                        getJS().executeScript("arguments[0].value = arguments[1];", PlanningUnloadingDate,
                                        PlanningUnloadingDateValue);
                        System.out.println("Заполнили дату выгрузки: " + PlanningUnloadingDateValue);

                        System.out.println("Заполнили поля");

                } catch (Exception e) {
                        System.out.println("Ошибка при взаимодействии с элементом 'Груз': " + e.getMessage());
                }

                // Кол-во груза input поле
                WebElement NumberOfShipment = waitAndGetClickableElement(
                                By.xpath("//td[contains(@controlname, 'Quantity')]/input[contains(@id, 'ee')]"));

                System.out.println("1");
                // aria-labelledby="column_header_b3tj"
                // id = b3szee

                // Вес груза input поле
                WebElement TheWholeCargo = waitAndGetClickableElement(
                                By.xpath("//td[contains(@controlname, 'Order Weight')]//descendant::input[contains(@id, 'ee')]"));
                System.out.println("НАШЛИ ВЕС ГРУЗА");
                // Order Weight
                // Объём груза input поле
                WebElement CargoVolume = waitAndGetClickableElement(
                                By.xpath("//td[contains(@controlname, 'Order Volume')]//descendant::input[contains(@id, 'ee')]"));
                // id = b3t2ee/ column_header_b3tg
                System.out.println("3");
                // Температура груза ОТ input поле
                WebElement TheTemperatureOfTheCargoFROM = getDriver().findElement(
                                By.xpath("//td[contains(@controlname, 'Temperature from')]//descendant::input[contains(@id, 'ee')]"));
                System.out.println("4");

                getJS().executeScript("arguments[0].value = arguments[1];", NumberOfShipment, NumberOfShipmentValue);
                System.out.println("Количество груза" + NumberOfShipmentValue);

                getJS().executeScript("arguments[0].value = arguments[1];", TheWholeCargo, TheWholeCargoValue);
                System.out.println("Заполнили вес заказа: " + TheWholeCargoValue);

                getJS().executeScript("arguments[0].value = arguments[1];", CargoVolume, CargoVolumeValue);
                System.out.println("Заполнили обьём заказа: " + CargoVolumeValue);

                WebElement scrollContainer = getDriver()
                                .findElement(By.xpath("(//div[contains(@class, 'freeze-pane-scrollbar')])[7]"));

                // Прокрутить контейнер вправо до элемента
                getJS().executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth;", scrollContainer);
                System.out.println("Прокрутили контейнер вправо.");

                // Температура груза ДО input поле
                WebElement TheTemperatureOfTheCargoIsUpTo = waitAndGetVisibleElement(
                                By.xpath("//td[contains(@controlname, 'Temperature to')]//descendant::input[contains(@id, 'ee')]"));
                System.out.println("5");
                // Выход на пэйдж заявки
                WebElement BackPage = waitAndGetClickableElement(
                                By.xpath("//button[contains(@data-is-focusable, 'true') and contains(@title, 'Назад')]"));
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

                returnToMainContent();
                System.out.println("Вышли из iframe.");

        }
}