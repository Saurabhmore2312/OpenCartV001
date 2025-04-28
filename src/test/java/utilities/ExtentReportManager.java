package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String reportName;
	
	public void onStart(ITestContext testContext ) {
		String tmeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-" + tmeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		sparkReporter.config().setReportName("OpenCart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports(); 
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Subd Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());   // To display the groups in the report
		test.log(Status.PASS, "Test Passed");
		
		test.pass("Test Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // To display the groups in the report
		test.log(Status.FAIL, result.getName() + "got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String screenshotPath = new BaseClass().captureScreenshot(result.getName()); 
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // To display the groups in the report
		test.log(Status.SKIP, result.getName() + "got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());

	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
		
		String reportPath = System.getProperty("user.dir") +"\\reports\\" + reportName;
		File extentReport = new File(reportPath);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
//		try {
//			URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + reportName);
//			
//			//Create email message
//			ImageHtmlEmail email = new ImageHtmlEmail();
//			email.setDataSourceResolver(new DataSourceUrlResolver(url));
//			email.setHostName("smtp.gmail.com");
//			email.setSmtpPort(587);
//			email.setAuthenticator(new DefaultAuthenticator("saurabhmore2126@gmail.com", "jgis ylhl vhcu dxbg"));
//			email.setStartTLSEnabled(true);
//			email.setFrom("saurabhmore2126@gmail.com");
//			email.setSubject("Test Report");
//			email.setMsg("Please find the attached test report.");
//			email.addTo("saurabhmore1223@gmail.com");
//			email.attach(extentReport);
//			email.send();
//			
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
		
	}
	

}
