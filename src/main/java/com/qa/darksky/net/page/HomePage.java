package com.qa.darksky.net.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.qa.darksky.net.base.BasePage;
import com.qa.darksky.net.util.ElementUtil;
import com.qa.darksky.net.util.JavaScriptUtil;

public class HomePage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil javaScriptUtil;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		
	}
	
	By todayClick = By.xpath("//a[@data-day='0']");
	 By temperature = By.xpath("(//div[@class='temps'])[2]//span//span");
	  By lowTemp = By.xpath("//a[@data-day=‘0’]//span[@class=‘minTemp’]");
	  By highTemp = By.xpath("//span[@class='high-temp-text']");
	  By maxTemp = By.xpath("//a[@data-day='0']//span[@class='minTemp']");
	
	
	public String getHomePageTitle() {
		return elementUtil.doGetPageTitle();
	}
	
	public void temperature() {
		WebElement element = driver.findElement(todayClick);

		JavaScriptUtil.scrollIntoView(element, driver);
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(todayClick));
				driver.findElement(todayClick).click();
//				System.out.println(getTempList());
//				System.out.println(getMinTemp());
//				System.out.println(getMaxTemp());
	}
	
	static ArrayList<Integer> tempListInt = new ArrayList<Integer>();
	
	public ArrayList<Integer> getTempList(WebDriver driver){
		//WebElement temperature = driver.findElement(By.xpath("(//div[@class='temps'])[2]//span//span"));
		WebDriverWait wait =  new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(temperature));
		List<WebElement> tempList = driver.findElements(temperature);
		
		for (int i = 0; i < tempList.size(); i++) {
			String tempText = tempList.get(i).getText().substring(0, 2);
			tempListInt.add(Integer.parseInt(tempText));
			
		}
		
		return tempListInt;
	}
	
	public  int minTemp() {
		Collections.sort(tempListInt);
		return tempListInt.get(0);
	}
	
	public  int maxTemp() {
		Collections.sort(tempListInt);
		return tempListInt.get(tempListInt.size()-1);
		
	}
	public int verifyMin() {
		WebElement lowtemp = driver.findElement(lowTemp);
		String lowTempStr =lowtemp.getText().substring(0, 2);
		int lowTempInt = Integer.parseInt(lowTempStr);
		return lowTempInt;
	}
	public int verifyMax() {
		WebElement hightemp = driver.findElement(highTemp);
		String lowTempStr =hightemp.getText().substring(0, 2);
		int highTempInt = Integer.parseInt(lowTempStr);
		return highTempInt;
	
	}

}
