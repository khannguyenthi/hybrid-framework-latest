package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.portal.nopCommerce.PageGeneratorManager;
import pageObject.portal.nopCommerce.UserAddressPageObject;
import pageObject.portal.nopCommerce.UserCustomerInfoPageObject;
import pageObject.portal.nopCommerce.UserMyProductReviewPageObject;
import pageObject.portal.nopCommerce.UserRewardPointPageObject;
import pageObjects.liveGuru.AccountInformationPageObject;
import pageObjects.liveGuru.AddressBookPageObject;
import pageObjects.liveGuru.BilingAgreementPageObject;
import pageObjects.liveGuru.MyOrdersPageObject;
import pageObjects.liveGuru.PageGeneratorManager_Live_Guru;
import pageObjects.liveGuru.RecurringProfilesPageObject;
import pageUIs.liveGuru.BasePageUI_Live_Guru;
import pageUIs.nopCommerce.user.BasePageUI;
import pageUIs.nopCommerce.user.CustomerInforPageUI;

public class BasePage {
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	//Neu run base on (III) thì fai de protected (vì class nào kế thừa thì mới dc dùng), 3 cai private moi dung
	//Nhung 2 type tren ko run dc. 
	public void openPageUrl(WebDriver driver, String pageUrl)
	{
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}	
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return  waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public void switchToWindowByID(WebDriver driver, String windowPageID) {
		 Set<String> windowPageIDList = driver.getWindowHandles();
		
		 for (String eachWindow : windowPageIDList) {
			if (!eachWindow.equals(windowPageID)) {
				driver.switchTo().window(eachWindow);
				break;
			}
		}
		
	}
	
	public void swithToWindowByTitle(WebDriver driver, String expectedTitlePage) {
		 Set<String> windowPageIDList = driver.getWindowHandles();
		 for (String eachWindow : windowPageIDList) {
			driver.switchTo().window(eachWindow);
			 String actualPageTitle = driver.getTitle().trim();
			System.out.println(actualPageTitle);
			if(actualPageTitle.equals(expectedTitlePage)) {
				break;
			 }
		   }
		}
	
	public void closeAllWindowWithoutParent(WebDriver driver, String parentPageID) {
		 Set<String> windowPageIDList = driver.getWindowHandles();
		
		 for (String eachWindow : windowPageIDList) {
			 if(!eachWindow.equals(parentPageID)) {
				driver.switchTo().window(eachWindow);
			 	driver.close();
			 }
			 driver.switchTo().window(parentPageID);
		   }
	}
	
	//Locator Type: id=/ css= xpath=/ name=/ class=
	private By getByLocator(String locatorType) {
		By by = null;
		System.out.println("Locattor Type = " + locatorType);
		if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		}else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator is not support");
		}
		
		return by;
	}
	
	private String getDynamicXpath(String locatorType, String...dynamicValues) {
		System.out.println("Locator Type efore =" + locatorType);
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[])	dynamicValues);
		}
		for (String value : dynamicValues) {
			System.out.println("Values map to locator = "+ value);
		}
		
		System.out.println("Locator Type After = " + locatorType);
		return locatorType;
	}
	

	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	private List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	
	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click(); 
	}
	
	public void clickToElement(WebDriver driver, String locatorType, String...dynamicValues) {
		locatorType = getDynamicXpath(locatorType, dynamicValues);
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click(); 
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValues) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValues);
	}
	
	public void sendkeyToElement(WebDriver driver, String textValues, String locatorType, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValues);
	}
	
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	
	public String getElementText(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByValue(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByValue(textItem);
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}
	
	public void sleepInSecond(long second)
	{
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItemText) {
		 		getWebElement(driver, parentXpath).click();
				sleepInSecond(2);
				
				WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
				
			 	explicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
				
				 List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
				 //System.out.println("Tong so luong item trong dropdown = " + childItems.size());
				 for (WebElement tempElement : childItems) {
					//System.out.println("Item text = " + tempElement.getText());
				 	if (tempElement.getText().trim().equals(expectedItemText)) {
					 	if (tempElement.isDisplayed()) {
							//System.out.println("Click by Selenium " + tempElement.getText());
							tempElement.click();
							sleepInSecond(1);
						} else {
							JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
						 	//System.out.println("Click by Js" + tempElement.getText());
							jsExecutor.executeScript("arguments[0].scrollIntoView(true)", tempElement);
							sleepInSecond(1);
						 	jsExecutor.executeScript("arguments[0].click();", tempElement);
							sleepInSecond(1);
						}
						 break;
					}
				}

	}
	
	public String getElementAttribute (WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	} 
	//getByLocator
	
	public String getHexaColorFromRGBR(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}
	
	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver,  getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}
	
	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void hightlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}
	
	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}
	
	public void scrollToElementOnTop(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}
	
	public void scrollToElementOnDown(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locatorType));
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}
	
	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		}
		return false;
	}
	
	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition <Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return((Long) jsExecutor.executeScript("return jQuery.active")==0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		System.out.println("Driver at wait for element  = " + driver.toString());
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		System.out.println("Driver at wait for element  = " + driver.toString());
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	//Toi uu o lessio Level 07 Switch Page
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
		clickToElement(driver, BasePageUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}
	
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}
	
	public UserRewardPointPageObject openRewardPoint(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINTS_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINTS_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}
	
	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO);
		clickToElement(driver, BasePageUI.CUSTOMER_INFO);
		return PageGeneratorManager.getUserCustomerInforPage(driver);
	}
	//Toi uu o lession Level 09 Dynamic Locator
	public BasePage openPagesAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInforPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
		default:
			throw new RuntimeException("Invalid page nam at My Account area");
		}
	}
	
	public void openPagesAtMyAccountByPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
	}
	
	//Level 8 Switch role
	public AccountInformationPageObject openAccountInformationPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI_Live_Guru.ACCOUNT_INFORMATION_LINK);
		clickToElement(driver, BasePageUI_Live_Guru.ACCOUNT_INFORMATION_LINK);
		return PageGeneratorManager_Live_Guru.getAccountInformationPage(driver);
	}
	public AddressBookPageObject openAddressBookPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI_Live_Guru.ADDRESS_BOOK_LINK);
		clickToElement(driver, BasePageUI_Live_Guru.ADDRESS_BOOK_LINK);
		return PageGeneratorManager_Live_Guru.getAddressBookPage(driver);
	}
	
	public MyOrdersPageObject openMyOrdersPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI_Live_Guru.MY_ORDERS_LINK);
		clickToElement(driver, BasePageUI_Live_Guru.MY_ORDERS_LINK);
		return PageGeneratorManager_Live_Guru.getMyOrdersPage(driver);
	}
	
	public BilingAgreementPageObject openBilingAgreementPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI_Live_Guru.BILLING_AGREEMENT_LINK);
		clickToElement(driver, BasePageUI_Live_Guru.BILLING_AGREEMENT_LINK);
		return PageGeneratorManager_Live_Guru.getBillingAgreementPage(driver);
	}
	
	public RecurringProfilesPageObject openRecurringProfilePage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI_Live_Guru.RECURRING_PROFILE_LINK);
		clickToElement(driver, BasePageUI_Live_Guru.RECURRING_PROFILE_LINK);
		return PageGeneratorManager_Live_Guru.getRecurringProfilesPage(driver);
	}
		
	private long longTimeout = 30;
}
 
