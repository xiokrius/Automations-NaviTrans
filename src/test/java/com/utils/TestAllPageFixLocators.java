package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.PagesOrder.Praktika;

public class TestAllPageFixLocators {

    public static void main(String[] args) {
        System.out.println("Запуск теста...");
        // Настройка WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // // Создание новой заявки
        // Praktika CreateNewOrder = new Praktika(driver);
        // // CreateNewOrder.switchToIframe();

    }
}
