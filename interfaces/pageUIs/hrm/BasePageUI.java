package pageUIs.hrm;

public class BasePageUI {
	public static final String MENU_BY_PAGE_NAME = "xpath=//div[@id='mainMenu']//a[string()='%s']";
	public static final String BUTTON_BY_ID = "xpath=//input[@id='%s']";
	public static final String TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DROPDOWN_BY_ID = "xpath=//select[@id='%s']";

	public static final String ANY_FIELD_BY_ID = "xpath=//*[@id='%s']"; //Đại diện cho tất cả các field: textbox, radio, checkbox, button.. có id 
	
	public static final String CHECKBOX_BY_LABEL = "xpath=//label[text()='%s']/following-sibling::input";
	public static final String RADIO_BY_LABEL = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String TABLE_HEADER_BY_ID_AND_NAME = "xpath=//table[@id='%s']//th[string()='%s']/preceding-sibling::th";
	public static final String TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//table[@id='%s']/tbody/tr['%s']/td['%s']";
	public static final String SUCCESS_MESSAGE_VALUE = "xpath=//div[@class='inner']/div[contains(text(),'%s')]";
	
	//Hard locator
	public static final String WELCOME_USER_LINK = "xpath=//a[@id='welcome']";
	public static final String LOGOUT_LINK = "xpath=//a[text()='Logout']"; // //div[@id='welcome-menu'] hoi thay
	public static final String USER_LOGIN_TEXTBOX = "xpath=//input[@id='txtUsername']";
	public static final String USER_PASSWORD_TEXTBOX = "xpath=//input[@id='txtPassword']";
	public static final String LOGIN_BUTTON = "xpath=//input[@id='btnLogin']";
	public static final String UPLOAD_FILE = "xpath=//input[@type='file']";
	
}
