package com.example.PagesVendor;

import java.time.Duration;

import javax.swing.plaf.basic.BasicSliderUI.ScrollListener;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

        WebElement PaymentList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@text()='Платежи']")));

        frameSwitcher.scrollToElement(PaymentList);

        PaymentList.click();

        frameSwitcher.returnToMainContent();

    }

}
