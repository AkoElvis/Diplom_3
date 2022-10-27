package stellarburgers.PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
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

    // Локатор кнопки Булки
    @FindBy(how = How.XPATH, using = ".//main/section[1]/div/div[1]")
    public SelenideElement bunsButton;

    // Локатор кнопки Соусы
    @FindBy(how = How.XPATH, using = ".//main/section[1]/div/div[2]")
    public SelenideElement saucesButton;

    // Локатор кнопки Начинки
    @FindBy(how = How.XPATH, using = ".//main/section[1]/div/div[3]")
    public SelenideElement fillingsButton;

    // Локатор последнего элемента списка начинок
    @FindBy(how = How.XPATH, using = ".//section/div/ul[3]/a[last()]")
    public SelenideElement lastFillingsElement;

    // Метод ожидания загрузки страницы
    @Step("Wait until the Home page header is visible")
    public void waitForLoadHomePage() {
        lastFillingsElement.shouldBe(Condition.visible);
    }

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

    // Метод клика по кнопке Булки
    @Step("Click the Buns button")
    public void clickBunsButton() { bunsButton.shouldBe(Condition.enabled).click(); }

    // Метод клика по кнопке Соусы
    @Step("Click the Sauces button")
    public void clickSaucesButton() {
        saucesButton.shouldBe(Condition.enabled).click();
    }

    // Метод клика по кнопке Начинки
    @Step("Click the Fillings button")
    public void clickFillingsButton() {
        fillingsButton.shouldBe(Condition.enabled).click();
    }

    // Метод скролла к последнему элементу списка начинок
    @Step("Scroll to the last fillings element")
    public void scrollToLastFillingsElement() {
        lastFillingsElement.sendKeys(Keys.END);
    }
}
