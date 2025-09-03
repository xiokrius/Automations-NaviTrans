package com.example.PaymentPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.ConfigManager;
import com.example.Environment.BasePage;

public class PaymentCalendar extends BasePage {

    private String order = ConfigManager.getProperty("order.number");
    private WebElement searchButton2;
    private WebElement searchButton1;
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

        setInputValue(inputOrderPayment, order);

        returnToMainContent();
    }

    public void searchPayment2() {

        switchToIframe();

        logger.info("до элемента");

        WebElement searchButton2 = waitAndGetVisibleElement(
                By.xpath("//div[contains (@class, 'search-box-container--1aOM57_7nkghJ4mG2z5f_O pointer')]"));

        logger.info("нашёл");

        searchButton2.click();

        logger.info("кликнут смог");

        returnToMainContent();
    }

}