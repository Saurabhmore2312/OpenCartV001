package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	
	@Test (groups = {"Sanity", "Master"})
	public void verify_login() {
		logger.info("*********Starting TC002_LoginTest**********");
		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickOnMyAccount();
			homePage.clickOnLogin();
			logger.info("*********Clicked on Login link**********");
			LoginPage loginPage = new LoginPage(driver);
			MyAccountPage myAccount = new MyAccountPage(driver);
			
			loginPage.enterEmail(p.getProperty("email"));
			loginPage.enterPassword(p.getProperty("password"));
			loginPage.clickOnLogin();
			
			boolean targetPage = myAccount.isMyAccountPageExists();
			
			Assert.assertTrue(targetPage);
			homePage.clickOnMyAccount();
			driver.findElement(By.xpath("//a[@title='My Account']/..//a[text()='Logout']")).click();
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
}
