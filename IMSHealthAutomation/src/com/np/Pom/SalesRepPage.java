package com.np.Pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesRepPage{

	@FindBy(xpath="//input[@value='New Sales Rep']")
	public WebElement newSalesRepButton;
	@FindBy(id="ExternalEmployeeId")
	public WebElement externalEmployeeIdTextBox;
	@FindBy(id="FirstName")
	public WebElement firstNameTextBox;
	@FindBy(id="LastName")
	public WebElement lastNameTextBox;
	@FindBy(id="Position")
	public WebElement positionTextBox;
	@FindBy(id="City")
	public WebElement cityTextBox;
	@FindBy(id="State")
	public WebElement stateTextBox;
	@FindBy(id="Phone")
	public WebElement phoneTextBox;
	@FindBy(id="PhoneExtension")
	public WebElement phoneExtensionTextBox;
	@FindBy(id="Fax")
	public WebElement FaxTextBox;
	@FindBy(id="Email")
	public WebElement emailTextBox;
	@FindBy(id="Active")
	public WebElement activeRadioButton;
	@FindBy(xpath="//input[@name='reps.save']")
	public WebElement saveNewSalesRep;
	@FindBy(xpath="//input[@name='reps.cancel']")
	public WebElement cancelNewSalesRep;
	@FindBy(xpath="//a[text()='Edit']")
	public WebElement editSalesRepLink;

	@FindBy(xpath="//td[@fieldid='ExternalEmployeeId']/span")
	public WebElement externalEmployeeIdValue;
	@FindBy(xpath="//td[@fieldid='FirstName']/span")
	public WebElement firstNameValue;
	@FindBy(xpath="//td[@fieldid='LastName']/span")
	public WebElement lastNameValue;
	@FindBy(xpath="//td[@fieldid='Position']/span")
	public WebElement positionValue;
	@FindBy(xpath="//td[@fieldid='City']/span")
	public WebElement cityValue;
	@FindBy(xpath="//td[@fieldid='State']/span")
	public WebElement stateValue;
	@FindBy(xpath="//td[@fieldid='Phone']/span")
	public WebElement phoneValue;
	@FindBy(xpath="//td[@fieldid='PhoneExtension']/span")
	public WebElement phoneExtensionValue;
	@FindBy(xpath="//td[@fieldid='Fax']/span")
	public WebElement faxValue;
	@FindBy(xpath="//td[@fieldid='Email']/span")
	public WebElement emailValue;
	@FindBy(xpath="//td[@fieldid='Active']/span")
	public WebElement activeTitle;

	public SalesRepPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	/**
	 * Function to Enter the values in newSalesRepData popup
	 * @param externalEmployeeId
	 * @param firstName
	 * @param lastName
	 * @param position
	 * @param city
	 * @param state
	 * @param phone
	 * @param phoneExtension
	 * @param fax
	 * @param email
	 * @param active
	 */
	public void enterNewSalesRepData(String externalEmployeeId,String firstName,String lastName
			,String position,String city,String state,String phone ,String phoneExtension,String fax, String email, String active){

		if(!externalEmployeeId.equals(""))
			externalEmployeeIdTextBox.sendKeys(externalEmployeeId);

		if(!firstName.equals(""))
			firstNameTextBox.sendKeys(firstName);

		if(!lastName.equals(""))
			lastNameTextBox.sendKeys(lastName);

		if(!position.equals(""))
			positionTextBox.sendKeys(position);

		if(!city.equals(""))
			cityTextBox.sendKeys(city);

		if(!state.equals(""))
			stateTextBox.sendKeys(state);

		if(!phone.equals(""))
			phoneTextBox.sendKeys(phone);

		if(!phoneExtension.equals(""))
			phoneExtensionTextBox.sendKeys(phoneExtension);

		if(!fax.equals(""))
			FaxTextBox.sendKeys(fax);

		if(!email.equals(""))
			emailTextBox.sendKeys(email);

		if(active.equalsIgnoreCase("Yes")){
			if(!activeRadioButton.isSelected())
				activeRadioButton.click();
		}else if(active.equalsIgnoreCase("No")){
			if(activeRadioButton.isSelected())
				activeRadioButton.click();
		}
	}

	/**
	 * 
	 * @param externalEmployeeId
	 * @param firstName
	 * @param lastName
	 * @param position
	 * @param city
	 * @param state
	 * @param phone
	 * @param phoneExtension
	 * @param fax
	 * @param email
	 * @param active
	 * @return
	 */
	public boolean verifySalesRepDetails(String externalEmployeeId,String firstName,String lastName
			,String position,String city,String state,String phone ,String phoneExtension,String fax, String email, String active){

		boolean status=true;

		if(!externalEmployeeId.equals("")){
			if(!externalEmployeeIdValue.getText().equalsIgnoreCase(externalEmployeeId))
				status=false;
		}
		if(!firstName.equals("")){
			if(!firstNameValue.getText().equalsIgnoreCase(firstName))
				status=false;
		}
		if(!lastName.equals("")){
			if(!lastNameValue.getText().equalsIgnoreCase(lastName))
				status=false;
		}
		if(!position.equals("")){
			if(!positionValue.getText().equalsIgnoreCase(position))
				status=false;
		}
		if(!city.equals("")){
			if(!cityValue.getText().equalsIgnoreCase(city))
				status=false;
		}
		if(!state.equals("")){
			if(!stateValue.getText().equalsIgnoreCase(state))
				status=false;
		}
		if(!phone.equals("")){
			if(!phoneValue.getText().equalsIgnoreCase(phone))
				status=false;
		}
		if(!phoneExtension.equals("")){
			if(!phoneExtensionValue.getText().equalsIgnoreCase(phoneExtension))
				status=false;
		}
		if(!fax.equals("")){
			if(!faxValue.getText().equalsIgnoreCase(fax))
				status=false;
		}
		if(!email.equals("")){
			if(!emailValue.getText().equalsIgnoreCase(email))
				status=false;
		}
		if(active.equalsIgnoreCase("Yes")){
			if(!activeTitle.getAttribute("title").equalsIgnoreCase(active))
				status=false;
		}
		return status;
	}
}
