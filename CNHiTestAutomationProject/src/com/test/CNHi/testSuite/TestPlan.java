package com.test.CNHi.testSuite;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.cnhi.ExtentReporter.FW_ExtentReporter;
import com.htap.fw_core.FW_Const;
import com.htap.fw_core.FW_TestPlan;
import com.htap.fw_core.FW_UtilFunctions;
import com.htap.fw_core.FW_Enums.logStatus;
import com.test.CNHi.testcases.CNHiFleetAPPLoginTest;
import com.test.CNHi.testcases.CNHiFarmAPPFieldTestUserA;
import com.test.CNHi.testcases.CNHiFarmAPPFieldTestUserB;
import com.test.CNHi.testcases.CNHiFarmAPPFieldTestUserC;
import com.test.CNHi.testcases.CNHiFarmAPPVehicleStatusTest;
import com.test.CNHi.testcases.CNHiFleetAPPLoginTestAndroid;
import com.test.CNHi.testcases.CNHiRestAssured_GET;
import com.test.CNHi.testcases.EVOFleetHomeScreenTest;

public class TestPlan extends FW_TestPlan {

	FW_ExtentReporter fw_ExtentReporter = new FW_ExtentReporter();
	FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();
	ExtentReports extentReports = new ExtentReports();

	public String resultsFolder = fw_UtilFunctions.createNewResultsFolder(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));
	public String reportname = resultsFolder + FW_Const.BACK_SLASH + fw_Init.getPlatform() + FW_Const.UNDERSCORE + fw_Init.getAppType() + FW_Const.UNDERSCORE + fw_Init.getBrowser() + FW_Const.UNDERSCORE + fw_Init.getBrowserVersion() + ".html";


	/**
	 * Windows web test cases -- START *************************************************************************************************************************************
	 */
	@Test(priority= 0, groups = "CNHiFleetWebAPPLoginTest", testName =  "CNHiFleetAPPLoginTest", description = "Run EVO Fleet web Application successful Login test")
	@Parameters ({"platform", "appType", "browser", "browserVersion"})
	public void CNHiFleetWebAPPLoginTest() {
		CNHiFleetAPPLoginTest test = new CNHiFleetAPPLoginTest();
		test.setTestcaseInfo("CNHiFleetWebAPPLoginTest_TC001", "CNHiFleetWebAPPLoginTest ", "Run EVO Fleet web Application successful Login test");
		test.ExtentReportResultsFolderName=resultsFolder;
		fw_ExtentReporter.extentReportInit(reportname, test.getTestCaseId(), test.getTestCaseName(), fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_USER));
		//test.evoFleetApplicationLoginPage.getInputData("D_EVOFleetApplicationLoginPage.csv", 0);
		try {
			test.run();
		} catch (Exception e) {
			test.teardown();
			
		}
		setExecuted(getExecuted()+1);
		if (test.getTestResult()) {
			setPassed(getPassed()+1);
		} else {
			setFailed(getFailed()+1);
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "[TEARDOWN] Test case " + test.getTestCaseId() + " - " +  test.getTestCaseName() + "FAILED due to Exception at Run Time. Please check the logs for detailed errors");
		}		
		

		createExecutionSummary(); 
		fw_ExtentReporter.extentReportFlush();
		
		fw_UtilFunctions.copyResultsFolder(resultsFolder, fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));
		test.printTestSummary();
	}

	@Test(priority= 1, groups = "EVOFleetHomeScreenTest", testName =  "EVOFleetHomeScreenTest", description = "Run test on HOME screen of EVO fleet application")
	@Parameters ({"platform", "appType", "browser", "browserVersion"})
	public void EVOFleetHomeScreenTest() {
		EVOFleetHomeScreenTest test = new EVOFleetHomeScreenTest();
		test.setTestcaseInfo("EVOFleetHomeScreenTest_TC002", "EVOFleetHomeScreenTest ", "Run test on HOME screen of EVO fleet application");

		test.ExtentReportResultsFolderName=resultsFolder;

		fw_ExtentReporter.extentReportInit(reportname, test.getTestCaseId(), test.getTestCaseName(), fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_USER));
		//test.evoFleetApplicationLoginPage.getInputData("D_EVOFleetApplicationLoginPage.csv", 0);
		
		try {
			test.run();
		} catch (Exception e) {
			test.teardown();			
		}
		setExecuted(getExecuted()+1);
		if (test.getTestResult()) {
			setPassed(getPassed()+1);
		} else {
			setFailed(getFailed()+1);
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "[TEARDOWN] Test case " + test.getTestCaseId() + " - " +  test.getTestCaseName() + "FAILED due to Exception at Run Time. Please check the logs for detailed errors");
		}
		
		createExecutionSummary(); 
		fw_ExtentReporter.extentReportFlush();
		fw_UtilFunctions.copyResultsFolder(resultsFolder, fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));

		test.printTestSummary();
	}

	/**
	 * Android web test cases -- START *************************************************************************************************************************************
	 */

	@Test(priority= 0, groups = "CNHiFleetAPPLoginTestAndroid", testName =  "CNHiFleetAPPLoginTest_Android", description = "Run EVO Fleet Application successful Login test on Android")
	@Parameters ({"platform", "appType", "browser", "browserVersion"})
	//@Parameters ({"ANDROID", "WEB", "CHROME", "66.0.3359.181"})
	public void CNHiFleetAPPLoginTest_Android() {
		CNHiFleetAPPLoginTestAndroid test = new CNHiFleetAPPLoginTestAndroid();
		test.setTestcaseInfo("CNHiFleetAPPLoginTestAndroid_TC001", "CNHiFleetAPPLoginTestAndroid ", "Run EVO Fleet Application successful Login test on Android");

		test.ExtentReportResultsFolderName=resultsFolder;
		fw_ExtentReporter.extentReportInit(reportname, test.getTestCaseId(), test.getTestCaseName(), fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_USER));
		test.evoFleetApplicationLoginPage.getInputData("D_EVOFleetApplicationLoginPage.csv", 0);
		test.evoFleetHomePage.getInputData("D_EVOFleetHomePage.csv", 0);
		try {
			test.run();
		} catch (Exception e) {
			test.teardown();			
		}
		setExecuted(getExecuted()+1);
		if (test.getTestResult()) {
			setPassed(getPassed()+1);
		} else {
			setFailed(getFailed()+1);
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "[TEARDOWN] Test case " + test.getTestCaseId() + " - " +  test.getTestCaseName() + "FAILED due to Exception at Run Time. Please check the logs for detailed errors");
		}
		
		/*createExecutionSummary(); 
		fw_ExtentReporter.extentReportFlush();*/
		fw_UtilFunctions.copyResultsFolder(resultsFolder, fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));
		test.printTestSummary();
	}

	@Test(priority= 1, groups = "CNHiFleetAPPLoginTest_Android2", testName =  "CNHiFleetAPPLoginTest_Android", description = "Run EVO Fleet Application successful Login test on Android")
	@Parameters ({"platform", "appType", "browser", "browserVersion"})
	//@Parameters ({"ANDROID", "WEB", "CHROME", "66.0.3359.181"})
	public void CNHiFleetAPPLoginTest_Android2() {
		CNHiFleetAPPLoginTestAndroid test = new CNHiFleetAPPLoginTestAndroid();
		test.setTestcaseInfo("CNHiFleetAPPLoginTestAndroid_TC002", "CNHiFleetAPPLoginTestAndroid2 ", "Run EVO Fleet Application successful Login test on Android");

		test.ExtentReportResultsFolderName=resultsFolder;
		fw_ExtentReporter.extentReportInit(reportname, test.getTestCaseId(), test.getTestCaseName(), fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_USER));
		test.evoFleetApplicationLoginPage.getInputData("D_EVOFleetApplicationLoginPage.csv", 0);
		test.evoFleetHomePage.getInputData("D_EVOFleetHomePage.csv", 1);
		try {
			test.run();
		} catch (Exception e) {
			test.teardown();			
		}
		setExecuted(getExecuted()+1);
		if (test.getTestResult()) {
			setPassed(getPassed()+1);
		} else {
			setFailed(getFailed()+1);
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "[TEARDOWN] Test case " + test.getTestCaseId() + " - " +  test.getTestCaseName() + "FAILED due to Exception at Run Time. Please check the logs for detailed errors");
		}
		
		/*createExecutionSummary(); 
		fw_ExtentReporter.extentReportFlush();*/
		fw_UtilFunctions.copyResultsFolder(resultsFolder, fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));


		test.printTestSummary();
	}


	/**
	 * RestAPI test cases -- START *************************************************************************************************************************************
	 */
	@Test(priority= 0, groups = "CNHiRestAssured_GETTest", testName = "CNHiRestAssured_GETTest", description = "Rest Assured api test")
	@Parameters({ "baseURI", "apiName", "contentType", "statusCode", "response", "httpRequest",
			"responseBody" })
	public void CNHiRestAssured_GETTest() {
		CNHiRestAssured_GET test = new CNHiRestAssured_GET();
		test.setTestcaseInfo("CNHiRestAssured_GETTest_TC001", "CNHiRestAssured_GETTest ", "Rest Assured api test");
		test.ExtentReportResultsFolderName = resultsFolder;
		fw_ExtentReporter.extentReportInit(reportname, test.getTestCaseId(), test.getTestCaseName(),
	    fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_USER));
		test.d_RestAssuredTestApiInput.getInputData("RestAssuredInputTestdata.csv", 0);
		test.run();

		setExecuted(getExecuted() + 1);
		if (test.getTestResult()) {
			setPassed(getPassed() + 1);
		} else {
			setFailed(getFailed() + 1);
		}
		createExecutionSummary();

		fw_ExtentReporter.extentReportFlush();
		fw_UtilFunctions.copyResultsFolder(resultsFolder,
		fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));
		test.printTestSummary();
	}

	@Test(priority=0, groups = "CNHiFarmAPPVehicleStatusTest", testName =  "CNHiFarmAPPVehicleStatusTest", description = "Run EVO Farm web Application and verify Vechicle Status")
	@Parameters ({"platform", "appType", "browser", "browserVersion"})
	public void CNHiFarmAPPVehicleStatusTest() {
		CNHiFarmAPPVehicleStatusTest test = new CNHiFarmAPPVehicleStatusTest();
		test.setTestcaseInfo("CNHiFarmAPPVehicleStatusTest", "CNHiFarmAPPVehicleStatusTest ", "EVO Fleet Application Home screen test.");
		test.ExtentReportResultsFolderName=resultsFolder;
		fw_ExtentReporter.extentReportInit(reportname, test.getTestCaseId(), test.getTestCaseName(), fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_USER));

		try {
			test.run();
		} catch (Exception e) {
			test.teardown();			
		}
		setExecuted(getExecuted()+1);
		if (test.getTestResult()) {
			setPassed(getPassed()+1);
		} else {
			setFailed(getFailed()+1);
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "[TEARDOWN] Test case " + test.getTestCaseId() + " - " +  test.getTestCaseName() + "FAILED due to Exception at Run Time. Please check the logs for detailed errors");
		}
		
		/*createExecutionSummary(); 
		fw_ExtentReporter.extentReportFlush();*/
		fw_UtilFunctions.copyResultsFolder(resultsFolder, fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));

		test.printTestSummary();
	}

	@Test(priority=2, groups = "CNHiFarmAPPFieldTestUserA", testName =  "CNHiFarmAPPFieldTestUserA", description = "Run EVO Farm web Application Login using user A Feed Data in ExcelSheet")
	@Parameters ({"platform", "appType", "browser", "browserVersion"})
	public void CNHiFarmAPPFieldTestUserA() {
		CNHiFarmAPPFieldTestUserA test = new CNHiFarmAPPFieldTestUserA();
		test.setTestcaseInfo("CNHiFarmAPPFieldTestUserA", "CNHiFarmAPPFieldTestUserA ", "User A fetches the GFF data.");
		test.ExtentReportResultsFolderName=resultsFolder;
		fw_ExtentReporter.extentReportInit(reportname, test.getTestCaseId(), test.getTestCaseName(), fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_USER));
		try {
			test.run();
		} catch (Exception e) {
			test.teardown();
			
		}
		setExecuted(getExecuted()+1);
		if (test.getTestResult()) {
			setPassed(getPassed()+1);
		} else {
			setFailed(getFailed()+1);
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "[TEARDOWN] Test case " + test.getTestCaseId() + " - " +  test.getTestCaseName() + "FAILED due to Exception at Run Time. Please check the logs for detailed errors");
		}
		
		/*createExecutionSummary(); 
		fw_ExtentReporter.extentReportFlush();*/
		fw_UtilFunctions.copyResultsFolder(resultsFolder, fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));

		test.printTestSummary();
	}


	@Test(priority= 3, groups = "CNHiFarmAPPFieldTestUserB", testName =  "CNHiFarmAPPFieldTestUserB", description = "Run EVO Farm web Application Login using user B Verify Data in ExcelSheet")
	@Parameters ({"platform", "appType", "browser", "browserVersion"})
	public void CNHiFarmAPPFieldTestUserB() {
		CNHiFarmAPPFieldTestUserB test = new CNHiFarmAPPFieldTestUserB();
		test.setTestcaseInfo("CNHiFarmAPPFieldTestUserB", "CNHiFarmAPPFieldTestUserB ", "User B compares GFF data.");
		test.ExtentReportResultsFolderName=resultsFolder;
		fw_ExtentReporter.extentReportInit(reportname, test.getTestCaseId(), test.getTestCaseName(), fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_USER));
		try {
			test.run();
		} catch (Exception e) {
			test.teardown();
			
		}
		setExecuted(getExecuted()+1);
		if (test.getTestResult()) {
			setPassed(getPassed()+1);
		} else {
			setFailed(getFailed()+1);
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "[TEARDOWN] Test case " + test.getTestCaseId() + " - " +  test.getTestCaseName() + "FAILED due to Exception at Run Time. Please check the logs for detailed errors");
		}
		createExecutionSummary(); 
		fw_ExtentReporter.extentReportFlush();

		fw_UtilFunctions.copyResultsFolder(resultsFolder, fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));
		test.printTestSummary();
	}


	@Test(priority= 3, groups = "CNHiFarmAPPFieldTestUserC", testName =  "CNHiFarmAPPFieldTestUserC", description = "Run EVO Farm web Application Login using user C Verify Data in ExcelSheet")
	@Parameters ({"platform", "appType", "browser", "browserVersion"})
	public void CNHiFarmAPPFieldTestUserC() {
		CNHiFarmAPPFieldTestUserC test = new CNHiFarmAPPFieldTestUserC();
		test.setTestcaseInfo("CNHiFarmAPPFieldTestUserC", "CNHiFarmAPPFieldTestUserB ", "User C compares GFF Data.");
		test.ExtentReportResultsFolderName=resultsFolder;
		fw_ExtentReporter.extentReportInit(reportname, test.getTestCaseId(), test.getTestCaseName(), fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_APP_USER));
		try {
			test.run();
		} catch (Exception e) {
			test.teardown();
			
		}
		setExecuted(getExecuted()+1);
		if (test.getTestResult()) {
			setPassed(getPassed()+1);
		} else {
			setFailed(getFailed()+1);
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "[TEARDOWN] Test case " + test.getTestCaseId() + " - " +  test.getTestCaseName() + "FAILED due to Exception at Run Time. Please check the logs for detailed errors");
		}
		
		/*createExecutionSummary(); 
		fw_ExtentReporter.extentReportFlush();*/

		fw_UtilFunctions.copyResultsFolder(resultsFolder, fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_RESULTS_FOLDER));
		test.printTestSummary();

	}




		
	private void createExecutionSummary() {

		double passed = getPassed();
		double failed = getFailed();
		int executed  = getExecuted();

		fw_ExtentReporter.extentAddSysInfo("Execution summary:","");
		fw_ExtentReporter.extentAddSysInfo("Tests Executed", Integer.toString(getExecuted()));
		fw_ExtentReporter.extentAddSysInfo("Tests Passed", passed + " ( " + (Math.round(((passed / executed))*100)) + " % )");
  		fw_ExtentReporter.extentAddSysInfo("Tests Failed", failed + " ( " + (Math.round(((failed / executed))*100)) + " % )");

	}
}
