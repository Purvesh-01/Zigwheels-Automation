package test;

import base.BaseTest;
import com.zigwheels.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UsedCarsTest extends BaseTest {

    WebDriverWait wait;

    @Test
    public void VerifyHomePage() {
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = ConfigReader.getProperty("baseUrl");
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test(dependsOnMethods = "VerifyHomePage")
    public void visitUsedCarsPage() {
        Actions action = new Actions(driver);
        WebElement more = driver.findElement(By.xpath(ConfigReader.getProperty("moreBtn.xpath")));
        action.moveToElement(more).perform();
        driver.findElement(By.xpath(ConfigReader.getProperty("usedCars.xpath"))).click();
        String usedCarUrl = driver.getCurrentUrl();
        Assert.assertEquals(usedCarUrl, ConfigReader.getProperty("usedCars.url"));
    }

    @Test(dependsOnMethods = "visitUsedCarsPage")
    public void visitUsedCarsChennai() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for city popup modal to appear
        WebElement cityPopup = driver.findElement(By.id("city-popup"));
        wait.until(ExpectedConditions.visibilityOf(cityPopup));

        WebElement chennaiLink = driver.findElement(
                By.xpath(ConfigReader.getProperty("chennai.usedCars.xpath"))
        );

        chennaiLink.click();

        // Assert URL now contains "Chennai"
        wait.until(ExpectedConditions.urlContains("Chennai"));
        String chennaiUrl = driver.getCurrentUrl();
        Assert.assertTrue(chennaiUrl.contains("Chennai"), "URL should contain 'Chennai' after selection!");
        System.out.println("Navigated to: " + chennaiUrl);
    }

    @Test(dependsOnMethods = "visitUsedCarsChennai")
    public void extractPopularModelsInChennai() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for popular models list to load
        List<WebElement> modelElements = driver.findElements(
                By.xpath(ConfigReader.getProperty("popularModels.xpath"))
        );

        wait.until(ExpectedConditions.visibilityOfAllElements(modelElements));

        // Extract model names into a List
        List<String> popularModels = new ArrayList<>();
        for (WebElement model : modelElements) {
            String name = model.getText().trim();
            if (!name.isEmpty()) {
                popularModels.add(name);
            }
        }

        // Print all popular models
        System.out.println("=== Popular Used Car Models in Chennai ===");
        for (int i = 0; i < popularModels.size(); i++) {
            System.out.println((i + 1) + ". " + popularModels.get(i));
        }

        // Assert the list is not empty
        Assert.assertFalse(popularModels.isEmpty(), "Popular models list should not be empty!");
        System.out.println("Total popular models found: " + popularModels.size());
    }
}
