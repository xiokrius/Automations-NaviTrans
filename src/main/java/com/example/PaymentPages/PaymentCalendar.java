package com.example.PaymentPages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.ConfigManager;
import com.example.Environment.BasePage;

public class PaymentCalendar extends BasePage {

    private String order = ConfigManager.getProperty("order.number");
    private WebElement searchButton;

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

        setInputValue(searchButton, order);

        returnToMainContent();
    }

}