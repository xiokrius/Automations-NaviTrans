package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.IntercompanyInvoice;
import pages.OrderPage;
import pages.QLoginTest;
import pages.ZayavkaPage;
import resources.ConfigManager;

public class TestAllPageFixLocators {

    public static void main(String[] args) {
        System.out.println("Запуск теста...");
        // Настройка WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://192.168.1.13:8080/BC210-TEST/SignIn?ReturnUrl=%2FBC210-TEST%2F");

        String login = ConfigManager.getProperty("inputLogin");
        String password = ConfigManager.getProperty("inputPassword");

        // Выполнение входа (логин, пароль, нажатие кнопки)
        QLoginTest loginTest = new QLoginTest(driver);
        loginTest.inputLogin(login);
        loginTest.inputPassword(password);
        loginTest.clickLoginButton();

        // Переход на страницу заявок
        System.out.println("Переход на страницу заявок...");
        ZayavkaPage zayavkaPage = loginTest.goToZayavkaPage();

        // Создание новой заявки
        ZayavkaPage CreateNewOrder = new ZayavkaPage(driver);
        CreateNewOrder.CreateNewZayavkaCZ();
        CreateNewOrder.returnToMainContent();

        ZayavkaPage OpenDataOrder = new ZayavkaPage(driver);
        OpenDataOrder.NewOrderCreate();
        OpenDataOrder.returnToMainContent();

        OrderPage OpenInctercompany = new OrderPage(driver);
        OpenInctercompany.fillIntercompanyForm();
        CreateNewOrder.returnToMainContent();

        IntercompanyInvoice FillingIntercompany = new IntercompanyInvoice(driver);
        FillingIntercompany.InterCompanyInfo();

    }
}
