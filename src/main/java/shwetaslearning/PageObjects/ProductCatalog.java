package shwetaslearning.PageObjects;

import shwetaslearning.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponent {
    WebDriver driver;
    public ProductCatalog(WebDriver driver){
        super(driver);
        //initialization
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //PageFactory
    @FindBy(css="div.mb-3")
    List<WebElement> products;

    By productLocator = By.cssSelector("div.mb-3");
    By addToCartLocator = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");
    By spinner= By.cssSelector(".ng-animating");


    public List<WebElement> getProductList(){
        waitForElementToAppear(productLocator);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement prod = getProductList().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName){
        WebElement prod =  getProductByName(productName);
        prod.findElement(addToCartLocator).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }



}
