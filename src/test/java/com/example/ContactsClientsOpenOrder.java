package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.PagesClient.AllClients;
import com.example.PagesClient.AutorisedClients;
import com.example.PagesClient.ClientsPage;
import com.example.PagesClient.Contacts;
import com.example.PagesClient.OpenContactsPage;
import com.example.PagesOrder.QLoginTest;
import com.example.PagesOrder.Invoice;
import com.example.PagesOrder.OpenInvoice;
import com.example.PagesOrder.OrderPage;
import com.example.PagesOrder.PageTransp;
import com.example.PagesOrder.QLoginTest;
import com.example.PagesOrder.ReadyInvoic;
import com.example.PagesOrder.VehiclePlanning;
import com.example.PagesOrder.VehicleRoute;
import com.example.PagesOrder.ZayavkaByPage;
import com.example.PagesOrder.ZayavkaPage;
import com.example.ConfigManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.example.ConfigManager;

public class ContactsClientsOpenOrder {

    public static void main(String[] args) {
        System.out.println("Запуск теста...");
        // Настройка WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String BaseURL = ConfigManager.getProperty("BaseURL");
        driver.get(BaseURL);

        // Cохраняем окно
        String mainWindowHandle = driver.getWindowHandle();

        String login = ConfigManager.getProperty("inputLogin");
        String password = ConfigManager.getProperty("inputPassword");
        String inputLogin2 = ConfigManager.getProperty("inputLogin2");
        String inputPassword2 = ConfigManager.getProperty("inputPassword2");

        // Выполнение входа (логин, пароль, нажатие кнопки)
        QLoginTest loginTest = new QLoginTest(driver);
        loginTest.inputLogin(login);
        loginTest.inputPassword(password);
        loginTest.clickLoginButton();

        System.out.println("Переход на страницу заявок...");
        Contacts Contacts = loginTest.goToContacts();

        Contacts CreateNewContacts = new Contacts(driver);
        CreateNewContacts.ContactsOrderOpen();
        CreateNewContacts.returnToMainContent();

        OpenContactsPage OpenContacts = new OpenContactsPage(driver);
        OpenContacts.OpenContacts();

        String generatedName = OpenContacts.NameContactsValue;

        CreateNewContacts.returnToMainContent();

        String URLClients = ConfigManager.getProperty("URLClients");
        driver.get(URLClients);

        AllClients clientsOpenFull = new AllClients(driver, generatedName);
        clientsOpenFull.ClientsOpen();
        CreateNewContacts.returnToMainContent();

        ClientsPage OpenClients = new ClientsPage(driver);
        OpenClients.fillingClientsForm();

        AllClients OpenClientsWindow = new AllClients(driver, generatedName);
        OpenClientsWindow.Window();

        System.out.println("Открытие второго браузера...");
        WebDriver driver2 = new ChromeDriver();
        driver2.manage().window().maximize();
        driver2.get(BaseURL);

        String secondWindowHandle = driver2.getWindowHandle();

        QLoginTest loginTest2 = new QLoginTest(driver2);
        loginTest2.inputLogin(inputLogin2);
        loginTest2.inputPassword(inputPassword2);
        loginTest2.clickLoginButton();

        // Создание экземпляра AutorisedClientsg
        AutorisedClients autorisedClients = new AutorisedClients(driver2, generatedName);
        System.out.println("Дескриптор окна driver2 в тесте: " + driver2.getWindowHandle());
        // Авторизация по URL из конфига
        autorisedClients.Autorised();

        driver.switchTo().window(mainWindowHandle);
        System.out.println("Переключились обратно в первое окно");

        AllClients OpenClientCD = new AllClients(driver, generatedName);
        OpenClientCD.creditLimit();
        CreateNewContacts.returnToMainContent();

        ClientsPage OpenCD = new ClientsPage(driver);
        OpenCD.OpenCD();

        AllClients BackToAllClients = new AllClients(driver, generatedName);
        BackToAllClients.Window();

        // Переключение обратно на второе окно
        driver2.switchTo().window(secondWindowHandle);
        System.out.println("Переключились обратно во второе окно");

        // Обновление страницы
        driver2.navigate().refresh();
        System.out.println("Страница во втором окне обновлена");

        // Продолжение работы во втором окне
        AutorisedClients continueInSecondWindow = new AutorisedClients(driver2, generatedName);
        continueInSecondWindow.Autorised();

        driver2.quit();

        driver.switchTo().window(mainWindowHandle);
        System.out.println("Переключились обратно в первое окно");

        loginTest.goToZayavkaPage();

        ZayavkaPage zayavkaPage = new ZayavkaPage(driver);
        zayavkaPage.CreateNewZayavkaCZ();
        zayavkaPage.NewOrderCreate();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderFormClients();

        OrderPage PageOrder = new OrderPage(driver);
        PageOrder.PerevozkaInFrame();

    }

}
