package org.example.base;

import org.example.utils.AllureHelper;
import org.example.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://demoqa.com";

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        System.out.println("Setting up test suite - initializing driver...");
        driver = DriverManager.getDriver();
        System.out.println("Navigating to: " + BASE_URL);
        driver.get(BASE_URL);
        System.out.println("Page loaded successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterTest(ITestResult result) {
        System.out.println("Test completed: " + result.getName());
        if (!result.isSuccess()) {
            System.out.println("Test failed - taking screenshot and saving page source");
            AllureHelper.takeScreenshot(driver);
            AllureHelper.savePageSource(driver);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        System.out.println("Tearing down test suite...");
        System.out.println("Quitting driver...");
        DriverManager.quitDriver();
    }
}
