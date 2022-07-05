package commons;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

@Getter

public class GlobalConstants {
	
	private static GlobalConstants globalInstance;
	
	private GlobalConstants() {
		//tao ra private constructor để ko cho phép truy cap truc tiep tu ben ngoai bang cach new 1 doi tuong ma phai tao hàm getGlobalConstants()
		//Dung cach Singleton
	}
	
	public static synchronized GlobalConstants getGlobalConstants() {
		if(globalInstance==null) {
			globalInstance = new GlobalConstants();
		}
		return globalInstance;
	}
	
	/* //Neu ko dung Lombok de get gia tri bien thi dung ham getter/ setter hoac dung cach generate getter/setter nhung sinh ra nhieu code
	public long getShortTimeout() {
		return shortTimeout();
	} */ 
	
	private final String portalPageUrl = "https://demo.nopcommerce.com/"; //public static final String PORTAL_PAGE_URL -> vi ko phai la hang so nua (hang so binh thuong) private final
	private final String adminPageUrl = "https://admin-demo.nopcommerce.com/";
	
	private final String projectPath = System.getProperty("user.dir");
	private final String osName= System.getProperty("os.name");
	private final String javaVersion = System.getProperty("java.version");
	
	//Windown - Linux - Mac co the dung dc path nÃ y 	
	private final String uploadFile = projectPath + File.separator + "uploadFiles" + File.separator;
	//Tro ve 1 thu muc mac dinh cua user e.g: Download
	private final String downloadFile = projectPath + File.separator + "downloadFiles";
	//Browser log
	private final String browserLog = projectPath + File.separator + "browserLogs" + File.separator;
	
	private final String dragDropHtml5 = projectPath + File.separator + "dragDropHTML5";
	
	private final String reportingScreenshot = projectPath + File.separator + "reportNGImages" + File.separator;
	
	private final String autoITScript = projectPath + File.separator + "autoIT";
	
	//DB Account/ User/ Port/ Pass for DEV
	private final String dbDevUrl = "192.168.1.15:8888";
	private final String dbDevUser = "automationfc";
	private final String dbDevPass = "123456";
	//DB Account/ User/ Port/ Pass for Testing
	private final String dbTestUrl = "192.168.1.15:8888";
	private final String dbTestUser = "automationfc";
	private final String dbTestPass = "123456";
	
	private final long shortTimeout = 5;
	private final long longTimeout = 10;
	private final long retryTestFail = 3;
	
	//For Browserstack config
	private final String broswerUsername = "minhkhannguyenth_36jPvN";
	private final String browserAutomateKey = "jpvxfdFtDpABm6JCUp4C";
	private final String browserStackUrl = "https://" + broswerUsername + ":" + browserAutomateKey + "@hub-cloud.browserstack.com/wd/hub";
	
	//SauceLab config
	private final String sauceUserName = "oauth-khanntm-04168";
	private final String sauceAutomateKey = "f803253c-31ac-448d-ab9d-17122f8c1472";
	private final String sauceUrl = "https://" + sauceUserName + ":" + sauceAutomateKey + "@ondemand.apac-southeast-1.saucelabs.com:443/wd/hub";
	
	//Crossbrowser config
	private final String crossUsername = "".replace("@", "%40");
	private final String crossAutomateKey = "";
	private final String crossUrl = "http://" + crossUsername + ":" + crossAutomateKey + "@...";
	
	//Lamda Test config
	private final String lambdaUsername = "";
	private final String lambdaAutomateKey = "";
	private final String lambdaUrl = "https://" + lambdaUsername + ":" + lambdaAutomateKey + "@...";
}

	
/* Neu dung theo cach nay thi se ko tuan theo tính encapsulation - bao ve bao mat che dau du lieu, du lieu la public thì có the thong tin se bi lead ra ben ngoai
 * Giai pháp là chi ko dc truy cap thong qua thuoc tinh, ma phai qua ham Get() Set() thong qua doi tuong
public class GlobalConstants {
	public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	//Windown - Linux - Mac co the dung dc path nÃ y 	
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
} */
