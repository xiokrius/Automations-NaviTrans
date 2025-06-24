package com.example.Environment;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;


public class Settings extends BasePage {

        private String PasswordOldValue = ConfigManager.getProperty("PasswordOld");
        private String EmailValue = ConfigManager.getProperty("EmailValue");

        private WebDriver driver;
        private WebDriverWait wait;
        private JavascriptExecutor js;

        private static final Logger logger = LogManager.getLogger(Settings.class);

        public Settings(WebDriver driver) {

                super(driver); 

        }

        public void setInputValue(WebElement element, String value) {
                js.executeScript(
                                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                element, value);
                System.out.println("Заполнили значение: " + value);
        }

        public void goToSettings() {

                logger.info("Переход на страницу настроек");

                driver.get(
                                "http://192.168.1.13:8080/BC210-TEST/?company=Trans_Solutions_CZ&page=9807&dc=0&bookmark=24%3beJQ1dwCReeYyo70A4kWn3vFF3%2bIEOQ%3d%3d");

                switchToIframe();

                logger.info("Переход выпонлен");

                WebElement pass3 = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//div[@controlname='Authentication Email']/following::input[contains(@id, 'ee')]")));

                logger.info("1");
                // Адрес электронной почты для проверки подлинности

                WebElement openButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//span[text()='Проверка подлинности пароля Business Central']")));

                openButton.click();

                WebElement Email = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[@aria-label='Просмотреть или обновить значение для Пароль']")));

                logger.info("2");

                Email.click();
                logger.info("3");

                setInputValue(Email, EmailValue);

                logger.info("4");

                try {

                        WebElement WindowPass = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                                        "//div[@title='Правка - Введите пароль']")));

                        logger.info("5");

                        WebElement PasswordOld = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                        "//a[text()='Пароль']/following-sibling::div//input[@type='password']")));

                        // "//input[@type='password'][2]")));
                        logger.info("6");
                        setInputValue(PasswordOld, PasswordOldValue);

                        PasswordOld.click(); // Инициализация, ну а шо поделать, навик есть навик

                        logger.info("7");

                } catch (Exception e) {

                        System.out.println("Ошибка при взаимодействии с элементом");
                        logger.info("obhod");

                }

                logger.info("tuta");
                WebElement PasswordOld2 = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                                "//a[text()='Подтвердить пароль']/following-sibling::div//input[@type='password']")));
                // "//a[text()='Пароль']/following-sibling::div//input[@type='password']")));
                logger.info("ne");
                setInputValue(PasswordOld2, PasswordOldValue);

                PasswordOld2.click();
                // div[@controlname='Пароль']

                WebElement buttonInOK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "//button//span[text()='ОК']")));

                buttonInOK.click();

                returnToMainContent();

        }

}
