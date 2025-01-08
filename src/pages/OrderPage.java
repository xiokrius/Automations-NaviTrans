package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ConfigManager;

import org.openqa.selenium.support.ui.Select;

public class OrderPage {

    private String transportRequirementValue = ConfigManager.getProperty("transportRequirementValue");
    private String customersCodeValue = ConfigManager.getProperty("customersCodeValue");
    private String carrierValue = ConfigManager.getProperty("carrierValue");
    private String typeCarrierValue = ConfigManager.getProperty("typeCarrierValue");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Метод для переключения в iframe
    private void switchToIframe(String iframeXpath) {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(iframeXpath)));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в iframe.");
    }

    // Метод для получения элемента
    private WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Метод для заполнения значения через JavaScript
    private void fillInputWithJS(WebElement element, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", element, value);
        System.out.println("Заполнили поле: " + value);
    }

    // Метод для выбора значения в select
    private void selectDropdownByValue(WebElement selectElement, String value) {
        Select select = new Select(selectElement);
        select.selectByValue(value);
        System.out.println("Выбрали значение в select: " + value);
    }

    public void fillOrderForm() {
        // Переход в iframe
        switchToIframe("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe");

        // Нахождение элементов, явная прогрузка первого элемента

        WebElement transportRequirement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("b3m1ee")));

        System.out.println("Нашли ид");

        WebElement customersCode = findElement(By.xpath(
                "/html/body/div[1]/div[3]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[5]/div/input"));
        WebElement carrier = findElement(By.xpath(
                "/html/body/div[1]/div[3]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[17]/div/select"));
        WebElement typeCarrier = findElement(By.xpath(
                "/html/body/div[1]/div[3]/form/main/div[2]/div[6]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[18]/div/select"));

        // Заполнение данных
        fillInputWithJS(transportRequirement, transportRequirementValue);
        fillInputWithJS(customersCode, customersCodeValue);

        // Выбор значений в select
        selectDropdownByValue(carrier, carrierValue);
        selectDropdownByValue(typeCarrier, typeCarrierValue);

        System.out.println("Форма заполнена.");

        // Инициализация строк
        customersCode.click();
        transportRequirement.click();

    }

    // ТУТ ПЕРЕХОЖУ НА СТРАНИЦУ РЕДАКТИРОВАНИЯ ПЕРЕВОЗКИ (NVT Shipment (2002947)) и
    // инициализирую кнопки Перевозки по заказу/Управление/Правка
    // !!!!!!!!!!!!!!!!!! НУЖНО СОЗДАТЬ ОТДЕЛЬНЫЙ ПЕЙДЖ ДЛЯ ЭТОГО БЛОКА И ПЕРЕНЕСТИ
    // ЕГО !!!!!!!!!!!!!!!!!!

    public void PerevozkaInFrame() {

        System.out.println("Начинаем PageOpentTransp.");

        // Переключаемся в нужный фрейм
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions
                .presenceOfElementLocated(
                        By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));
        driver.switchTo().frame(iframe);
        System.out.println("Перешли в фрейм.");

        // Клик для раскрытия списка перевозок
        WebElement buttonInFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//*[@id=\'b3fe\']/div[2]/div/span")));

        // Кликаем по первой кнопке
        buttonInFrame.click();
        System.out.println("Клик по первой кнопке внутри фрейма выполнен.");

        // Перевозки/Управление(кнопка)
        WebElement buttonInPerevozkiUpravlenie = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-control-id='b3ff']")));
        buttonInPerevozkiUpravlenie.click();
        System.out.println("Клик по второй кнопке внутри фрейма выполнен.");

        // Перевозки/Управление/Создать
        WebElement buttonInPereozkiUpravlenieSozdat = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//*[@data-control-id=\'b3fh\']")));

        buttonInPereozkiUpravlenieSozdat.click();
        System.out.println("Клик по второй кнопке 3 внутри фрейма выполнен.");
    }

    // ИНИЦИАЛИЗИРУЮ ОБРАБОТКУ ДЛЯ ПЕРЕХОДА В РАСХОДЫ И СОЗДАНИЯ ИНТЕРКОМПАНИ

    public void fillIntercompanyForm() {
        switchToIframe("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe");
        WebElement obrabotkaButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label=' Обработка']")));

        obrabotkaButton.click();

        WebElement rashodiButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label='Расходы']")));

        rashodiButton.click();

    }

    public void returnToMainContent() {
        driver.switchTo().defaultContent();
        System.out.println("ласт вышел с фрейма, проверка");
    }

}
