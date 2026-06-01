package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
@Log4j2
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
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @Override
    @Step("Открытие страницы корзины")
    //открыть страницу корзины
    public CartPage open() {
        log.info("Opening Cart page");
        driver.get(BASE_URL + "/cart.html");
        return this;
    }
    @Override
    @Step("Проверка открытия страницы корзины")
    public CartPage isPageOpened(){
        log.info("Checking Cart page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE))
                .isDisplayed();
        return this;
    }
    @Step("Получение имени товара")
    //получить имя товара
    public String getProductName() {
        String productName = driver.findElement(PRODUCT_NAME).getText();
        log.info("Product name in cart: '{}'", productName);
        return productName;
    }
    @Step("Удаление товара из корзины")
    //удалить товар
    public CartPage removeProduct() {
        log.info("Removing product from cart");
        driver.findElement(REMOVE_BUTTON).click();
        return this;
    }
    @Step("Проверка наличия товаров в корзине")
    //проверить наличие товаров в корзине
    public boolean isCartEmpty() {
        boolean isEmpty = driver.findElements(ITEMS).isEmpty();
        log.info("Cart is empty: {}", isEmpty);
        return isEmpty;
    }
    @Step("Проверка кнопки чекаут")
    //нажать кнопку чекаут
    public CheckoutPage clickCheckout() {
        log.info("Clicking Checkout button");
        driver.findElement(CHECKOUT).click();
        return new CheckoutPage(driver);
    }
    @Step("Праверка кнопки возврата на страницу покупок")
    //вернуться на страницу покупок
    public ProductsPage clickContinueShopping() {
        log.info("Clicking Continue Shopping button");
        driver.findElement(CONTINUE_SHOPPING).click();
        return new ProductsPage(driver);
    }
    @Step("Получение цены товара")
    //получить цену товара
    public String getProductPrice() {
        String productPrice = driver.findElement(PRODUCT_PRICE).getText();
        log.info("Product price in cart: '{}'", productPrice);
        return productPrice;
    }
    @Step("Проварка заголовка страницы корзины")
    //Получить заголовок страницы
    public String getTitle() {
        String title = driver.findElement(TITLE).getText();
        log.info("Cart page title: '{}'", title);
        return title;
    }
}