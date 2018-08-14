
package com.htap.fw_core;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.htap.fw_core.FW_Enums.appType;
import com.htap.fw_core.FW_Enums.platform;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * <h1>This is the base test plan class for application layer Test plan Case class. All the automation test plan will extend this base class. </h1>
 * <p>
 * This class does the setup for before suite, before test case class, post test case and post test suite.
 * This class will create appropriate driver for the test execution based on the various values fetched from framework.properties which is set by FW_Init
 * class.
 * <p>
 * <b>Usage:</b><br>
 * <code>public class TestPlan extends FW_TestPlan</code>
 * @author Aman Kumar
 */
public class FW_TestPlan {
	private long startTime;
	private long endTime;
	private long totalExecutionTime;
	private int executed;
	private int passed;
	private int failed;

	/**
	 * This method will return time the when the test suite has started.
	 * @return Test Suite execution start time.
	 */
	private long getStartTime() {
		return startTime;
	}

	/**
	 * This method will return the time when the test suite execution stopped/ended.
	 * @return Test Suite execution end time.
	 */
	private long getEndTime() {
		return endTime;
	}

	/**
	 * This method will return total time taken in executing the test suite.
	 * @return totalExecutionTime
	 */
	private long getTotalExecutionTime() {
		return totalExecutionTime;
	}

	/**
	 * This method will return the total number of test cases executed for a suite.
	 * @return Total number of test cases executed for a suite.
	 */
	public int getExecuted() {
		return executed;
	}

	/**
	 * This method will return the total number of passed test cases executed for a suite.
	 * @return Total number of passed test cases executed for a suite.
	 */
	public int getPassed() {
		return passed;
	}

	/**
	 * This method will return the total number of failed test cases executed for a suite.
	 * @return Total number of failed test cases executed for a suite.
	 */
	public int getFailed() {
		return failed;
	}

	/**
	 * This Method used to set the time when test suite execution started.
	 * @param startTime Parameter for test suite start time.
	 */
	private void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * This Method used to set the time when test suite execution stopped/ended.
	 * @param endTime Parameter for test suite execution stop time.
	 */
	private void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * This Method used to set total time for executing the test suite. It is calculated by subtracting the execution end time minus execution start time.
	 */
	private void setTotalExecutionTime() {
		this.totalExecutionTime = (this.getEndTime() - this.getStartTime()) / FW_Const.THOUSAND;
	}

	/**
	 * This method will set the total number of test cases executed for a suite.
	 * @param executed Total number of test cases executed for a suite.
	 */
	public void setExecuted(int executed) {
		this.executed = executed;
	}

	/**
	 * This method will set the total number of passed test cases executed for a suite.
	 * @param passed Total number of passed test cases executed for a suite.
	 */
	public void setPassed(int passed) {
		this.passed = passed;
	}

	/**
	 * This method will set the total number of failed test cases executed for a suite.
	 * @param failed Total number of failed test cases executed for a suite.
	 */
	public void setFailed(int failed) {
		this.failed = failed;
	}

	/**
	 * Object initialization for various classes.
	 */
	public FW_Init fw_Init = new FW_Init();
	public FW_PropertyReader fw_PropertyReader = FW_PropertyReader.getInstance();
	public FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();

	public FW_WebDriver fw_WebDriver = new FW_WebDriver();
	public FW_AndroidDriver fw_AndroidDriver = new FW_AndroidDriver();


	private static Logger logger = LogManager.getLogger(FW_TestPlan.class.getName());

	String device = fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_DEVICE);

	/**
	 * This method will be executed at the start of the suite and executed only once for a suite.
	 */
	@BeforeSuite (alwaysRun = true)
	public void testplanEnter() {
		setStartTime(System.currentTimeMillis());
		logger.info("[FW_TestPlan : testplanEnter] Testplan execution started...");
	}

	/**
	 * This method will be executed before execution of each test case method/class.
	 * @param platform Type of the platform, WINDOWS or ANDROID
	 * @param appType Type of Application type, WEB or API
	 * @param browser Type of Browser, CHROME, FIREFOX, IE, SAFARI
	 * @param browserVersion Version number of the browser
	 * @throws IOException Input output exception
	 */
	@BeforeMethod (alwaysRun = true)
	@Parameters ({"platform", "appType", "browser", "browserVersion"})
	public void testcaseEnter(@Optional String platform, @Optional String appType, @Optional String browser, @Optional String browserVersion) throws IOException {

		if (!platform.isEmpty() || platform != null) {
			fw_Init.setPlatform(FW_Enums.platform.valueOf(platform));
		}

		if (!browser.isEmpty() || browser != null) {
			fw_Init.setBrowser(FW_Enums.browser.valueOf(browser));
		}

		if (!appType.isEmpty() || appType != null) {
			fw_Init.setAppType(FW_Enums.appType.valueOf(appType));
		}

		if (!browserVersion.isEmpty() || browserVersion != null) {
			fw_Init.setBrowserVersion(browserVersion);
		}

		logger.info("[FW_TestPlan : testcaseEnter] Testcase execution started with: " + fw_Init.getPlatform() + " " + fw_Init.getAppType() + " " + fw_Init.getBrowser());

		String runBeforeSetup = fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RUN_BEFORE_SETUP);
		if (!runBeforeSetup.isEmpty()) {
			fw_UtilFunctions.runExternalProgram(runBeforeSetup);

		}

		/**
		 * Setting capabilities for Android WEB, Windows WEB by getting the values from framework.properties.
		 */

		DesiredCapabilities mobileCapabilities = new DesiredCapabilities();
		mobileCapabilities.setCapability("platformName", fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_PLATFORM));
		mobileCapabilities.setCapability("deviceName", device);
		mobileCapabilities.setCapability("browserName", "Chrome");

		DesiredCapabilities ieCap = DesiredCapabilities.internetExplorer();
		DesiredCapabilities chromeCap = DesiredCapabilities.chrome();
		DesiredCapabilities safariCap = DesiredCapabilities.safari();
		DesiredCapabilities ffCap = DesiredCapabilities.firefox();

		/**
		 * Switch case condition logic to create appropriate driver for test execution.
		 */

		switch (fw_Init.getPlatform()) {
		case ANDROID:
			switch (fw_Init.getAppType()) {
			default : case WEB:

				switch (fw_Init.getMobileTool()) {

				default : case APPIUM:
					try {
						logger.debug("[FW_TestPlan : testcaseEnter] Creating Android Driver.");
						fw_AndroidDriver.setAndroidDriver(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_DEFAULT_URL), mobileCapabilities);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("[FW_TestPlan : testcaseEnter] Exception: " + e.getMessage());
					}
					break;
				}
				break;
			}

			break;


		default : case WINDOWS:
			switch (fw_Init.getAppType()) {

			case API:
				logger.debug("[testCaseEnter] Creatinh API drivers");
				break;

			default : case WEB:
				switch (fw_Init.getBrowser()) {
				case IE:
					File file = new File(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_IE_WEBDRIVER_PATH));
					System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
					ieCap.setCapability("version", fw_Init.getBrowserVersion());
					file = new File(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_IE_WEBDRIVER_PATH));
					System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

					try {
						logger.debug("[FW_TestPlan : testcaseEnter] Creating WINDOWS IE WebDriver");
						fw_WebDriver.setWebDriver(new InternetExplorerDriver(ieCap));
						WebDriver d = fw_WebDriver.getWebDriver();
						d.manage().deleteAllCookies();
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("[FW_TestPlan : testcaseEnter] Exception: " + e.getMessage());
					}

					break;
				case CHROME:

					file = new File(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_CHROME_WEBDRIVER_PATH));
					System.setProperty	("webdriver.chrome.driver", file.getAbsolutePath());

					ChromeOptions options = new ChromeOptions();
					options.addArguments("test-type");
					options.addArguments("disable-extensions");

					chromeCap.setCapability(ChromeOptions.CAPABILITY, options);
					chromeCap.setCapability("version", fw_Init.getBrowserVersion());

					try {
						logger.debug("[FW_TestPlan : testcaseEnter] Creating WINDOWS CHROME WebDriver");
						fw_WebDriver.setWebDriver(new ChromeDriver(chromeCap));
						WebDriver d = fw_WebDriver.getWebDriver();
						d.manage().deleteAllCookies();
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("[FW_TestPlan : testcaseEnter] Exception: " + e.getMessage());
					}

					break;

				case SAFARI:
					file = new File(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_SAFARI_EXTENSION));
					System.setProperty	("webdriver.safari.driver", file.getAbsolutePath());

					safariCap.setJavascriptEnabled(Boolean.valueOf(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_JS_ENABLED)));
					safariCap.setCapability("version", fw_Init.getBrowserVersion());

					try {
						logger.debug("[FW_TestPlan : testcaseEnter] Creating WINDOWS SAFARI WebDriver");
						fw_WebDriver.setWebDriver(new SafariDriver(safariCap));
						WebDriver d = fw_WebDriver.getWebDriver();
						d.manage().deleteAllCookies();
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("[FW_TestPlan : testcaseEnter] Exception: " + e.getMessage());
					}


					break;
				default: case FIREFOX:
					File file1 = new File(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_FIREFOX_GEKODRIVER_PATH));
					System.setProperty("webdriver.gecko.driver", file1.getAbsolutePath());

					//ffCap.setCapability("version", fw_Init.getBrowserVersion());
					ffCap.setCapability("marionette", true);
					try {
						logger.debug("[FW_TestPlan : testcaseEnter] Creating WINDOWS FIREFOX WebDriver");
						fw_WebDriver.setWebDriver(new FirefoxDriver(ffCap));
						WebDriver d = fw_WebDriver.getWebDriver();
						d.manage().deleteAllCookies();
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("[FW_TestPlan : testcaseEnter] Exception: " + e.getMessage());
					}

					break;
				}
				break;
			}
			break;
		}

	}
	/**
	 *This method will be executed after each test method execution.
	 *This will check the boolean value of key FORCE_CLOSE_APP within framework.properties.
	 *If this is true it will the driver instance at suite level.
	 */
	@AfterMethod (alwaysRun = true)
	public void testcaseExit() {
		boolean forceCloseApp = Boolean.parseBoolean(fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_FORCE_CLOSE_APP));

		if (forceCloseApp) {
			if ((fw_Init.getAppType().equals(appType.WEB))) {
				if (fw_Init.getPlatform().equals(platform.WINDOWS)) {
					WebDriver d = fw_WebDriver.getWebDriver();
					try {
						d.quit();
					}
					catch (NoSuchWindowException e) {
						logger.warn("[FW_TestPlan : testcaseExit] NoSuchWindowException: Browser main window might have already been closed " + e.getMessage());
					}
				}

				if (fw_Init.getPlatform().equals(platform.ANDROID)) {
					AndroidDriver<MobileElement> ad = fw_AndroidDriver.getAndroidDriver();
					try {
						ad.quit();
					}
					catch (NoSuchWindowException e) {
						logger.warn("[FW_TestPlan : testcaseExit] NoSuchWindowException: Browser main window might have already been closed " + e.getMessage());
					}
				}
			}

		}
		logger.info("[FW_TestPlan : testcaseExit] Testcase execution completed...");

		String runAfterTeardown = fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RUN_AFTER_TEARDOWN);
		if (!runAfterTeardown.isEmpty()) {
			fw_UtilFunctions.runExternalProgram(runAfterTeardown);
		}
	}

	/**
	 * This method will be executed once post complete suite execution and
	 * will get the the the total number of PASSED or FAILED test cases and total
	 * test suite execution time.
	 */
	@AfterSuite (alwaysRun = true)
	public void testplanExit() {
		setEndTime(System.currentTimeMillis());
		setTotalExecutionTime();

		logger.info("[testplanExit] EXECUTION SUMMARY");
		logger.info("[testplanExit] Testcases executed: " + getExecuted());
		if (getExecuted() > 0) {
			logger.info("[FW_TestPlan : testplanExit] Testcases Passed: " + getPassed() + " (" + ((getPassed() * FW_Const.HUNDRED)/getExecuted()) + "%)");
			logger.info("[FW_TestPlan : testplanExit] Testcases Failed: " + getFailed() + " (" + ((getFailed() * FW_Const.HUNDRED)/getExecuted()) + "%)");
		}
		if (getTotalExecutionTime() >= FW_Const.SIXTY)
		{
			logger.info("[FW_TestPlan : testplanExit] Executed in: " + getTotalExecutionTime() / FW_Const.SIXTY + " minutes.");
		}
		else
		{
			logger.info("[FW_TestPlan : testplanExit] Executed in: " + getTotalExecutionTime() + " seconds.");
		}


	}

}
