package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.admin_Page;

import java.time.Duration;

public class admin_test {
    private WebDriver driver;
    private admin_Page adminPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // Admin login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        adminPage = new admin_Page(driver);
    }

    @Test(priority = 1)
    public void testDisableUser() {
        adminPage.disableUser("Mazen");

        driver.get("https://opensource-demo.orangehrmlive.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Mazen", "mazen12345");

        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "User should not be able to login");
    }

    @Test(priority = 2)
    public void testChangeUserPasswordAndLogin() {
        // Admin changes user password
        adminPage.changeUserPasswordFlow("FMLName", "FML12345");

        driver.get("https://opensource-demo.orangehrmlive.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("FMLName", "FML12345");

        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "User should be logged in with new password");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
