package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class payrollTest extends payrollPage {

    // Setup
    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        getWait().until(ExpectedConditions.visibilityOfElementLocated(usernameFieldLocator));
    }

    public void loginAsAdmin() {
        driver.findElement(usernameFieldLocator).sendKeys(ADMIN_USERNAME);
        driver.findElement(passwordFieldLocator).sendKeys(ADMIN_PASSWORD);
        driver.findElement(loginButtonLocator).click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(pimMenuLocator));
    }


    // Test Cases
@Test(testName ="valid salary ", priority = 1 )
    public void saveSalary(){
        WebDriverWait wait = getWait();
        loginAsAdmin();
        getWait().until(ExpectedConditions.elementToBeClickable(pimMenuLocator)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(editButtonLocator)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(salaryTabLocator)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(addSalaryButtonLocator)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(salaryComponentFieldLocator)).sendKeys("Basic Salary");

        driver.findElement(payGradeDropdownLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']/span[text()='Grade 1']"))).click();

        driver.findElement(payFrequencyDropdownLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']/span[text()='Monthly']"))).click();

        driver.findElement(currencyDropdownLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']/span[contains(text(),'United States Dollar')]"))).click();
        driver.findElement(amountFieldLocator).sendKeys("50000");
        driver.findElement(commentsFieldLocator).sendKeys("No comment");
        wait.until(ExpectedConditions.elementToBeClickable(saveSalaryButtonLocator)).click();

        By basicSalaryDataLocator = By.xpath("//div[contains(text(), 'Basic Salary')]");
        WebElement salaryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(basicSalaryDataLocator));
        String displayedText = salaryElement.getText();
        Assert.assertEquals(displayedText, "Basic Salary", "The salary component displayed is incorrect or missing.");
    }

    @Test(testName = "Submit salary form with missing Pay Grade and Pay Frequency", priority = 2)
    public void missingData() {
        WebDriverWait wait = getWait();
        loginAsAdmin();
        getWait().until(ExpectedConditions.elementToBeClickable(pimMenuLocator)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(editButtonLocator)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(salaryTabLocator)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(addSalaryButtonLocator)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(salaryComponentFieldLocator)).sendKeys("Basic Salary");
        driver.findElement(commentsFieldLocator).sendKeys("No comment");
        wait.until(ExpectedConditions.elementToBeClickable(saveSalaryButtonLocator)).click();

        By RequiredLocator = By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]");
        WebElement salaryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(RequiredLocator));
        String displayedText = salaryElement.getText();
        Assert.assertEquals(displayedText, "Required", "The salary component displayed is incorrect or missing.");
    }

    @Test(testName = "Delete a Valid Assigned Salary Component", priority = 3)
    public void Delete() {
        WebDriverWait wait = getWait();
        loginAsAdmin();
        getWait().until(ExpectedConditions.elementToBeClickable(pimMenuLocator)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(editButtonLocator)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(salaryTabLocator)).click();

        // Create salary to delete first
        getWait().until(ExpectedConditions.elementToBeClickable(addSalaryButtonLocator)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(salaryComponentFieldLocator)).sendKeys("Basic Salary");
        driver.findElement(payGradeDropdownLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']/span[text()='Grade 1']"))).click();
        driver.findElement(payFrequencyDropdownLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']/span[text()='Monthly']"))).click();
        driver.findElement(currencyDropdownLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']/span[contains(text(),'United States Dollar')]"))).click();
        driver.findElement(amountFieldLocator).sendKeys("50000");
        driver.findElement(commentsFieldLocator).sendKeys("No comment");
        wait.until(ExpectedConditions.elementToBeClickable(saveSalaryButtonLocator)).click();

        // delete it
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtonLocator)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteLocator)).click();

        WebElement deleteToast = wait.until(ExpectedConditions.visibilityOfElementLocated(deleteToastLocator));
        String displayedText = deleteToast.getText();
        Assert.assertTrue(displayedText.contains("Successfully Deleted") || displayedText.contains("successfully deleted"),
                "The salary delete confirmation message was not as expected.");
    }


    @Test(testName = "Time sheet", priority = 4)
    public void Time() {
        WebDriverWait wait = getWait();
        loginAsAdmin();
        getWait().until(ExpectedConditions.elementToBeClickable(TimeLocator)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(searchEmployeelocator)).sendKeys("manda akhil user");
        getWait().until(ExpectedConditions.elementToBeClickable(viewlocator)).click();
        // Create salary to delete first
        getWait().until(ExpectedConditions.elementToBeClickable(ApproveLocator)).click();


        WebElement salaryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ApproveLocator));
        String displayedText = salaryElement.getText();
        Assert.assertEquals(displayedText, "Approve", "the view button doesnt work.");
    }
    @Test(testName = "cancle a Valid Assigned Salary Component", priority = 5)
    public void cancle() {
        WebDriverWait wait = getWait();
        loginAsAdmin();
        getWait().until(ExpectedConditions.elementToBeClickable(pimMenuLocator)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(editButtonLocator)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(salaryTabLocator)).click();

        // Create salary to delete first
        getWait().until(ExpectedConditions.elementToBeClickable(addSalaryButtonLocator)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(salaryComponentFieldLocator)).sendKeys("Basic Salary");
        driver.findElement(payGradeDropdownLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']/span[text()='Grade 1']"))).click();
        driver.findElement(payFrequencyDropdownLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']/span[text()='Monthly']"))).click();
        driver.findElement(currencyDropdownLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']/span[contains(text(),'United States Dollar')]"))).click();
        driver.findElement(amountFieldLocator).sendKeys("50000");
        driver.findElement(commentsFieldLocator).sendKeys("No comment");
        wait.until(ExpectedConditions.elementToBeClickable(cancleLocator)).click();

        // delete it
        wait.until(ExpectedConditions.elementToBeClickable(cancleLocator)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmCancleLocator)).click();

        WebElement deleteToast = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmCancleLocator));
        String displayedText = deleteToast.getText();
        Assert.assertTrue(displayedText.contains("Successfully cancled") || displayedText.contains("successfully cancled"),
                "The salary cancle confirmation message was not as expected.");
    }


    @AfterClass
    public void finish() {
        if (driver != null) {
            driver.quit();
        }
    }

}
