package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[3]/a")
    private WebElement leaveMenu;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[3]/span")
    private WebElement entitlementsMenu;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[3]/ul/li[1]/a")
    private WebElement addEntitlementsOption;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div/div/div[2]/div/div/input")
    private WebElement employeeNameInput;

    @FindBy(xpath = "//div[@role='listbox']//div[contains(@class,'oxd-autocomplete-option')][1]")
    private WebElement employeeNameSuggestion;

    @FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[1]")
    private WebElement leaveTypeDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span[contains(.,'US - Vacation')]")
    private WebElement leaveTypeOption;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[3]/div/div[2]/input")
    private WebElement entitlementInput;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/button[2]")
    private WebElement saveButton;

    @FindBy(css = "//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]")
    private WebElement successToast;


    public void navigateToLeavePage() {
        try {
            // Click on the Leave main menu
            wait.until(ExpectedConditions.elementToBeClickable(leaveMenu)).click();

            // Click on the Entitlements dropdown (not hover)
            wait.until(ExpectedConditions.elementToBeClickable(entitlementsMenu)).click();

            // Wait for the "Add Entitlements" option to appear, then click it
            wait.until(ExpectedConditions.visibilityOf(addEntitlementsOption));
            wait.until(ExpectedConditions.elementToBeClickable(addEntitlementsOption)).click();

            // Wait for the form to load
            wait.until(ExpectedConditions.visibilityOf(employeeNameInput));
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to Add Entitlements: " + e.getMessage());
        }
    }



    public void addLeaveEntitlement(String employeeName, String leaveType, String entitlementAmount) {
        // Type the employee name
        employeeNameInput.sendKeys(employeeName);

        // Now press down and enter to select the suggestion
        wait.until(ExpectedConditions.elementToBeClickable(employeeNameInput)).click();
        employeeNameInput.sendKeys(Keys.ARROW_DOWN);
        employeeNameInput.sendKeys(Keys.ENTER);

        // Select leave type
        leaveTypeDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(leaveTypeOption)).click();

        // Enter entitlement amount
        entitlementInput.click();
        entitlementInput.clear();
        entitlementInput.sendKeys(entitlementAmount);

        // Click Save
        saveButton.click();
    }





    public boolean isEntitlementSuccessMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successToast)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
