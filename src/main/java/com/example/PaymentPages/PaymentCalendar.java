package com.example.PaymentPages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.Environment.BasePage;

public class PaymentCalendar extends BasePage {

    public PaymentCalendar(WebDriver driver) {

        super(driver);
    }

    public void searchPayment() {

        switchToIframe();

        WebElement SearchButton = waitAndGetClickableElement(By.id("SearchButton365"));

        returnToMainContent();

    }

}