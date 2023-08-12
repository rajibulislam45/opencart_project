package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.*;


public class BaseClass {

	public static WebDriver driver;
	
	public Logger logger; //for logging
	
	
	public ResourceBundle rb;
	
	
	

	@BeforeClass(groups= {"Master","Sanity","Regression"})
	@Parameters("browser")
	
	public void setup(String br) 
	{
		rb=ResourceBundle.getBundle("config");//load config.properties file
		
		logger=LogManager.getLogger(this.getClass());
		
		
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
		
		if(br.equals("chrome"))
		{
			
		driver=new ChromeDriver(options);
		
		}
		else if(br.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("http://localhost/opencart/upload/index.php");
		//driver.get("https://demo.opencart.com/index.php");
		driver.get(rb.getString("appURL"));
		driver.manage().window().maximize();
		
		
	}
	
	@AfterClass(groups= {"Master","Sanity","Regression"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}

	public String randomeNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return (generatedString2);
	}
	
	public String randomAlphaNumeric() {
		String st = RandomStringUtils.randomAlphabetic(4);
		String num = RandomStringUtils.randomNumeric(3);
		
		return (st+"@"+num);
	}
	
	
	public String captureScreen(String tname) throws IOException 
	{
		/*
		SimpleDateFormat df= new SimpleDateFormat("yyyyMMddhhmmss");
		
		Date dt= new Date();    // combined these 3 lines into 1
		String timestamp= df.format(dt);*/
		
		String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takescreenshot= (TakesScreenshot)driver;
		
		File source=takescreenshot.getScreenshotAs(OutputType.FILE);
		String destination=System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" +timestamp + ".png";
		
		try
		{
			FileUtils.copyFile(source, new File(destination));
		}
		catch(Exception e)
		{
			e.getMessage();
	
		}
		return destination;

	}
	
	
}
