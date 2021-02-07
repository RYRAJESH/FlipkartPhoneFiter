package com.flipkart.phones;

import org.testng.annotations.Test;

import com.pageObjects.FlipkartLogin;
import com.flipkart.library.CommonFunctions;
import com.pageObjects.FlipkartHomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class FilterPhones extends TestReport {

	WebDriver driver;
	FlipkartHomePage homePage;
	FlipkartLogin loginPage;
	CommonFunctions functions;
	Properties property;

	@BeforeClass
	public void beforeClass() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		FileReader reader = new FileReader("src/test/resources/testdata.properties");
		property = new Properties();
		property.load(reader);
		homePage = new FlipkartHomePage(driver);
		loginPage = new FlipkartLogin(driver);
		functions = new CommonFunctions(driver);
		startTest();
		boolean login = functions.login(property.getProperty("url"), property.getProperty("userId"),property.getProperty("password"));
		if(login) {
			logResult_Pass("Login Sucessfull", driver);
		}else {
			logResult_Fail("Login Failed", driver);
		}

	}

	//Below method fetches the iphone with price range upto Rs. 50,000
	@Test(priority=0)
	public void fetchIphones() throws TimeoutException, InterruptedException, IOException {
		String title = driver.getTitle();
		System.out.println("Browser title is "+title);
		try {
		homePage.clickMobileLink();
		homePage.clickElectronicsHeader();
		Thread.sleep(4000);
		homePage.clickIphoneLink();
		Thread.sleep(5000);
		Select maxPriceDropDown = homePage.selectMaxPrice();
		maxPriceDropDown.selectByValue("50000");
		}catch (Exception e) {
			e.printStackTrace();
		}

		FileWriter csvWriter = new FileWriter("iPhones.csv");
		csvWriter.append("iPhone Modal (Size) ");
		csvWriter.append(",");
		csvWriter.append(" Cost ");
		csvWriter.append(",");
		csvWriter.append(" Rating ");
		csvWriter.append("\n");

		for(int i=0;i<homePage.getIphoneList().size();i++) {

			String iPhonePrice = homePage.getIphonePriceList().get(i).getText();

			String iPhoneName = homePage.getIphoneList().get(i).getText();

			int closingBrackerIndex = iPhoneName.indexOf(")");
			String phoneModal = iPhoneName.substring(0, closingBrackerIndex+1);

			String iPhoneCustRating = homePage.getIphoneRating().get(i).getText();

			String filedata = phoneModal+" , "+iPhonePrice+" , "+iPhoneCustRating;

			csvWriter.append(filedata);
			csvWriter.append("\n");
		}
		csvWriter.flush();
		csvWriter.close();			
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.close();
		endResult();
	}

}
