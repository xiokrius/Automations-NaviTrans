package com.example.PagesOrder;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.Environment.BasePage;



public class UchtenniySchet extends BasePage{


    public UchtenniySchet(WebDriver driver) {

        super(driver);

    }

    public void OpenSchet() {

        String NoSchet = getDriver().findElement(By.xpath("//span[@id='b5xfee']")).getText();

    }

}
