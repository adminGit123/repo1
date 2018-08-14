/*
 * Copyright (c) Capgemini Technology Services India Limited. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Capgemini
 * Technology Services India Limited. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Capgemini.
 *
 * CAPGEMINI MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. IGATE SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.htap.fw_core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * Common library functions to work with <code>AndroidDriver</code>.
 * <p>
 * <b>Usage:</b><br>
 * <code>FW_AndroidFunctions fw_AndroidFunctions = new FW_AndroidFunctions();</code>
 * <p>
 * @author      Aman Kumar
 */
public class FW_AndroidFunctions {
	private static Logger logger = LogManager.getLogger(FW_AndroidFunctions.class.getName());

	FW_AndroidDriver fw_AndroidDriver = new FW_AndroidDriver();
	AndroidDriver<MobileElement> d = fw_AndroidDriver.getAndroidDriver();

	/**
	 * Loads the application <code>URL</code> using selected browser on android device.
	 * <p>
	 * Browser is specified as part of <code>DesiredCapabilities</code> while creating the <code>AndroidDriver</code> object.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_AndroidFunctions fw_AndroidFunctions = new FW_AndroidFunctions();<br>
	 * fw_AndroidFunctions.launch("www.google.com");
	 * </code>
	 * <p>
	 * @param url Application URL to be loaded.
	 */
	public void launch(String url){
    	logger.debug("[FW_AndroidFunctions : launch] Launching application with URL '" + url + "'.");
		try {
			d.get(url);
        } catch (Exception e) {
        	logger.error("Exception: " + e.getMessage());
        }
	}

	/**
	 * Checks and switches to the alert message box if it is present.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_AndroidFunctions fw_AndroidFunctions = new FW_AndroidFunctions();<br>
	 * fw_AndroidFunctions.isAlertPresent();
	 * </code>
	 * <p>
	 * @return <code><b>true</b></code> if alert is present else returns <code><b>false</b></code>
	 */
	public boolean isAlertPresent() {
	    try
	    {
	        d.switchTo().alert();
			logger.debug("[FW_AndroidFunctions : isAlertPresent] Switching to Alert");
	        return true;
	    }
	    catch (NoAlertPresentException Ex)
	    {
			logger.error("[FW_AndroidFunctions : isAlertPresent] NoAlertPresentException: " + Ex.getMessage());
	        return false;
	    }
	}

	/**
	 * Waits for the presence of element identified by locator like <code>Id, Name, Xapth, ClassName, TagName, CssSelector, LinkText, PartialLinkText</code>
	 * <p>
	 * Times out if the element is not displayed even after user specified timeout seconds.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_AndroidFunctions fw_AndroidFunctions = new FW_AndroidFunctions();<br>
	 * fw_AndroidFunctions.waitForElementToAppear(By.xpath("//input[@id='username']"), "UsernameTextField", 60);
	 * </code>
	 * <p>
	 * @param by Locator like <code>Id, Name, Xapth, ClassName, TagName, CssSelector, LinkText, PartialLinkText</code>
	 * @param elementTitle Short description about element
	 * @param timeoutInSeconds Time in seconds to wait for the element to appear on the page
	 */
	public void waitForElementToAppear(By by, String elementTitle, Integer timeoutInSeconds){
		logger.debug("[FW_AndroidFunctions : waitForElementToAppear()] Waiting for element to appear on webpage");

		WebDriverWait wait = new WebDriverWait(d, timeoutInSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	/**
	 * Waits till the element identified by locator like <code>Id, Name, Xapth, ClassName, TagName, CssSelector, LinkText, PartialLinkText</code>
	 * disappears from the page.<p>
	 * Times out if the element is still displayed even after user specified timeout seconds.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_AndroidFunctions fw_AndroidFunctions = new FW_AndroidFunctions();<br>
	 * fw_AndroidFunctions.waitForElementToDisappear(By.xpath("//input[@id='alert']"), "Alert Message", 60);
	 * </code>
	 * <p>
	 * @param by Locator like <code>Id, Name, Xapth, ClassName, TagName, CssSelector, LinkText, PartialLinkText</code>
	 * @param elementTitle Short description about element
	 * @param timeoutInSeconds Time in seconds to wait for the element to disappear from the page
	 */
	public void waitForElementToDisappear(By by, String elementTitle, Integer timeoutInSeconds) {

		logger.debug("[FW_AndroidFunctions : waitForElementDisappear()] Waiting for element to disappear from webpage");

		try {

			WebDriverWait wait = new WebDriverWait(d, timeoutInSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			logger.info("[FW_AndroidFunctions : waitForElementToDisappear] Element Disappeared");
		} catch (Exception e) {
			logger.error("[FW_AndroidFunctions : waitForElementToDisappear] Exception: " + e.getMessage());
		}
	}



	/**
	 * Captures the image for the user specified <code>WebElement</code>. Saves the image in user specified path with user specified image name.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_AndroidFunctions fw_AndroidFunctions = new FW_AndroidFunctions();<br>
	 * fw_AndroidFunctions.captureWebElementImage(elementSignInButton, "D:/myWorkspace/Project/com/test/auto/img/runtime/elementSignInButton.png");
	 * </code>
	 * <p>
	 * @param ele <code>WebElement</code> for which image is to be captured
	 * @param imageNameWithPath Image name with full path where the image needs to be saved
	 */
	public void captureWebElementImage(WebElement ele, String imageNameWithPath) {
		//Get entire page screenshot
		File screenshot = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = null;
		try {
			fullImg = ImageIO.read(screenshot);
		} catch (IOException e) {
			logger.error("[FW_AndroidFunctions : captureWebElementImage] IOException: " + e.getMessage());
		}

		//Get the location of element on the page
		Coordinates location =((Locatable)ele).getCoordinates();
		Point point = location.inViewPort();

		//Get width and height of the element
		int eleWidth = ele.getSize().getWidth() + 2;
		int eleHeight = ele.getSize().getHeight() + 2;

		//Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
		try {
			ImageIO.write(eleScreenshot, "png", screenshot);
		} catch (IOException e) {
			logger.error("FW_AndroidFunctions : [captureWebElementImage] IOException: " + e.getMessage());
		}
		//Copy the element screenshot to disk
		try {
			FileUtils.copyFile(screenshot, new File(imageNameWithPath + ".png"));
			logger.debug("[FW_AndroidFunctions : captureWebElementImage] Saving WebElement image: " + imageNameWithPath + ".png");
		} catch (IOException e) {
			logger.error("[FW_AndroidFunctions : captureWebElementImage] IOException: " + e.getMessage());
		}
	}

	/**
	 * Captures the screenshot and saves the image in user specified path with user specified image name.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_AndroidFunctions fw_AndroidFunctions = new FW_AndroidFunctions();<br>
	 * fw_AndroidFunctions.captureScreenshot("D:/myWorkspace/Project/com/test/auto/img/runtime/Step1.png");
	 * </code>
	 * <p>
	 * @param imageNameWithPath Image name with full path where the image needs to be saved
	 */
	public void captureScreenshot(String imageNameWithPath) {
		//Get entire page screenshot
		File screenshot = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = null;
		try {
			fullImg = ImageIO.read(screenshot);
		} catch (IOException e) {
			logger.error("[FW_AndroidFunctions : captureScreenshot] IOException: " + e.getMessage());
		}

		try {
			ImageIO.write(fullImg, "png", screenshot);
		} catch (IOException e) {
			logger.error("[FW_AndroidFunctions : captureScreenshot] IOException: " + e.getMessage());
		}
		//Copy the element screenshot to disk
		try {
			FileUtils.copyFile(screenshot, new File(imageNameWithPath));
			logger.debug("[FW_AndroidFunctions : captureScreenshot] Saving WebElement image: " + imageNameWithPath);
		} catch (IOException e) {
			logger.error("[FW_AndroidFunctions : captureScreenshot] IOException: " + e.getMessage());
		}
	}
}