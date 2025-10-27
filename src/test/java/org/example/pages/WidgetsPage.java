package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;
import java.time.Duration;

public class WidgetsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    private final By droppableButton = By.xpath("//span[text()='Droppable']");
    private final By draggableElement = By.id("draggable");
    private final By droppableElement = By.id("droppable");

    private final By tooltipsButton = By.xpath("//span[text()='Tool Tips']");
    private final By hoverButton = By.id("toolTipButton");
    private final By tooltipText = By.className("tooltip-inner");

    private final By datePickerButton = By.xpath("//span[text()='Date Picker']");
    private final By datePickerInput = By.id("datePickerMonthYearInput");
    private final By dateAndTimeInput = By.id("dateAndTimePickerInput");

    public WidgetsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    @Step("Open Droppable section")
    public void openDroppable() {
        wait.until(ExpectedConditions.elementToBeClickable(droppableButton)).click();
    }

    @Step("Perform drag and drop")
    public boolean performDragAndDrop() {
        WebElement dragElement = wait.until(ExpectedConditions.presenceOfElementLocated(draggableElement));
        WebElement dropElement = wait.until(ExpectedConditions.presenceOfElementLocated(droppableElement));
        
        actions.dragAndDrop(dragElement, dropElement).perform();
        
        String dropText = dropElement.getText();
        return "Dropped!".equals(dropText);
    }

    @Step("Open Tool Tips section")
    public void openTooltips() {
        wait.until(ExpectedConditions.elementToBeClickable(tooltipsButton)).click();
    }

    @Step("Hover over button and get tooltip text")
    public String hoverOverButtonAndGetTooltip() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(hoverButton));
        actions.moveToElement(button).perform();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(tooltipText));
        return driver.findElement(tooltipText).getText();
    }

    @Step("Open Date Picker section")
    public void openDatePicker() {
        wait.until(ExpectedConditions.elementToBeClickable(datePickerButton)).click();
    }

    @Step("Select date")
    public void selectDate(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.presenceOfElementLocated(datePickerInput));
        dateInput.clear();
        dateInput.sendKeys(date);
    }

    public String getSelectedDate() {
        return driver.findElement(datePickerInput).getAttribute("value");
    }
}
