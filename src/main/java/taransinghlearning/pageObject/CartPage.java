package taransinghlearning.pageObject;

import java.util.List;
import taransinghlearning.AbstractComponents.AbstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import taransinghlearning.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy(xpath="//*[text()='Checkout']")
	WebElement checkout;
	
	
	
	
	public boolean verifyProductDisplay(String productName) {
		boolean match = productTitles.stream().anyMatch(addedProduct -> addedProduct.getText().equals(productName));
		return match ;
	}
	
	public CheckOutPage goToCheckout() {
		checkout.click();
		return new CheckOutPage(driver);
	}

	
	

}
