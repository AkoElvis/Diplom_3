import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.Constants.Browsers;
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

    // Перед каждым тестом формируем случайные тестовые данные и создаем пользователя
    @Before
    public void setUp() {
        RestAssured.baseURI = TestStandEndpoints.BASE_URL;
        String name = CreatingRandomData.getRandomAlekseyString();
        this.email = CreatingRandomData.getRandomAlekseyEmail();
        String password = CreatingRandomData.getRandomAlekseyString();
        UserRequest user = new UserRequest(email, password, name);
        this.userResponse = UserResponse.getRegisterUserResponse(user);
        // Раскомментировать строку ниже чтобы тестировать в браузере Firefox
        Configuration.browser = Browsers.FIREFOX;
        HomePage homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);
        LoginPage loginPage = homePage.getLoginPageEnterButton();
        this.signedInHomePage = loginPage.loginProfile(email, password);
    }

    // После окончания теста удаляем созданного пользователя
    @After
    public void deleteCreatedUser() {
        UserResponse.deleteUser(userResponse);
    }

    @Test
    public void checkGoProfile() {
        ProfilePage profilePage = signedInHomePage.getProfilePageProfileLink();
        profilePage.loginValue.shouldHave(Condition.value(email.toLowerCase(Locale.ROOT)));
    }
}
