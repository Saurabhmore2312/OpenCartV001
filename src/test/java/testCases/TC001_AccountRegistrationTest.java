package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	@Test (groups = {"Regression", "Master"})
	public void verfiy_account_resgistration() {
		try{
		logger.info("*********Starting TC001_AccountRegistrationTest**********");
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.clickOnRegister();
		logger.info("*********Clicked on Register link**********");
		
		AccountRegistrationPage accountRegister = new AccountRegistrationPage(driver);
		logger.info("*********Providing customer details**********");
		accountRegister.enterFirstName(randomString().toUpperCase());
		accountRegister.enterLastName(randomString().toUpperCase());
		accountRegister.enterEmail(randomString() + "@gmail.com");
		accountRegister.enterTelephone(randomNumbers());
		String password = randomAlphanumeric();
		accountRegister.enterPassword(password);
		accountRegister.enterConfirmPassword(password);
		accountRegister.checkPrivacyPolicy();
		accountRegister.clickContinue();
		logger.info("*********Clicked on Continue button**********");
		logger.info("*********Verifying account registration**********");
		String confirmmsg = accountRegister.getConfirmationMessage();
		if (confirmmsg.equals("Congratulations! Your new account has been successfully created!")) {
			Assert.assertTrue(true);
			logger.info("*********Account registration successful**********");
		} else {
			logger.error("*********Test failed**********");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
			
		}
		homePage.clickOnMyAccount();
		driver.findElement(By.xpath("//a[@title='My Account']/..//a[text()='Logout']")).click();
		}
		catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("*********Finished TC001_AccountRegistrationTest**********");
	}
	
	
	
}
