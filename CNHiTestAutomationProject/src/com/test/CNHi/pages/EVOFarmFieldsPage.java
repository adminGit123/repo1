package com.test.CNHi.pages;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cnhi.DataConnector.DataReader;
import com.cnhi.DataConnector.ExcelWriter;
import com.htap.fw_core.FW_Const;
import com.htap.fw_core.FW_WebDriver;
import com.htap.fw_core.FW_WebDriverFunctions;

public class EVOFarmFieldsPage
{
	private static Logger logger = LogManager.getLogger(EVOFarmFieldsPage.class.getName());

    FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();
    
	FW_WebDriver fw_WebDriver=new FW_WebDriver();
	WebDriver d=fw_WebDriver.getWebDriver();


    private static final String LOCATOR_SEARCH_FIELD="/html/body/cnh-farm/cnh-page-main/cnh-main-container/div/div[3]/tc-page-map/div/cnh-layout-horizontal-split/div/div[1]/cnh-utility-bar/div/div[1]/div[1]/div/cnh-search/div";
	
    private static final String LOCATOR_GROWER_FIELD="/html/body/cnh-farm/cnh-page-main/cnh-main-container/div/div[3]/tc-page-map/div/cnh-layout-horizontal-split/div/div[2]/cnh-layout-vertical-split/div/div[1]/div[2]/tc-overview-sidebar/div/cnh-tabs/div/div[2]/cnh-tab-content[1]/div/cnh-gff-widget/div/div/cnh-loader/div/div[2]/div/cnh-collapsible[1]/div/div[1]/div[1]/cnh-list-header/div/div";
  
    private static final String LOCATOR_FARM_FIELD="/html/body/cnh-farm/cnh-page-main/cnh-main-container/div/div[3]/tc-page-map/div/cnh-layout-horizontal-split/div/div[2]/cnh-layout-vertical-split/div/div[1]/div[2]/tc-overview-sidebar/div/cnh-tabs/div/div[2]/cnh-tab-content[1]/div/cnh-gff-widget/div/div/cnh-loader/div/div[2]/div/cnh-collapsible[1]/div/div[2]/div/cnh-collapsible[1]/div/div[1]/div[1]/cnh-list-header/div/div";
    
    private static final String LOCATOR_FIELD="/html/body/cnh-farm/cnh-page-main/cnh-main-container/div/div[3]/tc-page-map/div/cnh-layout-horizontal-split/div/div[2]/cnh-layout-vertical-split/div/div[1]/div[2]/tc-overview-sidebar/div/cnh-tabs/div/div[2]/cnh-tab-content[1]/div/cnh-gff-widget/div/div/cnh-loader/div/div[2]/div/cnh-collapsible[1]/div/div[2]/div/cnh-collapsible[1]/div/div[2]/div/cnh-field-list-item[1]/div/div[1]/div";
    
   
    @FindBy(how=How.XPATH, using=LOCATOR_SEARCH_FIELD)
    private WebElement SearchFieldBox;
     
    @FindBy(how=How.XPATH, using=LOCATOR_GROWER_FIELD)
    private WebElement GrowerField;
  
    @FindBy(how=How.XPATH, using=LOCATOR_FARM_FIELD)
    private WebElement FarmField;

    @FindBy(how=How.XPATH, using=LOCATOR_FIELD)
    private WebElement Field;
    
    
	public void getGFFValue() {

		
		logger.info("[TestEvo : getGFFValue] getting 'getGFFValue' GROWER FARM FIELD VALUE");
		
		DataReader dataReader = new DataReader();

		Map<String, Object[]> data = new HashMap<String, Object[]>();

		dataReader.setFilePath(System.getProperty("user.dir") + "\\" + "FieldInputData.xlsx");

		dataReader.getXLSXContent();

		ArrayList<ArrayList<String>> listXlsxData = dataReader.getDataListArray();

		String fieldValue = listXlsxData.get(0).get(0);

		fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_SEARCH_FIELD), "Status List", FW_Const.SIXTY);

		try {
			Actions actions = new Actions(d);
			actions.moveToElement(SearchFieldBox);
			actions.click();
			actions.sendKeys(fieldValue);
			actions.build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_GROWER_FIELD), "GFFData Label", FW_Const.SIXTY);

		// First Row will be act as column header and second onward the data
		data.put("1", new Object[] { "GROWER", "FARM", "FIELD" });
		data.put("2", new Object[] { GrowerField.getText(), FarmField.getText(), Field.getText() });

		ExcelWriter ew = new ExcelWriter();
		ew.createExcel("FieldValue", data);
	}
	
	
	
	
	public String getActualGFFValue() {
		
		logger.info("[TestEvo : getActualGFFValue] getting 'getActualGFFValue' GROWER FARM FIELD VALUE");

		DataReader dataReader = new DataReader();

		Map<String, Object[]> data = new HashMap<String, Object[]>();

		dataReader.setFilePath(System.getProperty("user.dir") + "\\" + "FieldInputData.xlsx");

		dataReader.getXLSXContent();

		ArrayList<ArrayList<String>> listXlsxData = dataReader.getDataListArray();

		String fieldValue = listXlsxData.get(0).get(0);

		fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_SEARCH_FIELD), "Status List", FW_Const.SIXTY);

		Actions actions = new Actions(d);
		actions.moveToElement(SearchFieldBox);
		actions.click();
		actions.sendKeys(fieldValue);
		actions.build().perform();

		fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_GROWER_FIELD), "GFFData Label", FW_Const.SIXTY);

		// First Row will be act as column header and second onward the data
		data.put("1", new Object[] { "GROWER", "FARM", "FIELD" });
		data.put("2", new Object[] { GrowerField.getText(), FarmField.getText(), Field.getText() });

		String CombineFieldsValue = GrowerField.getText() + "-" + FarmField.getText() + "-" + Field.getText();

		return CombineFieldsValue;

	}
	
	
	public String geExpectedFieldsFromExcel() {
		
		logger.info("[TestEvo : geExpectedFieldsFromExcel] getting 'geExpectedFieldsFromExcel' GROWER FARM FIELD VALUE");

		DataReader dataReader = new DataReader();

		dataReader.setFilePath(System.getProperty("user.dir") + "\\" + "FieldValue.xlsx");

		dataReader.getXLSXContent();

		ArrayList<ArrayList<String>> listXlsxData = dataReader.getDataListArray();

		String Grower = listXlsxData.get(0).get(0);

		String Farm = listXlsxData.get(0).get(1);

		String Fields = listXlsxData.get(0).get(2);

		String CombineFieldsValue = Grower + "-" + Farm + "-" + Fields;

		return CombineFieldsValue;

	}
	
	@SuppressWarnings("unused")
	public String geDataFromExcel() {

		String Success = null,failed = null;
		
		DataReader dataReader = new DataReader();

		dataReader.setFilePath(System.getProperty("user.dir") + "\\" + "FieldValue.xlsx");

		dataReader.getXLSXContent();

		ArrayList<ArrayList<String>> listXlsxData = dataReader.getDataListArray();

		String Grower = listXlsxData.get(0).get(0);

		String Farm = listXlsxData.get(0).get(1);

		String Fields = listXlsxData.get(0).get(2);

		String CombineFieldsValue = Grower + "-" + Farm + "-" + Fields;

		if (CombineFieldsValue != null) {
			return "Data Inserted In FieldValue ExcelSheet";
		} else {
			return"Data Insertion Failed in ExcelSheet";
		}
 }
	
	
	
	

		

}