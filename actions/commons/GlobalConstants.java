package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	//Windown - Linux - Mac co the dung dc path này 	
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	//Tro ve 1 thu muc mac dinh cua user e.g: Download
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	//Browser log
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
	
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;
	
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	
	//DB Account/ User/ Port/ Pass for DEV
	public static final String DB_DEV_URL = "192.168.1.15:8888";
	public static final String DB_DEV_USER = "automationfc";
	public static final String DB_DEV_PASS = "123456";
	//DB Account/ User/ Port/ Pass for Testing
	public static final String DB_TEST_URL = "192.168.1.15:8888";
	public static final String DB_TEST_USER = "automationfc";
	public static final String DB_TEST_PASS = "123456";
	
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 10;
	public static final long RETRY_TEST_FAIL = 3;
	
	//For Browserstack config
	public static final String BROWSER_USERNAME = "minhkhannguyenth_36jPvN";
	public static final String BROWSER_AUTOMATE_KEY = "jpvxfdFtDpABm6JCUp4C";
	public static final String BROWSER_STACK_URL = "https://" + BROWSER_USERNAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	//SauceLab config
	public static final String SAUCE_USERNAME = "oauth-khanntm-04168";
	public static final String SAUCE_AUTOMATE_KEY = "f803253c-31ac-448d-ab9d-17122f8c1472";
	public static final String SAUCE_URL = "https://" + SAUCE_USERNAME + ":" + SAUCE_AUTOMATE_KEY + "@ondemand.apac-southeast-1.saucelabs.com:443/wd/hub";
	
	//Crossbrowser config
	public static final String CROSS_USERNAME = "".replace("@", "%40");
	public static final String CROSS_AUTOMATE_KEY = "";
	public static final String CROSS_URL = "http://" + CROSS_USERNAME + ":" + CROSS_AUTOMATE_KEY + "@...";
	
	//Lamda Test config
	public static final String LAMBDA_USERNAME = "";
	public static final String LAMBDA_AUTOMATE_KEY = "";
	public static final String LAMBDA_URL = "https://" + LAMBDA_USERNAME + ":" + LAMBDA_AUTOMATE_KEY + "@...";
	
}
