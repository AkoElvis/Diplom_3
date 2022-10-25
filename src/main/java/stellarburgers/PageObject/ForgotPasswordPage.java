package stellarburgers.PageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {
    // URL страницы Восстановление пароля
    public final static String FORGOT_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    // Локатор ссылки Войти
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    SelenideElement loginLink;

    // Метод клика по кнопке Войти
    public void clickLoginLink() {
        loginLink.click();
    }

    // Метод получения экземпляра страницы Логин
    // клик по кнопке "Войти"
    public LoginPage getLoginPageEnterButton() {
        clickLoginLink();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }
}
