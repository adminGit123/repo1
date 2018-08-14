package com.test.CNHi.testcases;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.cnhi.ExtentReporter.FW_ExtentReporter;
import com.htap.fw_core.FW_AndroidDriver;
import com.htap.fw_core.FW_AndroidFunctions;
import com.htap.fw_core.FW_BaseTestCase;
import com.htap.fw_core.FW_Const;
import com.htap.fw_core.FW_Enums.logStatus;
import com.htap.fw_core.FW_GenericDataType;
import com.htap.fw_core.FW_UtilFunctions;
import com.test.CNHi.testdata.D_EVOFleetApplicationLoginPageAndroid;
import com.test.CNHi.testdata.D_EVOFleetHomePageAndroid;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;



public class CNHiFleetAPPLoginTestAndroid extends FW_BaseTestCase {
	private static Logger logger = LogManager.getLogger(CNHiFleetAPPLoginTestAndroid.class.getName());

	//Android Driver
	FW_AndroidDriver fw_AndroidDriver = new FW_AndroidDriver();
	AndroidDriver<MobileElement> d=fw_AndroidDriver.getAndroidDriver();
	FW_AndroidFunctions fw_AndroidFunctions=new FW_AndroidFunctions();

	FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();
	FW_ExtentReporter fw_ExtentReporter = new FW_ExtentReporter();

	public D_EVOFleetApplicationLoginPageAndroid evoFleetApplicationLoginPage = PageFactory.initElements(d, D_EVOFleetApplicationLoginPageAndroid.class);
	public D_EVOFleetHomePageAndroid evoFleetHomePage=PageFactory.initElements(d, D_EVOFleetHomePageAndroid.class);


	String imageFolder=null;
	String relativePath=null;
	public String ExtentReportResultsFolderName=null;
	public void setup () {
		//Test data can be loaded from test case class as well
		//evoFleetApplicationLoginPage.getInputData("D_EVOFleetApplicationLoginPage.csv", 0);
		//evoFleetHomePage.getInputData("D_EVOFleetHomePage.csv", 0);

		imageFolder = ExtentReportResultsFolderName + FW_Const.BACK_SLASH + getTestCaseId() + FW_Const.BACK_SLASH + "img" + FW_Const.BACK_SLASH;
		relativePath = getTestCaseId() + FW_Const.BACK_SLASH + "img" + FW_Const.BACK_SLASH;
		fw_UtilFunctions.checkAndCreateDirectory(imageFolder);

		fw_AndroidFunctions.captureScreenshot(imageFolder + "1.png");
		logger.info("[SETUP] Setup for " + getTestCaseId() + " - " +  getTestCaseName());
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[SETUP] Setup for " + getTestCaseId() + " - " +  getTestCaseName(), relativePath + "1.png");

		fw_AndroidFunctions.captureScreenshot(imageFolder + "2.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Executing '" + getTestCaseName() + "' using '" + fw_Init.getBrowser() + " on '" + fw_Init.getPlatform() + "'.", relativePath + "2.png");

		logger.info("Launching EVO fleet application URL: "+fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_HTTP) + fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_URL)+" in selected web browser.");
		fw_AndroidFunctions.launch(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_HTTP) + fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_URL));
		fw_AndroidFunctions.captureScreenshot(imageFolder + "3.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Launching application  ", relativePath + "3.png");

	}


	public void test () {
		logger.info("[TEST] Test steps for " + getTestCaseId() + " - " +  getTestCaseName());
		fw_AndroidFunctions.captureScreenshot(imageFolder + "4.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[TEST] Test steps for  " + getTestCaseId() + " - " +  getTestCaseName(), relativePath + "4.png");

		logger.info("Entering UserName: " + evoFleetApplicationLoginPage.getUsername() + " - " + "in username text field.");
		evoFleetApplicationLoginPage.enterUserNameTextField(evoFleetApplicationLoginPage.getUsername());
		fw_AndroidFunctions.captureScreenshot(imageFolder + "5.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Entering UserName: " + evoFleetApplicationLoginPage.getUsername() + " - " + "in username text field.", relativePath + "5.png");

		logger.info("Entering Password: " +  " - " + "in password text field.");
		evoFleetApplicationLoginPage.enterPasswordTextField(evoFleetApplicationLoginPage.getPassword());
		fw_AndroidFunctions.captureScreenshot(imageFolder + "6.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Entering Password: " + " - " + "in password text field.", relativePath + "6.png");


		logger.info("Clicking on SingIn button.");
		evoFleetApplicationLoginPage.clickSignInButton();
		fw_AndroidFunctions.captureScreenshot(imageFolder + "7.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Clicking on SingIn button.", relativePath + "7.png");

	}


	public boolean verify() {
		logger.info("[VERIFY] Verify test results for " + getTestCaseId() + " - " +  getTestCaseName());
		fw_AndroidFunctions.captureScreenshot(imageFolder + "8.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[VERIFY] Verify test results for " + getTestCaseId() + " - " +  getTestCaseName(), relativePath + "8.png");

		FW_GenericDataType<String> expectedPageHeaderFirstString = new FW_GenericDataType<String>();
		FW_GenericDataType<String> actualPageHeaderFirstString = new FW_GenericDataType<String>();
		fw_UtilFunctions.haltExecution(15);
		actualPageHeaderFirstString.setT(evoFleetHomePage.getHomePageHeaderFirstStringLabel());
		//Fetching Test data for Home Screen first label from csv file
		expectedPageHeaderFirstString.setT(evoFleetHomePage.geAppHomePageHeaderFirstString());

		if (VerifySafely("Page header first string detail is as expected.", expectedPageHeaderFirstString, actualPageHeaderFirstString)) {
			logger.info("Page header first string detail is as expected [Expected] " + expectedPageHeaderFirstString.getT() + "   [Actual] " +  actualPageHeaderFirstString.getT());
			fw_AndroidFunctions.captureScreenshot(imageFolder + "9.png");
			fw_ExtentReporter.extentMessage(logStatus.PASS.toString(), "Page header first string detail is as expected [Expected] " + expectedPageHeaderFirstString.getT() + "   [Actual] " +  actualPageHeaderFirstString.getT(), relativePath + "9.png");

			FW_GenericDataType<String> expectedPageHeaderSecondString = new FW_GenericDataType<String>();
			FW_GenericDataType<String> actualPageHeaderSecondString = new FW_GenericDataType<String>();

			actualPageHeaderSecondString.setT(evoFleetHomePage.getHomePageHeaderSecondStringLabel());

			//Fetching Test data for Home Screen second label from csv file
			expectedPageHeaderSecondString.setT(evoFleetHomePage.getAppHomePageHeaderSecondString());

			if (VerifySafely("Page header second string detail is as expected.", expectedPageHeaderSecondString, actualPageHeaderSecondString)) {
				logger.info("Page header second string detail is as expected [Expected] " + expectedPageHeaderSecondString.getT() + "   [Actual] " +  actualPageHeaderSecondString.getT());
				fw_AndroidFunctions.captureScreenshot(imageFolder + "10.png");
				fw_ExtentReporter.extentMessage(logStatus.PASS.toString(), "Page header second string detail is as expected [Expected] " + expectedPageHeaderSecondString.getT() + "   [Actual] " +  actualPageHeaderSecondString.getT(), relativePath + "10.png");

				return true;
			} else {
				logger.error("Page header second string detail is not as expected [Expected] " + expectedPageHeaderSecondString.getT() + "   [Actual] " +  actualPageHeaderSecondString.getT());
				fw_AndroidFunctions.captureScreenshot(imageFolder + "10.png");
				fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "Page header second string detail is not as expected [Expected] " + expectedPageHeaderSecondString.getT() + "   [Actual] " +  actualPageHeaderSecondString.getT(), relativePath + "10.png");
				return false;
			}
		} else {
			logger.error("Page header first string detail is not as expected [Expected] " + expectedPageHeaderFirstString.getT() + "   [Actual] " +  actualPageHeaderFirstString.getT());
			fw_AndroidFunctions.captureScreenshot(imageFolder + "9.png");
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "Page header first string detail is not as expected [Expected] " + expectedPageHeaderFirstString.getT() + "   [Actual] " +  actualPageHeaderFirstString.getT(), relativePath + "9.png");
			return false;
		}

	}


	public void teardown () {
		logger.info("[TEARDOWN] Teardown for " + getTestCaseId() + " - " +  getTestCaseName());
		fw_AndroidFunctions.captureScreenshot(imageFolder + "11.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[TEARDOWN] Teardown for " + getTestCaseId() + " - " +  getTestCaseName(), relativePath + "11.png");
		d.quit();
	}

}
