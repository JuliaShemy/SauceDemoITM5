package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
@Log4j2
public class ProductsPage extends BasePage {
    //заголовок страницы
    private final By TITLE = By.cssSelector("[data-test=title]");
       //список инаименований товаров
    private final By ITEM_NAME = By.className("inventory_item_name");
    //Кнопки добавления товаров в корзину
    private final By ADD_TO_CART_BUTTONS = By.cssSelector("button.btn_inventory");
    //Иконка корзины
    private final By CART = By.className("shopping_cart_link");
    //цена товара
    private final By ITEM_PRICE = By.className("inventory_item_price");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }
    @Override
    @Step("Открытие страницы товаров")
    //Открыть страницу товаров
    public ProductsPage open() {
        log.info("Opening Products page");
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }
    @Override
    @Step("Проверка открытия страницы товаров")
    public ProductsPage isPageOpened() {
        log.info("Checking Products page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE))
                .isDisplayed();
        return this;
    }
    @Step("Поверка заголовка страницы")
    //Получить заголовок страницы
    public String getTitle() {
        log.info("Getting Products page title");
        return driver.findElement(TITLE).getText();
    }
    @Step("Получение наименования первого товара")
    //Получить имя первого товара
    public String getFirstItemName() {
        log.info("Getting first product name");
        return driver.findElements(ITEM_NAME).get(0).getText();
    }
    @Step("Добавление первого товара в корзину")
    //Добавить первый товар в корзину
    public ProductsPage addFirstItemToCart() {
        log.info("Adding first product to cart");
        driver.findElements(ADD_TO_CART_BUTTONS).get(0).click();
        return this;
    }
    @Step("Получение цены первого товара")
    //Получить цену первого тоывара на странице
    public String getFirstItemPrice() {
        log.info("Getting first product price");
        return driver.findElements(ITEM_PRICE).get(0).getText();
    }
    @Step("Перейти в корзину")
    //Перейти в корзину
    public CartPage openCart() {
        log.info("Opening cart");
        driver.findElement(CART).click();
        return new CartPage(driver);
    }

}