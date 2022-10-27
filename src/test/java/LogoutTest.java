import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.Constants.TestStandEndpoints;
import stellarburgers.PageObject.HomePage;
import stellarburgers.PageObject.LoginPage;
import stellarburgers.PageObject.ProfilePage;
import stellarburgers.TestData.CreatingRandomData;
import stellarburgers.UserRequest;
import stellarburgers.UserResponse;

import static com.codeborne.selenide.Selenide.open;

public class LogoutTest {
    private UserResponse userResponse;
    private ProfilePage profilePage;
    private String expectedHeaderName = "Вход";

    @Step("Check that the page header matches the expected")
    public void checkPageHeader(LoginPage logoutPage,String headerName) {
        logoutPage.header.shouldHave(Condition.exactText(headerName));
    }

    // Перед каждым тестом формируем случайные тестовые данные и создаем пользователя
    @Before
    public void setUp() {
        RestAssured.baseURI = TestStandEndpoints.BASE_URL;
        String name = CreatingRandomData.getRandomEdeltanString();
        String email = CreatingRandomData.getRandomEdeltanEmail();
        String password = CreatingRandomData.getRandomEdeltanString();
        UserRequest user = new UserRequest(email, password, name);
        this.userResponse = UserResponse.getRegisterUserResponse(user);
        // Раскомментировать строку ниже чтобы тестировать в браузере Firefox
        //Configuration.browser = Browsers.FIREFOX;
        HomePage homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);
        LoginPage loginPage = homePage.getLoginPageLoginButton();
        HomePage signedInHomePage = loginPage.loginProfile(email, password);
        this.profilePage = signedInHomePage.getProfilePageProfileLink();
    }

    // После окончания теста удаляем созданного пользователя
    @After
    public void deleteCreatedUser() {
        UserResponse.deleteUser(userResponse);
    }

    @Test
    @DisplayName("Checking that it is available to log out clicking the Logout button on the Profile page. " +
            "The Login page should open. " +
            "The name of the page header should match the expected")
    public void checkLogoutFromProfile() {
        LoginPage logoutPage = profilePage.getLoginPageClickLogoutButton();
        //logoutPage.header.shouldHave(Condition.text("Вход"));
        checkPageHeader(logoutPage, expectedHeaderName);
    }
}
