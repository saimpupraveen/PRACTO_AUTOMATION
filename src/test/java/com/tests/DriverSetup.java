package com.tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class DriverSetup {
	
	public static WebDriver driver;
	

 @BeforeClass
 public static WebDriver getDriver(String browser) {
		 	 
		 switch (browser.toLowerCase()) {
         case "chrome":
             driver = new ChromeDriver();
             break;
         case "edge":
             driver = new EdgeDriver();
             break;
         default:
             throw new IllegalArgumentException("Unsupported browser: " + browser);
     }
		 
		 driver.manage().window().maximize();
	        return driver;
	    }
 
 
 @AfterClass
 public void tearDown() {
    
     // Capture screenshot on failure
     
     if (driver != null) {
         driver.quit();
     }
 }
	

}
