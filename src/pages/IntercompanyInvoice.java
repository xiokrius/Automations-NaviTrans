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

        public IntercompanyInvoice(WebDriver driver) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Инициализация
        }

        private void switchToIframe() {
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
                driver.switchTo().frame(iframe);
        }

        public void InterCompanyInfo() {

                switchToIframe();
                System.out.println("Начали интеркомпаниИнфо.");

                // Ожидаем появления инпут-поля с динамическим ID
                WebElement InputIntercompanyCode = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[contains(@id, 'fee') and contains(@aria-labelledby, 'pd')]")));
                System.out.println("Нашли поле код Интеркомпани");

                // Заполняем значение через JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                InputIntercompanyCode, InputIntercompanyCodeValue);

                System.out.println("Заполнили значение: " + InputIntercompanyCodeValue);

                WebElement Supplier = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[contains(@id, 'hee') and contains(@aria-labelledby, 'pb')]")));
                System.out.println("Нашли поле код Поставщик");

                js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                                Supplier, SupplierValue);

                System.out.println("Заполнили значение: " + SupplierValue);

                // Находим элемент, к которому нужно проскроллить (ЦЕНА)
                WebElement PriceIntercompany = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[contains(@id, 'mee') and contains(@aria-labelledby, 'p6')]")));

                // Скроллинг к элементу
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                                PriceIntercompany);

                // Ждем немного, чтобы элемент полностью появился
                wait.until(ExpectedConditions.elementToBeClickable(PriceIntercompany));

                // фокус и события для ввода
                try {
                        // Добавление фокуса на поле
                        js.executeScript("arguments[0].focus();", PriceIntercompany);
                        Thread.sleep(1000); // Небольшая задержка после фокуса

                        // Используем JavaScript для ввода значения
                        js.executeScript(
                                        "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change')); arguments[0].dispatchEvent(new Event('blur'));",
                                        PriceIntercompany, PriceIntercompanyValue);

                        System.out.println("Заполнили поле Цена значением: " + PriceIntercompanyValue);

                        // Ожидаем изменения значения в поле
                        wait.until(ExpectedConditions.attributeToBe(PriceIntercompany, "value",
                                        PriceIntercompanyValue));
                        Thread.sleep(1000); // Даем немного времени для обработки

                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

                // Проверка результата
                System.out.println("Price field value after input: " + PriceIntercompanyValue);
        }

}
