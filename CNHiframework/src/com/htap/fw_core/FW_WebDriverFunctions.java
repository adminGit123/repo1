package com.htap.fw_core;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Common library functions to work with <code>WebDriver</code>.
 * <p>
 * <b>Usage:</b><br>
 * <code>FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();</code>
 * <p>
 *
 * @author Vishal Kumar
 */

public class FW_WebDriverFunctions {

	static Logger logger = LogManager.getLogger(FW_WebDriverFunctions.class.getName());

	FW_WebDriver fw_WebDriver = new FW_WebDriver();
	WebDriver driver = fw_WebDriver.getWebDriver();
	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

	/**
	 * Loads the application <code>URL</code> using selected browser on desktop.
	 * <p>
	 * Browser is specified as part of <code>DesiredCapabilities</code> while
	 * creating the <code>WebDriver</code> object.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_Functions.launch("www.msn.com");
	 * </code>
	 * <p>
	 *
	 * @param url: Application URL to be loaded.
	 */
	public void launch(String url) {
		logger.debug("[FW_WebDriverFunctions : launch()] Launching application with url" + url);
		try {
			driver.get(url);
		} catch (Exception e) {
			logger.error("[FW_WebDriverFunctions : launch()] Exception: " + e.getMessage());
		}
	}

	/**
	 * Closes the current <code>Active</code> browser window.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_Functions.closeWindow();
	 * </code>
	 * </p>
	 */
	public void closeWindow() {
		logger.debug("[FW_WebDriverFunctions : closeWindow()] Closing the current active window");
		try {
			driver.close();
		} catch (Exception e) {
			logger.error("[FW_WebDriverFunctions : closeWindow()] Exception: " + e.getMessage());
		}
	}

	/**
	 * Closes all the opened <code>driver browser window</code> on desktop. *
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_Functions.quitAllWindows();
	 * </code>
	 * </p>
	 */
	public void quitAllWindows() {
		logger.debug("[FW_WebDriverFunctions : quitAllWindows()] Closes all the browser windows and terminates the WebDriver session.");
		try {
			driver.quit();
		} catch (Exception e) {
			logger.error("[FW_WebDriverFunctions : quitAllWindows()] Exception: " + e.getMessage());
		}
	}

	/**
	 * Checks and switches to the alert message box if it is present.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_WebDriverFunctions.isAlertPresent();
	 * </code>
	 * <p>
	 * @return <code><b>true</b></code> if alert is present else returns <code><b>false</b></code>
	 */
	public boolean isAlertPresent() {
		logger.debug("[FW_WebDriverFunctions : isAlertPresent()] To check if there is an alert message");
		try {
			driver.switchTo().alert();
			logger.debug("[FW_WebDriverFunctions : isAlertPresent()]  Switching to Alert");
			return true;
		} catch (NoAlertPresentException e) {
			logger.error("[FW_WebDriverFunctions : isAlertPresent()]  Exception: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Switches to the alert message box and accepts <code>message/info/warning</code> popup.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_Functions.acceptAlert();
	 * </code>
	 * </p>
	 */
	public void acceptAlert() {
		logger.debug("[FW_WebDriverFunctions : acceptAlert()] Accepting the alert message");
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			logger.error("[FW_WebDriverFunctions : acceptAlert()] Exception: " + e.getMessage());
		}
	}

	/**
	 * Switches to the alert message box and dismiss <code>message/info/warning</code> popup.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_Functions.dismissAlert();
	 * </code>
	 * </p>
	 */
	public void dismissAlert() {
		logger.debug("[FW_WebDriverFunctions : dismissAlert()] Dismissing the alert message");
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (Exception e) {
			logger.error("[FW_WebDriverFunctions : dismissAlert()] Exception: " + e.getMessage());
		}
	}

	/**
	 * Captures the browser screenshot and saves the image in user specified path with user specified image name.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_WebDriverFunctions.browserScreenshot("D:/myWorkspace/Project/com/test/auto/img/runtime/Step1.png");
	 * </code>
	 * <p>
	 * @param pathWithFileName Image name with full path where the image needs to be saved
	 */
	public void browserScreenshot(String pathWithFileName) {

		try {
			logger.debug("[FW_WebDriverFunctions : browserScreenshot()] Takes browser screenshot with file name "
					+ pathWithFileName);

			// Call getScreenshotAs method to create image file
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			BufferedImage fullImg = ImageIO.read(screenshot);
			logger.info("Screenshot taken");

			// To copy temp image in to permanent file
			ImageIO.write(fullImg, "png", screenshot);

			try {
				File destFile = new File(pathWithFileName);

				// copying file at destination
				FileUtils.copyFile(screenshot, destFile);
				logger.info("[FW_WebDriverFunctions : browserScreenshot()] screenshot file copied to path specified");

			} catch (IOException e) {
				logger.error("[FW_WebDriverFunctions : browserScreenshot()] Exception: " + e.getMessage());
			}
		} catch (Exception e) {
			logger.error("[FW_WebDriverFunctions : browserScreenshot()] Exception: " + e.getMessage());
		}
	}

	/**
	 * Captures the desktop screenshot and saves the image in user specified path with user specified image name.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_WebDriverFunctions.captureScreenshot("D:/myWorkspace/Project/com/test/auto/img/runtime/Step1.png");
	 * </code>
	 * <p>
	 * @param pathWithFileName Image name with full path where the image needs to be saved
	 */
	public void captureScreenshot(String pathWithFileName) {

		try {
			logger.debug("[FW_WebDriverFunctions : desktopScreenshot()] Takes desktop screenshot with file name "
					+ pathWithFileName);

			Robot robot = new Robot();
			// getting screen size
			Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

			// Capturing screenshot by providing size
			BufferedImage image = robot.createScreenCapture(screenSize);

			try {
				File destFile = new File(pathWithFileName);

				// To copy temp image in to permanent file
				ImageIO.write(image, "png", destFile);

				// copying file at destination
				// FileUtils.copyFile(path, destFile);
				logger.info("[FW_WebDriverFunctions : desktopScreenshot()] Screenshot file copied to path " + pathWithFileName);

			} catch (IOException e) {
				logger.error("[FW_WebDriverFunctions : desktopScreenshot()] Exception: " + e.getMessage());
			}
		} catch (Exception e) {
			logger.error("[FW_WebDriverFunctions : desktopScreenshot()] Exception: " + e.getMessage());
		}
	}

	/**
	 * Captures the image for the user specified <code>WebElement</code>. Saves the image in user specified path with user specified image name.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_WebDriverFunctions.webElementScreenshot(elementSignInButton, "D:/myWorkspace/Project/com/test/auto/img/runtime/elementSignInButton.png");
	 * </code>
	 * <p>
	 * @param element <code>WebElement</code> for which image is to be captured
	 * @param pathWithFileName Image name with full path where the image needs to be saved
	 */
	public void webElementScreenshot(WebElement element, String pathWithFileName) {
		logger.debug("[FW_WebDriverFunctions : webElementScreenshot()] Takes screenshot of a web element with name "
				+ pathWithFileName);
		try {
			// Get entire page screenshot
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			logger.info("[FW_WebDriverFunctions : webElementScreenshot()] Screenshot taken");
			BufferedImage fullImg = ImageIO.read(screenshot);

			// Get the location of element on the page
			Point point = element.getLocation();

			// Get width and height of the element
			int elementWidth = element.getSize().getWidth();
			int elementHeight = element.getSize().getHeight();

			// Crop the entire page screenshot to get only element screenshot
			BufferedImage elementScreenshot = fullImg.getSubimage(point.getX(), point.getY(), elementWidth,
					elementHeight);

			ImageIO.write(elementScreenshot, "png", screenshot);
			logger.info("[FW_WebDriverFunctions : webElementScreenshot()] File screenshotFile written on disk");

			try {
				File destFile = new File(pathWithFileName);

				// Copying file at the destination
				FileUtils.copyFile(screenshot, destFile);
			} catch (IOException e) {
				logger.error("[FW_WebDriverFunctions : webElementScreenshot()] Exception: " + e.getMessage());
			}
		} catch (Exception e) {
			logger.error("[FW_WebDriverFunctions : webElementScreenshot()] Exception: " + e.getMessage());
		}
	}

	/**
	 * Waits for the presence of element identified by locator like <code>Id, Name, Xapth, ClassName, TagName, CssSelector, LinkText, PartialLinkText</code>
	 * <p>
	 * Times out if the element is not displayed even after user specified timeout seconds.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_WebDriverFunctions.waitForElementToAppear(By.xpath("//input[@id='username']"), "UsernameTextField", 60);
	 * </code>
	 * <p>
	 * @param by Locator like <code>Id, Name, Xapth, ClassName, TagName, CssSelector, LinkText, PartialLinkText</code>
	 * @param elementTitle Short description about element
	 * @param timeoutInSeconds Time in seconds to wait for the element to appear on the page
	 */
	public void waitForElementToAppear(By by, String elementTitle, Integer timeoutInSeconds) {
		logger.debug("[FW_WebDriverFunctions : waitForElementAppear()] Waiting for element to appear on webpage");
		try {

			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			logger.info("[FW_WebDriverFunctions : waitForElementAppear()] Element appeared");

		} catch (Exception e) {
			logger.error("[FW_WebDriverFunctions : waitForElementAppear()] Exception: " + e.getMessage());
		}
	}

	/**
	 * Waits till the element identified by locator like <code>Id, Name, Xapth, ClassName, TagName, CssSelector, LinkText, PartialLinkText</code>
	 * disappears from the page.<p>
	 * Times out if the element is still displayed even after user specified timeout seconds.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_WebDriverFunctions.waitForElementToDisappear(By.xpath("//input[@id='alert']"), "Alert Message", 60);
	 * </code>
	 * <p>
	 * @param by Locator like <code>Id, Name, Xapth, ClassName, TagName, CssSelector, LinkText, PartialLinkText</code>
	 * @param elementTitle Short description about element
	 * @param timeoutInSeconds Time in seconds to wait for the element to disappear from the page
	 */
	public void waitForElementToDisappear(By by, String elementTitle, Integer timeoutInSeconds) {
		logger.debug("[FW_WebDriverFunctions : waitForElementDisappear()] Waiting for element to disappear from webpage");

		try {

			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			logger.info("[FW_WebDriverFunctions : waitForElementDisappear()]Element Disappeared");
		} catch (Exception e) {
			logger.error("[FW_WebDriverFunctions : waitForElementDisappear()] Exception: " + e.getMessage());
		}
	}

	/**
	 * Executes the JavaScript code embedded in the page using <code>JavascriptExecutor</code>.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();<br>
	 * fw_WebDriverFunctions.executeJavascript("showVaultSelector()");
	 * </code>
	 * <p>
	 * @param scriptName Name of the Javascript function to be executed.
	 */
	public void executeJavascript(String scriptName) {

		try {
			logger.debug("[FW_WebDriverFunctions : executeJavaScript()] Executing script " + scriptName + " on page");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(scriptName);
			logger.info("[FW_WebDriverFunctions : executeJavaScript()] Executing " + scriptName + " script");

		} catch (Exception e) {
			logger.error("[FW_WebDriverFunctions : executeJavaScript()] Exception: " + e.getMessage());
		}
	}

}
