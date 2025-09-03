package com.e2e;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.ConfigManager;
import com.example.PagesOrder.QLoginTest;
import com.example.PaymentPages.PaymentCalendar;
import com.example.PaymentPages.PaymentLogbook;
import com.example.PaymentPages.TestPaymentLogbook;
import com.utils.CreateManyOrdersTest;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Проверка учёта платежа в таблице ДЗ")
@Feature("Проверка платежа")

public class PaymentOrderTest2 {

    private WebDriver driver;
    private WebDriverWait wait;
    private QLoginTest loginTest;
    private static final Logger logger = LogManager.getLogger(PaymentOrderTest2.class);

    @BeforeClass

    public void openListHeaderPayment() {

        logger.info("Начало теста \"На проверку учёта платежа в таблице ДЗ\" ");

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get(ConfigManager.getProperty("HeaderPaymentInvoice"));

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

    @Story("Переход на страницу ДЗ")
    @Severity(SeverityLevel.NORMAL)
    @Step("Переход в таблицу Дебиторской задолженности")
    @Test(priority = 2)
    public void goToListHeaderPayments() {
        driver.get(ConfigManager.getProperty("HeaderPaymentInvoice"));

        PaymentCalendar headerTest = new PaymentCalendar(driver);

        headerTest.searchPayment2();

        headerTest.makingOrderPayment();

    }
}
