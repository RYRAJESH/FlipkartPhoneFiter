package com.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartHomePage {
	
	WebDriver driver;
	By headerElectronics = By.xpath("//*[contains(text(),'Electronics')]");
	By iPhoneLink = By.xpath("//*[contains(text(),'iPhone SE')]");
	By maxAmountDropDown = By.xpath("(//select[@class='_2YxCDZ'])[2]");
	By mobileLink = By.xpath("//*[@class='xtXmba' and contains(text(),'Mobiles')]");
	By maxPriceDrpDwn = By.xpath("//*[@class='_3uDYxP']/select");
	By iphoneModalList = By.xpath("//*[@class='_4rR01T']");
	By iphonePriceList = By.xpath("//*[@class='_30jeq3 _1_WHN1']");
	By iphoneRating = By.xpath("//*[@class='_2_R_DZ']/span/span[1]");
	
	
	public FlipkartHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickIphoneLink() {
		WebElement phonelink = waitToLoadObj(iPhoneLink);
		phonelink.click();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", driver.findElement(iPhoneLink));
	}
	
	public void clickMobileLink() {
		WebElement mobile = waitToLoadObj(mobileLink);
		mobile.click();
	}
	
	public void clickElectronicsHeader() {
		WebElement electronics = waitToLoadObj(headerElectronics);
		electronics.click();
	}
	
	public Select selectMaxPrice() {
		WebElement maxdropdown = waitToLoadObj(maxPriceDrpDwn);
		Select dropdown = new Select(maxdropdown);
		return dropdown;
	}
	
	public List<WebElement> getIphoneList() {
		WebElement iphoneNameList = waitToLoadObj(iphoneModalList);
		List<WebElement> iphoneNames = driver.findElements(iphoneModalList);
		return iphoneNames;
	}
	
	public List<WebElement> getIphonePriceList() {
		WebElement iphonePriceLists = waitToLoadObj(iphonePriceList);
		List<WebElement> iphonePrices = driver.findElements(iphonePriceList);
		return iphonePrices;
	}
	
	public List<WebElement> getIphoneRating() {
		WebElement iphoneratings = waitToLoadObj(iphoneRating);
		List<WebElement> iphoneRate = driver.findElements(iphoneRating);
		return iphoneRate;
	} 
	
	public WebElement waitToLoadObj(By by) {
		WebDriverWait wait=new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}
}
