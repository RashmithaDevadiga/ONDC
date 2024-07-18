package com.qa.ExtentReporterListener;

import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentReportNG implements ITestListener {

    private static ExtentReports extent = ExtentReportSetup.getExtent();
    private  ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Nothing to do here
    }

    @Override
    public void onFinish(ITestContext context) {
        // Nothing to do here
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test for each test method
        test = ExtentReportSetup.getExtent().createTest(result.getMethod().getMethodName());
        System.out.println("Test Started: " + test);
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test passed");
        System.out.print("Test passed " );
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Test failed");
        System.out.print("Test failed " );
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test skipped");
        System.out.print("Test skipped" );
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Nothing to do here
    }
    
    }


