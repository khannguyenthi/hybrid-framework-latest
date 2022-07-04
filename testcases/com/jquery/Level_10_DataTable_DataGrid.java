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
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager_JQuery;

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

public class Level_10_DataTable_DataGrid extends BaseTest {
	HomePageObject homePage;
	List<String> actualAllCountryValue;
	List<String> expectedAllCountryValue;
	@Parameters({"envName","serverName","browser", "ipAddress", "portNumber", "osName", "osVersion"})
	 @BeforeClass 
	 public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10")String osVersion) {
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion); 
		 
		 homePage = PageGeneratorManager_JQuery.getHomePage(driver);
	 }

 //@Test
  public void Table_01_Paging() {
	 homePage.openPagingByPageNumber("10");
	 homePage.sleepInSecond(3); 
	 Assert.assertTrue(homePage.isPageNumerActived("10"));
	 
	 homePage.openPagingByPageNumber("19");
	 homePage.sleepInSecond(3); 
	 Assert.assertTrue(homePage.isPageNumerActived("19"));
	 
	 homePage.openPagingByPageNumber("1");
	 homePage.sleepInSecond(3); 
	 Assert.assertTrue(homePage.isPageNumerActived("1"));
	 
  }
 
 //@Test
 public void Table_02_Enter_To_Header() {
	 homePage.refreshCurrentPage(driver);
	 
	 homePage.enterToHeaderTextBoxByLabel("Country", "Afghanistan");
	 homePage.enterToHeaderTextBoxByLabel("Females", "384187");
	 homePage.enterToHeaderTextBoxByLabel("Males", "407124");
	 homePage.enterToHeaderTextBoxByLabel("Total", "791312");
	 homePage.sleepInSecond(3); 
	 
	 homePage.enterToHeaderTextBoxByLabel("Country", "Angola");
	 homePage.enterToHeaderTextBoxByLabel("Females", "276880");
	 homePage.enterToHeaderTextBoxByLabel("Males", "276472");
	 homePage.enterToHeaderTextBoxByLabel("Total", "553353");
	 homePage.sleepInSecond(3); 
 }
 
 //@Test
 public void Table_03_Get_All_Values_All_Page() {
	 //Doc du lieu cua file country.txt
	 //Luu vao 1 List<String> = Expected Value = expectedAllCountryValues
	 
	 //Actual 
	 actualAllCountryValue = homePage.getValueEachRowAtAllPage();
	 Assert.assertEquals(actualAllCountryValue, expectedAllCountryValue);
 }
 
 @Test
 public void Table_04_Action_To_Textbox_At_Any_Row() {
	 homePage.clickToLoadButton();
	 homePage.sleepInSecond(5);
	 
	 homePage.enterToTextboxAtRowNumberByColumnName("Album","1","Khan Album");
	 homePage.enterToTextboxAtRowNumberByColumnName("Artist","2","Khan Artist");
	 homePage.enterToTextboxAtRowNumberByColumnName("Year","3","1983");
	 homePage.enterToTextboxAtRowNumberByColumnName("Price","4","199");
	 homePage.selectDropDowAtRowNumberByColumnName("Origin","5","US");
	 
	 
	 //Value de nhap lieu - tham so 1
	 //Row number: tai row nào
	 //Ex: nhap vao textbox tai dong so 3/5/5
	 //Column name: Album/Artist/Year/Price
	 //Du lieu truyen vao: column name, cot nào, giá trị
	 //Album: ten cot
	 //1: dòng nào rowNumber
	 //Michel 97: nhap value gì? 
	 
	 //homePage.enterToTextboxAtRowNumberByColumnName("Album","1","Michel 97");
	 
	 //homePage.enterToTextboxAtRowNumberByColumnName("Artist","2","Michel ");

	 //homePage.enterToTextboxAtRowNumberByColumnName("Year","3","1997");
	 
	 //homePage.enterToTextboxAtRowNumberByColumnName("Price","4","97");
	 
	 //Chon 1 value trong dropdown Origin 
	 //homePage.selectDropDowAtRowNumberByColumnName("Origin","1","Hong Kong");
	 //homePage.sleepInSecond(3);
	 
	 //homePage.selectDropDowAtRowNumberByColumnName("Origin","5","US");
	 //homePage.sleepInSecond(3);
	 
	 //Click on checkbox 'With Poster?'
	 homePage.checkOnCheckboxAtRowNumberByColumnName("With Poster?","3");
	 homePage.sleepInSecond(3);
	 homePage.checkOnCheckboxAtRowNumberByColumnName("With Poster?","5");
	 homePage.sleepInSecond(3);
	 
	 homePage.uncheckOnCheckboxAtRowNumberByColumnName("With Poster?","1");
	 homePage.sleepInSecond(3);
	 homePage.uncheckOnCheckboxAtRowNumberByColumnName("With Poster?","2");
	 homePage.sleepInSecond(3);
	 homePage.uncheckOnCheckboxAtRowNumberByColumnName("With Poster?","4");
	 homePage.sleepInSecond(3);
	 
	 //Action
	 homePage.clickToIconByRowNumber("1","Remove Current Row");
	 homePage.sleepInSecond(2);
	 
	 homePage.clickToIconByRowNumber("1","Insert Row Above");
	 homePage.sleepInSecond(2);
	 
	 homePage.clickToIconByRowNumber("3","Move Up");
	 homePage.sleepInSecond(2);
	 
	 homePage.clickToIconByRowNumber("5","Remove Current Row");
	 homePage.sleepInSecond(2);
	 
	 homePage.clickToIconByRowNumber("4","Remove Current Row");
	 homePage.sleepInSecond(2);
	 
	 homePage.clickToIconByRowNumber("3","Remove Current Row");
	 homePage.sleepInSecond(2);
	 
	 homePage.clickToIconByRowNumber("2","Remove Current Row");
	 homePage.sleepInSecond(2);
	 
	 homePage.clickToIconByRowNumber("1","Remove Current Row");
	 homePage.sleepInSecond(2);
	 
	 
 }
	
   
  @AfterClass
	public void afterClass() {
		driver.quit();
	}
    private WebDriver driver; 
	 
}
