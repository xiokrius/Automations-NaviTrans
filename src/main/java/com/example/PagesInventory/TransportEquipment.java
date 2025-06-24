package com.example.PagesInventory;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.example.Environment.BasePage;



public class TransportEquipment extends BasePage {


    public TransportEquipment(WebDriver driver) {

        super(driver);
    }

   
    public void OpenInventory() {

        openUrl(
                "http://192.168.1.13:8080/BC210-TEST/?company=Trans_Solutions_CZ&page=50022&filter=%27Transport%20Equipment%27.Subtype%20IS%20%270%27&dc=0&bookmark=12%3bZsMAAACHAQ%3d%3d");

        switchToIframe();

        WebElement TSButton = waitAndGetClickableElement(By.xpath("//button[@aria-label='Изменить ТС']"));

        TSButton.click();

        System.out.println("Клик по элементу");

        returnToMainContent();
    }

}
