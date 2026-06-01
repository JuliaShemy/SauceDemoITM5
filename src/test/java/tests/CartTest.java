package tests;/*
a. Залогиниться
b. Добавить товар в корзину
c. Перейти в корзину
d. Проверить (assertEquals) его имя в корзине
 */

import io.qameta.allure.*;
import jdk.jfr.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
@Log4j2
public class CartTest extends BaseTest {
    //добавление товара в корзину
    @Test(description = "Проверка добавления товара в корзину",
            testName = "Проверка добавления товара в корзину",
            groups = {"smoke"})
    @Description("Проверка добавления товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Julia Shem")
    public void addProductToCart() {
        log.info("Checking adding product to cart");
        loginPage.open()
                .login("standard_user", "secret_sauce");
        String nameItem = productsPage.getFirstItemName();
        productsPage.addFirstItemToCart()
                .openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getProductName(), nameItem);
    }

    //корзина не пустая после добавления товара
    @Test(description = "Проверка, что после добавления товара в корзину, она не пустая",
            testName = "Проверка, что корзина не пустая")
    @Description("Проверка, что корзина не пустая")
    public void cartIsNotEmptyAfterAddProduct() {
        log.info("Checking cart is not empty after adding product");
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addFirstItemToCart()
                .openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.isCartEmpty());
    }

    //проверка работы кнопки вернуться на станицу товаров
    @Test(description = "Проверка работы кнопки возврата из корзины на страницу товаров",
            testName = "Проверка работы кнопки Continue Shopping")
    @Description("Проверка работы кнопки возврата из корзины на страницу товаров")
    public void checkButtonContinueShopping() {
        log.info("Checking Continue Shopping button");
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    //проверка кнопки чекаут
    @Test(description = "Проверка работы кнопки перехода к оформлению товара из корзины ",
            testName = "Проверка работы кнопки Checkout",
            groups = {"smoke"})
    @Description("Проверка работы кнопки перехода к оформлению товара")
    public void checkButtonCheckout() {
        log.info("Checking Checkout button");
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addFirstItemToCart()
                .openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"));
    }

    //проверка цены товара
    @Test(description = "Проверка цены товара в корзине",
            testName = "Проверка цены товара в корзине")
    @Description("Проверка цены товара в корзине")
    public void productPriceInCartIsCorrect() {
        log.info("Checking product price in cart");
        loginPage.open()
                .login("standard_user", "secret_sauce");
        String expectedPrice = productsPage.getFirstItemPrice();
        productsPage.addFirstItemToCart()
                .openCart();
        CartPage cartPage = new CartPage(driver);
        String actualPrice = cartPage.getProductPrice();
        Assert.assertEquals(actualPrice, expectedPrice);
    }

    //удаление товара
    @Test(description = "Проверка удаления товара из корзины",
            testName = "Проверка удаления товара")
    @Description("Проверка удаления товара из корзины")
    public void removeProductFromCart() {
        log.info("Checking removing product from cart");
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addFirstItemToCart()
                .openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.removeProduct();
        Assert.assertTrue(cartPage.isCartEmpty());
    }
}