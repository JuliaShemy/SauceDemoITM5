package tests;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.AllureUtils;

import static org.testng.Assert.assertEquals;
import static utils.AllureUtils.takeScreenshot;

public class LoginTest extends BaseTest {
    @Test(description = "Проверка входа в систему с позитивными кредами",
            testName = "Проверка входа в систему с позитивными кредами",
            groups = {"smoke"})
    @Description("Проверка входа в систему с позитивными кредами")
    @Epic("E2E")
    @Feature("Login in to SauceDemo")
    @Story("Positive login")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Flaky
    @Owner("Julia Shem")
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
    @Description("Проверка входа в систему с негативными кредами")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}