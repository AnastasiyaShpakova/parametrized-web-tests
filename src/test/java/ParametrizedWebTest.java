import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParametrizedWebTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        //Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void setup() {
        open("https://yagoda.coffee/shop/");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @CsvSource(value = {
            "кофе, #btn777, Эспрессо",
            "чай, #btn1184, Еловый чай",
            "десерт, #btn791, Наполеон"
    })

    @DisplayName("Добавление товаров в Корзину")
    @ParameterizedTest
    @Tag("MainTest")
    void addGoodsToBasketTest(String searchValue, String selector, String goodInBasket) {
        $("#KeyWords").setValue(searchValue);
        $(selector).click();
        open("https://yagoda.coffee/shop/cart.php");
        $("#MAINFORM").shouldHave(
                text(goodInBasket),
                text("Стоимость заказа"),
                text("Доставка"),
                text("Скидка"));
    }

    @CsvFileSource(resources = "/testData.csv")
    @DisplayName("Добавление дополнительных товаров в Корзину")
    @ParameterizedTest
    @Tag("AdditionalTest")
    public void addAdditionalGoodsToBasketTest(String searchValue, String selector) {
        $("#KeyWords").setValue(searchValue);
        $(selector).click();
        open("https://yagoda.coffee/shop/cart.php");
        $("#MAINFORM").shouldHave(
                text("Стоимость заказа"),
                text("Доставка"),
                text("Скидка"));
    }

    @ValueSource(strings = {"Аэропресс", "Капучино", "Иммерсия"})
    @DisplayName("Проверка поисковых запросов")
    @ParameterizedTest
    @Tag("MainTest")
    public void successfulSearchTest(String value) {
        $("#KeyWords").setValue(value);
        $(".tab-content").shouldHave(
                text(value));
    }

}

