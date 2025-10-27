package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final String BASE_URL = "https://demoqa.com";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void navigateToElements() {
        try {
            System.out.println("Navigating to Elements...");
            
            // Direct navigation to Elements page
            driver.get(BASE_URL + "/elements");
            System.out.println("Navigated to Elements page successfully");
            
        } catch (Exception e) {
            System.err.println("Error navigating to Elements: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void navigateToForms() {
        try {
            driver.get(BASE_URL + "/forms");
        } catch (Exception e) {
            System.err.println("Error navigating to Forms: " + e.getMessage());
            throw e;
        }
    }

    public void navigateToAlerts() {
        try {
            driver.get(BASE_URL + "/alertsWindows");
        } catch (Exception e) {
            System.err.println("Error navigating to Alerts: " + e.getMessage());
            throw e;
        }
    }

    public void navigateToWidgets() {
        try {
            driver.get(BASE_URL + "/widgets");
        } catch (Exception e) {
            System.err.println("Error navigating to Widgets: " + e.getMessage());
            throw e;
        }
    }
}
