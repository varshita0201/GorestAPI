package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports implements ITestListener{
	public ExtentSparkReporter rep;
	public ExtentReports extent;
	public ExtentTest etest;
	
	public void onStart(ITestContext context) {
		String time=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="TestReport"+time+".html";
		String repPath=System.getProperty("user.dir")+"/Reports/"+repName;
		rep=new ExtentSparkReporter(repPath);
		rep.config().setReportName("Gorest Results");
		rep.config().setDocumentTitle("GorestUser");
		extent=new ExtentReports();
		extent.attachReporter(rep);
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("tested by", "Varshita");
		etest=extent.createTest("GorestUser Test");
	}

	public void onTestFailure(ITestResult result) {
		etest.fail("Test Failed:"+result.getName());
	}
	
	public void onTestSuccess(ITestResult result) {
		etest.pass("Test passed:"+result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		etest.skip("Test Skipped:"+result.getName());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
