package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.OrderPage;
import pages.PageTransp;
import pages.QLoginTest;
import pages.VehiclePlanning;
import pages.VehicleRoute;
import pages.ZayavkaByPage;
import pages.ZayavkaPage;
import resources.ConfigManager;

public class CopyinganOrderVehicle {

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

        // Переход в саму заявку
        System.out.println("Вход на страницу заявок и переход в заявку");
        zayavkaPage.CopiedOrders();
        zayavkaPage.returnToMainContent();

        // КОПИРОВАНИЕ ЗАЯВКИ, опционально
        ZayavkaByPage zayavkaByPage = new ZayavkaByPage(driver);
        zayavkaByPage.clickSomeButtonInFrame();
        zayavkaByPage.returnToMainContent();

        OrderPage pageOpenTransp = new OrderPage(driver);
        pageOpenTransp.OpenTranspVehicle();
        zayavkaPage.returnToMainContent();

        // Установка Плановых дат в перевозке и выход обратно на страницу заявок
        PageTransp pageTransp = new PageTransp(driver);
        pageTransp.OpenOrLoadingLocation();

        // После установки план дат. Нажимаю Обработка/выпустить
        OrderPage vageOpenTransp = new OrderPage(driver);
        vageOpenTransp.obrabotkaVypustit();
        zayavkaPage.returnToMainContent();

        // Обработка/План, планирую рейс
        OrderPage testOpenTransp = new OrderPage(driver);
        testOpenTransp.vehiclePlan();
        zayavkaPage.returnToMainContent();

        // После обработка/План, нужно выбрать в какой поездке будут изменения
        OrderPage opentranspOp = new OrderPage(driver);
        opentranspOp.PlanOpen();
        zayavkaPage.returnToMainContent();

        // Вбиваем Тягач и прицеп
        VehiclePlanning OpenVehicle = new VehiclePlanning(driver);
        OpenVehicle.VehiclePlanOpenCopiedOrder();
        zayavkaPage.returnToMainContent();

        // На странице рейса выхожу обратно, пока там ничего не нужно, она
        // инициализирована.
        VehicleRoute backRoute = new VehicleRoute(driver);
        backRoute.clickSomeButtonInFrame();
        zayavkaPage.returnToMainContent();

        // Лезу в сервисы
        ZayavkaByPage Service = new ZayavkaByPage(driver);
        Service.clickSomeButtonInService();
        Service.returnToMainContent();

        // Завершение работы
        // driver.quit();
    }
}