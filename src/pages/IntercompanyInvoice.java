package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

//              !!!!!!!!!!!!!!!!!!   Page Intercompany   !!!!!!!!!!!!!!!!!!

public class IntercompanyInvoice {

        private String InputIntercompanyCodeValue = ConfigManager.getProperty("InputIntercompanyCodeValue");
        private String SupplierValue = ConfigManager.getProperty("SupplierValue");
        private String PriceIntercompanyValue = ConfigManager.getProperty("PriceIntercompanyValue");

        private WebDriver driver;
        private WebDriverWait wait;
        private JavascriptExecutor js;

        public IntercompanyInvoice(WebDriver driver) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Инициализация WebDriverWait
                this.js = (JavascriptExecutor) driver; // Инициализация JavascriptExecutor
        }

        private void switchToIframe() {
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.className("designer-client-frame")));
                driver.switchTo().frame(iframe);
        }

        private void setInputValue(WebElement element, String value) {
                js.executeScript(
                                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                element, value);
                System.out.println("Заполнили значение: " + value);
        }

        private void scrollToElementHorizontally(WebElement scrollContainer, WebElement targetElement) {
                js.executeScript(
                                "arguments[0].scrollLeft = arguments[0].scrollLeft + arguments[1].getBoundingClientRect().left;",
                                scrollContainer, targetElement);
        }

        private boolean isElementVisible(WebElement element) {
                return (Boolean) js.executeScript(
                                "return arguments[0].offsetWidth > 0 && arguments[0].offsetHeight > 0;",
                                element);
        }

        public void InterCompanyInfo() {
                switchToIframe();

                System.out.println("Начинаем IntercompanyInvoice/InterCompanyInfo");

                // Заполняем поле "Код Интеркомпани"
                WebElement inputIntercompanyCode = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[contains(@id, 'qee') and contains(@aria-labelledby, 'lo')]")));
                System.out.println("Нашли поле код Интеркомпани");
                setInputValue(inputIntercompanyCode, InputIntercompanyCodeValue);

                // Заполняем поле "Код Поставщика"
                WebElement supplier = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[contains(@id, 'see') and contains(@aria-labelledby, 'lm')]")));
                System.out.println("Нашли поле код Поставщик");
                setInputValue(supplier, SupplierValue);

                // Заполняем поле "Цена"
                WebElement priceIntercompany = driver.findElement(
                                By.xpath("//input[contains(@id, 'xee') and contains(@aria-labelledby, 'b5lh')]"));
                WebElement scrollContainer = driver.findElement(
                                By.xpath("//div[contains(@class, 'freeze-pane-scrollbar')]"));

                scrollToElementHorizontally(scrollContainer, priceIntercompany);

                // Проверяем видимость элемента
                if (!isElementVisible(priceIntercompany)) {
                        throw new RuntimeException("Элемент все еще не виден после горизонтальной прокрутки!");
                }

                setInputValue(priceIntercompany, PriceIntercompanyValue);

                System.out.println("Заполнили поле Цена значением: " + PriceIntercompanyValue);

                WebElement ProcessButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//*[@aria-label=' процесс']")));

                ProcessButton.click();

                WebElement CreateICfile = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//*[@aria-label='Создать файл IC']")));

                CreateICfile.click();

        }

}
