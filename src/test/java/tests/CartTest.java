package tests;/*
a. Залогиниться
b. Добавить товар в корзину
c. Перейти в корзину
d. Проверить (assertEquals) его имя в корзине
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

public class CartTest extends BaseTest {
    //добавление товара в корзину
    @Test(description = "Проверка добавления товара в корзину",
            testName = "Проверка добавления товара в корзину",
            groups = {"smoke"})
    public void addProductToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        String nameItem = productsPage.getFirstItemName();
        productsPage.addFirstItemToCart();
        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getProductName(), nameItem);
    }

    //корзина не пустая после добавления товара
    @Test(description = "Проверка, что после добавления товара в корзину, она не пустая",
            testName = "Проверка, что корзина не пустая")
    public void cartIsNotEmptyAfterAddProduct() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addFirstItemToCart();
        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.isCartEmpty());
    }

    //проверка работы кнопки вернуться на станицу товаров
    @Test(description = "Проверка работы кнопки возврата из корзины на страницу товаров",
            testName = "Проверка работы кнопки Continue Shopping")
    public void checkButtonContinueShopping() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    //проверка кнопки чекаут
    @Test(description = "Проверка работы кнопки перехода к оформлению товара из корзины ",
            testName = "Проверка работы кнопки Checkout",
            groups = {"smoke"})
    public void checkButtonCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addFirstItemToCart();
        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"));
    }

    //проверка цены товара
    @Test(description = "Проверка цены товара в корзине",
            testName = "Проверка цены товара в корзине")
    public void productPriceInCartIsCorrect() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        String expectedPrice = productsPage.getFirstItemPrice();
        productsPage.addFirstItemToCart();
        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        String actualPrice = cartPage.getProductPrice();
        Assert.assertEquals(actualPrice, expectedPrice);
    }

    //удаление товара
    @Test(description = "Проверка удаления товара из корзины",
            testName = "Проверка удаления товара")
    public void removeProductFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addFirstItemToCart();
        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.removeProduct();
        Assert.assertTrue(cartPage.isCartEmpty());
    }
}