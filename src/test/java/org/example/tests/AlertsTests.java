package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.AlertsPage;
import org.example.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;

@Epic("DemoQA Tests")
@Feature("Alerts and Frames")
public class AlertsTests extends BaseTest {

    @Test(priority = 1, description = "Verify simple alert handling")
    @Severity(SeverityLevel.NORMAL)
    @Story("Alerts - Simple Alert")
    public void testSimpleAlert() {
        MainPage mainPage = new MainPage(driver);
        AlertsPage alertsPage = new AlertsPage(driver);

        mainPage.navigateToAlerts();
        alertsPage.openAlerts();
        
        String alertText = alertsPage.clickAlertButton();
        
        Assert.assertTrue(alertText.contains("You clicked a button"),
                         "Alert text should contain expected message");
    }

    @Test(priority = 2, description = "Verify confirm alert with accept")
    @Severity(SeverityLevel.NORMAL)
    @Story("Alerts - Confirm Alert")
    public void testConfirmAlertAccept() {
        MainPage mainPage = new MainPage(driver);
        AlertsPage alertsPage = new AlertsPage(driver);

        mainPage.navigateToAlerts();
        alertsPage.openAlerts();
        
        String result = alertsPage.clickConfirmButtonAndAccept();
        
        Assert.assertTrue(result.contains("Ok"), 
                         "Result should indicate user clicked Ok");
    }

    @Test(priority = 3, description = "Verify confirm alert with dismiss")
    @Severity(SeverityLevel.NORMAL)
    @Story("Alerts - Confirm Alert Dismiss")
    public void testConfirmAlertDismiss() {
        MainPage mainPage = new MainPage(driver);
        AlertsPage alertsPage = new AlertsPage(driver);

        mainPage.navigateToAlerts();
        alertsPage.openAlerts();
        
        String result = alertsPage.clickConfirmButtonAndDismiss();
        
        Assert.assertTrue(result.contains("Cancel") || result.contains("Cancel"),
                         "Result should indicate user clicked Cancel");
    }

    @Test(priority = 4, description = "Verify prompt alert with text input")
    @Severity(SeverityLevel.NORMAL)
    @Story("Alerts - Prompt Alert")
    public void testPromptAlert() {
        MainPage mainPage = new MainPage(driver);
        AlertsPage alertsPage = new AlertsPage(driver);

        mainPage.navigateToAlerts();
        alertsPage.openAlerts();
        
        String result = alertsPage.clickPromptButton("Test User");
        
        Assert.assertTrue(result.contains("Test User"), 
                         "Result should contain the input text");
    }

    @Test(priority = 5, description = "Verify frame content is accessible")
    @Severity(SeverityLevel.MINOR)
    @Story("Frames - Frame Content")
    public void testFrames() {
        MainPage mainPage = new MainPage(driver);
        AlertsPage alertsPage = new AlertsPage(driver);

        mainPage.navigateToAlerts();
        alertsPage.openFrames();
        
        String frame1Text = alertsPage.getFrame1Text();
        String frame2Text = alertsPage.getFrame2Text();
        
        Assert.assertNotNull(frame1Text, "Frame 1 should have content");
        Assert.assertNotNull(frame2Text, "Frame 2 should have content");
    }
}
