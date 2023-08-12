package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{

	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") //my account page heading
	WebElement msgHeading;
	
	@FindBy(css="a:nth-child(13)")
	WebElement lnkLogout;
	
	public boolean isMyAccountPageExist() //my account page heading
	{
		try 
		{
			return(msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return(false);
		}
	}
	
	
	public void clickLogout() 
	{
		lnkLogout.click();
	}
}
