package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.ConfigManager;

// ВХОД В ТЕСТ

public class QLoginTest {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='Password']")
    private WebElement passwdField;

    @FindBy(xpath = "//*[@id='UserName']")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id='submitButton']")
    private WebElement loginButton;

    public QLoginTest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Метод ввода логина
    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    // Метод ввода пароля
    public void inputPassword(String password) {
        passwdField.sendKeys(password);
    }

    // Метод нажатия на кнопку входа
    public void clickLoginButton() {
        loginButton.click();
    }

    private String inputLogin = ConfigManager.getProperty("inputLogin");
    private String inputPassword = ConfigManager.getProperty("inputPassword");

    public ZayavkaPage goToZayavkaPage() {
        driver.get(
                "http://192.168.1.13:8080/BC210-TEST/?company=Trans_Solutions_CZ&page=2003133&filter=%27NVT%20File%20Tpt%27.%27File%20date%27%20IS%20%27%3e%3d09.06.24%27&dc=0&bookmark=33%3bApAeAAJ7%2f0IAWQAyADQAMQAyADAANQAwADAAMw%3d%3d");

        return new ZayavkaPage(driver);
    }
}

// Тестил гугл, все переходит, всё работает, не то что навик сук(((
// public Testgoogle goToZayavkapage() {
// driver.get(
// "https://www.google.by/search?q=seleselenium+нажать+на+ссылку+в+браузере&sca_esv=711f59ee6954731e&sxsrf=ADLYWIKWiBT8xfA3NhWsPokjdZNJbTufLw%3A1733745649300&ei=8dtWZ9L2EaH1i-gPirWCmAs");

// return new Testgoogle(driver);
// }

// }

// Метод для перехода на страницу заявок Пока лежит отдыхает блиадь, ща взял в
// работу, трабл с фреймами был
// public ZayavkaPage goToZayavkaPage() {
// driver.get(
// "http://192.168.1.13:8080/BC210-TEST/?company=Trans_Solutions_CZ&page=2003133&filter=%27NVT%20File%20Tpt%27.%27File%20date%27%20IS%20%27%3e%3d09.06.24%27&dc=0&bookmark=33%3bApAeAAJ7%2f0IAWQAyADQAMAA2ADEAMAAwADAAMw%3d%3d");

// return new ZayavkaPage(driver);
// }

// public class QLoginTest {

// // Инициализация элементов с помощью @FindBy
// @FindBy(xpath = "//*[@id='UserName']")
// private WebElement loginField;

// @FindBy(xpath = "//*[@id='Password']")
// private WebElement passwdField;

// @FindBy(xpath = "//*[@id='submitButton']")
// private WebElement loginButton;

// private WebDriver driver;

// // Конструктор для инициализации WebDriver и PageFactory
// public QLoginTest(WebDriver driver) {
// this.driver = driver;
// PageFactory.initElements(driver, this);
// }

// // Метод ввода логина
// public void inputLogin(String login) {
// loginField.sendKeys(login);
// }

// // Метод ввода пароля
// public void inputPassword(String password) {
// passwdField.sendKeys(password);
// }

// // Метод нажатия на кнопку входа
// public void clickLoginButton() {
// loginButton.click();
// }

// public static void main(String[] args) {
// // Настройка WebDriver
// WebDriver driver = new ChromeDriver();
// driver.manage().window().maximize();
// driver.get("http://192.168.1.13:8080/BC210-TEST/SignIn?ReturnUrl=%2FBC210-TEST%2F");
// driver.manage().timeouts();// .implicitlyWait(10, TimeUnit.SECONDS);

// // Создание экземпляра QLoginTest
// QLoginTest loginTest = new QLoginTest(driver);

// // Выполнение теста
// loginTest.inputLogin("FTS1");
// loginTest.inputPassword("Aa.124578");
// loginTest.clickLoginButton();
// }
// }
