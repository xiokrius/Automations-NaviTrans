package com.example.PagesInventory;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.PagesOrder.FrameSwitcher;

public class TransportEquipment {

    private WebDriver driver;
    private WebDriverWait wait;
    public FrameSwitcher frameSwitcher;

    public TransportEquipment(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.frameSwitcher = new FrameSwitcher(driver);
    }

    public void SwitchToIframe() {

        WebElement iframe = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
    }

    public void OpenInventory() {

        driver.get(
                "http://192.168.1.13:8080/BC210-TEST/?company=Trans_Solutions_CZ&page=50022&filter=%27Transport%20Equipment%27.Subtype%20IS%20%270%27&dc=0&bookmark=12%3bZsMAAACHAQ%3d%3d");

        frameSwitcher.switchToIframe();

        WebElement TSButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Изменить ТС']")));
        System.out.println("Элемент найден");
        TSButton.click();
        System.out.println("Клик по элементу");

        frameSwitcher.returnToMainContent();
    }

}
