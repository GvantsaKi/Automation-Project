package org.example.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


/**
 * Listens to test events and logs them to ExtentReports
 * Takes screenshots when a test fails
 */
public class TestListener implements ITestListener {

    /**
     * Runs when a test starts
     * Creates a new test entry in the report
     *
     * @param result The test that is starting
     */
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.createTest(testName);
    }

    /**
     * Runs when a test passes
     * Marks the test as passed in the report
     *
     * @param result The test that passed
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        ExtentReportManager.getTest().pass("Test passed");
    }


    /**
     * Runs when a test fails
     * Takes a screenshot, saves it and adds it to the report
     *
     * @param result The test that failed
     */
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        ExtentReportManager.getTest().fail("Test failed: " + result.getName());
        System.out.println("Test Failed: " + result.getName());

        String screenshotDir = System.getProperty("user.dir") + "/report/screenshots/";
        new File(screenshotDir).mkdirs();

        String screenshotPath = screenshotDir + result.getName() + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(screenshotPath));

            ExtentReportManager.getTest().fail(
                    "Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(
                            "./screenshots/" + result.getName() + ".png"
                    ).build()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs when a test is skipped
     * Marks the test as skipped in the report
     *
     * @param result The test that was skipped
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().skip("Test skipped");
        System.out.println("Test Skipped: " + result.getName());
    }

    /**
     * Runs before the test suite starts
     * Can be used to set up global things before tests run
     *
     * @param context The test suite context
     */
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    /**
     * Runs after the test suite finishes
     * Saves all report logs to the report file
     *
     * @param context The test suite context
     */
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
        ExtentReportManager.flushReports();
    }
}
