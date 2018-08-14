package com.test.CNHi.testdata;

import java.util.ArrayList;

import com.cnhi.DataConnector.DataReader;
import com.cnhi.DataConnector.NameValuePair;
import com.htap.fw_core.FW_UtilFunctions;
import com.test.CNHi.pages.EVOFleetHomePage;

public class D_EVOFleetHomePage extends EVOFleetHomePage {

	private String AppHomePageHeaderFirstString;
	private String AppHomePageHeaderSecondString;

	public String geAppHomePageHeaderFirstString() {
		return AppHomePageHeaderFirstString;
	}
	public String getAppHomePageHeaderSecondString() {
		return AppHomePageHeaderSecondString;
	}

	private void setAppHomePageHeaderFirstString(String AppHomePageHeaderFirstString) {
		this.AppHomePageHeaderFirstString = AppHomePageHeaderFirstString;
	}

	private void setAppHomePageHeaderSecondString(String AppHomePageHeaderSecondString) {
		this.AppHomePageHeaderSecondString = AppHomePageHeaderSecondString;
	}

	public DataReader fw_Data = new DataReader();

	FW_UtilFunctions fw_UtilFunctions = new FW_UtilFunctions();

	private void setInputValues (ArrayList<NameValuePair> row) {
		setAppHomePageHeaderFirstString(row.get(fw_Data.getColumnIndex("setAppHomePageHeaderFirstString", row)).getElementValue());
		setAppHomePageHeaderSecondString(row.get(fw_Data.getColumnIndex("setAppHomePageHeaderSecondString", row)).getElementValue());
	}

	public void getInputData(String inputFilename, Integer recordNumber) {
		fw_Data.setFilePath(fw_UtilFunctions.getAbsoluteTestDataFilePath(inputFilename));
		ArrayList<ArrayList<NameValuePair>> records = new ArrayList<ArrayList<NameValuePair>>();
		records = fw_Data.getFileContents();
		setInputValues(records.get(recordNumber));
	}
}
