import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        //Configuration.holdBrowserOpen = true;
    }

    @DisplayName("Добавление товара из раздела Кофе в Корзину")
    @Test
    @Tags({@Tag("UI"), @Tag("Cart"), @Tag("Drinks")})
    void addCoffeeToBasketTest() {
        open("https://yagoda.coffee/shop/");
        $("#KeyWords").setValue("кофе");
        $("#btn773").click();
        open("https://yagoda.coffee/shop/cart.php");
        $("#MAINFORM").shouldHave(
                text("Лунго"),
                text("Стоимость заказа"),
                text("Доставка"),
                text("Скидка"));
    }

    @DisplayName("Добавление товара из раздела Чай в Корзину")
    @Test
    @Tags({@Tag("UI"), @Tag("Cart"), @Tag("Drinks")})
    void addTeaToBasketTest() {
        open("https://yagoda.coffee/shop/");
        $("#KeyWords").setValue("чай");
        $("#btn1185").click();
        open("https://yagoda.coffee/shop/cart.php");
        $("#MAINFORM").shouldHave(
                text("Облепиховый чай"),
                text("Стоимость заказа"),
                text("Доставка"),
                text("Скидка"));
    }

    @DisplayName("Добавление товара из раздела Десерт в Корзину")
    @Test
    @Tags({@Tag("UI"), @Tag("Cart"), @Tag("Food")})
    void addDessertToBasketTest() {
        open("https://yagoda.coffee/shop/");
        $("#KeyWords").setValue("десерт");
        $("#btn791").click();
        open("https://yagoda.coffee/shop/cart.php");
        $("#MAINFORM").shouldHave(
                text("Наполеон"),
                text("Стоимость заказа"),
                text("Доставка"),
                text("Скидка"));
    }
}
