package com.htap.fw_core;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * The base class that creates and holds <code>androidDriver</code> object of type <code>AndroidDriver</code>.
 * <p>
 * <b>Usage:</b><br>
 * <code>FW_AndroidDriver fw_AndroidDriver = new FW_AndroidDriver();</code>
 * <p>
 * @author      Aman Kumar
 */
public class FW_AndroidDriver {
	private static Logger logger = LogManager.getLogger(FW_AndroidDriver.class.getName());

	private static AndroidDriver<MobileElement> androidDriver;
	/**
	 * Creates and initializes <code>androidDriver</code> object of type <code>AndroidDriver</code>.
	 * <p>
	 * In order to create <code>androidDriver</code>, user needs to pass the
	 * <code>AndroidDriver</code> URL and <code>DesiredCapabilities</code> for the
	 * selected browser.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>FW_AndroidDriver fw_AndroidDriver = new FW_AndroidDriver();<br>
	 * fw_AndroidDriver.setAndroidDriver("http://127.0.0.1:4723/wd/hub", androidCap);</code>
	 * <p>
	 * @param url Remote android driver URL
	 * @param capabilities DesiredCapabilities settings for androidDriver
	 */
	public void setAndroidDriver(String url, DesiredCapabilities capabilities) {
		try {
			androidDriver = new AndroidDriver<MobileElement>(new URL(url), capabilities);
		} catch (MalformedURLException e) {
			logger.error("[FW_AndroidDriver : setAndroidDriver] MalformedURLException:" + e.getMessage());
		}
	}

	/**
	 * Returns the initialized <code>androidDriver</code> object of type <code>AndroidDriver</code>
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_AndroidDriver fw_AndroidDriver = new FW_AndroidDriver();<br>
	 * fw_AndroidDriver.setAndroidDriver("http://127.0.0.1:4723/wd/hub", androidCap);
	 * AndroidDriver androidDriver = fw_AndroidDriver.getAndroidDriver();
	 * </code>
	 * <p>
	 * @return AndroidDriver
	 */
	public AndroidDriver<MobileElement> getAndroidDriver() {
		return androidDriver;
	}

}