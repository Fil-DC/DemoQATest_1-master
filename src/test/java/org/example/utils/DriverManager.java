package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            try {
                String browser = System.getProperty("browser", "edge");
                
                if ("firefox".equalsIgnoreCase(browser)) {
                    FirefoxOptions options = new FirefoxOptions();
                    driver.set(new FirefoxDriver(options));
                } else if ("chrome".equalsIgnoreCase(browser)) {
                    ChromeOptions options = new ChromeOptions();
                    driver.set(new ChromeDriver(options));
                } else {
                    String driverPath = System.getProperty("webdriver.edge.driver", "C:\\Users\\79291\\Desktop\\msedgedriver.exe");
                    System.setProperty("webdriver.edge.driver", driverPath);
                    
                    EdgeOptions options = new EdgeOptions();
                    
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--disable-software-rasterizer");
                    

                    options.setExperimentalOption("excludeSwitches", java.util.Arrays.asList("enable-automation", "enable-logging"));
                    options.setExperimentalOption("useAutomationExtension", false);
                    
                    options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
                    
                    options.setCapability("acceptInsecureCerts", true);
                    options.setCapability("pageLoadStrategy", "eager");
                    
                    driver.set(new EdgeDriver(options));
                }
                
                driver.get().manage().window().maximize();
                driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            } catch (Exception e) {
                System.err.println("Failed to initialize WebDriver: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("WebDriver initialization failed", e);
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().close();
            driver.remove();
        }
    }
}
