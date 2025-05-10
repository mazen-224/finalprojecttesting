package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import org.testng.asserts.SoftAssert;


public class PIM_Test {
    private WebDriver browser;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(browser);
        dashboardPage = new DashboardPage(browser);
        pimPage = new PIMPage(browser);
        dashboardPage = loginPage.login("Admin", "admin123");
        softAssert = new SoftAssert();
    }

    @Test(testName = "Add Employee with Valid Data", priority = 1)
    public void testAddEmployee() throws InterruptedException {
        // navigate to PIM Module
        pimPage = dashboardPage.goToPIMModule();

        // Add new Employee
        pimPage.clickAddEmployee();
        pimPage.enterEmployeeDetails("Ahmed", "Amr");
        pimPage.clickSave();
        pimPage.waitForSeconds(2);


        pimPage = dashboardPage.goToPIMModule();

        // Search about Employee
        pimPage.searchEmployeeByName("Ahmed");
        boolean isEmployeeFound = pimPage.isEmployeeFound("Ahmed");

        // Verify that the employee has been added successfully
        Assert.assertTrue(isEmployeeFound, "Fail !");
    }

    @Test(testName = "Add Employee with InValid Data", priority = 2)
    public void testAddInvalidEmployee() throws InterruptedException {
        // navigate to PIM Module
        pimPage = dashboardPage.goToPIMModule();

        // Add new Employee
        pimPage.clickAddEmployee();
        pimPage.enterEmployeeDetails("!@#$%%^&^", "1234567");
        pimPage.clickSave();
        pimPage.waitForSeconds(2);


        pimPage = dashboardPage.goToPIMModule();

        // Search about Employee
        pimPage.searchEmployeeByName("!@#$%%^&^");
        boolean isEmployeeFound = pimPage.isEmployeeFound("!@#$%%^&^");

        // Verify that the employee hasn't been added
        softAssert.assertFalse(isEmployeeFound, "Fail ! Employee with invalid data was added!");
        softAssert.assertAll();
    }

    @Test(testName = "Add Employee without Mandatory Fields", priority = 3)
    public void testAddEmployeeWithoutMandatoryFields() {
        // navigate to PIM Module
        pimPage = dashboardPage.goToPIMModule();

        // Click Add Employee
        pimPage.clickAddEmployee();

        // Leave First Name and Last Name blank, enter Employee ID
        pimPage.enterEmployeeDetails("", ""); // Leave blank

        // Click Save
        pimPage.clickSave();

        // Verify error messages for mandatory fields
        WebElement firstNameError = pimPage.findElement(By.xpath("//span[text() = 'Required']"));
        WebElement lastNameError = pimPage.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/span"));
        softAssert.assertTrue(firstNameError.isDisplayed(), "First Name Required error message not displayed!");
        softAssert.assertTrue(lastNameError.isDisplayed(), "Last Name Required error message not displayed!");
    }

    @Test(testName = "Add Employee with Valid Login Details", priority = 4)
    public void testAddEmployeeWithValidLoginDetails() throws InterruptedException {
        // Navigate to PIM Module
        pimPage = dashboardPage.goToPIMModule();

        // Add Employee
        pimPage.clickAddEmployee();
        pimPage.enterEmployeeDetails("omar", "ali");
        pimPage.setEmployeeId("15555");

        // Enable Create Login Details
        pimPage.enableCreateLoginDetails();
        pimPage.enterLoginDetails("omar.ali", "Pass@123", "Pass@123");

        // Save
        pimPage.clickSave();
        pimPage.waitForSeconds(2);

        // Logout
        pimPage.performLogout();

        // Login with new employee credentials
        browser.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(browser);
        dashboardPage = loginPage.login("omar.ali", "Pass@123");
        pimPage.waitForSeconds(2);
        softAssert.assertNotNull(dashboardPage, "Employee failed to log in with new credentials!");

        softAssert.assertAll();
    }


    @AfterClass
    public void teardown() {
        if (browser != null) {
            browser.quit();
        }
    }
}
