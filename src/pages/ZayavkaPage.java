package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

//ПЕРЕХОД НА ПЕЙДЖ С ЗАКАЗАМИ И ВХОД В ЗАЯВКУ(1 ПОКА ПО СПИСКУ)

public class ZayavkaPage {

    private WebDriver driver;

    public ZayavkaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickZayavkaBY() {

        System.out.println("Начинаем ZayavkaPage.");

        // Шаг 1: Ожидаем загрузки фрейма и переключаемся на него
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[1]/div/iframe")));

        driver.switchTo().frame(iframe); // Переключаемся в фрейм
        System.out.println("Перешли в фрейм.");

        // Шаг 2: Ждем, пока кнопка станет видимой и кликабельной
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