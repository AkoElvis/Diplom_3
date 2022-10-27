import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.Constants.TestStandEndpoints;
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
    private String expectedHeaderName = "Вход";
    private String expectedWarningMessage = "Некорректный пароль";

    @Step("Check that the page header matches the expected")
    public void checkPageHeader(LoginPage loginPage,String headerName) {
        loginPage.header.shouldHave(Condition.exactText(headerName));
    }

    @Step("Check that the warning message matches the expected")
    public void checkWarningMessage(RegisterPage registerPage, String warningMessage) {
        registerPage.incorrectPasswordWarning.shouldHave(Condition.exactText(warningMessage));
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = TestStandEndpoints.BASE_URL;
        this.name = CreatingRandomData.getRandomEdeltanString();
        this.email = CreatingRandomData.getRandomEdeltanEmail();
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
    @DisplayName("Checking that registration is available. " +
            "The Login page should open. " +
            "The name of the page header should match the expected")
    public void checkSuccessfulRegistration() {
        // Создаем "обычный" пароль
        this.password = CreatingRandomData.getRandomEdeltanString();
        // Заполняем форму регистрации и нажимаем Зарегистрироваться
        // открывается страница Логин
        LoginPage loginPage = registerPage.getLoginPageRegisterNewUser(name,email,password);
        // Проверяем что заголовок открывшейся страницы - "Вход"
        checkPageHeader(loginPage,expectedHeaderName);
    }

    @Test
    @DisplayName("Checking that registration with a short password is unavailable. " +
            "The Login page should not open. " +
            "The warning message text should match the expected")
    public void checkUnableRegisterShortPassword() {
        // Создаем короткий пароль 5 символов
        this.password = CreatingRandomData.getRandomShortAlexString();
        // Заполняем форму регистрации и нажимаем Зарегистрироваться
        // не должна открыться страница Логин
        registerPage.registerNewUser(name,email,password);
        // Проверяем что появилось сообщение о некорректном пароле
        checkWarningMessage(registerPage,expectedWarningMessage);
    }
}
