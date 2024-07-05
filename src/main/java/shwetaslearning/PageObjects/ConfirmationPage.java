package shwetaslearning.PageObjects;

import shwetaslearning.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;
    public ConfirmationPage(WebDriver driver){
        super(driver);
        //initialization
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".hero-primary")
    WebElement message;


    public String confirmationMessage(){
        return message.getText();
    }
}
