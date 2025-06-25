package com.example.PagesOrder;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.ConfigManager;
import com.example.Environment.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

//ПЕРЕХОД НА ПЕЙДЖ С ЗАКАЗАМИ И ВХОД В ЗАЯВКУ(1 ПОКА ПО СПИСКУ)

public class ZayavkaPage extends BasePage {

        private String TSGroupCodeValue = ConfigManager.getProperty("TSGroupCodeValue");
        private String ButtonNewZayavkaValue = ConfigManager.getProperty("ButtonNewZayavkaValue");

        public ZayavkaPage(WebDriver driver) {

                super(driver);
        }

        public void clickZayavkaBY() {

                System.out.println("clickzayavkaBy");

                switchToIframe(); // Переключаемся в фрейм

                // Шаг 2: КНОПКА ЗАЯВКИ на пейдже страницы заявок
                WebElement button = waitAndGetClickableElement(
                                By.xpath(
                                                "/html/body/div[1]/div[2]/form/div/div[2]/main/div[2]/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[6]/td[3]/a"));

                try {
                        button.click();
                        System.out.println("Клик по кнопке выполнен.");
                } catch (Exception e) {
                        System.out.println("Не удалось кликнуть по кнопке: " + e.getMessage());
                }

                returnToMainContent();

        }

        public void CopiedOrders() {

                System.out.println("CopiedOrders");

                switchToIframe(); // Переключаемся в фрейм

                // Шаг 2: КНОПКА ЗАЯВКИ на пейдже страницы заявок
                WebElement DateButton = waitAndGetClickableElement(
                                By.xpath("//a[contains(@title, 'Дата заявки')]"));

                try {
                        DateButton.click();
                        System.out.println("Клик по кнопке выполнен.");
                        Thread.sleep(300);
                        DateButton.click();
                        System.out.println("Клик по кнопке выполнен.");

                } catch (Exception e) {
                        System.out.println("Не удалось кликнуть по кнопке: " + e.getMessage());
                }

                try {
                        WebElement OrdersPage = waitAndGetClickableElement(
                                        By.xpath("//a[contains(@title, 'Открыть запись ')]"));
                        Thread.sleep(500);

                        OrdersPage.click();
                        System.out.println("Клик по заявке выполнен");
                        OrdersPage.click();
                } catch (Exception e) {
                        System.out.println("Не удалось кликнуть по кнопке: " + e.getMessage());
                }

                returnToMainContent();
        }

        public void CreateNewZayavkaCZ() {

                System.out.println("ZayavkaPage/CreateNewZayavkaCZ");

                switchToIframe();

                // Шаг 2: КНОПКА НОВЫЙ(для создания заказа)
                WebElement Noviy = waitAndGetClickableElement(
                                By.xpath(
                                                "//button[@aria-label='Новый']//span[contains(@class, '2dIQkzAbHK18inPTYeiPdF')]"));

                Noviy.click();

                // Кнопка Создать(потом надо дать нормальный нейм)
                WebElement button = waitAndGetClickableElement(
                                By.xpath("//button[@aria-label='Создать' and contains(@class, 'ms-Button')]"));
                button.click();

                returnToMainContent();

        }

        public void NewOrderCreate() {

                System.out.println("Начинаем ZayavkaPage/NewOrderCreate");

                switchToIframe(); // Переключаемся в фрейм;

                // Поле TSGroupCode ПЕРЕДЕЛАТЬ, НУЖНО УКАЗЫВАТЬ ПО ПУТИ, Т.К. ARIA МЕНЯЕТСЯ
                WebElement TSGroupCode = waitAndGetClickableElement(
                                By.xpath("//a[contains(@title, 'элемента TS Group code')]/following-sibling::input"));

                WebElement ButtonInOk = waitAndGetClickableElement(
                                By.xpath("//button[contains(@id, 'b1')]/span[text()='ОК']"));

                setInputValue(TSGroupCode, TSGroupCodeValue);
                System.out.println("Заполнил ТС групп Код: " + TSGroupCodeValue);

                System.out.println("Клик для инициализации.");

                // Шаг 2: Поле Отдел ПЕРЕДЕЛАТЬ, НУЖНО УКАЗЫВАТЬ ПО ПУТИ, Т.К. ARIA МЕНЯЕТСЯ
                WebElement ButtonNewZayavka = waitAndGetClickableElement(
                                By.xpath("//a[contains(@title, 'элемента Отдел')]/following-sibling::input"));

                setInputValue(ButtonNewZayavka, ButtonNewZayavkaValue);
                System.out.println("Заполнили отдел: " + ButtonNewZayavkaValue);

                ButtonNewZayavka.click();
                System.out.println("Клик для инициализации.");

                ButtonInOk.click();

                returnToMainContent();

        }

}