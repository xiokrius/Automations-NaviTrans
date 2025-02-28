package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.PagesClient.AllClients;
import com.example.PagesClient.AutorisedClients;
import com.example.PagesClient.ClientsPage;
import com.example.PagesClient.Contacts;
import com.example.PagesClient.OpenContactsPage;
import com.example.PagesOrder.QLoginTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class OpenContactsOrClientTest {

    private WebDriver driver1;
    private WebDriver driver2;
    private String mainWindowHandle;
    private String secondWindowHandle;
    private String generatedName;
    private static final Logger logger = Logger.getLogger(OpenContactsOrClientTest.class.getName());

    @BeforeClass
    public void setup() {
        logger.info("Инициализация драйвера и открытие браузера");
        driver1 = new ChromeDriver();
        driver1.manage().window().maximize();
        driver1.get(ConfigManager.getProperty("BaseURL"));
        mainWindowHandle = driver1.getWindowHandle();
    }

    @Test(priority = 1)
    public void createContactAndClient() {
        logger.info("Создание нового контакта и клиента");
        QLoginTest loginTest = new QLoginTest(driver1);
        loginTest.inputLogin(ConfigManager.getProperty("inputLogin"));
        loginTest.inputPassword(ConfigManager.getProperty("inputPassword"));
        loginTest.clickLoginButton();

        Contacts contactsPage = loginTest.goToContacts();
        contactsPage.ContactsOrderOpen();
        contactsPage.returnToMainContent();

        OpenContactsPage openContacts = new OpenContactsPage(driver1);
        openContacts.OpenContacts();
        generatedName = openContacts.NameContactsValue;
        logger.info("Контакт создан: " + generatedName);
        Assert.assertNotNull(generatedName, "Контакт не был создан");
    }

    @Test(priority = 2)
    public void fillClientFormAndAuthorize() {
        logger.info("Заполнение карточки клиента и авторизация");
        driver1.get(ConfigManager.getProperty("URLClients"));

        AllClients clientsOpenFull = new AllClients(driver1, generatedName);
        clientsOpenFull.ClientsOpen();

        ClientsPage clientPage = new ClientsPage(driver1);
        clientPage.fillingClientsForm();
    }

    @Test(priority = 3)
    public void approveClientInSecondWindow() {
        logger.info("Открытие второго браузера для подтверждения клиента");
        driver2 = new ChromeDriver();
        driver2.manage().window().maximize();
        driver2.get(ConfigManager.getProperty("BaseURL"));
        secondWindowHandle = driver2.getWindowHandle();

        QLoginTest loginTest2 = new QLoginTest(driver2);
        loginTest2.inputLogin(ConfigManager.getProperty("inputLogin2"));
        loginTest2.inputPassword(ConfigManager.getProperty("inputPassword2"));
        loginTest2.clickLoginButton();

        AutorisedClients autorisedClients = new AutorisedClients(driver2, generatedName);
        autorisedClients.Autorised();
    }

    @Test(priority = 4)
    public void setAndApproveCreditLimit() {
        logger.info("Настройка и согласование кредитного лимита");
        driver1.switchTo().window(mainWindowHandle);

        AllClients clientCD = new AllClients(driver1, generatedName);
        clientCD.creditLimit();

        driver2.switchTo().window(secondWindowHandle);
        driver2.navigate().refresh();
        AutorisedClients continueInSecondWindow = new AutorisedClients(driver2, generatedName);
        continueInSecondWindow.Autorised();
    }

    // @AfterClass
    // public void tearDown() {
    // logger.info("Завершение теста и закрытие браузеров");
    // if (driver1 != null)
    // driver1.quit();
    // if (driver2 != null)
    // driver2.quit();
    // }
}
