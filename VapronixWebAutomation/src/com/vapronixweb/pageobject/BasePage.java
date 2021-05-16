package com.vapronixweb.pageobject;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.asserts.SoftAssert;
import com.vapronixweb.generic.ExcelUtils;
import com.vapronixweb.generic.WaitStatementLib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public abstract class BasePage {
	
	
		public static WebDriver driver;
		
		public static boolean isAlertPresent(WebDriver driver) {
		    try {
		        driver.switchTo().alert();
		        return true;
		    } 
		    catch (Exception e) {
		        return false;
		        
		    } 
		}
		
		
		public static void closeJEETestSeriesPopup(WebDriver driver, WebElement element, WebElement element1){
			if(String.valueOf(BasePage.isPresentAndDisplayed(element))=="true"){
			try{
				WaitStatementLib.explicitWaitForVisiblity(driver, 5, element1);
				element1.click();
				BasePage.sleepForMilliSecond(1000);
			
				}
				catch(ElementNotVisibleException ex){
					
				}
				catch(TimeoutException ex){
			
				}
			}
		}
		
		public static void scrollDown(WebElement element, WebDriver driver){
			((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView(true);", element);

		}
		
		public static String randomString() 
		{
			int leftLimit = 97;
			int rightLimit = 122; 
			int targetStringLength = 4;
			Random random = new Random();
			StringBuilder buffer = new StringBuilder(targetStringLength);
			for (int i = 0; i < targetStringLength; i++) {
				int randomLimitedInt = leftLimit + (int) 
						(random.nextFloat() * (rightLimit - leftLimit + 1));
				buffer.append((char) randomLimitedInt);
			}
			String generatedString = buffer.toString();
			return (generatedString);
		}
		public static int randomNumber()
		{
			int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
			return(randomNum);
		}
		
		public static void focusElement(WebElement element, WebDriver driver)
		{
		 
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		}
		
		public static void selectDrpdwnValue(WebElement element, String value)
		{
			Select sel = new Select(element);
			sel.selectByValue(value);
		}
		
		public static void selectDrpdwnByVisibleText(WebElement element, String text)
		{
			Select sel = new Select(element);
			sel.selectByVisibleText(text);;
		}
		public static void selectUsingOption(WebElement element, String text)
		{
			element.click();
			element.getAttribute(text);
			
		}
		public static void elementDisplay(WebElement element, String text)
		{
			element.isDisplayed();
		}
		
		
		public static void sleepForMilliSecond(int time)  
		{
			try{
				Thread.sleep(time);
			}
			catch(InterruptedException ex){
				
			}
		}
		public static void switchToWindow(WebDriver driver, List<WebElement> element)
		{
			element.get(0).click();
			try{
				Set <String> allWindow = driver.getWindowHandles();
				Iterator<String> itr = allWindow.iterator();
				List<String> myl = new ArrayList<String>();
				for (int i =0;i<allWindow.size();i++)
				{
					myl.add(itr.next());
				}
				driver.switchTo().window(myl.get(1));
				//BasePage.sleepForMilliSecond(2000);
				}
			catch(RuntimeException ex){
				
			}
		}
		public static void switchToParentWindow(WebDriver driver){
			
			try{
				Set <String> allWindow = driver.getWindowHandles();
				Iterator<String> itr = allWindow.iterator();
				List<String> myl = new ArrayList<String>();
				
				for (int i =0;i<allWindow.size();i++){
					myl.add(itr.next());
				}
				driver.switchTo().window(myl.get(0));
				}
			catch(RuntimeException ex){
				
			}
		}
		public static boolean isPresentAndDisplayed(final WebElement element) {
			  try {
			    return element.isDisplayed();
			  } catch (NoSuchElementException e) {
			    return false;
			  }
			}
		public static boolean isPresentAndDisplayed(final MobileElement element) {
			  try {
			    return element.isDisplayed();
			  } catch (NoSuchElementException e) {
			    return false;
			  }
			}
		public static boolean isSelected(WebElement element, String className) {
		    return element.getAttribute("class").contains(className);
		}
		

		public static void verifyCourseMenuContent(List<WebElement> element){
			for (int i=0;i<element.size();i++)
			{
				String visibledata = element.get(i).getText().toString();
				for (int j=4;j<=15;j++)
				{
					String data = ExcelUtils.readData("TestData", j, 1).toString();
					if(data.equals(visibledata)){
						break;
					}
					else{
						continue;
					}
				}
			}
		}
		
		public static void verifyCourseMenuContentforAllClasses(List<WebElement> element, int k){
			for (int i=0;i<element.size();i++)
			{
				String visibledata = element.get(i).getText().toString();
				for (int j=4;j<=15;j++)
				{
					String data = ExcelUtils.readData("TestData", j, k).toString();
					if(data.equals(visibledata)){
						break;
					}
					else{
						continue;
					}
				}
			}
		}
		public static void verifyCourseMenuContentforAllClassesSPecificUsers(WebElement element1,List<WebElement> element, int k){
			Assert.assertTrue((element1.getText().toString()).equals(ExcelUtils.readData("TestData", 3, k).toString()));
			for (int i=0;i<element.size();i++)
			{
				String visibledata = element.get(i).getText().toString();
				for (int j=4;j<=15;j++)
				{
					String data = ExcelUtils.readData("TestData", j, k).toString();
					if(data.equals(visibledata)){
						break;
					}
					else{
						continue;
					}
				}
			}
		}
		
		
		
		public static void verifyStudyMenuContent(List<WebElement> element){
			for (int i=0;i<element.size();i++)
			{
				String visibledata = element.get(i).getText().toString();
			
				for (int j=15;j<25;j++)
				{
					String data = ExcelUtils.readData("TestData", j, 1).toString();
				
					if(data.equalsIgnoreCase(visibledata)){
						break;
					}
					else{
						continue;
					}
				}
			}
		}
		public static void verifyStudyMenuContentForAllClasses(List<WebElement> element, int k){
			for (int i=0;i<element.size();i++)
			{
				String visibledata = element.get(i).getText().toString();
			
				for (int j=15;j<25;j++)
				{
					String data = ExcelUtils.readData("TestData", j, k).toString();
				
					if(data.equalsIgnoreCase(visibledata)){
						break;
					}
					else{
						continue;
					}
				}
			}
		}
		
		public static void verifyAnaMenuContent(List<WebElement> element){
			for (int i=0;i<element.size();i++)
			{
				String visibledata = element.get(i).getText().toString();
				for (int j=22;j<26;j++)
				{
					String data = ExcelUtils.readData("TestData", j, 1).toString();
					if(data.equalsIgnoreCase(visibledata)){
						break;
					}
					else{
						continue;
					}
				}
			}
		}
		
		public static void verifyMeMenuContent(List<WebElement> element){
			for (int i=0;i<element.size();i++)
			{
				String visibledata = element.get(i).getText().toString();
				for (int j=26;j<35;j++)
				{
					String data = ExcelUtils.readData("TestData", j, 1).toString();
					if(data.equalsIgnoreCase(visibledata)){
						break;
					}
					else{
						continue;
					}
				}
			}
		}
		
		public static boolean checkLogoutBox(final WebElement element) {
			  try {
			    return element.isDisplayed();
			  } catch (NoSuchElementException e) {
			    return false;
			  }
			  
			}
		
		public static void moveToElementAndClick(WebDriver driver, WebElement toElement, WebElement toElement1){
			Actions act = new Actions(driver);
			act.moveToElement(toElement).moveToElement(toElement1).click().build().perform();
		}
		public static void moveToElementAndClick(WebDriver driver, WebElement toElement){
			Actions act = new Actions(driver);
			act.moveToElement(toElement).click().build().perform();
		}
		public static void moveToElementAndStay(WebDriver driver, WebElement toElement){
		
			Actions act = new Actions(driver);
			act.moveToElement(toElement).build().perform();
		}
		public static void pressEscapeKey(WebDriver driver){
			
			try {
				Robot r = new Robot();
				r.keyPress(java.awt.event.KeyEvent.VK_ESCAPE); 
				r.keyRelease(java.awt.event.KeyEvent.VK_ESCAPE);
				
			} catch (AWTException e) {
				
				e.printStackTrace();
			} 
		}
		public static void pressHomeKey(WebDriver driver){
			
			try {
				Robot r = new Robot();
				r.keyPress(java.awt.event.KeyEvent.VK_HOME); 
				r.keyRelease(java.awt.event.KeyEvent.VK_HOME);
				
			} catch (AWTException e) {
				
				e.printStackTrace();
			} 
		}
	public static void pressUpArrowKey(WebDriver driver){
			
			try {
				Robot r = new Robot();
				r.keyPress(java.awt.event.KeyEvent.VK_KP_UP); 
				r.keyRelease(java.awt.event.KeyEvent.VK_KP_UP);
				
			} catch (AWTException e) {
				
				e.printStackTrace();
			} 
		}
		public static void usingSoftAssert(boolean condition){
			SoftAssert sf = new SoftAssert();
			sf.assertTrue(condition);
		}
		
		public static void copy(File sourceLocation, File targetLocation) {
		    if (sourceLocation.isDirectory()) {
		    
		    	try {
					copyDirectory(sourceLocation, targetLocation);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		    } else {
		    	try {
					copyFile(sourceLocation, targetLocation);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		    }
		}
		private static void copyDirectory(File source, File target) throws IOException {
		    if (!target.exists()) {
		        target.mkdir();
		    }

		    for (String f : source.list()) {
		        copy(new File(source, f), new File(target, f));
		    }
		}

		private static void copyFile(File source, File target) throws IOException {        
		    try (
		            InputStream in = new FileInputStream(source);
		            OutputStream out = new FileOutputStream(target)
		    ) {
		        byte[] buf = new byte[1024];
		        int length;
		        while ((length = in.read(buf)) > 0) {
		            out.write(buf, 0, length);
		        }
		    }
		}
		private static void addAttachment(Multipart multipart, String filename)
		{
		    DataSource source = new FileDataSource(filename);
		    BodyPart messageBodyPart = new MimeBodyPart();        
		    try {
				messageBodyPart.setDataHandler(new DataHandler(source));
			} catch (MessagingException e) {
			
				e.printStackTrace();
			}
		    try {
				messageBodyPart.setFileName(filename);
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}
		    try {
				multipart.addBodyPart(messageBodyPart);
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}
		}
		
		
		public static void sendEmail(){
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("anup.kumar@meritnation.com","Anup@12345");
					}
				});
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("anup.kumar@meritnation.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse("enggqa@meritnation.com"));
				message.setRecipients(Message.RecipientType.CC,
						InternetAddress.parse("anup.kumar@meritnation.com"));
				message.setSubject("Selenium Automation Report");
				//message.setContent("Dear All", "text/html; charset=utf-8");
				BodyPart messageBodyPart = new MimeBodyPart();
				//messageBodyPart.setText("test");
			    //messageBodyPart.setContent("Test", "text/html");
				messageBodyPart.setContent("Dear All", "text/html; charset=utf-8");
				Multipart multipart = new MimeMultipart();
		        String filename = "/var/www/mn-testing/Meritnation/Customized/Main Test Suite/Meritnation Mobile Web Regression.html";
		        String filename1 = "/var/www/mn-testing/Meritnation/Customized/Main Test Suite/Meritnation Web Regression.html";
			    DataSource source = new FileDataSource(filename1);
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        messageBodyPart.setFileName(filename1);
		        multipart.addBodyPart(messageBodyPart);
		        addAttachment(multipart, filename);
		        message.setContent(multipart);
		        Transport.send(message);
				} 
				catch (MessagingException e) 
				{
				throw new RuntimeException(e);
				}
			}
		public static void closeLocationPopupinMobileSite(AppiumDriver<MobileElement> _driver, MobileElement element){
			try{
			String webContext = _driver.getContext();
			    Set<String> contexts = _driver.getContextHandles();
			    for (String context: contexts){
			        if (context.contains("NATIVE_APP")){
			            _driver.context(context);
			            break;
			        }
			    }
			    element.click();
			    _driver.context(webContext);
			}
			catch(NoSuchElementException ex){
				
			}
		}
	/*	public static void presssAndroidBackButton(WebDriver _driver){
			_driver.pressKey(new KeyEvent(AndroidKey.BACK));
		} */
		
		public static void scrollToExactElement(String str) {
	        ((AndroidDriver<MobileElement>)driver).findElementByAndroidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
	                        + str + "\").instance(0))").click();
	    }
		
		public static void selectElementByText(WebDriver driver, String inputText) {
			
			WebElement selectedElement = driver.findElement(By.xpath("//span[text()='"+inputText+"']"));
			
			selectedElement.click();
			
		}
		
		public static void clickUsingJavaScriptExecutor(WebDriver driver, String clickableElement) {
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", clickableElement);
		}
		
		
		
		public BasePage (WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		public static void scrollDownPage(WebDriver driver) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");

		}
		
		public static void printText(String text) {
			System.out.println(text);
			
		}

}
