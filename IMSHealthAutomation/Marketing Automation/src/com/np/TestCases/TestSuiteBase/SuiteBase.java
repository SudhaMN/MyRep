package com.np.TestCases.TestSuiteBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

import com.np.Pom.HomePage;
import com.np.Pom.LoginPage;
import com.np.Pom.SalesRepPage;
import com.np.utility.Read_XLS;

/**
 * This class is the base class for all the suites which will be used to verify the running status of the suite
 * include all reusable methods used in the function.
 * @author SPoojary
 *
 */
public class SuiteBase {
	//ATU
	{
		System.setProperty("atu.reporter.config", System.getProperty("user.dir")+"//src//com//np//property//atu.properties");
	}
	public static Read_XLS TestSuiteListExcel=null;
	public static Read_XLS TestCaseListExcelLogin=null;
	public static Read_XLS TestCaseListExcelSalesRep=null;
	public static Logger Add_Log = null;
	public boolean BrowseralreadyLoaded=false;
	public static Properties Param = null;
	public static Properties Object = null;
	public static WebDriver driver=null;
	public static WebDriver ExistingchromeBrowser;
	public static WebDriver ExistingmozillaBrowser;
	public static WebDriver ExistingIEBrowser;
	public static LoginPage loginPage=null;
	public static HomePage homePage=null;
	public static SalesRepPage salesRepPage=null;

	public void init() throws IOException{
		//To Initialize logger service.
		Add_Log = Logger.getLogger("rootLogger");				

		//Initializing Test Suite List(TestSuiteList.xls) File Path Using Constructor Of Read_XLS Utility Class.
		TestSuiteListExcel = new Read_XLS(System.getProperty("user.dir")+"\\src\\com\\np\\ExcelFiles\\TestSuiteList.xls");
		//Initializing Test Suite One(Login.xls) File Path Using Constructor Of Read_XLS Utility Class.
		TestCaseListExcelLogin = new Read_XLS(System.getProperty("user.dir")+"\\src\\com\\np\\ExcelFiles\\Login.xls");
		//Initializing Test Suite Two(SalesRep.xls) File Path Using Constructor Of Read_XLS Utility Class.
		TestCaseListExcelSalesRep = new Read_XLS(System.getProperty("user.dir")+"\\src\\com\\np\\ExcelFiles\\SalesRep.xls");
		//Bellow given syntax will Insert log In applog.log file.
		Add_Log.info("All Excel Files Initialised successfully.");

		//Initialize Param.properties file.
		Param = new Properties();
		FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"//src//com//np//property//Param.properties");
		Param.load(fip);
		Add_Log.info("Param.properties file loaded successfully.");		

		//Initialize Objects.properties file.
		Object = new Properties();
		fip = new FileInputStream(System.getProperty("user.dir")+"//src//com//np//property//Objects.properties");
		Object.load(fip);
		Add_Log.info("Objects.properties file loaded successfully.");
	}

	public void loadWebBrowser(){
		//Check If any previous webdriver browser Instance Is exist then run new test In that existing webdriver browser Instance.
		if(Param.getProperty("testBrowser").equalsIgnoreCase("Mozilla") && ExistingmozillaBrowser!=null){
			driver = ExistingmozillaBrowser;
			return;
		}else if(Param.getProperty("testBrowser").equalsIgnoreCase("chrome") && ExistingchromeBrowser!=null){
			driver = ExistingchromeBrowser;
			return;
		}else if(Param.getProperty("testBrowser").equalsIgnoreCase("IE") && ExistingIEBrowser!=null){
			driver = ExistingIEBrowser;
			return;
		}		


		if(Param.getProperty("testBrowser").equalsIgnoreCase("Mozilla")){
			//To Load Firefox driver Instance. 
			driver = new FirefoxDriver();
			ExistingmozillaBrowser=driver;
			Add_Log.info("Firefox Driver Instance loaded successfully.");

		}else if(Param.getProperty("testBrowser").equalsIgnoreCase("Chrome")){
			//To Load Chrome driver Instance.
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//BrowserDrivers//chromedriver.exe");
			driver = new ChromeDriver();
			ExistingchromeBrowser=driver;
			Add_Log.info("Chrome Driver Instance loaded successfully.");

		}else if(Param.getProperty("testBrowser").equalsIgnoreCase("IE")){
			//To Load IE driver Instance.
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//BrowserDrivers//IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			ExistingIEBrowser=driver;
			Add_Log.info("IE Driver Instance loaded successfully.");

		}	

		//ATU
		ATUReports.setWebDriver(driver);
		ATUReports.indexPageDescription = "Nexxus Performance";
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		loginPage=new LoginPage(driver,Param.getProperty("userName"),Param.getProperty("password"));
		homePage=new HomePage(driver);
		salesRepPage=new SalesRepPage(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();			
	}

	/**
	 * Function to close the browser
	 */
	public void closeWebBrowser(){
		driver.close();
		//null browser Instance when close.
		ExistingchromeBrowser=null;
		ExistingmozillaBrowser=null;
		ExistingIEBrowser=null;
	}


	/**
	 * This method check for the presence of element in the page
	 * @param element
	 * @return true/false 
	 */
	public boolean elementPresent(WebElement element){
		try{
			if(driver!=null){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}

	/**
	 * Function to move the cursor on the element
	 * @param webElement
	 */
	public void moveCursorToElement(WebElement webElement){
		Actions builder = new Actions(driver); 
		builder.moveToElement(webElement).perform(); 
	}
	
	/**
	 * Function to wait for the element to complete the action
	 * @param element
	 * @param timeInSeconds
	 */
	public void waitForElementAction(WebElement element,int timeInSeconds){
		try {
			for (int i = 0; i < timeInSeconds; i++) {
				element.isDisplayed();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	

}
