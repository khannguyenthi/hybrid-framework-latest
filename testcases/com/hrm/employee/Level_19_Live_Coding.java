package com.hrm.employee;
	
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.MyInfoPO;
import pageObjects.hrm.pageGenerator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_19_Live_Coding extends BaseTest{
	String adminUserName, adminPassword, empFirstName, empLastName, empUserName, empPassword, employeeID, statusValue, empFullName;
	String avatarFilePath = GlobalConstants.UPLOAD_FILE + "Avatar.jpg";
	
	 @Parameters({ "browser", "url" })
	 @BeforeClass 
	  public void beforeClass(String browserName, String appUrl) {
		 log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		 driver = getBrowserDriver(browserName, appUrl);	 
		 loginPage = pageGenerator.getLoginPage(driver);
		 driver.manage().window().maximize();
		 
		 
		 statusValue = "Enabled";
		 adminUserName = "Admin";
		 adminPassword = "admin123";
		 empFirstName = "Automation";
		 empLastName = "FC";
		 empUserName = "automationfc";
		 empPassword = "automation123";
		 empFullName = empFirstName + " " + empLastName; 
		 
		 log.info("Pre-condition - Step 02: Login with admin role");
		 dashboardPage = loginPage.loginToSystem(driver,  adminUserName, adminPassword);
		  
	 }

 @Test
  public void Employee_01_Add_New_Employee() {
	 log.info("Add_New_01 - Step 01: Open 'Employee List' Page");
	 //employeeListPage = dashboardPage.openEmployeeListPage();
	 dashboardPage.openSubMenuPage(driver, "PIM", "Employee List"); 
	 //Do return void nen phai gan lai page & khoi tao trang Employee List
	 //Khoi tao page tu pageGeneratorManager
	 employeeListPage = pageGenerator.getEmployeeListPage(driver);
	 
	 
	 log.info("Add_New_01 - Step 02: Click to 'Add' button");
	 //addEmployeePage = employeeListPage.clickToAddButton();
	 employeeListPage.clickToButtonById(driver, "btnAdd");
	 addEmployeePage = pageGenerator.getAddEmployeePage(driver); //Khoi tao trang Add Employee Page
	 
	 
	 log.info("Add_New_01 - Step 03: Enter valid info to 'First Name' textbox");
	 addEmployeePage.enterToTextboxByID(driver, "firstName", empFirstName);
	 
	 
	 log.info("Add_New_01 - Step 04: Enter valid info to 'Last Name' textbox");
	 addEmployeePage.enterToTextboxByID(driver, "lastName", empLastName);

	 log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
	 //employeeID = addEmployeePage.getEmployeeID();
	 employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");
	 addEmployeePage.sleepInSecond(3);
	 System.out.println(employeeID);
	 
	 log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
	 addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

	 log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
	 addEmployeePage.enterToTextboxByID(driver, "user_name", empUserName);
	 
	 log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
	 addEmployeePage.enterToTextboxByID(driver, "user_password", empPassword);
	 
	 log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
	 addEmployeePage.enterToTextboxByID(driver, "re_password", empPassword);
	 
	 log.info("Add_New_01 - Step 10: Select '" + statusValue + "' value in 'Status' dropdown");
	 addEmployeePage.selectItemInDropdownByID(driver, "status", statusValue);
	 
	 log.info("Add_New_01 - Step 11: Click to 'Save' button");
	 //personalDetailPage = addEmployeePage.clickToSaveButton();
	 addEmployeePage.clickToButtonById(driver, "btnSave");
	 personalDetailPage = pageGenerator.getMyInfoPage(driver);
	 
	 
	 log.info("Add_New_01 - Step 12: Open 'Employee List' Page after create employee successfully");
	 //employeeListPage = personalDetailPage.openEmployeeListPage();
	 
	 personalDetailPage.openSubMenuPage(driver, "PIM", "Employee List");
	 employeeListPage = pageGenerator.getEmployeeListPage(driver);
	 
	 log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
	 verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
	 employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", empFullName);
	 verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
	 
	 log.info("Add_New_01 - Step 14: Click to 'Search' button");
	 //employeeListPage.clickToSearchButton();
	 employeeListPage.clickToButtonById(driver, "searchBtn");
	 verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
	 
	 log.info("Add_New_01 - Step 15: Verify Employee Information displayed at 'Result Table'");
	 //verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), empLastName); 
	 
	 log.info("Add_New_01 - Step 16: Logout to system");
}
 
 
  @Test
  public void Employee_02_Upload_Avatar() { 
	  log.info("Upload_Avatar_02 - Step 01: Login with employee role");
	  loginPage = employeeListPage.logoutToSystem(driver);
	  dashboardPage = loginPage.loginToSystem(driver,  empUserName, empPassword);
	  
	  log.info("Upload_Avatar_02 - Step 02: Open 'My Info' menu page");
	  dashboardPage.openMenuPage(driver, "My Info");
	  personalDetailPage = pageGenerator.getMyInfoPage(driver);
	  
	  log.info("Upload_Avatar_02 - Step 03: Click to Change Photo Image");
	  personalDetailPage.clickToChangePhotoImage();
	  
	  log.info("Upload_Avatar_02 - Step 04: Upload new avatar image");
	  personalDetailPage.uploadImage(driver, avatarFilePath);
	  
	  log.info("Upload_Avatar_02 - Step 05: Click to Upload button");
	  personalDetailPage.clickToButtonById(driver, "btnSave");
	  
	  log.info("Upload_Avatar_02 - Step 06: Verify success message is displayed");
	  personalDetailPage.isJQueryAjaxLoadedSuccess(driver);
	  verifyTrue(personalDetailPage.isUploadAvatarSuccessMessageDisplay());
	  
	  personalDetailPage.isJQueryAjaxLoadedSuccess(driver);
	  log.info("Upload_Avatar_02 - Step 07: Verify new avatar image is displayed");
	  verifyTrue(personalDetailPage.isNewAvatarImageDisplay());
	  
  }
  
  @Test
  public void Employee_03_Personal_Details() {
	  
  }
  
  @Test
  public void Employee_04_Contact_Details() {
	  
  }
  
  @Test
  public void Employee_05_Emergency_Contacts() {
	  
  }
 
  @Test
  public void Employee_06_Dependents() {
	  
  }
  
  @Test
  public void Employee_07_Edit_View_Job() {
	  
  }
 
  @Test
  public void Employee_08_Edit_View_Salary() {
	  
  }
  
  @Test
  public void Employee_09_Edit_View_Tax() {
	  
  }
  
  @Test
  public void Employee_10_Qualifications() {
	  
  }
 
  @Test
  public void Employee_11_Search_Employee() {
	  
  }
 
 
 
  @AfterClass(alwaysRun = true)
 	public void afterClass() {
 	  closeBrowserAndDriver();
 	}
  
  	private WebDriver driver; 
  	LoginPO loginPage;
	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage; 
	EmployeeListPO employeeListPage;
	MyInfoPO personalDetailPage;
	
}
