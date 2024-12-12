package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// КОПИРОВАНИЕ ЗАЯВКИ ПО ДЕФОЛТУ

public class ZayavkaByPage {

    private WebDriver driver;

    public ZayavkaByPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSomeButtonInFrame() {

        System.out.println("Начинаем ZayavkaByPage.");

        // Переключаемся в нужный фрейм
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в фрейм.");

        // Ожидаем появления первой кнопки
        WebElement buttonInFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button/span")));
        System.out.println("Нашли первую кнопку.");

        // Кликаем по первой кнопке
        buttonInFrame.click();
        System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

        WebElement buttonInFrame2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[3]/div[2]/div/button")));

        buttonInFrame2.click();
        System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

        WebElement buttonInFrame3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/div[1]/div[4]/form/main/div/div[4]/button[1]")));
        buttonInFrame3.click();
    }

    public void clickSomeButtonInService() {

        System.out.println("Начинаем переход в сервисы.");

        // Переключаемся в нужный фрейм
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в фрейм.");

        // Ожидаем появления первой кнопки
        WebElement buttonInObrabotka = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/div[1]/div[4]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button")));
        System.out.println("Нашли Кнопку Обработка.");

        buttonInObrabotka.click();

        WebElement buttonInService = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[4]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[1]/div[2]/div/button/span")));
        System.out.println("Нашли Кнопку Сервис");

        buttonInService.click();
        System.out.println("Клик по второй кнопке внутри фрейма выполнен.");
    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("ласт вышел с фрейма, проверка");
    }
}

// "//*[@id='commandBarItemButton923']/span"
// "//*[@id='submitButton']"

// ОБНОВЛЯЕТ DOM ТОЛЬКО ПОСЛЕ НАЖАТИЯ F12

// package pages;

// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.Keys;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import java.time.Duration;

// public class ZayavkaByPage {

// private WebDriver driver;

// public ZayavkaByPage(WebDriver driver) {
// this.driver = driver;
// }

// public void clickSomeButtonInFrame() {

// System.out.println("Мы ещё до фрейма, но не начали его искать.");

// // Переключаемся в нужный фрейм (путь фрейма нужно уточнить)
// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
// WebElement iframe = wait.until(ExpectedConditions
// .presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));

// driver.switchTo().frame(iframe); // Переключаемся в фрейм
// System.out.println("Перешли в фрейм.");

// // Ожидаем появления кнопки в фрейме
// WebElement buttonInFrame =
// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
// "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[2]/div/div/button/span")));

// System.out.println("Нашли кнопку ура.");

// buttonInFrame.click();

// System.out.println("Клик по кнопке внутри фрейма выполнен.");

// // СУПЕР КОСТЫЛЬ ДЛЯ ВЫЗОВА DevTools и обновления DOOM, я хуй знает как это

// driver.findElement(By.tagName("body")).sendKeys(Keys.F12);
// driver.findElement(By.tagName("body")).sendKeys(Keys.F12);

// System.out.println("Глянуть клик отрабатывает или нет.");
// // добавил ожидание
// // wait.until(ExpectedConditions.stalenessOf(buttonInFrame));
// // System.out.println("Изменения после клика на первую кнопку завершены.");

// WebElement buttonInFrame2 =
// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
// "/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[2]/div[3]/div/button/span")));
// buttonInFrame2.click();
// System.out.println("Клик по второй кнопке выполнен.");

// }
// }

// $x("/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[2]/div[3]/div/button/span")
// $x("/html/body/div[1]/div[3]/form/main/div[2]/div[4]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div[2]/div[3]/div/button/span")
// $x("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")

// Метод для возвращения в основной контент
// public void returnToMainContent() {
// driver.switchTo().defaultContent();
// System.out.println("Возвращаемся в основной контент страницы.");
// }