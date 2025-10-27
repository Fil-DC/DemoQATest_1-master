package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;
import java.time.Duration;

public class ElementsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By textBoxButton = By.xpath("//span[text()='Text Box']");
    private final By fullNameInput = By.id("userName");
    private final By emailInput = By.id("userEmail");
    private final By currentAddressInput = By.id("currentAddress");
    private final By permanentAddressInput = By.id("permanentAddress");
    private final By submitButton = By.id("submit");
    private final By outputName = By.id("name");
    private final By outputEmail = By.id("email");
    
    private final By buttonsButton = By.xpath("//span[text()='Buttons']");
    private final By doubleClickButton = By.id("doubleClickBtn");
    private final By rightClickButton = By.id("rightClickBtn");
    private final By clickMeButton = By.xpath("//button[text()='Click Me']");
    private final By doubleClickMessage = By.id("doubleClickMessage");
    private final By rightClickMessage = By.id("rightClickMessage");
    private final By clickMessage = By.id("dynamicClickMessage");

    public ElementsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Open Text Box section")
    public void openTextBox() {
        wait.until(ExpectedConditions.elementToBeClickable(textBoxButton)).click();
    }

    @Step("Fill Text Box form with valid data")
    public void fillTextBoxForm(String fullName, String email, String currentAddress, String permanentAddress) {
        driver.findElement(fullNameInput).sendKeys(fullName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(currentAddressInput).sendKeys(currentAddress);
        driver.findElement(permanentAddressInput).sendKeys(permanentAddress);
    }

    @Step("Submit Text Box form")
    public void submitTextBoxForm() {
        driver.findElement(submitButton).click();
    }

    @Step("Verify Text Box output")
    public boolean verifyOutput(String expectedName, String expectedEmail) {
        wait.until(ExpectedConditions.presenceOfElementLocated(outputName));
        WebElement nameElement = driver.findElement(outputName);
        WebElement emailElement = driver.findElement(outputEmail);
        
        return nameElement.getText().contains(expectedName) && 
               emailElement.getText().contains(expectedEmail);
    }

    @Step("Fill Text Box form with invalid email")
    public void fillTextBoxWithInvalidEmail(String fullName, String invalidEmail) {
        driver.findElement(fullNameInput).sendKeys(fullName);
        driver.findElement(emailInput).sendKeys(invalidEmail);
    }

    public boolean isEmailFieldInvalid() {
        WebElement emailField = driver.findElement(emailInput);
        String classAttribute = emailField.getAttribute("class");
        return classAttribute.contains("field-error") || classAttribute.contains("is-invalid");
    }
    
    @Step("Open Buttons section")
    public void openButtons() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonsButton)).click();
    }
    
    @Step("Perform double click on button")
    public void performDoubleClick() {
        Actions actions = new Actions(driver);
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(doubleClickButton));
        actions.doubleClick(button).perform();
    }
    
    @Step("Perform right click on button")
    public void performRightClick() {
        Actions actions = new Actions(driver);
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(rightClickButton));
        actions.contextClick(button).perform();
    }
    
    @Step("Perform single click on button")
    public void performSingleClick() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(clickMeButton));
        button.click();
    }
    
    @Step("Verify double click message")
    public String getDoubleClickMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(doubleClickMessage));
        return driver.findElement(doubleClickMessage).getText();
    }
    
    @Step("Verify right click message")
    public String getRightClickMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(rightClickMessage));
        return driver.findElement(rightClickMessage).getText();
    }
    
    @Step("Verify single click message")
    public String getClickMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(clickMessage));
        return driver.findElement(clickMessage).getText();
    }
    
    private final By checkBoxButton = By.xpath("//span[text()='Check Box']");
    private final By expandAllButton = By.cssSelector("button[title='Expand all']");
    private final By collapseAllButton = By.cssSelector("button[title='Collapse all']");
    private final By checkBoxResult = By.id("result");
    
    @Step("Open Check Box section")
    public void openCheckBox() {
        wait.until(ExpectedConditions.elementToBeClickable(checkBoxButton)).click();
    }
    
    @Step("Expand all check boxes")
    public void expandAllCheckBoxes() {
        wait.until(ExpectedConditions.elementToBeClickable(expandAllButton)).click();
    }
    
    @Step("Select check box")
    public void selectCheckBox(String checkBoxLabel) {
        WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[contains(@class,'rct-text')]//span[contains(text(),'" + checkBoxLabel + "')]")
        ));
        checkBox.click();
    }
    
    public String getCheckBoxResult() {
        wait.until(ExpectedConditions.presenceOfElementLocated(checkBoxResult));
        return driver.findElement(checkBoxResult).getText();
    }
    
    private final By radioButtonButton = By.xpath("//span[text()='Radio Button']");
    private final By yesRadioButton = By.cssSelector("label[for='yesRadio']");
    private final By impressiveRadioButton = By.cssSelector("label[for='impressiveRadio']");
    private final By noRadioButton = By.cssSelector("label[for='noRadio']");
    private final By radioResult = By.cssSelector(".text-success");
    
    @Step("Open Radio Button section")
    public void openRadioButton() {
        wait.until(ExpectedConditions.elementToBeClickable(radioButtonButton)).click();
    }
    
    @Step("Select Yes radio button")
    public void selectYesRadioButton() {
        wait.until(ExpectedConditions.elementToBeClickable(yesRadioButton)).click();
    }
    
    @Step("Select Impressive radio button")
    public void selectImpressiveRadioButton() {
        wait.until(ExpectedConditions.elementToBeClickable(impressiveRadioButton)).click();
    }
    
    @Step("Select No radio button")
    public void selectNoRadioButton() {
        wait.until(ExpectedConditions.elementToBeClickable(noRadioButton)).click();
    }
    
    public String getRadioButtonResult() {
        wait.until(ExpectedConditions.presenceOfElementLocated(radioResult));
        return driver.findElement(radioResult).getText();
    }
    
    public boolean isNoRadioButtonEnabled() {
        WebElement noRadio = driver.findElement(noRadioButton);
        return noRadio.isEnabled() && noRadio.getAttribute("class") == null;
    }
    
    private final By webTablesButton = By.xpath("//span[text()='Web Tables']");
    private final By addButton = By.id("addNewRecordButton");
    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By userEmailInput = By.id("userEmail");
    private final By ageInput = By.id("age");
    private final By salaryInput = By.id("salary");
    private final By departmentInput = By.id("department");
    private final By submitButtonWebTable = By.id("submit");
    private final By searchBox = By.id("searchBox");
    
    @Step("Open Web Tables section")
    public void openWebTables() {
        wait.until(ExpectedConditions.elementToBeClickable(webTablesButton)).click();
    }
    
    @Step("Click add button")
    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }
    
    @Step("Fill web table form")
    public void fillWebTableForm(String firstName, String lastName, String email, String age, String salary, String department) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(userEmailInput).sendKeys(email);
        driver.findElement(ageInput).sendKeys(age);
        driver.findElement(salaryInput).sendKeys(salary);
        driver.findElement(departmentInput).sendKeys(department);
    }
    
    @Step("Submit web table form")
    public void submitWebTableForm() {
        driver.findElement(submitButtonWebTable).click();
    }
    
    @Step("Search in web table")
    public void searchInWebTable(String searchText) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(searchText);
    }
    
    public boolean isRecordPresent(String searchText) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='rt-tbody']//div[contains(text(),'" + searchText + "')]")
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Step("Delete record from web table")
    public void deleteRecord(String firstName) {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(text(),'" + firstName + "')]//ancestor::div[@role='row']//span[@title='Delete']")
        ));
        deleteButton.click();
    }
    
    private final By linksButton = By.xpath("//span[text()='Links']");
    private final By homeLink = By.id("simpleLink");
    private final By dynamicLink = By.id("dynamicLink");
    
    @Step("Open Links section")
    public void openLinks() {
        wait.until(ExpectedConditions.elementToBeClickable(linksButton)).click();
    }
    
    @Step("Click home link")
    public void clickHomeLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(homeLink));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
    }
    
    private final By brokenLinksButton = By.xpath("//span[text()='Broken Links - Images']");
    
    @Step("Open Broken Links - Images section")
    public void openBrokenLinks() {
        wait.until(ExpectedConditions.elementToBeClickable(brokenLinksButton)).click();
    }
    
    public boolean isImageBroken(String imageAlt) {
        try {
            WebElement image = driver.findElement(By.xpath("//img[@alt='" + imageAlt + "']"));
            return !image.getAttribute("naturalWidth").equals("0");
        } catch (Exception e) {
            return false;
        }
    }
    
    private final By uploadDownloadButton = By.xpath("//span[text()='Upload and Download']");
    private final By downloadButton = By.id("downloadButton");
    private final By uploadFileInput = By.id("uploadFile");
    
    @Step("Open Upload and Download section")
    public void openUploadDownload() {
        wait.until(ExpectedConditions.elementToBeClickable(uploadDownloadButton)).click();
    }
    
    @Step("Click download button")
    public void clickDownloadButton() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadButton)).click();
    }
    
    public boolean isFileUploaded() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadedFilePath")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private final By dynamicPropertiesButton = By.xpath("//span[text()='Dynamic Properties']");
    private final By enableAfterButton = By.id("enableAfter");
    private final By colorChangeButton = By.id("colorChange");
    private final By visibleAfterButton = By.id("visibleAfter");
    
    @Step("Open Dynamic Properties section")
    public void openDynamicProperties() {
        wait.until(ExpectedConditions.elementToBeClickable(dynamicPropertiesButton)).click();
    }
    
    public boolean isButtonEnabled(String buttonId) {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return longWait.until(ExpectedConditions.elementToBeClickable(By.id(buttonId))).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isButtonVisible(String buttonId) {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return longWait.until(ExpectedConditions.presenceOfElementLocated(By.id(buttonId))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
