package com.test.CNHi.testdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cnhi.DataConnector.DataReader;
import com.cnhi.DataConnector.NameValuePair;
import com.htap.fw_core.FW_UtilFunctions;

/**
 * Base class that creates and holds execution <code>api</code>,
 * <code>method</code>, <code>contentType</code>, <code>status</code>,
 * <code>response</code> information for current execution cycle.
 * <p>
 * User can refer to these variables by creating object of type
 * <code>D_RestAssuredTestApiInput</code>
 * <p>
 * <b>Usage:</b><br>
 * <code>D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();</code>
 *
 * @author Vishal Kumar
 *
 */
public class D_RestAssuredTestApiInput {

	private String api;
	private String contentType;
	private String statusCode;
	private String response;

	DataReader fw_data = new DataReader();
	FW_UtilFunctions fw_UtilFunctions=new FW_UtilFunctions();

	/**
	 * Returns the api name of the api test result output. cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();<br>
	 * d_RestAssuredTestApiInput.getApi();</code>
	 * <p>
	 *
	 * @return api
	 */
	public String getApi() {
		return api;
	}

	/**
	 * Initializes and holds the api name of the api test result output.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();<br>
	 * d_RestAssuredTestApiInput.setApi();</code>
	 * <p>
	 *
	 * @param api
	 *            API name of the api request
	 */
	public void setApi(String api) {
		this.api = api;
	}

	/**
	 * Returns the content type (JSON, XML or TEXT) for the response obtained
	 * from the api test result output.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();<br>
	 * d_RestAssuredTestApiInput.getContentType();</code>
	 * <p>
	 *
	 * @return contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Initializes and holds the content type (JSON, XML or TEXT) of the api
	 * test result output.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();<br>
	 * d_RestAssuredTestApiInput.setContentType();</code>
	 * <p>
	 *
	 * @param contentType
	 *            Content type of the response obtained from the api test result
	 *            output.
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * Returns the status code obtained from the api test result output.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();<br>
	 * d_RestAssuredTestApiInput.getStatusCode();</code>
	 * <p>
	 *
	 * @return statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Initializes and holds the status code obtained from the api test result
	 * output.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();<br>
	 * d_RestAssuredTestApiInput.setStatusCode();</code>
	 * <p>
	 *
	 * @param statusCode
	 *            Status code obtained from the api test result
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Returns the response of the api test result output. cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();<br>
	 * d_RestAssuredTestApiInput.getResponse();</code>
	 * <p>
	 *
	 * @return response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * Initializes and holds the response of the api test result output.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();<br>
	 * d_RestAssuredTestApiInput.setResponse();</code>
	 * <p>
	 *
	 * @param response
	 *            Actual response got from the api test result
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * Initializes and holds the values of parameters <code>api</code>,
	 * <code>method</code>, <code>contentType</code>, <code>status</code>,
	 * <code>response</code>
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();<br>
	 * d_RestAssuredTestApiInput.setInputValues(row);</code>
	 * <p>
	 *
	 * @param row
	 *            NameValuePair Row passed for setting values of params.
	 */
	public void setInputValues(ArrayList<NameValuePair> row) {
		setApi(row.get(fw_data.getColumnIndex("api", row)).getElementValue());
		setContentType(row.get(fw_data.getColumnIndex("contentType", row)).getElementValue());
		setStatusCode(row.get(fw_data.getColumnIndex("statusCode", row)).getElementValue());
		setResponse(row.get(fw_data.getColumnIndex("response", row)).getElementValue());
	}

	/**
	 * Returns the values of the provided record number as argument from the api
	 * test result that has been run by the user. cases.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();<br>
	 * d_RestAssuredTestApiInput.getInputData(inputFileName, recordNumber);</code>
	 * <p>
	 *
	 * @param inputFileName
	 *            full path of test file to be executed.
	 * @param recordNumber
	 *            record for which data is needed.
	 */
	public void getInputData(String inputFileName, Integer recordNumber) {
		fw_data.setFilePath(fw_UtilFunctions.getAbsoluteTestDataFilePath(inputFileName));
		ArrayList<ArrayList<NameValuePair>> record = new ArrayList<ArrayList<NameValuePair>>();
		record = fw_data.getFileContents();
		setInputValues(record.get(recordNumber));
	}

	/**
	 * Format the response and put the parameters in a map object.
	 * <p>
	 * <b>Usage:</b><br>
	 * <code>
	 * D_RestAssuredTestApiInput d_RestAssuredTestApiInput = new D_RestAssuredTestApiInput();<br>
	 * d_RestAssuredTestApiInput.formatResponse();</code>
	 * <p>
	 *
	 * @return map
	 */
	public Map<String, String> formatResponse() {
		String response = getResponse();
		response = response.replaceAll("[{}]", "");
		response = response.replaceAll("\"", "");
		Map<String, String> map = new HashMap<String, String>();

		String[] formatResponse = response.split(",");

		for (String array : formatResponse) {
			String[] elements = array.split(":");
			map.put(elements[0].trim(), elements[1].trim());
		}
		return map;
	}
}
