package taransinghlearning.Tests;

import java.io.IOException;
import taransinghlearning.TestComponents.Retry;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import taransinghlearning.TestComponents.BaseTest;
import taransinghlearning.pageObject.CartPage;
import taransinghlearning.pageObject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" },retryAnalyzer=Retry.class )
	public void loginErrorValidation() throws IOException {
		// TODO Auto-generated method stub

		landingPage.loginApplication("writetotaransingh@gmail.com", "Taran@@123");
		String errorMessage = landingPage.getErrorMessege();
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
		System.out.println("SRK");

	}

	@Test(retryAnalyzer=Retry.class, groups = { "ErrorHandling" } )
	public void productErrorValidation() throws IOException {
		// TODO Auto-generated method stub

		String Product = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("writetotaransingh@gmail.com", "Taran@123");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(Product);
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(Product);
		Assert.assertTrue(match);
		System.out.println(match);
		System.out.println("John");
		System.out.println("benny");
		System.out.println("bittu");
	}

}
