package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.leave_page;
import pages.web_class;

public class leave_test {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = web_class.createDriver("chrome");
        driver.get("https://opensource-demo.orangehrmlive.com");
        new LoginPage(driver).login("Admin", "admin123");
    }

    @Test
    public void testAddLeaveEntitlement() {
        leave_page leavePage = new leave_page(driver);
        leavePage.navigateToLeavePage();

        String employeeName = "mazen 12345";
        String leaveType = "US - Vacation";
        String entitlement = "2000";

        leavePage.addLeaveEntitlement(employeeName, leaveType, entitlement);

        Assert.assertTrue(leavePage.isEntitlementSuccessMessageDisplayed());
    }
    @Test
    public void testAddEntitlementForMultipleEmployees() {
        leave_page leavePage = new leave_page(driver);

        leavePage.navigateToLeavePage();

        String location = "New York Sales Office";
        String subUnit = "Administration";
        String leaveType = "US - Vacation";
        String entitlement = "800";

        leavePage.addLeaveEntitlementForMultipleEmployees(location, subUnit, leaveType, entitlement);

        Assert.assertTrue(leavePage.isEntitlementSuccessMessageDisplayed(),
                "Entitlement success message not displayed for multiple employees");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
