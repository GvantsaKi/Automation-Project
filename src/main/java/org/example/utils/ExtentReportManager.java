package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Utility class to manage ExtentReports and ExtentTest
 * Handles report creation, test logging and report flushing
 */
public class ExtentReportManager {
    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Initializes ExtentReports, configures report name, title and system info
     *
     * @return ExtentReports
     */
    public static ExtentReports getExtentReports() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/report/report.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName(ConfigReader.get("report.name"));
            sparkReporter.config().setDocumentTitle(ConfigReader.get("report.title"));

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", ConfigReader.get("report.environment"));
            extent.setSystemInfo("Tester", ConfigReader.get("report.tester")
            );
        }
        return extent;
    }

    /**
     * Creates a new ExtentTest with the given test name and sets it in
     *
     * @param testName Name of the test (for example, "Login Test")
     * @return ExtentTest for the test
     */
    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = getExtentReports().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    /**
     * Returns the current ExtentTest for the running
     *
     * @return current ExtentTest
     */
    public static ExtentTest getTest() {
        return test.get();
    }

    /**
     * Flushes the ExtentReports, writing all logs to the report file
     * Should be called at the end of the test suite
     */
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
