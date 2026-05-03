package com.zigwheels.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    public static ExtentReports extent;
    public static ExtentTest test;

    // Call once to set up the report
    public static void setUp() {
        ExtentSparkReporter reporter =
                new ExtentSparkReporter("reports/TestReport.html");
        reporter.config().setReportName("Zigwheels Test Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    // Call at start of each test
    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    // Save report to file
    public static void flush() {
        extent.flush();
    }
}