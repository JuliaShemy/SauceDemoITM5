package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    public void checkLocator() {

        driver.get("https://www.saucedemo.com/");
        //id
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //name
        driver.findElement(By.name("login-button")).click();
        //• classname
        driver.findElement(By.className("title"));
        //• tagname
        driver.findElement(By.tagName("footer"));
        //• linktext
        driver.findElement(By.linkText("Facebook"));
        //• partiallinktext
        driver.findElement(By.partialLinkText("Backpack"));
        //• xpath:
        //- поиск по атрибуту, например By.xpath("//tag[@attribute='value']");
        driver.findElement(By.xpath("//select[@data-test='product-sort-container']"));
        //- поиск по тексту, например By.xpath("//tag[text()='text']");
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        //- поиск по частичному совпадению атрибута, например
        //By.xpath("//tag[contains(@attribute,'text')]");
        driver.findElement(By.xpath("//button[contains(@id, 'add-to-cart')]"));
        //- поиск по частичному совпадению текста,
        driver.findElement(By.xpath("//button[contains(text(),'cart')]"));
        //- ancestor, например //*[text()='Enterprise Testing']//ancestor::div
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']//ancestor::div[@data-test='inventory-item-description']"));
        //- descendant
        driver.findElement(By.xpath("//div[@class='inventory_list']//descendant::div[contains(text(),'Sauce Labs Backpack')]"));
        //- following
        driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]/following::div[@class='inventory_item_price']"));
        //- parent
        driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Backpack')]/.."));
        //- preceding
        driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]/preceding::div[@class='inventory_item_price']"));
        //- Подсказка: XPath Axes
        //- *поиск элемента с условием AND, например
        ////input[@class='_2zrpKA_1dBPDZ' and @type='text']
        driver.findElement(By.xpath("//a[@class='bm-item menu-item' and @style='display: block;']"));

        //• css:
        //- .class
        driver.findElement(By.cssSelector(".header_label"));
        //- .class1.class2
        driver.findElement(By.cssSelector(".btn.btn_primary"));
        //- .class1 .class2
        driver.findElement(By.cssSelector(".pricebar .inventory_item_price"));
        //- #id
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bolt-t-shirt"));
        //- tagname
        driver.findElement(By.cssSelector("button"));
        //
        //- tagname.class
        driver.findElement(By.cssSelector("button.btn_primary"));
        //- [attribute=value]
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"));
        //- [attribute~=value]
        driver.findElement(By.cssSelector("[class~=btn]"));
        //- [attribute|=value]
        driver.findElement(By.cssSelector("[lang|='en']"));
        //- [attribute^=value]
        driver.findElement(By.cssSelector("[id^='add-to-cart']"));
        //- [attribute$=value]
        driver.findElement(By.cssSelector("[id$='backpack']"));
        //- [attribute*=value]
        driver.findElement(By.cssSelector("[id*='sauce-labs']"));
        }
}