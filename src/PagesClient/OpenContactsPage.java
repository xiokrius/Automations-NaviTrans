package PagesClient;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

public class OpenContactsPage {

    String selectedClient = ConfigManager.getProperty("SelectedClient");
    private String NameContactsValue = ConfigManager.getProperty("NameContactsValue");

    private final WebDriver driver;
    private final WebDriverWait wait;
    private JavascriptExecutor js;

    public OpenContactsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    private void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
    }

    public void selectRecordByText(String recordNumber) {
        try {
            WebElement record = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[normalize-space()='" + recordNumber + "']")));

            System.out.println("Нашли запись: " + recordNumber);

            // Скроллим к элементу, если он не в зоне видимости
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", record);

            // Кликаем по найденной записи
            record.click();

            System.out.println("Кликнули по записи: " + recordNumber);
        } catch (Exception e) {
            System.out.println("Ошибка: Запись '" + recordNumber + "' не найдена!");
        }
    }

    public void OpenContacts() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        switchToIframe();
        System.out.println("Перешли в фрейм.");

        WebElement NameContacts = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@controlname, 'NameTRSL')]//descendant::input[contains(@id, 'ee')]")));
        System.out.println("Нашли поле Название контакта");

        NameContacts.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // controlname="NameTRSL" and id = ee

        js.executeScript("arguments[0].value = arguments[1];", NameContacts, NameContactsValue);
        System.out.println("Заполнили стартовую дату через JavaScript: " + NameContactsValue);

        // в будущем понадобится
        WebElement CompanyNo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@controlname, 'Company No.')]//descendant::input[contains(@id, 'ee')]")));
        System.out.println("Нашли поле Название контакта");

        // Клик для авторизации
        CompanyNo.click();

        WebElement DopButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@title='Показать дополнительные действия'])[2]")));

        System.out.println("Нашли поле Дополнительно");

        DopButton.click();

        WebElement DeystviyaButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@aria-label='Действия'])")));

        System.out.println("Нашли поле Дополнительно");

        DeystviyaButton.click();

        WebElement FunkciiButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@aria-label='Функции'])")));

        FunkciiButton.click();

        WebElement SozdatkakButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@aria-label='Создать как'])")));

        SozdatkakButton.click();

        WebElement ClientButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@aria-label='Клиент'])")));

        ClientButton.click();
        // Клиент

        selectRecordByText(selectedClient);
    }

}
