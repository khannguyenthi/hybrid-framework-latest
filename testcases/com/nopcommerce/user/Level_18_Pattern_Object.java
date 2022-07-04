package com.nopcommerce.user;

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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_18_Pattern_Object extends BaseTest{
	
	 @Parameters({"envName","serverName","browser", "ipAddress", "portNumber", "osName", "osVersion"})
	 @BeforeClass 
	 public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10")String osVersion) {
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion); 
		 
		 
		 // 1  using for pre-condition case. 
		 //homePage = new HomePageObject(driver);
		 homePage = PageGeneratorManager.getUserHomePage(driver);
		 registerPage = PageGeneratorManager.getUserRegisterPage(driver);
		 
		 emailAddress = "khan" + generateFakeNumber() + "@gmail.com";
		 firstName = "Khan";
		 lastName = "Auto";
		 password = "123456";
		 date = "2";
		 month = "August";
		 year = "1998";
	 }

 @Test
  public void User_01_Regiser() {
	 log.info("Register - Step 01: Navigate to 'Register page'");
	 registerPage = homePage.clickToRegisterLink(); 
	  
	 log.info("Register - Step 02: Enter to Firstname textbox with value is '"+ firstName + "'");
	 registerPage.inputToTextboxByID(driver, "FirstName", firstName);
	 
	  log.info("Register - Step 02: Enter to Lastname textbox with value is '"+ lastName + "'");
	  registerPage.inputToTextboxByID(driver, "LastName", lastName);
	  
	  registerPage.clickToRadioButtonByLabel(driver, "Male");
	  
	  registerPage.clickToCheckboxByLabel(driver, "Newsletter");
	  
	  log.info("Register - Step 02: Enter to Emailaddress textbox with value is '"+ emailAddress + "'");
	  registerPage.inputToTextboxByID(driver, "Email", emailAddress);
	  
	  log.info("Register - Step 02.1: Enter birthday '"+ date + "'");
	  registerPage.selectToDropdownByName(driver, "DateOfBirthDay", date);
	  registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", month);
	  registerPage.selectToDropdownByName(driver, "DateOfBirthYear", year);
	  
	  log.info("Register - Step 02: Enter to Password textbox with value is '"+ password + "'");
	  registerPage.inputToTextboxByID(driver, "Password", password);
	  
	  log.info("Register - Step 02: Enter to Confirm Password textbox with value is '"+ password + "'");
	  registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);
	  
	  log.info("Register - Step 03: Click Register Button");
	  //registerPage.clickToRegisterButton();
	  registerPage.clickToButtonByText(driver, "Register");

	  log.info("Register - Step 04: Verify success message displayed...");
	  Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");  
	  
	  log.info("Register - Step 05: Click logout link");
	  homePage = registerPage.clickToLogoutLink();  	  
  
	  log.info("Login - Step 06: Click Login link");
	  loginPage = homePage.clickToLoginLinkUser();
	  
	  log.info("Login - Step 07: Click Login link");
	  loginPage.inputToEmailTextbox(emailAddress);
	  loginPage.inputToPasswordTextbox(password);
	  
	  log.info("Login - Step 08: Click Login link");
	  homePage = loginPage.clickToLoginButton();
	  
	  log.info("Login - Step 09: Verify My Account link display");
	  verifyTrue(homePage.isMyAccountLinkDisplayed());     
	 
  }
 
 
  @Test
  public void User_02_My_Account() {
	  log.info("My Account - Step 01: Verify Customer Info link display");
	  customerInforPage = homePage.clickToMyAccountLink(); 
	  
	  log.info("My Account - Step 02: Verify Customer Info link display");
	  verifyTrue(customerInforPage.isCustomerInforPageDisplayed());
	  
	  log.info("My Account - Step 03: Verify firstName value is correctly");
	  //FirstName la gia tri cua ID inspect, firstName la gia tri o buoc dki
	  Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"FirstName"),firstName); 
	  
	  log.info("My Account - Step 04: Verify lastName value is correctly");
	  Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"LastName"),lastName); 
	  	
	  log.info("My Account - Step 05: Verify emailAddress value is correctly");
	  Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"Email"),emailAddress); 
	 
	  
  }
 
 
  //@AfterClass(alwaysRun = true)
 	//public void afterClass() {
 	  //closeBrowserAndDriver();
 	//}
  
  private WebDriver driver; 
	//private String projectPath = System.getProperty("user.dir");
	private String emailAddress, firstName, lastName, password, myAccountLinkText;
	private UserHomePageObject homePage; 
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInfoPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;
	private String date, month, year;
}
