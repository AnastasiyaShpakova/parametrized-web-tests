import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @CsvSource(value = {
            "кофе, #btn773, Лунго",
            "чай, #btn1185, Облепиховый чай",
            "десерт, #btn791, Наполеон"
    })

    @DisplayName("Добавление товаров в Корзину")
    @ParameterizedTest
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

}

