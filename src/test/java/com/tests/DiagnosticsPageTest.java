package com.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pages.DiagnosticsPage;
import com.pages.HomePage;
import com.utils.DriverSetup;

public class DiagnosticsPageTest {
  
	private WebDriver driver;
	private DiagnosticsPage page;
	
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String brower)
	{
	 driver=DriverSetup.getDriver(brower);
	 page=new DiagnosticsPage(driver);
	 DriverSetup.open();
	 
	}
	
	@Test(priority = 0)
	public void navigateToLabTests()
	{
		
		page.navigateToLabTests();
	}
	
	@Test(priority = 1)
	public void getTopCities()
	{
		page.getTopCities();
	}
	
	@AfterClass
	 public void tearDown() {
		    
	    
	     
	     if (driver != null) {
	         driver.quit();
	     }
	 }
	
	
}
