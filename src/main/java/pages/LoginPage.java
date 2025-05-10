package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final By usernameField = By.name("username");
    private static final By passwordField = By.name("password");
    private static final By loginButton = By.tagName("button");
    private final By errorMessage = By.cssSelector("div.oxd-alert-content--error p.oxd-alert-content-text");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static void setUsername(String username) {
        set(usernameField, username);
    }

    public static void setPassword(String password) {
        set(passwordField, password);
    }

    public static void clickLoginButton() {
        click(loginButton);
    }

    public static DashboardPage login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
        return null;
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }
}
