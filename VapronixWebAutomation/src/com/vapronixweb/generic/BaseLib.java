package com.vapronixweb.generic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.vapronixweb.pageobject.DashboardPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager; 

public class BaseLib {

	public WebDriver driver;
	public static AppiumDriver<MobileElement> _driver;
	
	// ----------All newly created page classes needs to be defined here and should be initialized into respective BeforeMethods------------
	protected DashboardPage dashboardPage;
	
	
	
	
	
	
	@BeforeMethod(alwaysRun =false, groups = {"Regression"})
	@Parameters({"browser"})
	public void preConditionWeb(String browsername){
		 final Logger logger = LogManager.getLogger(BaseLib.class);
		 logger.trace("entering into application");
		 
		 if(browsername.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "Exe Files/geckodriver");
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("dom.webnotifications.enabled", false);
			profile.setPreference("geo.enabled", false);
			
			String Node = "http://10.0.2.83:6666/wd/hub";
	 		DesiredCapabilities cap = DesiredCapabilities.firefox();
	 
	 		try {
				driver = new RemoteWebDriver(new URL(Node), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
		//	driver = new FirefoxDriver(profile);
			Reporter.log("Firefox Browser launches");
		}
		else if (browsername.equalsIgnoreCase("chrome")  )
		{
		//	WebDriverManager.chromedriver().version("71.0.3578.80").setup();
			System.setProperty("webdriver.chrome.driver", "Exe Files/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
	                  UnexpectedAlertBehaviour.IGNORE);
			options.addArguments("disable-geolocation");
			options.addArguments("--disable-notifications");
			options.addArguments("--no-sandbox");
			
			
			/*	String Node = "http://10.0.2.83:6666/wd/hub";
	 		DesiredCapabilities cap = DesiredCapabilities.chrome();
	 		cap.setPlatform(Platform.LINUX);
	 		cap.setVersion("71.0.3578.80");
	 
	 		try {
				driver = new RemoteWebDriver(new URL(Node), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}*/
			
			driver = new ChromeDriver(options);
			Reporter.log("Chrome Browser launches");
		}
		else if (browsername.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", ".\\Exe files\\IEdriverServer.exe");
			driver = new InternetExplorerDriver();
			Reporter.log("IE Browser launches");
		}
			driver.manage().window().maximize();
			
			driver.get(GetPropertyValues.getPropertyValue("holaQuiz"));
			Reporter.log("Navigate to the URL", true);
			WaitStatementLib.implicitWaitForSecond(driver, 5);
			
			
			dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
			
			
		/*
		 * MobileAskAnswerPage tts= new MobileAskAnswerPage(_driver);
		 * AppiumFieldDecorator ddcc = new AppiumFieldDecorator(_driver);
		 * 
		 * mobileAskAnserPage = PageFactory.initElements(new
		 * AppiumFieldDecorator(_driver), this);
		 * 
		 * 
		 * mobileAskAnserPage = PageFactory.initElements(new AppiumDriver(new
		 * URL("http://127.0.0.1:4723/wd/hub")), MobileAskAnswerPage.class);
		 * 
		 */
			
			
			
			
			
			
			
			
			
			 
		}
	
	
	
	

	@BeforeMethod(alwaysRun =false, groups = "CurrentTask")
	@Parameters({"browser"})
	public void precondition(String browsername){
		final Logger logger = LogManager.getLogger(BaseLib.class);
		 logger.trace("entering into application");
		 
		if(browsername.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "Exe Files/geckodriver");
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("dom.webnotifications.enabled", false);
			profile.setPreference("geo.enabled", false);
		/*	String Node = "http://10.0.2.83:6666/wd/hub";
	 		DesiredCapabilities cap = DesiredCapabilities.firefox();
	 
	 		try {
				driver = new RemoteWebDriver(new URL(Node), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}*/
			
		//	driver = new FirefoxDriver(profile);
			Reporter.log("Firefox Browser launches");
		}
		else if (browsername.equalsIgnoreCase("chrome"))
		{
		//	WebDriverManager.chromedriver().version("75.0.3770.80").setup();
			
			System.setProperty("webdriver.chrome.driver", "Exe Files/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-geolocation");
			options.addArguments("--disable-notifications");
			options.addArguments("--no-sandbox"); 
		//	options.addArguments("--headless");
	        options.setExperimentalOption("useAutomationExtension", false);
	     //   options.addArguments("--start-maximized"); // open Browser in maximized mode
	        options.addArguments("disable-infobars"); // disabling infobars
	        options.addArguments("--disable-extensions"); // disabling extensions
	        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
	     
			
		/*	String Node = "http://"+GetPropertyValues.getPropertyValue("ashishIp")+":1234/wd/hub";
	 		DesiredCapabilities cap = DesiredCapabilities.chrome();
	 		cap.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
	 		cap.merge(options);
	 		try {
				driver = new RemoteWebDriver(new URL(Node), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} */
			
			driver = new ChromeDriver(options);
			Reporter.log("Chrome Browser launches");
		}
		else if (browsername.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", ".\\Exe files\\IEdriverServer.exe");
			driver = new InternetExplorerDriver();
			Reporter.log("IE Browser launches");
		}
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(1024,768));;
		//driver.manage().window().setSize(new Dimension (412, 732));
		driver.navigate().to(GetPropertyValues.getPropertyValue("liveURL"));
	//	driver.get(GetPropertyValues.getPropertyValue("liveURL"));
		Reporter.log("Navigate to the URL", true);
		WaitStatementLib.implicitWaitForSecond(driver, 5);
		
		
		dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		
	} 
	@BeforeMethod(alwaysRun = false, groups = "MainApp")
	public void preConditionMainApp() throws InterruptedException, MalformedURLException{
		 File appDir = new File("Apps/");
		 File app = new File(appDir, "86.apk");
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		 capabilities.setCapability("deviceName", GetPropertyValues.getPropertyValue("galaxyOn5ProUDIDDeviceName"));
		 capabilities.setCapability("platformVersion", GetPropertyValues.getPropertyValue("galaxyOn5ProOSName"));
		 capabilities.setCapability("platformName", "Android");
		 capabilities.setCapability("app", app.getAbsolutePath());
		 capabilities.setCapability("appPackage", "com.xxx.school");
		 capabilities.setCapability("appActivity", "com.xxx.school.modules.app_init_auth.controller.SplashActivity");
		 capabilities.setCapability("noReset","true");
		 
		 capabilities.setCapability("automationName" , "UiAutomator2");
		 driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		try{
		//	MainAppLoginPage mainAppLoginPage = new MainAppLoginPage(driver);
			Thread.sleep(5000);
		//	mainAppLoginPage.validLogin();
			Thread.sleep(10000);
		//	mainAppLoginPage.clickIAmStudent();
		}
		catch(NoSuchElementException e){
			
		}
		
		 
		} 
	
	
	 
	@BeforeSuite(alwaysRun=true)
	
	
	public void deleteOutputDirectory(){
		
		String filepath = "test-output";
		try {
			FileUtils.deleteDirectory(new File(filepath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	public void preConditionExpertApp()
	{
		try
		{ 
		 File appDir = new File("Apps/");
		 File app = new File(appDir, "86.apk");
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		 capabilities.setCapability("deviceName", GetPropertyValues.getPropertyValue("anupPhoneName"));
		 capabilities.setCapability("platformVersion", GetPropertyValues.getPropertyValue("anupPhoneOSName"));
		 capabilities.setCapability("platformName", "Android");
		 capabilities.setCapability("app", app.getAbsolutePath());
		 capabilities.setCapability("appPackage", "com.xyz.mn_expert");
		 capabilities.setCapability("appActivity", "com.xyz.chat.modules.dashboard.controller.DashboardActivity");
		 capabilities.setCapability("noReset","true");
		 capabilities.setCapability("automationName" , "UiAutomator2");
		 driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		try{
		//	MainAppLoginPage mainAppLoginPage = new MainAppLoginPage(driver);
			Thread.sleep(5000);
		//	mainAppLoginPage.validLogin();
			Thread.sleep(10000);
		//	mainAppLoginPage.clickIAmStudent();
		}
		catch(NoSuchElementException ex){
			
		}
	}
		catch(InterruptedException | MalformedURLException e){
			
		}
	}
	
	
	@AfterMethod(alwaysRun = false, groups = "MainApp")
	public void postCondition(){
		driver.quit();
	
	}
	
	
	//	driver.manage().window().setSize(new Dimension (412, 732));
	//	driver.get(GetPropertyValues.getPropertyValue("MobileURL"));
	//	Reporter.log("Navigate to the URL", true);
	//	WaitStatementLib.implicitWaitForSecond(driver, 5);
		
	
	
	
	
	
	/*
@BeforeGroups(alwaysRun =true, groups = "tests")
	
	public void testSetUpForMobileSiteRegression(){
		System.out.println("sakkdjsdnsn");
		System.setProperty("webdriver.chrome.driver", "Exe Files/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-geolocation");
		options.addArguments("--disable-notifications");
		options.addArguments("--no-sandbox");
		driver = new ChromeDriver(options);
		Reporter.log("Chrome Browser launches");
		driver.manage().window().maximize();
		driver.navigate().to("https://m.meritnation.com/cbse-class-12-science/chemistry/chemistry-part-i/study/46_5_17");
		//driver.get(GetPropertyValues.getPropertyValue("liveURL"));
		Reporter.log("Navigate to the URL", true);
		WaitStatementLib.implicitWaitForSecond(driver, 5);
	}

@BeforeGroups(alwaysRun =true, groups = "Regression")
public void testSetUpForFullRegression(){
	System.out.println("sakkdjsdnsn");
	System.setProperty("webdriver.chrome.driver", "Exe Files/chromedriver");
	ChromeOptions options = new ChromeOptions();
	options.addArguments("disable-geolocation");
	options.addArguments("--disable-notifications");
	options.addArguments("--no-sandbox");
	driver = new ChromeDriver(options);
	Reporter.log("Chrome Browser launches");
	driver.manage().window().maximize();
	driver.get(GetPropertyValues.getPropertyValue("liveURL"));
	Reporter.log("Navigate to the URL", true);
	WaitStatementLib.implicitWaitForSecond(driver, 5);
}
	
*/
		
	@AfterMethod(alwaysRun =false, groups = "CurrentTask")
	public void postConditionCurrentTask(ITestResult result)
	{
		if (result.isSuccess())
		{
				Reporter.log("Script passed",true);
		}
		else
		{
		    Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		    String browserName = cap.getBrowserName().toLowerCase().toString();
			String filename = result.getMethod().getMethodName();
			SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");  
			Date date = new Date();
			String chromeFolder = "Screenshot/Chrome/" + formatter.format(date);  
			File file1 = new File(chromeFolder);
			if(!file1.exists()){
				file1.mkdir();
			}
			ScreenShotLib sLib= new ScreenShotLib();
			sLib.getScreenShot(driver, filename, browserName, chromeFolder);
			Reporter.log(filename + " has beeen failed and Screenshot has been taken",true);
			
		}
		//driver.quit();
		Reporter.log("Browser closed",true);
	}
	
	

	@AfterMethod(alwaysRun =false, groups = {"Regression"})
	public void postCondition(ITestResult result)
	{
		if (result.isSuccess())
		{
				Reporter.log("Script passed",true);
		}
		else
		{
		    Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		    String browserName = cap.getBrowserName().toLowerCase().toString();
			String filename = result.getMethod().getMethodName();
			SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");  
			Date date = new Date();
			String chromeFolder = "Screenshot/Chrome/" + formatter.format(date);  
			File file1 = new File(chromeFolder);
			if(!file1.exists()){
				file1.mkdir();
			}
			ScreenShotLib sLib= new ScreenShotLib();
			sLib.getScreenShot(driver, filename, browserName, chromeFolder);
			Reporter.log(filename + " has beeen failed and Screenshot has been taken",true);
		}
		driver.quit();
		Reporter.log("Browser closed",true);
	}
	
	@AfterMethod(alwaysRun =false, groups = "MobileRegression")
	public void postConditionMobile(ITestResult result)
	{
		if (result.isSuccess())
		{
				Reporter.log("Script passed",true);
		}
		else
		{
			Capabilities cap = ((RemoteWebDriver) _driver).getCapabilities();
		    String browserName = cap.getBrowserName().toLowerCase().toString();
			String filename = result.getMethod().getMethodName();
			SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");  
			Date date = new Date();
			String chromeFolder = "Screenshot/Chrome/" + formatter.format(date);  
			File file1 = new File(chromeFolder);
			if(!file1.exists()){
				file1.mkdir();
			}
			ScreenShotLib sLib= new ScreenShotLib();
			sLib.getScreenShot(_driver, filename, browserName, chromeFolder);
		   
			Reporter.log(filename + " has beeen failed and Screenshot has been taken",true);
		}
		_driver.quit();
		Reporter.log("Browser closed",true);
	}
	
	

	/*@AfterSuite(alwaysRun=true)
	public static void sendEmail()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");  
		Date date = new Date();
		File srcFile = new File("test-output");
		String filepath = "Customized/"+ formatter.format(date);
		File file = new File(filepath);
		if(!file.exists()){
			file.mkdir();
			
		}
		File destFile = new File(filepath);
			for (String f : srcFile.list()) {
		        BasePage.copy(new File(srcFile, f), new File(destFile, f));
		    }*/
		//	BasePage.sendEmail();


	
	
}
