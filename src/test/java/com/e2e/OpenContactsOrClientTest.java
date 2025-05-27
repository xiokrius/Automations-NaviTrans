package com.e2e;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.example.ConfigManager;
import com.example.PagesClient.AllClients;
import com.example.PagesClient.AutorisedClients;
import com.example.PagesClient.ClientsPage;
import com.example.PagesClient.Contacts;
import com.example.PagesClient.OpenContactsPage;
import com.example.PagesOrder.QLoginTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Epic("Управление клиентами и контактами")
@Feature("Создание и утверждение клиентов")
@Severity(SeverityLevel.CRITICAL)

public class OpenContactsOrClientTest {

    private WebDriver driver1;
    private WebDriver driver2;
    private String mainWindowHandle;
    private String secondWindowHandle;
    private String generatedName;

    private static final Logger logger = LogManager.getLogger(OpenContactsOrClientTest.class);

    @BeforeClass
    @Step("Инициализация драйвера и открытие браузера")
    public void setup() {
        logger.info(
                "Начало теста \"На создание и заполнение карточки контакта, создание из неё карточки клиента, заполнение карточки клиента, отправка на Утверждение, отправка на КД, согласование\" ");
        driver1 = new ChromeDriver();
        driver1.manage().window().maximize();
        driver1.get(ConfigManager.getProperty("BaseURL"));
        mainWindowHandle = driver1.getWindowHandle();
        logger.info("Перешли в окно ввода логина пароля");
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 1)
    @Story("Создание контакта и клиента")
    @Description("Тест проверяет создание нового контакта и клиента")
    public void createContactAndClient() {
        logger.info("Создание нового контакта и клиента");
        QLoginTest loginTest = new QLoginTest(driver1);
        loginTest.inputLogin(ConfigManager.getProperty("inputLogin"));
        loginTest.inputPassword(ConfigManager.getProperty("inputPassword"));
        loginTest.clickLoginButton();
        logger.info("Вход выполнен");

        // Переход на страницу Контактов

        Contacts contactsPage = new Contacts(driver1);
        contactsPage.ContactsOrderOpen();
        logger.info("Открылась страница с контактами");

        OpenContactsPage openContacts = new OpenContactsPage(driver1);
        openContacts.OpenContacts();
        generatedName = openContacts.NameContactsValue;
        logger.info("Контакт создан: " + generatedName);
        Assert.assertNotNull(generatedName, "Контакт не был создан");
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 2)
    @Story("Заполнение карточки клиента")
    @Description("Тест проверяет заполнение карточки клиента и авторизацию")
    public void fillClientFormAndAuthorize() {
        logger.info("Заполнение карточки клиента и авторизация");
        driver1.get(ConfigManager.getProperty("URLClients"));

        AllClients clientsOpenFull = new AllClients(driver1, generatedName);
        clientsOpenFull.ClientsOpen();
        logger.info("Карточка клиента открыта");

        ClientsPage clientPage = new ClientsPage(driver1);
        clientPage.fillingClientsForm();
        logger.info("Карточка клиента заполнена");

        clientsOpenFull.Window();
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 3)
    @Story("Подтверждение клиента")
    @Description("Тест проверяет подтверждение клиента")
    public void approveClientInSecondWindow() {
        logger.info("Открытие второго браузера для подтверждения клиента");
        driver2 = new ChromeDriver();
        driver2.manage().window().maximize();
        driver2.get(ConfigManager.getProperty("BaseURL"));
        secondWindowHandle = driver2.getWindowHandle();

        QLoginTest loginTest2 = new QLoginTest(driver2);
        loginTest2.inputLogin(ConfigManager.getProperty("inputLogin2"));
        loginTest2.inputPassword(ConfigManager.getProperty("inputPassword"));
        loginTest2.clickLoginButton();
        logger.info("Вход выполнен от 2 юзера");

        AutorisedClients autorisedClients = new AutorisedClients(driver2, generatedName);
        autorisedClients.Autorised();
        logger.info("Переход в т.Запросы утверждения, утверждение нового клиента");
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 4)
    @Story("Установка кредитного лимита")
    @Description("Тест проверяет установку и согласование кредитного лимита")
    public void setAndApproveCreditLimit() {
        logger.info("Настройка и согласование кредитного лимита");
        driver1.switchTo().window(mainWindowHandle);

        AllClients clientCD = new AllClients(driver1, generatedName);
        clientCD.creditLimit();
        logger.info("Переход в карточку клиента");

        ClientsPage OpenCD = new ClientsPage(driver1);
        logger.info("ожидание прогрузки страницы клиента");
        OpenCD.waitingForTheClientPageToLoad();
        logger.info("обновление страницы для валидации новых данных");
        driver1.navigate().refresh();
        logger.info("ожидание прогрузки страницы клиента");
        OpenCD.editCardOfClients();
        logger.info("Клик для редактирования карточки клиента");
        OpenCD.OpenCD();
        logger.info("Внесение КД");

        AllClients BackToAllClients = new AllClients(driver1, generatedName);
        BackToAllClients.Window();

        logger.info("Выход из карточки клиента");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 5)
    @Story("Подтверждение КД")
    @Description("Тест проверяет подтверждение КД")
    public void setApproverCd() {

        logger.info("Подтверждение КД");

        driver2.switchTo().window(secondWindowHandle);
        driver2.navigate().refresh();
        AutorisedClients continueInSecondWindow = new AutorisedClients(driver2, generatedName);
        continueInSecondWindow.Autorised();
        logger.info("Подтверждение КД успешно выполнено");

        driver2.quit();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 6)
    @Story("Проверка нового КД")
    @Description("Тест проверяет новый КД в основном браузере")
    public void OpenWindow() {

        driver1.switchTo().window(mainWindowHandle);
        logger.info("Проверка нового кд");
        logger.info(
                "Конец теста \"На создание и заполнение карточки контакта, создание из неё карточки клиента, заполнение карточки клиента, отправка на Утверждение, отправка на КД, согласование\" ");

    }

    @AfterMethod
    @Step("Создание скриншота при неудаче")
    public void takeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
    }

    private void takeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

        try {
            File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots");
            if (!screenshotDir.exists()) {
                boolean created = screenshotDir.mkdirs();
                if (created) {
                    logger.info("Папка для скриншотов успешно создана");
                } else {
                    logger.error("Не удалось создать папку для скриншотов");
                }
            }

            FileUtils.copyFile(srcFile, new File(screenshotName));
            logger.info("Тест-ран не пройден " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            logger.info("Скриншот сохранен: " + screenshotName);
        } catch (IOException e) {
            logger.error("Ошибка при сохранении скриншота: " + e.getMessage());
        }
    }

    @AfterClass
    @Step("Завершение теста и закрытие браузеров")
    public void tearDown() {
        logger.info("Завершение теста и закрытие браузеров");
        if (driver1 != null)
            driver1.quit();
        if (driver2 != null)
            driver2.quit();
    }
}
