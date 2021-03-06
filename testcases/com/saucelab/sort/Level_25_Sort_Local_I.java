package com.saucelab.sort;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.saucelab.InventoryPO;
import pageObject.saucelab.LoginPO;
import pageObject.saucelab.pageGenerator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_25_Sort_Local_I extends BaseTest{
	WebDriver driver;
	LoginPO loginPage;
	InventoryPO inventoryPage;
	
	 @Parameters({"envName","serverName","browser", "ipAddress", "portNumber", "osName", "osVersion"})
	 @BeforeClass 
	  public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10")String osVersion) {
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
		driver.manage().window().maximize();
		
		loginPage = pageGenerator.getLoginPage(driver);
		loginPage.enterToUsernameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		inventoryPage = loginPage.clickToLoginButton(); 
		
		System.out.println("Thread ID = " + Thread.currentThread().getId() + " with browser = " + browserName);
		System.out.println("Thread ID = " + Thread.currentThread().getId() + " with driver = " + driver.toString());
		
	 }

 @Test
  public void Sort_01_Name() {
	 log.info("Register - Step 01: Navigate to 'Register page'");
	 inventoryPage.selectItemInSortDropdown("Name (A to Z)");
	 inventoryPage.sleepInSecond(3);
	 verifyTrue(inventoryPage.isProductNameSortAscending());
	 
	 inventoryPage.selectItemInSortDropdown("Name (Z to A)");
	 inventoryPage.sleepInSecond(3);
	 verifyTrue(inventoryPage.isProductNameSortDescending());
	 
  }
 
  @Test
  public void Sort_02_Price() {
	  inventoryPage.selectItemInSortDropdown("Price (low to high)");
	  inventoryPage.sleepInSecond(3);
	  verifyTrue(inventoryPage.isProductPriceSortAscending());
	  	  
	  inventoryPage.selectItemInSortDropdown("Price (high to low)");
	  inventoryPage.sleepInSecond(3);
	  verifyTrue(inventoryPage.isProductPriceSortDescending());
  }
 
 
  @AfterClass(alwaysRun = true)
 	public void afterClass() {
 	  closeBrowserAndDriver("local");
 	}
  
  
	
}
