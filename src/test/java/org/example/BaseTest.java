package org.example;

import org.example.utils.ConfigReader;
import org.example.utils.DriverManager;
import org.example.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest class provides setup, teardown and assertion methods for all tests
 * All test classes should extend this class to reuse WebDriver, assertion and logging
 */
public class BaseTest {
    protected WebDriver driver;


    /**
     * Sets up WebDriver before every test
     * Opens the browser, maximizes window and navigates to the base URL
     */
    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url"));
        Utils.logInfo("Navigate to: " + ConfigReader.get("base.url"));

    }

    /**
     * Quits WebDriver after every test to close the browser
     */
    @AfterMethod
    public void tearDown() {
        DriverManager.quit();

    }

    /**
     * Asserts that actual string matches expected string and logs the assertion
     *
     * @param act actual string value
     * @param exp expected string value
     */
    public void assertString(String act, String exp) {
        Assert.assertEquals(act, exp, "custom error message");
        Utils.logInfo("*ASSERTION*: act: [ " + act + " ] exp: [ " + exp + " ] ");
    }

}
