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

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;

public class ProfileTest {
    private String email;
    private UserResponse userResponse;
    private HomePage signedInHomePage;

    @Step("Check that the Login field contains the user's email")
    public void checkLoginFieldContainsEmail(ProfilePage profilePage, String email) {
        profilePage.loginValue.shouldHave(Condition.exactValue(email.toLowerCase(Locale.ROOT)));
    }

    // Перед каждым тестом формируем случайные тестовые данные и создаем пользователя
    @Before
    public void setUp() {
        RestAssured.baseURI = TestStandEndpoints.BASE_URL;
        String name = CreatingRandomData.getRandomEdeltanString();
        this.email = CreatingRandomData.getRandomEdeltanEmail();
        String password = CreatingRandomData.getRandomEdeltanString();
        UserRequest user = new UserRequest(email, password, name);
        this.userResponse = UserResponse.getRegisterUserResponse(user);
        // Раскомментировать строку ниже чтобы тестировать в браузере Firefox
        //Configuration.browser = Browsers.FIREFOX;
        HomePage homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);
        LoginPage loginPage = homePage.getLoginPageLoginButton();
        this.signedInHomePage = loginPage.loginProfile(email, password);
    }

    // После окончания теста удаляем созданного пользователя
    @After
    public void deleteCreatedUser() {
        UserResponse.deleteUser(userResponse);
    }

    @Test
    @DisplayName("Check that the authorized user may access the Profile by clicking the Profile button on the Home page. " +
            "The Profile page should open. " +
            "The Login field should contain the user's email")
    public void checkGoProfile() {
        ProfilePage profilePage = signedInHomePage.getProfilePageProfileLink();
        checkLoginFieldContainsEmail(profilePage,email);
    }
}
