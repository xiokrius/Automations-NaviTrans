package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import java.time.Duration;
import org.testng.annotations.Test;

import com.example.LinkedPages.AddContacts;
import com.example.LinkedPages.addContactsNevad;
import com.example.LinkedPages.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Linkedin {

    String url = "https://www.linkedin.com/mynetwork/grow/";

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test(priority = 1)
    public void login() {
        login ligAp = new com.example.LinkedPages.login(driver);
        ligAp.loginInput();
        ligAp.passWordInput();
        ligAp.okButton();
    }

    @Test(priority = 2)
    public void addContact() {
        addContactsNevad addCont = new addContactsNevad(driver);
        addCont.goToPageContacts();
        addCont.addMeFriend();
        // addCont.loca();
    }
    // @AfterClass
    // public void teardown() {
    // driver.quit();
    // }

}
