package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.*;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass
{

	@Test(groups= {"Sanity","Master"})
	public void test_login() 
	{
		try
		{
			
		logger.info("*** Starting TC_002_LoginTest ***");
		
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on my Account");
		
		hp.clickLogin();
		logger.info("Clicked on login link");
		
		LoginPage lp=new LoginPage(driver);
		logger.info("providing login details");
		
		lp.setEmail(rb.getString("email"));//valid email ,getting from config.properties file
		lp.setPassword(rb.getString("password"));//valid password ,getting from config.properties file
		
		lp.ClickLogin();
		logger.info("Clicked on Login button");
		
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExist();
		Assert.assertEquals(targetpage, true,"Invalid login data");
	    }
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*** Finished TC_002_LoginTest ***");
	}
	
	
}
