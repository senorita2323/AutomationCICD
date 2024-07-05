package shwetaslearning.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import shwetaslearning.AbstractComponents.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        //initialization
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".cartSection h3")
    List<WebElement> cartProducts;
    @FindBy(css=".totalRow button")
    WebElement checkout;

    public void verifyCart(String productName){
//        Assert.assertEquals(productName,"ZARA COAT 3");
        boolean match = cartProducts.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
    }

    public CheckOutPage checkoutButton(){
        checkout.click();
        return new CheckOutPage(driver);

    }

}
