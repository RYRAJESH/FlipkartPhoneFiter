package com.flipkart.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pageObjects.FlipkartHomePage;
import com.pageObjects.FlipkartLogin;

public class CommonFunctions {
	WebDriver driver;
	FlipkartHomePage homePage;
	FlipkartLogin loginPage;
	
	public CommonFunctions(WebDriver driver) {
		this.driver = driver;
		homePage= new FlipkartHomePage(driver);
		loginPage = new FlipkartLogin(driver);
	}
	
	public boolean login(String url, String user, String pwd) {
		driver.get(url);
		driver.manage().window().maximize(); 
		loginPage.setUserId(user);
		loginPage.setPassword(pwd);
		loginPage.clickSubmitButton();
		
		if(loginPage.getCartIcon().isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
