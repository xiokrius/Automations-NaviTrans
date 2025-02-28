package com.example.PagesClient;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllClients {

    private final WebDriver driver;
    private final WebDriverWait wait;
    public final String nameContactsValue;

    public AllClients(WebDriver driver, String nameContactsValue) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.nameContactsValue = nameContactsValue;

    }

    // Метод для переключения в iframe
    private void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
    }

    public void ClientsOpen() {

        System.out.println("Начинаем AllClients/ClientsOpen");

        // Переключаемся в нужный фрейм
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        switchToIframe();
        System.out.println("Перешли в фрейм.");

        // Клик для фильтра
        WebElement searchClients = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, '2QlGchCqsOBuKedaQywo14')]")));

        System.out.println("Текст найденного элемента: " + searchClients.getText());
        System.out.println("Отображается ли элемент: " + searchClients.isDisplayed());
        System.out.println("Активен ли элемент: " + searchClients.isEnabled());

        // Кликаем по первой кнопке
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", searchClients);
        System.out.println("Клик по кнопке фильтр для поиска клиента выполнен.");

        // вбили имя поиска клиента
        searchClients.sendKeys(nameContactsValue);

        // Инициализируем карточку клиента
        try {
            WebElement clientCard = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@title, 'CUST-02')]")));
            // Дополнительные действия с карточкой клиента

            System.out.println("Карточка найдена");
            clientCard.click(); // Например, клик по карточке клиента
            System.out.println("Клик выполнен");

        } catch (Exception e) {
            System.out.println("Картинка клиента не была найдена: " + nameContactsValue);
        }

    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("ласт вышел с фрейма, проверка");
    }

    public void Window() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        switchToIframe();
        System.out.println("Перешли в фрейм.");

        try {
            WebElement WindowsCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//p[@title='Запрос утверждения отправлен.']")));
            System.out.println("Окно найдено.");

            WebElement ButtonInOkWindow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "//button[contains(@class, '1632124310') and not(@aria-hidden='true')]//span[text()='ОК']")));

            System.out.println("Видимость кнопки: " + ButtonInOkWindow.isDisplayed());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ButtonInOkWindow);

            System.out.println("Нажата кнопка ОК.");

            WebElement ButtonInOkWindow2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "//button[contains(@class, '1632124310')]//span[text()='ОК']")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ButtonInOkWindow2);

        } catch (Exception e) {

            System.out.println("Окно не найдено");
        }
    }

    public void creditLimit() {

        System.out.println("Начинаем AllClients/ClientsOpen");

        // Переключаемся в нужный фрейм
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        switchToIframe();
        System.out.println("Перешли в фрейм.");

        try {
            WebElement clientCard = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@title, 'CUST-02')]")));
            // Дополнительные действия с карточкой клиента

            System.out.println("Карточка найдена");
            clientCard.click(); // Например, клик по карточке клиента
            System.out.println("Клик выполнен");

        } catch (Exception e) {
            System.out.println("Картинка клиента не была найдена: " + nameContactsValue);
        }

    }

}
