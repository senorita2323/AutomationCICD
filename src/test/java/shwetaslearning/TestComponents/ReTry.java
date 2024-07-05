package shwetaslearning.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReTry implements IRetryAnalyzer {

    int count =0;
    int maxTry =1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count<maxTry){
            count++;
            return true;//rerun until maxTry =1
        }
        return false;
    }
}
