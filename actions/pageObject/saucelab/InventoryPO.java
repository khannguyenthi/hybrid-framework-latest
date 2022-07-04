package pageObject.saucelab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.saucelab.InventoryPageUI;

public class InventoryPO extends BasePage{
 WebDriver driver;

	public InventoryPO(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInSortDropdown(String itemText) {
		waitForElementClickable(driver, InventoryPageUI.SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, InventoryPageUI.SORT_DROPDOWN, itemText);
		
	}

	public boolean isProductNameSortAscending() {
		List<WebElement> productNameElements = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		
		List<String> productNameText = new ArrayList<String>();
		
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}
		
		System.out.println("Before sorting asc: -----------");	
		for(String product:productNameText) {
			System.out.println(product);
		}
		
		List<String> productNameTextClone = new ArrayList<String>();
		for(String product:productNameText) {
			productNameTextClone.add(product);
		}	
		
		
		Collections.sort(productNameTextClone);
		
		System.out.println("After sort asc:------------");
		for(String product:productNameTextClone) {
			System.out.println(product);
		}
		
		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductNameSortDescending() {
		
		List<WebElement> productNameElements = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		
		List<String> productNameText = new ArrayList<String>();
		
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}
		
		System.out.println("Before sorting desc: -----------");	
		for(String product:productNameText) {
			System.out.println(product);
		}
		
		List<String> productNameTextClone = new ArrayList<String>();
		for(String product:productNameText) {
			productNameTextClone.add(product);
		}	
		
		Collections.sort(productNameTextClone); //default as sort ascending
		Collections.reverse(productNameTextClone);
		
		System.out.println("After sort desc:------------");
		for(String product:productNameTextClone) {
			System.out.println(product);
		}
		
		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductPriceSortAscending() {
		
		List<WebElement> productPriceElements = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
		
		List<Float> productPriceValue = new ArrayList<Float>();
		
		for (WebElement productPrice : productPriceElements) {
			productPriceValue.add(Float.parseFloat(productPrice.getText().replace("$","")));
		}
		
		System.out.println("Before sorting asc: -----------");	
		for(Float productPrice:productPriceValue) {
			System.out.println(productPrice);
		}
		
		List<Float> productPriceTextClone = new ArrayList<Float>();
		for(Float productPrice:productPriceValue) {
			productPriceTextClone.add(productPrice);
		}
		
		Collections.sort(productPriceTextClone);
		
		System.out.println("After sort asc:------------");
		for(Float productPrice:productPriceTextClone) {
			System.out.println(productPrice);
		}
		
		return productPriceValue.equals(productPriceTextClone);
	}

	public boolean isProductPriceSortDescending() {
		List<WebElement> productPriceElements = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
		
		List<Float> productPriceValue = new ArrayList<Float>();
		
		for (WebElement productPrice : productPriceElements) {
			productPriceValue.add(Float.parseFloat(productPrice.getText().replace("$","")));
		}
		
		System.out.println("Before sorting asc: -----------");	
		for(Float productPrice:productPriceValue) {
			System.out.println(productPrice);
		}
		
		List<Float> productPriceTextClone = new ArrayList<Float>();
		for(Float productPrice:productPriceValue) {
			productPriceTextClone.add(productPrice);
		}
		
		Collections.sort(productPriceTextClone);
		Collections.reverse(productPriceTextClone);
		
		System.out.println("After sort asc:------------");
		for(Float productPrice:productPriceTextClone) {
			System.out.println(productPrice);
		}
		
		return productPriceValue.equals(productPriceTextClone);
	}

  
}
