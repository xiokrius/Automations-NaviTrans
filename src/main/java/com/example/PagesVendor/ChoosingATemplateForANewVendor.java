package com.example.PagesVendor;

import com.example.ConfigManager;
import com.example.Environment.BasePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChoosingATemplateForANewVendor extends BasePage {

    private String ValueVendor = ConfigManager.getProperty("SelectedVendor");
    private static final Logger logger = LogManager.getLogger(ChoosingATemplateForANewVendor.class);

    public ChoosingATemplateForANewVendor(WebDriver driver) {

        super(driver);

    }

    // С локатором на xpath описания поставщиков очень аккуратно, он в обще с божьей
    // помощью работает
    public void choosingATemplate() {

        switchToIframe();

        String vendor = ValueVendor.trim();
        String normalizedVendor = vendor.replace("'", "\\'");
        logger.info("Decoded vendor value: " + normalizedVendor);
        try {
            WebElement Choosing = waitAndGetVisibleElement(By.xpath(
                    "//div[@title='Выбор шаблона для нового поставщика']"));
            logger.info("тут нашли окно");

            WebElement TypeVendor = waitAndGetVisibleElement(
                    By.xpath("//a[contains(text(), '" + normalizedVendor + "')]"));

            logger.info("нашли кнопки тип поставщика");

            TypeVendor.click();

            logger.info("клик");

            try {

                WebElement SpanOkVendor = waitAndGetVisibleElement(By.xpath(
                        "//p[@title='Блок адрес тоже?']"));

                logger.info("нашли ласт окно");

                WebElement popupConfirmButton = waitAndGetClickableElement(
                        By.xpath("//button[contains(@class, '1632124310')]/span[text()='Да']"));
                logger.info("клик да");

                popupConfirmButton.click();

            } catch (Exception o) {
                logger.info("Ну пиздец, окно не дождались");
            }

        } catch (Exception e) {

            logger.info("Элемент не найден: " + e.getMessage());
            // Дополнительная диагностика
            logger.info("Искомый текст: '" + vendor + "'");

        }

        returnToMainContent();
    }
}
