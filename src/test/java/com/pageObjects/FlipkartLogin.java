package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartLogin {
	WebDriver driver;
	By userId = By.xpath("//*[@class='_2IX_2- VJZDxU']");
	By password = By.xpath("//*[@class='_2IX_2- _3mctLh VJZDxU']");
	By loginButton = By.xpath("//*[@class='_2KpZ6l _2HKlqd _3AWRsL']");
	By cartIcon = By.xpath("//*[contains(text(),'Cart')]");
	

	
	public FlipkartLogin(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void setUserId(String user) {
		WebElement userid = waitToLoadObj(userId);
		userid.sendKeys(user);
	}
	
	public void setPassword(String pwd) {
		WebElement userPwd = waitToLoadObj(password);
		userPwd.sendKeys(pwd);
	}
	
	public void clickSubmitButton() {
		WebElement loginBttn = waitToLoadObj(loginButton);
		loginBttn.click();
	}
	
	public WebElement getCartIcon() {
		WebElement carticon = waitToLoadObj(cartIcon);
		return carticon;
	}
	

	

	
	public WebElement waitToLoadObj(By by) {
		WebDriverWait wait=new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}
	
}
