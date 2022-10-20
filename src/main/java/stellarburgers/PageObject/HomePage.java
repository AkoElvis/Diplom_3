package stellarburgers.PageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {
    // URL Главной страницы
    public final static String HOME_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    // Локатор ссылки Личный кабинет
    @FindBy(how = How.XPATH, using = ".//nav/a")
    SelenideElement profileLink;

    // Локатор кнопки Зарегистрироваться на странице Логин
    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    SelenideElement registerLink;

    // Метод клика по ссылке Личный кабинет
    public void clickProfileLink() {
        profileLink.click();
    }

    // Метод клика по сслыке Зарегистрироваться на странице Логин
    public void clickRegisterLink() {
        registerLink.click();
    }
}
