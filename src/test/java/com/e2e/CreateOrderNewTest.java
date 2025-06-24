package com.e2e;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.qameta.allure.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
import com.example.ConfigManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Epic("Клиенты и заказы")
@Feature("Создание заказ")

public class CreateOrderNewTest {
    private WebDriver driver;
    private QLoginTest loginTest;
    private static final Logger logger = LogManager.getLogger(CreateOrderNewTest.class);

    @BeforeClass
    public void setup() {

        logger.info("Начало теста \"На создание и заполнение заявки, планирования рейса, учёта счёта\" ");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigManager.getProperty("URLAutorisedNavi"));
        loginTest = new QLoginTest(driver);
        logger.info("Браузер запущен и открыт URL");
    }

    @Story("Создание и запонление заявки")
    @Description("Создаёт заказ")
    @Severity(SeverityLevel.CRITICAL)

    @Step("Вход в систему с логином и паролем")
    @Test(priority = 1)
    public void login() {
        String login = ConfigManager.getProperty("inputLogin");
        String password = ConfigManager.getProperty("inputPassword");
        loginTest.inputLogin(login);
        loginTest.inputPassword(password);
        loginTest.clickLoginButton();
        logger.info("Успешный вход в систему");
    }

    @Severity(SeverityLevel.NORMAL)
    @Step("Открытие заявок")
    @Test(priority = 2, dependsOnMethods = "login")
    public void openingTheApplicationPage() {
        driver.navigate().refresh();
        driver.get(ConfigManager.getProperty("URLOrder"));
        logger.info("Открыта страница заявок");
    }

    @Severity(SeverityLevel.NORMAL)
    @Step("Создана новая заявка")
    @Test(priority = 3, dependsOnMethods = "openingTheApplicationPage")
    public void creatingANewOrder() {
        ZayavkaPage zayavkaPage = new ZayavkaPage(driver);
        zayavkaPage.CreateNewZayavkaCZ();
        zayavkaPage.NewOrderCreate();
        logger.info("Создана новая заявка");
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Заполнена форма заказа")
    @Test(priority = 4, dependsOnMethods = "creatingANewOrder")
    public void fillOrderForm() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm();
        logger.info("Заполнена форма заказа");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("Обработан заказ: переход в перевозки")
    @Test(priority = 5, dependsOnMethods = "fillOrderForm")
    public void processOrder() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.PerevozkaInFrame();
        logger.info("Обработан заказ: переход в перевозки");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("Установлены плановые даты перевозки")
    @Test(priority = 6, dependsOnMethods = "processOrder")
    public void setPlannedDates() {
        PageTransp pageTransp = new PageTransp(driver);
        pageTransp.OpenOrLoadingLocation();
        logger.info("Установлены плановые даты перевозки");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("Открыт раздел сервисов")
    @Test(priority = 7, dependsOnMethods = "setPlannedDates")
    public void goToServices() {
        ZayavkaByPage servicePage = new ZayavkaByPage(driver);
        servicePage.clickSomeButtonInService();
        logger.info("Открыт раздел сервисов");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("Обработаны сервисы")
    @Test(priority = 8, dependsOnMethods = "goToServices")
    public void processServices() {
        OpenInvoice openInvoice = new OpenInvoice(driver);
        openInvoice.OpenServices();
        logger.info("Обработаны сервисы");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("Установлены плановые даты перевозки")
    @Test(retryAnalyzer = com.utils.RetryAnalyzer.class, priority = 9, dependsOnMethods = "processServices")
    public void orderFull() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.obrabotkaVypustit();
        logger.info("Установлены плановые даты перевозки");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("Планироание и создание рейса")
    @Test(retryAnalyzer = com.utils.RetryAnalyzer.class, priority = 10, dependsOnMethods = "orderFull")
    public void testVehiclePlan() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.vehiclePlan();
        orderPage.PlanOpen();
        logger.info("планирование и создание рейса");
    }

    @Severity(SeverityLevel.MINOR)
    @Step("Запланирован тягач и прицеп")
    @Test(priority = 11, dependsOnMethods = "testVehiclePlan")
    public void vehiclePlanning() {
        VehiclePlanning vehiclePlanning = new VehiclePlanning(driver);
        vehiclePlanning.VehiclePlanOpen();
        logger.info("Запланирован тягач и прицеп");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("Открыта страница перевозок")
    @Test(priority = 12, dependsOnMethods = "vehiclePlanning")
    public void goToVehile() {
        VehicleRoute vehicleRoute = new VehicleRoute(driver);
        vehicleRoute.clickSomeButtonInFrame();
        logger.info("Рейс заполнен");
    }

    @Severity(SeverityLevel.NORMAL)
    @Step("Заказ завершен, сформирован счет")
    @Test(priority = 13, dependsOnMethods = "goToVehile")
    public void finalizeInvoice() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.readyInInvoicing();
        orderPage.obrabotkaSchet();
        ReadyInvoic readyInvoic = new ReadyInvoic(driver);
        readyInvoic.SchetRuchnoy();
        Invoice invoice = new Invoice(driver);
        invoice.fullSchet();
        logger.info("Заказ завершен, сформирован счет");
        logger.info("Конец теста \"На создание и заполнение заявки, планирования рейса, учёта счёта\" ");
    }

    // @Step("Считываю номер учтённого счёта")
    // @Test(priority = 14, dependsOnMethods = "finalizeInvoice")
    // public void goToServices2() {
    // ZayavkaByPage servicePage = new ZayavkaByPage(driver);
    // servicePage.clickSomeButtonInService();
    // logger.info("Переход в сервисы");
    // }

    // @Step("Считываю номер учтённого счёта")
    // @Test(priority = 15, dependsOnMethods = "finalizeInvoice")
    // public void readyNoCheck() {
    // OpenInvoice openInvoice = new OpenInvoice(driver);
    // String invoiceNumber = openInvoice.extractInvoiceNumber();
    // logger.info("Номер считан: " + invoiceNumber);
    // logger.info("номер считан");
    // }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
    }

    private void takeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] screenshot = null;
        try {
            screenshot = FileUtils.readFileToByteArray(srcFile);
            saveScreenshot(screenshot); // Это сохранит скриншот в отчет Allure
        } catch (IOException e) {
            logger.error("Ошибка при чтении файла скриншота: " + e.getMessage());
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = "screenshots/" + testName + "_" + timestamp + ".png";

        try {
            new File("screenshots").mkdirs(); // Создаст папку, если её нет
            FileUtils.copyFile(srcFile, new File(screenshotName));
            logger.error("Скриншот сохранен: " + screenshotName);
        } catch (IOException e) {
            logger.error("Ошибка при сохранении скриншота: " + e.getMessage());
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
            logger.info("Браузер закрыт");
        }
    }
}