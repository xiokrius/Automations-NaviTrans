package com.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.ConfigManager;
import com.example.PagesOrder.FrameSwitcher;
import com.example.PagesOrder.Invoice;
import com.example.PagesOrder.OpenInvoice;
import com.example.PagesOrder.OrderPage;
import com.example.PagesOrder.PageTransp;
import com.example.PagesOrder.ReadyInvoic;
import com.example.PagesOrder.VehiclePlanning;
import com.example.PagesOrder.VehicleRoute;
import com.example.PagesOrder.ZayavkaByPage;
import com.example.PagesOrder.ZayavkaPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.example.PagesOrder.QLoginTest;

public class CreateManyOrdersTest {

    private WebDriver driver;
    private QLoginTest loginTest;
    private static final Logger logger = LogManager.getLogger(CreateManyOrdersTest.class);

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://192.168.1.13:8080/BC210-TEST/SignIn?ReturnUrl=%2FBC210-TEST%2F");
        loginTest = new QLoginTest(driver);
    }

    @Test(priority = 1)
    public void login() {
        String login = ConfigManager.getProperty("inputLogin");
        String password = ConfigManager.getProperty("inputPassword");
        loginTest.inputLogin(login);
        loginTest.inputPassword(password);
        loginTest.clickLoginButton();
        logger.info("Логин и пароль введены");
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void createOrders() {
        for (int i = 0; i < 1; i++) {
            System.out.println("Создание заявки " + (i + 1));
            createOrder(i);
            logger.info("Переход на страницу транспортных заявок");
        }
    }

    private void createOrder(int iteration) {
        performAction(() -> {
            ZayavkaPage zayavkaPage = loginTest.goToZayavkaPage();
            zayavkaPage.CreateNewZayavkaCZ();
            zayavkaPage.NewOrderCreate();
            logger.info("Создали новую заявку");
            OrderPage orderPage = new OrderPage(driver);
            orderPage.fillOrderForm();
            logger.info("Заполнили основными данными");
            orderPage.PerevozkaInFrameIteration(iteration);

            PageTransp pageTransp = new PageTransp(driver);
            logger.info("перешли в планирование перевозки");
            pageTransp.OpenOrLoadingLocationIteration(iteration);
            logger.info("Выпускаем заказ");
            orderPage.obrabotkaVypustit();
            logger.info("Выпустили заказ");
            orderPage.vehiclePlan();
            logger.info("Планируем рейс");
            orderPage.PlanOpen();

            VehiclePlanning vehiclePlanning = new VehiclePlanning(driver);
            logger.info("Вносим данные в рейсе");
            vehiclePlanning.VehiclePlanOpen();

            VehicleRoute vehicleRoute = new VehicleRoute(driver);
            logger.info("Выходим из рейса обратно на страницу транспортной заявки");
            vehicleRoute.clickSomeButtonInFrame();

            ZayavkaByPage zayavkaByPage = new ZayavkaByPage(driver);
            logger.info("Переход в сервисы");
            zayavkaByPage.clickSomeButtonInService();

            OpenInvoice openInvoice = new OpenInvoice(driver);
            logger.info("Открытие сервисов");
            openInvoice.OpenServices();
            logger.info("Заполнили сервисы и вышли");

            logger.info("Готово к инвойсированию");
            orderPage.readyInInvoicing();
            logger.info("Нажали готово к инвойсированию, переходим к созданию счёта");
            orderPage.obrabotkaSchet();
            logger.info("Нажали обработка/счёт");

            ReadyInvoic readyInvoic = new ReadyInvoic(driver);
            logger.info("Нажэали счёт номер ручной");
            readyInvoic.SchetRuchnoy();

            Invoice invoice = new Invoice(driver);
            logger.info("Выпускаем и учитываем счёт");
            invoice.fullSchet();
        });
    }

    private void performAction(Runnable action) {
        action.run();
        new FrameSwitcher(driver).returnToMainContent();
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
    }

    private void takeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
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
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}