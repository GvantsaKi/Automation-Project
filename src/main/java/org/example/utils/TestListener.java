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

public class TestListener implements ITestListener {

    // This will run when test is started
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.createTest(testName);
    }

    // This will run when test is passed
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        ExtentReportManager.getTest().pass("Test passed");
    }


    // This method will take the screenshot once the test fails
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

    // This will run when test is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().skip("Test skipped");
        System.out.println("Test Skipped: " + result.getName());
    }

    // This will run when test suite is passed
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    // This will run when test suite is finished
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
        ExtentReportManager.flushReports();
    }
}
