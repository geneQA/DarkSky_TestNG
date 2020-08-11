package com.qa.darksky.net.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;



import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	WebDriver driver;
	Properties prop;
	
	public static boolean highlightelement;
public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	public WebDriver init_driver(String browserName){
		
		highlightelement = prop.getProperty("highlight").equals("yes") ? true : false;
		System.out.println("Browser name is " + browserName);
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();}
		
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();}
		
		else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver= new SafariDriver();	}
		else {
			System.out.println("Browser name " + browserName + "is not found. ");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		return driver;
		
	}
	
	
	public Properties init_properties(){
	 prop= new Properties();
	 
	 String path = "/Users/fatihgene/eclipse-workspace/DarkSky_TESTNG/src/main/java/com/qa/darksky/net/config/config.properties";
	try {
		FileInputStream ip= new FileInputStream(path);
		prop.load(ip);
	} catch (FileNotFoundException e) {
		System.out.println("some issue happened with config properties...Correct config file");
	}
	catch (IOException e) {
		e.printStackTrace();
	}return prop;
	
	
	}
public String getScreenshot() {
		
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+ "/screenshots/" + System.currentTimeMillis()+ ".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.err.println("screenshot captured failed...");
		}
		
		return path;
		
	}

}
