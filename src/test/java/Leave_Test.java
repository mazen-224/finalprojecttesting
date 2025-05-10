import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LeavePage;
import pages.LoginPage;

import java.time.Duration;

public class Leave_Test {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeClass
    public void setup() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
    }

    @Test
    public void testAddLeaveEntitlement() {
        LeavePage leavePage = new LeavePage(driver);

        leavePage.navigateToLeavePage();

        String employeeName = "mazen 12345";
        String leaveType = "US - Vacation";
        String entitlement = "800";

        leavePage.addLeaveEntitlement(employeeName, leaveType, entitlement);

        Assert.assertTrue(leavePage.isEntitlementSuccessMessageDisplayed(),
                "Entitlement success message was not displayed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
