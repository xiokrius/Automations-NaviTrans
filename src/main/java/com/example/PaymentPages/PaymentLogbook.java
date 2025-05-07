package com.example.PaymentPages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;

public class PaymentLogbook {

    private JavascriptExecutor js;
    private WebDriver driver;
    private WebDriverWait wait;

    public PaymentLogbook(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPaymentLoogbook() {
        driver.get(ConfigManager.getProperty("PaymentLogbook"));

    }

}
