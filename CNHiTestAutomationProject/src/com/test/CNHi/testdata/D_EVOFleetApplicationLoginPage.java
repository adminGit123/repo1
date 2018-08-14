package com.test.CNHi.testdata;

import java.util.ArrayList;

import com.cnhi.DataConnector.DataReader;
import com.cnhi.DataConnector.NameValuePair;
import com.htap.fw_core.FW_UtilFunctions;
import com.test.CNHi.pages.EVOFleetApplicationLoginPage;

public class D_EVOFleetApplicationLoginPage extends EVOFleetApplicationLoginPage {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public DataReader fw_Data = new DataReader();

	FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();

	private void setInputValues (ArrayList<NameValuePair> row) {
		setUsername(row.get(fw_Data.getColumnIndex("setUsername", row)).getElementValue());
		setPassword(row.get(fw_Data.getColumnIndex("setPassword", row)).getElementValue());
	}

	public void getInputData(String inputFilename, Integer recordNumber) {
		fw_Data.setFilePath(fw_UtilFunctions.getAbsoluteTestDataFilePath(inputFilename));
		ArrayList<ArrayList<NameValuePair>> records = new ArrayList<ArrayList<NameValuePair>>();
		records = fw_Data.getFileContents();
		setInputValues(records.get(recordNumber));
	}
}
