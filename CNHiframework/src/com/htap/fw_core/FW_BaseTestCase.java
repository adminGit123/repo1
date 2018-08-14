package com.htap.fw_core;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;

/**
 * <h1>This is the base test case for all Test Case classes. All the automation test case will extend this base class. </h1>
 * The base class is used to implement Test cases. This class contain basic
 * initialization methods. This class contains getter and setter for TestCase
 * Identification.
 * <p>
 * <b>Usage:</b><br>
 * <code>public class EVOFleetAppLoginTestCase extends FW_BaseTestCase</code>
 * <p>
 *
 * @author Rohit Kothari
 */
public class FW_BaseTestCase extends FW_TestPlan {

	private String testCaseId;
	private String testCaseName;
	private String testCaseDescription;
	private long executionStartTime;
	private long executionEndTime;
	private long totalExecutionTime;
	private boolean testResult;

	private static Logger logger = Logger.getLogger(FW_BaseTestCase.class.getName());

	/**
	 * This method will return the ID of the test case.
	 * @return testCaseId
	 */
	public String getTestCaseId() {
		return testCaseId;
	}

	/**
	 * This Method used to set the TestCase Id.
	 * @param testCaseId: Parameter for testCaseId.
	 */
	private void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	/**
	 * This method will return the description or information about the test case.
	 * @return testCaseDescription
	 */
	public String getTestCaseDescription() {
		return testCaseDescription;
	}

	/**
	 * This Method used to set the TestCase Id.
	 * @param testCaseDescription: Parameter for test case description.
	 */
	private void setTestCaseDescription(String testCaseDescription) {
		this.testCaseDescription = testCaseDescription;
	}

	/**
	 * This method will return time the when the test case has started.
	 * @return executionStartTime
	 */
	private long getExecutionStartTime() {
		return executionStartTime;
	}

	/**
	 * This Method used to set the time when test case execution started.
	 * @param executionStartTime: Parameter for test case start time.
	 */
	private void setExecutionStartTime(long executionStartTime) {
		this.executionStartTime = executionStartTime;
	}

	/**
	 * This method will return the time when the test case execution stopped/ended.
	 * @return executionEndTime
	 */
	private long getExecutionEndTime() {
		return executionEndTime;
	}

	/**
	 * This Method used to set the time when test case execution stopped/ended.
	 * @param executionEndTime: Parameter for test case stop time.
	 */
	private void setExecutionEndTime(long executionEndTime) {
		this.executionEndTime = executionEndTime;
	}

	/**
	 * This method will return test case name.
	 * @return testCaseName
	 */
	public String getTestCaseName() {
		return testCaseName;
	}

	/**
	 * This Method used to set the name of the test case.
	 * @param testCaseName: Name of the test case.
	 */
	private void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	/**
	 * This method will return total time taken in executing a test case.
	 * @return totalExecutionTime
	 */
	private long getTotalExecutionTime() {
		return totalExecutionTime;
	}

	/**
	 * This Method used to set total time for executing a test case. It is calculated by subtracting the execution end time minus execution start time.
	 * @param totalExecutionTime: Total time of test case execution.
	 */
	@SuppressWarnings("unused")
	private void setTotalExecutionTime(long totalExecutionTime) {
		this.totalExecutionTime = (this.getExecutionEndTime() - this.getExecutionStartTime()) / FW_Const.THOUSAND;
	}

	/**
	 * This method true or false based on test result get PASSED or FAILED.
	 * @return testResult
	 */
	public boolean getTestResult() {
		return testResult;
	}

	/**
	 * This Method used to set the boolean value for the test result. If PASSED it will be true else false.
	 * @param testResult: PASS/FAIL - TRUE/FALSE
	 */
	public void setTestResult(boolean testResult) {
		this.testResult = testResult;
	}

	/**
	 * This method block will be implemented as setting up test case environment, say creation of driver, initializing browsers etc.
	 * @throws UnknownError 'setup()' method not defined.
	 */
	public void setup() throws UnknownError {
		logger.error("[FW_BaseTestCase : setup] setup() method not defined");
		throw new UnknownError("[ERROR] 'setup()' method not defined.");
	}

	/**
	 * This block will used within the test case execute all the steps using interaction function defined in
	 * page class till it reaches to the verification point.
	 * @throws UnknownError 'test()' method not defined.
	 */
	public void test() throws UnknownError {
		logger.error("[FW_BaseTestCase : test] test() method not defined");
		throw new UnknownError("[ERROR] 'test()' method not defined.");
	}

	/**
	 * This block will be use to do all kind of verification which is required for a test case using VerifySafely(),
	 * VerifyPartial() or any other assertion.
	 * @return true or false based on verification result.
	 * @throws UnknownError 'verify()' method not defined.
	 */
	public boolean verify() throws UnknownError {
		logger.error("[FW_BaseTestCase : verify] verify() method not defined");
		throw new UnknownError("[ERROR] 'verify()' method not defined.");
	}

	/**
	 * Use to cleanup every driver instance to kill the driver session safely.
	 * @throws UnknownError 'teardown()' method not defined.
	 */
	public void teardown() throws UnknownError {
		logger.error("[FW_BaseTestCase : teardown] teardown() method not defined");
		throw new UnknownError("[ERROR] 'teardown()' method not defined.");
	}


	/**
	 * Run method will be a wrapper method for setup(), test(), verify() and teardown();
	 */
	public void run() {
		boolean teardownExecuted = false;

		setExecutionStartTime(System.currentTimeMillis());
		logger.info("[FW_BaseTestCase : RUN] Executing '" + getTestCaseName() + "' using '" + fw_Init.getBrowser() + "' on '" + fw_Init.getPlatform() + "'.");
		try {
			// Testcase START

			if (getTestCaseId() == "" || getTestCaseId() == null) {
				logger.error("[FW_BaseTestCase : run] Testcase ID not set");
				throw new UnknownError("[ERROR] Testcase ID not set.");
			}

			if (getTestCaseDescription() == "" || getTestCaseDescription() == null) {
				logger.error("[FW_BaseTestCase : run] Testcase description not set");
				throw new UnknownError("[ERROR] Testcase description not set.");
			}

			// For Setup Initialization
			setup();

			// To Write TestCases
			test();

			// Verification of Testcases
			setTestResult(verify());

			// Testcase END
		} catch (UnknownError e) {
			markTCStateFail();
			logger.error("[FW_BaseTestCase : run] UnknownError: " + e.getMessage());

			teardown();
			teardownExecuted = true;
		}
		catch (WebDriverException e) {
			markTCStateFail();
			logger.error("[run] WebDriverException: " + e.getMessage());

			teardown();
			teardownExecuted = true;
		} 
		if (!teardownExecuted) {
			teardown();
		}

		setExecutionEndTime(System.currentTimeMillis());
		// To Calculate Total Execution Time.
		getTotalExecutionTime();
	}


	/**
	 * VerifySafely can be used to do verification for exact match.
	 * @param description: A brief Description regarding what verification will be performed.
	 * @param expected: Due to generic data type uses expected value could be String, Integer, boolean, array etc
	 * @param actual Actual value retrieved at run time during test execution.
	 * @return TRUE/FALSE - Based on verification is passed or failed.
	 */
	public boolean VerifySafely(String description, FW_GenericDataType<?> expected, FW_GenericDataType<?> actual) {
		boolean result = true;

		try {
			Assert.assertEquals(actual.getT(), expected.getT(), description);
		} catch (AssertionError e) {
			result = false;
			logger.error("[FW_BaseTestCase : VerifySafely] AssertionError: " + description + " 	[Expected]: " + expected.getT().toString() + "		[Actual]: " + actual.getT().toString());
		}
		if (result) {
			logger.info("VERIFIED - " + description + " 	[Expected]: " + expected.getT().toString() + "		[Actual]: " + actual.getT().toString());
		}
		return result;
	}

	/**
	 * VerifyPartial can be used to do verification for partial match. Say one wants to check if CNHi is there in long label.
	 * @param description: A brief Description regarding what verification will be performed.
	 * @param expected: Due to generic data type uses expected value could be String, Integer, boolean, array etc
	 * @param actual Actual value retrieved at run time during test execution.
	 * @return TRUE/FALSE - Based on verification is passed or failed.
	 */

	public boolean VerifyPartial(String description, FW_GenericDataType<?> expected, FW_GenericDataType<?> actual) {
		boolean result = true;
		try {
			Assert.assertEquals(actual.getT().toString().contains(expected.getT().toString()), true, description);
		} catch (AssertionError e) {
			result = false;
			logger.error("[FW_BaseTestCase : VerifySafely] AssertionError: " + description + " 	[Expected]: " + expected.getT().toString() + "		[Actual]: " + actual.getT().toString());
		}
		if (result) {
			logger.info("VERIFIED - " + description + " 	[Expected]: " + expected.getT().toString() + "		[Actual]: " + actual.getT().toString());
		}
		return result;
	}

	/**
	 * This method used to set TestCase Information to Identify Test Case
	 * scenario.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>TestCaseA test = new TestCaseA();<br>
	 * test.setTestcaseInfo("testCaseID","testCaseName","testCaseDescription")
	 * </code>
	 *
	 * @param testCaseID: ID of the test case.
	 * @param testCaseName: Name of TestCase
	 * @param testCaseDescription: A short description of the test case.
	 */

	public void setTestcaseInfo(String testCaseID, String testCaseName, String testCaseDescription) {
		setTestCaseId(testCaseID);
		setTestCaseName(testCaseName);
		setTestCaseDescription(testCaseDescription);
	}

	/**
	 * This method used for Print Summary of TestCase like
	 * TotalExecutionTime,TestCaseName.
	 */
	public void printTestSummary() {
		if (getTotalExecutionTime() >= FW_Const.SIXTY) {
			logger.info("Executed in: " + getTotalExecutionTime() / FW_Const.SIXTY + " minutes.");
		} else {
			logger.error("Executed in: " + getTotalExecutionTime() + " seconds.");
		}

		if (getTestResult()) {
			logger.info("Testcase '" + getTestCaseName() + "' PASSED.");
		} else {
			logger.error("Testcase '" + getTestCaseName() + "' FAILED.");
			Assert.fail("[printTestSummary] Testcase '" + getTestCaseName() + "' FAILED.");
		}
	}

	/**
	 * This method used for when TestCase Failed.
	 * <p>
	 * <b>Usage:</b><br>
	 * It will mark TCState Failed when TestCase Failed<br>
	 * </p>
	 */
	private void markTCStateFail() {
		logger.error("Testcase '" + getTestCaseName() + "' FAILED.");
		setTestResult(false);
	}

}
