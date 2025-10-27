package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;
import java.time.Duration;

public class FormsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By practiceFormButton = By.xpath("//span[text()='Practice Form']");
    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("userEmail");
    private final By maleRadioButton = By.xpath("//label[@for='gender-radio-1']");
    private final By mobileInput = By.id("userNumber");
    private final By dateOfBirthInput = By.id("dateOfBirthInput");
    private final By subjectsInput = By.id("subjectsInput");
    private final By hobbiesSports = By.xpath("//label[@for='hobbies-checkbox-1']");
    private final By currentAddressInput = By.id("currentAddress");
    private final By submitButton = By.id("submit");
    private final By closeModalButton = By.id("closeLargeModal");

    public FormsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Open Practice Form section")
    public void openPracticeForm() {
        wait.until(ExpectedConditions.elementToBeClickable(practiceFormButton)).click();
    }

    @Step("Fill Practice Form with valid data")
    public void fillPracticeForm(String firstName, String lastName, String email, 
                                  String mobile, String subject, String address) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(maleRadioButton).click();
        driver.findElement(mobileInput).sendKeys(mobile);
        
        WebElement dateInput = driver.findElement(dateOfBirthInput);
        dateInput.sendKeys(Keys.CONTROL + "a");
        dateInput.sendKeys("15 Jan 1990");
        dateInput.sendKeys(Keys.ENTER);
        
        WebElement subjects = driver.findElement(subjectsInput);
        subjects.sendKeys(subject);
        subjects.sendKeys(Keys.ENTER);
        
        driver.findElement(hobbiesSports).click();
        
        driver.findElement(currentAddressInput).sendKeys(address);
    }

    @Step("Submit Practice Form")
    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    @Step("Close modal")
    public void closeModal() {
        wait.until(ExpectedConditions.elementToBeClickable(closeModalButton)).click();
    }

    public boolean isFormSubmitted() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(closeModalButton)).isDisplayed();
    }

    @Step("Fill Practice Form with invalid data")
    public void fillPracticeFormWithInvalidData() {
        driver.findElement(firstNameInput).sendKeys("");
        driver.findElement(lastNameInput).sendKeys("");
        driver.findElement(mobileInput).sendKeys("123");
    }

    public boolean isFormInvalid() {
        WebElement firstNameField = driver.findElement(firstNameInput);
        String classAttribute = firstNameField.getAttribute("class");
        return classAttribute.contains("is-invalid") || classAttribute.contains("field-error");
    }
}
