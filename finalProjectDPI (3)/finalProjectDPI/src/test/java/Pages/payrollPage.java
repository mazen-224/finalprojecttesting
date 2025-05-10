package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class payrollPage {
    // Constants
    public static final String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    public static final String ADMIN_USERNAME = "Admin";
    public static final String ADMIN_PASSWORD = "admin123";

    public WebDriver driver;

    // Enhanced Locators
    public final By usernameFieldLocator = By.name("username");
    public final By passwordFieldLocator = By.name("password");
    public final By loginButtonLocator = By.cssSelector("button[type='submit']");
    public final By pimMenuLocator = By.xpath("//a[@href=\"/web/index.php/pim/viewPimModule\"]");
    public final By editButtonLocator = By.cssSelector("button.oxd-icon-button i.bi-pencil-fill");

    public final By salaryTabLocator = By.xpath("//a[@href=\"/web/index.php/pim/viewSalaryList/empNumber/171\"]");
    public final By addSalaryButtonLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/button");
    public final By saveSalaryButtonLocator = By.cssSelector("button[class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space\"]");

    public final By salaryComponentFieldLocator = By.xpath("//label[text()='Salary Component']/following::input[1]");
    public final By payGradeDropdownLocator = By.xpath("//label[text()='Pay Grade']/following::div[contains(@class,'oxd-select-wrapper')]");
    public final By payFrequencyDropdownLocator = By.xpath("//label[text()='Pay Frequency']/following::div[contains(@class,'oxd-select-wrapper')]");
    public final By currencyDropdownLocator = By.xpath("//label[text()='Currency']/following::div[contains(@class,'oxd-select-wrapper')]");
    public final By amountFieldLocator = By.xpath("//label[text()='Amount']/following::input[1]");
    public final By commentsFieldLocator = By.xpath("//label[text()='Comments']/following::textarea[1]");

    public final By deleteButtonLocator = By.xpath("//button[i[contains(@class, 'bi-trash')]]");
    public final By confirmDeleteLocator = By.xpath("//div[@role='document']//button[normalize-space()='Yes, Delete']");
    public final By deleteToastLocator = By.id("oxd-toaster_1");

    public final By TimeLocator = By.xpath("//a[contains(@href, 'time') and .//span[text()='Time']]");
    public final By searchEmployeelocator = By.xpath("//label[text()='Employee Name']/following::input[1]");
    public final By viewlocator = By.xpath("//form//button[normalize-space()='View']");
    public final By ApproveLocator = By.xpath("//form//button[normalize-space()='Approve']");

    public final By cancleLocator =By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--ghost\"]");
    public final By  confirmCancleLocator = By.cssSelector("oxd-button oxd-button--medium oxd-button--ghost");


    public WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
