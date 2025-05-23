package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.EMP_leave_page;
import pages.LoginPage;

import java.time.Duration;

public class EMP_leave_test {
    private WebDriver driver;
    private EMP_leave_page empLeavePage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("eldyaste", "mazen12345");

        empLeavePage = new EMP_leave_page(driver);
    }

    @Test
    public void testApplyLeaveAsEmployee() {
        empLeavePage.navigateToApplyLeavePage();

        empLeavePage.applyForLeave("US - Vacation", "2024-05-10", "2024-05-12");

        Assert.assertTrue(empLeavePage.isLeaveApplicationSuccessful(), "Leave application failed or toast not displayed");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {

        }
    }
}
