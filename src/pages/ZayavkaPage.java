package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

//ПЕРЕХОД НА ПЕЙДЖ С ЗАКАЗАМИ И ВХОД В ЗАЯВКУ(1 ПОКА ПО СПИСКУ)

public class ZayavkaPage {

    private WebDriver driver;

    private String TSGroupCodeValue = "CZ_TS";
    private String ButtonNewZayavkaValue = "FOOD";

    public ZayavkaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickZayavkaBY() {

        System.out.println("Переход на страницу Заявок и вход в заявку");

        // Шаг 1: Ожидаем загрузки фрейма и переключаемся на него
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));

        driver.switchTo().frame(iframe); // Переключаемся в фрейм

        // Шаг 2: КНОПКА ЗАЯВКИ на пейдже страницы заявок
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(
                        "/html/body/div[1]/div[2]/form/div/div[2]/main/div[2]/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[6]/td[3]/a")));

        try {
            button.click();
            System.out.println("Клик по кнопке выполнен.");
        } catch (Exception e) {
            System.out.println("Не удалось кликнуть по кнопке: " + e.getMessage());
        }
    }

    public void CreateNewZayavkaCZ() {

        System.out.println("Переход на страницу Заявок и вход в заявку");

        // Шаг 1: Ожидаем загрузки фрейма и переключаемся на него
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));

        driver.switchTo().frame(iframe); // Переключаемся в фрейм

        // Шаг 2: КНОПКА НОВЫЙ(для создания заказа)
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(
                        "/html/body/div[1]/div[2]/form/div/div[2]/div[2]/div/div/nav/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div/div/div[1]/div/div/div/div[1]/div/div/button/span")));

        try {
            button.click();
            System.out.println("Клик по кнопке выполнен.");
        } catch (Exception e) {
            System.out.println("Не удалось кликнуть по кнопке: " + e.getMessage());
        }

        WebElement ButtonNewZayavka = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[2]/form/div/div[2]/div[5]/div/div/div/div/div/div/ul/li/div/div/button/span")));

        ButtonNewZayavka.click();

    }

    public void NewOrderCreate() {

        System.out.println("Переход на страницу Заявок и вход в заявку");

        // Шаг 1: Ожидаем загрузки фрейма и переключаемся на него
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));

        driver.switchTo().frame(iframe); // Переключаемся в фрейм

        // Поле TSGroupCode
        WebElement TSGroupCode = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(
                        "/html/body/div[1]/div[3]/form/main/div/div[3]/div[1]/div/div[4]/div/div/div/div/div/div[2]/div[1]/div/input")));

        WebElement ButtonInOk = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(
                        "/html/body/div[1]/div[3]/form/main/div/div[4]/button[1]")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", TSGroupCode, TSGroupCodeValue);
        System.out.println("Заполнил ТС групп Код: " + TSGroupCodeValue);

        System.out.println("Клик для инициализации.");

        // Шаг 2: Поле Отдел
        WebElement ButtonNewZayavka = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/html/body/div[1]/div[3]/form/main/div/div[3]/div[1]/div/div[4]/div/div/div/div/div/div[2]/div[2]/div/input")));

        js.executeScript("arguments[0].value = arguments[1];", ButtonNewZayavka, ButtonNewZayavkaValue);
        System.out.println("Заполнили отдел: " + ButtonNewZayavkaValue);

        ButtonNewZayavka.click();
        System.out.println("Клик для инициализации.");

        ButtonInOk.click();

    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("Возвращаемся в основной контент страницы.");
    }

}

// Метод для возвращения в основной контент, если нужно
// public void returnToMainContent() {
// driver.switchTo().defaultContent();
// System.out.println("Возвращаемся в основной контент страницы.");
// }
// }

// Работаент переход но не видит фрейм

// public class ZayavkaPage {

// private WebDriver driver;

// @FindBy(xpath =
// "/html/body/div[1]/div[2]/form/div/div[2]/main/div[2]/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[6]/td[3]/a")
// private WebElement ZayavkaBY;

// public ZayavkaPage(WebDriver driver) {
// this.driver = driver;
// PageFactory.initElements(driver, this);
// }

// public void clickZayavkaBY() {

// System.out.println("Ожидание кнопки заявки...");

// // Ожидаем, пока элемент станет кликабельным (внутри основного контента)
// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
// wait.until(ExpectedConditions.elementToBeClickable(ZayavkaBY));

// // Переключаемся на фрейм, чтобы найти нужную ссылку внутри него
// WebElement iframe =
// driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[1]/div/iframe"));
// driver.switchTo().frame(iframe);

// // После переключения в фрейм, находим и кликаем по элементу внутри фрейма
// WebElement linkInsideFrame = driver.findElement(By.xpath(
// "/html/body/div[1]/div[2]/form/div/div[2]/main/div[2]/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[6]/td[3]/a"));
// linkInsideFrame.click();

// System.out.println("Клик по заявке выполнен.");

// // После выполнения действия можно вернуться в основной контент, если это
// // необходимо
// driver.switchTo().defaultContent();
// }
// }

// public class ZayavkaPage {

// private WebDriver driver;

// @FindBy(xpath =
// "/html/body/div[1]/div[2]/form/div/div[2]/main/div[2]/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[6]/td[3]/a")
// private WebElement ZayavkaBY;

// // ПЕРЕБРАЛ ВСЁ ГОВНО, НИЧЕГО НЕ РАБОТАЕТ
// // "/*[@id='b0_factBoxToggle']/span"
// // "//*[@id='BusinessGrid_b6r']/tbody/tr[6]/td[3]"
// "//*[@id='biqee']"
// *[@id="b0_content"]/div[2]/div/div/nav/div[1]/div/div[2]/div/div/div/div[1]/div/div/div/div/div[1]/div/a/div/div[2]/span/span
// //
// /html/body/div[1]/div[2]/form/div/div[2]/main/div[2]/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[6]/td[3]/a

// public ZayavkaPage(WebDriver driver) {
// this.driver = driver;
// PageFactory.initElements(driver, this);
// }

// public void clickZayavkaBY() {

// System.out.println("Ожидание кнопки заявки...");

// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
// wait.until(ExpectedConditions.elementToBeClickable(ZayavkaBY));

// System.out.println("Кнопка заявки доступна. Выполняем клик...");

// ZayavkaBY.click();

// System.out.println("Клик по заявке выполнен.");
// }
// }