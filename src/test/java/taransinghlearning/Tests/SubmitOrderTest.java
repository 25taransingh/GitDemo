package taransinghlearning.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import taransinghlearning.TestComponents.BaseTest;
import taransinghlearning.pageObject.CartPage;
import taransinghlearning.pageObject.CheckOutPage;
import taransinghlearning.pageObject.ConfirmationPage;
import taransinghlearning.pageObject.LandingPage;
import taransinghlearning.pageObject.OrderPage;
import taransinghlearning.pageObject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String Product = "ZARA COAT 3";
// Test integrated with HashMap
// to remove HashMap and use less number just remove the "HashMap<String, String> input" in method argument and inside method input.get values

	@Test(dataProvider = "getData", groups = "PurchaseOrder")
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub

		String CountryName = "India";
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		System.out.println(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry(CountryName);
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmationMsg = confirmationPage.getConfirmationMsg();
		Assert.assertEquals(confirmationMsg, "THANKYOU FOR THE ORDER.");
		System.out.println(confirmationMsg);
		System.out.println("Modified in GitDemoY3");
		System.out.println("Modified in GitDemoY4");
		System.out.println("Modified in GitDemoY5");
		System.out.println("Modified in GitDemoY6");
		System.out.println("Modified in GitDemoY7");
//		Thread.sleep(2000);		
		driver.quit();

	}

// Only dependsonMethod 	

	@Test(dependsOnMethods = { "submitOrder" })
	public void verifyOrder() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("writetotaransingh@gmail.com", "Taran@123");
		OrderPage orderpage = productCatalogue.goToOrderPage();
		boolean match = orderpage.verifyOrderDisplay(Product);
		Assert.assertTrue(match);

	}
//	---------------------------------------------------------------
// When dependsonMethod need to integrate with data providers and groups	
//	@Test(dataProvider = "getData", dependsOnMethods = { "submitOrder" }, groups = "PurchaseOrder")
//	public void verifyOrder(String email, String password, String product) {
//		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
//		OrderPage orderpage = productCatalogue.goToOrderPage();
//		boolean match = orderpage.verifyOrderDisplay(product);
//		Assert.assertTrue(match);
//	}
//	---------------------------------------------------------------


	
	
	
	
//	So by using above utility system get store the ss in local system. 

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\taransinghlearning\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
	
	
	
}
//----------------------------------------------------------------------------------------

// Data provider when less number of variable is required and easy to show as an argument in method.	
//	@DataProvider
//	public Object[][] getData() {
//
//		return new Object[][] { { "writetotaransingh@gmail.com", "Taran@123", "ZARA COAT 3" },
//				{ "manu@yopmail.com", "manu@1234", "ADIDAS ORIGINAL" } };
//
//	}
// ------------------------------------------------------------------------------
//When variable became more in number like 15,16 not easy to show it is not
//efficient, so we use HashMap
//same we need to mention on test method as well

//	@Test(dataProvider = "getData", groups = "PurchaseOrder")
//	public void submitOrder(HashMap<String, String> input) throws IOException 




//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "writetotaransingh@gmail.com");
//	map.put("password", "Taran@123");
//	map.put("product", "ZARA COAT 3");
//
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "manu@yopmail.com");
//	map1.put("password", "manu@1234");
//	map1.put("product", "ADIDAS ORIGINAL");

