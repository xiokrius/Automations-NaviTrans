package com.example.PagesVendor;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;
import com.example.PagesClient.OpenContactsPage.RandomUtils;
import com.example.PagesOrder.FrameSwitcher;

public class CardOfVendor {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private FrameSwitcher frameSwitcher;
    public final String NameVendorsValue;

    public CardOfVendor(WebDriver driver) {

        this.NameVendorsValue = RandomUtils.generateRandomString(10); // Длина 10 символов
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        this.frameSwitcher = new FrameSwitcher(driver);
    }

    public void completionOfCardVendor() {

        frameSwitcher.switchToIframe();

        WebElement NameVendor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//input[@title='required']")));

        js.executeScript("arguments[0].value = arguments[1];", NameVendor, NameVendorsValue);
        System.out.println("Заполнили наименование поставщика: " + NameVendorsValue);

        frameSwitcher.returnToMainContent();

        // Jaromer это город

    }

}
