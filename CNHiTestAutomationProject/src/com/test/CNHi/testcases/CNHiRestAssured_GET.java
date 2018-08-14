package com.test.CNHi.testcases;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cnhi.ExtentReporter.FW_ExtentReporter;
import com.cnhi.jiraupdate.JiraIntegration;
import com.htap.fw_core.FW_BaseTestCase;
import com.htap.fw_core.FW_Const;
import com.htap.fw_core.FW_Enums.logStatus;
import com.htap.fw_core.FW_GenericDataType;
import com.htap.fw_core.FW_RestAssuredAPI;
import com.htap.fw_core.FW_UtilFunctions;
import com.test.CNHi.testdata.D_RestAssuredTestApiInput;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CNHiRestAssured_GET extends FW_BaseTestCase {
	private static Logger logger = LogManager.getLogger(CNHiRestAssured_GET.class.getName());

	FW_ExtentReporter fw_ExtentReporter = new FW_ExtentReporter();
	FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();


	public D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();
	FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();
	JiraIntegration jiraIntegration = new JiraIntegration();

	String imageFolder = null;
	String relativePath = null;
	public String ExtentReportResultsFolderName = null;
	String username = fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_JIRA_USER);
	String password = fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_JIRA_PASS);

	String codeFromSheet;
	String codeValue;
	String response;
	Map<String, String> map;

	public void setup() {
		logger.info("[SETUP] Setup for " + getTestCaseId() + " - " + getTestCaseName());
		System.out.println("Inside setup()............");

		imageFolder = ExtentReportResultsFolderName + FW_Const.BACK_SLASH + getTestCaseId() + FW_Const.BACK_SLASH
				+ "img" + FW_Const.BACK_SLASH;
		relativePath = getTestCaseId() + FW_Const.BACK_SLASH + "img" + FW_Const.BACK_SLASH;
		fw_UtilFunctions.checkAndCreateDirectory(imageFolder);

		logger.info("[SETUP] Setup for " + getTestCaseId() + " - " + getTestCaseName());
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(),
				"[SETUP] Setup for " + getTestCaseId() + " - " + getTestCaseName());

		fw_RestAssuredAPI.setBaseURI(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_BASE_URI));
		fw_RestAssuredAPI.setAuthURI(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_AUTH_URI));
		fw_RestAssuredAPI.setToken_api(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_TOKEN_API));
		fw_RestAssuredAPI.setApiName(d_RestAssuredTestApiInput.getApi());
		fw_RestAssuredAPI.setContentType(ContentType.valueOf(d_RestAssuredTestApiInput.getContentType()));
		fw_RestAssuredAPI.setClient_id(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_CLIENT_ID));
		fw_RestAssuredAPI.setClient_secret(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_CLIENT_SECRET));
		fw_RestAssuredAPI.setGrant_type(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_GRANT_TYPE));
		fw_RestAssuredAPI.setRedirect_uri(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_REDIRECT_URI));
		fw_RestAssuredAPI.setSubscription_key(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_SUBSCRIPTION_KEY));

		String authUri = fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_AUTH_URI) + "/authorize?" + "client_id=" + fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_CLIENT_ID) + "&redirect_uri=" + fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_REDIRECT_URI) + "&response_type=" + fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_AUTH_RESPONSE_TYPE) + "&scope=" + fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_AUTH_SCOPE) + "&audience=" + fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_AUTH_AUDIENCE);

		fw_RestAssuredAPI.requestAccessToken(authUri);

		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Hitting base url  ");

	}

	public void test() {
		logger.info("[TEST] Test steps for " + getTestCaseId() + " - " + getTestCaseName());
		System.out.println("Inside test() ............");

		logger.info("[TEST] Test steps for " + getTestCaseId() + " - " + getTestCaseName());
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(),
				"[TEST] Test steps for  " + getTestCaseId() + " - " + getTestCaseName());

		fw_RestAssuredAPI.getResponseBody();
		map = d_RestAssuredTestApiInput.formatResponse();

		codeFromSheet = map.get("Code");

		response = fw_RestAssuredAPI.getResponseBody();

		logger.info("Final response: " + response);

		codeValue = fw_RestAssuredAPI.getParamValue(response, "statusCode");

		logger.info("Getting status code : " + fw_RestAssuredAPI.getResponseStatus() + "- from api");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "Getting status code for response: " + fw_RestAssuredAPI.getResponseStatus() + "- from api");

	}

	public boolean verify() {
		logger.info("[VERIFY] Verify test results for " + getTestCaseId() + " - " + getTestCaseName());
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(), "[VERIFY] Verify test results for " + getTestCaseId() + " - " +  getTestCaseName());
		System.out.println("Inside verify() ............");

		FW_GenericDataType<String> expectedCode = new FW_GenericDataType<String>();
		FW_GenericDataType<String> actualCode = new FW_GenericDataType<String>();

		expectedCode.setT(codeFromSheet);
		actualCode.setT(codeValue);

		if (VerifySafely("Status code is expected ", expectedCode, actualCode)) {
			logger.info("Status code as expected [Expected] " + expectedCode.getT() + "   [Actual] "
					+ actualCode.getT());
			fw_ExtentReporter.extentMessage(logStatus.PASS.toString(), "Status code as expected " + expectedCode.getT() + " [Actual] " + actualCode.getT());
			jiraIntegration.addComment("QAF-1", username, password, "Test case passed");
			return true;


		} else {
			logger.error("Status code is not as expected [Expected] " + expectedCode.getT() + "   [Actual] "
					+ actualCode.getT());
			fw_ExtentReporter.extentMessage(logStatus.FAIL.toString(), "Status code is not as expected [Expected] "
					+ expectedCode.getT() + "   [Actual] " + actualCode.getT());
			jiraIntegration.addComment("QAF-1", username, password, "Test Case is failed : Comment added by Mahesh" );
			return false;
		}

	}

	public void teardown() {
		logger.info("[TEARDOWN] Teardown for " + getTestCaseId() + " - " + getTestCaseName());
		System.out.println("Inside teardown() ............");
		fw_ExtentReporter.extentMessage(logStatus.INFO.toString(),
				"[TEARDOWN] Teardown for " + getTestCaseId() + " - " + getTestCaseName());
		RestAssured.reset();
	}

}
