package shwetaslearning.tests;

import shwetaslearning.PageObjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {
    public static void main(String[] args) {

        String productName ="ZARA COAT 3";

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.get("https://rahulshettyacademy.com/client");

        LandingPage landingPage = new LandingPage(driver);

        driver.findElement(By.id("userEmail")).sendKeys("anjali679@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Anjali@123");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));

        //add to cart
        //all elements in the cart complete product
        List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
        //getting text of the elements and finding particular element
        WebElement prod = products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);
        //adding the product to card
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//        driver.close();
        // we can write id in form of css like #id
        //waiting for #toast-container to be visible it means we are waiting for object to be added to the cart

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        //ng-animating waiting this to be invisible, it is class name
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));


        //click on cart button
        WebElement cart = driver.findElement(By.cssSelector("[routerlink*='cart']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cart); //need to confirm this

        //to verify if product is added to cart or not
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));// if item present in cart it will return true
        Assert.assertTrue(match);//if match is true test case will passed otherwise fail
        //checkout
        driver.findElement(By.cssSelector(".totalRow button")).click();// css selector = .classname child tagname
        //select country by auto suggestive
//        Actions a = new Actions(driver);
//        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).click();//direct sendkeys is not working so just using click before it
        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("india");

        WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(8));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
        //place order
        driver.findElement(By.cssSelector(".action__submit")).click();
        //message displayed over the page
        Boolean confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText().contains("THANKYOU FOR THE ORDER");
        Assert.assertTrue(confirmMessage);//verify it




    }
}

