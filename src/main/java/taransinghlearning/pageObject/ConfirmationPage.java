package taransinghlearning.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import taransinghlearning.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {


	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//tr/td/h1")
	WebElement confirmationMsg;
	
	public String getConfirmationMsg() {
		return confirmationMsg.getText();
	}
	

	
}
