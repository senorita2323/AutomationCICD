package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//cucumber->To run cucumber feature file we have to ultimately depend upon either TestNg or junit runners
//i.e. we are only using testng just to run our feature file
@CucumberOptions(features = "src/test/java/Cucumber",glue = "shwetaslearning.stepDefinition",
monochrome = true,tags = "@ErrorValidation",plugin = {"html:target/cucumber.html"})//glue is location of step definition file

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
