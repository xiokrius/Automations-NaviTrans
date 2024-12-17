package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// ЗАПОЛНЕНИЕ ПЕРЕМЕЩЕНИЯ (ПОКА ТОЛЬКО ПЛАНОВЫЕ ДАТЫ)
public class PageTransp {

    private String CodeTovaraValue = "ITEM-00002";
    private String PlanningLoadingDateValue = "01.12.2024";
    private String PlanningUnloadingDateValue = "25.12.2024";
    private String OpenLoadingLocationValue = "220112";
    private String OpenUnloadingLocationValue = "CP 06180 DE CACERES,";

    private String NumberOfShipmentValue = "1";
    private String TheWholeCargoValue = "1";
    private String CargoVolumeValue = "1";
    private String TheTemperatureOfTheCargoFROMValue = "-";
    private String TheTemperatureOfTheCargoIsUpToValue = "-";

    private WebDriver driver;

    private String startingDateValue = "09.12.2024";
    private String unloadDateValue = "21.12.2024";

    public PageTransp(WebDriver driver) {
        this.driver = driver;
    }

    public void fillDateFieldInFrame() {

        System.out.println("Начинаем PageTransp.");
        // Инициализация WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Переключаемся в iframe
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
        driver.switchTo().frame(iframe);

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

        System.out.println("Начинаем PageTransp.");
        // Инициализация WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Переключаемся в iframe
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
        driver.switchTo().frame(iframe);

        WebElement OpenLoadingLocation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//label[text()='Код адреса погрузки']/following-sibling::input")));
        System.out.println("Нашли поле ввода Код адреса погрузки");

        WebElement OpenUnloadingLocation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@aria-labelledby='b3vblbl']")));
        System.out.println("Нашли поле ввода Код адреса погрузки");

        // План Дата загрузки
        WebElement PlanningLoadingDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@aria-labelledby='b3vjlbl']")));
        System.out.println("Нашли поле ввода даты загрузки");

        // План Дата разагрузки
        WebElement PlanningUnloadingDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@aria-labelledby='b3vrlbl']")));
        System.out.println("Нашли поле ввода Даты разгрузки");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // НУЖНО ЗАМЕНИТЬ прямые xpath!!!
        try {
            // Поиск кнопки "Груз"
            WebElement Cargo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                    "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div/span")));
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
            js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", Cargo);
            System.out.println("Прокрутили к элементу 'Груз'.");

            // Клик по элементу
            wait.until(ExpectedConditions.elementToBeClickable(Cargo));
            js.executeScript("arguments[0].click();", Cargo);
            System.out.println("Клик по элементу 'Груз' выполнен.");
        } catch (Exception e) {
            System.out.println("Ошибка при взаимодействии с элементом 'Груз': " + e.getMessage());
        }

        // надо перенеймить переменную ТУТ ЗАКОНЧИЛ 16.12.
        try {
            WebElement CodeTovara = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                    "/html/body/div[1]/div[4]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[3]/div[3]/div/div/div/div[2]/table/tbody/tr[1]/td[5]/input")));
            System.out.println("Нашли поле ввода Кода товара");

            for (int i = 0; i < 10; i++) {
                js.executeScript("window.scrollBy(0, 900);"); // Скроллим вниз
                Thread.sleep(100); // Пауза для стабильности
                if (CodeTovara.isDisplayed()) {
                    System.out.println("Элемент 'Груз' стал видимым.");
                    break;
                }
            }

            js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", CodeTovara);
            System.out.println("Прокрутили к элементу 'Код товара'.");

            js.executeScript("arguments[0].value = arguments[1];", CodeTovara, CodeTovaraValue);
            System.out.println("Заполнили Код Товара: " + CodeTovaraValue);

            js.executeScript("arguments[0].value = arguments[1];", OpenLoadingLocation, OpenLoadingLocationValue);
            System.out.println("Заполнили плановую локацию загрузки: " + OpenLoadingLocationValue);

            js.executeScript("arguments[0].value = arguments[1];", OpenUnloadingLocation, OpenUnloadingLocationValue);
            System.out.println("Заполнили плановую локацию выгрузки: " + OpenUnloadingLocationValue);

            js.executeScript("arguments[0].value = arguments[1];", PlanningLoadingDate, PlanningLoadingDateValue);
            System.out.println("Заполнили плановую стартовую дату: " + PlanningLoadingDateValue);

            js.executeScript("arguments[0].value = arguments[1];", PlanningUnloadingDate, PlanningUnloadingDateValue);
            System.out.println("Заполнили дату выгрузки: " + PlanningUnloadingDateValue);

            System.out.println("Заполнили поля");

            CodeTovara.click();

        } catch (Exception e) {
            System.out.println("Ошибка при взаимодействии с элементом 'Груз': " + e.getMessage());
        }

        // Кол-во груза input поле
        WebElement NumberOfShipment = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@aria-labelledby='column_header_b3t8']")));
        // aria-labelledby="column_header_b3t8"
        // Вес груза input поле
        WebElement TheWholeCargo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@aria-labelledby='column_header_b3t5']")));
        // Объём груза input поле
        WebElement CargoVolume = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@aria-labelledby='column_header_b3t4']")));
        // Температура груза ОТ input поле
        WebElement TheTemperatureOfTheCargoFROM = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@aria-labelledby='column_header_b3t1']")));
        // Температура груза ДО input поле
        WebElement TheTemperatureOfTheCargoIsUpTo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@aria-labelledby='column_header_b3t0']")));

        // Выход на пэйдж заявки
        WebElement BackPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[4]/form/main/div[2]/div[2]/div/div/div[1]/span/button/span/i")));

        js.executeScript("arguments[0].value = arguments[1];", NumberOfShipment, NumberOfShipmentValue);
        System.out.println("Заполнили Код Товара: " + NumberOfShipmentValue);

        js.executeScript("arguments[0].value = arguments[1];", TheWholeCargo, TheWholeCargoValue);
        System.out.println("Заполнили плановую локацию загрузки: " + TheWholeCargoValue);

        js.executeScript("arguments[0].value = arguments[1];", CargoVolume, CargoVolumeValue);
        System.out.println("Заполнили плановую локацию выгрузки: " + CargoVolumeValue);

        js.executeScript("arguments[0].value = arguments[1];", TheTemperatureOfTheCargoFROM,
                TheTemperatureOfTheCargoFROMValue);
        System.out.println("Заполнили плановую стартовую дату: " + TheTemperatureOfTheCargoFROMValue);

        js.executeScript("arguments[0].value = arguments[1];", TheTemperatureOfTheCargoIsUpTo,
                TheTemperatureOfTheCargoIsUpToValue);
        System.out.println("Заполнили дату выгрузки: " + TheTemperatureOfTheCargoIsUpToValue);

        TheTemperatureOfTheCargoFROM.click();
        TheTemperatureOfTheCargoIsUpTo.click();
        OpenLoadingLocation.click();
        OpenUnloadingLocation.click();
        PlanningLoadingDate.click();
        PlanningUnloadingDate.click();
        OpenLoadingLocation.click();
        BackPage.click();

        // BackPage.click();

        // column_header_b3t9
        // Возвращаемся в основной контекст
        driver.switchTo().defaultContent();
        System.out.println("Вышли из iframe.");

    }
}

// Погрузка aria-labelledby="b3v3lbl"

// разгрузка

// Для входа в заявку