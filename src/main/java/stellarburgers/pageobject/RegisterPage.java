package stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {
    // URL Страницы регистрации
    public final static String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    // Локатор поля ввода Имя
    @FindBy(how= How.XPATH, using = ".//form/fieldset[1]/div/div/input")
    public SelenideElement nameField;

    // Локатор поля ввода Email
    @FindBy(how= How.XPATH, using = ".//form/fieldset[2]/div/div/input")
    public SelenideElement emailField;

    // Локатор поля ввода Пароль
    @FindBy(how= How.XPATH, using = ".//form/fieldset[3]/div/div/input")
    public SelenideElement passwordField;

    // Локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//form/button")
    public SelenideElement registerButton;

    // Локатор ссылки Войти
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    public SelenideElement loginLink;

    // Локатор заголовка страницы
    @FindBy(how = How.XPATH, using = ".//div[@class = 'Auth_login__3hAey']/h2")
    public SelenideElement header;

    @FindBy(how = How.XPATH, using = ".//fieldset/div/p")
    public SelenideElement incorrectPasswordWarning;

    // Метод ввода значения в поле ввода Имени
    @Step("Input a name value")
    public void inputNameField(String value) {
        nameField.sendKeys(value);
    }

    // Метод ввода значения в поле ввода Email
    @Step("Input an email value")
    public void inputEmailField(String value) {
        emailField.sendKeys(value);
    }

    // Метод ввода значения в поле ввода Пароля
    @Step("Input a password value")
    public void inputPasswordField(String value) {
        passwordField.sendKeys(value);
    }

    // Метод клика по кнопке Зарегистрироваться
    @Step("Click the Register button")
    public void clickRegisterButton() {
        registerButton.click();
    }

    // Метод клика по кнопке Войти
    @Step("Click the Login button on the Register page")
    public void clickLoginLink() {
        loginLink.click();
    }

    // Метод получения экземпляра страницы Логин
    // клик по кнопке "Войти"
    @Step("Click the Login button on the Register page and go to the Login page")
    public LoginPage getLoginPageLoginButton() {
        clickLoginLink();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    // Метод регистрации нового пользователя
    // возвращает экземпляр страницы Логин
    @Step("Fill out the Registration form, register a new user and go to the Login page")
    public LoginPage getLoginPageRegisterNewUser(String name, String email, String password) {
        inputNameField(name);
        inputEmailField(email);
        inputPasswordField(password);
        clickRegisterButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    // Метод регистрации нового пользователя
    @Step("Fill out the Registration form, register a new user")
    public void registerNewUser(String name, String email, String password) {
        inputNameField(name);
        inputEmailField(email);
        inputPasswordField(password);
        clickRegisterButton();
    }
}
