package com.cnhi.ExtentReporter;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
/**
 * Base class for reporting engine.
 * <p>
 * <b>Usage:</b><br>
 * <code>FW_ExtentReporter fw_ExtentReporter = new FW_ExtentReporter();</code>
 * <p>
 * @author Rohit Kothari
 */
	public class FW_ExtentReporter {
	private static Logger logger = LogManager.getLogger(FW_ExtentReporter.class.getName());

	private static ExtentHtmlReporter htmlReporter = null;
	private static ExtentReports extent = new ExtentReports();
	private static ExtentTest test = null;

	/**
	 * Private method to create extent report using testID and description.
	 * @param testID Test case ID
	 * @param description Brief description about the test case.
	 */
	private void extentStartTest(String testID, String description) {
		logger.debug("[ExtentReporterWrapper : extentReportStartTest] Starting Extent Reports test...");
		test = extent.createTest(testID, description);
	}

	/**
	 * Private method to set the author name for HTML report which ultimatly used within extentReportInit.
	 * @param author Name of the author.
	 */
	private void extentSetAuthor(String author) {
		logger.debug("[ExtentReporterWrapper: extentReportSetAuthor] Author: " + author);
		test.assignAuthor(author);
	}

	/**
	 *  Initializes values <code>extentReportName</code>, <code>testID</code>, <code>description</code>, and <code>author</code>for report engine.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_ExtentReporter fw_ExtentReporter = new FW_ExtentReporter();<br>
	 * fw_ExtentReporter.extentReportInit(reportname, test.getTestcaseID(), test.getTestcaseName(), fw_PropertyReader.getAppProperty(FW_Const.APP_PROP_APP_USER));
	 * </code>
	 * <p>
	 * @param extentReportName: Name of the report
	 * @param testID: Test case ID
	 * @param description: Brief description about the test case
	 * @param author: Test case/suite author.
	 */
	public void extentReportInit(String extentReportName, String testID, String description, String author) {
		logger.debug("[ExtentReporterWrapper : extentReportInit] Initializing Extent Reports...");
		htmlReporter = new ExtentHtmlReporter(extentReportName);
		extent.attachReporter(htmlReporter);
//		setExtentReportName(extentReportName);

		htmlReporter.setAppendExisting(true);
		htmlReporter.config().setReportName(extentReportName);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("CNHi - ExtentReports");
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setProtocol(Protocol.HTTPS);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setTheme(Theme.STANDARD);

		extentStartTest(testID, description);
		extentSetAuthor(author);
	}

	/**
	 * Generates the test reports. This method at the end of the test execution when all the test finish their execution.
	 * This method will flush the test information gathered while running test case and suite.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_ExtentReporter fw_ExtentReporter = new FW_ExtentReporter();<br>
	 * fw_ExtentReporter.extentReportFlush();
	 * </code>
	 */
	public void extentReportFlush()
	{
		logger.debug("[ExtentReporterWrapper : extentReportFlush] Flushing Extent Reports...");
		extent.flush();
	}

	/**
	 * Method for capturing different reporter logStatus <code>INFO</code>, <code>PASS</code>, <code>FAIL</code> etc. status with details from test case.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_ExtentReporter fw_ExtentReporter = new FW_ExtentReporter();<br>
	 * fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[SETUP] Setup for " + getTestcaseID() + " - " +  getTestcaseName());
	 * </code>
	 * <p>
	 * @param logStatus PASS, FAIL, INFO, WARNING, ERROR etc
	 * @param details Basic information about the execution step
	 */
	public void extentMessage(String logStatus, String details)
	{
		logger.debug("[ExtentReporterWrapper: extentMessage] logStatus: " + logStatus + "	Details: " + details);
		test.log(Status.valueOf(logStatus), details);
	}

	/**
	 *  Method for capturing different reporter logStatus <code>INFO</code>, <code>PASS</code>, <code>FAIL</code> etc. status with details from test case with screenshot evidence.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_ExtentReporter fw_ExtentReporter = new FW_ExtentReporter();<br>
	 * fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[SETUP] Setup for " + getTestcaseID() + " - " +  getTestcaseName(), relativePath + "1.png");
	 * </code>
	 * <p>
	 * @param logStatus PASS, FAIL, INFO, WARNING, ERROR etc
	 * @param details basic information about the execution step
	 * @param imgPath Imagename along with path which required to be included in the HTML report.
	 */
	public void extentMessage(String logStatus, String details, String imgPath)
	{
		try {
			logger.debug("[ExtentReporterWrapper: extentMessage] logStatus: " + logStatus + "	Details: " + details + "	Image path: " + imgPath);
			test.log(Status.valueOf(logStatus), details, MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
		} catch (IOException e) {
			logger.error("[ExtentReporterWrapper: extentMessage] IOException: " + e.getMessage());
		}

	}
	
	/**
	 *  Method for add TestCase Information <code>INFO</code>, <code>PASS</code>, <code>FAIL</code> etc. status with details.
	 *  This Method used for create Execution Summary.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_ExtentReporter fw_ExtentReporter = new FW_ExtentReporter();<br>
	 * fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[SETUP] Setup for " + getTestcaseID() + " - " +  getTestcaseName(), relativePath + "1.png");
	 * </code>
	 * <p>
	 * @param name value etc.
	 * @param name TestCase Information about pass,failed and Executed.
	 * @param value Calculated information with respect to pass,fail and Execution.
	 */

	public void extentAddSysInfo(String name, String value)
    {
           logger.debug("[ExtentReporterWrapper : extentAddSysInfo] Adding System info...");
           extent.setSystemInfo(name, value);
    }


}
