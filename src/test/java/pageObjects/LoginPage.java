package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "input-email")
	WebElement txtEmail;
	
	@FindBy(id = "input-password")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//div[@id='content']/h2[1]")
	WebElement msgLogin;
	
	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickOnLogin() {
		btnLogin.click();
	}
	
	public String getLoginMessage() {
		try {
		return msgLogin.getText();
		}
		catch (Exception e) {
			return (e.getMessage());
		}
	}
	
}
