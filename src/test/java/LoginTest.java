import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.constants.TestStandEndpoints;
import stellarburgers.pageobject.ForgotPasswordPage;
import stellarburgers.pageobject.HomePage;
import stellarburgers.pageobject.LoginPage;
import stellarburgers.pageobject.RegisterPage;
import stellarburgers.testdata.CreatingRandomData;
import stellarburgers.UserRequest;
import stellarburgers.UserResponse;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    private String email;
    private String password;
    private UserResponse userResponse;
    private HomePage homePage;
    private LoginPage loginPage;
    private String expectedButtonName = "Оформить заказ";

    @Step("Check that the name of a Login/Order button matches the expected")
    public void checkOrderButtonName(HomePage signedInHomePage, String buttonName) {
        signedInHomePage.loginOrOrderButton.shouldHave(Condition.exactText(buttonName));
    }

    // Перед каждым тестом формируем случайные тестовые данные и создаем пользователя
    @Before
    public void setUp() {
        RestAssured.baseURI = TestStandEndpoints.BASE_URL;
        String name = CreatingRandomData.getRandomEdeltanString();
        this.email = CreatingRandomData.getRandomEdeltanEmail();
        this.password = CreatingRandomData.getRandomEdeltanString();
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
    @DisplayName("Checking that it is available to log in clicking the Login button." +
            "The Home page should open." +
            "The name of the Login/Order button should match the expected")
    public void checkLoginHomePageEnterButton() {
        this.homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);
        this.loginPage = homePage.getLoginPageLoginButton();
        HomePage signedInHomePage = loginPage.loginProfile(email,password);
        checkOrderButtonName(signedInHomePage, expectedButtonName);
    }

    @Test
    @DisplayName("Checking that it is available to log in clicking the Profile button in the Header. " +
            "The Home page should open. " +
            "The name of the Login/Order button should match the expected")
    public void checkLoginHomePageProfileLink() {
        this.homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);
        this.loginPage = homePage.getLoginPageProfileLink();
        HomePage signedInHomePage = loginPage.loginProfile(email,password);
        checkOrderButtonName(signedInHomePage, expectedButtonName);
    }

    @Test
    @DisplayName("Checking that it is available to log in clicking the Login button in the Register page. " +
            "The Home page should open. " +
            "The name of the Login/Order button should match the expected")
    public void checkLoginRegisterPage() {
        RegisterPage registerPage = open(RegisterPage.REGISTER_PAGE_URL, RegisterPage.class);
        this.loginPage = registerPage.getLoginPageLoginButton();
        HomePage signedInHomePage = loginPage.loginProfile(email,password);
        checkOrderButtonName(signedInHomePage, expectedButtonName);
    }

    @Test
    @DisplayName("Checking that it is available to log in clicking the Login button in the Forgot Password page. " +
            "The Home page should open. " +
            "The name of the Login/Order button should match the expected")
    public void checkLoginForgotPasswordPage() {
        ForgotPasswordPage forgotPasswordPage = open(ForgotPasswordPage.FORGOT_PASSWORD_URL, ForgotPasswordPage.class);
        this.loginPage = forgotPasswordPage.getLoginPageEnterButton();
        HomePage signedInHomePage = loginPage.loginProfile(email,password);
        checkOrderButtonName(signedInHomePage, expectedButtonName);
    }
}
