package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LocatorsPage {
    // Constants
    public static final String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    public static final String ADMIN_USERNAME = "Admin";
    public static final String ADMIN_PASSWORD = "admin123";

    // WebDriver and Wait
    public WebDriver driver;

    // Locators
    public final By usernameFieldLocator = By.name("username");
    public final By passwordFieldLocator = By.name("password");
    public final By loginButtonLocator = By.cssSelector("button[type='submit']");
    public final By pimMenuLocator = By.xpath("//span[text()='PIM']");
    public final By editButtonLocator = By.cssSelector("button.oxd-icon-button.oxd-table-cell-action-space i.bi-pencil-fill");
    public final By salaryTabLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[7]/a");
    public final By addSalaryButtonLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/button");
    public final By saveSalaryButtonLocator = By.cssSelector("button.oxd-button.oxd-button--secondary.orangehrm-left-space");
    public final By salaryComponentFieldLocator = By.xpath("//label[text()='Salary Component']/parent::div/following-sibling::div//input");
    public final By payGradeDropdownLocator = By.xpath("//label[text()='Pay Grade']/following::div[@class='oxd-select-wrapper'][1]");
    public final By payFrequencyDropdownLocator = By.xpath("//label[text()='Pay Frequency']/following::div[@class='oxd-select-wrapper'][1]");
    public final By currencyDropdownLocator = By.xpath("//label[text()='Currency']/following::div[@class='oxd-select-wrapper'][1]");
    public final By amountFieldLocator = By.xpath("//label[text()='Amount']/following::input[1]");
    public final By commentsFieldLocator = By.xpath("//label[text()='Comments']/following::textarea[1]");
    public final By deleteButtonLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[3]/div/div[2]/div/div/div[7]/div/button[1]");
    public final By confirmDeleteLocator = By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]");
    public final By deleteToastLocator = By.id("oxd-toaster_1");
    public final By TimeLocator = By.xpath( "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[4]/a");
    public final By searchEmployeelocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/form/div[1]/div/div/div/div[2]/div/div/input");
    public final By viewlocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/form/div[2]/button");
    public final By ApproveLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/form/div[2]/button[2]\n");

    public final By JobLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[6]/a");
    public final By JoinedDateLocator = By.xpath(" //*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div[1]/div/div[2]/div/div/input");

    public final By TitleLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i");
    public final By JobCategoryLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div[4]/div/div[2]/div/div/div[2]/i");

    public final By SubUnitLocator= By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div[5]/div/div[2]/div/div/div[2]/i");

    public final By LocationLocator= By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div[6]/div/div[2]/div/div/div[2]/i");

    public final By EmploymentStatus = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div[7]/div/div[2]/div/div/div[2]/i");
    public final By saveJobLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/button");

    public final By successfulLocator = By.xpath( "//*[@id=\"oxd-toaster_1\"]");


    public WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
