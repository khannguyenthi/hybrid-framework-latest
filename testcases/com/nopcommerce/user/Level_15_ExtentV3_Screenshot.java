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

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_15_ExtentV3_Screenshot extends BaseTest{
	
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
	 }

 @Test
  public void User_01_Regiser_Login(Method method) {
	 
	 registerPage = homePage.clickToRegisterLink(); 
	  
	 registerPage.inputToFirstNameTextbox(firstName);
	  
	  registerPage.inputToLastNameTextbox(lastName);
	  
	  registerPage.inputToEmailTextbox(emailAddress);
	  
	  registerPage.inputToPasswordTextbox(password);
	  
	  registerPage.inputToConfirmPasswordTextbox(password);
	  
	  registerPage.clickToRegisterButton();

	  Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");  
	  
	  homePage = registerPage.clickToLogoutLink();  	  
  
	  loginPage = homePage.clickToLoginLinkUser();
	  
	  loginPage.inputToEmailTextbox(emailAddress);
	  loginPage.inputToPasswordTextbox(password);
	  
	  homePage = loginPage.clickToLoginButton();
	  
	  Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	       
	  customerInforPage = homePage.clickToMyAccountLink();    
	  Assert.assertFalse(customerInforPage.isCustomerInforPageDisplayed());
	  
	  
  }
 
 //@Test
 public void User_02_Switch_Page() {
	 
	 //Customer Infor > Address
	 log.info("Customer Info - Step 01: Switch to Address page");
	 addressPage = customerInforPage.openAddressPage(driver);
	 log.info("Address - Step 02: Switch to My Product review page");
	 //Address > My Product Review
	 myProductReviewPage = addressPage.openMyProductReviewPage(driver);
	 //My Product Review > Reward Point
	 log.info("My Product Review - Step 03: Switch to Reward Point page");
	 rewardPointPage = myProductReviewPage.openRewardPoint(driver);
	 //Reward Point -> Address
	 log.info("Reward Point - Step 04: Switch to Address page");
	 addressPage = rewardPointPage.openAddressPage(driver);
	 //Address -> Reward Point
	 log.info("Address - Step 05: Switch to Reward Point page");
	 rewardPointPage = addressPage.openRewardPoint(driver);
	 //Reward Point > My Product Review
	 log.info("Reward Point - Step 06: Switch to My Product review page");
	 myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
	 
 }
  //@Test
  public void User_03_Dynamic_Page_01() {
	  
	  //My Product Review > Reward Point
	  rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Reward points");
	  //Reward Point -> Address
	  addressPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
	  //Address -> Reward Point
	  rewardPointPage = (UserRewardPointPageObject) addressPage.openPagesAtMyAccountByName(driver, "Reward points");
	  //Reward Point > My Product Review
	  myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "My product reviews");
	 
	  //My Product Review > Customer Info
	  customerInforPage = (UserCustomerInfoPageObject) myProductReviewPage.openAddressPage(driver).openPagesAtMyAccountByName(driver, "Customer info");
	  
  }
 //@Test
  public void User_04_Dynamic_Page_02() {
	  //Customer Info -> My Product Review
	 customerInforPage.openPagesAtMyAccountByName(driver, "My product reviews");
	 myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
	 
	 //My Product Review -> Reward Point
	 myProductReviewPage.openPagesAtMyAccountByName(driver, "Reward points");
	 rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
	 
	 //Reward Point -> Address
	 rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
	 addressPage = PageGeneratorManager.getUserAddressPage(driver);
	 
	 //Address -> Reward Point
	 addressPage.openPagesAtMyAccountByName(driver, "Reward points");
	 rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
	 
	 
	}
  
  @AfterClass
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
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;
}
