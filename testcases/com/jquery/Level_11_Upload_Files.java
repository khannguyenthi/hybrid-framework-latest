package com.jquery;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.portal.nopCommerce.PageGeneratorManager;
import pageObject.portal.nopCommerce.UserAddressPageObject;
import pageObject.portal.nopCommerce.UserCustomerInfoPageObject;
import pageObject.portal.nopCommerce.UserHomePageObject;
import pageObject.portal.nopCommerce.UserLoginPageObject;
import pageObject.portal.nopCommerce.UserMyProductReviewPageObject;
import pageObject.portal.nopCommerce.UserRegisterPageObject;
import pageObject.portal.nopCommerce.UserRewardPointPageObject;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager_JQuery;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_11_Upload_Files extends BaseTest {
	String csharpFileName = "CSharp.png";	
	String javaFileName = "Java.jpg";	
	String pythonFileName = "Python.jpg";	
	String rubyFileName = "Ruby.jpg";	
	String[] multipleFileNames = {csharpFileName,javaFileName,pythonFileName,rubyFileName};
	
	@Parameters({"envName","serverName","browser", "ipAddress", "portNumber", "osName", "osVersion"})
	 @BeforeClass 
	 public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10")String osVersion) {
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion); 

		 homePage = PageGeneratorManager_JQuery.getHomePage(driver);
	 }
	 //@Test
	 public void Upload_01_One_File_Per_Time() {
		 //Step 01 - Load single file
		 homePage.uploadMultipleFiles(driver, csharpFileName);
		 
		 //Step 2 - Verify single file loaded success
		 Assert.assertTrue(homePage.isFileLoadedByName(csharpFileName));
		 
		 //Step3: click to Start button
		 homePage.clickToStartButton();
		 
		 //Step 4:  Verify single file link upload success
		 Assert.assertTrue(homePage.isFileLinkUploadedByName(csharpFileName));
		 
		 //Step 5: Verify single file image upload success
		 Assert.assertTrue(homePage.isFileImageUploadedByName(csharpFileName));
	 }
@Test
	public void Upload_02_Multiple_File_Per_Time() {
	 //Step 01 - Load mul file
	homePage.uploadMultipleFiles(driver, csharpFileName);
	homePage.uploadMultipleFiles(driver, javaFileName);
	homePage.uploadMultipleFiles(driver, pythonFileName);
	homePage.uploadMultipleFiles(driver, rubyFileName);
	 
	 //Step 2 - Verify mul file loaded success
	 //csharpFileName, javaFileName, pythonFileName, rubyFileName  
	 Assert.assertTrue(homePage.isFileLoadedByName(csharpFileName));
	 Assert.assertTrue(homePage.isFileLoadedByName(javaFileName));
	 Assert.assertTrue(homePage.isFileLoadedByName(pythonFileName));
	 Assert.assertTrue(homePage.isFileLoadedByName(rubyFileName));
	 
	 //Step3: click to Start button
	 homePage.clickToStartButton();
	 
	 //Step 4:  Verify mul file link upload success
	 Assert.assertTrue(homePage.isFileLinkUploadedByName(csharpFileName));
	 Assert.assertTrue(homePage.isFileLinkUploadedByName(javaFileName));
	 Assert.assertTrue(homePage.isFileLinkUploadedByName(pythonFileName));
	 Assert.assertTrue(homePage.isFileLinkUploadedByName(rubyFileName));
	 
	 //Step 5: Verify mul file image upload success
	 Assert.assertTrue(homePage.isFileImageUploadedByName(csharpFileName));
	 Assert.assertTrue(homePage.isFileImageUploadedByName(javaFileName));
	 Assert.assertTrue(homePage.isFileImageUploadedByName(pythonFileName));
	 Assert.assertTrue(homePage.isFileImageUploadedByName(rubyFileName));
}
    
  @AfterClass
	public void afterClass() {
		//driver.quit();
	}
    private WebDriver driver; 
    private HomePageObject homePage; 
	 
}
