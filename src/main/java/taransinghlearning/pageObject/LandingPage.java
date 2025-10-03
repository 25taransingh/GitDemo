package taransinghlearning.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import taransinghlearning.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userName;

	@FindBy(id = "userPassword")
	WebElement passwordele;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement errorMsg;

	public ProductCatalogue loginApplication(String email, String password) {
		userName.sendKeys(email);
		passwordele.sendKeys(password);
		waitForWebElementToAppear(submit);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");

	}

	public String getErrorMessege() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();

	}

}
