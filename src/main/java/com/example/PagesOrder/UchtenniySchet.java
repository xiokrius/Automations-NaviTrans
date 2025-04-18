package com.example.PagesOrder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UchtenniySchet {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private FrameSwitcher frameSwitcher;

    public UchtenniySchet(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        this.frameSwitcher = new FrameSwitcher(driver);
    };

    public void OpenSchet() {

        String NoSchet = driver.findElement(By.xpath("//span[@id='b5xfee']")).getText();

    }

}
