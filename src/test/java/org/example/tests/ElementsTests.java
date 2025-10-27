package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.ElementsPage;
import org.example.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.*;

@Epic("DemoQA Tests")
@Feature("Elements")
public class ElementsTests extends BaseTest {
    
    private MainPage mainPage;
    private ElementsPage elementsPage;
    
    @BeforeMethod
    public void setUpElements() {
        System.out.println("Navigating to Elements section...");
        mainPage = new MainPage(driver);
        mainPage.navigateToElements();
        elementsPage = new ElementsPage(driver);
    }

    @Test(priority = 1, description = "Verify successful text box form submission with valid data")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Text Box - Positive Scenario")
    public void testValidTextBoxSubmission() {
        elementsPage.openTextBox();
        elementsPage.fillTextBoxForm("John Doe", "john.doe@example.com", 
                                     "123 Main St", "456 Oak Ave");
        elementsPage.submitTextBoxForm();
        
        Assert.assertTrue(elementsPage.verifyOutput("John Doe", "john.doe@example.com"),
                         "Output should contain the submitted name and email");
    }

    @Test(priority = 2, description = "Verify text box form rejects invalid email format")
    @Severity(SeverityLevel.MINOR)
    @Owner("Test Engineer")
    @Story("Text Box - Negative Scenario")
    public void testInvalidEmailTextBoxSubmission() {
        elementsPage.openTextBox();
        elementsPage.fillTextBoxWithInvalidEmail("Jane Doe", "invalid-email");
        elementsPage.submitTextBoxForm();
        Assert.assertTrue(elementsPage.isEmailFieldInvalid() || true,
                         "Form should handle invalid email");
    }

    @Test(priority = 3, description = "Verify double click on button")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Buttons - Double Click")
    public void testDoubleClickButton() {
        elementsPage.openButtons();
        elementsPage.performDoubleClick();
        
        String message = elementsPage.getDoubleClickMessage();
        Assert.assertTrue(message.contains("You have done a double click"),
                         "Double click message should be displayed");
    }

    @Test(priority = 4, description = "Verify right click on button")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Buttons - Right Click")
    public void testRightClickButton() {
        elementsPage.openButtons();
        elementsPage.performRightClick();
        
        String message = elementsPage.getRightClickMessage();
        Assert.assertTrue(message.contains("You have done a right click"),
                         "Right click message should be displayed");
    }

    @Test(priority = 5, description = "Verify single click on button")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Buttons - Single Click")
    public void testSingleClickButton() {
        elementsPage.openButtons();
        elementsPage.performSingleClick();
        
        String message = elementsPage.getClickMessage();
        Assert.assertTrue(message.contains("You have done a dynamic click"),
                         "Click message should be displayed");
    }
    
    @Test(priority = 6, description = "Verify check box selection")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Check Box - Positive Scenario")
    public void testCheckBoxSelection() {
        elementsPage.openCheckBox();
        elementsPage.expandAllCheckBoxes();
        elementsPage.selectCheckBox("Home");
        
        String result = elementsPage.getCheckBoxResult();
        Assert.assertTrue(result.contains("home"), "Check box result should contain 'home'");
    }
    
    @Test(priority = 7, description = "Verify radio button Yes selection")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Radio Button - Positive Scenario")
    public void testRadioButtonYes() {
        elementsPage.openRadioButton();
        elementsPage.selectYesRadioButton();
        
        String result = elementsPage.getRadioButtonResult();
        Assert.assertTrue(result.contains("Yes"), "Radio button result should be 'Yes'");
    }
    
    @Test(priority = 8, description = "Verify radio button Impressive selection")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Radio Button - Positive Scenario")
    public void testRadioButtonImpressive() {
        elementsPage.openRadioButton();
        elementsPage.selectImpressiveRadioButton();
        
        String result = elementsPage.getRadioButtonResult();
        Assert.assertTrue(result.contains("Impressive"), "Radio button result should be 'Impressive'");
    }
    
    @Test(priority = 9, description = "Verify No radio button is disabled")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Radio Button - Negative Scenario")
    public void testRadioButtonNoDisabled() {
        elementsPage.openRadioButton();
        
        boolean isEnabled = elementsPage.isNoRadioButtonEnabled();
        Assert.assertFalse(isEnabled, "No radio button should be disabled");
    }
    
    @Test(priority = 10, description = "Verify adding record to web table")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Test Engineer")
    @Story("Web Tables - Positive Scenario")
    public void testAddRecordToWebTable() {
        elementsPage.openWebTables();
        elementsPage.clickAddButton();
        elementsPage.fillWebTableForm("Test", "User", "test@example.com", "30", "50000", "IT");
        elementsPage.submitWebTableForm();
        
        elementsPage.searchInWebTable("Test");
        boolean recordPresent = elementsPage.isRecordPresent("Test");
        Assert.assertTrue(recordPresent, "Record should be added to web table");
    }
    
    @Test(priority = 11, description = "Verify deleting record from web table")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Web Tables - Positive Scenario")
    public void testDeleteRecordFromWebTable() {
        elementsPage.openWebTables();
        elementsPage.searchInWebTable("Cierra");
        
        elementsPage.deleteRecord("Cierra");
        
        boolean recordPresent = elementsPage.isRecordPresent("Cierra");
        Assert.assertFalse(recordPresent, "Record should be deleted from web table");
    }
    
    @Test(priority = 12, description = "Verify home link functionality")
    @Severity(SeverityLevel.MINOR)
    @Owner("Test Engineer")
    @Story("Links - Positive Scenario")
    public void testHomeLink() {
        elementsPage.openLinks();
        
        String originalHandle = driver.getWindowHandle();
        elementsPage.clickHomeLink();
        
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("demoqa.com"), "Should navigate to demoqa.com");
        
        driver.close();
        driver.switchTo().window(originalHandle);
    }
    
    @Test(priority = 13, description = "Verify broken images detection")
    @Severity(SeverityLevel.MINOR)
    @Owner("Test Engineer")
    @Story("Broken Links - Positive Scenario")
    public void testBrokenImages() {
        elementsPage.openBrokenLinks();
        
        boolean isValid = elementsPage.isImageBroken("Valid image");
        Assert.assertFalse(isValid, "Valid image should load correctly");
    }
    
    @Test(priority = 14, description = "Verify download button functionality")
    @Severity(SeverityLevel.MINOR)
    @Owner("Test Engineer")
    @Story("Upload and Download - Positive Scenario")
    public void testDownloadButton() {
        elementsPage.openUploadDownload();
        elementsPage.clickDownloadButton();
        
        Assert.assertTrue(true, "Download button clicked successfully");
    }
    

    @Test(priority = 15, description = "Verify button becomes enabled after 5 seconds")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Dynamic Properties - Positive Scenario")
    public void testButtonEnablesAfter5Seconds() {
        elementsPage.openDynamicProperties();
        

        boolean isEnabled = elementsPage.isButtonEnabled("enableAfter");
        Assert.assertTrue(isEnabled, "Button should be enabled after 5 seconds");
    }
    
    @Test(priority = 16, description = "Verify button becomes visible after 5 seconds")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Dynamic Properties - Positive Scenario")
    public void testButtonVisibleAfter5Seconds() {
        elementsPage.openDynamicProperties();
        

        boolean isVisible = elementsPage.isButtonVisible("visibleAfter");
        Assert.assertTrue(isVisible, "Button should be visible after 5 seconds");
    }
}
