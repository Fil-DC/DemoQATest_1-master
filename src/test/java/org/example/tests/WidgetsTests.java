package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.MainPage;
import org.example.pages.WidgetsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;

@Epic("DemoQA Tests")
@Feature("Widgets")
public class WidgetsTests extends BaseTest {

    @Test(priority = 1, description = "Verify drag and drop functionality")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Test Engineer")
    @Story("Widgets - Drag and Drop")
    public void testDragAndDrop() {
        MainPage mainPage = new MainPage(driver);
        WidgetsPage widgetsPage = new WidgetsPage(driver);

        mainPage.navigateToWidgets();
        widgetsPage.openDroppable();
        
        boolean result = widgetsPage.performDragAndDrop();
        
        Assert.assertTrue(result, "Drag and drop should be successful");
    }

    @Test(priority = 2, description = "Verify tooltip appears on hover")
    @Severity(SeverityLevel.MINOR)
    @Owner("Test Engineer")
    @Story("Widgets - Tooltips")
    public void testTooltips() {
        MainPage mainPage = new MainPage(driver);
        WidgetsPage widgetsPage = new WidgetsPage(driver);

        mainPage.navigateToWidgets();
        widgetsPage.openTooltips();
        
        String tooltipText = widgetsPage.hoverOverButtonAndGetTooltip();
        
        Assert.assertNotNull(tooltipText, "Tooltip should appear");
        Assert.assertFalse(tooltipText.isEmpty(), "Tooltip should have text");
    }

    @Test(priority = 3, description = "Verify date picker functionality")
    @Severity(SeverityLevel.MINOR)
    @Owner("Test Engineer")
    @Story("Widgets - Date Picker")
    public void testDatePicker() {
        MainPage mainPage = new MainPage(driver);
        WidgetsPage widgetsPage = new WidgetsPage(driver);

        mainPage.navigateToWidgets();
        widgetsPage.openDatePicker();
        
        String testDate = "01/15/2024";
        widgetsPage.selectDate(testDate);
        
        String selectedDate = widgetsPage.getSelectedDate();
        
        Assert.assertNotNull(selectedDate, "Date should be selected");
    }
}
