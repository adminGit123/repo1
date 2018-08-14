package com.test.CNHi.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.htap.fw_core.FW_Const;
import com.htap.fw_core.FW_WebDriverFunctions;

public class RestAssuredLoginPage {

	private static Logger logger = LogManager.getLogger(EVOFleetApplicationLoginPage.class.getName());
	FW_WebDriverFunctions fw_WebDriverFunctions = new FW_WebDriverFunctions();

	private static final String LOCATOR_USERNAMETEXTFIELD="userNameInput";

	private static final String LOCATOR_PASSWORDTEXTFIELD="passwordInput";

	private static final String LOCATOR_SIGNINBUTTON="submitButton";

	@FindBy(how=How.ID, using=LOCATOR_USERNAMETEXTFIELD)
	private WebElement UserNameTextField;

	@FindBy(how=How.ID, using=LOCATOR_PASSWORDTEXTFIELD)
	private WebElement PasswordTextField;

	@FindBy(how=How.ID, using=LOCATOR_SIGNINBUTTON)
	private WebElement SignInButton;

	public void enterUserNameTextField(String textToBeEntered){
		logger.info("[RestAssuredLoginPage : enterUserNameTextField] Entering '" + textToBeEntered + "' in UserNameTextField");
		fw_WebDriverFunctions.waitForElementToAppear(By.id(LOCATOR_USERNAMETEXTFIELD), "UserNameTextField", FW_Const.SIXTY);
		UserNameTextField.clear();
		UserNameTextField.sendKeys(textToBeEntered);
	}

	public String getUserNameTextField(){
		logger.info("[RestAssuredLoginPage : getUserNameTextField] Getting text from UserNameTextField");
		fw_WebDriverFunctions.waitForElementToAppear(By.id(LOCATOR_USERNAMETEXTFIELD), "UserNameTextField", FW_Const.SIXTY);
		return UserNameTextField.getText();
	}

	public void enterPasswordTextField(String textToBeEntered){
		logger.info("[RestAssuredLoginPage : enterPasswordTextField] Entering '" + "password" + "' in PasswordTextField");
		fw_WebDriverFunctions.waitForElementToAppear(By.id(LOCATOR_PASSWORDTEXTFIELD), "PasswordTextField", FW_Const.SIXTY);
		PasswordTextField.clear();
		PasswordTextField.sendKeys(textToBeEntered);
	}

	public String getPasswordTextField(){
		logger.info("[RestAssuredLoginPage : getPasswordTextField] Getting text from PasswordTextField");
		fw_WebDriverFunctions.waitForElementToAppear(By.id(LOCATOR_PASSWORDTEXTFIELD), "PasswordTextField", FW_Const.SIXTY);
		return PasswordTextField.getText();
	}

	public void clickSignInButton(){
		logger.info("[ : clickSignInButton] Clicking 'SignInButton' button");
		fw_WebDriverFunctions.waitForElementToAppear(By.id(LOCATOR_SIGNINBUTTON), "SignInButton Button", FW_Const.SIXTY);
		SignInButton.click();
	}

	//This is a wrapper function which is created manually for login. In test case class instead of calling each interaction function manually this one wrapper function can be called.
	public void wrapperSignIn(String username, String password ){
		logger.info("[ : Wrapper login function] ");

		enterUserNameTextField(username);
		enterPasswordTextField(password);
		clickSignInButton();

	}


}

