package stellarburgers.PageObject;

import com.codeborne.selenide.SelenideElement;
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

    // Метод клика по кнопке Конструктор
    public void clickBuilderButton() {
        builderButton.click();
    }

    // Метод клика по логотипу Stellar Burgers
    public void clickLogo() {
        logo.click();
    }

    // Метод получения экземпляра Главной страницы
    // клик на «Конструктор»
    public HomePage getHomePageClickBuilderButton() {
        clickBuilderButton();
        return page(HomePage.class);
    }

    // Метод получения экземпляра Главной страницы
    // клик по логотипу Stellar Burgers
    public HomePage getHomePageClickLogo() {
        clickLogo();
        return page(HomePage.class);
    }
}
