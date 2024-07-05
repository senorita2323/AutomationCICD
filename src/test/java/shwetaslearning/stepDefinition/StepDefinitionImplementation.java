package shwetaslearning.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import shwetaslearning.PageObjects.*;
import shwetaslearning.TestComponents.BaseTest;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImplementation extends BaseTest {
    public LandingPage landingPage;
    public ConfirmationPage confirmationPage;
    public ProductCatalog productCatalog;
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }
    @Given("Logged in with username{} and password{}")
    public void Logged_in_with_username_and_password(String userName,String password){
        productCatalog = landingPage.loginApplication(userName,password);
    }
    @When("I add the product{} to cart")// it is parameterized value so we have to give it as regular expression i.e regex(^ and $)
    public void I_add_the_product_to_cart(String productName){
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
    }

    @When("Checkout {} and submit the order")
    public void Checkout_and_submit_the_order(String productName){
        CartPage cartPage = productCatalog.goToCart();
        cartPage.verifyCart(productName);
        CheckOutPage checkoutPage = cartPage.checkoutButton();
        checkoutPage.enterCountryName("india");
        confirmationPage = checkoutPage.placeOrder();
    }

    @Then("{string} message is displayed on Confirmation page")
    public void message_is_displayed_on_Confirmation_page(String string){
        String confirmMessage = confirmationPage.confirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void messageIsDisplayed(String strArg1) {
        Assert.assertEquals(strArg1,landingPage.getErrorMessage());
        driver.close();
    }
}


