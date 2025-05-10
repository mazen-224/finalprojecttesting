package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PIMPage extends BasePage {

    private By addEmployeeButton = By.cssSelector("[class=\"oxd-button oxd-button--medium oxd-button--secondary\"]");
    private By firstNameField = By.cssSelector("[name=\"firstName\"]");
    private By lastNameField = By.cssSelector("[name=\"lastName\"]");
    private By saveButton = By.xpath("//button[text() = ' Save ']");
    private By employeeIdField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input");
    private By nameEmployeeInSearch = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input");
    private By searchButton = By.xpath("//button[text() = ' Search ']");
    private By createLoginDetailsCheckbox = By.cssSelector("[class=\"oxd-switch-input oxd-switch-input--active --label-right\"]");
    private By usernameField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input");
    private By passwordField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input");
    private By confirmPasswordField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input");
    private By userProfileDropdown = By.cssSelector(".oxd-userdropdown-name");
    private By logoutOption = By.xpath("//a[text()='Logout']");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddEmployee() {
        WebElement element = findElement(addEmployeeButton);
        waitForElementToBeClickable(element);
        element.click();
    }

    public void enterEmployeeDetails(String firstName, String lastName) {
        WebElement firstNameElement = findElement(firstNameField);
        WebElement lastNameElement = findElement(lastNameField);
        waitForElementToBeVisible(firstNameElement);
        firstNameElement.sendKeys(firstName);
        lastNameElement.sendKeys(lastName);
    }

    public String getEmployeeId() {
        WebElement element = findElement(employeeIdField);
        waitForElementToBeVisible(element);
        return element.getAttribute("value");
    }

    public void setEmployeeId(String employeeId) {
        WebElement element = findElement(employeeIdField);
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(employeeId);
    }

    public void clickSave() {
        WebElement element = findElement(saveButton);
        waitForElementToBeClickable(element);
        element.click();
    }

    public void searchEmployeeByName(String employeeName) {
        WebElement searchElement = findElement(nameEmployeeInSearch);
        waitForElementToBeVisible(searchElement);
        searchElement.clear();
        searchElement.sendKeys(employeeName);
        WebElement searchBtn = findElement(searchButton);
        waitForElementToBeClickable(searchBtn);
        searchBtn.click();
    }


    public boolean isEmployeeFound(String employeeName) {
        try {
            String byXpath_searchResult = "//div[contains(text()," + " '" + employeeName + "')]";
            By searchResult = By.xpath(byXpath_searchResult);
            WebElement element = findElement(searchResult);
            waitForElementToBeVisible(element);
            // return element.getText().contains(employeeName);
            return element.getText().trim().equals(employeeName);
        } catch (Exception e) {
            return false;
        }
    }

    public void enableCreateLoginDetails() {
        WebElement checkbox = findElement(createLoginDetailsCheckbox);
        waitForElementToBeClickable(checkbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void enterLoginDetails(String username, String password, String confirmPassword) {
        WebElement usernameElement = findElement(usernameField);
        WebElement passwordElement = findElement(passwordField);
        WebElement confirmPasswordElement = findElement(confirmPasswordField);
        waitForElementToBeVisible(usernameElement);
        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        confirmPasswordElement.sendKeys(confirmPassword);
    }

    public void performLogout() {
        WebElement dropdown = findElement(userProfileDropdown);
        waitForElementToBeClickable(dropdown);
        dropdown.click();
        WebElement logout = findElement(logoutOption);
        waitForElementToBeClickable(logout);
        logout.click();
    }
}
