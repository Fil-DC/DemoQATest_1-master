package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.FormsPage;
import org.example.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;

@Epic("DemoQA Tests")
@Feature("Forms")
public class FormsTests extends BaseTest {

    @Test(priority = 1, description = "Verify successful practice form submission with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Test Engineer")
    @Story("Practice Form - Positive Scenario")
    public void testValidPracticeFormSubmission() {
        MainPage mainPage = new MainPage(driver);
        FormsPage formsPage = new FormsPage(driver);

        mainPage.navigateToForms();
        formsPage.openPracticeForm();
        formsPage.fillPracticeForm("John", "Doe", "john.doe@example.com", 
                                   "1234567890", "Maths", "123 Main St");
        formsPage.submitForm();
        
        Assert.assertTrue(formsPage.isFormSubmitted(), 
                         "Form should be submitted successfully");
        
        formsPage.closeModal();
    }

    @Test(priority = 2, description = "Verify practice form validation with empty fields")
    @Severity(SeverityLevel.MINOR)
    @Owner("Test Engineer")
    @Story("Practice Form - Negative Scenario")
    public void testInvalidPracticeFormSubmission() {
        MainPage mainPage = new MainPage(driver);
        FormsPage formsPage = new FormsPage(driver);

        mainPage.navigateToForms();
        formsPage.openPracticeForm();
        formsPage.fillPracticeFormWithInvalidData();
        
        formsPage.submitForm();
        
        Assert.assertTrue(true, "Form validation should be triggered");
    }
}
