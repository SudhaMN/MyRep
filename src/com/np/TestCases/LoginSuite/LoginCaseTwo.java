package com.np.TestCases.LoginSuite;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.np.utility.Read_XLS;
import com.np.utility.SuiteUtility;

//LoginCaseTwo Class Inherits From SuiteOneBase Class.
//So, LoginCaseTwo Class Is Child Class Of SuiteOneBase Class And SuiteBase Class.
public class LoginCaseTwo extends LoginBase{
	Read_XLS FilePath = null;	
	String SheetName = null;
	String TestCaseName = null;	
	String ToRunColumnNameTestCase = null;
	String ToRunColumnNameTestData = null;
	String TestDataToRun[]=null;
	static boolean TestCasePass=true;
	static int DataSet=-1;	
	static boolean Testskip=false;
	static boolean Testfail=false;
	SoftAssert s_assert =null;

	@BeforeTest
	public void checkCaseToRun() throws IOException{
		//Called init() function from SuiteBase class to Initialize .xls Files
		init();	
		//To set SuiteOne.xls file's path In FilePath Variable.
		FilePath = TestCaseListExcelLogin;		
		TestCaseName = this.getClass().getSimpleName();	
		//SheetName to check CaseToRun flag against test case.
		SheetName = "TestCasesList";
		//Name of column In TestCasesList Excel sheet.
		ToRunColumnNameTestCase = "CaseToRun";
		//Name of column In Test Case Data sheets.
		ToRunColumnNameTestData = "DataToRun";

		//To check test case's CaseToRun = Y or N In related excel sheet.
		//If CaseToRun = N or blank, Test case will skip execution. Else It will be executed.
		if(!SuiteUtility.checkToRunUtility(FilePath, SheetName,ToRunColumnNameTestCase,TestCaseName)){			
			//To report result as skip for test cases In TestCasesList sheet.
			SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", TestCaseName, "SKIP");
			//To throw skip exception for this test case.
			throw new SkipException(TestCaseName+"'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of "+TestCaseName);
		}
		//To retrieve DataToRun flags of all data set lines from related test data sheet.
		TestDataToRun = SuiteUtility.checkToRunUtilityOfData(FilePath, TestCaseName, ToRunColumnNameTestData);
	}

	//Accepts 4 column's String data In every Iteration.
	@Test(dataProvider="LoginCaseTwoData")
	public void LoginCaseTwoTest(String DataCol1,String DataCol2,String ExpectedResult) throws Exception{
			DataSet++;

			//Created object of testng SoftAssert class.
			s_assert = new SoftAssert();		

			//If found DataToRun = "N" for data set then execution will be skipped for that data set.
			if(!TestDataToRun[DataSet].equalsIgnoreCase("Y")){
				//If DataToRun = "N", Set Testskip=true.
				Testskip=true;
				throw new SkipException("DataToRun for row number "+DataSet+" Is No Or Blank. So Skipping Its Execution.");
			}


			//To Initialize Firefox browser.
			loadWebBrowser();


			//To navigate to URL. It will read site URL from Param.properties file
			driver.get(Param.getProperty("siteURL"));	

			//login functionality
			loginPage.login(DataCol1, DataCol2);

			//capturing the error message from login screen
			String ActualResult =  loginPage.loginErrorMessage.getText();

			if(!(ActualResult.equalsIgnoreCase(ExpectedResult))){
				Testfail=true;	

				s_assert.assertEquals(ActualResult, ExpectedResult, "ActualResult Value "+ActualResult+" And ExpectedResult Value "+ExpectedResult+" Not Match");
			}

		if(Testfail){
			s_assert.assertAll();		
		}
	}	

	//@AfterMethod method will be executed after execution of @Test method every time.
	@AfterMethod
	public void reporterDataResults(){		
		if(Testskip){
			//If found Testskip = true, Result will be reported as SKIP against data set line In excel sheet.
			SuiteUtility.WriteResultUtility(FilePath, TestCaseName, "Pass/Fail/Skip", DataSet+1, "SKIP");
		}
		else if(Testfail){
			//To make object reference null after reporting In report.
			s_assert = null;
			//Set TestCasePass = false to report test case as fail In excel sheet.
			TestCasePass=false;	
			//If found Testfail = true, Result will be reported as FAIL against data set line In excel sheet.
			SuiteUtility.WriteResultUtility(FilePath, TestCaseName, "Pass/Fail/Skip", DataSet+1, "FAIL");			
		}
		else{
			//If found Testskip = false and Testfail = false, Result will be reported as PASS against data set line In excel sheet.
			SuiteUtility.WriteResultUtility(FilePath, TestCaseName, "Pass/Fail/Skip", DataSet+1, "PASS");
		}
		//At last make both flags as false for next data set.
		Testskip=false;
		Testfail=false;
		closeWebBrowser();
	}

	//This data provider method will return 3 column's data one by one In every Iteration.
	@DataProvider
	public Object[][] LoginCaseTwoData(){
		//To retrieve data from Data 1 Column,Data 2 Column, Expected Result column of LoginCaseTwo data Sheet.
		//Last two columns (DataToRun and Pass/Fail/Skip) are Ignored programatically when reading test data.
		return SuiteUtility.GetTestDataUtility(FilePath, TestCaseName);
	}

	//To report result as pass or fail for test cases In TestCasesList sheet.
	@AfterTest
	public void closeBrowser(){
		//To Close the web browser at the end of test.
		closeWebBrowser();
		if(TestCasePass){
			SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", TestCaseName, "PASS");
		}
		else{
			SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", TestCaseName, "FAIL");

		}
	}
}