package taransinghlearning.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import taransinghlearning.pageObject.CartPage;
import taransinghlearning.pageObject.OrderPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	  // Spinner overlay (ngx-spinner)
    @FindBy(css = ".ngx-spinner-overlay")
    WebElement spinnerOverlay;
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(css = "button[routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	
	

	public void waitForByElementToAppear(By FindBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	public void waitForByElementToDisappear(By FindBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(FindBy));
	}
	
	public void waitForWebElementToAppear(WebElement FindBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	public void waitForWebElementToDisappear(WebElement FindBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(FindBy));
	}
	public void waitForWebElementToClickable(WebElement FindBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(FindBy));
	}
	
	
	
	public CartPage goToCartPage() {
		
		waitForWebElementToAppear(cartHeader);
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		
	}
	public OrderPage goToOrderPage() {
		
		waitForWebElementToAppear(orderHeader);
		waitForWebElementToClickable(orderHeader);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderHeader);
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}
	
	
}
