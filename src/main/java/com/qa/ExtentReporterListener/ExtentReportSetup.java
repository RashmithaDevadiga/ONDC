package com.qa.ExtentReporterListener;

import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportSetup implements ITestListener {

    private static ExtentReports extent;

    static {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("report_zupaloop.html");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Extent Reports");
        extent.attachReporter(spark);
    }

    public synchronized static ExtentReports getExtent() {
        return extent;
    }

    public synchronized static void tearDown() {
        System.out.println("Tearing down ExtentReports...");
        if (extent != null) {
            extent.flush();
            System.out.println("ExtentReports flushed.");
        } else {
            System.out.println("ExtentReports is null. Nothing to flush.");
        }
    }
}
