package com.zigwheels.pages;

import com.zigwheels.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UpcomingBikesPage extends BasePage {

    private By hondaFilterLink = By.xpath(
            "//a[@data-track-label='filter-by-brand' and normalize-space()='Honda']");

    private By bikeCards = By.xpath(
            "//ul[@id='modelList']/li[contains(@class,'modelItem')]");

    // Inside each bike card
    private By bikeName   = By.xpath(".//a[@data-track-label='model-name']");
    private By bikePrice  = By.xpath(".//div[contains(@title,'Ex-Showroom Price')]");
    private By launchDate = By.xpath(".//div[contains(text(),'Expected Launch')]");

    public UpcomingBikesPage(WebDriver driver) {
        super(driver);
    }

    // Scroll to Honda button and click it
    public void applyHondaFilter() {
        WebElement honda = driver.findElement(hondaFilterLink);
        scrollIntoView(honda);
        pause(1000);
        jsClick(hondaFilterLink);
        pause(2000);
        System.out.println("[INFO] Honda filter applied");
    }

    // Loop through all bike cards and print bikes under 4 Lac
    public int displayBikesUnder4Lac() {

        List<WebElement> cards = driver.findElements(bikeCards);
        System.out.println("[INFO] Total Honda bikes found: " + cards.size());

        System.out.println("==========================================");
        System.out.println("   Honda Upcoming Bikes — Under 4 Lac    ");
        System.out.println("==========================================");

        int count = 0;

        for (WebElement card : cards) {
            try {
                // Read bike name
                String name = card.findElement(bikeName)
                        .getAttribute("title").trim();

                // Read price text
                String priceText = card.findElement(bikePrice)
                        .getText().trim();

                // Read launch date and clean it up
                String date = card.findElement(launchDate)
                        .getText().trim()
                        .replace("Expected Launch : ", "");

                // Get price number from data-price attribute for comparison
                String dataPriceAttr = card.getAttribute("data-price");
                long priceInRupees = Long.parseLong(dataPriceAttr.trim());

                // Only show bikes under 4 Lac (400000 rupees)
                if (priceInRupees > 0 && priceInRupees < 400000) {
                    count++;
                    System.out.println("Bike Name  : " + name);
                    System.out.println("Price      : " + priceText);
                    System.out.println("Launch Date: " + date);
                    System.out.println("------------------------------------------");
                }

            } catch (Exception e) {
                // If any element is missing in a card, skip it
                System.out.println("[WARN] Skipping a card: " + e.getMessage());
            }
        }

        System.out.println("==========================================");
        System.out.println("Total bikes under 4 Lac: " + count);
        System.out.println("==========================================");
        return count;
    }



}