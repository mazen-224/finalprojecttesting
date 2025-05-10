package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class admin_Page {
    private WebDriver driver;
    private WebDriverWait wait;

    public admin_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    // --- Locators (unchanged as requested) ---
    private final By adminMenu = By.xpath("//span[text()='Admin']/parent::a");
    private final By usernameField = By.cssSelector("input.oxd-input[data-v-1f99f73c]:not([placeholder])");
    private final By searchButton = By.xpath("//button[normalize-space()='Search']");
    private final By editButton = By.xpath("//div[@role='row']//i[@class='oxd-icon bi-pencil-fill']");
    private final By statusDropdown = By.xpath("//div[@class='oxd-select-text-input' and text()='Enabled']");
    private final By disabledOption = By.xpath("//span[text()='Disabled']");
    private final By saveButton = By.xpath("//button[normalize-space()='Save']");

    // --- Password Change Section ---
    private final By changePasswordYesRadio = By.cssSelector("i.oxd-icon.bi-check[data-v-bddebfba]");
    private final By passwordInput = By.xpath("//label[text()='Password']/following::input[1]");
    private final By confirmPasswordInput = By.xpath("//label[text()='Confirm Password']/following::input[1]");

    // --- Actions ---

    public void openAdminMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
    }

    public void searchUser(String username) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        userField.clear();
        userField.sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void clickEditUser() {
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
    }

    public void disableUserStatus() {
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(disabledOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void changeUserPassword(String newPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordYesRadio)).click();
        WebElement pwdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        WebElement confirmPwdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordInput));

        pwdInput.clear();
        pwdInput.sendKeys(newPassword);
        confirmPwdInput.clear();
        confirmPwdInput.sendKeys(newPassword);

        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    // Full flows

    public void disableUser(String username) {
        openAdminMenu();
        searchUser(username);
        clickEditUser();
        disableUserStatus();
    }

    public void changeUserPasswordFlow(String username, String newPassword) {
        openAdminMenu();
        searchUser(username);
        clickEditUser();
        changeUserPassword(newPassword);
    }
}
