
package com.htap.fw_core;

/**
 * Base class that holds Enumerated types for various parameters. Users can directly refer to the Enums using
 * <code>FW_Enums</code> class.
 * <p>
 *
 * @author Rohit Kothari
 */
public class FW_Enums {

	public enum mobileTool {
		APPIUM,
	}

	public enum yesno {
		YES,
		NO;
	}

	public enum truefalse {
		TRUE,
		FALSE;
	}

	public enum browser {
		IE,
		FIREFOX,
		CHROME,
		SAFARI,
		APIAPP;
	}

	public enum platform {
		WINDOWS,
		ANDROID;
	}

	public enum appType  {
		WEB,
		API;
	}

	public enum logAs {
		INFO,
		WARNING,
		PASSED,
		FAILED;
	}

	public enum logStatus {
		ERROR,
		FAIL,
		FATAL,
		INFO,
		PASS,
		SKIP,
		UNKNOWN,
		WARNING;
	}

	public enum screenshotOf {
		BROWSER_PAGE,
		DESKTOP;
	}

	public enum contentType {
		JSON,
		XML,
		TEXT;
	}

	}
