package com.test.CNHi.pages;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.htap.fw_core.FW_PropertyReader;
import com.htap.fw_core.FW_WebDriver;
import com.htap.fw_core.FW_WebDriverFunctions;

public class EVOFarmHomePage
{

    private static Logger logger = LogManager.getLogger(EVOFarmHomePage.class.getName());

    FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();

    FW_PropertyReader fw_PropertyReader = new FW_PropertyReader();

	FW_WebDriver fw_WebDriver=new FW_WebDriver();
	WebDriver d=fw_WebDriver.getWebDriver();

    private static final String LOCATOR_HOMEPAGEHEADERSTRINGLABEL="/html/body/cnh-farm/cnh-page-main/cnh-main-container/div/div[1]/cnh-main-menu/div/div[2]/span[2]";

    private static final String LOCATOR_FEILDBUTTON="/html/body/cnh-farm/tc-page-main/tc-page-wrapper/div/tc-page-map/div/div[1]/tc-overview-sidebar/div/tc-tabs/div/ul/li[1]";

    private static final String LOCATOR_VEHICLESBUTTON="/html/body/cnh-farm/cnh-page-main/cnh-main-container/div/div[3]/tc-page-map/div/cnh-layout-horizontal-split/div/div[2]/cnh-layout-vertical-split/div/div[1]/div[2]/tc-overview-sidebar/div/cnh-tabs/div/div[1]/cnh-tab-header[2]/div/span";

    private static final String LOCATOR_SEARCHBUTTON="/html/body/cnh-farm/tc-page-main/tc-page-wrapper/div/tc-page-map/div/div[1]/tc-overview-sidebar/div/tc-tabs/div/tc-tab[2]/div/cnh-vehicle-widget/div/div[1]/tc-search/div/span";

    private static final String LOCATOR_FLEETDROPDOWNBUTTON="/html/body/cnh-farm/tc-page-main/tc-page-wrapper/div/tc-page-map/div/div[1]/tc-overview-sidebar/div/tc-tabs/div/tc-tab[2]/div/cnh-vehicle-widget/div/div[1]/mcl-advanced-select/div/div";

    private static final String LOCATOR_MAP="/html/body/cnh-farm/tc-page-main/tc-navbar-secondary/div/ul/li[1]";

    private static final String LOCATOR_FIELD_DATA="/html/body/cnh-farm/tc-page-main/tc-navbar-secondary/div/ul/li[2]";

    private static final String LOCATOR_DROPDOWN_FIELD="/html/body/cnh-farm/tc-page-main/tc-page-wrapper/div/tc-page-map/div/div[1]/tc-overview-sidebar/div/cnh-tabs/div/div[2]/cnh-tab-content[1]/div/tc-gff-widget/div/div[1]/mcl-advanced-select[1]/div/div";

    private static final String LOCATOR_DROPDOWN_CHARLIE_FIELD="/html/body/cnh-farm/tc-page-main/tc-page-wrapper/div/tc-page-map/div/div[1]/tc-overview-sidebar/div/cnh-tabs/div/div[2]/cnh-tab-content[1]/div/tc-gff-widget/div/div[1]/mcl-advanced-select[1]/div/div/span";

    private static final String LOCATOR_APPLICATION_BUTTON="/html/body/cnh-farm/cnh-page-main/cnh-main-container/div/div[1]/cnh-main-menu/div/div[2]/span[1]";

    private static final String LOCATOR_FLEET_ICON="/html/body/cnh-farm/cnh-page-main/cnh-main-container/div/div[2]/cnh-application-menu/div/a[3]/span[1]";

    private static final String LOCATOR_STATUS_LIST="//div[@class='text-top']";

    private static final String LOCATOR_SEARCH_BOX="/html/body/cnh-fleet/tc-page-main/cnh-main-container/div/div[3]/div[1]/tc-page-overview/tc-page-fleet-list/cnh-layout-horizontal-split/div/div[1]/cnh-utility-bar/div/div[1]/div[1]/div/cnh-search";

    private static final String LOCATOR_SEARCH_FARM_BOX="/html/body/cnh-farm/cnh-page-main/cnh-main-container/div/div[3]/tc-page-map/div/cnh-layout-horizontal-split/div/div[1]/cnh-utility-bar/div/div[1]/div[1]/div/cnh-search";

    private static final String LOCATOR_SINGLE_STATUS="/html/body/cnh-fleet/tc-page-main/cnh-main-container/div/div[3]/div[1]/tc-page-overview/tc-page-fleet-list/cnh-layout-horizontal-split/div/div[2]/div/cnh-fleet-list/div/cnh-table/div[2]/cnh-loader/div/div[2]/cnh-collapsible/div/div[2]/div/cnh-fleet-list-item/div/cnh-grid/div/div[3]/div/div[2]/span";

    private static final String LOCATOR_MOVE_APPLICATION_BUTTON="/html/body/cnh-fleet/tc-page-main/cnh-main-container/div/div[1]/cnh-main-menu/div/div[2]/span[1]";

    private static final String LOCATOR_FARM_BUTTON="/html/body/cnh-fleet/tc-page-main/cnh-main-container/div/div[2]/cnh-application-menu/div/a[2]/span[1]";

    private static final String LOCATOR_VEHICLE_BOX="/html/body/cnh-farm/cnh-page-main/cnh-main-container/div/div[3]/tc-page-map/div/cnh-layout-horizontal-split/div/div[2]/cnh-layout-vertical-split/div/div[1]/div[2]/tc-overview-sidebar/div/cnh-tabs/div/div[2]/cnh-tab-content[2]/div/cnh-vehicle-widget/div/div/cnh-loader/div/div[2]/div/cnh-collapsible/div/div[2]/div/cnh-vehicle-list-item[5]/div/div";

    private static final String LOCATOR_VEHICLE_ICON="//*[@id=\"main-map\"]/div[1]/div[4]/div[1]/div/div[1]";

    private static final String LOCATOR_ICON_STATUS="/html/body/cnh-farm/cnh-page-main/cnh-main-container/div/div[3]/tc-page-map/div/cnh-layout-horizontal-split/div/div[2]/cnh-layout-vertical-split/div/div[2]/tc-map-widget/div/tc-balloon/div/cnh-balloon-vehicle-content/div/div[2]/cnh-loader/div/div[2]/div/div[1]/div[3]/cnh-status-widget/div[2]";



   @FindBy(how=How.XPATH, using=LOCATOR_HOMEPAGEHEADERSTRINGLABEL)
    private WebElement HomePageHeaderStringLabel;

    @FindBy(how=How.XPATH, using=LOCATOR_FEILDBUTTON)
    private WebElement FieldButton;

    @FindBy(how=How.XPATH, using=LOCATOR_VEHICLESBUTTON)
    private WebElement VehiclesButton;

    @FindBy(how=How.XPATH, using=LOCATOR_SEARCHBUTTON)
    private WebElement SearchButton;

    @FindBy(how=How.XPATH, using=LOCATOR_FLEETDROPDOWNBUTTON)
    private WebElement FleetDropDownButton;

    @FindBy(how=How.XPATH, using=LOCATOR_MAP)
    private WebElement Map;

    @FindBy(how=How.XPATH, using=LOCATOR_FIELD_DATA)
    private WebElement FieldData;

    @FindBy(how=How.XPATH, using=LOCATOR_DROPDOWN_FIELD)
    private WebElement DropDownFieldData;

    @FindBy(how=How.XPATH, using=LOCATOR_DROPDOWN_CHARLIE_FIELD)
    private WebElement DropDownCharlieFieldData;


    @FindBy(how=How.XPATH, using=LOCATOR_APPLICATION_BUTTON)
    private WebElement ApplicatonButton;

    @FindBy(how=How.XPATH, using=LOCATOR_FLEET_ICON)
    private WebElement FleetIcon;

    @FindBy(how=How.XPATH, using=LOCATOR_STATUS_LIST)
    private List<WebElement> StatusList;

    @FindBy(how=How.XPATH, using=LOCATOR_SEARCH_BOX)
    private WebElement Searchbox;

    @FindBy(how=How.XPATH, using=LOCATOR_SINGLE_STATUS)
    private WebElement SingleStatus;


    @FindBy(how=How.XPATH, using=LOCATOR_MOVE_APPLICATION_BUTTON)
    private WebElement MoveFarm;

    @FindBy(how=How.XPATH, using=LOCATOR_FARM_BUTTON)
    private WebElement FarmButton;


    @FindBy(how=How.XPATH, using=LOCATOR_SEARCH_FARM_BOX)
    private WebElement SearchFarmBox;

    @FindBy(how=How.XPATH, using=LOCATOR_VEHICLE_BOX)
    private WebElement VechiclesBox;

    @FindBy(how=How.XPATH, using=LOCATOR_VEHICLE_ICON)
    private WebElement VechiclesIcon;

    @FindBy(how=How.XPATH, using=LOCATOR_ICON_STATUS)
    private WebElement IconStatus;


  public String getHomePageHeaderStringLabel() {
        logger.info("[EVOHOME1 : getHomePageHeaderStringLabel] Getting HomePageHeaderFirstStringLabel.");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_HOMEPAGEHEADERSTRINGLABEL), "HomePageHeaderStringLabel Label", FW_Const.SIXTY);
        String parsedText = HomePageHeaderStringLabel.getText().replaceAll("(?:\n|\r)", "").trim();
        return parsedText;
    }



    public void clickSearchButton(){
        logger.info("[evoFleetHomePage : clickSearchButton] Getting text from SearchTextField");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_SEARCHBUTTON), "SearchButton", FW_Const.SIXTY);
        SearchButton.click();
    }

    public void clickFleetDropdownButton(){
        logger.info("[EVOHOME1 : clickFleetDropdownButton] Clicking 'SearchButton' button");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_FLEETDROPDOWNBUTTON), "FleetDropdownButton Button", FW_Const.SIXTY);
        FleetDropDownButton.click();
    }

    public void clickMapButton(){
        logger.info("[TestEvo : clickMapButton] Clicking 'clickMapButton' button");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_MAP), "MapButton Button", FW_Const.SIXTY);
        Map.click();
    }

    public void clickFieldDataButton(){
        logger.info("[TestEvo : clickFieldDataButton] Clicking 'clickFieldDataButton' button");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_FIELD_DATA), "FieldDataButton Button", FW_Const.SIXTY);
        FieldData.click();
    }

    public void clickDropDownTextField() throws InterruptedException{
        logger.info("[TestEvo : clickFieldDataButton] Clicking 'DropDownTextField' button");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_DROPDOWN_FIELD), "Field Button", FW_Const.SIXTY);
        DropDownFieldData.click();

  }


    public void clickApplicationButton() {
    	logger.info("[TestEvo : clickApplicationButton] Clicking 'ApplicationButton' button");
    	fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_APPLICATION_BUTTON), "Application Button", FW_Const.SIXTY);
    	ApplicatonButton.click();

    }


    public void clickFleetIcon() {
    	logger.info("[TestEvo : clickFleetIcon] Clicking 'FleetIcon' button");
    	fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_FLEET_ICON), "Fieet Button", FW_Const.SIXTY);
    	FleetIcon.click();

    }





	public void getGlobalStatusOfVehicles() {

		try {

			logger.info("[TestEvo : getGlobalStatusOfVehicles] getting 'GlobalStatusOfVehicles' Status");

			fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_STATUS_LIST), "Status List", FW_Const.SIXTY);

			Map<String, Object[]> data = new HashMap<String, Object[]>();

			int statusListSize = StatusList.size();

			for (int j = statusListSize-1; j < statusListSize; j++) {

				fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_SEARCH_BOX), "Status List", FW_Const.SIXTY);

				Actions actions = new Actions(d);

				actions.moveToElement(Searchbox);
				actions.click();
				actions.sendKeys(StatusList.get(j).getText());
				actions.build().perform();

				fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_SINGLE_STATUS), "Status List", FW_Const.SIXTY);



				// First Row will be act as column header and second onward the data
				Thread.sleep(1000);
				String vehicleID = StatusList.get(0).getText();
				String status = SingleStatus.getText();
				Thread.sleep(1000);

				data.put("1", new Object[] { "VEHICLE_ID", "STATUS" });
				data.put("2", new Object[] { vehicleID, status });

				ExcelWriter ew = new ExcelWriter();
				ew.createExcel("CNHiVehicleStatus", data);

				fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_MOVE_APPLICATION_BUTTON), "Status List",FW_Const.SIXTY);

				MoveFarm.click();

				fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_FARM_BUTTON), "Status List", FW_Const.SIXTY);

				FarmButton.click();

				fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_VEHICLESBUTTON), "Status List", FW_Const.SIXTY);

				VehiclesButton.click();

				fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_SEARCH_FARM_BOX), "Status List", FW_Const.SIXTY);

				actions.moveToElement(SearchFarmBox);
				actions.click();
				actions.sendKeys(vehicleID);
				actions.build().perform();

				fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_VEHICLE_BOX), "Status List", FW_Const.SIXTY);

				VechiclesBox.click();
		}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public String getActualStatusOfVechicle() {

		try {

			logger.info("[TestEvo : getActualStatusOfVechicle] getting 'getActualStatusOfVechicle' Status");

			fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_VEHICLE_ICON), "Status List", FW_Const.SIXTY);

			Actions actions = new Actions(d);
			actions.moveToElement(VechiclesIcon);
			actions.click();
			actions.build().perform();

			fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_ICON_STATUS), "Status List", FW_Const.SIXTY);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return IconStatus.getText();

	}

	public String geExpectedStatusFromExcel() {

		logger.info("[TestEvo : geExpectedStatusFromExcel] getting 'geExpectedStatusFromExcel' Status");

		DataReader dataReader = new DataReader();

		dataReader.setFilePath(System.getProperty("user.dir")+"\\"+"CNHiVehicleStatus.xlsx");

		dataReader.getXLSXContent();

		ArrayList<ArrayList<String>> listXlsxData = dataReader.getDataListArray();

		String status = listXlsxData.get(0).get(1);

		return status;
	}


}