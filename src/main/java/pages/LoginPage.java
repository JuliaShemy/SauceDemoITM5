package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
@Log4j2
public class LoginPage extends BasePage {

    /*
        1. Описываем в классе элементы с которыми мы взаимодествуем
        2. Описываем методы взаимодействия с этими элементами
         */
    private  final By USERNAME_FIELD = By.id("user-name");
    private  final By PASSWORD_FIELD = By.id("password");
    private  final By LOGIN_BUTTON = By.name("login-button");
    private  final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public LoginPage(WebDriver driver) {
        super(driver);
        log.info("LoginPage initialized");
    }
@Override
    @Step("Открытие страницы Login")
    public LoginPage open()
{       log.info("Opening Login page");
        driver.get(BASE_URL);
        return this;
    }
     public LoginPage isPageOpened(){
        log.info("Checking Login page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Вход в магазин с именем пользователя: '{user}' и паролем: '{password}'")
    public ProductsPage login(String user, String password){
        log.info("Logging in with user '{}'", user);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }
    @Step("Полученгие сообщения об ошибке")
    public String getErrorMessage() {
        String errorMessage = driver.findElement(ERROR_MESSAGE).getText();
        log.info("Error message received: '{}'", errorMessage);
        return errorMessage;
    }
}