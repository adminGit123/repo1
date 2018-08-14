package com.cnhi.jiraupdate;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.htap.fw_core.FW_SessionCookies;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

/**
 * This class is updating the issue comments on Jira for the passed issue key
 * using rest assured api.
 * <p>
 * <b>Usage:</b><br>
 * <code>JiraIntegration jiraIntegration = new JiraIntegration();</code>
 * <p>
 *
 * @author Vishal Kumar
 */
public class JiraIntegration {
	private static Logger logger = LogManager.getLogger(JiraIntegration.class.getName());
	private RequestSpecification httpRequest;
	Map<String, String> map = new HashMap<String, String>();
	FW_SessionCookies sessionCookies = new FW_SessionCookies();

	/**
	 * This method is adding the comments to the issue on Jira using rest assured api.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>JiraIntegration jiraIntegration = new JiraIntegration();</br>
	 * JiraIntegration.addComment(issueKey, username, password, comment)</code>
	 * <p>
	 * @param driver
	 *            current WebDriver instance
	 * @param issueKey
	 *            key of the particular issue
	 * @param username
	 *            username for Jira authentication
	 * @param password
	 *            password for Jira authentication
	 * @param comment
	 *            Comment that needs to be added
	 */
	@SuppressWarnings("unchecked")
	public void addComment(String issueKey, String username, String password, String comment) {
		logger.debug("[JiraIntegration:addComment()] Adding comments to a Jira issue");

		try {
			map = sessionCookies.getCookies();
			RestAssured.baseURI = "https://jiraict.cnhind.com";

			httpRequest = RestAssured.given();

			httpRequest.header("content-Type", "application/json");

			httpRequest.auth().basic(username, password);
			httpRequest.cookie("ROUTEID", map.get("ROUTEID"));
			httpRequest.cookie("JSESSIONID", map.get("JSESSIONID"));
			httpRequest.cookie("atlassian.xsrf.token", map.get("atlassian.xsrf.token"));

			JSONObject requestParams = new JSONObject();

			requestParams.put("body", comment);

			httpRequest.body(requestParams.toJSONString());

			httpRequest.post("/rest/api/2/issue/" + issueKey + "/comment?expand");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
