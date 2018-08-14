package com.test.CNHi.pages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.htap.fw_core.FW_AndroidFunctions;
import com.htap.fw_core.FW_Const;

public class EVOFleetApplicationLoginPageAndroid
{
    private static Logger logger = LogManager.getLogger(EVOFleetApplicationLoginPageAndroid.class.getName());

    //Android webdriver functions: Object name <fw_WebDriverFunctions> i have kept same as other parts is exactly same as for web application page.
    FW_AndroidFunctions fw_WebDriverFunctions = new FW_AndroidFunctions();

    private static final String LOCATOR_USERNAMETEXTFIELD="id(\"userNameInput\")";

    private static final String LOCATOR_PASSWORDTEXTFIELD="id(\"passwordInput\")";

    private static final String LOCATOR_SIGNINBUTTON="id(\"submitButton\")";



    @FindBy(how=How.XPATH, using=LOCATOR_USERNAMETEXTFIELD)
    private WebElement UserNameTextField;

    @FindBy(how=How.XPATH, using=LOCATOR_PASSWORDTEXTFIELD)
    private WebElement PasswordTextField;

    @FindBy(how=How.XPATH, using=LOCATOR_SIGNINBUTTON)
    private WebElement SignInButton;



    public void enterUserNameTextField(String textToBeEntered){
        logger.info("[EVOFleetApplicationLoginPage : enterUserNameTextField] Entering '" + textToBeEntered + "' in UserNameTextField");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_USERNAMETEXTFIELD), "UserNameTextField", FW_Const.SIXTY);
        UserNameTextField.clear();
        UserNameTextField.sendKeys(textToBeEntered);
    }

    public String getUserNameTextField(){
        logger.info("[EVOFleetApplicationLoginPage : getUserNameTextField] Getting text from UserNameTextField");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_USERNAMETEXTFIELD), "UserNameTextField", FW_Const.SIXTY);
        return UserNameTextField.getText();
    }

    public void enterPasswordTextField(String textToBeEntered){
        logger.info("[EVOFleetApplicationLoginPage : enterPasswordTextField] Entering " + "-"+ "in PasswordTextField");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_PASSWORDTEXTFIELD), "PasswordTextField", FW_Const.SIXTY);
        PasswordTextField.clear();
        PasswordTextField.sendKeys(textToBeEntered);
    }

    public String getPasswordTextField(){
        logger.info("[EVOFleetApplicationLoginPage : getPasswordTextField] Getting text from PasswordTextField");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_PASSWORDTEXTFIELD), "PasswordTextField", FW_Const.SIXTY);
        return PasswordTextField.getText();
    }

    public void clickSignInButton(){
        logger.info("[ : clickSignInButton] Clicking 'SignInButton' button");
        fw_WebDriverFunctions.waitForElementToAppear(By.xpath(LOCATOR_SIGNINBUTTON), "SignInButton Button", FW_Const.SIXTY);
        SignInButton.click();
    }

  //This is a wrapper function which is created manually for login. In test case class instead of calling each interaction fucntion manually this one wrapper function can be called.
  	public void wrapperSignIn(String username, String password ){
  		logger.info("[ : Wrapper login function] ");

  		enterUserNameTextField(username);
  		enterPasswordTextField(password);
  		clickSignInButton();

  	}
}