package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.PagesClient.AllClients;
import com.example.PagesClient.ClientsPage;
import com.example.PagesClient.Contacts;
import com.example.PagesClient.OpenContactsPage;
import com.example.PagesOrder.QLoginTest;
import com.example.ConfigManager;

public class OpenContactsOrClient {

    public static void main(String[] args) {
        System.out.println("Запуск теста...");
        // Настройка WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://192.168.1.13:8080/BC210-TEST/SignIn?ReturnUrl=%2FBC210-TEST%2F");

        String login = ConfigManager.getProperty("inputLogin");
        String password = ConfigManager.getProperty("inputPassword");

        // Выполнение входа (логин, пароль, нажатие кнопки)
        QLoginTest loginTest = new QLoginTest(driver);
        loginTest.inputLogin(login);
        loginTest.inputPassword(password);
        loginTest.clickLoginButton();

        System.out.println("Переход на страницу заявок...");
        Contacts Contacts = loginTest.goToContacts();

        Contacts CreateNewContacts = new Contacts(driver);
        CreateNewContacts.ContactsOrderOpen();
        CreateNewContacts.returnToMainContent();

        OpenContactsPage OpenContacts = new OpenContactsPage(driver);
        OpenContacts.OpenContacts();

        String generatedName = OpenContacts.NameContactsValue;

        CreateNewContacts.returnToMainContent();

        driver.get(
                "http://192.168.1.13:8080/BC210-TEST/?company=Trans_Solutions_CZ&page=22&dc=0&bookmark=31%3bEgAAAAJ7%2f0MAVQBTAFQALQAwADAAMAAwADE%3d");

        AllClients clientsOpenFull = new AllClients(driver, generatedName);
        clientsOpenFull.ClientsOpen();
        CreateNewContacts.returnToMainContent();

        ClientsPage OpenClients = new ClientsPage(driver);
        OpenClients.fillingClientsForm();

        AllClients OpenClientsWindow = new AllClients(driver, generatedName);
        OpenClientsWindow.Window();

    }
}
