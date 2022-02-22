package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;
import pageUIs.RegisterPageUI;

public class HomePageObject extends BasePage {
	//bien gloal
	private WebDriver driver;
		
	public	HomePageObject(WebDriver driver) {
		//neu co có tao thì nó se tạo ngầm mac dinh ko có tham so ntn
		//ien local
		this.driver = driver;
		System.out.println("Driver at class HomePageOject = " + driver.toString());
	}
		public void clickToRegisterLink() {
			waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
			clickToElement(driver,HomePageUI.REGISTER_LINK);
			 
		}
		public void clickToLoginLink() {
			waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
			clickToElement(driver, HomePageUI.LOGIN_LINK);
			
		}
		public String getMyAccountLink(String string) {
			waitForElementVisible(driver,HomePageUI.MY_ACCOUNT_LINK);
			return getElementText(driver,HomePageUI.MY_ACCOUNT_LINK);
			
		}
		public boolean isMyAccountLinkDisplayed() {
			waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
			return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
		}

}