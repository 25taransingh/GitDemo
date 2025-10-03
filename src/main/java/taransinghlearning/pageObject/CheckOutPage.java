package taransinghlearning.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import taransinghlearning.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="input[placeholder='Select Country']")
	WebElement selectCOuntry;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement country;
	
	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement placeOrder;
	
	public void selectCountry(String CountryName) {
		Actions a = new Actions(driver);
		
		a.sendKeys(selectCOuntry, CountryName).build().perform();
		country.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", placeOrder);
		js.executeScript("arguments[0].click();", placeOrder);
		return new ConfirmationPage(driver);
	}
	
	
	
	
	

}
