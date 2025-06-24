package com.example.PagesOrder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.Environment.BasePage;

public class ReadyInvoic extends BasePage {

    private WebDriver driver;

    public ReadyInvoic(WebDriver driver) {

        super(driver);
    }

    public void SchetRuchnoy() {

        System.out.println("Начинаем ReadyInvoic/SchetRuchnoy");

        switchToIframe();
        System.out.println("Перешли в фрейм.");

        // НАШЛИ КНОПКУ ОБРАБОТКА
        WebElement SchetFakturaButton = waitAndGetClickableElement(By.xpath(
                "//button[@aria-label='Счет-фактура']"));
        System.out.println("Нашли первую кнопку Cчёт фактура.");
        SchetFakturaButton.click();

        // НАШЛИ КНОПКУ Cчёт номер ручной
        WebElement schetNomerRuchnoyButton = waitAndGetClickableElement(By.xpath(
                "//button[@aria-label='Счет № ручн.']"));
        System.out.println("Нашли Вторую кнопку, Счёт номер ручной .");

        schetNomerRuchnoyButton.click();

        try {
            WebElement popupWindow = waitAndGetPresentElement(By.xpath(
                    "//p[contains(@title, 'Вы хотите создать счета?')]")); // XPath окна
            System.out.println("Всплывающее окно обнаружено.");
            // Выполняем действия внутри окна
            WebElement popupConfirmButton = popupWindow.findElement(By.xpath(
                    "//button[contains(@class, '1632124310')]/span[text()='Да']")); // Кнопка
            // подтверждения
            popupConfirmButton.click();
            System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");
        } catch (Exception e) {
            System.out.println("Всплывающее окно не появилось, продолжаем выполнение." + e.getMessage());
        }

        returnToMainContent();

    }

}
