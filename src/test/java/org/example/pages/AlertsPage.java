package org.example.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;
import java.time.Duration;

public class AlertsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By alertsButton = By.xpath("//span[text()='Alerts']");
    private final By alertButton = By.id("alertButton");
    private final By confirmButton = By.id("confirmButton");
    private final By promptButton = By.id("promtButton");
    private final By confirmResult = By.id("confirmResult");
    private final By promptResult = By.id("promptResult");

    private final By framesButton = By.xpath("//span[text()='Frames']");
    private final By frame1 = By.id("frame1");
    private final By frame2 = By.id("frame2");

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Open Alerts section")
    public void openAlerts() {
        wait.until(ExpectedConditions.elementToBeClickable(alertsButton)).click();
    }

    @Step("Click simple alert button")
    public String clickAlertButton() {
        driver.findElement(alertButton).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    @Step("Click confirm alert button")
    public String clickConfirmButtonAndAccept() {
        driver.findElement(confirmButton).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return wait.until(ExpectedConditions.presenceOfElementLocated(confirmResult)).getText();
    }

    @Step("Click confirm alert button and dismiss")
    public String clickConfirmButtonAndDismiss() {
        driver.findElement(confirmButton).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        return wait.until(ExpectedConditions.presenceOfElementLocated(confirmResult)).getText();
    }

    @Step("Click prompt alert button")
    public String clickPromptButton(String text) {
        driver.findElement(promptButton).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
        return wait.until(ExpectedConditions.presenceOfElementLocated(promptResult)).getText();
    }

    @Step("Open Frames section")
    public void openFrames() {
        wait.until(ExpectedConditions.elementToBeClickable(framesButton)).click();
    }

    @Step("Get text from frame 1")
    public String getFrame1Text() {
        driver.switchTo().frame(driver.findElement(frame1));
        String text = driver.findElement(By.id("sampleHeading")).getText();
        driver.switchTo().defaultContent();
        return text;
    }

    @Step("Get text from frame 2")
    public String getFrame2Text() {
        driver.switchTo().frame(driver.findElement(frame2));
        String text = driver.findElement(By.id("sampleHeading")).getText();
        driver.switchTo().defaultContent();
        return text;
    }
}
