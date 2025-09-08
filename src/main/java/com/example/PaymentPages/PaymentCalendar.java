package com.example.PaymentPages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.example.ConfigManager;
import com.example.Environment.BasePage;

public class PaymentCalendar extends BasePage {

    private String order = ConfigManager.getProperty("order.number");
    private static final Logger logger = LogManager.getLogger(PaymentCalendar.class);

    public PaymentCalendar(WebDriver driver) {

        super(driver);
    }

    public void searchPayment() {

        switchToIframe();

        WebElement searchButton = waitAndGetClickableElement(By.id("SearchBox337"));

        searchButton.click();

        returnToMainContent();

    }

    public void makingOrderPayment() {

        switchToIframe();

        WebElement inputOrderPayment = waitAndGetVisibleElement(
                By.xpath("//input[@aria-label='Поиск Дебиторская задолженность']"));

        setInputJS(inputOrderPayment, order);

        returnToMainContent();
    }

    public void searchPayment2() {

        switchToIframe();

        logger.info("до элемента");

        WebElement searchButton2 = waitAndGetVisibleElement(
                By.xpath("//div[contains (@class, 'search-box-container--1aOM57_7nkghJ4mG2z5f_O pointer')]"));

        logger.info("нашёл");

        getWait().until(ExpectedConditions.elementToBeClickable(searchButton2));

        searchButton2.click();

        logger.info("кликнут смог");

        returnToMainContent();
    }

    public void clickAndWorksFilterList() {

        switchToIframe();

        WebElement searchFilter = waitAndGetClickableElement(By.xpath("//button[@aria-label='Переключить фильтр']"));

        searchFilter.click();

        returnToMainContent();
    }

    public void deleteFilter() {

        switchToIframe();

        List<WebElement> filters = getDriver().findElements(By.xpath("//button[@title='Очистить этот фильтр']"));

        for (int i = 0; i < filters.size(); i++) {
            try {
                WebElement filter = filters.get(i);
                getWait().until(ExpectedConditions.elementToBeClickable(filter)).click();
            } catch (StaleElementReferenceException e) {
                logger.info("Ошибка");
                filters = getDriver().findElements(By.xpath("//button[@title='Очистить этот фильтр']"));
                i--;
            }
        }

        returnToMainContent();
    }

    public String getPaymentText() {
        switchToIframe();
        WebElement paymentElement = waitAndGetVisibleElement(By.xpath("//span[@title='Платёж']"));
        String actualText = paymentElement.getText().trim();
        returnToMainContent();
        return actualText;
    }

}