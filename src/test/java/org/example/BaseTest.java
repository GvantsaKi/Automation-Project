package org.example;

import org.example.utils.ConfigReader;
import org.example.utils.DriverManager;
import org.example.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    protected WebDriver driver;


    // Code which will run before every test
    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url"));
        Utils.logInfo("Navigate to: " + ConfigReader.get("base.url"));

    }

    // Code which will run after every test
    @AfterMethod
    public void tearDown() {
        DriverManager.quit();

    }

    // Assertion which will be used in tests
    public void assertString(String act, String exp) {
        Assert.assertEquals(act, exp, "custom error message");
        Utils.logInfo("*ASSERTION*: act: [ " + act + " ] exp: [ " + exp + " ] ");
    }

}
