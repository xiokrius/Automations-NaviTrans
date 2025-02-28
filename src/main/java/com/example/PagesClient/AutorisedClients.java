package com.example.PagesClient;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
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

    public AutorisedClients(WebDriver driver, String nameContactsValue) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        this.nameContactsValue = nameContactsValue;

    }

    // Переборщил с проверками, потому что забыл в фрейм зайти в начале, и ничего не
    // работало)))))
    private void clickButtonByClientName(String clientName) {

        System.out.println("Текущий URL драйвера в AutorisedClients: " + driver.getCurrentUrl());
        System.out.println("Дескриптор окна в AutorisedClients: " + driver.getWindowHandle());

        String clientValue = TestData.clientValue;

        System.out.println("Начали Поиск имени");
        String xpath = "//span[contains(@title, '" + clientValue + "')]";

        // Ожидаем элемент и проверяем его видимость
        System.out.println("Начали Поиск имени 2");
        System.out.println(clientValue);
        System.out.println("Дескриптор окна в AutorisedClients: " + driver.getWindowHandle());
        WebElement ClientName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        // Проверка на видимость элемента и клик по нему
        if (ClientName.isDisplayed()) {
            js.executeScript("arguments[0].click();", ClientName);
            System.out.println("Клик по элементу с текстом клиента: " + clientName);
        } else {
            System.out.println("Элемент не видим для клика.");
        }
    }

    private void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
    }

    public void Autorised() {

        System.out.println("Начали авторизацию во втором браузере");

        String AutorisedURL = ConfigManager.getProperty("AutorisedURL");
        if (AutorisedURL == null || AutorisedURL.isEmpty()) {
            System.out.println("Ошибка: URL не найден в конфиге.");
            return;
        }

        System.out.println("Переход по URL: " + AutorisedURL);
        driver.get(AutorisedURL);

        // Логируем текущий URL для проверки
        System.out.println("Текущий URL после перехода: " + driver.getCurrentUrl());

        System.out.println("Дескриптор окна в AutorisedClients: " + driver.getWindowHandle());

        switchToIframe();

        try {
            clickButtonByClientName(nameContactsValue);
        } catch (Exception e) {
            System.out.println("Ошибка при клике по имени клиента: " + e.getMessage());
        }

        try {
            WebElement clickOpenClientsPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//button[@title='Утверждение запрошенных изменений.']")));

            js.executeScript("arguments[0].scrollIntoView(true);", clickOpenClientsPage);

            if (clickOpenClientsPage.isDisplayed() && clickOpenClientsPage.isEnabled()) {
                js.executeScript("arguments[0].click();", clickOpenClientsPage);
                System.out.println("Клик по кнопке выполнен.");
            } else {
                System.out.println("Элемент не доступен для клика.");
            }
        } catch (Exception e) {
            System.out.println("Элемент не найден или не стал кликабельным: " + e.getMessage());
        }
    }
}

// $x("//span[contains(translate(@title, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ',
// 'abcdefghijklmnopqrstuvwxyz'), 'oogukovlyn')]")
