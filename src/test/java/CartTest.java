/*
a. Залогиниться
b. Добавить товар в корзину
c. Перейти в корзину
d. Проверить (assertEquals) его имя в корзине
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

public class CartTest {

    @Test
    public void addProductToCart() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
        //сохраняем в переменную название первого товара на странице
        WebElement firstItem = driver.findElements(By.className("inventory_item")).get(0);
        String nameItem = firstItem.findElement(By.className("inventory_item_name")).getText();
        //добаваляем в корзину
        driver.findElements(By.cssSelector("button.btn_inventory")).get(0).click();
        //переходим в корзину
        driver.findElement(By.className("shopping_cart_link")).click();
        //находим элемент в корзине
        String productNameInCart = driver.findElement(By.className("inventory_item_name")        ).getText();

        Assert.assertEquals(productNameInCart, nameItem);
        driver.quit();
    }
}