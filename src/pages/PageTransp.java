package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// ЗАПОЛНЕНИЕ ПЕРЕМЕЩЕНИЯ (ПОКА ТОЛЬКО ПЛАНОВЫЕ ДАТЫ)
public class PageTransp {

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
                "//*[@aria-labelledby='b3v3lbl']")));
        System.out.println("Нашли поле ввода Код адреса погрузки");

        WebElement OpenUnloadingLocation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@aria-labelledby='b3vblbl']")));
        System.out.println("Нашли поле ввода Код адреса погрузки");

        // План Дата загрузки
        WebElement PlanningLoadingDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@aria-labelledby='b3v7lbl']")));
        System.out.println("Нашли поле ввода даты загрузки");

        // План Дата разагрузки
        WebElement PlanningUnloadingDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@aria-labelledby='b3vflbl']")));
        System.out.println("Нашли поле ввода Даты разгрузки");

        try {
            // Закрываем оверлей, если он есть
            WebElement overlay = driver.findElement(By.cssSelector("div.overlay-transparent[role='button']"));
            if (overlay.isDisplayed()) {
                overlay.click();
                System.out.println("Закрыли оверлей-подсказку.");
            }
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
            System.out.println("Оверлей не найден или не удалось кликнуть. Удаляем через JavaScript.");

            // Удаляем оверлей с помощью JS
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelector('div.overlay-transparent').remove();");
            System.out.println("Оверлей удалён.");
        }

        // Кнопка Груз
        WebElement Cargo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@class='ms-nav-group-caption thm-color-1818861216--not_FCM thm-font-size-medium thm-segoeSemibold ms-nav-collapsible-part-caption icon-RightCaret-after']")));
        System.out.println("Нашли кнопку Груз");

        // Прокрутка и клик
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", Cargo);
        System.out.println("Прокрутили к элементу 'Груз'");

        try {
            Cargo.click();
            System.out.println("Клик по элементу 'Груз' выполнен.");
        } catch (ElementClickInterceptedException e) {
            System.out.println("Не удалось кликнуть напрямую. Используем JavaScript.");
            js.executeScript("arguments[0].click();", Cargo);
            System.out.println("Клик выполнен через JavaScript.");
        }
    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("Вышли из iframe.");
    }
}

// Погрузка aria-labelledby="b3v3lbl"

// разгрузка

// Для входа в заявку