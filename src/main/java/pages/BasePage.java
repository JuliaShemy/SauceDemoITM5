package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    WebDriver driver;
    public final String BASE_URL = "https://www.saucedemo.com/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract BasePage open();

    public abstract BasePage isPageOpened();
}
