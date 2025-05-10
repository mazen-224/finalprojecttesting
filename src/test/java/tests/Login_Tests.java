package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Login_Tests {

    WebDriver driver;
    WebDriverWait wait;


    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginButton = By.tagName("button");
    By errorMessage = By.cssSelector("div.oxd-alert-content--error p.oxd-alert-content-text");
    By dashboardHeader = By.tagName("h6");
    String URL = "https://opensource-demo.orangehrmlive.com";
    @BeforeClass
    public void setup() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void navigateToLoginPage() {
        driver.get(URL);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDirectDashboardURL() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        String currentUrl = driver.getCurrentUrl();
        String loginUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        Assert.assertEquals(currentUrl, loginUrl, "User was able to access the dashboard without logging in.");
    }

    @Test
    public void testSQLInjection() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("Admin' OR '1'='1");
        driver.findElement(passwordField).sendKeys("558123");
        driver.findElement(loginButton).click();

        String actualErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        Assert.assertEquals(actualErrorMessage, "Invalid credentials", "SQL Injection was not prevented.");
    }

    @Test
    public void testPasswordCannotBeCopied() {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        WebElement passwordInput = driver.findElement(passwordField);
        passwordInput.sendKeys("admin123");

        Actions actions = new Actions(driver);
        actions.click(passwordInput)
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL)
                .perform();

        usernameInput.click();
        usernameInput.clear();
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

        String pastedValue = usernameInput.getAttribute("value");
        Assert.assertNotEquals(pastedValue, "admin123", "Password was copied into username field. Copy protection failed.");
    }

    @Test
    public void testLoginWithInvalidUsername() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("InvalidUser");
        driver.findElement(passwordField).sendKeys("admin123");
        driver.findElement(loginButton).click();

        String actualErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        Assert.assertEquals(actualErrorMessage, "Invalid credentials", "Invalid username login failed.");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("Admin");
        driver.findElement(passwordField).sendKeys("WrongPassword");
        driver.findElement(loginButton).click();

        String actualErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        Assert.assertEquals(actualErrorMessage, "Invalid credentials", "Invalid password login failed.");
    }

    @Test
    public void testPasswordIsMasked() {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        String inputType = passwordInput.getAttribute("type");
        Assert.assertEquals(inputType.trim().toLowerCase(), "password", "Password is not masked.");
    }

    @Test
    public void testCaseSensitivePassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("Admin");
        driver.findElement(passwordField).sendKeys("Admin123");
        driver.findElement(loginButton).click();

        String actualErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        Assert.assertEquals(actualErrorMessage, "Invalid credentials", "Case-sensitive password check failed.");
    }

    @Test( priority = 1)
    public void testLoginWithValidCredentials() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("Admin");
        driver.findElement(passwordField).sendKeys("admin123");
        driver.findElement(loginButton).click();

        String actualResult = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).getText();
        Assert.assertEquals(actualResult, "Dashboard", "Login failed or dashboard not loaded.");

        // Logout
//        driver.findElement(By.className("oxd-userdropdown-tab")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Logout"))).click();
    }



}
