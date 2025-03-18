package com.unit;

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
import com.example.PagesInventory.TransportEquipment;
import com.example.PagesInventory.VehicleTrailerDriver;
import com.example.PagesOrder.QLoginTest;

import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Управление клиентами")
@Feature("Создание клиента, контакта и заявки на нового клиента")
public class LanguageTestDriver {

    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger(LanguageTestDriver.class);
    private static final String BASE_URL = ConfigManager.getProperty("BaseURL");
    private static final String LOGIN = ConfigManager.getProperty("inputLogin");
    private static final String PASSUSER1 = ConfigManager.getProperty("inputPassword");

    @BeforeClass
    public void setup() {
        logger.info("Инициализация драйвера и открытие браузера");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Перешли в окно ввода логина пароля");
        driver.get(BASE_URL);
        logger.info("Вход выполнен успешно");
    }

    @Test(priority = 1)
    @Story("Проверка переводов в оборудования ТС")
    @Severity(SeverityLevel.BLOCKER)
    public void createContactAndClient() {
        logger.info("Создание нового контакта и клиента");
        QLoginTest loginTest = new QLoginTest(driver);
        logger.info("Авторизация по логину и паролю");
        loginTest.inputLogin(ConfigManager.getProperty("inputLogin"));
        loginTest.inputPassword(ConfigManager.getProperty("inputPassword"));
        loginTest.clickLoginButton();
        logger.info("Авторизация выполнена успешно");

        TransportEquipment testDriver = new TransportEquipment(driver);
        logger.info("Переход в оборудования ТС");
        testDriver.OpenInventory();

        VehicleTrailerDriver testLanguage = new VehicleTrailerDriver(driver);
        logger.info("Проверка переводов в оборудования ТС");
        testLanguage.PageTrailerDriver();
        logger.info("Проверка выполнена успешно");

    }

    @AfterClass
    public void tearDown() {
        logger.info("Завершение теста и закрытие браузеров");
        if (driver != null)
            driver.quit();
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

}
