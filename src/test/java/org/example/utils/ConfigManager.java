package org.example.utils;

public class ConfigManager {
    
    public static String getBrowser() {
        return System.getProperty("browser", "chrome");
    }
    
    public static boolean isHeadless() {
        String headless = System.getProperty("headless", "true");
        return "true".equalsIgnoreCase(headless);
    }
    
    public static long getImplicitWait() {
        return Long.parseLong(System.getProperty("implicitWait", "10"));
    }
    
    public static long getPageLoadTimeout() {
        return Long.parseLong(System.getProperty("pageLoadTimeout", "30"));
    }
}
