package shwetaslearning.PageObjects;

import shwetaslearning.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;
    public CheckOutPage(WebDriver driver){
        super(driver);
        //initialization
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="input[placeholder='Select Country']")
    WebElement country;
    @FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted'][2]")
    WebElement selectCountry;
    @FindBy(css = ".action__submit")
    WebElement orderButton;

    By countryResult = By.cssSelector(".ta-results");

    public void enterCountryName(String countryName){
        country.click();
        country.sendKeys(countryName);
        waitForElementToAppear(countryResult);
        selectCountry.click();
    }

    public ConfirmationPage placeOrder(){
        orderButton.click();
        return new ConfirmationPage(driver);
    }


}
