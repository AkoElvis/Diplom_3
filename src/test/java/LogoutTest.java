import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.Constants.TestStandEndpoints;
import stellarburgers.Constants.Browsers;
import stellarburgers.PageObject.HomePage;
import stellarburgers.PageObject.LoginPage;
import stellarburgers.PageObject.ProfilePage;
import stellarburgers.TestData.CreatingRandomData;
import stellarburgers.UserRequest;
import stellarburgers.UserResponse;

import static com.codeborne.selenide.Selenide.open;

public class LogoutTest {
    private String email;
    private String password;
    private String name;
    private UserResponse userResponse;
    private UserRequest user;
    private HomePage homePage;
    private LoginPage loginPage;
    private HomePage signedInHomePage;
    private ProfilePage profilePage;

    // Перед каждым тестом формируем случайные тестовые данные и создаем пользователя
    @Before
    public void setUp() {
        RestAssured.baseURI = TestStandEndpoints.BASE_URL;
        this.name = CreatingRandomData.getRandomAlekseyString();
        this.email = CreatingRandomData.getRandomAlekseyEmail();
        this.password = CreatingRandomData.getRandomAlekseyString();
        this.user = new UserRequest(email,password,name);
        this.userResponse = UserResponse.getRegisterUserResponse(user);
        // Раскомментировать строку ниже чтобы тестировать в браузере Firefox
        //Configuration.browser = Browsers.FIREFOX;
        this.homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);
        this.loginPage = homePage.getLoginPageEnterButton();
        this.signedInHomePage = loginPage.loginProfile(email,password);
        this.profilePage = signedInHomePage.getProfilePageProfileLink();
    }

    // После окончания теста удаляем созданного пользователя
    @After
    public void deleteCreatedUser() {
        UserResponse.deleteUser(userResponse);
    }

    @Test
    public void checkLogoutFromProfile() {
        LoginPage logoutPage = profilePage.getLoginPageClickLogoutButton();
        logoutPage.header.shouldHave(Condition.text("Вход"));
    }
}
