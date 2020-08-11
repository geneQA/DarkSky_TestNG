package com.qa.darksky.net.test;


	import java.util.List;
	import java.util.Properties;
	
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;
	
	import com.qa.darksky.net.base.BasePage;
	import com.qa.darksky.net.page.HomePage;
	import com.qa.darksky.net.util.AppConstants;

	public class HomePageTest {
		
		WebDriver driver;
		BasePage basePage;
		Properties properties;
		HomePage homePage;
		
		
		@BeforeTest
		
		public void Setup() {
			
			basePage= new BasePage();
			properties=basePage.init_properties();
			String browserName= properties.getProperty("browser");
			driver=basePage.init_driver(browserName);
			driver.manage().deleteAllCookies();
			driver.get(properties.getProperty("url"));
			homePage= new HomePage(driver);
			
			}
		
		@Test(priority=1)
		
		public void clickToday() {
			homePage.temperature();
		}
		
		@Test(priority=2)
		public void verifyHomeaPageTitle() {
			String title= homePage.getHomePageTitle();
			Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
			
		}
		@Test(priority=3)
		public void getTempeList() {
			System.out.println(homePage.getTempList(driver));
			
		}
		@Test(priority=4)
		public void minTempe() {
		System.out.println("Expected minimum temperature : "+homePage.minTemp());
		}
		@Test(priority=5)
		public void maxTempe() {
			System.out.println("Expected maksimum temperature : "+ homePage.maxTemp());
		}
		@Test(priority=6)
		public void verifyMinTempe() {
			homePage.verifyMin();
			Assert.assertEquals(homePage.verifyMin(), homePage.minTemp());
			
			
		}
		@Test(priority=7)
		public void verifyMax() {
			homePage.verifyMax();
			Assert.assertEquals(homePage.verifyMax(), homePage.maxTemp());
		}
		
		
		@AfterTest(enabled=false)
		public void tearDown() {
			driver.close();
		}
		
		
		

}
