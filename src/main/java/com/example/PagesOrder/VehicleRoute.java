package com.example.PagesOrder;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;
import com.example.Environment.BasePage;

public class VehicleRoute extends BasePage {

        private String ActualStartingDateValue = ConfigManager.getProperty("ActualStartingDateValue");
        private String ActualStartingTimeValue = ConfigManager.getProperty("ActualStartingTimeValue");
        private String ActualEndingDateValue = ConfigManager.getProperty("ActualEndingDateValue");
        private String ActualEndingTimeValue = ConfigManager.getProperty("ActualEndingTimeValue");
        private String SupplyKmValue = ConfigManager.getProperty("SupplyKmValue");
        private String StartingKmValue = ConfigManager.getProperty("StartingKmValue");
        private String EndingKmValue = ConfigManager.getProperty("EndingKmValue");

        public VehicleRoute(WebDriver driver) {

                super(driver);
        }

        public void clickSomeButtonInFrame() {

                System.out.println("Начинаем VehicleRoute/clickSomeButtonInFrame.");

                // Переключаемся в нужный фрейм
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

                switchToIframe();

                WebElement ActualStartingDate = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath(
                                                "//td[contains(@controlname, 'FTS_Actual Starting Date')]//descendant::input[contains(@id, 'ee')]")));
                System.out.println("1.");

                WebElement orderNumberSpan = getDriver()
                                .findElement(By.xpath("//a[text()='№ Заказа']/following-sibling::div//a"));

                String orderNumber = orderNumberSpan.getText();

                System.out.println("Номер заказа: " + orderNumber);
                WebElement ActualStartingTime = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath(
                                                "//td[contains(@controlname, 'FTS_Actual Start Time')]//descendant::input[contains(@id, 'ee')]")));
                System.out.println("2");
                WebElement ActualEndingDate = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath(
                                                "//td[contains(@controlname, 'FTS_Actual Ending Date')]//descendant::input[contains(@id, 'ee')]")));
                System.out.println("Перешли в фрейм.");
                WebElement ActualEndingTime = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath(
                                                "//td[contains(@controlname, 'FTS_Actual End Time')]//descendant::input[contains(@id, 'ee')]")));

                WebElement scrollContainer = getDriver().findElement(
                                By.xpath("(//div[contains(@class, 'freeze-pane-scrollbar')])[7]"));

                scrollToElementHorizontally(scrollContainer, ActualEndingTime);

                setInputValue(ActualStartingDate, ActualStartingDateValue);

                setInputValue(ActualStartingTime, ActualStartingTimeValue);

                setInputValue(ActualEndingDate, ActualEndingDateValue);

                setInputValue(ActualEndingTime, ActualEndingTimeValue);

                ActualStartingDate.click();
                ActualEndingTime.click();
                ActualEndingDate.click();
                ActualEndingTime.click();

                WebElement SupplyKm = getDriver().findElement(
                                By.xpath("//td[contains(@controlname, 'Supply Km')]//descendant::input[contains(@id, 'ee')]"));

                WebElement StartingKm = getDriver().findElement(
                                By.xpath("//td[contains(@controlname, 'Starting Km')]//descendant::input[contains(@id, 'ee')]"));

                WebElement EndingKm = getDriver().findElement(
                                By.xpath("//td[contains(@controlname, 'Ending Km')]//descendant::input[contains(@id, 'ee')]"));

                scrollToElementHorizontally(scrollContainer, SupplyKm);

                setInputValue(SupplyKm, SupplyKmValue);

                SupplyKm.click();

                setInputValue(StartingKm, StartingKmValue);
                setInputValue(EndingKm, EndingKmValue);

                StartingKm.click();
                EndingKm.click();

                try {

                        Thread.sleep(500);
                        // Ожидаем появления первой кнопки
                        WebElement BackPage = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//button[contains(@data-is-focusable, 'true') and contains(@title, 'Назад')]")));
                        System.out.println("Нашли кнопку назад.");

                        // Отладка +
                        System.out.println("Кнопка видима: " + BackPage.isDisplayed());
                        System.out.println("Кнопка доступна для клика: " + BackPage.isEnabled());

                        // Кликаем по первой кнопке
                        clickJSToElement(BackPage);
                        System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

                } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                }

                returnToMainContent();

                Properties orderProps = new Properties();
                try (FileOutputStream out = new FileOutputStream("src/main/resources/order.properties")) {
                        orderProps.setProperty("order.number", orderNumber);
                        orderProps.store(out, "Номер заказа");
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

}
