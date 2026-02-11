package org.example.utils;

public class Utils {
    public static void logInfo(String msg) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().info(msg);
        }
    }

    public static void logPass(String msg) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().pass(msg);
        }
    }

    public static void logFailed(String msg) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().fail(msg);
        }
    }


}
