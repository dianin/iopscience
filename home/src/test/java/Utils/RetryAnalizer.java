package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalizer implements IRetryAnalyzer{
    public boolean retry(ITestResult iTestResult) {
        return false;
    }
}
