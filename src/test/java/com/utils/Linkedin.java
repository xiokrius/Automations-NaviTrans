package com.utils;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Linkedin {

    private WebDriver driver;
    private static WebDriverWait wait;
    private static JavascriptExecutor js;

    public Linkedin(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;

    }

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.linkedin.com/mynetwork/grow/");

        for (int i = 0; i < 10; i++) {
            js.executeScript("window.scrollBy(0, 900);");

            Duration.ofSeconds(1); // Пауза для стабильности

            try {
                WebElement rekomend = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[text()='Дополнительные рекомендации для вас']")));
                // Если элемент найден и видим, выходим из цикла
                break;
            } catch (Exception e) {
                // Элемент не найден, продолжаем прокрутку
                if (i == 9)
                    throw new RuntimeException("Элемент не найден после 10 прокруток");

            }
        }

        js.executeScript("window.scrollBy(0, 900);");

        try {
            // Ищем все элементы с текстом "Установить контакт"
            List<WebElement> elements = driver.findElements(By.xpath("//span[text()='Установить контакт']"));

            // Если элементы не найдены, можно сразу выйти
            if (elements.isEmpty()) {
                throw new RuntimeException("Элементы 'Установить контакт' не найдены");
            }

            // Проходим по всем найденным элементам
            for (WebElement element : elements) {
                try {
                    // Пытаемся прокрутить к элементу и кликнуть
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
                            element);
                    Thread.sleep(300); // Небольшая задержка для стабилизации
                    element.click();
                    break; // Выходим из цикла после успешного клика
                } catch (Exception e) {
                    // Если не удалось кликнуть, прокручиваем страницу
                    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
                    Thread.sleep(300);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Процесс был прерван", e);
        }

    }

}
