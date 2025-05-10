package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeavePage extends BasePage {

    private final By leaveMenu = By.xpath("//aside//span[text()='Leave']/ancestor::a");
    private final By entitlementsMenu = By.xpath("//nav//ul//li[.//span[normalize-space()='Entitlements']]");
    private final By addEntitlementsOption = By.xpath("//nav//a[normalize-space()='Add Entitlements']");
    private final By employeeNameInput = By.cssSelector("input[placeholder='Type for hints...']");
    private final By employeeNameSuggestion = By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-autocomplete-option')]");
    private final By leaveTypeDropdown = By.cssSelector(".oxd-select-wrapper");
    private final By leaveTypeOption = By.xpath("//div[@role='listbox']//span[text()='US - Vacation']");
    private final By entitlementInput = By.xpath("//label[contains(text(),'Entitlement')]/following::input[1]");
    private final By saveButton = By.xpath("//button[contains(.,'Save')]");
    private final By successToast = By.xpath("//*[@id='app']/div[3]/div/div/div/div[3]/button[2]");


    public LeavePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLeavePage() {
        click(leaveMenu);
        click(entitlementsMenu);
        click(addEntitlementsOption);
    }

    public void addLeaveEntitlement(String employeeName, String leaveType, String entitlement) {
        set(employeeNameInput, employeeName);


        if (isDisplayed(employeeNameSuggestion)) {
            click(employeeNameSuggestion);
        }


        click(leaveTypeDropdown);
        click(leaveTypeOption);

        set(entitlementInput, entitlement);
        click(saveButton);
    }

    public boolean isEntitlementSuccessMessageDisplayed() {
        return isDisplayed(successToast);
    }
}
