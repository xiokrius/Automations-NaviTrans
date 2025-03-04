package com.example;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.PagesClient.AllClients;
import com.example.PagesClient.AutorisedClients;
import com.example.PagesClient.ClientsPage;
import com.example.PagesClient.Contacts;
import com.example.PagesClient.OpenContactsPage;
import com.example.PagesOrder.QLoginTest;
import com.example.PagesOrder.Invoice;
import com.example.PagesOrder.OpenInvoice;
import com.example.PagesOrder.OrderPage;
import com.example.PagesOrder.PageTransp;
import com.example.PagesOrder.QLoginTest;
import com.example.PagesOrder.ReadyInvoic;
import com.example.PagesOrder.VehiclePlanning;
import com.example.PagesOrder.VehicleRoute;
import com.example.PagesOrder.ZayavkaByPage;
import com.example.PagesOrder.ZayavkaPage;

public class ContactsClientsOpenOrder {

    private WebDriver driver1;
    private WebDriver driver2;
    private String mainWindowHandle;
    private String secondWindowHandle;
    private String generatedName;

    private static final Logger logger = LogManager.getLogger(ContactsClientsOpenOrder.class);

    @BeforeClass
    public void setup() {
        logger.info("Инициализация драйвера и открытие браузера");
        driver1 = new ChromeDriver();
        driver1.manage().window().maximize();
        driver1.get(ConfigManager.getProperty("BaseURL"));
        mainWindowHandle = driver1.getWindowHandle();
        logger.info("Перешли в окно ввода логина пароля");
    }

    @Test(priority = 1)
    public void createContactAndClient() {
        logger.info("Создание нового контакта и клиента");
        QLoginTest loginTest = new QLoginTest(driver1);
        loginTest.inputLogin(ConfigManager.getProperty("inputLogin"));
        loginTest.inputPassword(ConfigManager.getProperty("inputPassword"));
        loginTest.clickLoginButton();
        logger.info("Вход выполнен");

        Contacts contactsPage = loginTest.goToContacts();
        contactsPage.ContactsOrderOpen();
        logger.info("Открылась страница с контактами");

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
        logger.info("Карточка клиента открыта");

        ClientsPage clientPage = new ClientsPage(driver1);
        clientPage.fillingClientsForm();
        logger.info("Карточка клиента заполнена");

        clientsOpenFull.Window();
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
        logger.info("Вход выполнен от 2 юзера");

        AutorisedClients autorisedClients = new AutorisedClients(driver2, generatedName);
        autorisedClients.Autorised();
        logger.info("Переход в т.Запросы утверждения, утверждение нового клиента");
    }

    @Test(priority = 4)
    public void setAndApproveCreditLimit() {
        logger.info("Настройка и согласование кредитного лимита");
        driver1.switchTo().window(mainWindowHandle);

        AllClients clientCD = new AllClients(driver1, generatedName);
        clientCD.creditLimit();
        logger.info("Переход в карточку клиента, установка Кредитного Лимита");

        ClientsPage OpenCD = new ClientsPage(driver1);
        OpenCD.OpenCD();

        AllClients BackToAllClients = new AllClients(driver1, generatedName);
        BackToAllClients.Window();

        logger.info("Выход из карточки клиента");

    }

    @Test(priority = 5)
    public void setApproverCd() {

        logger.info("Подтверждение КД");

        driver2.switchTo().window(secondWindowHandle);
        driver2.navigate().refresh();
        AutorisedClients continueInSecondWindow = new AutorisedClients(driver2, generatedName);
        continueInSecondWindow.Autorised();
        logger.info("Подтверждение КД успешно выполнено");

        driver2.quit();
    }

    @Test(priority = 6)
    public void OpenWindow() {

        driver1.switchTo().window(mainWindowHandle);
        logger.info("Проверка нового кд");

    }

    @Test(priority = 7)
    public void GoZayavkaPage() {
        driver1.get(ConfigManager.getProperty("URLPagesOrder"));
        ZayavkaPage zayavkaPage = new ZayavkaPage(driver1);
        zayavkaPage.CreateNewZayavkaCZ();
        zayavkaPage.NewOrderCreate();
        logger.info("sssssssss");

    }

    @Test(priority = 8)
    public void fillOrderFormClients() {
        OrderPage orderPage = new OrderPage(driver1);
        orderPage.fillOrderFormClients();
        logger.info("sssssssss");

    }

    @Test(priority = 9)
    public void PerevozkaInFrame() {
        OrderPage PageOrder = new OrderPage(driver1);
        PageOrder.PerevozkaInFrame();

    }

    @Test(priority = 10)
    public void OpenOrLoadingLocation() {
        PageTransp OpenDate = new PageTransp(driver1);
        OpenDate.OpenOrLoadingLocation();
        logger.info("sssssssss");

    }

    @Test(priority = 11)
    public void obrabotkaVypustit() {

        OrderPage vageOpenTransp = new OrderPage(driver1);
        vageOpenTransp.obrabotkaVypustit();
        logger.info("sssssssss");

    }

    @Test(priority = 12)
    public void vehiclePlan() {

        OrderPage testOpenTransp = new OrderPage(driver1);
        testOpenTransp.vehiclePlan();
        logger.info("sssssssss");

    }

    @Test(priority = 13)
    public void PlanOpen() {
        OrderPage opentranspOp = new OrderPage(driver1);
        opentranspOp.PlanOpen();
        logger.info("sssssssss");

    }

    @Test(priority = 14)
    public void VehiclePlanOpen() {

        VehiclePlanning OpenVehicle = new VehiclePlanning(driver1);
        OpenVehicle.VehiclePlanOpen();
        logger.info("sssssssss");

    }

    @Test(priority = 15)
    public void backRoute() {

        VehicleRoute backRoute = new VehicleRoute(driver1);
        backRoute.clickSomeButtonInFrame();
        logger.info("sssssssss");

    }

    @Test(priority = 16)
    public void OpenService() {

        ZayavkaByPage OpenService = new ZayavkaByPage(driver1);
        OpenService.clickSomeButtonInService();
        logger.info("sssssssss");

    }

    @Test(priority = 17)
    public void OpenServices() {

        OpenInvoice Service = new OpenInvoice(driver1);
        Service.OpenServices();
        logger.info("sssssssss");

    }

    @Test(priority = 18)
    public void readyInInvoicing() {

        OrderPage ReadyInInvoicing = new OrderPage(driver1);
        ReadyInInvoicing.readyInInvoicing();
        logger.info("sssssssss");

    }

    @Test(priority = 19)
    public void obrabotkaSchet() {

        OrderPage Schet = new OrderPage(driver1);
        Schet.obrabotkaSchet();
        logger.info("sssssssss");

    }

    @Test(priority = 20)
    public void SchetRuchnoy() {

        ReadyInvoic SchetNRuchnoy = new ReadyInvoic(driver1);
        SchetNRuchnoy.SchetRuchnoy();
        logger.info("sssssssss");

    }

    @Test(priority = 21)
    public void fullSchet() {

        Invoice FullInvoice = new Invoice(driver1);
        FullInvoice.fullSchet();
        logger.info("sssssssss");

    }

    @AfterMethod
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

}
