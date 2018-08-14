package com.test.CNHi.testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.cnhi.ExtentReporter.FW_ExtentReporter;
import com.htap.fw_core.FW_BaseTestCase;
import com.htap.fw_core.FW_Const;
import com.htap.fw_core.FW_Enums.logStatus;
import com.htap.fw_core.FW_GenericDataType;
import com.htap.fw_core.FW_UtilFunctions;
import com.htap.fw_core.FW_WebDriver;
import com.htap.fw_core.FW_WebDriverFunctions;
import com.test.CNHi.pages.EVOFleetHomePage;
import com.test.CNHi.testdata.D_EVOFleetApplicationLoginPage;

public class EVOFleetHomeScreenTest extends FW_BaseTestCase {
	private static Logger logger = LogManager.getLogger(EVOFleetHomeScreenTest.class.getName());


	FW_WebDriver fw_WebDriver=new FW_WebDriver();
	WebDriver d=fw_WebDriver.getWebDriver();
	FW_WebDriverFunctions fw_WebDriverFunctions= new FW_WebDriverFunctions();
	FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();
	FW_ExtentReporter fw_ExtentReporter = new FW_ExtentReporter();

	public D_EVOFleetApplicationLoginPage evoFleetApplicationLoginPage = PageFactory.initElements(d, D_EVOFleetApplicationLoginPage.class);
	EVOFleetHomePage evoFleetHomePage=PageFactory.initElements(d, EVOFleetHomePage.class);

	String imageFolder=null;
	String relativePath=null;
	public String ExtentReportResultsFolderName=null;

	public void setup () {

		logger.info("[SETUP] Setup for " + getTestCaseId() + " - " +  getTestCaseName());

		evoFleetApplicationLoginPage.getInputData("D_EVOFleetApplicationLoginPage.csv", 0);

		imageFolder =ExtentReportResultsFolderName + FW_Const.BACK_SLASH + getTestCaseId() + FW_Const.BACK_SLASH + "img" + FW_Const.BACK_SLASH;
		relativePath = getTestCaseId() + FW_Const.BACK_SLASH + "img" + FW_Const.BACK_SLASH;
		fw_UtilFunctions.checkAndCreateDirectory(imageFolder);

		fw_WebDriverFunctions.captureScreenshot(imageFolder + "1.png");
		logger.info("[SETUP] Setup for " + getTestCaseId() + " - " +  getTestCaseName());
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[SETUP] Setup for " + getTestCaseId() + " - " +  getTestCaseName(), relativePath + "1.png");

		fw_WebDriverFunctions.captureScreenshot(imageFolder + "2.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Executing '" + getTestCaseName() + "' using '" + fw_Init.getBrowser() + " on '" + fw_Init.getPlatform() + "'.", relativePath + "2.png");

		logger.info("Launching EVO fleet application URL: "+fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_HTTP) + fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_URL)+" in selected web browser.");
		fw_WebDriverFunctions.launch(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_HTTP) + fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_URL));
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "3.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Launching application  ", relativePath + "3.png");

		logger.info("Logging into EVO Fleet Application with valid username and Password.");
		evoFleetApplicationLoginPage.wrapperSignIn(evoFleetApplicationLoginPage.getUsername(), evoFleetApplicationLoginPage.getPassword());
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "4.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Logging into EVO Fleet Application with valid username and Password.", relativePath + "4.png");

	}

	public void test () {

		logger.info("[TEST] Test steps for " + getTestCaseId() + " - " +  getTestCaseName());
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "5.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[TEST] Test steps for  " + getTestCaseId() + " - " +  getTestCaseName(), relativePath + "5.png");

		logger.info("Entering Text: " + "test" + " - " + "in search text field.");
		evoFleetHomePage.enterSearchTextField("test");
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "6.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Entering string test in search text field.", relativePath + "6.png");

		logger.info("Clicking on Search button.");
		evoFleetHomePage.clickSearchButton();
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "7.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Clicking on Search button.", relativePath + "7.png");

		logger.info("Clicking on Fleet drop down list.");
		evoFleetHomePage.clickFleetDropDownButton();
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "8.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Clicking on Fleet drop down list.", relativePath + "8.png");

		//Stopping the execution for 5 seconds.
		fw_UtilFunctions.haltExecution(5);

		logger.info("Selecting the option CNH_EVO from Fleet drop down list.");
		evoFleetHomePage.clickFleetDropDownOptionCNH_EVOButton();
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "9.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Selecting the option CNH_EVO from Fleet drop down list.", relativePath + "9.png");

	}


	public boolean verify() {

		logger.info("[VERIFY] Verify test results for " + getTestCaseId() + " - " +  getTestCaseName());
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "10.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[VERIFY] Verify test results for " + getTestCaseId() + " - " +  getTestCaseName(), relativePath + "10.png");

		String goldImagesFolder =  fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_GOLD_IMG_FOLDER) + FW_Const.BACK_SLASH;
		String runtimeImagesFolder = fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RUNTIME_IMG_FOLDER) + FW_Const.BACK_SLASH;

		goldImagesFolder = fw_Init.getWorkingDir() + goldImagesFolder + fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_BROWSER) + FW_Const.BACK_SLASH;

		runtimeImagesFolder = fw_Init.getWorkingDir() + runtimeImagesFolder + fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_BROWSER) + FW_Const.BACK_SLASH;

    	FW_GenericDataType<Boolean> expected = new FW_GenericDataType<Boolean>();
    	FW_GenericDataType<Boolean> actual = new FW_GenericDataType<Boolean>();

    	expected.setT(true);
    	actual.setT(false);

    	evoFleetHomePage.captureBellIconButtonScreenshot(runtimeImagesFolder+"actual.png");

    	//Stopping the execution for 5 seconds.
    	fw_UtilFunctions.haltExecution(5);
    	BufferedImage goldImage=null;
    	BufferedImage runTimeImage=null;

    	//Reading the images from location.
		try {
			goldImage = ImageIO.read( new File(goldImagesFolder+"expected.png"));
			runTimeImage = ImageIO.read(new File(runtimeImagesFolder+"actual.png"));
		} catch (IOException e) {
			logger.error("Unable to find gold or runtime image" + e.getMessage());
		}

		/*Image goldImage = Toolkit.getDefaultToolkit().getImage(goldImagesFolder + "expected.PNG");
    	Image runTimeImage = Toolkit.getDefaultToolkit().getImage(runtimeImagesFolder + "actual.PNG");*/

		actual.setT(fw_UtilFunctions.compareGoldImageWithRuntimeImage(goldImage, runTimeImage));

    	if (VerifySafely("Bell ICON image MATCHED", expected, actual)) {
    		logger.info("Bell ICON image MATCHED with expected [Expected] = [Actual] ");
			fw_WebDriverFunctions.captureScreenshot(imageFolder + "9.png");
			fw_ExtentReporter.extentMessage(logStatus.PASS.toString(), "Bell ICON image MATCHED with expected");
			return true;
    	} else {

    		logger.error("Bell ICON image does not MATCHED with expected [Expected] != [Actual] ");
			fw_WebDriverFunctions.captureScreenshot(imageFolder + "9.png");
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "Bell ICON image MATCHED with expected");
			return false;
    	}

	}


	public void teardown () {
		logger.info("[TEARDOWN] Teardown for " + getTestCaseId() + " - " +  getTestCaseName());
		fw_WebDriverFunctions.captureScreenshot(imageFolder + "10.png");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[TEARDOWN] Teardown for " + getTestCaseId() + " - " +  getTestCaseName(), relativePath + "10.png");
		d.quit();
	}

}