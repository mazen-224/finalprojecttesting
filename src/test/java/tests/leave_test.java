package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.leave_page;
import pages.login_page;
import pages.web_class;

import java.time.Duration;

public class leave_test {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = web_class.createDriver("chrome");
        driver.get("https://opensource-demo.orangehrmlive.com");

        // Ensure successful login
        login_page loginPage = new login_page(driver);
        loginPage.login("Admin", "admin123");

    }

    @Test
    public void testAddLeaveEntitlement() {
        leave_page leavePage = new leave_page(driver);

        // Navigate to entitlements
        leavePage.navigateToLeavePage();

        // Test data
        String employeeName = "mazen 12345";
        String leaveType = "US - Vacation";
        String entitlement = "800";

        // Execute test
        leavePage.addLeaveEntitlement(employeeName, leaveType, entitlement);

        // Verification
        Assert.assertTrue(leavePage.isEntitlementSuccessMessageDisplayed(),
                "Entitlement success message not displayed");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}