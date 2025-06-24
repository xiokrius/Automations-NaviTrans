package com.unit;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderTestNoOrder {

    private WebDriver driver;
    private WebDriverWait wait;

    public OrderTestNoOrder(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void main(String[] args) {

    }

}
