package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.PagesOrder.OpenInvoice;
import com.example.PagesOrder.OrderPage;
import com.example.PagesOrder.PageTransp;
import com.example.PagesOrder.QLoginTest;
import com.example.PagesOrder.VehiclePlanning;
import com.example.PagesOrder.VehicleRoute;
import com.example.PagesOrder.ZayavkaByPage;
import com.example.PagesOrder.ZayavkaPage;
import com.example.ConfigManager;

public class CreateManyOrders {

    public static void main(String[] args) {
        System.out.println("Запуск теста...");
        // Настройка WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigManager.getProperty("URLAutorisedNavi"));

        String login = ConfigManager.getProperty("inputLogin");
        String password = ConfigManager.getProperty("inputPassword");

        // Выполнение входа (логин, пароль, нажатие кнопки)
        QLoginTest loginTest = new QLoginTest(driver);
        loginTest.inputLogin(login);
        loginTest.inputPassword(password);
        loginTest.clickLoginButton();

        for (int i = 0; i < 25; i++) {
            System.out.println("Создание заявки " + (i + 1));

            // для рефреша всех урлов и перенаправления Navi, без него в итерации не тот урл
            // подкидывает navi
            driver.navigate().refresh();
            // Переход на страницу заявок
            System.out.println("Переход на страницу заявок...");
            driver.get(ConfigManager.getProperty("URLOrder"));

            // Создание новой заявки
            ZayavkaPage CreateNewOrder = new ZayavkaPage(driver);
            CreateNewOrder.CreateNewZayavkaCZ();

            // Заполнение данных перед планированием
            ZayavkaPage OpenDataOrder = new ZayavkaPage(driver);
            OpenDataOrder.NewOrderCreate();

            OrderPage OrderPage = new OrderPage(driver);
            OrderPage.fillOrderForm();

            // Переход в перевозки(Заменены xPath с прямых по классам)
            OrderPage Perevozki = new OrderPage(driver);
            Perevozki.PerevozkaInFrameIteration(i); // Добавлена итерация для повторения

            // Установка Плановых дат в перевозке и выход обратно на страницу заявок
            PageTransp OpenDate = new PageTransp(driver);
            OpenDate.OpenOrLoadingLocationIteration(i);

            // // После установки план дат. Нажимаю Обработка/выпустить
            OrderPage vageOpenTransp = new OrderPage(driver);
            vageOpenTransp.obrabotkaVypustit();

            // // Обработка/План, планирую рейс
            OrderPage testOpenTransp = new OrderPage(driver);
            testOpenTransp.vehiclePlan();

            // // // После обработка/План, нужно выбрать в какой поездке будут изменения
            OrderPage opentranspOp = new OrderPage(driver);
            opentranspOp.PlanOpen();

            // // // Вбиваем Тягач и прицеп
            VehiclePlanning OpenVehicle = new VehiclePlanning(driver);
            OpenVehicle.VehiclePlanOpen();

            // // На странице рейса выхожу обратно, пока там ничего не нужно, она
            // // инициализирована.
            VehicleRoute backRoute = new VehicleRoute(driver);
            backRoute.clickSomeButtonInFrame();

            // Сам переход в сервисы
            ZayavkaByPage OpenService = new ZayavkaByPage(driver);
            OpenService.clickSomeButtonInService();

            // Действия в сервисах
            OpenInvoice Service = new OpenInvoice(driver);
            Service.OpenServices();

            OrderPage ReadyInInvoicing = new OrderPage(driver);
            ReadyInInvoicing.readyInInvoicing();

            // OrderPage Schet = new OrderPage(driver);
            // Schet.obrabotkaSchet();

            // ReadyInvoic SchetNRuchnoy = new ReadyInvoic(driver);
            // SchetNRuchnoy.SchetRuchnoy();

            // Invoice FullInvoice = new Invoice(driver);
            // FullInvoice.fullSchet();

        }
    }
}