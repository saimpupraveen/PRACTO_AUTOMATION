package com.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pages.CorporateWellnessPage;
import com.pages.HomePage;
import com.utils.DriverSetup;

public class CorporateWellnessPageTest {
 
	private WebDriver driver;
	private CorporateWellnessPage page;
	
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String browser)
	{
	 driver=DriverSetup.getDriver(browser);
	 page=new CorporateWellnessPage(driver);
	 DriverSetup.open();
	 
	}
	
	@Test(priority = 0)
	 public void goToCorporateWellness()
	 {
		 
		page.goToCorporateWellness();
	 }
	
	@Test(priority = 1)
	public void readInputDataFromExcel()
	{
		
		page.fillFormWithInvalidData();
		page.selectDropdowns();
		page.forceClickScheduleButton();
		
	
	}
	
	@Test(priority = 2)
	public void printErrorMessages()
	{
		page.logInvalidFields();

		
	}
	
	
	
	
	@AfterClass
	 public void tearDown() {
		    
	    
	     
	     if (driver != null) {
	         driver.quit();
	     }
	 }
  
}
