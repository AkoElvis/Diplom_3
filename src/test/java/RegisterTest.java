import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.Constants.TestStandEndpoints;
import stellarburgers.Constants.Browsers;
import stellarburgers.PageObject.RegisterPage;
import stellarburgers.TestData.CreatingRandomData;
import stellarburgers.UserRequest;
import stellarburgers.UserResponse;

import static com.codeborne.selenide.Selenide.open;

public class RegisterTest {
    private String name;
    private String email;
    private String password;
    private UserRequest user;
    private UserResponse userResponse;

    @Before
    public void setUp() {
        RestAssured.baseURI = TestStandEndpoints.BASE_URL;
        this.name = CreatingRandomData.getRandomAlekseyString();
        this.email = CreatingRandomData.getRandomAlekseyEmail();
        // Раскомментировать строку ниже чтобы тестировать в браузере Firefox
        //Configuration.browser = Browsers.FIREFOX;
    }

    // После окончания теста удаляем созданного пользователя
    @After
    public void deleteCreatedUser() {
        this.user = new UserRequest(email,password);
        this.userResponse = UserResponse.getLoginUserResponse(user);
        if (userResponse.getSuccess()) {
            UserResponse.deleteUser(userResponse);}
    }

    @Test
    public void checkSuccessfulRegistration() {
        RegisterPage registerPage = open(RegisterPage.REGISTER_PAGE_URL, RegisterPage.class);
        // Создаем "обычный" пароль
        this.password = CreatingRandomData.getRandomAlekseyString();
        // Вводим тестовые данные Имя, Email, Пароль
        registerPage.inputNameField(name);
        registerPage.inputEmailField(email);
        registerPage.inputPasswordField(password);
        // Кликаем кнопку Зарегистрироваться
        registerPage.clickRegisterButton();
        // Проверяем что заголовок страницы - "Вход"
        registerPage.header.shouldHave(Condition.text("Вход"));
    }

    @Test
    public void checkUnableRegisterShortPassword() {
        RegisterPage registerPage = open(RegisterPage.REGISTER_PAGE_URL, RegisterPage.class);
        // Создаем короткий пароль 5 символов
        this.password = CreatingRandomData.getRandomShortAlexString();
        // Вводим тестовые данные Имя, Email, Пароль
        registerPage.inputNameField(name);
        registerPage.inputEmailField(email);
        registerPage.inputPasswordField(password);
        // Кликаем кнопку Зарегистрироваться
        registerPage.clickRegisterButton();
        // Проверяем что появилось сообщение о некорректном пароле
        registerPage.incorrectPasswordWarning.shouldHave(Condition.text("Некорректный пароль"));
    }
}
