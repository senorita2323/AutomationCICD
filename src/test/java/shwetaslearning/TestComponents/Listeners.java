package shwetaslearning.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import shwetaslearning.resources.ExtentReporterNG;

import java.io.IOException;

public class Listeners extends  BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReporterObject();//calling method using classname.method name
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();//to avoid sync issues during parallel execution of testcases

    @Override
    public void onTestStart(ITestResult iTestResult) {
       test = extent.createTest(iTestResult.getMethod().getMethodName());//retrieving every test case name particularly and creating test entry automatically
        //unique thread id of test,like (error validation-test thread id)
        extentTest.set(test);//set the object in the thread local to avoid sync issues during parallel execution of test cases
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.get().log(Status.PASS,"Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String filePath;
        extentTest.get().fail(iTestResult.getThrowable());//it will give error message
        try {
            driver =(WebDriver) iTestResult.getTestClass().getRealClass().getField("driver").get(iTestResult.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Take Screenshot and attach it to reporter
        try {
            filePath = getScreenShot(iTestResult.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            System.out.println("Issue with file path");
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath,iTestResult.getMethod().getMethodName());//arguments are (filepath and file title)
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
