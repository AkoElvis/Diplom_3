import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.Constants.Browsers;
import stellarburgers.Constants.TestStandEndpoints;
import stellarburgers.PageObject.ForgotPasswordPage;
import stellarburgers.PageObject.HomePage;
import stellarburgers.PageObject.LoginPage;
import stellarburgers.PageObject.RegisterPage;
import stellarburgers.TestData.CreatingRandomData;
import stellarburgers.UserRequest;
import stellarburgers.UserResponse;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    private String email;
    private String password;
    private UserResponse userResponse;
    private HomePage homePage;
    private LoginPage loginPage;

    // Перед каждым тестом формируем случайные тестовые данные и создаем пользователя
    @Before
    public void setUp() {
        RestAssured.baseURI = TestStandEndpoints.BASE_URL;
        String name = CreatingRandomData.getRandomAlekseyString();
        this.email = CreatingRandomData.getRandomAlekseyEmail();
        this.password = CreatingRandomData.getRandomAlekseyString();
        UserRequest user = new UserRequest(email, password, name);
        this.userResponse = UserResponse.getRegisterUserResponse(user);
        // Раскомментировать строку ниже чтобы тестировать в браузере Firefox
        //Configuration.browser = Browsers.FIREFOX;
    }

    // После окончания теста удаляем созданного пользователя
    @After
    public void deleteCreatedUser() {
        UserResponse.deleteUser(userResponse);
    }
    @Test
    public void checkLoginHomePageEnterButton() {
        this.homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);
        this.loginPage = homePage.getLoginPageEnterButton();
        HomePage signedInHomePage = loginPage.loginProfile(email,password);
        signedInHomePage.loginOrOrderButton.shouldHave(Condition.text("Оформить заказ"));
    }

    @Test
    public void checkLoginHomePageProfileLink() {
        this.homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);
        this.loginPage = homePage.getLoginPageProfileLink();
        HomePage signedInHomePage = loginPage.loginProfile(email,password);
        signedInHomePage.loginOrOrderButton.shouldHave(Condition.text("Оформить заказ"));
    }

    @Test
    public void checkLoginRegisterPage() {
        RegisterPage registerPage = open(RegisterPage.REGISTER_PAGE_URL, RegisterPage.class);
        this.loginPage = registerPage.getLoginPageEnterButton();
        HomePage signedInHomePage = loginPage.loginProfile(email,password);
        signedInHomePage.loginOrOrderButton.shouldHave(Condition.text("Оформить заказ"));
    }

    @Test
    public void checkLoginForgotPasswordPage() {
        ForgotPasswordPage forgotPasswordPage = open(ForgotPasswordPage.FORGOT_PASSWORD_URL, ForgotPasswordPage.class);
        this.loginPage = forgotPasswordPage.getLoginPageEnterButton();
        HomePage signedInHomePage = loginPage.loginProfile(email,password);
        signedInHomePage.loginOrOrderButton.shouldHave(Condition.text("Оформить заказ"));
    }
}
