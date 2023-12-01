package BASIC;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import COMMON_UTILS1.ExcelUtils;
import COMMON_UTILS1.FileUtils;
import COMMON_UTILS1.WebDriverUtils;

public class Contact 
{
	public static void main(String[] args) throws IOException 
	
	{      
		final WebDriver driver;
		
		//create a object of fileutils 
		
		FileUtils futils = new FileUtils();
		
      //pass the keys of properties file BY CALLING A FILE UTILS CLASS METHODs 
	String BROWSER = 	futils.getDataFromPropertyFile1("Bro_wser");
	String URL     =	futils.getDataFromPropertyFile1("u_rl");
	String USERNAME =	futils.getDataFromPropertyFile1("userName");
	String PASSWORD =	futils.getDataFromPropertyFile1("passWord");

	
	if(BROWSER.equals("Chrome"))
	{
		
		driver = new ChromeDriver();
	}
	else if(BROWSER.equals("Edge"))
	{
		driver= new EdgeDriver();
	}
	else {
	  driver= new FirefoxDriver();
	}
	
	//FOR WEBDRIVER CLASS BCOZ ALL THOSE METODS BELONGS TO WEBDRIVER CLASS.
	//CREATE A OBJECT OF WEBDRIVERutils CLASS
	WebDriverUtils wutils = new WebDriverUtils();
	
	//with help of webdriverutils class object we call the methods of webdriver
	wutils.maximize(driver);
	wutils.ImplicitWait(driver);
	
	  
	  driver.get(URL);
	  driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	  driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	  driver.findElement(By.id("submitButton")).click();
	  driver.findElement(By.xpath("//a[text()='Contacts']")).click();
	  driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	  
	  
	  //create a object of excelutils becoz we get data now from excel sheet for dropdown
	  ExcelUtils eutils = new ExcelUtils();
	  
	  //call the method from excelutils files class with there objet  //selectbyvisibletext
	  
	 String unamesuffix = eutils.getDataFromExcelFile2("Sheet1", 1, 0);
	 driver.findElement(By.name("salutationtype")).sendKeys(unamesuffix);
	  
     String FirstName = eutils.getDataFromExcelFile2("Sheet1", 1, 1);
     driver.findElement(By.name("firstname")).sendKeys(FirstName);
     
     String LastnameFIELD = eutils.getDataFromExcelFile2("Sheet1", 1, 2);	
     driver.findElement(By.name("lastname")).sendKeys(LastnameFIELD);
     
     //another dropdown method overloading in webdriver class  select by index
     
     String secndnddrop = eutils.getDataFromExcelFile2("Sheet1", 1, 3);	
      WebElement dropdown =  driver.findElement(By.name("leadsource"));
      wutils.HandleDropdown(dropdown, secndnddrop);
     
     //we can call multiple elements with help of 4 class methods DDT.
     
      driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
    //infutre any thing is change so u can modify the code easily. 	
      
      //dropdown assign to 
      String asignto = eutils.getDataFromExcelFile2("Sheet1", 1, 4);	

      WebElement drop2=  driver.findElement(By.name("assigned_group_id"));
      wutils.handledropdown(drop2, asignto);
    
       driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
    
      //mouse hovering on signout element
      WebElement mhover= driver.findElement(By.xpath("(//td[@valign='bottom'])[2]"));
   
   wutils.Action(driver, mhover);
   
   //click on signup
   driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
   
   
    
     
	}

}

