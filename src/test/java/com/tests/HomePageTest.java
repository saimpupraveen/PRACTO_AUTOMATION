package com.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.pages.HomePage;
import com.utils.DriverSetup;

public class HomePageTest {
	
	
	
	private WebDriver driver;
	private HomePage homePage;
	
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String browser)
	{
	 driver=DriverSetup.getDriver(browser);
	 homePage=new HomePage(driver);
	 DriverSetup.open();
	 
	}
	
	
	@Test(priority = 1)
	@Parameters("city")
	public void searchHospitals(String city)
	{
		homePage.searchhospital(city);
	}
	
	@Test(priority = 2)
	public   void filterHospitals()
	{
		homePage.filterHospitals();
	}
	
	@AfterClass
	 public void tearDown() {
		    
	    
	     
	     if (driver != null) {
	         driver.quit();
	     }
	 }
  
}
