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
        System.out.println("Выполнение действий на странице заявок...");
        zayavkaPage.clickZayavkaBY();
        zayavkaPage.returnToMainContent();

        System.out.println("Переход на страницу ZayavkaBy...");

        ZayavkaByPage zayavkaByPage = new ZayavkaByPage(driver);
        zayavkaByPage.clickSomeButtonInFrame();
        zayavkaByPage.returnToMainContent();

        System.out.println("Выход отработал из фрейма");

        PageOpenTransp pageOpenTransp = new PageOpenTransp(driver);
        pageOpenTransp.clickSomeButtonInFrame();
        pageOpenTransp.returnToMainContent();

        PageTransp pageTransp = new PageTransp(driver);
        pageTransp.fillDateFieldInFrame();
        pageTransp.returnToMainContent();

        PageOpenTransp vageOpenTransp = new PageOpenTransp(driver);
        vageOpenTransp.obrabotkaVypustit();
        vageOpenTransp.returnToMainContent();

        PageOpenTransp testOpenTransp = new PageOpenTransp(driver);
        testOpenTransp.vehiclePlan();
        testOpenTransp.returnToMainContent();

        PageOpenTransp opentranspOp = new PageOpenTransp(driver);
        opentranspOp.PlanOpen();
        opentranspOp.returnToMainContent();

        PageOpenTransp OpenVehicle = new PageOpenTransp(driver);
        OpenVehicle.VehiclePlanOpen();
        OpenVehicle.returnToMainContent();

        VehicleRoute backRoute = new VehicleRoute(driver);
        backRoute.clickSomeButtonInFrame();
        backRoute.returnToMainContent();

        ZayavkaByPage Service = new ZayavkaByPage(driver);
        Service.clickSomeButtonInService();
        Service.returnToMainContent();

        // Завершение работы
        // driver.quit();
    }
}
