package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class login_page {

    private WebDriver driver;

    @FindBy(name = "username")
    static WebElement usernameInput;

    @FindBy(name = "password")
    static WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    static WebElement loginButton;

    public login_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        // Optional: wait for page to load before interacting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
    }

    public static void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void navigateTologin_page(String url) {
    }
}