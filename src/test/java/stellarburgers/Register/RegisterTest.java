package stellarburgers.Register;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Assert;
import org.junit.Test;
import stellarburgers.PageObject.HomePage;
import stellarburgers.PageObject.LoginPage;
import stellarburgers.PageObject.RegisterPage;

import static com.codeborne.selenide.Selenide.open;

public class RegisterTest {
    private String name = "aДядька";
    private String email = "aabcexample@example.com";
    private String password = "somePassword";

    //создаем переменные для запуска тестов в разных браузерах
    private String browserChrome = "Chrome";
    //private String browserFirefox = "firefox";

    @Test
    public void checkRegistration() {
        // Открываем страницу регистрации в браузере Chrome
        Configuration.browser = browserChrome;
        RegisterPage registerPage = open(RegisterPage.REGISTER_PAGE_URL, RegisterPage.class);
        // Вводим "обычные" тестовые данные Имя, Email, Пароль
        registerPage.inputNameField(name);
        registerPage.inputEmailField(email);
        registerPage.inputPasswordField(password);
        // Кликаем кнопку Зарегистрироваться
        registerPage.clickRegisterButton();
        // Проверяем что заголовок страницы - "Вход"
        registerPage.header.shouldHave(Condition.text("Вход"));
    }

    @Test
    public void checkPassword() {
        // Открываем страницу регистрации в браузере Chrome
        Configuration.browser = browserChrome;
        RegisterPage registerPage = open(RegisterPage.REGISTER_PAGE_URL, RegisterPage.class);
        // Вводим "обычные" тестовые данные Имя, Email, Пароль
        registerPage.inputNameField(name);
        registerPage.inputEmailField(email);
        registerPage.inputPasswordField("123");
        // Кликаем кнопку Зарегистрироваться
        registerPage.clickRegisterButton();
        // Проверяем что появилось сообщение о некорректном пароле
        registerPage.incorrectPasswordWarning.shouldHave(Condition.text("Некорректный пароль"));
    }
}
