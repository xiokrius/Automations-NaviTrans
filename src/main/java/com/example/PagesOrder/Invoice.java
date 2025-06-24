package com.example.PagesOrder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.Environment.BasePage;



public class Invoice extends BasePage{



        public Invoice(WebDriver driver) {
                super(driver);
        }

        public void fullSchet() {

                System.out.println("Начинаем Invoice/fullSchet");

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                switchToIframe();

                System.out.println("Перешли в фрейм.");

                // НАШЛИ КНОПКУ Учёт(для первой итерации нужно тут прикрутить ожидание)
                WebElement uchetButton = waitAndGetClickableElement(
                                By.xpath("//button[@aria-label='Учет']"));
                System.out.println("Нашли первую кнопку Учёт.");
                uchetButton.click();

                // НАШЛИ КНОПКУ Учёт
                WebElement uchetFullButton = waitAndGetClickableElement(By.xpath(
                                "//button[contains(@role, 'menuitem') and contains(@title, 'F9') and contains(@aria-label, 'Учет')]"));
                System.out.println("Нашли Вторую кнопку, Учёт .");

                uchetFullButton.click();

                try {
                        WebElement popupWindow = waitAndGetClickableElement(
                                        By.xpath("//p[contains(@title, 'Отсутствует экспорт!')]")); // XPath

                        System.out.println("Всплывающее окно обнаружено.");

                        // Ожидаем появления кнопки 'Да' и кликаем по ней
                        WebElement popupConfirmButton = waitAndGetClickableElement(
                                        By.xpath("//button[contains(@class, '1632124310')]/span[text()='Да']"));
                        popupConfirmButton.click();
                        System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");

                        // Добавляем небольшую паузу, чтобы дать время на обновление страницы
                        Thread.sleep(500);

                        // Ожидаем исчезновения старой кнопки
                        wait.until(ExpectedConditions.stalenessOf(popupConfirmButton));

                        // Ожидаем исчезновения старой кнопки и появления новой
                        WebElement UchetSchetButton = waitAndGetClickableElement(
                                        By.xpath("//button[contains(@class, '1632124310')]/span[text()='Да']"));
                        UchetSchetButton.click();
                        System.out.println("Нажата кнопка 'Учет'.");

                        wait.until(ExpectedConditions.stalenessOf(UchetSchetButton));

                        // Даем странице немного времени для обновления перед следующим действием
                        Thread.sleep(500);

                        // Ожидаем появления кнопки 'Нет' и кликаем по ней
                        WebElement PerehodVSchetNet = waitAndGetClickableElement(
                                        By.xpath("//button[contains(@class, '1632124310')]/span[text()='Нет']"));
                        PerehodVSchetNet.click();
                        System.out.println("Нажата кнопка 'Нет'.");

                } catch (Exception e) {
                        System.out.println("Всплывающее окно не появилось, продолжаем выполнение." + e.getMessage());
                }

                returnToMainContent();
        }

}
