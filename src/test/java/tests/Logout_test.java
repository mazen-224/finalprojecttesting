package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Logout_test {

    WebDriver driver;
    WebDriverWait wait;


    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginButton = By.tagName("button");
    By errorMessage = By.cssSelector("div.oxd-alert-content--error p.oxd-alert-content-text");
    By dashboardHeader = By.tagName("h6");

    @BeforeClass
    public void setup() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void navigateToLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testValidLogout(){

        //Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("Admin");
        driver.findElement(passwordField).sendKeys("admin123");
        driver.findElement(loginButton).click();

        String actualResult = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).getText();
        Assert.assertEquals(actualResult, "Dashboard", "Login failed or dashboard not loaded.");
        //Logout
        driver.findElement(By.className("oxd-userdropdown-tab")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Logout"))).click();
    }

    @Test(testName = "Access After Logout", priority = 4)
    public void testAccessAfterLogout() {
        // Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("Admin");
        driver.findElement(passwordField).sendKeys("admin123");
        driver.findElement(loginButton).click();

        // Logout
        driver.findElement(By.className("oxd-userdropdown-tab")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Logout"))).click();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");

        boolean loginFieldVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).isDisplayed();

        Assert.assertTrue(loginFieldVisible, "User was not redirected to login page after logout.");
    }

// needs to use navigate()
@Test(testName = "Test Logout And Back Button", priority = 3)
public void testLogoutAndBack() {
    // Login
    wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("Admin");
    driver.findElement(passwordField).sendKeys("admin123");
    driver.findElement(loginButton).click();

    driver.findElement(By.className("oxd-userdropdown-tab")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Logout"))).click();

    driver.navigate().back();

    boolean isUsernameFieldVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).isDisplayed();
    Assert.assertTrue(isUsernameFieldVisible, "User was able to return to a logged-in state after logout.");
}
}
