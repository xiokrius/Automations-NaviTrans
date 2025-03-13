package com.example.PagesOrder;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Praktika {

    private WebDriver driver;
    private WebDriverWait wait;

    public Praktika(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void Contentim() {

        driver.get("https://www.rbc.ru/");

        WebElement lol = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(
                        "//span[@class='indicators__td indicators__name'] and (contains[@text='USD ']")));
        System.out.println("Элемент найден");
        lol.click();
        System.out.println("Клик выполнен");
    }

}
