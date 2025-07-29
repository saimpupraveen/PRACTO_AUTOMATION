package com.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.pages.HomePage;


public class DriverSetup {
	
	public static WebDriver driver;
	
	 private static final Logger logger = LogManager.getLogger(DriverSetup.class);
	


 public static WebDriver getDriver(String browser) {
	 
		 	 
		 switch (browser.toLowerCase()) {
         case "chrome":
             driver = new ChromeDriver();
             break;
         case "edge":
             driver = new EdgeDriver();
             break;
         default:
        	 driver = new ChromeDriver();
     }
		 
		 driver.manage().window().maximize();
	        return driver;
	    }
 
 public static void open() {
     driver.get("https://www.practo.com/");
     driver.manage().window().maximize();
     logger.info("Opened Practo homepage.");
     
 }
 


	

}
