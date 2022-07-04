package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

//import com.relevantcodes.extentreports.LogStatus;

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
//import reportConfig.ExtentTestManager;
import reportConfig.ExtentTestManager;

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

public class Level_15_ExtentV5_Screenshot extends BaseTest{
	
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
  public void User_01_Regiser(Method method) {
	 ExtentTestManager.startTest(method.getName(), "Register to system with valid Email and Password");
	 ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
	 registerPage = homePage.clickToRegisterLink();

	 ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
	 registerPage.inputToFirstNameTextbox(firstName);

	 ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
	 registerPage.inputToLastNameTextbox(lastName);

	 ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
	 registerPage.inputToEmailTextbox(emailAddress);

	 ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Enter to Password textbox with value is '" + password + "'");
	 registerPage.inputToPasswordTextbox(password);

	 ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
	 registerPage.inputToConfirmPasswordTextbox(password);

	 ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: Click to 'Register' button");
	 registerPage.clickToRegisterButton();

	 ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08: Verify register success message is displayed");
	 Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

  }
 
 @Test
 public void User_02_Login(Method method) {
	 ExtentTestManager.startTest(method.getName(), "Login to system successfully");
	 ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Navigate to Login page");
	 homePage = registerPage.clickToLogoutLink();
	 loginPage = homePage.clickToLoginLinkUser();

	 ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
	 loginPage.inputToEmailTextbox(emailAddress);

	 ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Enter to Password textbox with value is '" + password + "'");
	 loginPage.inputToPasswordTextbox(password);

	 ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Click to Login button");
	 homePage = loginPage.clickToLoginButton();

	 ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05: Verify 'My Account' link is displayed");
	 Assert.assertFalse(homePage.isMyAccountLinkDisplayed());

	 ExtentTestManager.getTest().log(Status.INFO, "Login - Step 06: Navigate to 'My Account' page");
	 customerInforPage = homePage.clickToMyAccountLink();

	 ExtentTestManager.getTest().log(Status.INFO, "Login - Step 06: Verify 'Customer Infor' page is displayed");
	 Assert.assertFalse(customerInforPage.isCustomerInforPageDisplayed());

		 
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
