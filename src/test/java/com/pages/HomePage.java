package com.pages;

import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.WriteToExcel;





public class HomePage {
	
	private static final Logger logger = LogManager.getLogger(HomePage.class);

	private WebDriver driver;
	private WebDriverWait wait;
	private  String mainWindow;
	List<String> filteredHospitals = new ArrayList<>();

	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
		logger.info("Homepage initialized.");
	}
	
	

	 
	 public  void searchhospital(String city) {
	        try {
	            WriteToExcel.log("TestCase1_HospitalFilter", "Searching for hospitals in: " + city);
	            logger.info("Searching for hospitals in: " + city);
	           
	        	mainWindow = driver.getWindowHandle();
	         wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	            WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search location']")));
	            cityInput.clear();
	            
	            cityInput.sendKeys(city);
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'" + city + "')]"))).click();

	            WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search doctors, clinics, hospitals, etc.']")));
	            search.clear();
	            search.sendKeys("Hospital");
	            Thread.sleep(2000);
	            WebElement hospitalOption = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("(//div[@id='c-omni-container']//div[contains(@class, 'c-omni-suggestion-item__content__title')])[4]")));
	            new Actions(driver).moveToElement(hospitalOption).click().perform();
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='line-1']")));
	        } catch (Exception e) {
	        	WriteToExcel.log("TestCase1_HospitalFilter", "Error during search: " + e.getMessage());
	            logger.error("Error during search: " + e.getMessage(), e);
	         
	        	
	        }
	    }
	 
	 
	 public   void filterHospitals() {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        List<WebElement> hospitals;
	       String  mainWindow = driver.getWindowHandle();
	        try {
	                js.executeScript("window.scrollBy(0, 1000)");
	                hospitals = driver.findElements(By.xpath("//h2[@class='line-1']")); 
	                for(WebElement hos:hospitals)
	                {
	                	  WriteToExcel.log("TestCase1_HospitalFilter", " hospital name: " + hos.getText());
	                        logger.info(" hospital name: " + hos.getText());
	                }
	        } catch (Exception e) {
	        	WriteToExcel.log("TestCase1_HospitalFilter", "Scrolling error: " + e.getMessage());
	            logger.error("Scrolling error: " + e.getMessage(), e);
	        }

	        List<WebElement> hospitals247 = driver.findElements(By.xpath("//span[contains(text(),'Open 24x7')]//ancestor::div[2]//h2[@class='line-1']"));
	        WriteToExcel.log("TestCase1_HospitalFilter", "filtered hospitals list ");
            logger.info("filtered hospitals list");
	        for (WebElement hospital : hospitals247) {
	            String name = hospital.getText();
	            try {
	                hospital.click();
	                Set<String> windows = driver.getWindowHandles();
	                for (String handle : windows) {
	                    if (!handle.equals(mainWindow)) {
	                        driver.switchTo().window(handle);
	                        break;
	                    }
	                }

	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bubbles")));
	                WebElement ratingElement = driver.findElement(By.xpath("//span[@class='common__star-rating__value']"));
	              
	                double rating = Double.parseDouble(ratingElement.getText());

	                if (rating > 3.5) {
	                    WebElement readMore = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Read more info']")));
	                    readMore.click();

	                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Amenities')]")));
	                    boolean hasParking = driver.findElements(By.xpath("//span[contains(text(),'Parking')]")).size() > 0;
	                   
	                    if (hasParking) {
	                    	 filteredHospitals.add(name);  
	                        WriteToExcel.log("TestCase1_HospitalFilter", "filtered hospital: " + name);
	                        logger.info("filtered hospital: " + name);
	                        
	                    }
	                }
	            } catch (Exception e) {
	                WriteToExcel.log("TestCase1_HospitalFilter", "Error checking parking for: " + name);
	                logger.error("Error checking parking for: " + name, e);
	            
	            } finally {
	                driver.close();
	                driver.switchTo().window(mainWindow);
	               
	            }
	              
	        }
	 }
	        
	      
	        
	    }

