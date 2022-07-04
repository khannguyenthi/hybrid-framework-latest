package com.facebook.register;

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
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager_Facebook;
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

public class Level_13_Element_Undisplay extends BaseTest {
	@Parameters({"envName","serverName","browser", "ipAddress", "portNumber", "osName", "osVersion"})
	@BeforeClass 
	public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10")String osVersion) {
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion); 

		 //loginPage = PageGeneratorManager_Facebook.getLoginPage(driver);
		 loginPage = PageGeneratorManager_Facebook.getLoginPage(driver); 
				 }

@Test
	 public void TC_01_Verify_Element_Display() {
	 	log.info("Login - Step 01: Click to Creat New Account Btn");
		loginPage.clickToCreateNewAccountButton();
		//Neu ham chi mong doi verify element display thôi - ket hop ca wait + isDisplay
		log.info("Login - Step 02: Check email field");
		verifyTrue(loginPage.isEmailAddressTextboxDisplayd());
		
		log.info("Login - Step 3: fill email field:");
		loginPage.enterToEmailAddressTextbox("automation@fc.com");
		loginPage.sleepInSecond(3);
		log.info("Login - Step 4: fill confirm email field");
		verifyTrue(loginPage.isReEnterEmailTextboxDisplayed());
	 }
@Test
	 public void TC_02_Verify_Element_Undisplayed_In_DOM() {
	
	loginPage.enterToEmailAddressTextbox("");
	loginPage.sleepInSecond(3);
	//verifyFalse(loginPage.isReEnterEmailTextboxDisplayed());
	verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	
	 }
	 
@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
	loginPage.clickCloseIconAtRegisterForm();
	loginPage.sleepInSecond(3);
	
	// isDisplayed: ban chat ko kiem tra được 1 element ko có trong DOM được - Vì nếu ko có trong DOM thì nó chưa pass wa dc step findElement 
	//verifyFalse(loginPage.isReEnterEmailTextboxDisplayed()); //=> fail do ko find dc element
	// No se fail step findElement
	verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	
	}
    
  @AfterClass
	public void afterClass() {
		driver.quit();
	}
    private WebDriver driver; 
    private LoginPageObject loginPage; 
	 
}
