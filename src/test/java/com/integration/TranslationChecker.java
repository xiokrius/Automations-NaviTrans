package com.integration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.logging.Logger;

public class TranslationChecker {
    private static final Logger logger = Logger.getLogger(TranslationChecker.class.getName());

    public static void main(String[] args) {
        // Указываем путь к драйверу (например, ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            // Пример списка URL-адресов для проверки
            String[] urls = {
                    "https://example.com/page1",
                    "https://example.com/page2",
                    // Добавь другие URL-адреса
            };

            for (String url : urls) {
                driver.get(url);

                // Пример проверки наличия английского текста
                List<WebElement> englishElements = driver
                        .findElements(By.xpath("//*[contains(text(), 'english text')]"));
                if (englishElements.isEmpty()) {
                    logger.warning("Английский текст не найден на странице: " + url);
                } else {
                    logger.info("Английский текст найден на странице: " + url);
                }
            }
        } finally {
            driver.quit();
        }
    }
}
