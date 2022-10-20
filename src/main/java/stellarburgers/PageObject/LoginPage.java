package stellarburgers.PageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    // Локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    SelenideElement registerLink;

    // Локатор ссылки Личный кабинет
    @FindBy(how = How.XPATH, using = ".//nav/a")
    SelenideElement profileLink;

    // Метод клика по сслыке Зарегистрироваться
    public void clickRegisterButton() {
        registerLink.click();
    }

    // Метод клика по ссылке Личный кабинет
    public void clickProfileLink() {
        profileLink.click();
    }
}
