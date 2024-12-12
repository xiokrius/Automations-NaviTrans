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
        System.out.println("Перешли в iframe.");

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

        // Выход из iframe
        returnToMainContent();
    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("Вышли из iframe.");
    }

    // Методы для изменения дат
    public void setStartingDateValue(String startingDateValue) {
        this.startingDateValue = startingDateValue;
    }

    public void setUnloadDateValue(String unloadDateValue) {
        this.unloadDateValue = unloadDateValue;
    }
}