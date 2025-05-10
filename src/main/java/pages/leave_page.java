package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class leave_page {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public leave_page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    private final By leaveMenu = By.xpath("//a[.//span[text()='Leave']]");
    private final By entitlementsMenu = By.xpath("//span[contains(text(),'Entitle')]");
    private final By addEntitlementsOption = By.xpath("//a[text()='Add Entitlements']");

    private final By employeeNameInput = By.cssSelector("input[placeholder*='Type']");
    private final By leaveTypeDropdown = By.cssSelector(".oxd-select-wrapper:first-of-type .oxd-select-text");
    private final By leaveTypeOption = By.xpath("//span[contains(text(),'US - Vacation')]");
    private final By entitlementInput = By.xpath("//label[text()='Entitlement']/following::input[1]");
    private final By saveButton = By.xpath("//button[normalize-space()='Save']");
    private final By confirmButton = By.xpath("//button[normalize-space()='Confirm']");
    private final By Toast = By.cssSelector(".oxd-toast-container");

    private final By multipleEmployeesRadio = By.xpath("(//span[contains(@class, 'oxd-radio-input--active')])[2]");
    private final By locationDropdown = By.xpath("(//form//div[contains(@class,'oxd-select-text')])[1]");
    private final By subUnitDropdown = By.xpath("//label[text()='Sub Unit']/following::div[contains(@class,'oxd-select-text-input')][1]");
    private final By Leave_type_dropdown = By.xpath("//label[text()='Leave Type']/following::div[contains(@class,'oxd-select-text-input')][1]");
    public void navigateToLeavePage() {
        wait.until(ExpectedConditions.elementToBeClickable(leaveMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(entitlementsMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addEntitlementsOption)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameInput));
    }

    public void addLeaveEntitlement(String employeeName, String leaveType, String entitlementAmount) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameInput));
        nameInput.sendKeys(employeeName);

        actions.pause(Duration.ofSeconds(5))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        wait.until(ExpectedConditions.elementToBeClickable(leaveTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(leaveTypeOption)).click();

        WebElement entitlementField = wait.until(ExpectedConditions.visibilityOfElementLocated(entitlementInput));
        entitlementField.clear();
        entitlementField.sendKeys(entitlementAmount);

        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Toast));
    }

    public void addLeaveEntitlementForMultipleEmployees(String location, String subUnit, String leaveType, String entitlementAmount) {
        wait.until(ExpectedConditions.elementToBeClickable(multipleEmployeesRadio)).click();

        wait.until(ExpectedConditions.elementToBeClickable(locationDropdown)).click();
        actions.pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        wait.until(ExpectedConditions.elementToBeClickable(subUnitDropdown)).click();
        actions.pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        wait.until(ExpectedConditions.elementToBeClickable(Leave_type_dropdown)).click();
        actions.pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        WebElement entitlementField = wait.until(ExpectedConditions.visibilityOfElementLocated(entitlementInput));
        entitlementField.clear();
        entitlementField.sendKeys(entitlementAmount);

        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Toast));
    }

    public boolean isEntitlementSuccessMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(Toast)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
