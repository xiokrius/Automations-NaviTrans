package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReadyInvoic {

    private WebDriver driver;

    public ReadyInvoic(WebDriver driver) {
        this.driver = driver;
    }

    public void SchetRuchnoy() {

        System.out.println("Cчёт-фактура/.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("designer-client-frame")));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в фрейм.");

        // НАШЛИ КНОПКУ ОБРАБОТКА
        WebElement SchetFakturaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/div[1]/div[4]/form/main/div[2]/div[5]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[1]/div/div/button/span")));
        System.out.println("Нашли первую кнопку Выпустить.");
        SchetFakturaButton.click();

        // НАШЛИ КНОПКУ Cчёт номер ручной
        WebElement schetNomerRuchnoyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/div[1]/div[4]/form/main/div[2]/div[5]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div/div/div/button/span")));
        System.out.println("Нашли Вторую кнопку, Счёт номер ручной .");

        schetNomerRuchnoyButton.click();

        try {
            WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                    "/html/body/div[1]/div[5]/form/div/main"))); // XPath окна
            System.out.println("Всплывающее окно обнаружено.");
            // Выполняем действия внутри окна
            WebElement popupConfirmButton = popupWindow.findElement(By.xpath(
                    "/html/body/div[1]/div[5]/form/div/main/div[3]/div/button[1]")); // Кнопка
            // подтверждения
            popupConfirmButton.click();
            System.out.println("Нажата кнопка 'Подтвердить' во всплывающем окне.");
        } catch (Exception e) {
            System.out.println("Всплывающее окно не появилось, продолжаем выполнение." + e.getMessage());
        }

    }

}
