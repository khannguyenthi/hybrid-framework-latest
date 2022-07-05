package commons;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import factoryBrowser.BrowserList;
import factoryEnvironment.BrowserstackFactory;
import factoryEnvironment.CrossbrowserFactory;
import factoryEnvironment.EnvironmentList;
import factoryEnvironment.GridFactory;
import factoryEnvironment.LambdaFactory;
import factoryEnvironment.LocalFactory;
import factoryEnvironment.SaucelabFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	//private WebDriver driver; -> Cach 1 ko toi uu, vi khi parrallel se tao ra 3 thread gay ton bo nho
	//private static WebDriver driver; //Khai bao static de tranh khai bao nhieu lan, nhung run parralel th� failed do driver cua bien nay, dung cho thread khac or = null
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(); //cach 3 dung ThreadLocal
	protected final Log log;
	private String projectPath = System.getProperty("user.dir");
	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	protected WebDriver getBrowserDriver(String envName, String serverName, String browserName, String ipAddress, String portNumber, String osName, String osVersion) {
		switch (envName) {
		case "local":
			driver.set(new LocalFactory(browserName).createDriver());
			break;
			
		case "grid":
			driver.set(new GridFactory(browserName, ipAddress, portNumber).createDriver());
			break;
			
		case "browserStack":
			driver.set(new BrowserstackFactory(browserName, osName, osVersion).createDriver());
			break;
			
		case "saucelab":
			driver.set(new SaucelabFactory(browserName, osName).createDriver());
			break;
			
		case "crossBrowser":
			driver.set(new CrossbrowserFactory(browserName, osName).createDriver());
			break;
			
		case "lambda":
			driver.set(new LambdaFactory(browserName, osName).createDriver());
			break;
			
			
			default:
				driver.set(new LocalFactory(browserName).createDriver());
				break;
		}
		
		driver.get().manage().timeouts().implicitlyWait(GlobalConstants.getGlobalConstants().getShortTimeout(), TimeUnit.SECONDS);
		driver.get().manage().window().maximize();
		driver.get().get(getEnvironmentUrl(serverName));
		//driver.get.(getEnvironmentUrl(serverName));
		return driver.get();
	}
	/*
	protected WebDriver getBrowserDriverLocal(String browserName, String appUrl) {
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
		 //driver.get(getEnvironmentUrl(appUrl));
		 return driver.get();
	} */
	

	public WebDriver getDriverInstance() {
		return this.driver.get();
	}
	
	protected String getEnvironmentUrl(String serverName) {
		String envUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(serverName.toUpperCase());
		if(environment == EnvironmentList.DEV) {
			envUrl = "https://www.saucedemo.com/";
			//envUrl = "https://opensource-demo.orangehrmlive.com/";
		} else if (environment == EnvironmentList.TESTING) {
			envUrl = "https://admin-demo.nopcommerce.com/"; 
		} else if (environment == EnvironmentList.STAGING) {
			envUrl = "";
		} else if (environment == EnvironmentList.PRODUCTION) {
			envUrl = "";
		}
		return envUrl;
	}
	
	protected int generateFakeNumber() {
		  Random rand = new Random();
		  return rand.nextInt(9999);
	  }

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	 

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			System.out.println(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	protected void closeBrowserAndDriver(String envName) {
		if(envName.equals("local") || envName.equals("grid")) {
			String cmd = "";
			try {
				String osName = System.getProperty("os.name").toLowerCase();
				log.info("OS name = " + osName);

				String driverInstanceName = driver.get().toString().toLowerCase();
				log.info("Driver instance name = " + driverInstanceName);

				if (driverInstanceName.contains("chrome")) {
					if (osName.contains("window")) {
						cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
					} else {
						cmd = "pkill chromedriver";
					}
				} else if (driverInstanceName.contains("internetexplorer")) {
					if (osName.contains("window")) {
						cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
					}
				} else if (driverInstanceName.contains("firefox")) {
					if (osName.contains("windows")) {
						cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
					} else {
						cmd = "pkill geckodriver";
					}
				} else if (driverInstanceName.contains("edge")) {
					if (osName.contains("window")) {
						cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
					} else {
						cmd = "pkill msedgedriver";
					}
				} else if (driverInstanceName.contains("opera")) {
					if (osName.contains("window")) {
						cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
					} else {
						cmd = "pkill operadriver";
					}
				} else if (driverInstanceName.contains("safari")) {
					if (osName.contains("mac")) {
						cmd = "pkill safaridriver";
					}
				}

				if (driver != null) {
					driver.get().manage().deleteAllCookies();
					driver.get().quit();
					
					driver.remove();
				}
			} catch (Exception e) {
				log.info(e.getMessage());
			} finally {
				try {
					Process process = Runtime.getRuntime().exec(cmd);
					process.waitFor();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	 
}
