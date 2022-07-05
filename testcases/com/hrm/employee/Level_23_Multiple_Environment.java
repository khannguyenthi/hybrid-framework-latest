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

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_23_Multiple_Environment extends BaseTest{
	Environment environment;

	String adminUserName, adminPassword, empFirstName, empLastName, empUserName, empPassword, employeeID, statusValue, empFullName;
	String editEmpFirstName, editEmpLastName, editEmpGender, editEmpMaritalStatus, editEmpNationality;
	String contactEmpStreet1, contactEmpStreet2, contactEmpCity, contactEmpProvince, contactEmpZipcode, contactEmpContry, contactEmpHomePhone;
	String contactEmpMobile, contactEmpWorkPhone, contactEmpWorkEmail, contactEmpOtherEmail;
	String emergencyEmpContactName, emergencyEmpContactRelationship,emergencyEmpContactPhone;
	String dependentEmpName, dependentEmpRelationshipValue, dependentEmpDOB;
	
	String experienceEmpCompany, experienceEmpJobTitle, experienceEmpFromDate, experienceEmpToDate, experienceEmpComment;
	
	String avatarFilePath = GlobalConstants.getGlobalConstants().getUploadFile() + "Avatar.jpg";
	
	
	@Parameters({"envName","serverName","browser", "ipAddress", "portNumber", "osName", "osVersion"})
	 @BeforeClass 
	 public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10")String osVersion) {
			 {
		
		//ConfigFactory.setProperty("env", appUrl);
		
		environment = ConfigFactory.create(Environment.class);
		
		 //log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		
		 driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion); 	 
		 
		 System.out.println(environment.osName());
		 System.out.println(environment.appUrl());
		 System.out.println(environment.appUsername());
		 
		 System.out.println(driver.getCurrentUrl());
		 
		 
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
		 
		 editEmpFirstName = "John";
		 editEmpLastName = "Wick";
		 editEmpGender = "Male";
		 editEmpMaritalStatus = "Single";
		 editEmpNationality = "Vietnamese";
		 
		 contactEmpStreet1 = "490 Le Van Sy";
		 contactEmpStreet2 = "Tan Binh"; 
		 contactEmpCity = "HCM";
		 contactEmpProvince = "Mien Nam";
		 contactEmpZipcode = "084";
		 contactEmpContry = "Viet Nam";
		 contactEmpHomePhone = "0904309549";
		 contactEmpMobile = "039423993";
		 contactEmpWorkPhone = "2398487";
		 contactEmpWorkEmail = "khan@gmail.com";
		 contactEmpOtherEmail = "automation@email.com";
		 
		 emergencyEmpContactName = "HangLe";
		 emergencyEmpContactRelationship = "Father";
		 emergencyEmpContactPhone = "0918508372";
		 
		 dependentEmpName = "Tue My" ; 
		 dependentEmpRelationshipValue = "Child"; 
		 dependentEmpDOB = "2012-11-04";
		 
		 experienceEmpCompany = "Nash Tech"; 
		 experienceEmpJobTitle = "Automation Tester"; 
		 experienceEmpFromDate = "2007-05-12"; 
		 experienceEmpToDate = "2022-07-15"; 
		 experienceEmpComment = "Fulltime Job";
		 
		 log.info("Pre-condition - Step 02: Login with admin role");
		 dashboardPage = loginPage.loginToSystem(driver,  adminUserName, adminPassword);
			 }  
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
	 //myInforPage = addEmployeePage.clickToSaveButton();
	 addEmployeePage.clickToButtonById(driver, "btnSave");
	 myInforPage = pageGenerator.getMyInfoPage(driver);
	 
	 
	 log.info("Add_New_01 - Step 12: Open 'Employee List' Page after create employee successfully");
	 //employeeListPage = myInforPage.openEmployeeListPage();
	 
	 myInforPage.openSubMenuPage(driver, "PIM", "Employee List");
	 employeeListPage = pageGenerator.getEmployeeListPage(driver);
	 
	 log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
	 verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
	 employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", empFullName);
	 verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
	 
	 log.info("Add_New_01 - Step 14: Click to 'Search' button");
	 //employeeListPage.clickToSearchButton();
	 employeeListPage.clickToButtonById(driver, "searchBtn");
	 verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
	 
	 //log.info("Add_New_01 - Step 15: Verify Employee Information displayed at 'Result Table'");
	 //verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), empLastName); 
	 
	 
}
 
 
  //@Test
  public void Employee_02_Upload_Avatar() { 
	  log.info("Upload_Avatar_02 - Step 01: Login with employee role");
	  loginPage = employeeListPage.logoutToSystem(driver);
	  dashboardPage = loginPage.loginToSystem(driver,  empUserName, empPassword);
	  
	  log.info("Upload_Avatar_02 - Step 02: Open 'My Info' menu page");
	  dashboardPage.openMenuPage(driver, "My Info");
	  myInforPage = pageGenerator.getMyInfoPage(driver);
	  
	  log.info("Upload_Avatar_02 - Step 03: Click to Change Photo Image");
	  myInforPage.clickToChangePhotoImage();
	  
	  log.info("Upload_Avatar_02 - Step 04: Upload new avatar image");
	  myInforPage.uploadImage(driver, avatarFilePath);
	  
	  log.info("Upload_Avatar_02 - Step 05: Click to Upload button");
	  myInforPage.clickToButtonById(driver, "btnSave");
	  
	  verifyTrue(myInforPage.isJQueryAjaxLoadedSuccess(driver));
		 
	  log.info("Upload_Avatar_02 - Step 06: Verify success message is displayed");
	  verifyTrue(myInforPage.isSuccessMessageDisplay(driver, "Successfully Uploaded"));

	  verifyTrue(myInforPage.isJQueryAjaxLoadedSuccess(driver));
		 
	  
	  log.info("Upload_Avatar_02 - Step 07: Verify new avatar image is displayed");
	  verifyTrue(myInforPage.isNewAvatarImageDisplay());
	  
  }
  
  //@Test
  public void Employee_03_Personal_Details() {
	  log.info("Personal Details 03 - Step 01: Click to 'Personal Details' tab at side bar");
	  myInforPage.openTabAtSideBarByName("Personal Details");
	  
	  log.info("Personal Details 03 - Step 02: Verify all fields at 'Personal Details' form are disabled");
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_txtEmpFirstName"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_txtEmpLastName"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_txtLicenNo"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_txtEmployeeId"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_txtSINNo"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_cmbMarital"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_optGender_1"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_optGender_2"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_DOB"));
	  
	  log.info("Personal Details 03 - Step 03: Click Edit button at 'Personal Details' form");
	  myInforPage.clickToButtonById(driver, "btnSave");
	  
	  log.info("Personal Details 03 - Step 04: Verify 'Employee Id' textbox is disabled");
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_txtEmployeeId"));
	  
	  log.info("Personal Details 03 - Step 05: Verify 'Driver's Lience Number' textbox is disabled ");
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_txtLicenNo"));
	  
	  log.info("Personal Details 03 - Step 06: Verify 'SSN Number' textbox is disabled");
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_txtNICNo"));
	  
	  log.info("Personal Details 03 - Step 07: Verify 'SIN Number' textbox is disabled");
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_txtSINNo"));
	  
	  log.info("Personal Details 03 - Step 08: Verify 'Date Of Birth' textbox is disabled");
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "personal_DOB"));
	  
	  log.info("Personal Details 03 - Step 09: Enter new value to 'First Name' textbox");
	  myInforPage.enterToTextboxByID(driver, "personal_txtEmpFirstName", editEmpFirstName);
	  
	  log.info("Personal Details 03 - Step 10: Enter new value to 'Last Name' textbox");
	  myInforPage.enterToTextboxByID(driver, "personal_txtEmpLastName", editEmpLastName);
	  
	  log.info("Personal Details 03 - Step 11: Select new value to 'Gender' radio button");
	  myInforPage.clickToRadioByLabel(driver ,editEmpGender);
	  
	  log.info("Personal Details 03 - Step 12: Select new value to 'Marital Status' dropdown");
	  myInforPage.selectItemInDropdownByID(driver, "personal_cmbMarital", editEmpMaritalStatus);
	  
	  log.info("Personal Details 03 - Step 13: Select new value to 'Nationality' dropdown");
	  myInforPage.selectItemInDropdownByID(driver, "personal_cmbNation", editEmpNationality);
	  
	  log.info("Personal Details 03 - Step 14: Click to 'Save' button at Personal Details");
	  myInforPage.clickToButtonById(driver, "btnSave");
	  
	  log.info("Personal Details 03 - Step 15: Verify Success Message is displayed");
	  verifyTrue(myInforPage.isSuccessMessageDisplay(driver, "Successfully Save"));
	  
	  log.info("Personal Details 03 - Step 16: Verify 'First Name' textbox update successfully");
	  assertEquals(myInforPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"), editEmpFirstName);
	  
	  log.info("Personal Details 03 - Step 17: Verify 'Last Name' textbox update successfully");
	  assertEquals(myInforPage.getTextboxValueByID(driver, "personal_txtEmpLastName"), editEmpLastName);
	  
	  log.info("Personal Details 03 - Step 18: Verify 'Gender' radio button update successfully");
	  verifyTrue(myInforPage.isRadioButtonSelectedByLabel(driver, editEmpGender));
	  
	  log.info("Personal Details 03 - Step 19: Verify 'Marital Status' dropdown update successfully");
	  verifyEquals(myInforPage.getSelectedValueInDropdownByID(driver, "personal_cmbMarital"), editEmpMaritalStatus);
	  
	  log.info("Personal Details 03 - Step 20: Verify 'Nationality' dropdown value update successfully");
	  verifyEquals(myInforPage.getSelectedValueInDropdownByID(driver, "personal_cmbNation"), editEmpNationality);
	   
	  log.info("Personal Details 03 - Step 21: Verify 'Employee ID' textbox is correct");
	  verifyEquals(myInforPage.getTextboxValueByID(driver, "personal_txtEmployeeId"), employeeID);
  }
  
  //@Test
  public void Employee_04_Contact_Details() {
	  log.info("Contact Details 04 - Step 01: Click to 'Contact Details' tab at side bar");
	  myInforPage.openTabAtSideBarByName("Contact Details");
	  
	  log.info("Contact Details 04 - Step 02: Verify all fields at 'Contact Details' form are disabled");
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "contact_street1"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "contact_street2"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "contact_city"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "contact_province"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "contact_emp_zipcode"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "contact_country"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "contact_emp_hm_telephone"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "contact_emp_mobile"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "contact_emp_work_telephone"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "contact_emp_work_email"));
	  verifyFalse(myInforPage.isFieldEnableByName(driver, "contact_emp_oth_email"));
	  
	  log.info("Contact Details 04 - Step 03: Click to 'Edit' button at 'Contact Details' form");
	  myInforPage.clickToButtonById(driver, "btnSave");
	  	  
	  log.info("Contact Details 04 - Step 04: Enter new value to 'Address Street 1' textbox");
	  myInforPage.enterToTextboxByID(driver, "contact_street1", contactEmpStreet1);
	  
	  log.info("Contact Details 04 - Step 05: Enter new value to 'Address Street 2' textbox");
	  myInforPage.enterToTextboxByID(driver, "contact_street2", contactEmpStreet2);
	  
	  log.info("Contact Details 04 - Step 06: Enter new value to 'City' textbox");
	  myInforPage.enterToTextboxByID(driver, "contact_city", contactEmpCity);
	  
	  log.info("Contact Details 04 - Step 07: Enter new value to 'State/Provice' textbox");
	  myInforPage.enterToTextboxByID(driver, "contact_province", contactEmpProvince);
	  
	  log.info("Contact Details 04 - Step 08: Enter new value to 'Zip/Postal Code' textbox");
	  myInforPage.enterToTextboxByID(driver, "contact_emp_zipcode", contactEmpZipcode);
	  
	  log.info("Contact Details 04 - Step 09: Enter new value to 'Zip/Postal Code' textbox");
	  myInforPage.enterToTextboxByID(driver, "contact_emp_zipcode", contactEmpZipcode);
	  
	  log.info("Contact Details 04 - Step 10: Select new value to 'Country' dropdown list");
	  myInforPage.selectItemInDropdownByID(driver, "contact_country", contactEmpContry);
	  
	  log.info("Contact Details 04 - Step 11: Enter new value to 'Home Telephone' textbox");
	  myInforPage.enterToTextboxByID(driver, "contact_emp_hm_telephone", contactEmpHomePhone);
	  
	  log.info("Contact Details 04 - Step 12: Enter new value to 'Mobile' textbox");
	  myInforPage.enterToTextboxByID(driver, "contact_emp_mobile", contactEmpMobile);
	  
	  log.info("Contact Details 04 - Step 13: Enter new value to 'Work Phone' textbox");
	  myInforPage.enterToTextboxByID(driver, "contact_emp_work_telephone", contactEmpWorkPhone);
	  
	  log.info("Contact Details 04 - Step 14: Enter new value to 'Work Email' textbox");
	  myInforPage.enterToTextboxByID(driver, "contact_emp_work_email", contactEmpWorkEmail);
	  
	  log.info("Contact Details 04 - Step 15: Enter new value to 'Other Email' textbox");
	  myInforPage.enterToTextboxByID(driver, "contact_emp_oth_email", contactEmpOtherEmail);
	  
	  log.info("Contact Details 04 - Step 16: Click to 'Save' button at 'Contact Details' form");
	  myInforPage.clickToButtonById(driver, "btnSave");
	  
	  verifyTrue(myInforPage.isJQueryAjaxLoadedSuccess(driver));
		 
	  log.info("Contact Details 04 - Step 17: Verify success message is displayed");
	  verifyTrue(myInforPage.isSuccessMessageDisplay(driver, "Successfully Saved"));
	  
	  verifyTrue(myInforPage.isJQueryAjaxLoadedSuccess(driver));
		 
	  log.info("Contact Details 04 - Step 18: Verify 'Address Street 1' textbox add successfully");
	  assertEquals(myInforPage.getTextboxValueByID(driver, "contact_street1"), contactEmpStreet1);
	  
	  log.info("Contact Details 04 - Step 19: Verify 'Address Street 2' textbox add successfully");
	  assertEquals(myInforPage.getTextboxValueByID(driver, "contact_street2"), contactEmpStreet2);
	  
	  log.info("Contact Details 04 - Step 20: Verify 'City' textbox add successfully");
	  assertEquals(myInforPage.getTextboxValueByID(driver, "contact_city"), contactEmpCity);
	  
	  log.info("Contact Details 04 - Step 21: Verify 'State/Province' textbox add successfully");
	  assertEquals(myInforPage.getTextboxValueByID(driver, "contact_province"), contactEmpProvince);
	  
	  log.info("Contact Details 04 - Step 22: Verify 'Zip/Postal Code' textbox add successfully");
	  assertEquals(myInforPage.getTextboxValueByID(driver, "contact_emp_zipcode"), contactEmpZipcode);
	  
	  log.info("Contact Details 04 - Step 23: Verify 'Country' dropdown select successfully");
	  assertEquals(myInforPage.getSelectedValueInDropdownByID(driver, "contact_country"), contactEmpContry);
	  
	  log.info("Contact Details 04 - Step 24: Verify 'Home Telephone' textbox add successfully");
	  assertEquals(myInforPage.getTextboxValueByID(driver, "contact_emp_hm_telephone"), contactEmpHomePhone);
	  
	  log.info("Contact Details 04 - Step 25: Verify 'Mobile' textbox add successfully");
	  assertEquals(myInforPage.getTextboxValueByID(driver, "contact_emp_mobile"), contactEmpMobile);
	  
	  log.info("Contact Details 04 - Step 26: Verify 'Work Telephone' textbox add successfully");
	  assertEquals(myInforPage.getTextboxValueByID(driver, "contact_emp_work_telephone"), contactEmpWorkPhone);
	  
	  log.info("Contact Details 04 - Step 27: Verify 'Work Email' textbox add successfully");
	  assertEquals(myInforPage.getTextboxValueByID(driver, "contact_emp_work_email"), contactEmpWorkEmail);
	  
	  log.info("Contact Details 04 - Step 28: Verify 'Other Email' textbox add successfully");
	  //assertEquals(myInforPage.getTextboxValueByID(driver, "contact_emp_oth_emaill"), contactEmpOtherEmail);
	  
  }
  
  //@Test
  public void Employee_05_Emergency_Contacts() {
	  log.info("Emergency_Contacts 05 - Step 01: Click to 'Emergency_Contacts' tab at side bar");
	  myInforPage.openTabAtSideBarByName("Emergency Contacts");
	  
	  log.info("Emergency_Contacts 05 - Step 02: Click to 'Add' button at 'Assigned Emergency Contacts' form");
	  myInforPage.clickToButtonById(driver, "btnAddContact");
	  
	  log.info("Emergency_Contacts 05 - Step 03: Enter new value to 'Name' textbox");
	  myInforPage.enterToTextboxByID(driver, "emgcontacts_name", emergencyEmpContactName);
	  
	  log.info("Emergency_Contacts 05 - Step 04: Enter new value to 'Relationship' textbox");
	  myInforPage.enterToTextboxByID(driver, "emgcontacts_relationship", emergencyEmpContactRelationship);
	  
	  log.info("Emergency_Contacts 05 - Step 05: Enter new value to 'Phone' textbox");
	  myInforPage.enterToTextboxByID(driver, "emgcontacts_homePhone", emergencyEmpContactPhone);
	  
	  log.info("Emergency_Contacts 05 - Step 06: Click to 'Save' button at 'Assigned Emergency Contacts' form");
	  myInforPage.clickToButtonById(driver, "btnSaveEContact");
	  
	  verifyTrue(myInforPage.isJQueryAjaxLoadedSuccess(driver));
		
	  log.info("Emergency_Contacts 05 - Step 07: Verify success message is displayed");
	  verifyTrue(myInforPage.isSuccessMessageDisplay(driver, "Successfully Saved"));
	  
	  verifyTrue(myInforPage.isJQueryAjaxLoadedSuccess(driver));
	  
	  log.info("Emergency_Contacts 05  - Step 08: Verify Name info displayed at 'Result Table'");
	  verifyEquals(myInforPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"), emergencyEmpContactName); 
	  
	  log.info("Emergency_Contacts 05  - Step 09: Verify Relationship info displayed at 'Result Table'");
	  verifyEquals(myInforPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Relationship", "1"), emergencyEmpContactRelationship); 
	  
	  log.info("Emergency_Contacts 05  - Step 10: Verify Home Phone info displayed at 'Result Table'");
	  verifyEquals(myInforPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Home Telephone", "1"), emergencyEmpContactPhone); 
		
  }
 
  //@Test
  public void Employee_06_Dependents() {
	  log.info("Assigned Dependents 06 - Step 01: Click to 'Dependents' tab at side bar");
	  myInforPage.openTabAtSideBarByName("Dependents");
	  
	  log.info("Assigned Dependents 06 - Step 02: Click to 'Add' button at 'Dependents' form");
	  myInforPage.clickToButtonById(driver, "btnAddDependent");
	  
	  log.info("Assigned Dependents 06 - Step 03: Enter new value to 'Name' textbox");
	  myInforPage.enterToTextboxByID(driver, "dependent_name", dependentEmpName);
	  
	  log.info("Assigned Dependents 06 - Step 04: Select value to 'Relationship' checkbox");
	  myInforPage.selectItemInDropdownByID(driver, "dependent_relationshipType", dependentEmpRelationshipValue);
	   
	  log.info("Assigned Dependents 06 - Step 05: Enter new value to 'Date Of Birth' textbox");
	  myInforPage.enterToTextboxByID(driver, "dependent_dateOfBirth", dependentEmpDOB);
	  
	  log.info("Assigned Dependents 06 - Step 06: Click to 'Save' button at 'Add Dependents' form");
	  myInforPage.clickToButtonById(driver, "btnSaveDependent");
	  
	  verifyTrue(myInforPage.isJQueryAjaxLoadedSuccess(driver));
		
	  log.info("Dependent_Contacts 05 - Step 07: Verify success message is displayed");
	  verifyTrue(myInforPage.isSuccessMessageDisplay(driver, "Successfully Saved"));
	  
	  verifyTrue(myInforPage.isJQueryAjaxLoadedSuccess(driver));
	  
	  log.info("Dependent_Contacts 05  - Step 08: Verify Name info displayed at 'Result Table'");
	  verifyEquals(myInforPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"), dependentEmpName); 
	  
	  log.info("Dependent_Contacts 05  - Step 09: Verify Relationship info displayed at 'Result Table'");
	  verifyEquals(myInforPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "1"), dependentEmpRelationshipValue); 
	  
	  log.info("Emergency_Contacts 05  - Step 10: Verify Date Of Birth displayed at 'Result Table'");
	  verifyEquals(myInforPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "1"), dependentEmpDOB); 
		  
  }
  
  //@Test
  public void Employee_07_Edit_View_Job() {
	  
  }
 
  //@Test
  public void Employee_08_Edit_View_Salary() {
	  
  }
  
  //@Test
  public void Employee_09_Edit_View_Tax() {
	  //Note lam sau 
	  //xu ly table van chua verify du lieu dc
	  //Qualification co table as null
	  //TC chay ko on dinh
	  //Switch role test case Job/ Salary
	  //Git ko add code len dc
	  
  }
  
  //@Test
  public void Employee_10_Qualifications() {
	  log.info("Work Experience 10 - Step 01: Click to 'Qualifications' tab at side bar");
	  myInforPage.openTabAtSideBarByName("Qualifications");
	  
	  log.info("Work Experience 10 - Step 02: Click to 'Add' button at 'Add Work Experience' form");
	  myInforPage.clickToButtonById(driver, "addWorkExperience");
	    
	  log.info("Work Experience 10 - Step 03: Enter new value to 'Company' textbox");
	  myInforPage.enterToTextboxByID(driver, "experience_employer", experienceEmpCompany);
	  
	  log.info("Work Experience 10 - Step 04: Enter new value to 'Job Title' textbox");
	  myInforPage.enterToTextboxByID(driver, "experience_jobtitle", experienceEmpJobTitle);
	  
	  log.info("Work Experience 10 - Step 05: Enter new value to 'From' textbox");
	  myInforPage.enterToTextboxByID(driver, "experience_from_date", experienceEmpFromDate);
	  
	  log.info("Work Experience 10 - Step 06: Enter new value to 'To' textbox");
	  myInforPage.enterToTextboxByID(driver, "experience_to_date", experienceEmpToDate);
	  
	  log.info("Work Experience 10 - Step 07: Enter new value to 'Comment' textbox");
	  myInforPage.enterToTextboxByID(driver, "experience_comments", experienceEmpComment);
	  
	  log.info("Work Experience 10 - Step 08: Click to 'Save' button at 'Add Experience' form");
	  myInforPage.clickToButtonById(driver, "btnWorkExpSave");
	  
	  verifyTrue(myInforPage.isJQueryAjaxLoadedSuccess(driver));
		
	  log.info("Work Experience 10 - Step 09: Verify success message is displayed");
	  verifyTrue(myInforPage.isSuccessMessageDisplay(driver, "Successfully Saved"));
	  
	  verifyTrue(myInforPage.isJQueryAjaxLoadedSuccess(driver));
	  
	  log.info("Work Experience 10  - Step 10: Verify Company info displayed at 'Result Table'");
	  //verifyEquals(myInforPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"), dependentEmpName); 
	  
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
	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage; 
	EmployeeListPO employeeListPage;
	MyInfoPO myInforPage;
	
}
