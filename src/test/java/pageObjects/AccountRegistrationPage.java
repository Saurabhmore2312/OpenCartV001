package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "input-firstname")
	WebElement txtFirstName;
	
	@FindBy(id = "input-lastname")
	WebElement txtLastName;
	
	@FindBy(id = "input-email")
	WebElement txtEmail;
	
	@FindBy(id = "input-telephone")
	WebElement txtTelephone;
	
	@FindBy(id = "input-password")
	WebElement txtPassword;
	
	@FindBy(id = "input-confirm")
	WebElement txtConfirmPassword;
	
	@FindBy(name = "agree")
	WebElement chkPolicy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//div[@id='content']/p[1]")
	WebElement msgConfirmation;
	
	public void enterFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}
	
	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void enterTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
	
	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void enterConfirmPassword(String password) {
		txtConfirmPassword.sendKeys(password);
	}
	
	public void checkPrivacyPolicy() {
		chkPolicy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
	
	public String getConfirmationMessage() {
		try {
		return msgConfirmation.getText();
	} catch (Exception e) {
		return (e.getMessage());
        }
	}
	
}
