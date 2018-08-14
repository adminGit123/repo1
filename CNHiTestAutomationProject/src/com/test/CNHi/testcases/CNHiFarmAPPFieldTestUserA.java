package com.test.CNHi.testcases;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.cnhi.ExtentReporter.FW_ExtentReporter;
import com.htap.fw_core.FW_BaseTestCase;
import com.htap.fw_core.FW_Const;
import com.htap.fw_core.FW_GenericDataType;
import com.htap.fw_core.FW_WebDriver;
import com.htap.fw_core.FW_WebDriverFunctions;
import com.htap.fw_core.FW_Enums.logStatus;
import com.test.CNHi.pages.EVOFarmFieldsPage;
import com.test.CNHi.testdata.D_EVOFleetApplicationLoginPage;
import com.test.CNHi.testdata.D_EVOFleetHomePage;

public class CNHiFarmAPPFieldTestUserA extends FW_BaseTestCase {

	private static Logger logger = LogManager.getLogger(CNHiFarmAPPFieldTestUserA.class.getName());


	FW_WebDriver fw_WebDriver=new FW_WebDriver();
	WebDriver d=fw_WebDriver.getWebDriver();
	FW_WebDriverFunctions fw_WebDriverFunctions= new FW_WebDriverFunctions();
	FW_ExtentReporter fw_ExtentReporter = new FW_ExtentReporter();
	

	public D_EVOFleetApplicationLoginPage evoFarmApplicationLoginPage = PageFactory.initElements(d, D_EVOFleetApplicationLoginPage.class);
	public D_EVOFleetHomePage evoFleetHomePage=PageFactory.initElements(d, D_EVOFleetHomePage.class);

	EVOFarmFieldsPage evoFarmFieldPage=PageFactory.initElements(d, EVOFarmFieldsPage.class);
	

	String imageFolder=null;
	String relativePath=null;
	public String ExtentReportResultsFolderName=null;
	public void setup () {

	
			evoFarmApplicationLoginPage.getInputData("D_EVOFleetApplicationLoginPage.csv", 0);
			evoFleetHomePage.getInputData("D_EVOFleetHomePage.csv", 1);
			
			imageFolder =ExtentReportResultsFolderName + FW_Const.BACK_SLASH + getTestCaseId() + FW_Const.BACK_SLASH + "img" + FW_Const.BACK_SLASH;
			relativePath = getTestCaseId() + FW_Const.BACK_SLASH + "img" + FW_Const.BACK_SLASH;
			fw_UtilFunctions.checkAndCreateDirectory(imageFolder);

			fw_WebDriverFunctions.captureScreenshot(imageFolder + "1.png");
			logger.info("[SETUP] Setup for " + getTestCaseId() + " - " +  getTestCaseName());
			fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[SETUP] Setup for " + getTestCaseId() + " - " +  getTestCaseName(), relativePath + "1.png");

			fw_WebDriverFunctions.captureScreenshot(imageFolder + "2.png");
			fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Executing '" + getTestCaseName() + "' using '" + fw_Init.getBrowser() + " on '" + fw_Init.getPlatform() + "'.", relativePath + "2.png");

		
			logger.info("Launching EVO fleet application URL: "+fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_HTTP) + fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_FARMAPP_URL)+" in selected web browser.");
			fw_WebDriverFunctions.launch(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_HTTP) + fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_FARMAPP_URL));
			
			fw_WebDriverFunctions.captureScreenshot(imageFolder + "3.png");
			fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Launching application  ", relativePath + "3.png");

	}


	public void test () {
		logger.info("[TEST] Test steps for " + getTestCaseId() + " - " +  getTestCaseName());
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "4.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[TEST] Test steps for  " + getTestCaseId() + " - " +  getTestCaseName(), relativePath + "4.png");

		
		
		logger.info("Entering UserName: " + evoFarmApplicationLoginPage.getUsername() + " - " + "in username text field.");
		evoFarmApplicationLoginPage.enterUserNameTextField(evoFarmApplicationLoginPage.getUsername());
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "5.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Entering UserName: " + evoFarmApplicationLoginPage.getUsername() + " - " + "in username text field.", relativePath + "5.png");

		
		logger.info("Entering Password: " + " - " + "in password text field.");
		evoFarmApplicationLoginPage.enterPasswordTextField(evoFarmApplicationLoginPage.getPassword());
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "6.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Entering Password: " + " - " + "in password text field.", relativePath + "6.png");

		
		logger.info("Clicking on SingIn button.");
		evoFarmApplicationLoginPage.clickSignInButton();
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "7.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Clicking on SingIn button.", relativePath + "7.png");

		evoFarmFieldPage.getGFFValue();
		
	
	}
	
	
	
	public boolean verify () {
		logger.info("[VERIFY] Verify test results for " + getTestCaseId() + " - " +  getTestCaseName());

		FW_GenericDataType<String> expectedStatus = new FW_GenericDataType<String>();
		FW_GenericDataType<String> actualStatus = new FW_GenericDataType<String>();

		
		expectedStatus.setT(evoFarmFieldPage.geDataFromExcel());
		actualStatus.setT("Data Inserted In FieldValue ExcelSheet");

		if (VerifySafely("Fields Status expected.", expectedStatus, actualStatus)) {
			logger.info("Excel Sheet Data Status is as expected [Expected] " + expectedStatus.getT() + "   [Actual] " +  actualStatus.getT());
			fw_WebDriverFunctions.captureScreenshot(imageFolder + "9.png");
			fw_ExtentReporter.extentMessage(logStatus.PASS.toString(), "Excel Sheet Data Status is as expected [Expected] " + expectedStatus.getT() + "   [Actual] " +  actualStatus.getT(), relativePath + "9.png");
			return true;

		} else {
			logger.info("Excel Sheet Data Status is not as expected [Expected] " + expectedStatus.getT() + "   [Actual] " +  actualStatus.getT());
			fw_WebDriverFunctions.captureScreenshot(imageFolder + "10.png");
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "Excel Sheet Data Status is not as expected [Expected] " + expectedStatus.getT() + "   [Actual] " +  actualStatus.getT(), relativePath + "10.png");
			return false;
		}
		
	}
	
	
	public void teardown () {
		logger.info("[TEARDOWN] Teardown for " + getTestCaseId() + " - " +  getTestCaseName());
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "11.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[TEARDOWN] Teardown for " + getTestCaseId() + " - " +  getTestCaseName(), relativePath + "11.png");
		d.quit();
	}
	


}
