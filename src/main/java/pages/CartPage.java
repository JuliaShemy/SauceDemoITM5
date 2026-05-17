package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    //заголовок страницы
    private final By TITLE = By.cssSelector("[data-test=title]");
    //название товара
    private final By PRODUCT_NAME = By.className("inventory_item_name");
    //кнопка удаления товара
    private final By REMOVE_BUTTON = By.cssSelector("button.btn_secondary");
    //товары в корзине
    private final By ITEMS = By.className("cart_item");
    //кнопка чекаут
    private final By CHECKOUT = By.id("checkout");
    //кнопка продолжить покупки
    private final By CONTINUE_SHOPPING = By.id("continue-shopping");
    //цена товара
    private final By PRODUCT_PRICE = By.className("inventory_item_price");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    @Step("Открытие страницы корзины")
    //открыть страницу корзины
    public void open() {
        driver.get(BASE_URL + "/cart.html");
    }
    @Step("Получение имени товара")
    //получить имя товара
    public String getProductName() {
        return driver.findElement(PRODUCT_NAME).getText();
    }
    @Step("Удаление товара из корзины")
    //удалить товар
    public void removeProduct() {
        driver.findElement(REMOVE_BUTTON).click();
    }
    @Step("Проверка наличия товаров в корзине")
    //проверить наличие товаров в корзине
    public boolean isCartEmpty() {
        return driver.findElements(ITEMS).isEmpty();
    }
    @Step("Проверка кнопки чекаут")
    //нажать кнопку чекаут
    public void clickCheckout() {
        driver.findElement(CHECKOUT).click();
    }
    @Step("Праверка кнопки возврата на страницу покупок")
    //вернуться на страницу покупок
    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING).click();
    }
    @Step("Получение цены товара")
    //получить цену товара
    public String getProductPrice() {
        return driver.findElement(PRODUCT_PRICE).getText();
    }
    @Step("Проварка заголовка страницы корзины")
    //Получить заголовок страницы
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
}