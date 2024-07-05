package shwetaslearning.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shwetaslearning.PageObjects.*;
import shwetaslearning.TestComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName ="ZARA COAT 3";
    @Test(dataProvider = "getData",groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException {

        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalog.goToCart();
        cartPage.verifyCart(input.get("product"));
        CheckOutPage checkoutPage = cartPage.checkoutButton();
        checkoutPage.enterCountryName("india");
        ConfirmationPage confirmationPage = checkoutPage.placeOrder();
        String confirmMessage = confirmationPage.confirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest(){
        ProductCatalog productCatalog = landingPage.loginApplication("anjali679@gmail.com","Anjali@123");
        OrderPage orderPage = productCatalog.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/shwetaslearning/data/PurchaseOrder.json");
//        return new Object[][] {{data.get(0)},{data.get(1)}};
        return new Object[][] {{data.get(0)}};
    }



    //        HashMap<Object,Object> map = new HashMap<Object,Object>();
//        map.put("email","anjali679@gmail.com");
//        map.put("password","Anjali@123");
//        map.put("product","ZARA COAT 3");
//
//        HashMap<Object,Object> map1 = new HashMap<Object,Object>();
//        map1.put("email","shetty@gmail.com");
//        map1.put("password","Iamking@000");
//        map1.put("product","ADIDAS ORIGINAL");

}
