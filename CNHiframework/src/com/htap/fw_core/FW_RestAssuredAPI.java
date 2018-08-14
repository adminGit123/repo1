package com.htap.fw_core;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Common library functions to work with <code>RestAssured</code>.
 * <p>
 * <b>Usage:</b><br>
 * <code>FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</code>
 * <p>
 *
 * @author Vishal Kumar
 */
public class FW_RestAssuredAPI {

	private static Logger logger = LogManager.getLogger(FW_RestAssuredAPI.class.getName());

	private String baseURI;
	private String authURI;
	private String apiName;
	private ContentType contentType;
	private RequestSpecification httpRequest;
	private Response response;
	private int statusCode;
	private String responseBody;

	private String client_id;
	private String client_secret;
	private String grant_type;
	private String redirect_uri;
	private String token_api;
	private String subscription_key;
	private String authCode;
	private String access_token;
	WebDriver driver;
	FW_PropertyReader fw_PropertyReader = new FW_PropertyReader();
	FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();

	/**
	 * Returns the client id for authorization on the api chosen by the user to
	 * run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getClient_id();</code>
	 * <p>
	 *
	 * @return client_id
	 */
	public String getClient_id() {
		return client_id;
	}

	/**
	 * Initializes and holds client id for authorization on the api chosen for
	 * test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setClient_id(client_id);</code>
	 * <p>
	 *
	 * @param client_id
	 *            Client id for authorization on the api chosen by the user to
	 *            run the test cases.
	 */
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	/**
	 * Returns the client secret for authorization on the api chosen by the user
	 * to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getClient_secret();</code>
	 * <p>
	 *
	 * @return client_secret
	 */
	public String getClient_secret() {
		return client_secret;
	}

	/**
	 * Initializes and holds client secret for authorization on the api chosen
	 * for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setClient_secret(client_secret);</code>
	 * <p>
	 *
	 * @param client_secret
	 *            Client secret for authorization on the api chosen by the user
	 *            to run the test cases.
	 */
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	/**
	 * Returns the grant type for the authorization api chosen by the user to
	 * run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getGrant_type();</code>
	 * <p>
	 *
	 * @return grant_type
	 */
	private String getGrant_type() {
		return grant_type;
	}

	/**
	 * Initializes and holds grant type for the authorization on the api chosen
	 * for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setGrant_type(grant_type);</code>
	 * <p>
	 *
	 * @param grant_type
	 *            Grant type for authorization on the api chosen for test case
	 *            execution.
	 */
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	/**
	 * Returns the redirect uri to be redirected after successful authentication
	 * on the api chosen by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getRedirect_uri();</code>
	 * <p>
	 *
	 * @return redirect_uri
	 */
	private String getRedirect_uri() {
		return redirect_uri;
	}

	/**
	 * Initializes and holds the redirect uri for api chosen for test case
	 * execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setRedirect_uri(redirect_uri);</code>
	 * <p>
	 *
	 * @param redirect_uri
	 *            Redirect uri for the api chosen for test case execution.
	 */
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	/**
	 * Returns the api name for getting the access token for api chosen by the
	 * user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getToken_api();</code>
	 * <p>
	 *
	 * @return token_api
	 */
	private String getToken_api() {
		return token_api;
	}

	/**
	 * Initializes and holds api name for getting the access token for api
	 * chosen for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setToken_api(token_api);</code>
	 * <p>
	 *
	 * @param token_api
	 *            Api for getting access token for api chosen for test case
	 *            execution.
	 */
	public void setToken_api(String token_api) {
		this.token_api = token_api;
	}

	/**
	 * Returns the subscription key required for the requests to be made on the
	 * api chosen by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getSubscription_key();</code>
	 * <p>
	 *
	 * @return subscription_key
	 */
	private String getSubscription_key() {
		return subscription_key;
	}

	/**
	 * Initializes and holds subscription key for the requests on the api chosen
	 * for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setSubscription_key(subscription_key);</code>
	 * <p>
	 *
	 * @param subscription_key
	 *            Subscription key for the api requests
	 */
	public void setSubscription_key(String subscription_key) {
		this.subscription_key = subscription_key;
	}

	/**
	 * Returns the base URI of the api chosen by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getBaseURI();</code>
	 * <p>
	 *
	 * @return baseURI
	 */
	private String getBaseURI() {
		return baseURI;
	}

	/**
	 * Returns the api name of the api chosen by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getApiName();</code>
	 * <p>
	 *
	 * @return apiName
	 */
	private String getApiName() {
		return apiName;
	}

	/**
	 * Returns the content type of the response (JSON, XML or TEXT) for api test
	 * chosen by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getContentType();</code>
	 * <p>
	 *
	 * @return contentType
	 */

	private ContentType getContentType() {
		return contentType;
	}

	/**
	 * Returns the http request object of type RequestSpecification for api
	 * chosen by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getHttpReuest();</code>
	 * <p>
	 *
	 * @return httpReuest
	 */
	private RequestSpecification getHttpReuest() {
		logger.debug("[FW_RestAssuredAPI:getHttpRequest()] Getting the httpRequest for URL " + getBaseURI() + ".");
		setHttpReuest(RestAssured.given());
		return httpRequest;
	}

	/**
	 * Returns the response object from the api request made by the user to run
	 * the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getResponse();</code>
	 * <p>
	 *
	 * @return response
	 */
	private Response getResponse() {
		logger.debug("[FW_RestAssuredAPI:getResponse()] Geeting response for API " + getApiName() + ".");
		return response;
	}

	/**
	 * Initializes and holds http request object of type RequestSpecification
	 * for api chosen for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setHttpReuest(httpRequest);</code>
	 * <p>
	 *
	 * @param httpRequest
	 *            Http request object of type Request Specification for api
	 *            chosen for test case execution.
	 */
	private void setHttpReuest(RequestSpecification httpRequest) {
		this.httpRequest = httpRequest;
	}

	/**
	 * Initializes and holds the response object from the api request made by
	 * the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setResponse(response);</code>
	 * <p>
	 *
	 * @param response
	 *            Response object from the api request made by the user to run
	 *            the test cases.
	 */
	private void setResponse(Response response) {
		this.response = response;
	}

	/**
	 * Returns the status code from the response body of the api request made by
	 * the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getStatusCode();</code>
	 * <p>
	 *
	 * @return statusCode
	 */
	private int getStatusCode() {
		logger.debug("[FW_RestAssuredAPI:getStatusCode] Geeting response for API " + getApiName() + ".");
		setStatusCode(getResponse().getStatusCode());
		return statusCode;
	}

	/**
	 * Initializes and holds the status code from the response body of the api
	 * request made by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setStatusCode(statusCode);</code>
	 * <p>
	 *
	 * @param statusCode
	 *            Status code from the response body of the api request made by
	 *            the user to run the test cases.
	 */
	private void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Initializes and holds the response body as string for the api request
	 * made by the user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setResponseBody(responseBody);</code>
	 * <p>
	 *
	 * @param responseBody
	 *            Response body obtained from the api chosen for test case
	 *            execution.
	 */
	private void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	/**
	 * Initializes and holds the base Url of the api chosen for test case
	 * execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setBaseURI(baseURI);</code>
	 * <p>
	 *
	 * @param baseURI
	 *            Base Url of the api chosen for test case execution.
	 */
	public void setBaseURI(String baseURI) {
		this.baseURI = baseURI;
	}

	/**
	 * Initializes and holds the api name chosen for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setApiName(apiName);</code>
	 * <p>
	 *
	 * @param apiName
	 *            Api name Url of the api chosen for test case execution.
	 */
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	/**
	 * Initializes and holds the base Url of the api chosen for test case
	 * execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setContentType(contentType);</code>
	 * <p>
	 *
	 * @param contentType
	 *            Content type of response body (JSON, XML or TEXT) chosen by
	 *            user for test case execution.
	 */
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	/**
	 * Returns the response body as string for the api request made by the user
	 * to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getResponseBody();</code>
	 * <p>
	 *
	 * @return responseBody
	 */
	public String getResponseBody() {
		logger.debug("[FW_RestAssuredAPI:getResponseBody()] Geeting response body for API " + getApiName() + ".");
		try {
			RestAssured.baseURI = getBaseURI();
			httpRequest = getHttpReuest();
			httpRequest.auth().oauth2(getAccess_token());
			httpRequest.header("Ocp-Apim-Subscription-Key", getSubscription_key());
			setResponse(httpRequest.get(getApiName()));
			setResponseBody(getResponse().getBody().asString());

		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
		return responseBody;
	}

	/**
	 * Getting and returning the response status code.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * <code>FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</code>
	 * fw_RestAssuredAPI.getResponseStatus(); </code>
	 * <p>
	 *
	 * @return statusCode
	 */

	public int getResponseStatus() {
		logger.debug("[FW_RestAssuredAPI:getResponseStatus()] Getting the reponse status");
		try {
			statusCode = getStatusCode();

		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
		return statusCode;

	}

	/**
	 * Posts the response object params to the server api request made by the
	 * user to run the test cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.postResponse(map);</code>
	 * <p>
	 *
	 * @param map
	 *            object containing params of response body of the api request
	 *            made by the user to run the test cases.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void postResponse(Map map) {
		logger.debug("[FW_RestAssuredAPI:postResponse()] Posting the response");
		try {
			httpRequest = getHttpReuest();
			JSONObject requestParams = new JSONObject();

			Set set = map.entrySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				Map.Entry mentry = (Map.Entry) iterator.next();
				String key = mentry.getKey().toString();
				String value = mentry.getValue().toString();
				requestParams.put(key, value);
			}

			httpRequest.auth().oauth2(getAccess_token());
			httpRequest.header("Ocp-Apim-Subscription-Key", getSubscription_key());
			httpRequest.body(requestParams.toJSONString());

			setResponse(httpRequest.post(getBaseURI() + getApiName()));

		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}

	/**
	 * Getting and returning the access token after successful authentication.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * <code>FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</code>
	 * fw_RestAssuredAPI.getAccess_token(); </code>
	 * <p>
	 *
	 * @return access_token
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * Initializes and holds the access token after successful authentication on
	 * the api chosen for test case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setAccess_token(access_token);</code>
	 * <p>
	 *
	 * @param access_token
	 *            Api name Url of the api chosen for test case execution.
	 */
	private void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * Getting and returning the auth uri for api authorization.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * <code>FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</code>
	 * fw_RestAssuredAPI.getAuthURI(); </code>
	 * <p>
	 *
	 * @return authURI
	 */
	public String getAuthURI() {
		return authURI;
	}

	/**
	 * Initializes and holds the auth uri for api authentication chosen for test
	 * case execution.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.setApiName(apiName);</code>
	 * <p>
	 *
	 * @param authURI
	 *            auth uri for getting the test api authenticated.
	 */
	public void setAuthURI(String authURI) {
		this.authURI = authURI;
	}

	/**
	 * Getting and initializing the access token after successful authentication
	 * on the test api.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.requestAccessToken(driver);</code>
	 * <p>
	 *
	 * @param driver
	 *            WebDriver object for the auth uri launch
	 *
	 * @return access_token
	 */
	@SuppressWarnings({ "unchecked" })
	public String requestAccessToken(String uri) {
		logger.debug("[FW_RestAssuredAPI:requestAccessToken()] Getting the access token");
		try {
			String authCode = getAuthCode(uri);

			httpRequest = getHttpReuest();
			JSONObject requestParams = new JSONObject();

			requestParams.put("client_id", getClient_id());
			requestParams.put("client_secret", getClient_secret());
			requestParams.put("grant_type", getGrant_type());
			requestParams.put("code", authCode);
			requestParams.put("redirect_uri", getRedirect_uri());

			httpRequest.header("Ocp-Apim-Subscription-Key", getSubscription_key());
			httpRequest.header("Content-Type", getContentType());

			httpRequest.body(requestParams.toJSONString());

			setResponse(httpRequest.post(getAuthURI() + getToken_api()));

			JsonPath jsonPath = new JsonPath(getResponse().asString());

			access_token = jsonPath.getString("access_token");

			setAccess_token(access_token);

		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
		return access_token;
	}

	/**
	 * Getting and returning the auth code for api authorization.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getAuthCode();</code>
	 * <p>
	 *
	 * @param driver
	 *            WebDriver object used to launch the auth url
	 *
	 * @return authCode
	 */
	private String getAuthCode(String uri) {
		logger.debug("[FW_RestAssuredAPI:getAuthCode()] Getting the intermediate auth code");
		try {
			String chrome_path = (System.getProperty("user.dir") + FW_Const.BACK_SLASH + fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_CHROME_WEBDRIVER_PATH));
			System.setProperty("webdriver.chrome.driver", chrome_path);

			driver = new ChromeDriver();
			driver.manage().window().setPosition(new Point(-2000, 0));
			driver.get(uri);
			WebElement username = driver.findElement(By.id("userNameInput"));

			WebElement password = driver.findElement(By.id("passwordInput"));

			WebElement signinButton = driver.findElement(By.id("submitButton"));

			username.sendKeys(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_REST_USER));
			password.sendKeys(fw_PropertyReader.getAppPropertyValue(FW_Const.APP_PROP_REST_PATH));
			signinButton.click();

			String currentUrl = driver.getCurrentUrl();
			driver.close();

			String[] elements = currentUrl.split("\\?");
			String code = elements[1];

			String[] element = code.split("=");
			authCode = element[1];

		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
		return authCode;

	}

	/**
	 * Getting the value of passed parameter from the response of the test api.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * FW_RestAssuredAPI fw_RestAssuredAPI = new FW_RestAssuredAPI();</br>
	 * fw_RestAssuredAPI.getParamValue(response, paramName);</code>
	 * <p>
	 *
	 * @param response
	 *            response body in string
	 * @param paramName
	 *            name of parameter from response
	 *
	 * @return paramValue
	 */
	public String getParamValue(String response, String paramName) {
		logger.debug("[FW_RestAssuredAPI:getAuthCode()] Getting the intermediate auth code");
		String paramValue = null;
		try {
			JsonPath jsonPath = new JsonPath(getResponse().asString());

			paramValue = jsonPath.getString(paramName);
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return paramValue;
	}

	public void configureProxy() {
	    System.getProperties().put("https.proxyHost", fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_PROXY_HOST));
        System.getProperties().put("https.proxyPort", fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_PROXY_PORT));
        System.getProperties().put("https.proxySet", fw_PropertyReader.getFrameworkPropertiesValue(FW_Const.FW_PROP_PROXY_SET));
	}

}
