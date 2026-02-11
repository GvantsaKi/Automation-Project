package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Initialize ExtentReports
    public static ExtentReports getExtentReports() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/report/report.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("Automation Test Report"); //ესენი გასატანია კონფიგ ფაილში, გაჰარდკოდებული რომ არ იყოს
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Your Name");
        }
        return extent;
    }

    // Create a test
    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = getExtentReports().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    // Get the current test
    public static ExtentTest getTest() {
        return test.get();
    }

    // Flush test reports
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
