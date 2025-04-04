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
@Feature("Создание 2 заказов")

public class CreateOrderNewTest {
    private WebDriver driver;
    private QLoginTest loginTest;
    private static final Logger logger = LogManager.getLogger(CreateManyOrdersTest.class);

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://192.168.1.13:8080/BC210-TEST/SignIn?ReturnUrl=%2FBC210-TEST%2F");
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

    @Step("Открытие заявок")
    @Test(priority = 2, dependsOnMethods = "login")
    public void openZayavkaPage() {
        loginTest.goToZayavkaPage();
        logger.info("Открыта страница заявок");
    }

    @Step("Создана новая заявка")
    @Test(priority = 3, dependsOnMethods = "openZayavkaPage")
    public void createNewZayavka() {
        ZayavkaPage zayavkaPage = new ZayavkaPage(driver);
        zayavkaPage.CreateNewZayavkaCZ();
        zayavkaPage.NewOrderCreate();
        logger.info("Создана новая заявка");
    }

    @Step("Заполнена форма заказа")
    @Test(priority = 4, dependsOnMethods = "createNewZayavka")
    public void fillOrderForm() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm();
        logger.info("Заполнена форма заказа");
    }

    @Step("Обработан заказ: переход в перевозки")
    @Test(priority = 5, dependsOnMethods = "fillOrderForm")
    public void processOrder() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.PerevozkaInFrame();
        logger.info("Обработан заказ: переход в перевозки");
    }

    @Step("Установлены плановые даты перевозки")
    @Test(priority = 6, dependsOnMethods = "processOrder")
    public void setPlannedDates() {
        PageTransp pageTransp = new PageTransp(driver);
        pageTransp.OpenOrLoadingLocation();
        logger.info("Установлены плановые даты перевозки");
    }

    @Step("Установлены плановые даты перевозки")
    @Test(priority = 7, dependsOnMethods = "setPlannedDates")
    public void orderFull() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.obrabotkaVypustit();
        orderPage.vehiclePlan();
        orderPage.PlanOpen();
        logger.info("Установлены плановые даты перевозки");
    }

    @Step("Запланирован тягач и прицеп")
    @Test(priority = 8, dependsOnMethods = "orderFull")
    public void vehiclePlanning() {
        VehiclePlanning vehiclePlanning = new VehiclePlanning(driver);
        vehiclePlanning.VehiclePlanOpen();
        logger.info("Запланирован тягач и прицеп");
    }

    @Step("Открыт раздел сервисов")
    @Test(priority = 9, dependsOnMethods = "vehiclePlanning")
    public void goToVehile() {
        VehicleRoute vehicleRoute = new VehicleRoute(driver);
        vehicleRoute.clickSomeButtonInFrame();
        logger.info("Открыт раздел сервисов");
    }

    @Step("Открыт раздел сервисов")
    @Test(priority = 10, dependsOnMethods = "goToVehile")
    public void goToServices() {
        ZayavkaByPage servicePage = new ZayavkaByPage(driver);
        servicePage.clickSomeButtonInService();
        logger.info("Открыт раздел сервисов");
    }

    @Step("Обработаны сервисы")
    @Test(priority = 11, dependsOnMethods = "goToServices")
    public void processServices() {
        OpenInvoice openInvoice = new OpenInvoice(driver);
        openInvoice.OpenServices();
        logger.info("Обработаны сервисы");
    }

    @Step("Заказ завершен, сформирован счет")
    @Test(priority = 12, dependsOnMethods = "processServices")
    public void finalizeInvoice() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.readyInInvoicing();
        orderPage.obrabotkaSchet();
        ReadyInvoic readyInvoic = new ReadyInvoic(driver);
        readyInvoic.SchetRuchnoy();
        Invoice invoice = new Invoice(driver);
        invoice.fullSchet();
        logger.info("Заказ завершен, сформирован счет");
    }

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