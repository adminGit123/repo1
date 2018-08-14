package com.test.CNHi.pages;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.htap.fw_core.FW_AndroidFunctions;
import com.htap.fw_core.FW_Const;

public class EVOFleetHomePageAndroid
{
    private static Logger logger = LogManager.getLogger(EVOFleetHomePageAndroid.class.getName());

    //Android webdriver functions: Object name <fw_WebDriverFunctions> i have kept same as other parts is exactly same as for web application page.
    FW_AndroidFunctions fw_WebDriverFunctions = new FW_AndroidFunctions();

    private static final String LOCATOR_HOMEPAGEHEADERFIRSTSTRINGLABEL="/html/body/cnh-fleet[1]/tc-page-main[1]/div[1]/cnh-topbar[1]/div[1]/div[1]/div[1]";

    private static final String LOCATOR_HOMEPAGEHEADERSECONDSTRINGLABEL="/html/body/cnh-fleet[1]/tc-page-main[1]/div[1]/cnh-topbar[1]/div[1]/div[1]/div[2]";

    private static final String LOCATOR_SEARCHTEXTFIELD="//input[@name='search']";

    private static final String LOCATOR_SEARCHBUTTON="/html/body/cnh-fleet[1]/tc-page-main[1]/div[2]/tc-page-overview[1]/cnh-layout-horizontal-split[1]/div[1]/cnh-utility-bar[1]/div[1]/div[1]/cnh-action-icon[1]/div[1]/div[1]/div[1]";

    private static final String LOCATOR_FLEETDROPDOWNBUTTON="/html/body/cnh-fleet[1]/tc-page-main[1]/div[2]/tc-page-overview[1]/cnh-layout-horizontal-split[1]/div[1]/cnh-utility-bar[1]/div[1]/div[3]/div[1]/cnh-vehicle-filters[1]/mcl-advanced-select[1]/div[1]/div[1]/span[1]";

    private static final String LOCATOR_FLEETDROPDOWNOPTIONCNH_EVOBUTTON="/html/body/cnh-fleet[1]/tc-page-main[1]/div[2]/tc-page-overview[1]/cnh-layout-horizontal-split[1]/div[1]/cnh-utility-bar[1]/div[1]/div[3]/div[1]/cnh-vehicle-filters[1]/mcl-advanced-select[1]/div[1]/div[2]/div[2]/div[2]/span[1]";

    @FindBy(how=How.XPATH, using=LOCATOR_HOMEPAGEHEADERFIRSTSTRINGLABEL)
    private WebElement HomePageHeaderFirstStringLabel;

    @FindBy(how=How.XPATH, using=LOCATOR_HOMEPAGEHEADERSECONDSTRINGLABEL)
    private WebElement HomePageHeaderSecondStringLabel;

    @FindBy(how=How.XPATH, using=LOCATOR_SEARCHTEXTFIELD)
    private WebElement searchTextField;

    @FindBy(how=How.XPATH, using=LOCATOR_SEARCHBUTTON)
    private WebElement SearchButton;

    @FindBy(how=How.XPATH, using=LOCATOR_FLEETDROPDOWNBUTTON)
    private WebElement FleetDropDownButton;

    @FindBy(how=How.XPATH, using=LOCATOR_FLEETDROPDOWNOPTIONCNH_EVOBUTTON)
    private WebElement FleetDropDownOptionCNH_EVOButton;

    public String getHomePageHeaderFirstStringLabel() {
        logger.info("[EVOHOME1 : getHomePageHeaderFirstStringLabel] Getting HomePageHeaderFirstStringLabel.");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_HOMEPAGEHEADERFIRSTSTRINGLABEL), "HomePageHeaderFirstStringLabel Label", FW_Const.SIXTY);
        String parsedText = HomePageHeaderFirstStringLabel.getText().replaceAll("(?:\n|\r)", "").trim();
        return parsedText;
    }

    public String getHomePageHeaderSecondStringLabel() {
        logger.info("[EVOHOME1 : getHomePageHeaderSecondStringLabel] Getting HomePageHeaderSecondStringLabel.");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_HOMEPAGEHEADERSECONDSTRINGLABEL), "HomePageHeaderSecondStringLabel Label", FW_Const.SIXTY);
        String parsedText = HomePageHeaderSecondStringLabel.getText().replaceAll("(?:\n|\r)", "").trim();
        return parsedText;
    }

    public void enterSearchTextField(String textToBeEntered){
        logger.info("[evoFleetHomePage : enterSearchTextField] Entering '" + textToBeEntered + "' in SearchTextField");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_SEARCHTEXTFIELD), "SearchTextField", FW_Const.SIXTY);
        searchTextField.clear();
        searchTextField.sendKeys(textToBeEntered);
    }

    public String getSearchTextField(){
        logger.info("[evoFleetHomePage : getSearchTextField] Getting text from SearchTextField");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_SEARCHTEXTFIELD), "SearchTextField", FW_Const.SIXTY);
        return searchTextField.getText();
    }

    public void clickSearchButton(){
        logger.info("[EVOHOME1 : clickSearchButton] Clicking 'SearchButton' button");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_SEARCHBUTTON), "SearchButton Button", FW_Const.SIXTY);
        SearchButton.click();
    }

    public void clickFleetDropDownButton(){
        logger.info("[TestEvo : clickFleetDropDownButton] Clicking 'FleetDropDownButton' button");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_FLEETDROPDOWNBUTTON), "FleetDropDownButton Button", FW_Const.SIXTY);
        FleetDropDownButton.click();
    }

    public void clickFleetDropDownOptionCNH_EVOButton(){
        logger.info("[TestEvo : clickFleetDropDownOptionCNH_EVOButton] Clicking 'FleetDropDownOptionCNH_EVOButton' button");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_FLEETDROPDOWNOPTIONCNH_EVOBUTTON), "FleetDropDownOptionCNH_EVOButton Button", FW_Const.SIXTY);
        FleetDropDownOptionCNH_EVOButton.click();
    }

}