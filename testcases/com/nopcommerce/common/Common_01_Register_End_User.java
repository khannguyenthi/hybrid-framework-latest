package com.nopcommerce.common;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.BaseTestNopCommerce;
import pageObject.portal.nopCommerce.PageGeneratorManager;
import pageObject.portal.nopCommerce.UserAddressPageObject;
import pageObject.portal.nopCommerce.UserCustomerInfoPageObject;
import pageObject.portal.nopCommerce.UserHomePageObject;
import pageObject.portal.nopCommerce.UserLoginPageObject;
import pageObject.portal.nopCommerce.UserMyProductReviewPageObject;
import pageObject.portal.nopCommerce.UserRegisterPageObject;
import pageObject.portal.nopCommerce.UserRewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class Common_01_Register_End_User extends BaseTest{
	 
 @Parameters({"envName","serverName","browser", "ipAddress", "portNumber", "osName", "osVersion"})
 @BeforeTest(description = "Create new Common User for all Class Test")
 public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10")String osVersion) {
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion); 	 
	
	 homePage = PageGeneratorManager.getUserHomePage(driver);
	 registerPage = PageGeneratorManager.getUserRegisterPage(driver);
	 
	 emailAddress = "khan" + generateFakeNumber() + "@gmail.com";
	 firstName = "Khan";
	 lastName = "Auto";
	 password = "123456";
	 
	 log.info("Pre-condition: Register - Step 01: Navigate to 'Register page'");
	 registerPage = homePage.clickToRegisterLink(); 
	  
	 log.info("Pre-condition: Register - Step 02: Enter to Firstname textbox with value is '"+ firstName + "'");
	  registerPage.inputToFirstNameTextbox(firstName);
	  log.info("Pre-condition: Register - Step 02: Enter to Lastname textbox with value is '"+ lastName + "'");
	  registerPage.inputToLastNameTextbox(lastName);
	  log.info("Pre-condition: Register - Step 02: Enter to Emailaddress textbox with value is '"+ emailAddress + "'");
	  registerPage.inputToEmailTextbox(emailAddress);
	  log.info("Pre-condition: Register - Step 02: Enter to Password textbox with value is '"+ password + "'");
	  registerPage.inputToPasswordTextbox(password);
	  log.info("Pre-condition: Register - Step 02: Enter to Confirm Password textbox with value is '"+ password + "'");
	  registerPage.inputToConfirmPasswordTextbox(password);
	  
	  log.info("Pre-condition: Register - Step 03: Click Register Button");
	  registerPage.clickToRegisterButton();

	  log.info("Pre-condition: Register - Step 04: Verify success message displayed...");
	  Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");  
	  
	  log.info("Pre-condition: Register - Step 05: Click logout link");
	  homePage = registerPage.clickToLogoutLink();  
	  driver.quit();
}
  
 
    private WebDriver driver; 
	//private String projectPath = System.getProperty("user.dir");
	public static String emailAddress, password;
	private  String firstName, lastName;
	private UserHomePageObject homePage; 
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInfoPageObject customerInforPage;
	//private UserAddressPageObject addressPage;
	//private UserMyProductReviewPageObject myProductReviewPage;
	//private UserRewardPointPageObject rewardPointPage;
}
