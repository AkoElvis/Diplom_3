import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.Constants.Browsers;
import stellarburgers.PageObject.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

public class IngredientsListTest {

    private String expectedClassName = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    private String actualClassName;

    @Before
    public void setUp() {
        // Раскомментировать строку ниже чтобы тестировать в браузере Firefox
        //Configuration.browser = Browsers.FIREFOX;
    }

    @Step("Check that the button has become active")
    public void checkButtonIsActive(String expectedClassName, String actualClassName) {
        assertEquals(expectedClassName, actualClassName);
    }

    @Test
    @DisplayName("Checking that going to the Buns list is available by clicking the Buns button")
    public void checkGoingToBuns() {
        HomePage homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);
        // Переходим в конец списка ингредиентов чтобы кнопка Булки стала активной
        homePage.scrollToLastFillingsElement();
        // Кликаем кнопку Булки
        homePage.clickBunsButton();
        // Ждем окончания скролла
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Находим статус кнопки Булки
        this.actualClassName = homePage.bunsButton.getAttribute("class");
        // Кнопка Булки должна стать активной
        checkButtonIsActive(expectedClassName, actualClassName);
    }

    @Test
    @DisplayName("Checking that going to the Sauces list is available by clicking the Sauces button")
    public void checkGoingToSauces() {
        HomePage homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);
        // Кликаем кнопку Соусы
        homePage.clickSaucesButton();
        // Ждем окончания скролла
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Находим статус кнопки Соусы
        this.actualClassName = homePage.saucesButton.getAttribute("class");
        // Кнопка Соусы должна стать активной
        checkButtonIsActive(expectedClassName, actualClassName);
    }

    @Test
    @DisplayName("Checking that going to the Fillings list is available by clicking the Fillings button")
    public void checkGoingToFillings() {
        HomePage homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);
        // Кликаем кнопку Начинки
        homePage.clickFillingsButton();
        // Ждем окончания скролла
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Находим статус кнопки Начинки
        this.actualClassName = homePage.fillingsButton.getAttribute("class");
        // Кнопка Начинки должна стать активной
        checkButtonIsActive(expectedClassName, actualClassName);
    }
}

