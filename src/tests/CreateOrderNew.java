package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.ReadyInvoic;
import pages.QLoginTest;
import pages.VehiclePlanning;
import pages.ZayavkaPage;
import resources.ConfigManager;
import pages.OrderPage;
import pages.IntercompanyInvoice;
import pages.Invoice;
import pages.OpenInvoice;
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
        Perevozki.PerevozkaInFrame(); // ТУТ всё гуд, но xpath мне не очень нравятся
        Perevozki.returnToMainContent();

        // Установка Плановых дат в перевозке и выход обратно на страницу заявок
        PageTransp OpenDate = new PageTransp(driver);
        OpenDate.OpenOrLoadingLocation(); // Вот тут жопа Требуется полный пересмотр всего пейджа и замена локаторов.
        Perevozki.returnToMainContent();

        // // После установки план дат. Нажимаю Обработка/выпустить
        OrderPage vageOpenTransp = new OrderPage(driver);
        vageOpenTransp.obrabotkaVypustit();
        vageOpenTransp.returnToMainContent();

        // // Обработка/План, планирую рейс
        OrderPage testOpenTransp = new OrderPage(driver);
        testOpenTransp.vehiclePlan();
        testOpenTransp.returnToMainContent();

        // // // После обработка/План, нужно выбрать в какой поездке будут изменения
        OrderPage opentranspOp = new OrderPage(driver);
        opentranspOp.PlanOpen();
        opentranspOp.returnToMainContent();

        // // // Вбиваем Тягач и прицеп
        VehiclePlanning OpenVehicle = new VehiclePlanning(driver);
        OpenVehicle.VehiclePlanOpen();
        CreateNewOrder.returnToMainContent();

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

        OrderPage ReadyInInvoicing = new OrderPage(driver);
        ReadyInInvoicing.readyInInvoicing();
        CreateNewOrder.returnToMainContent();

        OrderPage Schet = new OrderPage(driver);
        Schet.obrabotkaSchet();
        CreateNewOrder.returnToMainContent();

        ReadyInvoic SchetNRuchnoy = new ReadyInvoic(driver);
        SchetNRuchnoy.SchetRuchnoy();
        CreateNewOrder.returnToMainContent();

        Invoice FullInvoice = new Invoice(driver);
        FullInvoice.fullSchet();
        CreateNewOrder.returnToMainContent();

        // Переход в интеркампани
        OrderPage OpenInctercompany = new OrderPage(driver);
        OpenInctercompany.fillIntercompanyForm();
        CreateNewOrder.returnToMainContent();

        // Заполнение самого интеркампани
        IntercompanyInvoice FillingIntercompany = new IntercompanyInvoice(driver);
        FillingIntercompany.InterCompanyInfo();

        // IntercompanyInvoice OpenInctercompany = new IntercompanyInvoice(driver);
        // OpenInctercompany.fillIntercompanyForm();

        // Завершение работы
        // driver.quit();
    }
}
