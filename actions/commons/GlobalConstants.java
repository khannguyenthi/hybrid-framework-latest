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
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
	
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
	

}
