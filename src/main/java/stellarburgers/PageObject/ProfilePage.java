package stellarburgers.PageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {
    // URL страницы «Личный кабинет»
    public final static String FORGOT_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    // Локатор плейсхолдера поля Логин
    @FindBy(how= How.XPATH, using = ".//li[2]/div/div/label")
    public SelenideElement loginPlaceholder;

    // Локатор значения поля Логин
    @FindBy(how= How.XPATH, using = ".//li[2]/div/div/input")
    public SelenideElement loginValue;
}
