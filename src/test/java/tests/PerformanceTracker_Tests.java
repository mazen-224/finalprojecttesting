package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PerformanceTrackerPage;

public class PerformanceTracker_Tests extends Base_Test {

    private final String TRACKER_NAME = "Annual Review 2023";
    private final String UPDATED_NAME = "Annual Review 2023 - Updated";
    private final String EMPLOYEE_NAME = "Odis Adalwin";
    private final String REVIEWER_NAME = "Cassidy Hope";
    private final String DUE_DATE = "2023-12-31";

    @Test(priority = 1)
    public void testAddNewTracker() {
        PerformanceTrackerPage trackerPage = new PerformanceTrackerPage(driver);
        trackerPage.navigateToTrackers();

        trackerPage.addNewTracker(TRACKER_NAME, EMPLOYEE_NAME, REVIEWER_NAME, DUE_DATE);

        Assert.assertTrue(trackerPage.isSuccessMessageDisplayed(),
                "Tracker was not added successfully");
        Assert.assertTrue(trackerPage.isTrackerInList(TRACKER_NAME),
                "Tracker not found in the list");
    }

    @Test(priority = 2, dependsOnMethods = "testAddNewTracker")
    public void testEditExistingTracker() {
        PerformanceTrackerPage trackerPage = new PerformanceTrackerPage(driver);
        trackerPage.navigateToTrackers();

        trackerPage.editTracker(TRACKER_NAME, UPDATED_NAME);

        Assert.assertTrue(trackerPage.isSuccessMessageDisplayed(),
                "Tracker was not updated successfully");
        Assert.assertTrue(trackerPage.isTrackerInList(UPDATED_NAME),
                "Updated tracker not found in the list");
    }

    @Test(priority = 3, dependsOnMethods = "testEditExistingTracker")
    public void testDeleteTracker() {
        PerformanceTrackerPage trackerPage = new PerformanceTrackerPage(driver);
        trackerPage.navigateToTrackers();

        trackerPage.deleteTracker(UPDATED_NAME);

        Assert.assertTrue(trackerPage.isSuccessMessageDisplayed(),
                "Tracker was not deleted successfully");
        Assert.assertFalse(trackerPage.isTrackerInList(UPDATED_NAME),
                "Tracker still exists in the list");
    }

    @Test(priority = 4)
    public void testRequiredFieldValidation() {
        PerformanceTrackerPage trackerPage = new PerformanceTrackerPage(driver);
        trackerPage.navigateToTrackers();
        trackerPage.addNewTracker("", "", "", "");

        Assert.assertEquals(trackerPage.getRequiredErrorMessage(), "Required",
                "Required field validation failed");
}

}