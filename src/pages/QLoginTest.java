package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public ZayavkaPage goToZayavkaPage() {
        driver.get(
                "http://192.168.1.13:8080/BC210-TEST/?company=Trans_Solutions_CZ&page=2003133&filter=%27NVT%20File%20Tpt%27.%27File%20date%27%20IS%20%27%3e%3d09.06.24%27&dc=0&bookmark=33%3bApAeAAJ7%2f0IAWQAyADQAMQAyADAANQAwADAAMw%3d%3d");

        return new ZayavkaPage(driver);
    }

    public ClientPage goToClientPage() {
        driver.get(
                "http://192.168.1.13:8080/BC210-TEST/?company=Trans_Solutions_CZ&page=2003133&filter=%27NVT%20File%20Tpt%27.%27File%20date%27%20IS%20%27%3e%3d09.06.24%27&dc=0&bookmark=33%3bApAeAAJ7%2f0IAWQAyADQAMQAyADAANQAwADAAMw%3d%3d");

    }
}