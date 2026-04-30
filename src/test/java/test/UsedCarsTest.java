package test;
import base.BaseTest;
import com.zigwheels.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UsedCarsTest extends BaseTest {

    @Test
    public void VerifyHomePage(){
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = ConfigReader.getProperty("baseUrl");
        Assert.assertEquals(actualUrl,expectedUrl);
    }

    @Test
    public void visitUsedCarsPage() {
        Actions action = new Actions(driver);
        WebElement more = driver.findElement(By.xpath(ConfigReader.getProperty("moreBtn.xpath")));
        action.moveToElement(more).perform();
        driver.findElement(By.xpath(ConfigReader.getProperty("usedCars.xpath"))).click();
        String usedCarUrl = driver.getCurrentUrl();
        Assert.assertEquals(usedCarUrl,ConfigReader.getProperty("usedCars.url"));
    }
}
