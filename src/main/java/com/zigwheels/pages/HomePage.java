package com.zigwheels.pages;

import com.zigwheels.base.BasePage;
import com.zigwheels.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    //bike menu
    private By newBikesMenu = By.xpath(
            "//span[contains(@class,'ml-5') and contains(text(),'NEW BIKES')]");

    // Upcoming Bikes option in dropdown
    private By upcomingBikesLink = By.xpath(
            "//a[@data-track-label='nav-upcoming-bikes']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Open zigwheels home page
    public void openHomePage() {
        navigateTo(ConfigReader.getProperty("baseUrl"));
        System.out.println("[INFO] Opened Home Page");
    }

    // Hover on NEW BIKES → click Upcoming Bikes
    public void goToUpcomingBikes() {
        hover(newBikesMenu);
        pause(1000);
        click(upcomingBikesLink);
        pause(2000);
        System.out.println("[INFO] Navigated to Upcoming Bikes page");
    }




}
