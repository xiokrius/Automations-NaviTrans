package com.example.PagesVendor;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.ConfigManager;
import com.example.PagesClient.OpenContactsPage.RandomUtils;
import com.example.PagesOrder.FrameSwitcher;

public class CardOfVendor {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private FrameSwitcher frameSwitcher;
    public final String NameVendorsValue;
    private static final String CodePaymentVendorValue = ConfigManager.getProperty("CodePaymentVendorValue");
    private static final String CityVendorValue = ConfigManager.getProperty("CityVendorValue");
    private static final Logger logger = LogManager.getLogger(CardOfVendor.class);

    public CardOfVendor(WebDriver driver) {

        this.NameVendorsValue = RandomUtils.generateRandomString(10); // Длина 10 символов
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        this.frameSwitcher = new FrameSwitcher(driver);
    }

    public void completionOfNameCardVendor() {

        frameSwitcher.switchToIframe();
        WebElement NameVendor = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@required='required' and @type='text']")));

        js.executeScript("arguments[0].value = arguments[1];", NameVendor, NameVendorsValue);
        logger.info("Заполнили наименование поставщика: " + NameVendorsValue);

        NameVendor.click();

        frameSwitcher.returnToMainContent();

    }

    public void completionOfListOfPaymentsCardVendor() {

        frameSwitcher.switchToIframe();

        WebElement PaymentList = driver.findElement(
                By.xpath("//span[text()='Платежи']"));

        frameSwitcher.scrollToElement(PaymentList);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(PaymentList))
                .click();

        frameSwitcher.returnToMainContent();

    }

    public void fillingInThePaymentCode() {

        frameSwitcher.switchToIframe();

        WebElement CodePaymentVendor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "a[aria-label*='Код условий платежа'] + input")));
        logger.info("Нашли Код условия платежа");
        js.executeScript("arguments[0].value=arguments[1];", CodePaymentVendor, CodePaymentVendorValue);

        CodePaymentVendor.click();

        frameSwitcher.returnToMainContent();

    }

    public void fillingCityInVendor() {

        frameSwitcher.switchToIframe();

        WebElement CityVendor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "a[aria-label*='для элемента Город'] + input")));

        js.executeScript("arguments[0].value=arguments[1];", CityVendor, CityVendorValue);

        CityVendor.click();

        frameSwitcher.returnToMainContent();
    }

    public void buttonInBack() {

        frameSwitcher.switchToIframe();

        WebElement ButtonInBack = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@data-is-focusable, 'true') and contains(@title, 'Назад')]")));

        ButtonInBack.click();

        try {

            WebDriverWait quickWait = new WebDriverWait(driver, Duration.ofSeconds(3));

            quickWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@title='Почтовые индексы' and contains(text(), 'Почтовые индексы')]")));
            System.out.println("Всплывающее окно обнаружено");

            WebElement ButtonInOk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "//button[contains(@class, '1876861216 thm-bgcolor')]//span[text()='ОК']")));
            System.out.println("Кнопка ОК идентифицирована");
            ButtonInOk.click();
            System.out.println("Нажата кнопка ОК");
        } catch (Exception e) {

            System.out.println("Окно не обнаружено");
        }

        ButtonInBack.click();

        frameSwitcher.returnToMainContent();

    }

}
