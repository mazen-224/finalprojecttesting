

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class web_class {
    public static WebDriver createDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            return new ChromeDriver();
        }
        // You can extend this to support Firefox, Edge, etc.
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
}