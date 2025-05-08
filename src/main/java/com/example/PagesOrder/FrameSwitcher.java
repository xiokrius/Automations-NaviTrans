package com.example.PagesOrder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameSwitcher {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public FrameSwitcher(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Инициализация WebDriverWait
        this.js = (JavascriptExecutor) driver;
    }

    public void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в фрейм.");
    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("Возвращаемся в основной контент страницы.");
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    // Метод для заполнения значения через JavaScript
    public void fillSelectWithJS(WebElement element, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", element, value);
        System.out.println("Заполнили поле: " + value);
    }

    // Метод для выбора значения в select
    public void selectDropdownByValue(WebElement selectElement, String value) {
        Select select = new Select(selectElement);
        select.selectByValue(value);
        System.out.println("Выбрали значение в select: " + value);
    }

    public void scrollToElementHorizontally(WebElement scrollContainer, WebElement targetElement) {
        js.executeScript(
                "const container = arguments[0];" +
                        "const target = arguments[1];" +
                        "const containerWidth = container.offsetWidth;" +
                        "const targetLeft = target.getBoundingClientRect().left;" +
                        "const containerLeft = container.getBoundingClientRect().left;" +
                        "const targetOffset = targetLeft - containerLeft;" +
                        "const scrollAmount = targetOffset - containerWidth / 2 + target.offsetWidth / 2;"
                        +
                        "container.scrollLeft += scrollAmount;",
                scrollContainer, targetElement);
    }

    public void setInputValue(WebElement element, String value) {
        js.executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                element, value);
    }

    public void fillAndActivateSelect(WebElement select, String value) {
        // 1. Заполняем через JS
        fillSelectWithJS(select, value);

        // 2. Имитируем полную последовательность действий пользователя
        js.executeScript("""
                const select = arguments[0];
                select.dispatchEvent(new Event('focus'));
                select.dispatchEvent(new Event('change', {bubbles: true}));
                select.dispatchEvent(new Event('blur'));
                """, select);

        // 3. Даём время на обработку
        try {
            System.out.println("ес");
        } catch (Exception e) {
            System.out.println("ес1");
        }

    }
}