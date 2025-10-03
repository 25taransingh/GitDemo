package taransinghlearning.pageObject;

import java.util.List;
import taransinghlearning.AbstractComponents.AbstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import taransinghlearning.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(className = "mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	// By locator
	By productsBy = By.className("mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.id("toast-container");
	By goToCart = By.cssSelector("button[routerlink='/dashboard/cart']");
	By spinnerOverlay = By.cssSelector(".ngx-spinner-overlay");

	public List<WebElement> getProductList() {
		waitForByElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {

		WebElement prod = getProductByName(productName);
		
		// Wait until overlay gone first
		waitForByElementToDisappear(spinnerOverlay);
		
		 // Scroll element into view (fixes headless viewport issues)
	    ((org.openqa.selenium.JavascriptExecutor) driver)
	            .executeScript("arguments[0].scrollIntoView(true);", prod.findElement(addToCart));
	    
	    // Try clicking when it's actually clickable
	    waitForWebElementToAppear(prod.findElement(addToCart));
	    
		prod.findElement(addToCart).click();
		
		waitForByElementToAppear(toastMsg);
		waitForByElementToDisappear(spinnerOverlay);
		waitForWebElementToDisappear(spinner);
		waitForByElementToAppear(goToCart);

	}
	

}
