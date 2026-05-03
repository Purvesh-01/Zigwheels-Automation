package base;

import com.zigwheels.base.BasePage;
import com.zigwheels.utils.ConfigReader;
import com.zigwheels.utils.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected BasePage basePage;
    // Runs ONCE before all tests — set up report
    @BeforeSuite
    public void setUpReport() {
        ExtentReportManager.setUp();
    }

    // Runs before EACH test — open browser
    @BeforeClass
    public void setUp() {
        // Block notification popup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        basePage = new BasePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    // Runs after EACH test — close browser
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Runs ONCE after all tests — save report
    @AfterSuite
    public void saveReport() {
        ExtentReportManager.flush();
    }
}