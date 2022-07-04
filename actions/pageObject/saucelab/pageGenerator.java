package pageObject.saucelab;

import org.openqa.selenium.WebDriver;

public class pageGenerator {
	public static LoginPO getLoginPage(WebDriver driver) {
		return new LoginPO(driver);
	}
	
	public static InventoryPO getInventoryPage(WebDriver driver) {
		return new InventoryPO(driver);
	}
	
	
	
}
