package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.PagesOrder.OrderPage;
import com.example.PagesOrder.PageTransp;
import com.example.PagesOrder.QLoginTest;
import com.example.PagesOrder.VehiclePlanning;
import com.example.PagesOrder.VehicleRoute;
import com.example.PagesOrder.ZayavkaByPage;
import com.example.PagesOrder.ZayavkaPage;

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

        // КОПИРОВАНИЕ ЗАЯВКИ, опционально
        ZayavkaByPage zayavkaByPage = new ZayavkaByPage(driver);
        zayavkaByPage.clickSomeButtonInFrame();
        zayavkaByPage.returnToMainContent();

        OrderPage pageOpenTransp = new OrderPage(driver);
        pageOpenTransp.OpenTranspVehicle();

        // Установка Плановых дат в перевозке и выход обратно на страницу заявок
        PageTransp pageTransp = new PageTransp(driver);
        pageTransp.OpenOrLoadingLocation();

        // После установки план дат. Нажимаю Обработка/выпустить
        OrderPage vageOpenTransp = new OrderPage(driver);
        vageOpenTransp.obrabotkaVypustit();

        // Обработка/План, планирую рейс
        OrderPage testOpenTransp = new OrderPage(driver);
        testOpenTransp.vehiclePlan();

        // После обработка/План, нужно выбрать в какой поездке будут изменения
        OrderPage opentranspOp = new OrderPage(driver);
        opentranspOp.PlanOpen();

        // Вбиваем Тягач и прицеп
        VehiclePlanning OpenVehicle = new VehiclePlanning(driver);
        OpenVehicle.VehiclePlanOpenCopiedOrder();

        // На странице рейса выхожу обратно, пока там ничего не нужно, она
        // инициализирована.
        VehicleRoute backRoute = new VehicleRoute(driver);
        backRoute.clickSomeButtonInFrame();

        // Лезу в сервисы
        ZayavkaByPage Service = new ZayavkaByPage(driver);
        Service.clickSomeButtonInService();
        Service.returnToMainContent();

        // Завершение работы
        // driver.quit();
    }
}