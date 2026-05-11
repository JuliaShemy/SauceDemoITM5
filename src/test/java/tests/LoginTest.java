package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    @Test(description = "Проверка входа в систему с позитивными кредами",
            testName = "Проверка входа в систему с позитивными кредами",
            groups = {"smoke"})
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @DataProvider(name = "Тестовые данные для негативного логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}

        };
    }

    @Test(dataProvider = "Тестовые данные для негативного логина",
            description = "Проверка негативных сценариев логина",
            testName = "Проверка негативных сценариев логина")
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}