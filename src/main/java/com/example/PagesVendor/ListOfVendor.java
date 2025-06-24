package com.example.PagesVendor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.ConfigManager;
import com.example.Environment.BasePage;

public class ListOfVendor extends BasePage {

    private String URLVendor = ConfigManager.getProperty("URLVendorValue");

    public ListOfVendor(WebDriver driver) {

        super(driver);
    }

    public void SwitchToVendorsList() {

        openUrl(URLVendor);

        switchToIframe();

        WebElement CreateVendor = waitAndGetClickableElement(By.xpath(
                "//button[@aria-label='Создать' and @title='Создать новую операцию.']"));

        CreateVendor.click();

        returnToMainContent();

    }

}
