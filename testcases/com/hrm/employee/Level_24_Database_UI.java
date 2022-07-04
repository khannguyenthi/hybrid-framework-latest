package com.hrm.employee;
	
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import environmentConfig.Environment;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.MyInfoPO;
import pageObjects.hrm.pageGenerator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_24_Database_UI extends BaseTest{
	String adminUserName, adminPassword; 
	
	//Environment environment;
	
	@Parameters({"envName","serverName","browser", "ipAddress", "portNumber", "osName", "osVersion"})
	 @BeforeClass 
	 public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10")String osVersion) {
		 driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);  
		 log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '");
		 loginPage = pageGenerator.getLoginPage(driver);
		 driver.manage().window().maximize();
		 
		 adminUserName = "Admin";
		 adminPassword = "admin123";
		 log.info("Pre-condition - Step 02: Login with admin role");
		 dashboardPage = loginPage.loginToSystem(driver,  adminUserName, adminPassword);
		  
	 }

 @Test
  public void Employee_Search() throws SQLException {
	 log.info("Employee_Search_01 - Step 01: Get Employee size on UI");
	 int employeeListNumberUI = dashboardPage.getEmployeeListNumberUI(); 
	 	 
	 log.info("Employee_Search_02 - Step 02: Get Employee size in DB");
	 int employeeListNumberInDB = dashboardPage.getEmployeeListNumberInDB();
	 
	 log.info("Employee_Search_03 - Step 03: Verify Employee size in UI and DB equal");
	 verifyEquals(employeeListNumberUI, employeeListNumberInDB);
}
 

 
  //@Test
  public void Employee_11_Search_Employee() {
	  
  }
 
 
 
  @AfterClass(alwaysRun = true)
 	public void afterClass() {
 	  closeBrowserAndDriver("local");
 	}
  
  	private WebDriver driver; 
  	LoginPO loginPage;
	DashboardPO dashboardPage; 
	
}
