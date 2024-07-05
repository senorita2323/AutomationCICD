package shwetaslearning.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shwetaslearning.PageObjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {

        //properties class
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/shwetaslearning/resources/GlobalData.properties");
        prop.load(fis);
        String BrowserName =System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");

        if (BrowserName.contains("chrome")){
            ChromeOptions options = new ChromeOptions();
            if (BrowserName.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//to run in fullscreen mode it is optional


        } else if (BrowserName.equalsIgnoreCase("firefox")) {
             driver = new FirefoxDriver();

        } else if (BrowserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        return driver;
    }

    @Test
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read JSON to String
        String jsonContent =  FileUtils.readFileToString(new File(filePath));
        //String to HashMap by using dependency Jackson Databind(add dependency first)
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
        });
        return data;//it returns list of HashMap {map,map}
    }

    public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";//will return path of string shot
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
       driver =  initializeDriver();
       landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
