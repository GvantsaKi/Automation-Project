package org.example.utils;

/**
 * Utility methods for logging messages to ExtentReports
 * Helps to add info, pass or fail messages to the current test report
 */
public class Utils {
    /**
     * Logs an informational message to the current test report
     *
     * @param msg The message to log
     */
    public static void logInfo(String msg) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().info(msg);
        }
    }

    /**
     * Logs a passed message to the current test report
     *
     * @param msg The message to log as pass
     */
    public static void logPass(String msg) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().pass(msg);
        }
    }

    /**
     * Logs a failed message to the current test report
     *
     * @param msg The message to log as fail
     */
    public static void logFailed(String msg) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().fail(msg);
        }
    }


}
