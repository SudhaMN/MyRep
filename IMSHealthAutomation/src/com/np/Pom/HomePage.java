package com.np.Pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(id="userInfoButton")
	public WebElement userInfoButton;
	@FindBy(xpath="//div[@class='hbt'][contains(.,'Customers')]")
	public WebElement customerMenu;
	
	@FindBy(xpath="//span[contains(.,'Sales Reps')]")
	public WebElement salesResMenuItem;
	
	@FindBy(xpath="//span/img[@alt='Please wait']")
	public WebElement pleaseWaitMessage;
	
	

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	

}
