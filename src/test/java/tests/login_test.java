package tests;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.login_page;
import pages.web_class;

import org.openqa.selenium.By;



public class login_test {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = web_class.createDriver("chrome");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }

    @Test
    public void testValidLogin() {
        login_page loginPage = new login_page(driver);
        login_page.login("Admin", "admin123");

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();

        }
    }
}