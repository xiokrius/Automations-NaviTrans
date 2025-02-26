package com.example.PagesClient;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;

public class ClientsPage {

    private WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    private String typeCarrierValue = ConfigManager.getProperty("typeClientValue");
    private String CityValue = ConfigManager.getProperty("CityValue");

    public ClientsPage(WebDriver driver) {

        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    private void selectDropdownByValue(WebElement selectElement, String value) {
        Select select = new Select(selectElement);
        select.selectByValue(value);
        System.out.println("Выбрали значение в select: " + value);
    }

    private void setInputValue(WebElement element, String value) {
        js.executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                element, value);
        System.out.println("Заполнили значение: " + value);
    }

    private void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
    }

    public void fillingClientsForm() {

        System.out.println("Начинаем ClientsPage/fillingClientsForm");

        // Переключаемся в Фрейм
        switchToIframe();

        // Вводим "Тип клиента"
        WebElement typeClient = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()='Тип клиента']/following::select[1]")));
        System.out.println("Ввели значение поля Тип клиента");

        selectDropdownByValue(typeClient, typeCarrierValue);

        WebElement SpanCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Адрес и контакты']")));
        scrollToElement(SpanCountry);

        SpanCountry.click();

        WebElement Score = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Счет'][@class='caption-text']")));

        scrollToElement(Score);

        Score.click();

        WebElement City = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(
                        "//a[contains(@title, 'для элемента Город')]/following-sibling::input")));

        City.click();

        setInputValue(City, CityValue);

        // By.xpath("//a[contains(@aria-label, 'Код адреса разгрузки') and
        // @role='button']/following-sibling::input[contains(@id, 'ee') and
        // @role='combobox']")));

        System.out.println("Прокрутили до следующего элемента");

    }
}
