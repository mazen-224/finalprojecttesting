package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EMP_leave_page {
    private WebDriver driver;
    private WebDriverWait wait;

    public EMP_leave_page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    private final By leaveMenu = By.xpath("//span[text()='Leave']/parent::a");
    private final By applyMenu = By.xpath("//a[@class='oxd-topbar-body-nav-tab-item' and text()='Apply']");
    private final By leaveTypeDropdown = By.cssSelector(".oxd-select-wrapper:first-of-type .oxd-select-text");
    private final By leaveTypeOption = By.xpath("//span[text()='US - Vacation']");
    private final By dateInputs = By.cssSelector("input.oxd-input[placeholder='yyyy-dd-mm']"); // used for both dates
    private final By applyButton = By.xpath("//button[normalize-space()='Apply']");
    private final By Toast = By.cssSelector(".oxd-toast");

    public void navigateToApplyLeavePage() {
        wait.until(ExpectedConditions.elementToBeClickable(leaveMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(applyMenu)).click();
    }

    public void applyForLeave(String leaveType, String fromDate, String toDate) {
        wait.until(ExpectedConditions.elementToBeClickable(leaveTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + leaveType + "']"))).click();

        // Wait for both date inputs to be visible
        List<WebElement> dateFields = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dateInputs));
        WebElement fromDateField = dateFields.get(0);
        WebElement toDateField = dateFields.get(1);

        // Clear and type dates using Actions to avoid focus issues
        Actions actions = new Actions(driver);

        fromDateField.click();
        fromDateField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        actions.sendKeys(fromDateField, fromDate).perform();

        toDateField.click();
        toDateField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        actions.sendKeys(toDateField, toDate).perform();

        wait.until(ExpectedConditions.elementToBeClickable(applyButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Toast));
    }

    public boolean isLeaveApplicationSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(Toast)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
