
package com.htap.fw_core;

/**
 * Base class that holds constant values for various parameters. Users can directly refer to the constants using
 * <code>FW_Const</code> class.
 * <p>
 * <b>Usage:</b>
 * <pre>
 * {@code
 * fw_WebDriverFunctions.waitForElement(By.xpath(LOCATOR_DIRECTIONSBUTTON), "DirectionsButton Button", FW_Const.SIXTY);
 * fw_PropertyReader.getAppProperty(FW_Const.APP_URL);
 * }
 * </pre>
 * @author Rohit Kothari
 */
public class FW_Const {

/*********************  FW property file and constants *********************/

	/**Relative file path of config folder for application properties, reporter properties and resource files */
	//public static final String PROPERTIES_RELATIVE_FILE_PATH = "D://myHTAP//frameworkCNHi//src//com//test//properties";

	public static final String PROPERTIES_RELATIVE_FILE_PATH = "PROPERTIES_RELATIVE_FILE_PATH";

	public static final String PROPERTIES_FILE_RELATIVE_PATH = "PROPERTIES_FILES";

	/**Relative file path of config folder for framework and logger properties */
	//public static final String CONFIG_RELATIVE_FILE_PATH = "D:\\myHTAP\\frameworkCNHi\\src\\config\\";
	public static final String CONFIG_RELATIVE_FILE_PATH = "/src/config/";

	public static final String FOLDER_PAGES = "/pages";
	public static final String FOLDER_TESTDATA = "/testdata";
	public static final String FOLDER_TESTCASES = "/testcases";
	public static final String FOLDER_CSV = "/csv";

	public static final String TEST_INPUT_DATA_RELATIVE_PATH = "testdata/csv/";

	/** Framework Properties file name */
	public static final String FW_PROPERTIES_FILE_NAME = "framework.properties";

	/** Application Properties file name */
	public static final String FW_APP_PROPERTIES_FILE_NAME = "APP_PROPERTIES";

	/** Log4j Properties file name */
	public static final String FW_LOG4J_PROPERTIES_FILE_NAME = "log4j.properties";

	/** Constant for LIB key of framework.properties */
    public static final String SOURCE_FOLDER = "src";

    /** Constant for LIB key of framework.properties */
    public static final String FW_PROP_LIB = "LIB";

    /** Constant for WORKING_ROOT key of framework.properties */
    public static final String FW_PROP_WORKING_DIR = "WORKING_DIR";

    /** Constant for platform keys of framework.properties */
    public static final String FW_PROP_PLATFORM = "PLATFORM";

    /** Constant for app type keys of framework.properties */
    public static final String FW_PROP_APP_TYPE = "APP_TYPE";

    /** Constant for BROWSER key of framework.properties */
    public static final String FW_PROP_BROWSER = "BROWSER";

    /** Constant for platform keys of framework.properties */
    public static final String FW_PROP_MOBILE_TOOL = "MOBILE_TOOL";

    /** Constant for IE_WEBDRIVER_EXE_PATH key of framework.properties */
    public static final String FW_PROP_IE_WEBDRIVER_PATH = "IE_WEBDRIVER_PATH";

    /** Constant for CHROME_WEBDRIVER_EXE_PATH key of framework.properties */
    public static final String FW_PROP_CHROME_WEBDRIVER_PATH = "CHROME_WEBDRIVER_PATH";

    /** Constant for PHANTOMJS_DRIVER_PATH key of framework.properties */
    public static final String FW_PROP_PHANTOMJS_DRIVER_PATH = "PHANTOMJS_DRIVER_PATH";

    /** Constant for PHANTOMJS_DRIVER key of framework.properties */
    public static final String FW_PROP_PHANTOMJS_DRIVER_EXE = "PHANTOMJS_DRIVER";

    /** Constant for SAFARI_EXTENSION key of framework.properties */
    public static final String FW_PROP_SAFARI_EXTENSION = "SAFARI_EXTENSION";

    /** Constant for IE_WEBDRIVER_EXE key of framework.properties */
    public static final String FW_PROP_IE_WEBDRIVER_EXE = "IE_WEBDRIVER";

    /** Constant for CHROME_WEBDRIVER_EXE key of framework.properties */
    public static final String FW_PROP_CHROME_WEBDRIVER_EXE = "CHROME_WEBDRIVER";

    /** Constant for FIREFOX_PROFILE_PATH key of framework.properties */
    public static final String FW_PROP_FIREFOX_PROFILE_PATH = "FIREFOX_PROFILE_PATH";

    /** Constant for IE_WEBDRIVER_EXE_PATH key of framework.properties */
    public static final String FW_PROP_FIREFOX_GEKODRIVER_PATH = "FIREFOX_GEKODRIVER_PATH";

    public static final String FW_PROP_FORCE_CLOSE_APP = "FORCE_CLOSE_APP";

    /** Constant for BROWSER version of framework.properties */

    public static final String FW_PROP_IE_VERSION = "IE_VERSION";
    public static final String FW_PROP_FIREFOX_VERSION = "FIREFOX_VERSION";
    public static final String FW_PROP_CHROME_VERSION = "CHROME_VERSION";
    public static final String FW_PROP_SAFARI_VERSION = "SAFARI_VERSION";
    public static final String FW_PROP_API_VERSION = "API_VERSION";

    /** Constant for MOBILE platform keys of framework.properties */

    public static final String FW_PROP_APP_COMMAND_TIMEOUT = "APP_COMMAND_TIMEOUT";
    public static final String FW_PROP_PLATFORM_VERSION = "PLATFORM_VERSION";
    public static final String FW_PROP_DEFAULT_URL = "DEFAULT_URL";
    public static final String FW_PROP_JS_ENABLED = "JS_ENABLED";

    /** Constant for MOBILE platform keys of application.properties */

    // public static final String FW_PROP_PLATFORM_VERSION = "PLATFORM_VERSION";
    public static final String FW_PROP_DEVICE = "DEVICE";

    /** Constant for content type of app.properties */
    public static final String APP_PROP_CONTENT_TYPE = "CONTENT_TYPE";
    /** Constant for method type of app.properties */
    public static final String APP_PROP_METHOD_TYPE = "METHOD_TYPE";
    /** Constant for base uri of app.properties */
    public static final String APP_PROP_BASE_URI = "BASE_URI";
    /** Constant for auth uri of app.properties */
    public static final String APP_PROP_AUTH_URI = "AUTH_URI";
    /** Constant for redirect uri of app.properties */
    public static final String APP_PROP_REDIRECT_URI = "REDIRECT_URI";
    /** Constant for subscription key of app.properties */
    public static final String APP_PROP_SUBSCRIPTION_KEY = "SUBSCRIPTION_KEY";
    /** Constant for grant type of app.properties */
    public static final String APP_PROP_GRANT_TYPE = "GRANT_TYPE";
    /** Constant for client id of app.properties */
    public static final String APP_PROP_CLIENT_ID = "CLIENT_ID";
    /** Constant for client secret of app.properties */
    public static final String APP_PROP_CLIENT_SECRET = "CLIENT_SECRET";
    /** Constant for access token api of app.properties */
    public static final String APP_PROP_TOKEN_API = "TOKEN_API";
    /** Constant for auth reponse type of app.properties */
    public static final String APP_PROP_AUTH_RESPONSE_TYPE = "AUTH_RESPONSE_TYPE";
    /** Constant for auth scope of app.properties */
    public static final String APP_PROP_AUTH_SCOPE = "AUTH_SCOPE";
    /** Constant for auth audience api of app.properties */
    public static final String APP_PROP_AUTH_AUDIENCE = "AUTH_AUDIENCE";
    /** Constant for rest oauth user of app.properties */
    public static final String APP_PROP_REST_USER = "REST_USER";
    /** Constant for rest oauth password of app.properties */
    public static final String APP_PROP_REST_PATH = "REST_PASS";


    /** Constant for jira uri of app.properties */
    public static final String APP_PROP_JIRA_URI = "JIRA_URI";
    public static final String APP_PROP_JIRA_USER = "JIRA_USER";
    public static final String APP_PROP_JIRA_PASS = "JIRA_PASS";

    /** Constant for proxy of framework.properties */
    public static final String FW_PROP_PROXY_HOST = "PROXY_HOST";
    public static final String FW_PROP_PROXY_PORT = "PROXY_PORT";
    public static final String FW_PROP_PROXY_SET = "PROXY_SET";

    /** Constants for application.properties */
	public static final String APP_PROP_APP_HTTP = "APP_HTTP";
	public static final String APP_PROP_APP_URL = "APP_URL";
	public static final String APP_PROP_FARMAPP_URL = "FARMAPP_URL";

	public static final String APP_PROP_APP_USER = "APP_USER";
	public static final String APP_PROP_APP_PASS = "APP_PASS";

	public static final String APP_PROP_RUN_BEFORE_SETUP="RUN_BEFORE_SETUP";
    public static final String APP_PROP_RUN_AFTER_TEARDOWN="RUN_AFTER_TEARDOWN";

	public static final String APP_PROP_APP_LOCALE = "APP_LOCALE";
	public static final String DEFAULT_LOCALE = "en_us";


	public static final String APP_RESOURCE_FILE = "resource";
	public static final String APP_PROPERTIES_FILE_EXT = "properties";

	 /** Constant for Extent Report Results folder of app.properties */

	public static final String APP_PROP_RESULTS_FOLDER = "RESULTS_FOLDER";
	public static final String APP_PROP_GOLD_IMG_FOLDER = "GOLD_IMG_FOLDER";
	public static final String APP_PROP_RUNTIME_IMG_FOLDER = "RUNTIME_IMG_FOLDER";
    /*********************  common constants *********************/

    /** Constant used for Backspace key press */
    public static final String BACKSPACE = "<bksp>";

    /** Constant used for Back slash "/" character */
    public static final String BACK_SLASH = "/";

   /** Constant used for slash "\" character */
    public static final String SLASH = "\\";

    /** Constant used for Dot "." character */
    public static final String DOT = ".";

    /** Constant used for Colon ":" character */
    public static final String COLON = ":";

    /** Constant used for Underscore "_" character */
    public static final String UNDERSCORE = "_";

    /** Constant used for Ampersand character */
    public static final String AMPERSAND = "\\&";

    public static final String OPEN_BRACKET = "{";
    public static final String CLOSE_BRACKET= "}";

    public static final String OPEN_ROUND_BRACKET = "\\(";
    public static final String CLOSE_ROUND_BRACKET= "\\)";

    public static final String BREAK_LINE = "\n";

	public static final Integer ONE = 1;
	public static final Integer TWO = 2;
	public static final Integer THREE = 3;
	public static final Integer FOUR = 4;
	public static final Integer FIVE = 5;
	public static final Integer SIX = 6;
	public static final Integer SEVEN = 7;
	public static final Integer EIGHT = 8;
	public static final Integer NINE = 9;
	public static final Integer TEN = 10;

	public static final Integer THIRTY = 30;
	public static final Integer SIXTY = 60;
	public static final Integer EIGHTY = 60;
	public static final Integer HUNDRED = 100;
	public static final Integer FIVE_HUNDRED = 500;
	public static final Integer THOUSAND = 1000;

    public static final String EMPTY_STRING = "";

	public static final String ID = "ID";
	public static final String XPATH = "XPATH";

	public static final String RESOURCE_ID = "resource-id";
	public static final String CLASS = "class";
	public static final String INDEX = "index";
	public static final String TEXT = "text";
	public static final String CONTENT_DESC = "content-desc";


	public static final String SPACE = " ";
	public static final String SINGLE_QUOTE = "'";
	public static final String HYPHEN = "-";
	public static final String COMMA = ",";
	public static final String ESCAPE_SLASH_DOT = "\\.";
	public static final String PLUS_MINUS = "\\±";
	public static final String QUESTION_MARK = "\\?";
	public static final String ID_SEPARATOR = ":id/";
	public static final String STRING_TEXT = "text";
	public static final String STRING_TEXT_CLASS = "Text";



}
