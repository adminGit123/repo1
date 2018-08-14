package com.htap.fw_core;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
 * The base class to initialize the Driver,RemoteWebDriver which used in hybrid test automation platform. The object created from this class
 * initializes the <code>webDriver</code><code>RemoteWebDriver</code>
 * <p>
 * <b>Usage:</b><br>
 * <code>FW_WebDriver fw_WebDriver = new FW_WebDriver();</code>
 * <p>
 * @author      Rohit kothari
 */
public class FW_WebDriver {
	private static WebDriver webDriver;

	private static Logger logger = LogManager.getLogger(FW_WebDriver.class.getName());

	/**
	 * Creates and initializes browser specific <code>webDriver</code> object of type <code>WebDriver</code>.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>FW_WebDriver fw_WebDriver = new FW_WebDriver();<br>
	 * fw_WebDriver.setWebDriver(new ChromeDriver(chromeCap));<br>
	 * or fw_WebDriver.setWebDriver(new FirefoxDriver(firefoxCap));<br>
	 * or fw_WebDriver.setWebDriver(new InternetExplorerDriver(ieCap));</code>
	 * <p>
	 * @param driver Browser specific WebDriver<br>
	 */
	public void setWebDriver(WebDriver driver) {
		logger.debug("[setWebDriver] Creating new WebDriver");
		webDriver = driver;
	}

	/**
	 * Creates and initializes <code>webDriver</code> object of type <code>WebDriver</code>.
	 * <p>
	 * In order to create <code>webDriver</code>, user needs to pass the
	 * <code>WebDriver</code> URL and <code>DesiredCapabilities</code> for the
	 * selected browser.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>FW_WebDriver fw_WebDriver = new FW_WebDriver();<br>
	 * fw_WebDriver.setRemoteWebDriver("http://localhost:4444/wd/hub", chromeCap);</code>
	 * <p>
	 * @param url Remote web driver URL
	 * @param capabilities DesiredCapabilities settings for webDriver
	 */
	public void setRemoteWebDriver(String url, DesiredCapabilities capabilities) {
		try {
			logger.debug("[FW_WebDriver : setRemoteWebDriver] Creating new RemoteWebDriver using " + url + " Capabilities: " + capabilities);
			webDriver = new RemoteWebDriver(new URL(url), capabilities);
		} catch (MalformedURLException e) {
			logger.error("[FW_WebDriver : setRemoteWebDriver] MalformedURLException:" + e.getMessage());
		}
	}

	/**
	 * Returns the initialized <code>webDriver</code> object of type <code>WebDriver</code>
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriver fw_WebDriver = new FW_WebDriver();<br>
	 * fw_WebDriver.setRemoteWebDriver("http://localhost:4444/wd/hub", chromeCap);
	 * WebDriver webDriver = fw_WebDriver.getWebDriver();
	 * </code>
	 * <p>
	 * @return WebDriver
	 */
	public WebDriver getWebDriver() {
		return webDriver;
	}

}