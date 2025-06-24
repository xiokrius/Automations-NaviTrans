package com.example.PagesClient;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;
import com.example.PagesClient.ClientsPage.TestData;

public class AutorisedClients {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    public final String nameContactsValue;
    private static final Logger logger = LogManager.getLogger(AutorisedClients.class);

    public AutorisedClients(WebDriver driver, String nameContactsValue) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        this.nameContactsValue = nameContactsValue;

    }

    // Переборщил с проверками, потому что забыл в фрейм зайти в начале, и ничего не
    // работало)))))
    private void clickButtonByClientName(String clientName) {

        logger.info("Текущий URL драйвера в AutorisedClients: " + driver.getCurrentUrl());
        logger.info("Дескриптор окна в AutorisedClients: " + driver.getWindowHandle());

        String clientValue = TestData.clientValue;

        logger.info("Начали Поиск имени");
        String xpath = "//span[contains(@title, '" + clientValue + "')]";

        // Ожидаем элемент и проверяем его видимость
        logger.info("Начали Поиск имени 2");
        logger.info(clientValue);
        logger.info("Дескриптор окна в AutorisedClients: " + driver.getWindowHandle());
        WebElement ClientName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        // Проверка на видимость элемента и клик по нему
        if (ClientName.isDisplayed()) {
            js.executeScript("arguments[0].click();", ClientName);
            logger.info("Клик по элементу с текстом клиента: " + clientName);
        } else {
            logger.info("Элемент не видим для клика.");
        }
    }

    private void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        logger.info("ласт вышел с фрейма, проверка");
    }

    public void Autorised() {

        logger.info("Начали авторизацию во втором браузере");

        String AutorisedURL = ConfigManager.getProperty("AutorisedURL");
        if (AutorisedURL == null || AutorisedURL.isEmpty()) {
            logger.info("Ошибка: URL не найден в конфиге.");
            return;
        }

        logger.info("Переход по URL: " + AutorisedURL);
        driver.get(AutorisedURL);

        // Логируем текущий URL для проверки
        logger.info("Текущий URL после перехода: " + driver.getCurrentUrl());

        logger.info("Дескриптор окна в AutorisedClients: " + driver.getWindowHandle());

        switchToIframe();

        try {
            clickButtonByClientName(nameContactsValue);
        } catch (Exception e) {
            logger.info("Ошибка при клике по имени клиента: " + e.getMessage());
        }

        try {
            WebElement clickOpenClientsPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//button[@title='Утверждение запрошенных изменений.']")));

            js.executeScript("arguments[0].scrollIntoView(true);", clickOpenClientsPage);

            if (clickOpenClientsPage.isDisplayed() && clickOpenClientsPage.isEnabled()) {
                js.executeScript("arguments[0].click();", clickOpenClientsPage);
                logger.info("Клик по кнопке выполнен.");
            } else {
                logger.info("Элемент не доступен для клика.");
            }
        } catch (Exception e) {
            logger.info("Элемент не найден или не стал кликабельным: " + e.getMessage());
        }

        returnToMainContent();

    }

}

// $x("//span[contains(translate(@title, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ',
// 'abcdefghijklmnopqrstuvwxyz'), 'oogukovlyn')]")
