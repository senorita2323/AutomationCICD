package shwetaslearning.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shwetaslearning.AbstractComponents.AbstractComponent;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;
    public OrderPage(WebDriver driver){
        super(driver);
        //initialization
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="tbody tr td:nth-child(3)")
    List<WebElement> productNames;

    public Boolean verifyOrderDisplay(String productName){
        Boolean match = productNames.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
        return match;
    }


}
