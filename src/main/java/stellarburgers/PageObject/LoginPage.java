package stellarburgers.PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    // Локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    SelenideElement registerLink;

    // Локатор ссылки Личный кабинет
    @FindBy(how = How.XPATH, using = ".//nav/a")
    SelenideElement profileLink;

    @FindBy(how = How.XPATH, using = ".//div[@class = 'Auth_login__3hAey']/h2")
    public
    SelenideElement header;

    // Локатор поля ввода Email
    @FindBy(how= How.XPATH, using = ".//form/fieldset[1]/div/div/input")
    SelenideElement emailField;

    // Локатор поля ввода Пароля
    @FindBy(how= How.XPATH, using = ".//form/fieldset[2]/div/div/input")
    SelenideElement passwordField;

    // Локатор кнопки Войти
    @FindBy(how= How.XPATH, using = ".//form/button")
    SelenideElement enterButton;

    // Метод клика по сслыке Зарегистрироваться
    public void clickRegisterButton() {
        registerLink.click();
    }

    // Метод клика по ссылке Личный кабинет
    public void clickProfileLink() {
        profileLink.click();
    }

    // Метод получения названия заголовка
    public String getHeader() {
        return header.getText();
    }

    // Метод ввода значения в поле ввода Email
    public void inputEmailField(String value) {
        emailField.sendKeys(value);
    }

    // Метод ввода значения в поле ввода Пароля
    public void inputPasswordField(String value) {
        passwordField.sendKeys(value);
    }

    // Метод ожидания загрузки страницы
    public void waitForLoadLoginPage() {
    header.shouldHave(Condition.text("Вход"));
    }

    // Метод клика по кнопке Войти
    public void clickEnterButton() {
        enterButton.click();
    }

    // Метод входа в личный кабинет
    // возвращает экземпляр класса главной страницы
    public HomePage loginProfile(String email, String password) {
        inputEmailField(email);
        inputPasswordField(password);
        // клик по Войти
        clickEnterButton();
        HomePage homePage = page(HomePage.class);
        return homePage;
    }
}
