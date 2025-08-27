package com.example.PagesFleetHand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.Environment.BasePage;

public class FleetHandDriver extends BasePage {

    public String plateNumber = "A894CA67";

    public FleetHandDriver(WebDriver driver) {
        super(driver);
    }

    public WebElement getDriverByPlate(String plateNumber) {
        return waitAndGetVisibleElement(
                By.xpath("//span[@class='vehicle-plateNumber-content' and text()='" + plateNumber + "']"));
    }

    public void openDriver(String plateNumber) {
        getDriverByPlate(plateNumber).click();
    }

    public void openToListDriver(String plateNumber) {
        WebElement openListDriver = waitAndGetClickableElement(
                By.xpath("//span[@class='vehicle-plateNumber-content' and text()='" + plateNumber + "']" +
                        "/ancestor::div[@class='vehicle__data']//i[@class='ico-check']"));
        openListDriver.click();
    }

    public void visibilityOfListDriver(String plateNumber) {
        try {
            WebElement openListDriver = waitAndGetClickableElement(
                    By.xpath("//span[@class='vehicle-plateNumber-content' and text()='" + plateNumber + "']" +
                            "/ancestor::div[@class='vehicle__data']//i[@class='ico-check']"));
            openListDriver.click();
        } catch (Exception e) {
            System.out.println("Кнопки нет и не видно");
        }
    }
}
