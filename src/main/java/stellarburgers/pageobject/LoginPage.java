package stellarburgers.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    // Локатор заголовка
    @FindBy(how = How.XPATH, using = ".//div[@class = 'Auth_login__3hAey']/h2")
    public SelenideElement header;

    // Локатор поля ввода Email
    @FindBy(how= How.XPATH, using = ".//form/fieldset[1]/div/div/input")
    public SelenideElement emailField;

    // Локатор поля ввода Пароля
    @FindBy(how= How.XPATH, using = ".//form/fieldset[2]/div/div/input")
    public SelenideElement passwordField;

    // Локатор кнопки Войти
    @FindBy(how= How.XPATH, using = ".//form/button")
    public SelenideElement enterButton;

    // Метод получения названия заголовка
    @Step("Get header text")
    public String getHeader() {
        return header.getText();
    }

    // Метод ввода значения в поле ввода Email
    @Step("Input login value")
    public void inputEmailField(String value) {
        emailField.sendKeys(value);
    }

    // Метод ввода значения в поле ввода Пароля
    @Step("Input password value")
    public void inputPasswordField(String value) {
        passwordField.sendKeys(value);
    }

    // Метод ожидания загрузки страницы
    @Step("Wait until the Login page header is visible")
    public void waitForLoadLoginPage() {
    header.shouldHave(Condition.text("Вход"));
    }

    // Метод клика по кнопке Войти
    @Step("Click the Login button on the Login page")
    public void clickEnterButton() {
        enterButton.click();
    }

    // Метод входа в личный кабинет
    // возвращает экземпляр класса главной страницы
    @Step("Fill out an authorization form and go to the Home page")
    public HomePage loginProfile(String email, String password) {
        inputEmailField(email);
        inputPasswordField(password);
        clickEnterButton();
        HomePage homePage = page(HomePage.class);
        homePage.waitForLoadHomePage();
        return homePage;
    }
}
