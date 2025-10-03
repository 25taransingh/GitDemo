package taransinghlearning.Stepdefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import taransinghlearning.TestComponents.BaseTest;
import taransinghlearning.pageObject.*;

public class StepDefinationImp extends BaseTest {

    LandingPage landingPage;
    ProductCatalogue productCatalogue;
    CartPage cartPage;
    CheckOutPage checkOutPage;
    ConfirmationPage confirmationPage;

    // ---------------------- BACKGROUND ----------------------
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws Exception {
        // Opens the browser and navigates to the application
        landingPage = launchApplication();
    }

    // ---------------------- LOGIN ----------------------
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password) {
        // Login using provided credentials from Examples table
        productCatalogue = landingPage.loginApplication(username, password);
    }

    // ---------------------- ADD PRODUCT ----------------------
    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_cart(String productName) {
        // Add the specified product to cart
        productCatalogue.addProductToCart(productName);
    }

    // ---------------------- CHECKOUT ----------------------
    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName) {
        // Verify product in cart and go to checkout
        cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match, "❌ Product not found in Cart!");

        checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectCountry("India"); // Hardcoded, can be parameterized
        confirmationPage = checkOutPage.submitOrder();
    }

    // ---------------------- CONFIRMATION ----------------------
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_confirmation_page(String expectedMsg) {
        // Verify order confirmation message
        String confirmationMsg = confirmationPage.getConfirmationMsg();
        Assert.assertEquals(confirmationMsg, expectedMsg, "❌ Confirmation message mismatch!");
        driver.quit();
    }

    // ---------------- ERROR VALIDATION STEPS ----------------
    @Then("{string} message is displayed.")
    public void message_is_displayed(String expectedMessage) {
        String actualMessage = landingPage.getErrorMessege();
        Assert.assertEquals(actualMessage.trim(), expectedMessage);
        driver.quit();
    }
}
