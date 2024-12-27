package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.QLoginTest;
import pages.ReadyInvoic;
import pages.ZayavkaPage;
import resources.ConfigManager;
import pages.PageOpenTransp;
import pages.Invoice;
import pages.OpenInvoice;
import pages.OrderPage;
import pages.PageTransp;
import pages.VehicleRoute;
import pages.ZayavkaByPage;

public class CreateOrderNew {
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

        // Заполнение данных перед планированием
        ZayavkaPage OpenDataOrder = new ZayavkaPage(driver);
        OpenDataOrder.NewOrderCreate();
        OpenDataOrder.returnToMainContent();

        OrderPage OrderPage = new OrderPage(driver);
        OrderPage.fillOrderForm();
        OrderPage.returnToMainContent();

        // Переход в перевозки(Заменены xPath с прямых по классам)
        OrderPage Perevozki = new OrderPage(driver);
        Perevozki.PerevozkaInFrame();
        Perevozki.returnToMainContent();

        // Установка Плановых дат в перевозке и выход обратно на страницу заявок
        PageTransp OpenDate = new PageTransp(driver);
        OpenDate.OpenOrLoadingLocation();
        Perevozki.returnToMainContent();

        // // После установки план дат. Нажимаю Обработка/выпустить
        PageOpenTransp vageOpenTransp = new PageOpenTransp(driver);
        vageOpenTransp.obrabotkaVypustit();
        vageOpenTransp.returnToMainContent();

        // // Обработка/План, планирую рейс
        PageOpenTransp testOpenTransp = new PageOpenTransp(driver);
        testOpenTransp.vehiclePlan();
        testOpenTransp.returnToMainContent();

        // // // После обработка/План, нужно выбрать в какой поездке будут изменения
        PageOpenTransp opentranspOp = new PageOpenTransp(driver);
        opentranspOp.PlanOpen();
        opentranspOp.returnToMainContent();

        // // // Вбиваем Тягач и прицеп
        PageOpenTransp OpenVehicle = new PageOpenTransp(driver);
        OpenVehicle.VehiclePlanOpen();
        OpenVehicle.returnToMainContent();

        // // На странице рейса выхожу обратно, пока там ничего не нужно, она
        // // инициализирована.
        VehicleRoute backRoute = new VehicleRoute(driver);
        backRoute.clickSomeButtonInFrame();
        backRoute.returnToMainContent();

        // Сам переход в сервисы
        ZayavkaByPage OpenService = new ZayavkaByPage(driver);
        OpenService.clickSomeButtonInService();
        OpenService.returnToMainContent();

        // Действия в сервисах
        OpenInvoice Service = new OpenInvoice(driver);
        Service.OpenServices();
        Service.returnToMainContent();

        PageOpenTransp ReadyInInvoicing = new PageOpenTransp(driver);
        ReadyInInvoicing.readyInInvoicing();
        CreateNewOrder.returnToMainContent();

        PageOpenTransp Schet = new PageOpenTransp(driver);
        Schet.obrabotkaSchet();
        CreateNewOrder.returnToMainContent();

        ReadyInvoic SchetNRuchnoy = new ReadyInvoic(driver);
        SchetNRuchnoy.SchetRuchnoy();
        CreateNewOrder.returnToMainContent();

        Invoice FullInvoice = new Invoice(driver);
        FullInvoice.fullSchet();
        CreateNewOrder.returnToMainContent();

        // Завершение работы
        // driver.quit();
    }
}
