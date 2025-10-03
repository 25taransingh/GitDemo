package taransinghlearning.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		
		String Path =System.getProperty("user.dir")+"//reports//index.HTML";
		ExtentSparkReporter reporter =new ExtentSparkReporter(Path);
		reporter.config().setReportName("Web Automation");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Taran Singh");
		
		return extent;
		// below mentioned line start making entry of test in report so it is not possible to write it down every test
		//hence we use dynamically by implementing interface ItestListerns 
		//extent.createTest(Path);
		//we removed this line here and wrote it in Listerners file
		
	}
}
