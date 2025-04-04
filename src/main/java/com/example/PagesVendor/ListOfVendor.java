package com.example.PagesVendor;

import java.io.ObjectInputFilter.Config;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;
import com.example.PagesOrder.FrameSwitcher;

public class ListOfVendor {

    private WebDriver driver;
    private WebDriverWait wait;
    private FrameSwitcher frameSwitcher;
    private String URLVendor = ConfigManager.getProperty("URLVendorValue");

    public ListOfVendor(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.frameSwitcher = new FrameSwitcher(driver);
    }

    public void SwitchToVendorsList() {

        driver.get(URLVendor);

        frameSwitcher.switchToIframe();

        WebElement CreateVendor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//button[@aria-label='Создать' and @title='Создать новую операцию.']")));

        CreateVendor.click();

        frameSwitcher.returnToMainContent();

    }

}
