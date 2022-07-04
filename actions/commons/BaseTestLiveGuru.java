package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;

import factoryBrowser.BrowserList;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestLiveGuru {
	private WebDriver driver;
	protected final Log log;
	private String projectPath = System.getProperty("user.dir");
	
	protected BaseTestLiveGuru() {
		log = LogFactory.getLog(getClass());
	}
	
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		
		 if(browserList == BrowserList.FIREFOX) {
			 WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
		 } 
		 else if (browserList == BrowserList.H_FIREFOX) {
			 WebDriverManager.firefoxdriver().setup();
			 FirefoxOptions options = new FirefoxOptions();
			 options.addArguments("--headless");
			 options.addArguments("window-size=1920*1080");
			 driver = new FirefoxDriver(options);
		 }
		 
		 else if(browserList == BrowserList.CHROME) {
			 WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
		 }
		 else if(browserList == BrowserList.H_CHROME) {
			 WebDriverManager.chromedriver().setup();
			 ChromeOptions options = new ChromeOptions();
			 options.addArguments("-headless");
			 options.addArguments("window-size=1920*1080");
			 driver = new ChromeDriver(options);
		 }
		 else if(browserList == BrowserList.OPERA) {
			 WebDriverManager.operadriver().setup();
			 driver = new OperaDriver();
		 }
		 else if(browserList == BrowserList.EDGE_CHROMIUM) {
			 WebDriverManager.edgedriver().setup();
			 driver = new EdgeDriver();
		 } else {
			 throw new RuntimeException("Browser name invalid.");
		 }
		 
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 driver.get(appUrl);	
		 return driver;
	}
	
	protected int generateFakeNumber() {
		  Random rand = new Random();
		  return rand.nextInt(9999);
	  }

		
	
}
