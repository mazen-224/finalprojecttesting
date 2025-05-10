package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class PerformanceTrackerPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By performanceMenu = By.xpath("//span[text()='Performance']");
    private final By trackersMenu = By.xpath("//a[text()='Employee Trackers']");
    private final By addBtn = By.xpath("//button[text()=' Add ']");
    private final By trackerNameField = By.xpath("//label[text()='Tracker Name']/following::input[1]");
    private final By employeeField = By.xpath("//label[text()='Employee Name']/following::input[1]");
    private final By reviewerField = By.xpath("//label[text()='Reviewers']/following::input[1]");
    private final By dueDateField = By.cssSelector("input[placeholder='yyyy-mm-dd']");
    private final By saveBtn = By.xpath("//button[text()=' Save ']");
    private final By successMessage = By.cssSelector(".oxd-toast--success");
    private final By requiredError = By.cssSelector(".oxd-input-field-error-message");
    private final By trackerList = By.cssSelector(".oxd-table-card");
    private final By editBtn = By.xpath("//button[contains(@class,'oxd-table-cell-action-space')][1]");
    private final By deleteBtn = By.xpath("//button[contains(@class,'oxd-table-cell-action-space')][2]");
    private final By confirmDelete = By.xpath("//button[text()=' Yes, Delete ']");

    public PerformanceTrackerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToTrackers() {
        wait.until(ExpectedConditions.elementToBeClickable(performanceMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(trackersMenu)).click();
    }

    public void addNewTracker(String trackerName, String employeeName, String reviewerName, String dueDate) {
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
        setTrackerName(trackerName);
        selectEmployee(employeeName);
        addReviewer(reviewerName);
        setDueDate(dueDate);
        saveTracker();
    }

    public void setTrackerName(String name) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(trackerNameField));
        field.clear();
        field.sendKeys(name);
    }

    public void selectEmployee(String employeeName) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeField));
        field.sendKeys(employeeName);
        selectOptionFromDropdown(employeeName);
    }

    public void addReviewer(String reviewerName) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(reviewerField));
        field.sendKeys(reviewerName);
        selectOptionFromDropdown(reviewerName);
    }

    private void selectOptionFromDropdown(String optionText) {
        By optionLocator = By.xpath(String.format("//div[@role='option']/span[contains(.,'%s')]", optionText));
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    public void setDueDate(String date) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(dueDateField));
        field.clear();
        field.sendKeys(date);
    }

    public void saveTracker() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getRequiredErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredError)).getText();
    }

    public boolean isTrackerInList(String trackerName) {
        try {
            return driver.findElements(trackerList).stream()
                    .anyMatch(element -> element.getText().contains(trackerName));
        } catch (Exception e) {
            return false;
        }
    }

    public void editTracker(String oldName, String newName) {
        clickTrackerActionButton(oldName, editBtn);
        setTrackerName(newName);
        saveTracker();
    }

    public void deleteTracker(String trackerName) {
        clickTrackerActionButton(trackerName, deleteBtn);
        wait.until(ExpectedConditions.elementToBeClickable(confirmDelete)).click();
    }

    private void clickTrackerActionButton(String trackerName, By buttonLocator) {
        By trackerRow = By.xpath(String.format("//div[contains(text(),'%s')]/ancestor::div[contains(@class,'oxd-table-card')]", trackerName));
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(trackerRow));
        row.findElement(buttonLocator).click();
    }
}