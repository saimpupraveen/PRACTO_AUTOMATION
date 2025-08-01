package com.pages;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.ReadExcel;
import com.utils.WriteToExcel;

import java.io.IOException;
import java.time.Duration;
import java.util.List;




public class CorporateWellnessPage {
    WebDriver driver;
    WebDriverWait wait;

 private static final Logger logger = LogManager.getLogger(CorporateWellnessPage.class);

    public CorporateWellnessPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("CorporateWellnessPage initialized.");
    }

    By nameField = By.name("name");
    By organizationField = By.name("organizationName");
    By contactField = By.name("contactNumber");
    By emailField = By.name("officialEmailId");
    By sizeDropdown = By.name("organizationSize");
    By Dropdown = By.name("interestedIn");
    By scheduleButton = By.xpath("//button[contains(text(),'Schedule a demo')]");
    By forCorporatesMenu = By.xpath("//span[text()='For Corporates']");
    By healthWellnessOption= By.xpath("//a[contains(text(),'Health & Wellness Plans')]");

  
    String[] data = null;

    public void goToCorporateWellness() {
        logger.info("Navigating to 'For Corporates' menu.");
        Actions actions = new Actions(driver);
        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(forCorporatesMenu));
        actions.moveToElement(menu).click().perform();

        logger.info("Clicking on 'Health & Wellness Plans' option.");
        WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(healthWellnessOption));
        firstOption.click();
    }
    
    
    public void ReadExcelData(String filepath) {
        try {
            data = ReadExcel.readFormData(filepath);
            logger.info("Excel data read successfully from: " + filepath);
        } catch (IOException e) {
            logger.error("Error reading Excel file: " + filepath, e);
        	e.printStackTrace();
        }
    }

    public void fillFormWithInvalidData() {
        ReadExcelData("TestInput\\CorporateWellnessPageData.xlsx");
        logger.info("Filling form with invalid data.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(data[0]);
        driver.findElement(organizationField).sendKeys(data[1]);
        driver.findElement(contactField).sendKeys(data[2]);
        driver.findElement(emailField).sendKeys(data[3]);
    }

    public void selectDropdowns() {
        logger.info("Selecting dropdown values.");
        WebElement sizeDrop = wait.until(ExpectedConditions.elementToBeClickable(sizeDropdown));
        sizeDrop.click();
        sizeDrop.sendKeys(data[4]);

        WebElement interestDrop = wait.until(ExpectedConditions.elementToBeClickable(Dropdown));
        interestDrop.click();
        interestDrop.sendKeys(data[5]);
    }



    public void forceClickScheduleButton() {
        logger.info("Forcing click on Schedule button.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[disabled]\").removeAttribute('disabled');");
        js.executeScript("document.querySelector(\"button\").click();");
        WriteToExcel.log("TestCase3_InvalidForm", "Forced click on Schedule button.");
    }

    public void logInvalidFields() {
        logger.info("Checking for invalid fields.");
        WriteToExcel.log("TestCase3_InvalidForm", "Checking for invalid fields...");
        WebElement name = driver.findElement(nameField);
        WebElement org = driver.findElement(organizationField);
        WebElement contact = driver.findElement(contactField);
        WebElement email = driver.findElement(emailField);

        if (isEmpty(name)) {

            WriteToExcel.log("TestCase3_InvalidForm", "Empty Name");
            logger.warn("Empty Name field.");
        }
        if (isEmpty(org)) {

            WriteToExcel.log("TestCase3_InvalidForm", "Empty Organization Name");
            logger.warn("Empty Organization Name field.");
        }
        if (isInvalidPhone(contact)) {

            WriteToExcel.log("TestCase3_InvalidForm", "Invalid Contact Number");
            logger.warn("Invalid Contact Number.");
        }
        if (isInvalidEmail(email)) {

            WriteToExcel.log("TestCase3_InvalidForm", "Invalid Email ID1");
            logger.warn("Invalid Email ID.");
        }
    }

    private boolean isEmpty(WebElement element) {
        String value = element.getAttribute("value");
        return value == null || value.trim().isEmpty();
    }

    private boolean isInvalidPhone(WebElement element) {
        String value = element.getAttribute("value");
        return value == null || !value.matches("\\d{10}");
    }

    private boolean isInvalidEmail(WebElement element) {
        String value = element.getAttribute("value");
        return value == null || !value.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
	}
}