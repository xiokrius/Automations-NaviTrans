package com.example.PagesVendor;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.ConfigManager;
import com.example.Environment.BasePage;
import com.example.PagesClient.OpenContactsPage.RandomUtils;

public class CardOfVendor extends BasePage {

    public final String NameVendorsValue;
    private static final String CodePaymentVendorValue = ConfigManager.getProperty("CodePaymentVendorValue");
    private static final String CityVendorValue = ConfigManager.getProperty("CityVendorValue");
    private static final Logger logger = LogManager.getLogger(CardOfVendor.class);

    public CardOfVendor(WebDriver driver) {

        super(driver);
        this.NameVendorsValue = RandomUtils.generateRandomString(10); // Длина 10 символов

    }

    public void completionOfNameCardVendor() {

        switchToIframe();

        WebElement NameVendor = waitAndGetClickableElement(
                By.xpath("//input[@required='required' and @type='text']"));

        setInputValue(NameVendor, NameVendorsValue);
        logger.info("Заполнили наименование поставщика: " + NameVendorsValue);

        NameVendor.click();

        returnToMainContent();

    }

    public void completionOfListOfPaymentsCardVendor() {

        switchToIframe();

        WebElement PaymentList = getDriver().findElement(
                By.xpath("//span[text()='Платежи']"));

        scrollToElement(PaymentList);

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(PaymentList))
                .click();

        returnToMainContent();

    }

    public void fillingInThePaymentCode() {

        switchToIframe();

        WebElement CodePaymentVendor = waitAndGetVisibleElement(By.cssSelector(
                "a[aria-label*='Код условий платежа'] + input"));
        logger.info("Нашли Код условия платежа");

        setInputValue(CodePaymentVendor, CodePaymentVendorValue);

        CodePaymentVendor.click();

        returnToMainContent();

    }

    public void fillingCityInVendor() {

        switchToIframe();

        WebElement CityVendor = waitAndGetVisibleElement(By.cssSelector(
                "a[aria-label*='для элемента Город'] + input"));

        setInputValue(CityVendor, CityVendorValue);

        CityVendor.click();

        returnToMainContent();
    }

    public void buttonInBack() {

        switchToIframe();

        WebElement ButtonInBack = waitAndGetClickableElement(
                By.xpath("//button[contains(@data-is-focusable, 'true') and contains(@title, 'Назад')]"));

        ButtonInBack.click();

        try {

            createWait(3).until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@title='Почтовые индексы' and contains(text(), 'Почтовые индексы')]")));
            System.out.println("Всплывающее окно обнаружено");

            WebElement ButtonInOk = waitAndGetClickableElement(By.xpath(
                    "//button[contains(@class, '1876861216 thm-bgcolor')]//span[text()='ОК']"));
            System.out.println("Кнопка ОК идентифицирована");
            ButtonInOk.click();
            System.out.println("Нажата кнопка ОК");
        } catch (Exception e) {

            System.out.println("Окно не обнаружено");
        }

        ButtonInBack.click();

        returnToMainContent();

    }

}
