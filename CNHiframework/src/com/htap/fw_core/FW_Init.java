package com.htap.fw_core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Base class that creates and holds execution <code>platform</code>,
 * <code>appType</code>, <code>browser</code>, <code>browser version</code>, and
 * <code>mobiletool</code> information for current execution cycle.
 * <p>
 * User can refer to these variables by creating object of type <code>FW_Init</code>
 * <p>
 * <b>Usage:</b><br>
 * <code>FW_Init fw_Init = new FW_Init();</code>
 * @author      Vishal Kumar
 */

public class FW_Init {

	FW_PropertyReader fw_PropertyReader = new FW_PropertyReader();
	private String log4JProperties = FW_Const.CONFIG_RELATIVE_FILE_PATH + FW_Const.FW_LOG4J_PROPERTIES_FILE_NAME;
	private static Logger logger = LogManager.getLogger(FW_Init.class.getName());

	private String workingDir;
	private FW_Enums.platform platform;
	private FW_Enums.browser browser;
	private FW_Enums.appType appType;
	private FW_Enums.mobileTool mobileTool;
	private String browserVersion;

	/**
	 * This is a constructor to initialize values of parameters <code>platform</code>, <code>browser</code>, <code>version</code>,
	 * <code>appType</code>, <code>mobileTool</code>
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();</code><br>
	 */
	public FW_Init() {
		logger.debug("[ FW_Init() ] Constructor to initialize member variables.");

		logger.debug("[FW_Init()] Working directory: " + System.getProperty("user.dir"));
		setWorkingDir(System.getProperty("user.dir"));

		PropertyConfigurator.configure(getWorkingDir()+log4JProperties);

		logger.debug("Initializing logger...");


			setPlatform(FW_Enums.platform.valueOf(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_PLATFORM)));

			setAppType(FW_Enums.appType.valueOf(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_APP_TYPE)));

			setBrowser(FW_Enums.browser.valueOf(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_BROWSER)));

			switch (getBrowser()) {
			case IE:
				setBrowserVersion(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_IE_VERSION));
				break;
			case FIREFOX:
				setBrowserVersion(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_FIREFOX_VERSION));
				break;
			case SAFARI:
				setBrowserVersion(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_SAFARI_VERSION));
				break;
			case APIAPP:
				setBrowserVersion(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_API_VERSION));
				break;
			default: case CHROME:
				setBrowserVersion(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_CHROME_VERSION));
				break;
			}

		String Appplatform=getPlatform().toString();

			if(Appplatform.equals("ANDROID")){
			setMobileTool(FW_Enums.mobileTool.valueOf(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_MOBILE_TOOL)));
			logger.info("[ FW_Init() ] MobileTool is: " + getMobileTool());

		}
	}

	/**
	 * Returns the operating system platform <code>FW_Enums.platform</code> chosen by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.getPlatform();</code>
	 * <p>
	 * @return platform
	 */
	public FW_Enums.platform getPlatform() {
		return platform;
	}

	/**
	 * Initializes and holds the name of the operating system platform <code>FW_Enums.platform</code> chosen for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.setPlatform(platform);</code>
	 * <p>
	 * @param platform Name of the operating system platform <code>FW_Enums.platform</code> chosen for test case execution.
	 */
	public void setPlatform(FW_Enums.platform platform) {
		this.platform = platform;
	}

	/**
	 * Returns the name of the browser <code>FW_Enums.browser</code> chosen by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.getBrowser();</code>
	 * <p>
	 * @return browser
	 */
	public FW_Enums.browser getBrowser() {
		return browser;
	}

	/**
	 * Initializes and holds the name of the browser <code>FW_Enums.browser</code> chosen for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.setBrowser(browser);</code>
	 * <p>
	 * @param browser Name of the browser <code>FW_Enums.browser</code> chosen for test case execution.
	 */
	public void setBrowser(FW_Enums.browser browser) {
		this.browser = browser;
	}
	/**
	 * Returns the type of application platform <code>FW_Enums.appType</code> chosen by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.getAppType();</code>
	 * <p>
	 * @return appType
	 */
	public FW_Enums.appType getAppType() {
		return appType;
	}
	/**
	 * Initializes and holds the type of application platform <code>FW_Enums.platform</code> chosen for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.setappType(appType);</code>
	 * <p>
	 * @param appType Type of the application platform <code>FW_Enums.appType</code> chosen for test case execution.
	 */
	public void setAppType(FW_Enums.appType appType) {
		this.appType = appType;
	}

	/**
	 * Returns the name of the mobile tool <code>FW_Enums.mobileTool</code> chosen by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.getMobileTool();</code>
	 * <p>
	 * @return mobileTool
	 */
	public FW_Enums.mobileTool getMobileTool() {
		return mobileTool;
	}

	/**
	 * Initializes and holds the name of the mobile tool <code>FW_Enums.mobileTool</code> chosen for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.setMobileTool(mobileTool);</code>
	 * <p>
	 * @param mobileTool Name of the mobile tool <code>FW_Enums.mobileTool</code> chosen for test case execution.
	 */
	public void setMobileTool(FW_Enums.mobileTool mobileTool) {
		this.mobileTool = mobileTool;
	}
	/**
	 * Returns the version of the browser chosen by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.getVersion();</code>
	 * <p>
	 * @return version
	 */
	public String getBrowserVersion() {
		return browserVersion;
	}
	/**
	 * Initializes and holds the version of the browser chosen for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.setVersion(version);</code>
	 * <p>
	 * @param browserVersion Version of the browser chosen for test case execution.
	 */
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	/**
	 * Initializes and holds the working directory from where test case execution is started.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.setWorkingDir(workingDir);</code>
	 * <p>
	 * @param workingDir Working directory from where test case execution is started.
	 */
	public void setWorkingDir(String workingDir) {
		this.workingDir = workingDir;
	}

	/**
	 * Returns the current working directory.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_Init fw_Init = new FW_Init();<br>
	 * fw_Init.getWorkingDir();</code>
	 * <p>
	 * @return workingDir
	 */
	public String getWorkingDir() {
		return workingDir;
	}

}
