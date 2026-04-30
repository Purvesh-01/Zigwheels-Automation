package tests;

import base.BaseTest;
import com.zigwheels.pages.HomePage;
import com.zigwheels.pages.UpcomingBikesPage;
import com.zigwheels.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HondaBikesTest extends BaseTest {

    UpcomingBikesPage bikesPage;

    // Runs ONCE — navigate and apply filter
    // alwaysRun = true ensures this runs after parent @BeforeClass
    @BeforeClass(alwaysRun = true, dependsOnMethods = "setUp")
    public void navigateAndFilter() {

        // Navigate to upcoming bikes
        HomePage homePage = new HomePage(driver);
        homePage.goToUpcomingBikes();
        System.out.println("[INFO] On Upcoming Bikes page");

        // Apply Honda filter
        bikesPage = new UpcomingBikesPage(driver);
        bikesPage.applyHondaFilter();
        System.out.println("[INFO] Honda filter applied");
    }

    // TC01 — Verify upcoming bikes page opened
    @Test(priority = 1)
    public void tc01_verifyUpcomingBikesPageOpens() {
        ExtentReportManager.createTest("TC01 - Verify Upcoming Bikes Page Opens");

        String url = driver.getCurrentUrl();
        ExtentReportManager.test.info("Current URL: " + url);

        Assert.assertTrue(url.contains("upcoming"),
                "Page did not navigate to upcoming bikes!");

        ExtentReportManager.test.pass("Upcoming Bikes page verified ✅");
    }

    // TC02 — Verify Honda filter is applied
    @Test(priority = 2)
    public void tc02_verifyHondaFilterApplied() {
        ExtentReportManager.createTest("TC02 - Verify Honda Filter Applied");

        String url = driver.getCurrentUrl();
        ExtentReportManager.test.info("URL after Honda filter: " + url);

        Assert.assertTrue(url.contains("honda"),
                "Honda filter was not applied!");

        ExtentReportManager.test.pass("Honda filter verified ✅");
    }

    // TC03 — Verify bikes under 4 Lac are displayed
    @Test(priority = 3)
    public void tc03_verifyBikesUnder4LacDisplayed() {
        ExtentReportManager.createTest("TC03 - Verify Bikes Under 4 Lac");

        int count = bikesPage.displayBikesUnder4Lac();
        ExtentReportManager.test.info("Bikes found under 4 Lac: " + count);

        Assert.assertTrue(count > 0,
                "No Honda bikes found under 4 Lac!");

        ExtentReportManager.test.pass(count + " Honda bike(s) under 4 Lac found ✅");
    }
}