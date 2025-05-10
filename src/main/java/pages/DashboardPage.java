package pages;

import pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {

    private By pimMenu = By.xpath("//span[text() = 'PIM']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public PIMPage goToPIMModule() {
        WebElement element = findElement(pimMenu);
        waitForElementToBeClickable(element);
        element.click();
        return new PIMPage(driver);
    }
}

