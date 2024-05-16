package pages;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 5; // Maximum number of retries

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) { // Retry only if the test failed
            if (retryCount < MAX_RETRY_COUNT) { // Check if retry count is within the limit
                retryCount++;
                return true; // Retry the test
            }
        }
        return false; // Don't retry if max retry count is reached or test passed
    }
}