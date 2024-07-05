package shwetaslearning.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import shwetaslearning.PageObjects.CartPage;
import shwetaslearning.PageObjects.CheckOutPage;
import shwetaslearning.PageObjects.ConfirmationPage;
import shwetaslearning.PageObjects.ProductCatalog;
import shwetaslearning.TestComponents.BaseTest;
import shwetaslearning.TestComponents.ReTry;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"},retryAnalyzer = ReTry.class)//retryAnalyzer to rerun this testcase if it is failed
    public void submitOrder2() throws IOException {

        String productName ="ZARA COAT 3";
        ProductCatalog productCatalog = landingPage.loginApplication("anjali679@gmail.com","Anjali");
        //div[aria-label='Incorrect email or password.']
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    }

    @Test
    public void productErrorValidation() throws IOException {

        String productName ="ZARA COAT 3";
        ProductCatalog productCatalog = landingPage.loginApplication("anjali679@gmail.com","Anjali@123");
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCart();
        cartPage.verifyCart(productName);
        CheckOutPage checkoutPage = cartPage.checkoutButton();
        checkoutPage.enterCountryName("india");
        ConfirmationPage confirmationPage = checkoutPage.placeOrder();
        String confirmMessage = confirmationPage.confirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

}
