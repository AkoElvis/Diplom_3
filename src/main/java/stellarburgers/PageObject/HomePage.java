package stellarburgers.PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class HomePage {
    // URL Главной страницы
    public final static String HOME_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    // Локатор ссылки Личный кабинет
    @FindBy(how = How.XPATH, using = ".//nav/a")
    public SelenideElement profileLink;

    // Локатор кнопки Войти в аккаунт / Оформить заказ
    @FindBy(how = How.XPATH, using = ".//main/section/div/button")
    public SelenideElement loginOrOrderButton;

    // Метод клика по ссылке Личный кабинет
    @Step("Click the Profile button in the Header on the Home page")
    public void clickProfileLink() {
        profileLink.click();
    }

    // Метод клика по кнопке Войти в аккаунт / Оформить заказ
    @Step("Click the button Login/Order on the Home page")
    public void clickLoginOrOrderButton() {
        loginOrOrderButton.click();
    }

    // Метод получения экземпляра страницы Логин
    // клик по кнопке «Войти в аккаунт» на главной
    @Step("Click the Login/Order button on the Home page and go to the Login page")
    public LoginPage getLoginPageLoginButton() {
        clickLoginOrOrderButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    // Метод получения экземпляра страницы Логин
    // клик по кнопке «Личный кабинет» в шапке
    @Step("Click the Profile button in the Header on the Home page and go to the Login page")
    public LoginPage getLoginPageProfileLink() {
        clickProfileLink();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    // Метод получения экземпляра страницы Личный кабинет
    // клик по кнопке «Личный кабинет» в шапке
    @Step("Click the Profile button in the Header on the Home page and go to the Profile page")
    public ProfilePage getProfilePageProfileLink() {
        clickProfileLink();
        ProfilePage profilePage = page(ProfilePage.class);
        return profilePage;
    }
}
