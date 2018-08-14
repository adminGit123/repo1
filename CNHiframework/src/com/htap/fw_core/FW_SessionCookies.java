package com.htap.fw_core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.htap.fw_core.FW_PropertyReader;

/**
 * This class is getting the session cookies after successful login into Jira using selenium WebDriver
 * object after successful login to Jira.
 * <p>
 * <b>Usage:</b><br>
 * <code>FW_SessionCookies fw_SessionCookies = new FW_SessionCookies();</code>
 * <p>
 *
 * @author Vishal Kumar
 *
 */
public class FW_SessionCookies {
	private static Logger logger = LogManager.getLogger(FW_SessionCookies.class.getName());

	@SuppressWarnings("rawtypes")
	Map map = new HashMap<String, String>();
	FW_PropertyReader fw_PropertyReader = new FW_PropertyReader();
	WebDriver driverCookies;

	/**
	 * This method is getting the session cookies into a Map object using selenium WebDriver object
	 * after logging in to Jira.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>FW_SessionCookies fw_SessionCookies = new FW_SessionCookies();</br>
	 * fw_SessionCookies.getCookies()</code>
	 * <p>
	 *
	 * @return map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getCookies() {
		logger.debug("[SessionCookies:getCookies()] Getting cookies for the current session");

		try {

			String phantom_path = (System.getProperty("user.dir") + FW_Const.BACK_SLASH + fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_PHANTOMJS_DRIVER_PATH));
			System.setProperty("phantomjs.binary.path", phantom_path);


			driverCookies = new PhantomJSDriver();

			driverCookies.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driverCookies.get(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_JIRA_URI));

			driverCookies.navigate().refresh();

			WebElement username = driverCookies.findElement(By.xpath("//*[@id='userNameInput']"));

			WebElement password = driverCookies.findElement(By.xpath("//*[@id='passwordInput']"));

			WebElement signinButton = driverCookies.findElement(By.xpath("//*[@id='submitButton']"));

			username.sendKeys(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_JIRA_USER));
			password.sendKeys(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_JIRA_PASS));
			signinButton.click();

			driverCookies.get("https://jiraict.cnhind.com/rest/auth/1/session");

			Set<Cookie> cks = driverCookies.manage().getCookies();

			for (Cookie ck : cks) {

				map.put(ck.getName(), ck.getValue());

			}

			driverCookies.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return map;
	}
}
