package tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.web_class;
import pages.LoginPage;


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
        LoginPage loginPage = new LoginPage(driver);
        LoginPage.login("Admin", "admin123");

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}