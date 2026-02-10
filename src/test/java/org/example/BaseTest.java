package org.example;

import org.example.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;


    // Code which will run before every test
    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
    }

    // Code which will run after every test
    @AfterMethod
    public void tearDown() {
        DriverManager.quit();
    }

}
