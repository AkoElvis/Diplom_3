package stellarburgers.PageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage {
    // URL Страницы регистрации
    public final static String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    // Локатор поля ввода Имя
    @FindBy(how= How.XPATH, using = ".//form/fieldset[1]/div/div/input")
    SelenideElement nameField;

    // Локатор поля ввода Email
    @FindBy(how= How.XPATH, using = ".//form/fieldset[2]/div/div/input")
    SelenideElement emailField;

    // Локатор поля ввода Пароль
    @FindBy(how= How.XPATH, using = ".//form/fieldset[3]/div/div/input")
    SelenideElement passwordField;

    // Локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//form/button")
    SelenideElement registerButton;

    // Локатор ссылки Войти
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    SelenideElement loginLink;

    // Локатор заголовка страницы
    @FindBy(how = How.XPATH, using = ".//div[@class = 'Auth_login__3hAey']/h2")
    public
    SelenideElement header;

    @FindBy(how = How.XPATH, using = ".//fieldset/div/p")
    public
    SelenideElement incorrectPasswordWarning;

    // Метод ввода значения в поле ввода Имени
    public void inputNameField(String value) {
        nameField.sendKeys(value);
    }

    // Метод ввода значения в поле ввода Email
    public void inputEmailField(String value) {
        emailField.sendKeys(value);
    }

    // Метод ввода значения в поле ввода Пароля
    public void inputPasswordField(String value) {
        passwordField.sendKeys(value);
    }

    // Метод клика по кнопке Зарегистрироваться
    public void clickRegisterButton() {
        registerButton.click();
    }

    // Метод получения названия заголовка
    public String getHeader() {
        return header.getText();
    }

    // Метод получения текста сообщения о некорректном пароле
    public String getIncorrectPasswordWarning() {
        return incorrectPasswordWarning.getText();
    }
}
