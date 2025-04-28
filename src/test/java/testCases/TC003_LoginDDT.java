package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = "Datadriven")
	public void verify_LoginDDT(String email, String password, String exp) { 
		logger.info("*********Starting TC003_LoginDDT**********");
		try {
		//HomePage
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.clickOnLogin();
		
		//LoginPage
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLogin();
		
		//MyAccountPage
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		boolean targetPage = myAccountPage.isMyAccountPageExists();
		
		if (exp.equalsIgnoreCase("Valid")) {
			if (targetPage == true) {
				homePage.clickOnMyAccount();
				myAccountPage.clickLogout();
				logger.info("Login test passed");
				Assert.assertTrue(true);
				driver.navigate().refresh();
			} else {
				logger.error("Login test failed");
				 Assert.assertTrue(false);
			}
		} else if (exp.equalsIgnoreCase("Invalid")) {
			if (targetPage == false) {
				logger.info("Login test passed");
				Assert.assertTrue(true);
			} else {
				homePage.clickOnMyAccount();
				myAccountPage.clickLogout();
				logger.error("Login test failed");
				Assert.assertTrue(false);
				driver.navigate().refresh();
			}
			
		}
		}
		catch (Exception e) {
			logger.error("Login test failed");
			Assert.assertTrue(false);
		}
		logger.info("*********Finished TC003_LoginDDT**********");
	}
	
}
