package com.e2e;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Date;
import com.example.ConfigManager;
import com.example.PagesOrder.QLoginTest;
import com.example.PagesVendor.CardOfVendor;
import com.example.PagesVendor.ChoosingATemplateForANewVendor;
import com.example.PagesVendor.ListOfVendor;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Создание поставщика")
@Feature("Создание поставщика и внесение данных по поставщику")

public class CreateAndOpenVendorTest {
    private WebDriver driver;
    private QLoginTest loginTest;
    private static final Logger logger = LogManager.getLogger(CreateAndOpenVendorTest.class);

    @BeforeClass
    public void setup() {

        logger.info("Начало теста \"На создание, заполнение и подтверждение поставщика\" ");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://192.168.1.13:8080/BC210-TEST/SignIn?ReturnUrl=%2FBC210-TEST%2F");
        loginTest = new QLoginTest(driver);
        logger.info("Браузер запущен и открыт URL");
    }

    @Story("Создание и запонление поставщика")
    @Description("Создаёт поставщика")
    @Severity(SeverityLevel.BLOCKER)

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

    @Severity(SeverityLevel.CRITICAL)
    @Step("Переход на страницу поставщиков и создание нового")
    @Test(priority = 2, dependsOnMethods = "login")
    public void openingOfTheSupplierCountry() {
        ListOfVendor goVendor = new ListOfVendor(driver);
        goVendor.SwitchToVendorsList();
        logger.info("Открыт страница поставщиков");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("Выбор опций поставщика")
    @Test(priority = 3, dependsOnMethods = "openingOfTheSupplierCountry")
    public void creatingNewSupplier() {
        ChoosingATemplateForANewVendor goChoosVendor = new ChoosingATemplateForANewVendor(driver);
        goChoosVendor.choosingATemplate();
        logger.info("Создана новая заявка");
    }

    @Step("Заполнение карточки поставщика")
    @Test(priority = 4, dependsOnMethods = "creatingNewSupplier")
    public void fillingInANewSupplier() {
        CardOfVendor VendorList = new CardOfVendor(driver);
        VendorList.completionOfNameCardVendor();
        logger.info("Заполнен нейм поставщика");
        VendorList.completionOfListOfPaymentsCardVendor();

        VendorList.fillingInThePaymentCode();

        VendorList.fillingCityInVendor();

        VendorList.buttonInBack();

        logger.info("Конец теста \"На создание поставщика\" ");

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
