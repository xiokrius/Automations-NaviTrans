package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.QLoginTest;
import pages.ZayavkaByPage;
import pages.ZayavkaPage;

// ТЕСТ РАН ПО СОЗДАНИЮ ЗАКАЗА

public class CreatingOrder {
    public static void main(String[] args) {
        System.out.println("Запуск теста...");
        // Настройка WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://192.168.1.13:8080/BC210-TEST/SignIn?ReturnUrl=%2FBC210-TEST%2F");

        // Выполнение входа (Логин пароль, нажатие окей)
        QLoginTest loginTest = new QLoginTest(driver);
        loginTest.inputLogin("FTS1");
        loginTest.inputPassword("Aa.124578");
        loginTest.clickLoginButton();

        // Переход на страницу заявок
        System.out.println("Переход на страницу заявок...");
        ZayavkaPage zayavkaPage = loginTest.goToZayavkaPage();

        // Переход в саму заявку
        System.out.println("Выполнение действий на странице заявок...");
        zayavkaPage.clickZayavkaBY();

        System.out.println("Выхожу с фрейма");
        zayavkaPage.returnToMainContent();

        System.out.println("Выход отработал");

        System.out.println("Переход на страницу ZayavkaBy...");
        ZayavkaByPage zayavkaByPage = new ZayavkaByPage(driver);

        zayavkaByPage.clickSomeButtonInFrame();
        zayavkaByPage.returnToMainContent();

        System.out.println("Выход отработал из зая");

        // Завершение работы
        // driver.quit();
    }
}

// package tests;

// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

// import pages.QLoginTest;
// import pages.ZayavkaPage;

// import java.time.Duration;
// import java.util.concurrent.TimeUnit;

// public class TestRunner {
// public static void main(String[] args) {
// // Настройка WebDriver
// WebDriver driver = new ChromeDriver();
// driver.manage().window().maximize();
// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
// driver.get("http://192.168.1.13:8080/BC210-TEST/SignIn?ReturnUrl=%2FBC210-TEST%2F");

// // Выполнение входа
// QLoginTest loginTest = new QLoginTest(driver);
// loginTest.inputLogin("FTS1");
// loginTest.inputPassword("Aa.124578");
// loginTest.clickLoginButton();

// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
// wait.until(ExpectedConditions.urlContains("2003133"));

// // Переход на страницу заявок
// ZayavkaPage zayavkaPage = loginTest.goToZayavkaPage();

// // Выполнение действий на странице заявок
// zayavkaPage.clickZayavkaBY();

// // Завершение работы
// driver.quit();
// }
// }