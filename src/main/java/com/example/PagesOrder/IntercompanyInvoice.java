package com.example.PagesOrder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.example.ConfigManager;
import com.example.Environment.BasePage;


import org.openqa.selenium.By;


//              !!!!!!!!!!!!!!!!!!   Page Intercompany   !!!!!!!!!!!!!!!!!!

public class IntercompanyInvoice extends BasePage{

        private String InputIntercompanyCodeValue = ConfigManager.getProperty("InputIntercompanyCodeValue");
        private String SupplierValue = ConfigManager.getProperty("SupplierValue");
        private String PriceIntercompanyValue = ConfigManager.getProperty("PriceIntercompanyValue");



        public IntercompanyInvoice(WebDriver driver) {
                super(driver);
        }




        private boolean isElementVisible(WebElement element) {
                return (Boolean) getJS().executeScript(
                                "return arguments[0].offsetWidth > 0 && arguments[0].offsetHeight > 0;",
                                element);
        }

        public void InterCompanyInfo() {

                switchToIframe();

                System.out.println("Начинаем IntercompanyInvoice/InterCompanyInfo");

                // Заполняем поле "Код Интеркомпани"
                WebElement inputIntercompanyCode = waitAndGetClickableElement(
                                By.xpath("//td[contains(@controlname, 'Cost Code')]//descendant::input[contains(@id, 'ee')]"));
                System.out.println("Нашли поле код Интеркомпани");

                setInputValue(inputIntercompanyCode, InputIntercompanyCodeValue);

                // Заполняем поле "Код Поставщика"
                WebElement supplier = waitAndGetClickableElement(
                                By.xpath("//td[contains(@controlname, 'Vendor')]//descendant::input[contains(@id, 'ee')]"));
                System.out.println("Нашли поле код Поставщик");
                setInputValue(supplier, SupplierValue);

                // Заполняем поле "Цена"
                WebElement priceIntercompany = getDriver().findElement(
                                By.xpath("//td[contains(@controlname, 'Unit price')]//descendant::input[contains(@id, 'ee')]"));
                WebElement scrollContainer = getDriver().findElement(
                                By.xpath("//div[contains(@class, 'freeze-pane-scrollbar')]"));

                scrollToElementHorizontally(scrollContainer, priceIntercompany);

                // Проверяем видимость элемента
                if (!isElementVisible(priceIntercompany)) {
                        throw new RuntimeException("Элемент все еще не виден после горизонтальной прокрутки!");
                }

                setInputValue(priceIntercompany, PriceIntercompanyValue);

                System.out.println("Заполнили поле Цена значением: " + PriceIntercompanyValue);

                WebElement ProcessButton = waitAndGetVisibleElement(
                                By.xpath("//*[@aria-label=' процесс']"));

                ProcessButton.click();

                WebElement CreateICfile = waitAndGetVisibleElement(
                                By.xpath("//*[@aria-label='Создать файл IC']"));

                CreateICfile.click();

                switchToIframe();

        }

}
