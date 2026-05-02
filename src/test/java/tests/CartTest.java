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
    @Test
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
    @Test
    public void cartIsNotEmptyAfterAddProduct() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addFirstItemToCart();
        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.isCartEmpty());
    }

    //проверка работы кнопки вернуться на станицу товаров
    @Test
    public void checkButtonContinueShopping() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    //проверка кнопки чекаут
    @Test
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
    @Test
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
    @Test
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