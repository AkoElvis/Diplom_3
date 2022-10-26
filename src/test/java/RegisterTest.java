import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.Constants.TestStandEndpoints;
import stellarburgers.Constants.Browsers;
import stellarburgers.PageObject.LoginPage;
import stellarburgers.PageObject.RegisterPage;
import stellarburgers.TestData.CreatingRandomData;
import stellarburgers.UserRequest;
import stellarburgers.UserResponse;

import static com.codeborne.selenide.Selenide.open;

public class RegisterTest {
    private String name;
    private String email;
    private String password;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        RestAssured.baseURI = TestStandEndpoints.BASE_URL;
        this.name = CreatingRandomData.getRandomAlekseyString();
        this.email = CreatingRandomData.getRandomAlekseyEmail();
        // Раскомментировать строку ниже чтобы тестировать в браузере Firefox
        //Configuration.browser = Browsers.FIREFOX;
        this.registerPage = open(RegisterPage.REGISTER_PAGE_URL, RegisterPage.class);
    }

    // После окончания теста удаляем созданного пользователя
    @After
    public void deleteCreatedUser() {
        UserRequest user = new UserRequest(email, password);
        UserResponse userResponse = UserResponse.getLoginUserResponse(user);
        if (userResponse.getSuccess()) {
            UserResponse.deleteUser(userResponse);}
    }

    @Test
    public void checkSuccessfulRegistration() {
        // Создаем "обычный" пароль
        this.password = CreatingRandomData.getRandomAlekseyString();
        // Заполняем форму регистрации и нажимаем Зарегистрироваться
        // открывается страница Логин
        LoginPage loginPage = registerPage.getLoginPageRegisterNewUser(name,email,password);
        loginPage.header.shouldHave(Condition.text("Вход"));
    }

    @Test
    public void checkUnableRegisterShortPassword() {
        // Создаем короткий пароль 5 символов
        this.password = CreatingRandomData.getRandomShortAlexString();
        // Заполняем форму регистрации и нажимаем Зарегистрироваться
        // не должна открыться страница Логин
        registerPage.registerNewUser(name,email,password);
        // Проверяем что появилось сообщение о некорректном пароле
        registerPage.incorrectPasswordWarning.shouldHave(Condition.text("Некорректный пароль"));
    }
}
