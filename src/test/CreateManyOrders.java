package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.Invoice;
import pages.OpenInvoice;
import pages.OrderPage;
import pages.PageTransp;
import pages.QLoginTest;
import pages.ReadyInvoic;
import pages.VehiclePlanning;
import pages.VehicleRoute;
import pages.ZayavkaByPage;
import pages.ZayavkaPage;
import resources.ConfigManager;

public class CreateManyOrders {

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

        for (int i = 0; i < 25; i++) {
            System.out.println("Создание заявки " + (i + 4));

            // Переход на страницу заявок
            System.out.println("Переход на страницу заявок...");
            ZayavkaPage zayavkaPage = loginTest.goToZayavkaPage();

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

            OrderPage Schet = new OrderPage(driver);
            Schet.obrabotkaSchet();

            ReadyInvoic SchetNRuchnoy = new ReadyInvoic(driver);
            SchetNRuchnoy.SchetRuchnoy();

            Invoice FullInvoice = new Invoice(driver);
            FullInvoice.fullSchet();

        }
    }
}