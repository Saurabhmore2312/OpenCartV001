
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement linkMyAccount;
	
	@FindBy(xpath = "//a[text()='Login']")
	WebElement linkLogin;
	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement linkRegister;
	
	public void clickOnMyAccount() {
		linkMyAccount.click();
	}
	
	public void clickOnRegister() {
		linkRegister.click();
	}
	
	public void clickOnLogin() {
		linkLogin.click();
	}
	
	
}
