package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Utility Class for the WebDriver
 * Provides a driver for all tests and handles driver setup and teardown
 */
public class DriverManager {
    private static WebDriver driver;


    /**
     * Returns the WebDriver
     * Initializes the ChromeDriver if it has not been created yet
     *
     * @return WebDriver
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }

    /**
     * Quits the WebDriver if it exists and sets it to null
     * Ensures proper cleanup after tests
     */
    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


}
