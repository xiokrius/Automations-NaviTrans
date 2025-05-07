package com.integration;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Создание оплаты по счёту")
@Feature("Оплата счёта")
public class PaymentOrderTest {

    private WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    public PaymentOrderTest(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

}
