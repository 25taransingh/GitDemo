package taransinghlearning.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import taransinghlearning.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	//When running test in parallel there will be concurrency issue report shows wrong test failed
	// to overcome we have threadlocal class as mentioned below, also check its object use
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>(); // Thread Safe

	@Override
	public void onTestStart(ITestResult result) {
		// Started entry in report
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);// unique threadID for particular test get assigned

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Print msg
		extentTest.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// below line will give error msg
		extentTest.get().fail(result.getThrowable());

		try {

			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// Screenshot and attach to report.

		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();

	}

}
