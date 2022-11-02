import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.constants.TestStandEndpoints;
import stellarburgers.pageobject.HomePage;
import stellarburgers.pageobject.LoginPage;
import stellarburgers.pageobject.ProfilePage;
import stellarburgers.testdata.CreatingRandomData;
import stellarburgers.UserRequest;
import stellarburgers.UserResponse;

import static com.codeborne.selenide.Selenide.open;

public class BuilderTest {
    private UserResponse userResponse;
    private ProfilePage profilePage;
    private HomePage builderPage;
    private String expectedButtonName = "Оформить заказ";

    @Step("Check that the name of a Login/Order button matches the expected")
    public void checkOrderButtonName(HomePage builderPage, String buttonName) {
        builderPage.loginOrOrderButton.shouldHave(Condition.exactText(buttonName));
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
    @DisplayName("Checking that it is available to go from the Profile to the Builder page. " +
            "The name of the Login/Order button should match the expected")
    public void checkGoBuilderClickBuilderButton() {
        this.builderPage = profilePage.getHomePageClickBuilderButton();
        checkOrderButtonName(builderPage, expectedButtonName);
    }

    @Test
    @DisplayName("Checking that it is available to click Logo and go to the Builder page. " +
            "The name of the Login/Order button should match the expected")
    public void checkGoBuilderClickLogo() {
        this.builderPage = profilePage.getHomePageClickLogo();
        checkOrderButtonName(builderPage, expectedButtonName);
    }
}