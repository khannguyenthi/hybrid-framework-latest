package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import pageUIs.hrm.BasePageUI;
import pageUIs.jQuery.uploadFile.BasePageJQueryUI;
import pageUIs.liveGuru.BasePageUI_Live_Guru;
import pageUIs.nopCommerce.user.BasePageNopCommerceUI;
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
	
	public Set<Cookie> getAllCookies (WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie); 
		}
		sleepInSecond(3);
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
	public By getByLocator(String locatorType) {
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
	
	public String getDynamicXpath(String locatorType, String...dynamicValues) {
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
	

	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
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
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValues, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValues);
	}
	
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText().trim();
	}
	
	public String getElementText(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText().trim();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator, String...dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
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
	
	public String getElementAttribute (WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver,  getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
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

	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
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
		try {
			//tìm thấy element
			//case 1: tra ve true
			//case 2: chay dc tra ve false
			return getWebElement(driver, locatorType).isDisplayed();	
		} catch (Exception e) {
			//case 3: fail = tra ve false
			return false;
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	
	//Case nay dung cho case 2 & 3 - ko display co trong DOM & ko display & ko có trong DOM
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, shortTimeout); 
		List<WebElement> elements = getListWebElement(driver, locator);	
		// Neu minh gan = 5 apply cho tat ca cac step ve sau do: FindElement/ findElements
		overrideGlobalTimeout(driver, longTimeout);
		
		if(elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
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
	
	public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String...dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
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
	
	public String getElementValueByJSXpath(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath", "");
		return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", document, null, XpathResult.FIRST_ORDERED_NOTE_TYPE, null).singleNodeValue).val()");
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
	
	public boolean isImageLoaded(WebDriver driver, String locatorType, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		
		return status;
	}
	
	public boolean isJQueryAJAXLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition <Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	
		        @Override
		        public Boolean apply(WebDriver driver) {
		            return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
		        }
		    };
		    return explicitWait.until(jQueryLoad);
		  }

		
	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override 
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (windown.jQuery != null) && (jQuery.active == 0);");
			}
		};
		
		return explicitWait.until(jQueryLoad);
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
	/* 
	 * Wait for element un-displayed in DOM or not in DOM and override implicit timeout
	 * */
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideGlobalTimeout(driver, longTimeout);
		
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
	
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		//Duong dan cua thu muc upload file chay dc tren ca WIN MAC LINUx
		String filePath = GlobalConstants.UPLOAD_FILE;
		
		// duong dan cua tat ca cac file
		// 1 file: Java.png
		// nhieu file: String[] = {"Java.png", "Python.png"}
		String fullFileName ="";
		for(String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
	}
	
	//Toi uu o lession Level 07 Switch Page
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}
	
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}
	
	public UserRewardPointPageObject openRewardPoint(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.REWARD_POINTS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.REWARD_POINTS_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}
	
	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.CUSTOMER_INFO);
		clickToElement(driver, BasePageNopCommerceUI.CUSTOMER_INFO);
		return PageGeneratorManager.getUserCustomerInforPage(driver);
	}
	//Toi uu o lession Level 09 Dynamic Locator
	public BasePage openPagesAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
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
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
	}
	
	/** Enter to dynamic textbox by ID
	 * @author Khan Nguyen
	 * @param driver
	 * @param textboxID
	 * @param value
	 */
	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXT_BOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageNopCommerceUI.DYNAMIC_TEXT_BOX_BY_ID, value, textboxID);
	}
	
	
	/** Enter to dynamic button by text
	 * @author Khan Nguyen
	 * @param driver
	 * @param buttonText
	 * @param value
	 */
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}
	
	/** Select item in dropdown by Name attribute
	 * @author Khan Nguyen
	 * @param driver
	 * @param dropdownAttribute
	 * @param value
	 */
	
	public void selectToDropdownByName(WebDriver driver, String dropdownAttributeName, String itemValue) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
		selectItemInDefaultDropdown(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
		
	}
	
	/** Click to dynamic radio button by label
	 * @author Khan Nguyen
	 * @param driver
	 * @param radio button
	 * @param value
	 */
	public void clickToRadioButtonByLabel(WebDriver driver, String radioButtonLabelName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
	}
	
	
	/** Click to dynamic checkbox by label
	 * @author Khan Nguyen
	 * @param driver
	 * @param checkbox
	 * @param value
	 */
	/*public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);

	}*/
	
	/** Get value in textbox by textbox ID
	 * @param driver
	 * @param textboxID
	 * @return
	 */
	/*public String getTextboxValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXT_BOX_BY_ID,textboxID);
		//ten cua attribute inpect as value
		return getElementAttribute(driver, BasePageNopCommerceUI.DYNAMIC_TEXT_BOX_BY_ID, "value", textboxID);
	} */


	
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
	
	//HRM - Menu/ SubMenu/ ChildSubMenu 
	public void openMenuPage(WebDriver driver, String menuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
	}
	
	public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
	}
	
	public void openchildSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName, String childSubMenuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		
		//For childSubMenu must hover on subMenu instead of clicking on it
		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		hoverMouseToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);	
	}
	
	//HRM click to Button dynamic
	public void clickToButtonById(WebDriver driver, String buttonIDName) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
	}
	
	//Enter Textbox component dynamic
	public void enterToTextboxByID(WebDriver driver, String textboxIDName, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value,textboxIDName);	
	}
	//Get textbox value
	/**
	 * Get textbox value by textbox id 
	 * @param driver
	 * @param textboxIDName
	 * @return attribute value
	 * @author Khan Nguyen
	 */
	public String getTextboxValueByID(WebDriver driver, String textboxIDName) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxIDName);	
	}
	//Dropdown
	public void selectItemInDropdownByID(WebDriver driver, String dropDownID, String valueItem) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_ID, dropDownID);
		selectItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, valueItem, dropDownID);
	}
	
	/**
	 * Get value of selected dropdown dynamic base on ID
	 * @param driver
	 * @param dropDownID
	 * @return value of selected dropdown
	 * @author Khan Nguyen
	 */
	public String getSelectedValueInDropdownByID(WebDriver driver, String dropDownID) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_ID, dropDownID);
		return getSelectedItemDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, dropDownID);
	}
	
	/**
	 * Click to checkbox by lable text dynamic
	 * @param driver
	 * @param checkboxLabelName
	 * @author Khan Nguyen
	 */
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	
	/**
	 * Click to radio by label text value
	 * @param driver
	 * @param radioLabelName
	 * @author Khan Nguyen
	 */
	public void clickToRadioByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
	}
	
	//Video 18 - 60 at 1:58' 
	public String getValueInTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_BY_ID_AND_NAME, tableID, headerName) +1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
	}
		
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	
}
 
