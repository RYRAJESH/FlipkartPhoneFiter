package com.flipkart.phones;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestReport {

	static ExtentReports reports;
	static ExtentTest tests;

	public static void startTest() {
		reports = new ExtentReports(System.getProperty("üser.dir")+"\\Results.html");
		tests = reports.startTest("Demo");
	}

	public static void logResult_Pass(String desc,WebDriver driver) throws IOException {
		tests.log(LogStatus.PASS, tests.addScreenCapture(capture(driver)) ,desc);
	}

	public static void logResult_Fail(String desc,WebDriver driver) throws IOException {
		tests.log(LogStatus.FAIL,tests.addScreenCapture(capture(driver)), desc);
	}
	
	public static void endResult() {
		reports.endTest(tests);
		reports.flush();
		reports.close();
	}
	
	public static String capture(WebDriver driver) throws IOException {
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destfile = new File(System.getProperty("user.dir")+"\\test-output\\error.png");
		String errflpath = destfile.getAbsolutePath();
		FileUtils.copyFile(srcfile, destfile);
		return errflpath;
	}

}
