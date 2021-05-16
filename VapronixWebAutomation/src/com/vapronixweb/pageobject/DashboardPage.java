package com.vapronixweb.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DashboardPage {
	WebDriver driver;
	@FindBy(xpath="//a[@class='navbar-brand']/img")
	private WebElement logo;
	
	
	
	public void verifyAllHeaderElements() {
		
		Assert.assertTrue(BasePage.isPresentAndDisplayed(logo));
		
	}
	

	public DashboardPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	
	}

}
