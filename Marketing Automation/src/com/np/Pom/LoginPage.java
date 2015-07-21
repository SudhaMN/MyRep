package com.np.Pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public static String defaultUserName=null;
	public static String defaultPassword=null;
	
	@FindBy(id="retEmail")
	public WebElement userName;
	
	@FindBy(id="retPassword")
	public WebElement password;
	
	@FindBy(xpath="//input[@value='Log In']")
	public WebElement loginButton;
	
	@FindBy(xpath="//div[@class='error']")
	public WebElement loginErrorMessage;
	
	
	
	public LoginPage(WebDriver driver,String userName,String password)
	{
		PageFactory.initElements(driver,this);
		defaultUserName=userName;
		defaultPassword=password;
	}
	
	public void login(String userName,String password)
	{
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		this.loginButton.click();
	}
	
	public void login(){
		login(defaultUserName,defaultPassword);
	}
	
}
