package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.PageOpenTransp;
import pages.PageTransp;
import pages.QLoginTest;
import pages.VehicleRoute;
import pages.ZayavkaByPage;
import pages.ZayavkaPage;

public class CreatingACompleteOrder {
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
        System.out.println("Вход на страницу заявок и переход в заявку");
        zayavkaPage.clickZayavkaBY();
        zayavkaPage.returnToMainContent();

        // КОПИРОВАНИЕ ЗАЯВКИ, опционально
        ZayavkaByPage zayavkaByPage = new ZayavkaByPage(driver);
        zayavkaByPage.clickSomeButtonInFrame();
        zayavkaByPage.returnToMainContent();

        // Переход в перевозки
        PageOpenTransp pageOpenTransp = new PageOpenTransp(driver);
        pageOpenTransp.clickSomeButtonInFrame();
        pageOpenTransp.returnToMainContent();

        // Установка Плановых дат в перевозке и выход обратно на страницу заявок
        PageTransp pageTransp = new PageTransp(driver);
        pageTransp.fillDateFieldInFrame();
        pageTransp.returnToMainContent();

        // После установки план дат. Нажимаю Обработка/выпустить
        PageOpenTransp vageOpenTransp = new PageOpenTransp(driver);
        vageOpenTransp.obrabotkaVypustit();
        vageOpenTransp.returnToMainContent();

        // Обработка/План, планирую рейс
        PageOpenTransp testOpenTransp = new PageOpenTransp(driver);
        testOpenTransp.vehiclePlan();
        testOpenTransp.returnToMainContent();

        // После обработка/План, нужно выбрать в какой поездке будут изменения
        PageOpenTransp opentranspOp = new PageOpenTransp(driver);
        opentranspOp.PlanOpen();
        opentranspOp.returnToMainContent();

        // Вбиваем Тягач и прицеп
        PageOpenTransp OpenVehicle = new PageOpenTransp(driver);
        OpenVehicle.VehiclePlanOpen();
        OpenVehicle.returnToMainContent();

        // На странице рейса выхожу обратно, пока там ничего не нужно, она
        // инициализирована.
        VehicleRoute backRoute = new VehicleRoute(driver);
        backRoute.clickSomeButtonInFrame();
        backRoute.returnToMainContent();

        // Лезу в сервисы
        ZayavkaByPage Service = new ZayavkaByPage(driver);
        Service.clickSomeButtonInService();
        Service.returnToMainContent();

        // Завершение работы
        // driver.quit();
    }
}
