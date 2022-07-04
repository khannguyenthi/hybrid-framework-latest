package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Cookie;
import com.nopcommerce.common.Common_01_Register_End_User;

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
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class Level_16_Share_DataC extends BaseTest{
	 
	 @Parameters({"envName","serverName","browser", "ipAddress", "portNumber", "osName", "osVersion"})
	 @BeforeClass 
	 public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10")String osVersion) {
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion); 
	 
	
	 homePage = PageGeneratorManager.getUserHomePage(driver);
	 //registerPage = PageGeneratorManager.getUserRegisterPage(driver);	 
	  
	  log.info("Pre-condition: - Step 01: Navigate to Login page");
	  loginPage = homePage.clickToLoginLinkUser();
	  
	  log.info("Pre-condition: - Step 02: Set cookies and reload page");
	  loginPage.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
	  for (Cookie cookie : Common_01_Register_Cookie.loggedCookies) {
		System.out.println("Cookie at C class: " + cookie);
	}
	  
	  loginPage.refreshCurrentPage(driver);
	  
	  log.info("Pre-condition: - Step 03: Verify My Account link display");
	  verifyTrue(homePage.isMyAccountLinkDisplayed());
  }
 
 @Test
 public void Search_06_Correct_Manufactor() {
	  log.info("Login - Step 09: Verify My Account link display");
	  verifyTrue(homePage.isMyAccountLinkDisplayed());
 }
  
  @AfterTest
	public void afterClass() {
		driver.quit();
	}
    private WebDriver driver; 
	//private String projectPath = System.getProperty("user.dir");
	private String emailAddress, firstName, lastName, password, myAccountLinkText;
	private UserHomePageObject homePage; 
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInfoPageObject customerInforPage;
	//private UserAddressPageObject addressPage;
	//private UserMyProductReviewPageObject myProductReviewPage;
	//private UserRewardPointPageObject rewardPointPage;
}
