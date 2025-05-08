package com.integration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.ConfigManager;
import com.example.PagesOrder.QLoginTest;
import com.example.PaymentPages.PaymentLogbook;
import com.example.PaymentPages.TestPaymentLogbook;
import com.utils.CreateManyOrdersTest;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Создание оплаты по счёту")
@Feature("Оплата счёта")
public class PaymentOrderTest {

    private WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;
    private QLoginTest loginTest;
    private static final Logger logger = LogManager.getLogger(CreateManyOrdersTest.class);

    @BeforeClass
    public void setup() {

        logger.info("Начало теста \"На создание и заполнение заявки, планирования рейса, учёта счёта\" ");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigManager.getProperty("URLAutorisedNavi"));
        loginTest = new QLoginTest(driver);
        logger.info("Браузер запущен и открыт URL");
    }

    @Story("Авторизация")
    @Description("Вход в систему с валидными credentials")
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

    @Story("Создание платежа")
    @Description("Создание и заполнение платежной заявки")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Открытие страницы платежей и заполнение обязательных полей")
    @Test(priority = 2, dependsOnMethods = "login")
    public void goToPaymentJournal() {

        TestPaymentLogbook paymentTest = new TestPaymentLogbook(driver);

        paymentTest.openPaymentLoogbook();

        paymentTest.fillPostingDate();

        paymentTest.fillPaymentDate();

        paymentTest.fillDocumentNo();

        paymentTest.fillAccountType();

        paymentTest.fillAccountNo();

        paymentTest.fillMessagetoRecipient();

        paymentTest.fillCurrencyCode();

        paymentTest.fillBalAccountNo();

        paymentTest.fillAmount();

        paymentTest.openUchetPechat();

        paymentTest.openOpalaScheta();
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

    // @AfterClass
    // public void teardown() {
    // if (driver != null) {
    // driver.quit();
    // logger.info("Браузер закрыт");
    // }
    // }

}
