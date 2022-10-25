package stellarburgers.PageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class HomePage {
    // URL Главной страницы
    public final static String HOME_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    // Локатор ссылки Личный кабинет
    @FindBy(how = How.XPATH, using = ".//nav/a")
    SelenideElement profileLink;

    // Локатор кнопки Войти в аккаунт / Оформить заказ
    @FindBy(how = How.XPATH, using = ".//main/section/div/button")
    public SelenideElement loginOrOrderButton;

    // Метод клика по ссылке Личный кабинет
    public void clickProfileLink() {
        profileLink.click();
    }

    // Метод клика по кнопке Войти в аккаунт
    public void clickLoginOrOrderButton() {
        loginOrOrderButton.click();
    }

    // Метод получения экземпляра страницы Логин
    // клик по кнопке «Войти в аккаунт» на главной
    public LoginPage getLoginPageEnterButton() {
        clickLoginOrOrderButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    // Метод получения экземпляра страницы Логин
    // клик по кнопке «Личный кабинет» в шапке
    public LoginPage getLoginPageProfileLink() {
        clickProfileLink();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }
}
