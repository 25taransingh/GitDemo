package taransinghlearning.pageObject;

import java.util.List;
import taransinghlearning.AbstractComponents.AbstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import taransinghlearning.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderProductName;
	
	@FindBy(xpath="//*[text()='Checkout']")
	WebElement checkout;
	
	
	
	
	public boolean verifyOrderDisplay(String productName) {
		boolean match = orderProductName.stream().anyMatch(addedProduct -> addedProduct.getText().equals(productName));
		return match ;
	}
	


	
	

}
