package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import pages.FrameSwitcher;
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

public class CreateManyOrdersTest {

    private WebDriver driver;
    private QLoginTest loginTest;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://192.168.1.13:8080/BC210-TEST/SignIn?ReturnUrl=%2FBC210-TEST%2F");
        loginTest = new QLoginTest(driver);
    }

    @Test(priority = 1)
    public void login() {
        String login = ConfigManager.getProperty("inputLogin");
        String password = ConfigManager.getProperty("inputPassword");
        loginTest.inputLogin(login);
        loginTest.inputPassword(password);
        loginTest.clickLoginButton();
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void createOrders() {
        for (int i = 0; i < 2; i++) {
            System.out.println("Создание заявки " + (i + 1));
            createOrder(i);
        }
    }

    private void createOrder(int iteration) {
        performAction(() -> {
            ZayavkaPage zayavkaPage = loginTest.goToZayavkaPage();
            zayavkaPage.CreateNewZayavkaCZ();
            zayavkaPage.NewOrderCreate();

            OrderPage orderPage = new OrderPage(driver);
            orderPage.fillOrderForm();
            orderPage.PerevozkaInFrameIteration(iteration);

            PageTransp pageTransp = new PageTransp(driver);
            pageTransp.OpenOrLoadingLocationIteration(iteration);

            orderPage.obrabotkaVypustit();
            orderPage.vehiclePlan();
            orderPage.PlanOpen();

            VehiclePlanning vehiclePlanning = new VehiclePlanning(driver);
            vehiclePlanning.VehiclePlanOpen();

            VehicleRoute vehicleRoute = new VehicleRoute(driver);
            vehicleRoute.clickSomeButtonInFrame();

            ZayavkaByPage zayavkaByPage = new ZayavkaByPage(driver);
            zayavkaByPage.clickSomeButtonInService();

            OpenInvoice openInvoice = new OpenInvoice(driver);
            openInvoice.OpenServices();

            orderPage.readyInInvoicing();
            orderPage.obrabotkaSchet();

            ReadyInvoic readyInvoic = new ReadyInvoic(driver);
            readyInvoic.SchetRuchnoy();

            Invoice invoice = new Invoice(driver);
            invoice.fullSchet();
        });
    }

    private void performAction(Runnable action) {
        action.run();
        new FrameSwitcher(driver).returnToMainContent();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
