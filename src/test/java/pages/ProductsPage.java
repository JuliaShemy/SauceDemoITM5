package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    //Открыть страницу товаров
    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }

    //Получить заголовок страницы
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    //Получить имя первого товара
    public String getFirstItemName() {
        return driver.findElements(ITEM_NAME).get(0).getText();
    }

    //Добавить первый товар в корзину
    public void addFirstItemToCart() {
        driver.findElements(ADD_TO_CART_BUTTONS).get(0).click();
    }

    //Получить цену первого тоывара на странице
    public String getFirstItemPrice() {
        return driver.findElements(ITEM_PRICE).get(0).getText();
    }

    //Перейти в корзину
    public void openCart() {
        driver.findElement(CART).click();
    }
}