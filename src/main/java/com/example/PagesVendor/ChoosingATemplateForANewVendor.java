package com.example.PagesVendor;

import com.example.ConfigManager;
import com.example.PagesOrder.FrameSwitcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

public class ChoosingATemplateForANewVendor {

    private WebDriver driver;
    private WebDriverWait wait;
    private FrameSwitcher frameSwitcher;
    private String ValueVendor = ConfigManager.getProperty("SelectedVendor");
    private static final Logger logger = LogManager.getLogger(ChoosingATemplateForANewVendor.class);

    public ChoosingATemplateForANewVendor(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.frameSwitcher = new FrameSwitcher(driver);

    }

    // С локатором на xpath описания поставщиков очень аккуратно, он в обще с божьей
    // помощью работает
    public void choosingATemplate() {

        frameSwitcher.switchToIframe();

        String vendor = ValueVendor.trim();
        String normalizedVendor = vendor.replace("'", "\\'");
        logger.info("Decoded vendor value: " + normalizedVendor);
        try {
            WebElement Choosing = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@title='Выбор шаблона для нового поставщика']")));
            logger.info("тут нашли окно");

            WebElement TypeVendor = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(text(), '" + normalizedVendor + "')]")));

            logger.info("нашли кнопку");

            new Actions(driver).moveToElement(TypeVendor).click().perform();

            logger.info("нашли кнопки тип поставщика");
            TypeVendor.click();
            logger.info("клик");

            WebElement SpanOk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "//button//span[text()='ОК']")));
            logger.info("тут нашли ок");

            SpanOk.click();
            logger.info("ок клик");
            try {

                WebElement SpanOkVendor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//p[@title='Блок адрес тоже?']")));

                logger.info("нашли ласт окно");

                WebElement popupConfirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(@class, '1632124310')]/span[text()='Да']")));
                logger.info("клик да");

                popupConfirmButton.click();
                popupConfirmButton.click();
                popupConfirmButton.click();
                popupConfirmButton.click();
                popupConfirmButton.click();
                popupConfirmButton.click();

            } catch (Exception o) {
                logger.info("Ну пиздец, окно не дождались");
            }

        } catch (Exception e) {

            logger.info("Элемент не найден: " + e.getMessage());
            // Дополнительная диагностика
            logger.info("Искомый текст: '" + vendor + "'");

        }

        frameSwitcher.returnToMainContent();
    }
}
