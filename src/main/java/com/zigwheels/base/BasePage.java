package com.zigwheels.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //click
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // JavaScript click — bypasses overlays/popups
    public void jsClick(By locator) {
        WebElement el = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    // Hover over element
    public void hover(By locator) {
        WebElement el = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
        new Actions(driver).moveToElement(el).perform();
    }

    // Type text
    public void type(By locator, String text) {
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(locator)).sendKeys(text);
    }

    // Get text
    public String getText(By locator) {
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(locator)).getText();
    }

    // Scroll element into view
    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void pause(int milliseconds) {
        try { Thread.sleep(milliseconds); } catch (Exception e) {}
    }

}
