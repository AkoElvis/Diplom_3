package stellarburgers.PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {
    // URL страницы «Личный кабинет»
    public final static String FORGOT_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    // Локатор значения поля Логин
    @FindBy(how= How.XPATH, using = ".//li[2]/div/div/input")
    public SelenideElement loginValue;

    // Локатор кнопки Конструктор
    @FindBy(how = How.XPATH, using = ".//nav/ul/li[1]/a")
    public SelenideElement builderButton;

    // Локатор логотипа Stellar Burgers
    @FindBy(how = How.XPATH, using = ".//header/nav/div/a")
    public SelenideElement logo;

    // Локатор кнопки Выход
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    public SelenideElement logoutButton;

    // Метод клика по кнопке Конструктор
    @Step("Click the Builder button on the Profile page")
    public void clickBuilderButton() {
        builderButton.click();
    }

    // Метод клика по логотипу Stellar Burgers
    @Step("Click the logo on the Profile page")
    public void clickLogo() {
        logo.click();
    }

    // Метод клика по кнопке Выход
    @Step("Click the Logout button on the Profile page")
    public void clickLogoutButton() {
        logoutButton.click();
    }

    // Метод получения экземпляра Главной страницы
    // клик на «Конструктор»
    @Step("Click the Builder button on the Profile page and go to the Home page")
    public HomePage getHomePageClickBuilderButton() {
        clickBuilderButton();
        HomePage homePage = page(HomePage.class);
        homePage.waitForLoadHomePage();
        return homePage;
    }

    // Метод получения экземпляра Главной страницы
    // клик по логотипу Stellar Burgers
    @Step("Click the logo on the Profile page and go to the Home page")
    public HomePage getHomePageClickLogo() {
        clickLogo();
        HomePage homePage = page(HomePage.class);
        homePage.waitForLoadHomePage();
        return homePage;
    }

    // Метод получения экземпляра страницы Логин
    // клик по кнопке Выход
    @Step("Click the Logout button on the Profile page and go to the Login page")
    public LoginPage getLoginPageClickLogoutButton() {
        clickLogoutButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }
}
