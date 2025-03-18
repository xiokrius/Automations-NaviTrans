package com.utils;

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
import org.testng.annotations.AfterClass;

import com.example.ConfigManager;
import com.example.PagesClient.AllClients;
import com.example.PagesClient.AutorisedClients;
import com.example.PagesClient.ClientsPage;
import com.example.PagesClient.Contacts;
import com.example.PagesClient.OpenContactsPage;
import com.example.PagesOrder.QLoginTest;
import com.example.PagesOrder.IntercompanyInvoice;
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

import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Управление клиентами")
@Feature("Создание клиента, контакта и заявки на нового клиента")
public class ContactsClientsOpenOrder {

    private WebDriver driver1;
    private WebDriver driver2;
    private String mainWindowHandle;
    private String secondWindowHandle;
    private String generatedName;

    private static final Logger logger = LogManager.getLogger(ContactsClientsOpenOrder.class);
    private static final String BASE_URL = ConfigManager.getProperty("BaseURL");
    private static final String LOGIN = ConfigManager.getProperty("inputLogin");
    private static final String PASSUSER1 = ConfigManager.getProperty("inputPassword");

    @BeforeClass
    public void setup() {
        logger.info("Инициализация драйвера и открытие браузера");
        driver1 = new ChromeDriver();
        driver1.manage().window().maximize();
        logger.info("Перешли в окно ввода логина пароля");
        driver1.get(BASE_URL);
        mainWindowHandle = driver1.getWindowHandle();
        logger.info("Вход выполнен успешно");
    }

    @Test(priority = 1)
    @Story("Создание нового контакта")
    @Severity(SeverityLevel.BLOCKER)
    public void createContactAndClient() {
        logger.info("Создание нового контакта и клиента");
        QLoginTest loginTest = new QLoginTest(driver1);
        logger.info("Авторизация по логину и паролю");
        loginTest.inputLogin(ConfigManager.getProperty("inputLogin"));
        loginTest.inputPassword(ConfigManager.getProperty("inputPassword"));
        loginTest.clickLoginButton();
        logger.info("Авторизация выполнена успешно");

        Contacts contactsPage = loginTest.goToContacts();
        logger.info("Открытие страницы с контактами");
        contactsPage.ContactsOrderOpen();
        logger.info("Страница с контактами открылась успешно");

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
        logger.info("Открытие карточки клиента");
        clientsOpenFull.ClientsOpen();
        logger.info("Карточка клиента открыта");

        ClientsPage clientPage = new ClientsPage(driver1);
        logger.info("Заполнение карточки клиента");
        clientPage.fillingClientsForm();
        logger.info("Карточка клиента заполнена");

        clientsOpenFull.Window();
        logger.info("Подтверждено в окне");
    }

    @Test(priority = 3)
    public void approveClientInSecondWindow() {
        logger.info("Открытие второго браузера для подтверждения клиента");
        driver2 = new ChromeDriver();
        driver2.manage().window().maximize();
        driver2.get(ConfigManager.getProperty("BaseURL"));
        secondWindowHandle = driver2.getWindowHandle();
        logger.info("Второй браузер открыт");

        QLoginTest loginTest2 = new QLoginTest(driver2);
        logger.info("Выполнение входа по логину и паролю 2 юзера");
        loginTest2.inputLogin(ConfigManager.getProperty("inputLogin2"));
        loginTest2.inputPassword(ConfigManager.getProperty("inputPassword2"));
        loginTest2.clickLoginButton();
        logger.info("Вход выполнен от 2 юзера");

        AutorisedClients autorisedClients = new AutorisedClients(driver2, generatedName);
        logger.info("Переход в т.Запросы утверждения, утверждение нового клиента");
        autorisedClients.Autorised();
        logger.info("Новый клиент утверждён");
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
        logger.info("Переход на страницу заявок");
        zayavkaPage.CreateNewZayavkaCZ();
        logger.info("Создание новой заявки");
        zayavkaPage.NewOrderCreate();
        logger.info("Вход в новую заявку");

    }

    @Test(priority = 8)
    public void fillOrderFormClients() {
        OrderPage orderPage = new OrderPage(driver1);
        orderPage.fillOrderFormClients();
        logger.info("Заполнение данных в заявке");

    }

    @Test(priority = 9)
    public void PerevozkaInFrame() {
        OrderPage PageOrder = new OrderPage(driver1);
        PageOrder.PerevozkaInFrame();
        logger.info("Переход в перевозки");
    }

    @Test(priority = 10)
    public void OpenOrLoadingLocation() {
        PageTransp OpenDate = new PageTransp(driver1);
        OpenDate.OpenOrLoadingLocation();
        logger.info("Установка Плановых дат в перевозке и выход обратно на страницу заявок");

    }

    @Test(priority = 11)
    public void obrabotkaVypustit() {

        OrderPage vageOpenTransp = new OrderPage(driver1);
        vageOpenTransp.obrabotkaVypustit();
        logger.info("После установки план дат. Нажимаю Обработка/выпустить");

    }

    @Test(priority = 12)
    public void vehiclePlan() {

        OrderPage testOpenTransp = new OrderPage(driver1);
        testOpenTransp.vehiclePlan();
        logger.info("Обработка/План, планирую рейс");

    }

    @Test(priority = 13)
    public void PlanOpen() {
        OrderPage opentranspOp = new OrderPage(driver1);
        opentranspOp.PlanOpen();
        logger.info("После обработка/План, выбираю в какой поездке будут изменения");

    }

    @Test(priority = 14)
    public void VehiclePlanOpen() {

        VehiclePlanning OpenVehicle = new VehiclePlanning(driver1);
        OpenVehicle.VehiclePlanOpen();
        logger.info("Задаю значения тягача и прицепа");

    }

    @Test(priority = 15)
    public void backRoute() {

        VehicleRoute backRoute = new VehicleRoute(driver1);
        backRoute.clickSomeButtonInFrame();
        logger.info("Страница рейса, вбиваю начало км, конец км, даты");

    }

    @Test(priority = 16)
    public void OpenService() {

        ZayavkaByPage OpenService = new ZayavkaByPage(driver1);
        OpenService.clickSomeButtonInService();
        logger.info("Инициализация сервисов, переход в сервисы");

    }

    @Test(priority = 17)
    public void OpenServices() {

        OpenInvoice Service = new OpenInvoice(driver1);
        Service.OpenServices();
        logger.info("Заполняю сервисы, выставляю признак Счёт для томажни");

    }

    @Test(priority = 18)
    public void readyInInvoicing() {

        OrderPage ReadyInInvoicing = new OrderPage(driver1);
        ReadyInInvoicing.readyInInvoicing();
        logger.info("Выхожу с сервисов, нажимаю готово к инвойсированию");

    }

    @Test(priority = 19)
    public void obrabotkaSchet() {

        OrderPage Schet = new OrderPage(driver1);
        Schet.obrabotkaSchet();
        logger.info("Захожу в создание счёта");

    }

    @Test(priority = 20)
    public void SchetRuchnoy() {

        ReadyInvoic SchetNRuchnoy = new ReadyInvoic(driver1);
        SchetNRuchnoy.SchetRuchnoy();
        logger.info("Создаю счёт");

    }

    @Test(priority = 21)
    public void fullSchet() {

        Invoice FullInvoice = new Invoice(driver1);
        FullInvoice.fullSchet();
        logger.info("Выпускаю и учитываю счёт, выхожу обратно на пейдж заявки");

    }

    @AfterClass
    public void tearDown() {
        logger.info("Завершение теста и закрытие браузеров");
        if (driver1 != null)
            driver1.quit();
        if (driver2 != null)
            driver2.quit();
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
    }

    @Attachment(value = "Скриншот на ошибке", type = "image/png")
    public byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
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
