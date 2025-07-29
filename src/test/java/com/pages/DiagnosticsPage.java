package com.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.WriteToExcel;





public class DiagnosticsPage {
	
	
	private WebDriver driver;
	private WebDriverWait wait;
	private static final Logger logger = LogManager.getLogger(DiagnosticsPage.class);
	

	public DiagnosticsPage(WebDriver driver) {
		super();
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		logger.info("DiagnosticsPage initialized with implicit wait.");
	}
	
	  public  void navigateToLabTests() {
	        try {
	        	logger.info("Navigating to Surgeries section.");
	            WebElement surgeries = driver.findElement(By.xpath("//div[text()='Surgeries']"));
	            surgeries.click();

	            logger.info("Navigating to Lab Tests section.");
	            WebElement labTests = driver.findElement(By.xpath("//div[text()='Lab Tests']"));
	            labTests.click();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    // Extract top cities listed under Diagnostics
	    public  void getTopCities() {
          logger.info("Extracting top cities under Diagnostics.");
	        List<WebElement> cities = driver.findElements(By.cssSelector("ul > li > div.u-margint--standard"));
	        List<String> cityNames = cities.stream().map(WebElement::getText).collect(Collectors.toList());

	        WriteToExcel.log("TestCase2_TopCities", "Top Cities:");
	        cityNames.forEach(city -> {
	        WriteToExcel.log("TestCase2_TopCities", city);
	        logger.info("City found: " + city);
	       
	        });


	    }
	

}
